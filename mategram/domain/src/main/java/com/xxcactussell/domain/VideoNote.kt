package com.xxcactussell.domain

data class VideoNote(
    val duration: Int,
    val waveform: ByteArray,
    val length: Int,
    val minithumbnail: Minithumbnail? = null,
    val thumbnail: Thumbnail? = null,
    val speechRecognitionResult: SpeechRecognitionResult? = null,
    val video: File
) : Object
