package com.xxcactussell.data.utils.mappers.cancel

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun CancelPasswordReset.toData(): TdApi.CancelPasswordReset = TdApi.CancelPasswordReset(
)

fun CancelPreliminaryUploadFile.toData(): TdApi.CancelPreliminaryUploadFile = TdApi.CancelPreliminaryUploadFile(
    this.fileId
)

fun CancelRecoveryEmailAddressVerification.toData(): TdApi.CancelRecoveryEmailAddressVerification = TdApi.CancelRecoveryEmailAddressVerification(
)

