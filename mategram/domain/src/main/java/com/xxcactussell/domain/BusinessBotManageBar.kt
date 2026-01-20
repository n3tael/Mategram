package com.xxcactussell.domain

data class BusinessBotManageBar(
    val botUserId: Long,
    val manageUrl: String,
    val isBotPaused: Boolean,
    val canBotReply: Boolean
) : Object
