package com.xxcactussell.domain

data class SetSavedMessagesTagLabel(
    val tag: ReactionType,
    val label: String
) : Function
