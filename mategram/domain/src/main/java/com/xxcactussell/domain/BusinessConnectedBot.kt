package com.xxcactussell.domain

data class BusinessConnectedBot(
    val botUserId: Long,
    val recipients: BusinessRecipients,
    val rights: BusinessBotRights
) : Object
