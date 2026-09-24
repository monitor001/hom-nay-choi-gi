package com.gaucon.domain.picker

import com.gaucon.domain.model.Activity
import com.gaucon.domain.model.ContentStatus
import com.gaucon.domain.model.Domain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate

class DailyPickerTest {

    private val picker = DailyPicker()

    private fun act(
        id: String,
        min: Int,
        max: Int,
        domain: Domain,
        minutes: Int = 10,
    ) = Activity(
        id = id,
        ageMinMonths = min,
        ageMaxMonths = max,
        domains = listOf(domain),
        title = id,
        goal = "g",
        materials = listOf("m"),
        steps = listOf("1", "2", "3"),
        safety = null,
        durationMinutes = minutes,
        contentStatus = ContentStatus.DRAFT_UNREVIEWED,
    )

    @Test
    fun sameSeed_sameIdsAndOrder() {
        val activities = listOf(
            act("a1", 18, 36, Domain.PHYSICAL),
            act("a2", 18, 36, Domain.LANGUAGE),
            act("a3", 18, 36, Domain.COGNITIVE),
            act("a4", 18, 36, Domain.SELF_CARE),
            act("a5", 18, 36, Domain.AESTHETIC),
            act("a6", 18, 36, Domain.SOCIAL_EMOTIONAL),
        )
        val input = DailyPicker.Input(
            childId = "child-1",
            ageMonths = 24,
            today = LocalDate.of(2026, 9, 24),
            activities = activities,
        )
        val first = picker.pick(input)
        val second = picker.pick(input)
        assertEquals(first, second)
        assertTrue(first.size in 1..3)
    }

    @Test
    fun ageOutsideBand_excluded() {
        val activities = listOf(
            act("young", 12, 17, Domain.PHYSICAL),
            act("ok", 18, 36, Domain.LANGUAGE),
            act("old", 40, 60, Domain.COGNITIVE),
        )
        val ids = picker.pick(
            DailyPicker.Input(
                childId = "c",
                ageMonths = 24,
                today = LocalDate.of(2026, 9, 24),
                activities = activities,
            ),
        )
        assertEquals(listOf("ok"), ids)
    }

    @Test
    fun totalDuration_atMost30() {
        val activities = (1..10).map { i ->
            act("x$i", 18, 36, Domain.entries[i % 6], minutes = 12)
        }
        val ids = picker.pick(
            DailyPicker.Input(
                childId = "c2",
                ageMonths = 24,
                today = LocalDate.of(2026, 9, 24),
                activities = activities,
            ),
        )
        val total = activities.filter { it.id in ids }.sumOf { it.durationMinutes }
        assertTrue(total <= 30)
    }
}
