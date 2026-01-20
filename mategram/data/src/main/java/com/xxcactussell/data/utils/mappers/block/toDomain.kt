package com.xxcactussell.data.utils.mappers.block

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.BlockList.toDomain(): BlockList = when(this) {
    is TdApi.BlockListMain -> this.toDomain()
    is TdApi.BlockListStories -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.BlockListMain.toDomain(): BlockListMain = BlockListMain

fun TdApi.BlockListStories.toDomain(): BlockListStories = BlockListStories

