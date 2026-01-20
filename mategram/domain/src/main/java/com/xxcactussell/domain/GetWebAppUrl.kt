package com.xxcactussell.domain

data class GetWebAppUrl(
    val botUserId: Long,
    val url: String,
    val parameters: WebAppOpenParameters
) : Function
