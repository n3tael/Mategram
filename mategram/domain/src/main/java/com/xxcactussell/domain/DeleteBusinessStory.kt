package com.xxcactussell.domain

data class DeleteBusinessStory(
    val businessConnectionId: String,
    val storyId: Int
) : Function
