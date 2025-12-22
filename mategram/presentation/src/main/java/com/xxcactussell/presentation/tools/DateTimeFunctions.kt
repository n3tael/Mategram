package com.xxcactussell.presentation.tools

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.format.TextStyle
import java.time.temporal.WeekFields
import java.util.Locale

@Composable
fun formatTimestampToFullDateTime(timestamp: Int): String {
    val locale = LocalConfiguration.current.locales.get(0)
    val messageDateTime = LocalDateTime.ofInstant(
        Instant.ofEpochSecond(timestamp.toLong()),
        ZoneId.systemDefault()
    )
    val formatter = remember(locale) {
        DateTimeFormatter
            .ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT)
            .withLocale(locale)
    }

    return messageDateTime.format(formatter)
}

@Composable
fun formatTimestampToDateTime(timestamp: Int): String {
    val locale = LocalConfiguration.current.locales.get(0)

    val messageDateTime = remember(timestamp) {
        LocalDateTime.ofInstant(
            Instant.ofEpochSecond(timestamp.toLong()),
            ZoneId.systemDefault()
        )
    }

    val messageDate = messageDateTime.toLocalDate()
    val today = remember { LocalDate.now() }

    return when {
        messageDate == today -> {
            messageDateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(locale))
        }

        else -> {
            val formatter = remember(locale) { DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT).withLocale(locale) }
            messageDateTime.format(formatter)
        }
    }
}

@Composable
fun formatTimestampToDate(timestamp: Int): String {
    val locale = LocalConfiguration.current.locales.get(0)

    val messageDateTime = remember(timestamp) {
        LocalDateTime.ofInstant(
            Instant.ofEpochSecond(timestamp.toLong()),
            ZoneId.systemDefault()
        )
    }

    val messageDate = messageDateTime.toLocalDate()
    val today = remember { LocalDate.now() }
    val currentYear = remember { today.year }

    return when {
        messageDate == today -> {
            messageDateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(locale))
        }

        isThisWeek(messageDate, today, locale) -> {
            messageDateTime.dayOfWeek.getDisplayName(TextStyle.SHORT, locale)
        }

        messageDate.year == currentYear -> {
            val formatter = remember(locale) { DateTimeFormatter.ofPattern("d MMM", locale) }
            messageDateTime.format(formatter)
        }

        else -> {
            messageDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(locale))
        }
    }
}
private fun isThisWeek(messageDate: LocalDate, today: LocalDate, locale: Locale): Boolean {
    if (messageDate.isBefore(today.minusDays(6))) {
        return false
    }
    val weekFields = WeekFields.of(locale)
    val messageWeek = messageDate.get(weekFields.weekOfWeekBasedYear())
    val currentWeek = today.get(weekFields.weekOfWeekBasedYear())
    return messageDate.year == today.year && messageWeek == currentWeek
}

fun isSameDay(timestamp1: Int, timestamp2: Int): Boolean {
    val instant1 = Instant.ofEpochSecond(timestamp1.toLong())
    val instant2 = Instant.ofEpochSecond(timestamp2.toLong())

    val zoneId = ZoneId.systemDefault()

    val dateTime1 = LocalDateTime.ofInstant(instant1, zoneId)
    val dateTime2 = LocalDateTime.ofInstant(instant2, zoneId)

    return dateTime1.toLocalDate() == dateTime2.toLocalDate()
}