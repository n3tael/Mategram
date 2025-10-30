package com.xxcactussell.data.utils

import com.xxcactussell.domain.messages.model.WebPagePreview
import org.drinkless.tdlib.TdApi

fun TdApi.LinkPreview.toDomain(): WebPagePreview {
    return WebPagePreview(
        this.url,
        this.displayUrl,
        this.siteName,
        this.title,
        this.description?.toDomain(),
        this.author,
        "link", //TODO: MAKE IT LATER
        this.showLargeMedia,
        this.showMediaAboveDescription,
        this.instantViewVersion,
        this.showAboveText,
        this.skipConfirmation,
        this.hasLargeMedia
    )
}