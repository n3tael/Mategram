package com.xxcactussell.domain

data class GetBlockedMessageSenders(
    val blockList: BlockList,
    val offset: Int,
    val limit: Int
) : Function
