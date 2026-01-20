package com.xxcactussell.domain

data class FoundAffiliatePrograms(
    val totalCount: Int,
    val programs: List<FoundAffiliateProgram>,
    val nextOffset: String
) : Object
