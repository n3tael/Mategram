package com.xxcactussell.data.utils.mappers.identity

import com.xxcactussell.data.utils.mappers.date.toData
import com.xxcactussell.data.utils.mappers.dated.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun IdentityDocument.toData(): TdApi.IdentityDocument = TdApi.IdentityDocument(
    this.number,
    this.expirationDate?.toData(),
    this.frontSide.toData(),
    this.reverseSide?.toData(),
    this.selfie?.toData(),
    this.translation.map { it.toData() }.toTypedArray()
)

