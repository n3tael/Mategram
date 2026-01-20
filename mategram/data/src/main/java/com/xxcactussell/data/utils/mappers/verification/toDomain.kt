package com.xxcactussell.data.utils.mappers.verification

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.VerificationStatus.toDomain(): VerificationStatus = VerificationStatus(
    isVerified = this.isVerified,
    isScam = this.isScam,
    isFake = this.isFake,
    botVerificationIconCustomEmojiId = this.botVerificationIconCustomEmojiId
)

