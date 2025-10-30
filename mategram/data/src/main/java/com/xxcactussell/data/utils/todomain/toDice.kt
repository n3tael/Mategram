package com.xxcactussell.data.utils.todomain

import com.xxcactussell.data.utils.toDomain
import com.xxcactussell.domain.messages.model.DiceStickers
import org.drinkless.tdlib.TdApi

fun TdApi.DiceStickers.toDomain() : DiceStickers {
    return when(this.constructor) {
        TdApi.DiceStickersRegular.CONSTRUCTOR -> {
            DiceStickers.Regular(
                (this as TdApi.DiceStickersRegular).sticker.toDomain()
            )
        }
        TdApi.DiceStickersSlotMachine.CONSTRUCTOR -> {
            DiceStickers.SlotMachine(
                (this as TdApi.DiceStickersSlotMachine).background.toDomain(),
                this.lever.toDomain(),
                this.leftReel.toDomain(),
                this.centerReel.toDomain(),
                this.rightReel.toDomain()

            )
        }
        else -> DiceStickers.Unknown
    }
}