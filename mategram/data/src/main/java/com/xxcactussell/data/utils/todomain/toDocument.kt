package com.xxcactussell.data.utils.todomain

import com.xxcactussell.data.utils.toDomain
import com.xxcactussell.domain.messages.model.Document
import org.drinkless.tdlib.TdApi

fun TdApi.Document.toDomain() : Document {
    return Document(
        fileName = this.fileName,
        mimeType = this.mimeType,
        miniThumbnail = this.minithumbnail?.toDomain(),
        thumbnail = this.thumbnail?.toDomain(),
        document = this.document.toDomain()
    )
}