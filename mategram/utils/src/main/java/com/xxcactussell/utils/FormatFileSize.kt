package com.xxcactussell.utils

import java.text.DecimalFormat
import kotlin.math.log10
import kotlin.math.pow

fun Long.formatFileSize(): String {
    if (this <= 0) return "0 B"

    val units = arrayOf("B", "KB", "MB", "GB", "TB", "PB")

    val digitGroups = (log10(this.toDouble()) / log10(1024.0)).toInt()

    if (digitGroups >= units.size) return "$this B"

    val value = this / 1024.0.pow(digitGroups.toDouble())

    val decimalFormat = DecimalFormat("#,##0.##")

    return "${decimalFormat.format(value)} ${units[digitGroups]}"
}