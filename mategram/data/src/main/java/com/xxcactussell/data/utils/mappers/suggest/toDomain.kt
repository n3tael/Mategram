package com.xxcactussell.data.utils.mappers.suggest

import com.xxcactussell.data.utils.mappers.input.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.SuggestUserProfilePhoto.toDomain(): SuggestUserProfilePhoto = SuggestUserProfilePhoto(
    userId = this.userId,
    photo = this.photo.toDomain()
)

