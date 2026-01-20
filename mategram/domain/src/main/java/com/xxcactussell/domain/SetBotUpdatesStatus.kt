package com.xxcactussell.domain

data class SetBotUpdatesStatus(
    val pendingUpdateCount: Int,
    val errorMessage: String
) : Function
