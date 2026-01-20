package com.xxcactussell.domain

data class GetVideoChatStreamSegment(
    val groupCallId: Int,
    val timeOffset: Long,
    val scale: Int,
    val channelId: Int,
    val videoQuality: GroupCallVideoQuality
) : Function
