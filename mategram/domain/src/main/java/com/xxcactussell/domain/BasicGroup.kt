package com.xxcactussell.domain

data class BasicGroup(
    val id: Long,
    val memberCount: Int,
    val status: ChatMemberStatus,
    val isActive: Boolean,
    val upgradedToSupergroupId: Long
) : Object
