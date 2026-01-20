package com.xxcactussell.domain

data class GetWebAppLinkUrl(
    val chatId: Long,
    val botUserId: Long,
    val webAppShortName: String,
    val startParameter: String,
    val allowWriteAccess: Boolean,
    val parameters: WebAppOpenParameters
) : Function
