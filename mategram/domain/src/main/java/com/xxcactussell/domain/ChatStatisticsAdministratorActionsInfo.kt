package com.xxcactussell.domain

data class ChatStatisticsAdministratorActionsInfo(
    val userId: Long,
    val deletedMessageCount: Int,
    val bannedUserCount: Int,
    val restrictedUserCount: Int
) : Object
