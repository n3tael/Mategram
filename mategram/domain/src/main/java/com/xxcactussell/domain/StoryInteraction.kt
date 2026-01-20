package com.xxcactussell.domain

data class StoryInteraction(
    val actorId: MessageSender,
    val interactionDate: Int,
    val blockList: BlockList? = null,
    val type: StoryInteractionType
) : Object
