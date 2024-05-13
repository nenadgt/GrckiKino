package com.grckikino.helpers

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DateTimeFormatHelper {

    fun format(
        instant: Instant,
        pattern: String,
        zoneId: ZoneId = ZoneId.systemDefault()
    ): String {
        val formatter = DateTimeFormatter.ofPattern(pattern).withZone(zoneId)
        return formatter.format(instant)
    }
}