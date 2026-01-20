package com.xxcactussell.domain

data class GetReceivedGifts(
    val businessConnectionId: String,
    val ownerId: MessageSender,
    val collectionId: Int,
    val excludeUnsaved: Boolean,
    val excludeSaved: Boolean,
    val excludeUnlimited: Boolean,
    val excludeUpgradable: Boolean,
    val excludeNonUpgradable: Boolean,
    val excludeUpgraded: Boolean,
    val sortByPrice: Boolean,
    val offset: String,
    val limit: Int
) : Function
