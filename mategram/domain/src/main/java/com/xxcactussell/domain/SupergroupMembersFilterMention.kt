package com.xxcactussell.domain

data class SupergroupMembersFilterMention(
    val query: String,
    val messageThreadId: Long
) : SupergroupMembersFilter
