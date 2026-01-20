package com.xxcactussell.domain

data class ChatFolder(
    val name: ChatFolderName,
    val icon: ChatFolderIcon? = null,
    val colorId: Int,
    val isShareable: Boolean,
    val pinnedChatIds: LongArray,
    val includedChatIds: LongArray,
    val excludedChatIds: LongArray,
    val excludeMuted: Boolean,
    val excludeRead: Boolean,
    val excludeArchived: Boolean,
    val includeContacts: Boolean,
    val includeNonContacts: Boolean,
    val includeBots: Boolean,
    val includeGroups: Boolean,
    val includeChannels: Boolean
) : Object
