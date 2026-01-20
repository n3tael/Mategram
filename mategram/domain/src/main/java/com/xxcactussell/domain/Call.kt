package com.xxcactussell.domain

data class Call(
    val id: Int,
    val userId: Long,
    val isOutgoing: Boolean,
    val isVideo: Boolean,
    val state: CallState
) : Object
