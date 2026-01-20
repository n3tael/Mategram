package com.xxcactussell.domain

data class InternalLinkTypeBotStartInGroup(
    val botUsername: String,
    val startParameter: String,
    val administratorRights: ChatAdministratorRights? = null
) : InternalLinkType
