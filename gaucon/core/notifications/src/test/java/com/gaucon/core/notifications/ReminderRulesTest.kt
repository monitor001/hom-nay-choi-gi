package com.gaucon.core.notifications

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

class NextFireCalculatorTest {
    @Test
    fun beforeTarget_sameDay() {
        val now = ZonedDateTime.of(2026, 9, 24, 10, 0, 0, 0, ZoneId.of("Asia/Ho_Chi_Minh"))
        val next = NextFireCalculator.nextDailyAt(LocalTime.of(19, 30), now)
        assertEquals(24, next.dayOfMonth)
        assertEquals(19, next.hour)
        assertEquals(30, next.minute)
    }

    @Test
    fun afterTarget_nextDay() {
        val now = ZonedDateTime.of(2026, 9, 24, 20, 0, 0, 0, ZoneId.of("Asia/Ho_Chi_Minh"))
        val next = NextFireCalculator.nextDailyAt(LocalTime.of(19, 30), now)
        assertEquals(25, next.dayOfMonth)
    }
}

class ReminderRulesTest {
    private val quiet = QuietHours(LocalTime.of(21, 30), LocalTime.of(7, 0))

    @Test
    fun r1_quietHours_skips() {
        val result = ReminderRules.evaluate(
            hasNotificationPermission = true,
            now = LocalDateTime.of(2026, 9, 24, 22, 0),
            quietHours = quiet,
            isUserDefined = false,
            completedCountToday = 0,
            shownSuggestedToday = 0,
            maxSuggestedPerDay = 2,
        )
        assertEquals(ReminderEvalResult.SKIP_QUIET_HOURS, result)
    }

    @Test
    fun r4_alreadyDone_skips() {
        val result = ReminderRules.evaluate(
            hasNotificationPermission = true,
            now = LocalDateTime.of(2026, 9, 24, 19, 30),
            quietHours = quiet,
            isUserDefined = false,
            completedCountToday = 1,
            shownSuggestedToday = 0,
            maxSuggestedPerDay = 2,
        )
        assertEquals(ReminderEvalResult.SKIP_ALREADY_DONE, result)
    }

    @Test
    fun r2_maxSuggested_skips() {
        val result = ReminderRules.evaluate(
            hasNotificationPermission = true,
            now = LocalDateTime.of(2026, 9, 24, 19, 30),
            quietHours = quiet,
            isUserDefined = false,
            completedCountToday = 0,
            shownSuggestedToday = 2,
            maxSuggestedPerDay = 2,
        )
        assertEquals(ReminderEvalResult.SKIP_MAX_SUGGESTED, result)
    }

    @Test
    fun show_whenClear() {
        val result = ReminderRules.evaluate(
            hasNotificationPermission = true,
            now = LocalDateTime.of(2026, 9, 24, 19, 30),
            quietHours = quiet,
            isUserDefined = false,
            completedCountToday = 0,
            shownSuggestedToday = 0,
            maxSuggestedPerDay = 2,
        )
        assertEquals(ReminderEvalResult.SHOW, result)
    }

    @Test
    fun overnightQuiet_contains() {
        assertTrue(quiet.contains(LocalTime.of(22, 0)))
        assertTrue(quiet.contains(LocalTime.of(6, 0)))
        assertFalse(quiet.contains(LocalTime.of(12, 0)))
    }

    @Test
    fun r3_softPrompt_afterThree() {
        var state = AutoDegradeState()
        repeat(3) { state = state.onShownWithoutOpen() }
        assertTrue(state.shouldSoftPrompt())
        state = state.onOpenedOrDone()
        assertFalse(state.shouldSoftPrompt())
    }
}
