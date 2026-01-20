package com.xxcactussell.data.utils.mappers.block

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun BlockList.toData(): TdApi.BlockList = when(this) {
    is BlockListMain -> this.toData()
    is BlockListStories -> this.toData()
}

fun BlockListMain.toData(): TdApi.BlockListMain = TdApi.BlockListMain(
)

fun BlockListStories.toData(): TdApi.BlockListStories = TdApi.BlockListStories(
)

