package com.xxcactussell.domain

data class GetStatisticalGraph(
    val chatId: Long,
    val token: String,
    val x: Long
) : Function
