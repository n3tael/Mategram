package com.xxcactussell.domain

data class SearchAffiliatePrograms(
    val affiliate: AffiliateType,
    val sortOrder: AffiliateProgramSortOrder,
    val offset: String,
    val limit: Int
) : Function
