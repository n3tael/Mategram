package com.xxcactussell.domain

data class AddedReactions(
    val totalCount: Int,
    val reactions: List<AddedReaction>,
    val nextOffset: String
) : Object
