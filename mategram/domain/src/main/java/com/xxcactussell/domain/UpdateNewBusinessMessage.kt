package com.xxcactussell.domain

data class UpdateNewBusinessMessage(
    val connectionId: String,
    val message: BusinessMessage
) : Update
