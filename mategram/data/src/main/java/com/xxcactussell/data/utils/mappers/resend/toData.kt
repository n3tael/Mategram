package com.xxcactussell.data.utils.mappers.resend

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ResendAuthenticationCode.toData(): TdApi.ResendAuthenticationCode = TdApi.ResendAuthenticationCode(
    this.reason.toData()
)

fun ResendCodeReason.toData(): TdApi.ResendCodeReason = when(this) {
    is ResendCodeReasonUserRequest -> this.toData()
    is ResendCodeReasonVerificationFailed -> this.toData()
}

fun ResendCodeReasonUserRequest.toData(): TdApi.ResendCodeReasonUserRequest = TdApi.ResendCodeReasonUserRequest(
)

fun ResendCodeReasonVerificationFailed.toData(): TdApi.ResendCodeReasonVerificationFailed = TdApi.ResendCodeReasonVerificationFailed(
    this.errorMessage
)

fun ResendEmailAddressVerificationCode.toData(): TdApi.ResendEmailAddressVerificationCode = TdApi.ResendEmailAddressVerificationCode(
)

fun ResendLoginEmailAddressCode.toData(): TdApi.ResendLoginEmailAddressCode = TdApi.ResendLoginEmailAddressCode(
)

fun ResendPhoneNumberCode.toData(): TdApi.ResendPhoneNumberCode = TdApi.ResendPhoneNumberCode(
    this.reason.toData()
)

fun ResendRecoveryEmailAddressCode.toData(): TdApi.ResendRecoveryEmailAddressCode = TdApi.ResendRecoveryEmailAddressCode(
)

