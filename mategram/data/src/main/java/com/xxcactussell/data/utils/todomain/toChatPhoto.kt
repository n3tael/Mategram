package com.xxcactussell.data.utils

import com.xxcactussell.data.utils.todomain.toDomain
import com.xxcactussell.domain.chats.model.ChatPhoto
import org.drinkless.tdlib.TdApi

fun TdApi.ChatPhotoInfo.toDomain() : ChatPhoto {
    return ChatPhoto(
        small.toDomain(),
        big.toDomain(),
        hasAnimation,
        isPersonal
    )
}