package com.xxcactussell.domain

data class ChatAdministratorRights(
    val canManageChat: Boolean,
    val canChangeInfo: Boolean,
    val canPostMessages: Boolean,
    val canEditMessages: Boolean,
    val canDeleteMessages: Boolean,
    val canInviteUsers: Boolean,
    val canRestrictMembers: Boolean,
    val canPinMessages: Boolean,
    val canManageTopics: Boolean,
    val canPromoteMembers: Boolean,
    val canManageVideoChats: Boolean,
    val canPostStories: Boolean,
    val canEditStories: Boolean,
    val canDeleteStories: Boolean,
    val canManageDirectMessages: Boolean,
    val isAnonymous: Boolean
) : Object
