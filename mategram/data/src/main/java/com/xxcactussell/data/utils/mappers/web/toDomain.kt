package com.xxcactussell.data.utils.mappers.web

import com.xxcactussell.data.utils.mappers.internal.toDomain
import com.xxcactussell.data.utils.mappers.page.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.WebPageInstantView.toDomain(): WebPageInstantView = WebPageInstantView(
    pageBlocks = this.pageBlocks.map { it.toDomain() },
    viewCount = this.viewCount,
    version = this.version,
    isRtl = this.isRtl,
    isFull = this.isFull,
    feedbackLink = this.feedbackLink.toDomain()
)

