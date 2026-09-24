package com.gaucon.core.common

/** Re-export for Android modules that prefer core:common. */
fun ageMonths(birthDate: java.time.LocalDate, today: java.time.LocalDate): Int =
    com.gaucon.domain.ageMonths(birthDate, today)
