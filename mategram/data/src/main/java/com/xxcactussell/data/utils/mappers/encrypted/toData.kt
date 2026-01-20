package com.xxcactussell.data.utils.mappers.encrypted

import com.xxcactussell.data.utils.mappers.dated.toData
import com.xxcactussell.data.utils.mappers.passport.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun EncryptedCredentials.toData(): TdApi.EncryptedCredentials = TdApi.EncryptedCredentials(
    this.data,
    this.hash,
    this.secret
)

fun EncryptedPassportElement.toData(): TdApi.EncryptedPassportElement = TdApi.EncryptedPassportElement(
    this.type.toData(),
    this.data,
    this.frontSide.toData(),
    this.reverseSide?.toData(),
    this.selfie?.toData(),
    this.translation.map { it.toData() }.toTypedArray(),
    this.files.map { it.toData() }.toTypedArray(),
    this.value,
    this.hash
)

