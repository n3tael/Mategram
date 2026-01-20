package com.xxcactussell.domain

data class ConnectedAffiliateProgram(
    val url: String,
    val botUserId: Long,
    val parameters: AffiliateProgramParameters,
    val connectionDate: Int,
    val isDisconnected: Boolean,
    val userCount: Long,
    val revenueStarCount: Long
) : Object
