package com.xxcactussell.data.utils.mappers.encrypted

import com.xxcactussell.data.utils.mappers.dated.toDomain
import com.xxcactussell.data.utils.mappers.passport.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.EncryptedCredentials.toDomain(): EncryptedCredentials = EncryptedCredentials(
    data = this.data,
    hash = this.hash,
    secret = this.secret
)

fun TdApi.EncryptedPassportElement.toDomain(): EncryptedPassportElement = EncryptedPassportElement(
    type = this.type.toDomain(),
    data = this.data,
    frontSide = this.frontSide.toDomain(),
    reverseSide = this.reverseSide?.toDomain(),
    selfie = this.selfie?.toDomain(),
    translation = this.translation.map { it.toDomain() },
    files = this.files.map { it.toDomain() },
    value = this.value,
    hash = this.hash
)

