package com.xxcactussell.domain

data class ChatActionBarReportAddBlock(
    val canUnarchive: Boolean,
    val accountInfo: AccountInfo? = null
) : ChatActionBar
