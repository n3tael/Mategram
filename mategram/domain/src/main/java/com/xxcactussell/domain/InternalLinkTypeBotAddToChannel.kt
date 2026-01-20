package com.xxcactussell.domain

data class InternalLinkTypeBotAddToChannel(
    val botUsername: String,
    val administratorRights: ChatAdministratorRights
) : InternalLinkType
