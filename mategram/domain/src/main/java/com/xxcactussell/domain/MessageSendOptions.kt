package com.xxcactussell.domain

data class MessageSendOptions(
    val directMessagesChatTopicId: Long,
    val suggestedPostInfo: InputSuggestedPostInfo,
    val disableNotification: Boolean,
    val fromBackground: Boolean,
    val protectContent: Boolean,
    val allowPaidBroadcast: Boolean,
    val paidMessageStarCount: Long,
    val updateOrderOfInstalledStickerSets: Boolean,
    val schedulingState: MessageSchedulingState,
    val effectId: Long,
    val sendingId: Int,
    val onlyPreview: Boolean
) : Object
