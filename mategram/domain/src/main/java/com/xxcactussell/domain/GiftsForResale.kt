package com.xxcactussell.domain

data class GiftsForResale(
    val totalCount: Int,
    val gifts: List<GiftForResale>,
    val models: List<UpgradedGiftModelCount>,
    val symbols: List<UpgradedGiftSymbolCount>,
    val backdrops: List<UpgradedGiftBackdropCount>,
    val nextOffset: String
) : Object
