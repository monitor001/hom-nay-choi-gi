package com.gaucon.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class AgeMonthsTest {
    @Test
    fun march15_2024_to_sep24_2026_is_30() {
        val birth = LocalDate.of(2024, 3, 15)
        val today = LocalDate.of(2026, 9, 24)
        assertEquals(30, ageMonths(birth, today))
    }

    @Test
    fun dayBeforeBirthday_subtractsOne() {
        val birth = LocalDate.of(2024, 3, 15)
        val today = LocalDate.of(2026, 3, 14)
        assertEquals(23, ageMonths(birth, today))
    }

    @Test
    fun futureBirth_clampedToZero() {
        val birth = LocalDate.of(2030, 1, 1)
        val today = LocalDate.of(2026, 9, 24)
        assertEquals(0, ageMonths(birth, today))
    }
}
