package com.xxcactussell.domain

data class KeyboardButtonTypeRequestChat(
    val id: Int,
    val chatIsChannel: Boolean,
    val restrictChatIsForum: Boolean,
    val chatIsForum: Boolean,
    val restrictChatHasUsername: Boolean,
    val chatHasUsername: Boolean,
    val chatIsCreated: Boolean,
    val userAdministratorRights: ChatAdministratorRights? = null,
    val botAdministratorRights: ChatAdministratorRights? = null,
    val botIsMember: Boolean,
    val requestTitle: Boolean,
    val requestUsername: Boolean,
    val requestPhoto: Boolean
) : KeyboardButtonType
