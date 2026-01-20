package com.xxcactussell.domain

data class ChatEventPermissionsChanged(
    val oldPermissions: ChatPermissions,
    val newPermissions: ChatPermissions
) : ChatEventAction
