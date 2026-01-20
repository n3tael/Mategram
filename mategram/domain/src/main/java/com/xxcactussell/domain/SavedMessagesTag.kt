package com.xxcactussell.domain

data class SavedMessagesTag(
    val tag: ReactionType,
    val label: String,
    val count: Int
) : Object
