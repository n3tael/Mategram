package com.xxcactussell.domain

data class GetConnectedAffiliateProgram(
    val affiliate: AffiliateType,
    val botUserId: Long
) : Function
