package com.xxcactussell.data.utils.mappers.identity

import com.xxcactussell.data.utils.mappers.date.toDomain
import com.xxcactussell.data.utils.mappers.dated.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.IdentityDocument.toDomain(): IdentityDocument = IdentityDocument(
    number = this.number,
    expirationDate = this.expirationDate?.toDomain(),
    frontSide = this.frontSide.toDomain(),
    reverseSide = this.reverseSide?.toDomain(),
    selfie = this.selfie?.toDomain(),
    translation = this.translation.map { it.toDomain() }
)

