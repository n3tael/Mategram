package com.xxcactussell.data.utils.mappers.deep

import com.xxcactussell.data.utils.mappers.formatted.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun DeepLinkInfo.toData(): TdApi.DeepLinkInfo = TdApi.DeepLinkInfo(
    this.text.toData(),
    this.needUpdateApplication
)

