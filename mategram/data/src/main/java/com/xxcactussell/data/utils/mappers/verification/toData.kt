package com.xxcactussell.data.utils.mappers.verification

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun VerificationStatus.toData(): TdApi.VerificationStatus = TdApi.VerificationStatus(
    this.isVerified,
    this.isScam,
    this.isFake,
    this.botVerificationIconCustomEmojiId
)

