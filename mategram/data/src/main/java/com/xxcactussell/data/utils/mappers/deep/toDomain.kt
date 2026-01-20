package com.xxcactussell.data.utils.mappers.deep

import com.xxcactussell.data.utils.mappers.formatted.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.DeepLinkInfo.toDomain(): DeepLinkInfo = DeepLinkInfo(
    text = this.text.toDomain(),
    needUpdateApplication = this.needUpdateApplication
)

