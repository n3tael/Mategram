package com.xxcactussell.domain

data class GetStoryInteractions(
    val storyId: Int,
    val query: String,
    val onlyContacts: Boolean,
    val preferForwards: Boolean,
    val preferWithReaction: Boolean,
    val offset: String,
    val limit: Int
) : Function
