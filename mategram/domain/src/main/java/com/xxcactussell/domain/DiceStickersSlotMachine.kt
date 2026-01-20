package com.xxcactussell.domain

data class DiceStickersSlotMachine(
    val background: Sticker,
    val lever: Sticker,
    val leftReel: Sticker,
    val centerReel: Sticker,
    val rightReel: Sticker
) : DiceStickers
