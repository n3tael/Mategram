package com.xxcactussell.data.utils.mappers.product

import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.photo.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ProductInfo.toDomain(): ProductInfo = ProductInfo(
    title = this.title,
    description = this.description.toDomain(),
    photo = this.photo?.toDomain()
)

