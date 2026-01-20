package com.xxcactussell.data.utils.mappers.premium

import com.xxcactussell.data.utils.mappers.business.toDomain
import com.xxcactussell.data.utils.mappers.internal.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.monetization.toDomain
import com.xxcactussell.data.utils.mappers.sticker.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.PremiumGiftCodeInfo.toDomain(): PremiumGiftCodeInfo = PremiumGiftCodeInfo(
    creatorId = this.creatorId?.toDomain(),
    creationDate = this.creationDate,
    isFromGiveaway = this.isFromGiveaway,
    giveawayMessageId = this.giveawayMessageId,
    monthCount = this.monthCount,
    userId = this.userId,
    useDate = this.useDate
)

fun TdApi.PremiumGiftPaymentOption.toDomain(): PremiumGiftPaymentOption = PremiumGiftPaymentOption(
    currency = this.currency,
    amount = this.amount,
    starCount = this.starCount,
    discountPercentage = this.discountPercentage,
    monthCount = this.monthCount,
    storeProductId = this.storeProductId,
    sticker = this.sticker?.toDomain()
)

fun TdApi.PremiumGiftPaymentOptions.toDomain(): PremiumGiftPaymentOptions = PremiumGiftPaymentOptions(
    options = this.options.map { it.toDomain() }
)

fun TdApi.PremiumPaymentOption.toDomain(): PremiumPaymentOption = PremiumPaymentOption(
    currency = this.currency,
    amount = this.amount,
    discountPercentage = this.discountPercentage,
    monthCount = this.monthCount,
    storeProductId = this.storeProductId,
    paymentLink = this.paymentLink?.toDomain()
)

fun TdApi.PremiumSource.toDomain(): PremiumSource = when(this) {
    is TdApi.PremiumSourceLimitExceeded -> this.toDomain()
    is TdApi.PremiumSourceFeature -> this.toDomain()
    is TdApi.PremiumSourceBusinessFeature -> this.toDomain()
    is TdApi.PremiumSourceStoryFeature -> this.toDomain()
    is TdApi.PremiumSourceLink -> this.toDomain()
    is TdApi.PremiumSourceSettings -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PremiumSourceBusinessFeature.toDomain(): PremiumSourceBusinessFeature = PremiumSourceBusinessFeature(
    feature = this.feature.toDomain()
)

fun TdApi.PremiumSourceFeature.toDomain(): PremiumSourceFeature = PremiumSourceFeature(
    feature = this.feature.toDomain()
)

fun TdApi.PremiumSourceLimitExceeded.toDomain(): PremiumSourceLimitExceeded = PremiumSourceLimitExceeded(
    limitType = this.limitType.toDomain()
)

fun TdApi.PremiumSourceLink.toDomain(): PremiumSourceLink = PremiumSourceLink(
    referrer = this.referrer
)

fun TdApi.PremiumSourceSettings.toDomain(): PremiumSourceSettings = PremiumSourceSettings

fun TdApi.PremiumSourceStoryFeature.toDomain(): PremiumSourceStoryFeature = PremiumSourceStoryFeature(
    feature = this.feature.toDomain()
)

fun TdApi.PremiumStoryFeature.toDomain(): PremiumStoryFeature = when(this) {
    is TdApi.PremiumStoryFeaturePriorityOrder -> this.toDomain()
    is TdApi.PremiumStoryFeatureStealthMode -> this.toDomain()
    is TdApi.PremiumStoryFeaturePermanentViewsHistory -> this.toDomain()
    is TdApi.PremiumStoryFeatureCustomExpirationDuration -> this.toDomain()
    is TdApi.PremiumStoryFeatureSaveStories -> this.toDomain()
    is TdApi.PremiumStoryFeatureLinksAndFormatting -> this.toDomain()
    is TdApi.PremiumStoryFeatureVideoQuality -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PremiumStoryFeatureCustomExpirationDuration.toDomain(): PremiumStoryFeatureCustomExpirationDuration = PremiumStoryFeatureCustomExpirationDuration

fun TdApi.PremiumStoryFeatureLinksAndFormatting.toDomain(): PremiumStoryFeatureLinksAndFormatting = PremiumStoryFeatureLinksAndFormatting

fun TdApi.PremiumStoryFeaturePermanentViewsHistory.toDomain(): PremiumStoryFeaturePermanentViewsHistory = PremiumStoryFeaturePermanentViewsHistory

fun TdApi.PremiumStoryFeaturePriorityOrder.toDomain(): PremiumStoryFeaturePriorityOrder = PremiumStoryFeaturePriorityOrder

fun TdApi.PremiumStoryFeatureSaveStories.toDomain(): PremiumStoryFeatureSaveStories = PremiumStoryFeatureSaveStories

fun TdApi.PremiumStoryFeatureStealthMode.toDomain(): PremiumStoryFeatureStealthMode = PremiumStoryFeatureStealthMode

fun TdApi.PremiumStoryFeatureVideoQuality.toDomain(): PremiumStoryFeatureVideoQuality = PremiumStoryFeatureVideoQuality

