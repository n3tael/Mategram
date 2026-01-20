package com.xxcactussell.domain

data class UpdateNewCustomQuery(
    val id: Long,
    val data: String,
    val timeout: Int
) : Update
