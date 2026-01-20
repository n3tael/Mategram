package com.xxcactussell.domain

data class ReportSupergroupSpam(
    val supergroupId: Long,
    val messageIds: LongArray
) : Function
