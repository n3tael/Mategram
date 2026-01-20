package com.xxcactussell.domain

data class UpdateSpeechRecognitionTrial(
    val maxMediaDuration: Int,
    val weeklyCount: Int,
    val leftCount: Int,
    val nextResetDate: Int
) : Update
