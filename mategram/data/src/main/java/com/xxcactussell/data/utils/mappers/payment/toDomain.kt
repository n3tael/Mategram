package com.xxcactussell.data.utils.mappers.payment

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.PaymentProvider.toDomain(): PaymentProvider = when(this) {
    is TdApi.PaymentProviderSmartGlocal -> this.toDomain()
    is TdApi.PaymentProviderStripe -> this.toDomain()
    is TdApi.PaymentProviderOther -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PaymentProviderOther.toDomain(): PaymentProviderOther = PaymentProviderOther(
    url = this.url
)

fun TdApi.PaymentProviderSmartGlocal.toDomain(): PaymentProviderSmartGlocal = PaymentProviderSmartGlocal(
    publicToken = this.publicToken,
    tokenizeUrl = this.tokenizeUrl
)

fun TdApi.PaymentProviderStripe.toDomain(): PaymentProviderStripe = PaymentProviderStripe(
    publishableKey = this.publishableKey,
    needCountry = this.needCountry,
    needPostalCode = this.needPostalCode,
    needCardholderName = this.needCardholderName
)

