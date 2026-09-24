package com.gaucon.core.common

import java.time.Clock
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

interface AppClock {
    fun nowInstant(): Instant
    fun today(): LocalDate
    fun zoneId(): ZoneId
}

class SystemAppClock(
    private val clock: Clock = Clock.systemDefaultZone(),
) : AppClock {
    override fun nowInstant(): Instant = Instant.now(clock)
    override fun today(): LocalDate = LocalDate.now(clock)
    override fun zoneId(): ZoneId = clock.zone
}
