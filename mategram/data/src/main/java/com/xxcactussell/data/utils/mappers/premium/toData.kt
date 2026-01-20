package com.xxcactussell.data.utils.mappers.premium

import com.xxcactussell.data.utils.mappers.business.toData
import com.xxcactussell.data.utils.mappers.internal.toData
import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.monetization.toData
import com.xxcactussell.data.utils.mappers.sticker.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun PremiumGiftCodeInfo.toData(): TdApi.PremiumGiftCodeInfo = TdApi.PremiumGiftCodeInfo(
    this.creatorId?.toData(),
    this.creationDate,
    this.isFromGiveaway,
    this.giveawayMessageId,
    this.monthCount,
    this.userId,
    this.useDate
)

fun PremiumGiftPaymentOption.toData(): TdApi.PremiumGiftPaymentOption = TdApi.PremiumGiftPaymentOption(
    this.currency,
    this.amount,
    this.starCount,
    this.discountPercentage,
    this.monthCount,
    this.storeProductId,
    this.sticker?.toData()
)

fun PremiumGiftPaymentOptions.toData(): TdApi.PremiumGiftPaymentOptions = TdApi.PremiumGiftPaymentOptions(
    this.options.map { it.toData() }.toTypedArray()
)

fun PremiumPaymentOption.toData(): TdApi.PremiumPaymentOption = TdApi.PremiumPaymentOption(
    this.currency,
    this.amount,
    this.discountPercentage,
    this.monthCount,
    this.storeProductId,
    this.paymentLink?.toData()
)

fun PremiumSource.toData(): TdApi.PremiumSource = when(this) {
    is PremiumSourceLimitExceeded -> this.toData()
    is PremiumSourceFeature -> this.toData()
    is PremiumSourceBusinessFeature -> this.toData()
    is PremiumSourceStoryFeature -> this.toData()
    is PremiumSourceLink -> this.toData()
    is PremiumSourceSettings -> this.toData()
}

fun PremiumSourceBusinessFeature.toData(): TdApi.PremiumSourceBusinessFeature = TdApi.PremiumSourceBusinessFeature(
    this.feature.toData()
)

fun PremiumSourceFeature.toData(): TdApi.PremiumSourceFeature = TdApi.PremiumSourceFeature(
    this.feature.toData()
)

fun PremiumSourceLimitExceeded.toData(): TdApi.PremiumSourceLimitExceeded = TdApi.PremiumSourceLimitExceeded(
    this.limitType.toData()
)

fun PremiumSourceLink.toData(): TdApi.PremiumSourceLink = TdApi.PremiumSourceLink(
    this.referrer
)

fun PremiumSourceSettings.toData(): TdApi.PremiumSourceSettings = TdApi.PremiumSourceSettings(
)

fun PremiumSourceStoryFeature.toData(): TdApi.PremiumSourceStoryFeature = TdApi.PremiumSourceStoryFeature(
    this.feature.toData()
)

fun PremiumStoryFeature.toData(): TdApi.PremiumStoryFeature = when(this) {
    is PremiumStoryFeaturePriorityOrder -> this.toData()
    is PremiumStoryFeatureStealthMode -> this.toData()
    is PremiumStoryFeaturePermanentViewsHistory -> this.toData()
    is PremiumStoryFeatureCustomExpirationDuration -> this.toData()
    is PremiumStoryFeatureSaveStories -> this.toData()
    is PremiumStoryFeatureLinksAndFormatting -> this.toData()
    is PremiumStoryFeatureVideoQuality -> this.toData()
}

fun PremiumStoryFeatureCustomExpirationDuration.toData(): TdApi.PremiumStoryFeatureCustomExpirationDuration = TdApi.PremiumStoryFeatureCustomExpirationDuration(
)

fun PremiumStoryFeatureLinksAndFormatting.toData(): TdApi.PremiumStoryFeatureLinksAndFormatting = TdApi.PremiumStoryFeatureLinksAndFormatting(
)

fun PremiumStoryFeaturePermanentViewsHistory.toData(): TdApi.PremiumStoryFeaturePermanentViewsHistory = TdApi.PremiumStoryFeaturePermanentViewsHistory(
)

fun PremiumStoryFeaturePriorityOrder.toData(): TdApi.PremiumStoryFeaturePriorityOrder = TdApi.PremiumStoryFeaturePriorityOrder(
)

fun PremiumStoryFeatureSaveStories.toData(): TdApi.PremiumStoryFeatureSaveStories = TdApi.PremiumStoryFeatureSaveStories(
)

fun PremiumStoryFeatureStealthMode.toData(): TdApi.PremiumStoryFeatureStealthMode = TdApi.PremiumStoryFeatureStealthMode(
)

fun PremiumStoryFeatureVideoQuality.toData(): TdApi.PremiumStoryFeatureVideoQuality = TdApi.PremiumStoryFeatureVideoQuality(
)

