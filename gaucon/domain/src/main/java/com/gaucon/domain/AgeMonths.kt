package com.gaucon.domain

import java.time.LocalDate

/**
 * ageMonths per CONSENSUS §2.2 / BUILD_READY §2.3.
 */
fun ageMonths(birthDate: LocalDate, today: LocalDate): Int {
    var months = (today.year - birthDate.year) * 12 + (today.monthValue - birthDate.monthValue)
    if (today.dayOfMonth < birthDate.dayOfMonth) {
        months -= 1
    }
    if (months < 0) months = 0
    return months
}
