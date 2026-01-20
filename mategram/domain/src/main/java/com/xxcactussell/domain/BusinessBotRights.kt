package com.xxcactussell.domain

data class BusinessBotRights(
    val canReply: Boolean,
    val canReadMessages: Boolean,
    val canDeleteSentMessages: Boolean,
    val canDeleteAllMessages: Boolean,
    val canEditName: Boolean,
    val canEditBio: Boolean,
    val canEditProfilePhoto: Boolean,
    val canEditUsername: Boolean,
    val canViewGiftsAndStars: Boolean,
    val canSellGifts: Boolean,
    val canChangeGiftSettings: Boolean,
    val canTransferAndUpgradeGifts: Boolean,
    val canTransferStars: Boolean,
    val canManageStories: Boolean
) : Object
