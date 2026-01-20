package com.xxcactussell.data.utils.mappers.dice

import com.xxcactussell.data.utils.mappers.sticker.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun DiceStickers.toData(): TdApi.DiceStickers = when(this) {
    is DiceStickersRegular -> this.toData()
    is DiceStickersSlotMachine -> this.toData()
}

fun DiceStickersRegular.toData(): TdApi.DiceStickersRegular = TdApi.DiceStickersRegular(
    this.sticker.toData()
)

fun DiceStickersSlotMachine.toData(): TdApi.DiceStickersSlotMachine = TdApi.DiceStickersSlotMachine(
    this.background.toData(),
    this.lever.toData(),
    this.leftReel.toData(),
    this.centerReel.toData(),
    this.rightReel.toData()
)

