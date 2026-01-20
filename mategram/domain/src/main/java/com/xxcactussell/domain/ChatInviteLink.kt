package com.xxcactussell.domain

data class ChatInviteLink(
    val inviteLink: String,
    val name: String,
    val creatorUserId: Long,
    val date: Int,
    val editDate: Int,
    val expirationDate: Int,
    val subscriptionPricing: StarSubscriptionPricing? = null,
    val memberLimit: Int,
    val memberCount: Int,
    val expiredMemberCount: Int,
    val pendingJoinRequestCount: Int,
    val createsJoinRequest: Boolean,
    val isPrimary: Boolean,
    val isRevoked: Boolean
) : Object
