package com.xxcactussell.domain

data class VoiceNote(
    val duration: Int,
    val waveform: ByteArray,
    val mimeType: String,
    val speechRecognitionResult: SpeechRecognitionResult? = null,
    val voice: File
) : Object
