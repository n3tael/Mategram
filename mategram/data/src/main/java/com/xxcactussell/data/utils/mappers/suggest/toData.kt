package com.xxcactussell.data.utils.mappers.suggest

import com.xxcactussell.data.utils.mappers.input.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun SuggestUserProfilePhoto.toData(): TdApi.SuggestUserProfilePhoto = TdApi.SuggestUserProfilePhoto(
    this.userId,
    this.photo.toData()
)

