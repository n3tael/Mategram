package com.xxcactussell.domain

data class GiftUpgradePreview(
    val models: List<UpgradedGiftModel>,
    val symbols: List<UpgradedGiftSymbol>,
    val backdrops: List<UpgradedGiftBackdrop>
) : Object
