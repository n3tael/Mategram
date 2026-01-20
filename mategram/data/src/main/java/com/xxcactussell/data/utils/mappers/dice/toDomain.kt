package com.xxcactussell.data.utils.mappers.dice

import com.xxcactussell.data.utils.mappers.sticker.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.DiceStickers.toDomain(): DiceStickers = when(this) {
    is TdApi.DiceStickersRegular -> this.toDomain()
    is TdApi.DiceStickersSlotMachine -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.DiceStickersRegular.toDomain(): DiceStickersRegular = DiceStickersRegular(
    sticker = this.sticker.toDomain()
)

fun TdApi.DiceStickersSlotMachine.toDomain(): DiceStickersSlotMachine = DiceStickersSlotMachine(
    background = this.background.toDomain(),
    lever = this.lever.toDomain(),
    leftReel = this.leftReel.toDomain(),
    centerReel = this.centerReel.toDomain(),
    rightReel = this.rightReel.toDomain()
)

