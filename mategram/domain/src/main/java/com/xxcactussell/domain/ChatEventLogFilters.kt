package com.xxcactussell.domain

data class ChatEventLogFilters(
    val messageEdits: Boolean,
    val messageDeletions: Boolean,
    val messagePins: Boolean,
    val memberJoins: Boolean,
    val memberLeaves: Boolean,
    val memberInvites: Boolean,
    val memberPromotions: Boolean,
    val memberRestrictions: Boolean,
    val infoChanges: Boolean,
    val settingChanges: Boolean,
    val inviteLinkChanges: Boolean,
    val videoChatChanges: Boolean,
    val forumChanges: Boolean,
    val subscriptionExtensions: Boolean
) : Object
