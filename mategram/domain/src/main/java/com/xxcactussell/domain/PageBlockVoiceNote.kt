package com.xxcactussell.domain

data class PageBlockVoiceNote(
    val voiceNote: VoiceNote? = null,
    val caption: PageBlockCaption
) : PageBlock
