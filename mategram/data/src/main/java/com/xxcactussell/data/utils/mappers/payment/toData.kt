package com.xxcactussell.data.utils.mappers.payment

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun PaymentProvider.toData(): TdApi.PaymentProvider = when(this) {
    is PaymentProviderSmartGlocal -> this.toData()
    is PaymentProviderStripe -> this.toData()
    is PaymentProviderOther -> this.toData()
}

fun PaymentProviderOther.toData(): TdApi.PaymentProviderOther = TdApi.PaymentProviderOther(
    this.url
)

fun PaymentProviderSmartGlocal.toData(): TdApi.PaymentProviderSmartGlocal = TdApi.PaymentProviderSmartGlocal(
    this.publicToken,
    this.tokenizeUrl
)

fun PaymentProviderStripe.toData(): TdApi.PaymentProviderStripe = TdApi.PaymentProviderStripe(
    this.publishableKey,
    this.needCountry,
    this.needPostalCode,
    this.needCardholderName
)

