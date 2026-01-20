package com.xxcactussell.domain

data class ChatMemberStatusAdministrator(
    val customTitle: String,
    val canBeEdited: Boolean,
    val rights: ChatAdministratorRights
) : ChatMemberStatus
