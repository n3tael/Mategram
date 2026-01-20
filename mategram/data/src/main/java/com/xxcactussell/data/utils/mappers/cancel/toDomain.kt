package com.xxcactussell.data.utils.mappers.cancel

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.CancelPasswordReset.toDomain(): CancelPasswordReset = CancelPasswordReset

fun TdApi.CancelPreliminaryUploadFile.toDomain(): CancelPreliminaryUploadFile = CancelPreliminaryUploadFile(
    fileId = this.fileId
)

fun TdApi.CancelRecoveryEmailAddressVerification.toDomain(): CancelRecoveryEmailAddressVerification = CancelRecoveryEmailAddressVerification

