package com.xxcactussell.domain

data class PushMessageContentVoiceNote(
    val voiceNote: VoiceNote? = null,
    val isPinned: Boolean
) : PushMessageContent
