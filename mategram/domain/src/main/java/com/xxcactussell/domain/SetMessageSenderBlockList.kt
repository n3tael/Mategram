package com.xxcactussell.domain

data class SetMessageSenderBlockList(
    val senderId: MessageSender,
    val blockList: BlockList
) : Function
