package com.xxcactussell.domain

data class AnswerCallbackQuery(
    val callbackQueryId: Long,
    val text: String,
    val showAlert: Boolean,
    val url: String,
    val cacheTime: Int
) : Function
