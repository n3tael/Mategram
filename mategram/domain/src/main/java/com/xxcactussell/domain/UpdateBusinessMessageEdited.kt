package com.xxcactussell.domain

data class UpdateBusinessMessageEdited(
    val connectionId: String,
    val message: BusinessMessage
) : Update
