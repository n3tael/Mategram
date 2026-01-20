package com.xxcactussell.data.utils.mappers.product

import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.photo.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ProductInfo.toData(): TdApi.ProductInfo = TdApi.ProductInfo(
    this.title,
    this.description.toData(),
    this.photo?.toData()
)

