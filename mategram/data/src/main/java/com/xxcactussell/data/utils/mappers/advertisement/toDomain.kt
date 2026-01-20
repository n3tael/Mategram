package com.xxcactussell.data.utils.mappers.advertisement

import com.xxcactussell.data.utils.mappers.photo.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

fun TdApi.AdvertisementSponsor.toDomain(): AdvertisementSponsor = AdvertisementSponsor(
    url = this.url,
    photo = this.photo?.toDomain(),
    info = this.info
)

