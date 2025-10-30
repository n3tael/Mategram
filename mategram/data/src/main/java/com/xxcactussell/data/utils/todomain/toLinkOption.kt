package com.xxcactussell.data.utils

import com.xxcactussell.domain.messages.model.LinkOption
import org.drinkless.tdlib.TdApi

fun TdApi.LinkPreviewOptions.toDomain(): LinkOption {
    return LinkOption(
        this.isDisabled,
        this.url,
        this.forceSmallMedia,
        this.forceLargeMedia,
        this.showAboveText
    )
}