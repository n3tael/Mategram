package com.xxcactussell.domain

data class TonTransactionTypeFragmentDeposit(
    val isGift: Boolean,
    val sticker: Sticker? = null
) : TonTransactionType
