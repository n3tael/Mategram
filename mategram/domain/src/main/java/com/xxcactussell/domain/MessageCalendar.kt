package com.xxcactussell.domain

data class MessageCalendar(
    val totalCount: Int,
    val days: List<MessageCalendarDay>
) : Object
