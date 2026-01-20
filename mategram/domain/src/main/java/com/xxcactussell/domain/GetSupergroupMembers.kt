package com.xxcactussell.domain

data class GetSupergroupMembers(
    val supergroupId: Long,
    val filter: SupergroupMembersFilter,
    val offset: Int,
    val limit: Int
) : Function
