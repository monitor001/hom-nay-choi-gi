package com.gaucon.domain.usecase

import com.gaucon.domain.model.GxKinds
import com.gaucon.domain.model.GxLedgerEntry
import com.gaucon.domain.model.GxRates
import com.gaucon.domain.repository.ActivityLogRepository
import com.gaucon.domain.repository.GxLedgerRepository
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters
import java.util.UUID

class GxRewardsUseCase(
    private val ledger: GxLedgerRepository,
    private val activityLogRepository: ActivityLogRepository,
) {
    data class EarnResult(val awarded: Int, val message: String?)

    suspend fun balance(childId: String): Int = ledger.balance(childId)

    suspend fun onActivityCompleted(childId: String, activityId: String): EarnResult {
        val zone = ZoneId.systemDefault()
        val today = LocalDate.now(zone)
        val (dayStart, dayEnd) = dayBounds(today, zone)
        val (weekStart, weekEnd) = weekBounds(today, zone)
        var awarded = 0
        val notes = mutableListOf<String>()

        val already = ledger.countKindRefBetween(
            childId, GxKinds.COMPLETE, activityId, dayStart, dayEnd,
        )
        val completesToday = ledger.completeEarnCountBetween(childId, dayStart, dayEnd)
        val lastAt = ledger.lastCompleteEarnAt(childId) ?: 0L
        val now = System.currentTimeMillis()
        val dayEarned = ledger.earnedBetween(childId, dayStart, dayEnd)
        val weekEarned = ledger.earnedBetween(childId, weekStart, weekEnd)

        var denyHint: String? = null
        when {
            already > 0 -> denyHint = "Hôm nay đã nhận GX cho hoạt động này rồi."
            completesToday >= GxRates.MAX_COMPLETE_EARNS_PER_DAY ->
                denyHint = "Hôm nay đủ nhịp thưởng rồi — phần còn lại là chơi thật."
            now - lastAt < GxRates.COOLDOWN_MS ->
                denyHint = "Chờ một chút rồi chơi tiếp nhé (tránh bấm liên tục)."
            remainingCap(dayEarned, weekEarned, GxRates.COMPLETE) <= 0 ->
                denyHint = "Đã chạm trần GX hôm nay/tuần — vẫn ghi nhận buổi chơi."
        }

        if (already == 0 &&
            completesToday < GxRates.MAX_COMPLETE_EARNS_PER_DAY &&
            now - lastAt >= GxRates.COOLDOWN_MS
        ) {
            val room = remainingCap(dayEarned, weekEarned, GxRates.COMPLETE)
            if (room > 0) {
                insert(childId, room, GxKinds.COMPLETE, activityId, "Hoàn thành hoạt động")
                awarded += room
                notes += "+$room GX hoàn thành HĐ"
                denyHint = null
            }
        }

        // Streak: days with ≥1 activity log (use logs, not only earn)
        val streak = computePlayStreak(childId, today, zone)
        if (streak >= 2) {
            val streakRef = today.toString()
            val streakAlready = ledger.countKindRefBetween(
                childId, GxKinds.STREAK, streakRef, dayStart, dayEnd,
            )
            if (streakAlready == 0) {
                val dayEarned2 = ledger.earnedBetween(childId, dayStart, dayEnd)
                val weekEarned2 = ledger.earnedBetween(childId, weekStart, weekEnd)
                val room = remainingCap(dayEarned2, weekEarned2, GxRates.STREAK_DAY)
                if (room > 0) {
                    insert(childId, room, GxKinds.STREAK, streakRef, "Chuỗi ngày có chơi")
                    awarded += room
                    notes += "+$room GX chuỗi ngày"
                }
            }
        }
        if (streak == 7) {
            val ref = "streak7-${today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))}"
            val (ws, we) = weekBounds(today, zone)
            val had = ledger.countKindRefBetween(childId, GxKinds.STREAK_7, ref, ws, we)
            if (had == 0) {
                val dayEarned3 = ledger.earnedBetween(childId, dayStart, dayEnd)
                val weekEarned3 = ledger.earnedBetween(childId, weekStart, weekEnd)
                val room = remainingCap(dayEarned3, weekEarned3, GxRates.STREAK_7)
                if (room > 0) {
                    insert(childId, room, GxKinds.STREAK_7, ref, "Mốc 7 ngày có chơi")
                    awarded += room
                    notes += "+$room GX mốc 7 ngày"
                }
            }
        }

        // Weekly KPI ≥3 completes (activity logs this week)
        val weekLogs = activityLogRepository.recentForChild(childId, weekStart)
            .count { it.completedAt.toEpochMilli() < weekEnd }
        if (weekLogs >= 3) {
            val kpiRef = "kpi-${today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))}"
            val hadKpi = ledger.countKindRefBetween(childId, GxKinds.WEEK_KPI, kpiRef, weekStart, weekEnd)
            if (hadKpi == 0) {
                val dayEarned4 = ledger.earnedBetween(childId, dayStart, dayEnd)
                val weekEarned4 = ledger.earnedBetween(childId, weekStart, weekEnd)
                val room = remainingCap(dayEarned4, weekEarned4, GxRates.WEEK_KPI)
                if (room > 0) {
                    insert(childId, room, GxKinds.WEEK_KPI, kpiRef, "Đủ 3 hoạt động tuần")
                    awarded += room
                    notes += "+$room GX nhiệm vụ tuần"
                }
            }
        }

        val msg = when {
            awarded > 0 -> notes.joinToString(" · ")
            denyHint != null -> denyHint
            else -> "Đã ghi nhận buổi chơi."
        }
        return EarnResult(awarded, msg)
    }

    suspend fun onJournalSaved(childId: String, entryId: String): EarnResult {
        val zone = ZoneId.systemDefault()
        val today = LocalDate.now(zone)
        val (dayStart, dayEnd) = dayBounds(today, zone)
        val (weekStart, weekEnd) = weekBounds(today, zone)
        val hadCompleteToday = ledger.completeEarnCountBetween(childId, dayStart, dayEnd) > 0 ||
            activityLogRepository.completedCountToday(childId, today.toString()) > 0
        if (!hadCompleteToday) {
            return EarnResult(0, null)
        }
        val already = ledger.countKindRefBetween(childId, GxKinds.JOURNAL, entryId, dayStart, dayEnd)
        if (already > 0) return EarnResult(0, null)
        val completes = maxOf(
            ledger.completeEarnCountBetween(childId, dayStart, dayEnd),
            activityLogRepository.completedCountToday(childId, today.toString()),
        )
        val journalEarns = countJournalEarns(childId, dayStart, dayEnd)
        if (journalEarns >= completes) return EarnResult(0, null)

        val dayEarned = ledger.earnedBetween(childId, dayStart, dayEnd)
        val weekEarned = ledger.earnedBetween(childId, weekStart, weekEnd)
        val room = remainingCap(dayEarned, weekEarned, GxRates.JOURNAL)
        if (room <= 0) return EarnResult(0, "Hôm nay đủ nhịp thưởng rồi.")
        insert(childId, room, GxKinds.JOURNAL, entryId, "Nhật ký khoảnh khắc")
        return EarnResult(room, "+$room GX nhật ký")
    }

    suspend fun redeem(childId: String, itemId: String, cost: Int, title: String): EarnResult {
        if (cost <= 0) return EarnResult(0, "Mục không hợp lệ.")
        val bal = ledger.balance(childId)
        if (bal < cost) return EarnResult(0, "Chưa đủ Gấu Xu (cần $cost, đang có $bal).")
        insert(childId, -cost, GxKinds.REDEEM, itemId, "Đổi: $title")
        return EarnResult(-cost, "Đã ghi nhận đổi «$title». Bạn tự chuẩn bị ngoài đời nhé.")
    }

    private suspend fun countJournalEarns(childId: String, start: Long, end: Long): Int {
        // Use count with a dummy approach: recent and filter — or add dao query.
        return ledger.recent(childId, 80).count {
            it.kind == GxKinds.JOURNAL &&
                it.createdAt.toEpochMilli() in start until end
        }
    }

    private suspend fun computePlayStreak(childId: String, today: LocalDate, zone: ZoneId): Int {
        var streak = 0
        var day = today
        repeat(30) {
            val (s, e) = dayBounds(day, zone)
            val count = activityLogRepository.recentForChild(childId, s)
                .count { it.completedAt.toEpochMilli() < e }
            if (count > 0) {
                streak++
                day = day.minusDays(1)
            } else {
                return streak
            }
        }
        return streak
    }

    private fun remainingCap(dayEarned: Int, weekEarned: Int, want: Int): Int {
        val dayLeft = (GxRates.DAILY_CAP - dayEarned).coerceAtLeast(0)
        val weekLeft = (GxRates.WEEKLY_CAP - weekEarned).coerceAtLeast(0)
        return minOf(want, dayLeft, weekLeft)
    }

    private suspend fun insert(
        childId: String,
        amount: Int,
        kind: String,
        refId: String?,
        note: String,
    ) {
        ledger.insert(
            GxLedgerEntry(
                id = UUID.randomUUID().toString(),
                childId = childId,
                createdAt = Instant.now(),
                amount = amount,
                kind = kind,
                refId = refId,
                note = note,
            ),
        )
    }

    private fun dayBounds(day: LocalDate, zone: ZoneId): Pair<Long, Long> {
        val start = day.atStartOfDay(zone).toInstant().toEpochMilli()
        val end = day.plusDays(1).atStartOfDay(zone).toInstant().toEpochMilli()
        return start to end
    }

    private fun weekBounds(day: LocalDate, zone: ZoneId): Pair<Long, Long> {
        val monday = day.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        val start = monday.atStartOfDay(zone).toInstant().toEpochMilli()
        val end = monday.plusDays(7).atStartOfDay(zone).toInstant().toEpochMilli()
        return start to end
    }
}
