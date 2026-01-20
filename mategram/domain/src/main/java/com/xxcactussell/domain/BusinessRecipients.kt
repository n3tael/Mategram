package com.xxcactussell.domain

data class BusinessRecipients(
    val chatIds: LongArray,
    val excludedChatIds: LongArray,
    val selectExistingChats: Boolean,
    val selectNewChats: Boolean,
    val selectContacts: Boolean,
    val selectNonContacts: Boolean,
    val excludeSelected: Boolean
) : Object
