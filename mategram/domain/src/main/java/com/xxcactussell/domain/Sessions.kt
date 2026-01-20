package com.xxcactussell.domain

data class Sessions(
    val sessions: List<Session>,
    val inactiveSessionTtlDays: Int
) : Object
