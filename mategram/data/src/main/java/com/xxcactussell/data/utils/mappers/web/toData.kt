package com.xxcactussell.data.utils.mappers.web

import com.xxcactussell.data.utils.mappers.internal.toData
import com.xxcactussell.data.utils.mappers.page.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun WebPageInstantView.toData(): TdApi.WebPageInstantView = TdApi.WebPageInstantView(
    this.pageBlocks.map { it.toData() }.toTypedArray(),
    this.viewCount,
    this.version,
    this.isRtl,
    this.isFull,
    this.feedbackLink.toData()
)

