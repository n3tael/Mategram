package com.xxcactussell.domain

data class StartGroupCallRecording(
    val groupCallId: Int,
    val title: String,
    val recordVideo: Boolean,
    val usePortraitOrientation: Boolean
) : Function
