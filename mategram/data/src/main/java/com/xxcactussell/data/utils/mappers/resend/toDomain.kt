package com.xxcactussell.data.utils.mappers.resend

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ResendAuthenticationCode.toDomain(): ResendAuthenticationCode = ResendAuthenticationCode(
    reason = this.reason.toDomain()
)

fun TdApi.ResendCodeReason.toDomain(): ResendCodeReason = when(this) {
    is TdApi.ResendCodeReasonUserRequest -> this.toDomain()
    is TdApi.ResendCodeReasonVerificationFailed -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ResendCodeReasonUserRequest.toDomain(): ResendCodeReasonUserRequest = ResendCodeReasonUserRequest

fun TdApi.ResendCodeReasonVerificationFailed.toDomain(): ResendCodeReasonVerificationFailed = ResendCodeReasonVerificationFailed(
    errorMessage = this.errorMessage
)

fun TdApi.ResendEmailAddressVerificationCode.toDomain(): ResendEmailAddressVerificationCode = ResendEmailAddressVerificationCode

fun TdApi.ResendLoginEmailAddressCode.toDomain(): ResendLoginEmailAddressCode = ResendLoginEmailAddressCode

fun TdApi.ResendPhoneNumberCode.toDomain(): ResendPhoneNumberCode = ResendPhoneNumberCode(
    reason = this.reason.toDomain()
)

fun TdApi.ResendRecoveryEmailAddressCode.toDomain(): ResendRecoveryEmailAddressCode = ResendRecoveryEmailAddressCode

