package com.xxcactussell.data.utils.mappers.advertisement

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*
import com.xxcactussell.data.utils.mappers.photo.toData

// Mappers: Domain -> TdApi

fun AdvertisementSponsor.toData(): TdApi.AdvertisementSponsor = TdApi.AdvertisementSponsor(
    this.url,
    this.photo?.toData(),
    this.info
)

