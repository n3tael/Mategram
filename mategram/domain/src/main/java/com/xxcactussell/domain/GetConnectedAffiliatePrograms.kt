package com.xxcactussell.domain

data class GetConnectedAffiliatePrograms(
    val affiliate: AffiliateType,
    val offset: String,
    val limit: Int
) : Function
