package com.xxcactussell.domain

data class ConnectedAffiliatePrograms(
    val totalCount: Int,
    val programs: List<ConnectedAffiliateProgram>,
    val nextOffset: String
) : Object
