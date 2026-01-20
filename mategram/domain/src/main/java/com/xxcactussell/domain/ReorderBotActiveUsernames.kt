package com.xxcactussell.domain

data class ReorderBotActiveUsernames(
    val botUserId: Long,
    val usernames: List<String>
) : Function
