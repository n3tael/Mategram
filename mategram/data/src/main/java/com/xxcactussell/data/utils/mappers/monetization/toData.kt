package com.xxcactussell.data.utils.mappers.monetization

import com.xxcactussell.data.utils.mappers.accepted.toData
import com.xxcactussell.data.utils.mappers.address.toData
import com.xxcactussell.data.utils.mappers.affiliate.toData
import com.xxcactussell.data.utils.mappers.animation.toData
import com.xxcactussell.data.utils.mappers.business.toData
import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.input.toData
import com.xxcactussell.data.utils.mappers.internal.toData
import com.xxcactussell.data.utils.mappers.labeled.toData
import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.paid.toData
import com.xxcactussell.data.utils.mappers.payment.toData
import com.xxcactussell.data.utils.mappers.premium.toData
import com.xxcactussell.data.utils.mappers.product.toData
import com.xxcactussell.data.utils.mappers.revenue.toData
import com.xxcactussell.data.utils.mappers.saved.toData
import com.xxcactussell.data.utils.mappers.shipping.toData
import com.xxcactussell.data.utils.mappers.star.toData
import com.xxcactussell.data.utils.mappers.sticker.toData
import com.xxcactussell.data.utils.mappers.telegram.toData
import com.xxcactussell.data.utils.mappers.theme.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun DeleteSavedCredentials.toData(): TdApi.DeleteSavedCredentials = TdApi.DeleteSavedCredentials(
)

fun DeleteSavedOrderInfo.toData(): TdApi.DeleteSavedOrderInfo = TdApi.DeleteSavedOrderInfo(
)

fun GetAvailableGifts.toData(): TdApi.GetAvailableGifts = TdApi.GetAvailableGifts(
)

fun GetPaymentForm.toData(): TdApi.GetPaymentForm = TdApi.GetPaymentForm(
    this.inputInvoice.toData(),
    this.theme.toData()
)

fun GetPaymentReceipt.toData(): TdApi.GetPaymentReceipt = TdApi.GetPaymentReceipt(
    this.chatId,
    this.messageId
)

fun GetPremiumFeatures.toData(): TdApi.GetPremiumFeatures = TdApi.GetPremiumFeatures(
    this.source.toData()
)

fun GetPremiumLimit.toData(): TdApi.GetPremiumLimit = TdApi.GetPremiumLimit(
    this.limitType.toData()
)

fun GetPremiumState.toData(): TdApi.GetPremiumState = TdApi.GetPremiumState(
)

fun GetSavedOrderInfo.toData(): TdApi.GetSavedOrderInfo = TdApi.GetSavedOrderInfo(
)

fun GetStarRevenueStatistics.toData(): TdApi.GetStarRevenueStatistics = TdApi.GetStarRevenueStatistics(
    this.ownerId.toData(),
    this.isDark
)

fun GetStarWithdrawalUrl.toData(): TdApi.GetStarWithdrawalUrl = TdApi.GetStarWithdrawalUrl(
    this.ownerId.toData(),
    this.starCount,
    this.password
)

fun Gift.toData(): TdApi.Gift = TdApi.Gift(
    this.id,
    this.publisherChatId,
    this.sticker.toData(),
    this.starCount,
    this.defaultSellStarCount,
    this.upgradeStarCount,
    this.isForBirthday,
    this.isPremium,
    this.nextSendDate,
    this.userLimits?.toData(),
    this.overallLimits?.toData(),
    this.firstSendDate,
    this.lastSendDate
)

fun GiftChatTheme.toData(): TdApi.GiftChatTheme = TdApi.GiftChatTheme(
    this.gift.toData(),
    this.lightSettings.toData(),
    this.darkSettings.toData()
)

fun GiftChatThemes.toData(): TdApi.GiftChatThemes = TdApi.GiftChatThemes(
    this.themes.map { it.toData() }.toTypedArray(),
    this.nextOffset
)

fun GiftCollection.toData(): TdApi.GiftCollection = TdApi.GiftCollection(
    this.id,
    this.name,
    this.icon?.toData(),
    this.giftCount
)

fun GiftCollections.toData(): TdApi.GiftCollections = TdApi.GiftCollections(
    this.collections.map { it.toData() }.toTypedArray()
)

fun GiftForResale.toData(): TdApi.GiftForResale = TdApi.GiftForResale(
    this.gift.toData(),
    this.receivedGiftId
)

fun GiftForResaleOrder.toData(): TdApi.GiftForResaleOrder = when(this) {
    is GiftForResaleOrderPrice -> this.toData()
    is GiftForResaleOrderPriceChangeDate -> this.toData()
    is GiftForResaleOrderNumber -> this.toData()
}

fun GiftForResaleOrderNumber.toData(): TdApi.GiftForResaleOrderNumber = TdApi.GiftForResaleOrderNumber(
)

fun GiftForResaleOrderPrice.toData(): TdApi.GiftForResaleOrderPrice = TdApi.GiftForResaleOrderPrice(
)

fun GiftForResaleOrderPriceChangeDate.toData(): TdApi.GiftForResaleOrderPriceChangeDate = TdApi.GiftForResaleOrderPriceChangeDate(
)

fun GiftPremiumWithStars.toData(): TdApi.GiftPremiumWithStars = TdApi.GiftPremiumWithStars(
    this.userId,
    this.starCount,
    this.monthCount,
    this.text.toData()
)

fun GiftPurchaseLimits.toData(): TdApi.GiftPurchaseLimits = TdApi.GiftPurchaseLimits(
    this.totalCount,
    this.remainingCount
)

fun GiftResaleParameters.toData(): TdApi.GiftResaleParameters = TdApi.GiftResaleParameters(
    this.starCount,
    this.toncoinCentCount,
    this.toncoinOnly
)

fun GiftResalePrice.toData(): TdApi.GiftResalePrice = when(this) {
    is GiftResalePriceStar -> this.toData()
    is GiftResalePriceTon -> this.toData()
}

fun GiftResalePriceStar.toData(): TdApi.GiftResalePriceStar = TdApi.GiftResalePriceStar(
    this.starCount
)

fun GiftResalePriceTon.toData(): TdApi.GiftResalePriceTon = TdApi.GiftResalePriceTon(
    this.toncoinCentCount
)

fun GiftResaleResult.toData(): TdApi.GiftResaleResult = when(this) {
    is GiftResaleResultOk -> this.toData()
    is GiftResaleResultPriceIncreased -> this.toData()
}

fun GiftResaleResultOk.toData(): TdApi.GiftResaleResultOk = TdApi.GiftResaleResultOk(
)

fun GiftResaleResultPriceIncreased.toData(): TdApi.GiftResaleResultPriceIncreased = TdApi.GiftResaleResultPriceIncreased(
    this.price.toData()
)

fun GiftSettings.toData(): TdApi.GiftSettings = TdApi.GiftSettings(
    this.showGiftButton,
    this.acceptedGiftTypes.toData()
)

fun GiftUpgradePreview.toData(): TdApi.GiftUpgradePreview = TdApi.GiftUpgradePreview(
    this.models.map { it.toData() }.toTypedArray(),
    this.symbols.map { it.toData() }.toTypedArray(),
    this.backdrops.map { it.toData() }.toTypedArray()
)

fun GiftsForResale.toData(): TdApi.GiftsForResale = TdApi.GiftsForResale(
    this.totalCount,
    this.gifts.map { it.toData() }.toTypedArray(),
    this.models.map { it.toData() }.toTypedArray(),
    this.symbols.map { it.toData() }.toTypedArray(),
    this.backdrops.map { it.toData() }.toTypedArray(),
    this.nextOffset
)

fun InputInvoice.toData(): TdApi.InputInvoice = when(this) {
    is InputInvoiceMessage -> this.toData()
    is InputInvoiceName -> this.toData()
    is InputInvoiceTelegram -> this.toData()
}

fun InputInvoiceMessage.toData(): TdApi.InputInvoiceMessage = TdApi.InputInvoiceMessage(
    this.chatId,
    this.messageId
)

fun InputInvoiceName.toData(): TdApi.InputInvoiceName = TdApi.InputInvoiceName(
    this.name
)

fun InputInvoiceTelegram.toData(): TdApi.InputInvoiceTelegram = TdApi.InputInvoiceTelegram(
    this.purpose.toData()
)

fun InternalLinkTypePremiumFeatures.toData(): TdApi.InternalLinkTypePremiumFeatures = TdApi.InternalLinkTypePremiumFeatures(
    this.referrer
)

fun Invoice.toData(): TdApi.Invoice = TdApi.Invoice(
    this.currency,
    this.priceParts.map { it.toData() }.toTypedArray(),
    this.subscriptionPeriod,
    this.maxTipAmount,
    this.suggestedTipAmounts,
    this.recurringPaymentTermsOfServiceUrl,
    this.termsOfServiceUrl,
    this.isTest,
    this.needName,
    this.needPhoneNumber,
    this.needEmailAddress,
    this.needShippingAddress,
    this.sendPhoneNumberToProvider,
    this.sendEmailAddressToProvider,
    this.isFlexible
)

fun OrderInfo.toData(): TdApi.OrderInfo = TdApi.OrderInfo(
    this.name,
    this.phoneNumber,
    this.emailAddress,
    this.shippingAddress?.toData()
)

fun PaymentForm.toData(): TdApi.PaymentForm = TdApi.PaymentForm(
    this.id,
    this.type.toData(),
    this.sellerBotUserId,
    this.productInfo.toData()
)

fun PaymentFormType.toData(): TdApi.PaymentFormType = when(this) {
    is PaymentFormTypeRegular -> this.toData()
    is PaymentFormTypeStars -> this.toData()
    is PaymentFormTypeStarSubscription -> this.toData()
}

fun PaymentFormTypeRegular.toData(): TdApi.PaymentFormTypeRegular = TdApi.PaymentFormTypeRegular(
    this.invoice.toData(),
    this.paymentProviderUserId,
    this.paymentProvider.toData(),
    this.additionalPaymentOptions.map { it.toData() }.toTypedArray(),
    this.savedOrderInfo?.toData(),
    this.savedCredentials.map { it.toData() }.toTypedArray(),
    this.canSaveCredentials,
    this.needPassword
)

fun PaymentFormTypeStarSubscription.toData(): TdApi.PaymentFormTypeStarSubscription = TdApi.PaymentFormTypeStarSubscription(
    this.pricing.toData()
)

fun PaymentFormTypeStars.toData(): TdApi.PaymentFormTypeStars = TdApi.PaymentFormTypeStars(
    this.starCount
)

fun PaymentOption.toData(): TdApi.PaymentOption = TdApi.PaymentOption(
    this.title,
    this.url
)

fun PaymentReceipt.toData(): TdApi.PaymentReceipt = TdApi.PaymentReceipt(
    this.productInfo.toData(),
    this.date,
    this.sellerBotUserId,
    this.type.toData()
)

fun PaymentReceiptType.toData(): TdApi.PaymentReceiptType = when(this) {
    is PaymentReceiptTypeRegular -> this.toData()
    is PaymentReceiptTypeStars -> this.toData()
}

fun PaymentReceiptTypeRegular.toData(): TdApi.PaymentReceiptTypeRegular = TdApi.PaymentReceiptTypeRegular(
    this.paymentProviderUserId,
    this.invoice.toData(),
    this.orderInfo?.toData(),
    this.shippingOption?.toData(),
    this.credentialsTitle,
    this.tipAmount
)

fun PaymentReceiptTypeStars.toData(): TdApi.PaymentReceiptTypeStars = TdApi.PaymentReceiptTypeStars(
    this.starCount,
    this.transactionId
)

fun PaymentResult.toData(): TdApi.PaymentResult = TdApi.PaymentResult(
    this.success,
    this.verificationUrl
)

fun PremiumFeature.toData(): TdApi.PremiumFeature = when(this) {
    is PremiumFeatureIncreasedLimits -> this.toData()
    is PremiumFeatureIncreasedUploadFileSize -> this.toData()
    is PremiumFeatureImprovedDownloadSpeed -> this.toData()
    is PremiumFeatureVoiceRecognition -> this.toData()
    is PremiumFeatureDisabledAds -> this.toData()
    is PremiumFeatureUniqueReactions -> this.toData()
    is PremiumFeatureUniqueStickers -> this.toData()
    is PremiumFeatureCustomEmoji -> this.toData()
    is PremiumFeatureAdvancedChatManagement -> this.toData()
    is PremiumFeatureProfileBadge -> this.toData()
    is PremiumFeatureEmojiStatus -> this.toData()
    is PremiumFeatureAnimatedProfilePhoto -> this.toData()
    is PremiumFeatureForumTopicIcon -> this.toData()
    is PremiumFeatureAppIcons -> this.toData()
    is PremiumFeatureRealTimeChatTranslation -> this.toData()
    is PremiumFeatureUpgradedStories -> this.toData()
    is PremiumFeatureChatBoost -> this.toData()
    is PremiumFeatureAccentColor -> this.toData()
    is PremiumFeatureBackgroundForBoth -> this.toData()
    is PremiumFeatureSavedMessagesTags -> this.toData()
    is PremiumFeatureMessagePrivacy -> this.toData()
    is PremiumFeatureLastSeenTimes -> this.toData()
    is PremiumFeatureBusiness -> this.toData()
    is PremiumFeatureMessageEffects -> this.toData()
    is PremiumFeatureChecklists -> this.toData()
}

fun PremiumFeatureAccentColor.toData(): TdApi.PremiumFeatureAccentColor = TdApi.PremiumFeatureAccentColor(
)

fun PremiumFeatureAdvancedChatManagement.toData(): TdApi.PremiumFeatureAdvancedChatManagement = TdApi.PremiumFeatureAdvancedChatManagement(
)

fun PremiumFeatureAnimatedProfilePhoto.toData(): TdApi.PremiumFeatureAnimatedProfilePhoto = TdApi.PremiumFeatureAnimatedProfilePhoto(
)

fun PremiumFeatureAppIcons.toData(): TdApi.PremiumFeatureAppIcons = TdApi.PremiumFeatureAppIcons(
)

fun PremiumFeatureBackgroundForBoth.toData(): TdApi.PremiumFeatureBackgroundForBoth = TdApi.PremiumFeatureBackgroundForBoth(
)

fun PremiumFeatureBusiness.toData(): TdApi.PremiumFeatureBusiness = TdApi.PremiumFeatureBusiness(
)

fun PremiumFeatureChatBoost.toData(): TdApi.PremiumFeatureChatBoost = TdApi.PremiumFeatureChatBoost(
)

fun PremiumFeatureChecklists.toData(): TdApi.PremiumFeatureChecklists = TdApi.PremiumFeatureChecklists(
)

fun PremiumFeatureCustomEmoji.toData(): TdApi.PremiumFeatureCustomEmoji = TdApi.PremiumFeatureCustomEmoji(
)

fun PremiumFeatureDisabledAds.toData(): TdApi.PremiumFeatureDisabledAds = TdApi.PremiumFeatureDisabledAds(
)

fun PremiumFeatureEmojiStatus.toData(): TdApi.PremiumFeatureEmojiStatus = TdApi.PremiumFeatureEmojiStatus(
)

fun PremiumFeatureForumTopicIcon.toData(): TdApi.PremiumFeatureForumTopicIcon = TdApi.PremiumFeatureForumTopicIcon(
)

fun PremiumFeatureImprovedDownloadSpeed.toData(): TdApi.PremiumFeatureImprovedDownloadSpeed = TdApi.PremiumFeatureImprovedDownloadSpeed(
)

fun PremiumFeatureIncreasedLimits.toData(): TdApi.PremiumFeatureIncreasedLimits = TdApi.PremiumFeatureIncreasedLimits(
)

fun PremiumFeatureIncreasedUploadFileSize.toData(): TdApi.PremiumFeatureIncreasedUploadFileSize = TdApi.PremiumFeatureIncreasedUploadFileSize(
)

fun PremiumFeatureLastSeenTimes.toData(): TdApi.PremiumFeatureLastSeenTimes = TdApi.PremiumFeatureLastSeenTimes(
)

fun PremiumFeatureMessageEffects.toData(): TdApi.PremiumFeatureMessageEffects = TdApi.PremiumFeatureMessageEffects(
)

fun PremiumFeatureMessagePrivacy.toData(): TdApi.PremiumFeatureMessagePrivacy = TdApi.PremiumFeatureMessagePrivacy(
)

fun PremiumFeatureProfileBadge.toData(): TdApi.PremiumFeatureProfileBadge = TdApi.PremiumFeatureProfileBadge(
)

fun PremiumFeaturePromotionAnimation.toData(): TdApi.PremiumFeaturePromotionAnimation = TdApi.PremiumFeaturePromotionAnimation(
    this.feature.toData(),
    this.animation.toData()
)

fun PremiumFeatureRealTimeChatTranslation.toData(): TdApi.PremiumFeatureRealTimeChatTranslation = TdApi.PremiumFeatureRealTimeChatTranslation(
)

fun PremiumFeatureSavedMessagesTags.toData(): TdApi.PremiumFeatureSavedMessagesTags = TdApi.PremiumFeatureSavedMessagesTags(
)

fun PremiumFeatureUniqueReactions.toData(): TdApi.PremiumFeatureUniqueReactions = TdApi.PremiumFeatureUniqueReactions(
)

fun PremiumFeatureUniqueStickers.toData(): TdApi.PremiumFeatureUniqueStickers = TdApi.PremiumFeatureUniqueStickers(
)

fun PremiumFeatureUpgradedStories.toData(): TdApi.PremiumFeatureUpgradedStories = TdApi.PremiumFeatureUpgradedStories(
)

fun PremiumFeatureVoiceRecognition.toData(): TdApi.PremiumFeatureVoiceRecognition = TdApi.PremiumFeatureVoiceRecognition(
)

fun PremiumFeatures.toData(): TdApi.PremiumFeatures = TdApi.PremiumFeatures(
    this.features.map { it.toData() }.toTypedArray(),
    this.limits.map { it.toData() }.toTypedArray(),
    this.paymentLink?.toData()
)

fun PremiumGiveawayPaymentOption.toData(): TdApi.PremiumGiveawayPaymentOption = TdApi.PremiumGiveawayPaymentOption(
    this.currency,
    this.amount,
    this.winnerCount,
    this.monthCount,
    this.storeProductId,
    this.storeProductQuantity
)

fun PremiumGiveawayPaymentOptions.toData(): TdApi.PremiumGiveawayPaymentOptions = TdApi.PremiumGiveawayPaymentOptions(
    this.options.map { it.toData() }.toTypedArray()
)

fun PremiumLimit.toData(): TdApi.PremiumLimit = TdApi.PremiumLimit(
    this.type.toData(),
    this.defaultValue,
    this.premiumValue
)

fun PremiumLimitType.toData(): TdApi.PremiumLimitType = when(this) {
    is PremiumLimitTypeSupergroupCount -> this.toData()
    is PremiumLimitTypePinnedChatCount -> this.toData()
    is PremiumLimitTypeCreatedPublicChatCount -> this.toData()
    is PremiumLimitTypeSavedAnimationCount -> this.toData()
    is PremiumLimitTypeFavoriteStickerCount -> this.toData()
    is PremiumLimitTypeChatFolderCount -> this.toData()
    is PremiumLimitTypeChatFolderChosenChatCount -> this.toData()
    is PremiumLimitTypePinnedArchivedChatCount -> this.toData()
    is PremiumLimitTypePinnedSavedMessagesTopicCount -> this.toData()
    is PremiumLimitTypeCaptionLength -> this.toData()
    is PremiumLimitTypeBioLength -> this.toData()
    is PremiumLimitTypeChatFolderInviteLinkCount -> this.toData()
    is PremiumLimitTypeShareableChatFolderCount -> this.toData()
    is PremiumLimitTypeActiveStoryCount -> this.toData()
    is PremiumLimitTypeWeeklyPostedStoryCount -> this.toData()
    is PremiumLimitTypeMonthlyPostedStoryCount -> this.toData()
    is PremiumLimitTypeStoryCaptionLength -> this.toData()
    is PremiumLimitTypeStorySuggestedReactionAreaCount -> this.toData()
    is PremiumLimitTypeSimilarChatCount -> this.toData()
}

fun PremiumLimitTypeActiveStoryCount.toData(): TdApi.PremiumLimitTypeActiveStoryCount = TdApi.PremiumLimitTypeActiveStoryCount(
)

fun PremiumLimitTypeBioLength.toData(): TdApi.PremiumLimitTypeBioLength = TdApi.PremiumLimitTypeBioLength(
)

fun PremiumLimitTypeCaptionLength.toData(): TdApi.PremiumLimitTypeCaptionLength = TdApi.PremiumLimitTypeCaptionLength(
)

fun PremiumLimitTypeChatFolderChosenChatCount.toData(): TdApi.PremiumLimitTypeChatFolderChosenChatCount = TdApi.PremiumLimitTypeChatFolderChosenChatCount(
)

fun PremiumLimitTypeChatFolderCount.toData(): TdApi.PremiumLimitTypeChatFolderCount = TdApi.PremiumLimitTypeChatFolderCount(
)

fun PremiumLimitTypeChatFolderInviteLinkCount.toData(): TdApi.PremiumLimitTypeChatFolderInviteLinkCount = TdApi.PremiumLimitTypeChatFolderInviteLinkCount(
)

fun PremiumLimitTypeCreatedPublicChatCount.toData(): TdApi.PremiumLimitTypeCreatedPublicChatCount = TdApi.PremiumLimitTypeCreatedPublicChatCount(
)

fun PremiumLimitTypeFavoriteStickerCount.toData(): TdApi.PremiumLimitTypeFavoriteStickerCount = TdApi.PremiumLimitTypeFavoriteStickerCount(
)

fun PremiumLimitTypeMonthlyPostedStoryCount.toData(): TdApi.PremiumLimitTypeMonthlyPostedStoryCount = TdApi.PremiumLimitTypeMonthlyPostedStoryCount(
)

fun PremiumLimitTypePinnedArchivedChatCount.toData(): TdApi.PremiumLimitTypePinnedArchivedChatCount = TdApi.PremiumLimitTypePinnedArchivedChatCount(
)

fun PremiumLimitTypePinnedChatCount.toData(): TdApi.PremiumLimitTypePinnedChatCount = TdApi.PremiumLimitTypePinnedChatCount(
)

fun PremiumLimitTypePinnedSavedMessagesTopicCount.toData(): TdApi.PremiumLimitTypePinnedSavedMessagesTopicCount = TdApi.PremiumLimitTypePinnedSavedMessagesTopicCount(
)

fun PremiumLimitTypeSavedAnimationCount.toData(): TdApi.PremiumLimitTypeSavedAnimationCount = TdApi.PremiumLimitTypeSavedAnimationCount(
)

fun PremiumLimitTypeShareableChatFolderCount.toData(): TdApi.PremiumLimitTypeShareableChatFolderCount = TdApi.PremiumLimitTypeShareableChatFolderCount(
)

fun PremiumLimitTypeSimilarChatCount.toData(): TdApi.PremiumLimitTypeSimilarChatCount = TdApi.PremiumLimitTypeSimilarChatCount(
)

fun PremiumLimitTypeStoryCaptionLength.toData(): TdApi.PremiumLimitTypeStoryCaptionLength = TdApi.PremiumLimitTypeStoryCaptionLength(
)

fun PremiumLimitTypeStorySuggestedReactionAreaCount.toData(): TdApi.PremiumLimitTypeStorySuggestedReactionAreaCount = TdApi.PremiumLimitTypeStorySuggestedReactionAreaCount(
)

fun PremiumLimitTypeSupergroupCount.toData(): TdApi.PremiumLimitTypeSupergroupCount = TdApi.PremiumLimitTypeSupergroupCount(
)

fun PremiumLimitTypeWeeklyPostedStoryCount.toData(): TdApi.PremiumLimitTypeWeeklyPostedStoryCount = TdApi.PremiumLimitTypeWeeklyPostedStoryCount(
)

fun PremiumState.toData(): TdApi.PremiumState = TdApi.PremiumState(
    this.state.toData(),
    this.paymentOptions.map { it.toData() }.toTypedArray(),
    this.animations.map { it.toData() }.toTypedArray(),
    this.businessAnimations.map { it.toData() }.toTypedArray()
)

fun PremiumStatePaymentOption.toData(): TdApi.PremiumStatePaymentOption = TdApi.PremiumStatePaymentOption(
    this.paymentOption.toData(),
    this.isCurrent,
    this.isUpgrade,
    this.lastTransactionId
)

fun SendGift.toData(): TdApi.SendGift = TdApi.SendGift(
    this.giftId,
    this.ownerId.toData(),
    this.text.toData(),
    this.isPrivate,
    this.payForUpgrade
)

fun SendPaymentForm.toData(): TdApi.SendPaymentForm = TdApi.SendPaymentForm(
    this.inputInvoice.toData(),
    this.paymentFormId,
    this.orderInfoId,
    this.shippingOptionId,
    this.credentials.toData(),
    this.tipAmount
)

fun StarTransaction.toData(): TdApi.StarTransaction = TdApi.StarTransaction(
    this.id,
    this.starAmount.toData(),
    this.isRefund,
    this.date,
    this.type.toData()
)

fun StarTransactionType.toData(): TdApi.StarTransactionType = when(this) {
    is StarTransactionTypePremiumBotDeposit -> this.toData()
    is StarTransactionTypeAppStoreDeposit -> this.toData()
    is StarTransactionTypeGooglePlayDeposit -> this.toData()
    is StarTransactionTypeFragmentDeposit -> this.toData()
    is StarTransactionTypeUserDeposit -> this.toData()
    is StarTransactionTypeGiveawayDeposit -> this.toData()
    is StarTransactionTypeFragmentWithdrawal -> this.toData()
    is StarTransactionTypeTelegramAdsWithdrawal -> this.toData()
    is StarTransactionTypeTelegramApiUsage -> this.toData()
    is StarTransactionTypeBotPaidMediaPurchase -> this.toData()
    is StarTransactionTypeBotPaidMediaSale -> this.toData()
    is StarTransactionTypeChannelPaidMediaPurchase -> this.toData()
    is StarTransactionTypeChannelPaidMediaSale -> this.toData()
    is StarTransactionTypeBotInvoicePurchase -> this.toData()
    is StarTransactionTypeBotInvoiceSale -> this.toData()
    is StarTransactionTypeBotSubscriptionPurchase -> this.toData()
    is StarTransactionTypeBotSubscriptionSale -> this.toData()
    is StarTransactionTypeChannelSubscriptionPurchase -> this.toData()
    is StarTransactionTypeChannelSubscriptionSale -> this.toData()
    is StarTransactionTypeGiftPurchase -> this.toData()
    is StarTransactionTypeGiftTransfer -> this.toData()
    is StarTransactionTypeGiftSale -> this.toData()
    is StarTransactionTypeGiftUpgrade -> this.toData()
    is StarTransactionTypeGiftUpgradePurchase -> this.toData()
    is StarTransactionTypeUpgradedGiftPurchase -> this.toData()
    is StarTransactionTypeUpgradedGiftSale -> this.toData()
    is StarTransactionTypeChannelPaidReactionSend -> this.toData()
    is StarTransactionTypeChannelPaidReactionReceive -> this.toData()
    is StarTransactionTypeAffiliateProgramCommission -> this.toData()
    is StarTransactionTypePaidMessageSend -> this.toData()
    is StarTransactionTypePaidMessageReceive -> this.toData()
    is StarTransactionTypeSuggestedPostPaymentSend -> this.toData()
    is StarTransactionTypeSuggestedPostPaymentReceive -> this.toData()
    is StarTransactionTypePremiumPurchase -> this.toData()
    is StarTransactionTypeBusinessBotTransferSend -> this.toData()
    is StarTransactionTypeBusinessBotTransferReceive -> this.toData()
    is StarTransactionTypePublicPostSearch -> this.toData()
    is StarTransactionTypeUnsupported -> this.toData()
}

fun StarTransactionTypeAffiliateProgramCommission.toData(): TdApi.StarTransactionTypeAffiliateProgramCommission = TdApi.StarTransactionTypeAffiliateProgramCommission(
    this.chatId,
    this.commissionPerMille
)

fun StarTransactionTypeAppStoreDeposit.toData(): TdApi.StarTransactionTypeAppStoreDeposit = TdApi.StarTransactionTypeAppStoreDeposit(
)

fun StarTransactionTypeBotInvoicePurchase.toData(): TdApi.StarTransactionTypeBotInvoicePurchase = TdApi.StarTransactionTypeBotInvoicePurchase(
    this.userId,
    this.productInfo.toData()
)

fun StarTransactionTypeBotInvoiceSale.toData(): TdApi.StarTransactionTypeBotInvoiceSale = TdApi.StarTransactionTypeBotInvoiceSale(
    this.userId,
    this.productInfo.toData(),
    this.invoicePayload,
    this.affiliate?.toData()
)

fun StarTransactionTypeBotPaidMediaPurchase.toData(): TdApi.StarTransactionTypeBotPaidMediaPurchase = TdApi.StarTransactionTypeBotPaidMediaPurchase(
    this.userId,
    this.media.map { it.toData() }.toTypedArray()
)

fun StarTransactionTypeBotPaidMediaSale.toData(): TdApi.StarTransactionTypeBotPaidMediaSale = TdApi.StarTransactionTypeBotPaidMediaSale(
    this.userId,
    this.media.map { it.toData() }.toTypedArray(),
    this.payload,
    this.affiliate?.toData()
)

fun StarTransactionTypeBotSubscriptionPurchase.toData(): TdApi.StarTransactionTypeBotSubscriptionPurchase = TdApi.StarTransactionTypeBotSubscriptionPurchase(
    this.userId,
    this.subscriptionPeriod,
    this.productInfo.toData()
)

fun StarTransactionTypeBotSubscriptionSale.toData(): TdApi.StarTransactionTypeBotSubscriptionSale = TdApi.StarTransactionTypeBotSubscriptionSale(
    this.userId,
    this.subscriptionPeriod,
    this.productInfo.toData(),
    this.invoicePayload,
    this.affiliate?.toData()
)

fun StarTransactionTypeBusinessBotTransferReceive.toData(): TdApi.StarTransactionTypeBusinessBotTransferReceive = TdApi.StarTransactionTypeBusinessBotTransferReceive(
    this.userId
)

fun StarTransactionTypeBusinessBotTransferSend.toData(): TdApi.StarTransactionTypeBusinessBotTransferSend = TdApi.StarTransactionTypeBusinessBotTransferSend(
    this.userId
)

fun StarTransactionTypeChannelPaidMediaPurchase.toData(): TdApi.StarTransactionTypeChannelPaidMediaPurchase = TdApi.StarTransactionTypeChannelPaidMediaPurchase(
    this.chatId,
    this.messageId,
    this.media.map { it.toData() }.toTypedArray()
)

fun StarTransactionTypeChannelPaidMediaSale.toData(): TdApi.StarTransactionTypeChannelPaidMediaSale = TdApi.StarTransactionTypeChannelPaidMediaSale(
    this.userId,
    this.messageId,
    this.media.map { it.toData() }.toTypedArray()
)

fun StarTransactionTypeChannelPaidReactionReceive.toData(): TdApi.StarTransactionTypeChannelPaidReactionReceive = TdApi.StarTransactionTypeChannelPaidReactionReceive(
    this.userId,
    this.messageId
)

fun StarTransactionTypeChannelPaidReactionSend.toData(): TdApi.StarTransactionTypeChannelPaidReactionSend = TdApi.StarTransactionTypeChannelPaidReactionSend(
    this.chatId,
    this.messageId
)

fun StarTransactionTypeChannelSubscriptionPurchase.toData(): TdApi.StarTransactionTypeChannelSubscriptionPurchase = TdApi.StarTransactionTypeChannelSubscriptionPurchase(
    this.chatId,
    this.subscriptionPeriod
)

fun StarTransactionTypeChannelSubscriptionSale.toData(): TdApi.StarTransactionTypeChannelSubscriptionSale = TdApi.StarTransactionTypeChannelSubscriptionSale(
    this.userId,
    this.subscriptionPeriod
)

fun StarTransactionTypeFragmentDeposit.toData(): TdApi.StarTransactionTypeFragmentDeposit = TdApi.StarTransactionTypeFragmentDeposit(
)

fun StarTransactionTypeFragmentWithdrawal.toData(): TdApi.StarTransactionTypeFragmentWithdrawal = TdApi.StarTransactionTypeFragmentWithdrawal(
    this.withdrawalState?.toData()
)

fun StarTransactionTypeGiftPurchase.toData(): TdApi.StarTransactionTypeGiftPurchase = TdApi.StarTransactionTypeGiftPurchase(
    this.ownerId.toData(),
    this.gift.toData()
)

fun StarTransactionTypeGiftSale.toData(): TdApi.StarTransactionTypeGiftSale = TdApi.StarTransactionTypeGiftSale(
    this.userId,
    this.gift.toData()
)

fun StarTransactionTypeGiftTransfer.toData(): TdApi.StarTransactionTypeGiftTransfer = TdApi.StarTransactionTypeGiftTransfer(
    this.ownerId.toData(),
    this.gift.toData()
)

fun StarTransactionTypeGiftUpgrade.toData(): TdApi.StarTransactionTypeGiftUpgrade = TdApi.StarTransactionTypeGiftUpgrade(
    this.userId,
    this.gift.toData()
)

fun StarTransactionTypeGiftUpgradePurchase.toData(): TdApi.StarTransactionTypeGiftUpgradePurchase = TdApi.StarTransactionTypeGiftUpgradePurchase(
    this.ownerId.toData(),
    this.gift.toData()
)

fun StarTransactionTypeGiveawayDeposit.toData(): TdApi.StarTransactionTypeGiveawayDeposit = TdApi.StarTransactionTypeGiveawayDeposit(
    this.chatId,
    this.giveawayMessageId
)

fun StarTransactionTypeGooglePlayDeposit.toData(): TdApi.StarTransactionTypeGooglePlayDeposit = TdApi.StarTransactionTypeGooglePlayDeposit(
)

fun StarTransactionTypePaidMessageReceive.toData(): TdApi.StarTransactionTypePaidMessageReceive = TdApi.StarTransactionTypePaidMessageReceive(
    this.senderId.toData(),
    this.messageCount,
    this.commissionPerMille,
    this.commissionStarAmount.toData()
)

fun StarTransactionTypePaidMessageSend.toData(): TdApi.StarTransactionTypePaidMessageSend = TdApi.StarTransactionTypePaidMessageSend(
    this.chatId,
    this.messageCount
)

fun StarTransactionTypePremiumBotDeposit.toData(): TdApi.StarTransactionTypePremiumBotDeposit = TdApi.StarTransactionTypePremiumBotDeposit(
)

fun StarTransactionTypePremiumPurchase.toData(): TdApi.StarTransactionTypePremiumPurchase = TdApi.StarTransactionTypePremiumPurchase(
    this.userId,
    this.monthCount,
    this.sticker?.toData()
)

fun StarTransactionTypePublicPostSearch.toData(): TdApi.StarTransactionTypePublicPostSearch = TdApi.StarTransactionTypePublicPostSearch(
)

fun StarTransactionTypeSuggestedPostPaymentReceive.toData(): TdApi.StarTransactionTypeSuggestedPostPaymentReceive = TdApi.StarTransactionTypeSuggestedPostPaymentReceive(
    this.userId
)

fun StarTransactionTypeSuggestedPostPaymentSend.toData(): TdApi.StarTransactionTypeSuggestedPostPaymentSend = TdApi.StarTransactionTypeSuggestedPostPaymentSend(
    this.chatId
)

fun StarTransactionTypeTelegramAdsWithdrawal.toData(): TdApi.StarTransactionTypeTelegramAdsWithdrawal = TdApi.StarTransactionTypeTelegramAdsWithdrawal(
)

fun StarTransactionTypeTelegramApiUsage.toData(): TdApi.StarTransactionTypeTelegramApiUsage = TdApi.StarTransactionTypeTelegramApiUsage(
    this.requestCount
)

fun StarTransactionTypeUnsupported.toData(): TdApi.StarTransactionTypeUnsupported = TdApi.StarTransactionTypeUnsupported(
)

fun StarTransactionTypeUpgradedGiftPurchase.toData(): TdApi.StarTransactionTypeUpgradedGiftPurchase = TdApi.StarTransactionTypeUpgradedGiftPurchase(
    this.userId,
    this.gift.toData()
)

fun StarTransactionTypeUpgradedGiftSale.toData(): TdApi.StarTransactionTypeUpgradedGiftSale = TdApi.StarTransactionTypeUpgradedGiftSale(
    this.userId,
    this.gift.toData(),
    this.commissionPerMille,
    this.commissionStarAmount.toData()
)

fun StarTransactionTypeUserDeposit.toData(): TdApi.StarTransactionTypeUserDeposit = TdApi.StarTransactionTypeUserDeposit(
    this.userId,
    this.sticker?.toData()
)

fun StarTransactions.toData(): TdApi.StarTransactions = TdApi.StarTransactions(
    this.starAmount.toData(),
    this.transactions.map { it.toData() }.toTypedArray(),
    this.nextOffset
)

fun UpgradeGift.toData(): TdApi.UpgradeGift = TdApi.UpgradeGift(
    this.businessConnectionId,
    this.receivedGiftId,
    this.keepOriginalDetails,
    this.starCount
)

fun UpgradeGiftResult.toData(): TdApi.UpgradeGiftResult = TdApi.UpgradeGiftResult(
    this.gift.toData(),
    this.receivedGiftId,
    this.isSaved,
    this.canBeTransferred,
    this.transferStarCount,
    this.nextTransferDate,
    this.nextResaleDate,
    this.exportDate
)

fun UpgradedGift.toData(): TdApi.UpgradedGift = TdApi.UpgradedGift(
    this.id,
    this.regularGiftId,
    this.publisherChatId,
    this.title,
    this.name,
    this.number,
    this.totalUpgradedCount,
    this.maxUpgradedCount,
    this.isPremium,
    this.isThemeAvailable,
    this.usedThemeChatId,
    this.ownerId?.toData(),
    this.ownerAddress,
    this.ownerName,
    this.giftAddress,
    this.model.toData(),
    this.symbol.toData(),
    this.backdrop.toData(),
    this.originalDetails?.toData(),
    this.resaleParameters?.toData(),
    this.valueCurrency,
    this.valueAmount
)

fun UpgradedGiftAttributeId.toData(): TdApi.UpgradedGiftAttributeId = when(this) {
    is UpgradedGiftAttributeIdModel -> this.toData()
    is UpgradedGiftAttributeIdSymbol -> this.toData()
    is UpgradedGiftAttributeIdBackdrop -> this.toData()
}

fun UpgradedGiftAttributeIdBackdrop.toData(): TdApi.UpgradedGiftAttributeIdBackdrop = TdApi.UpgradedGiftAttributeIdBackdrop(
    this.backdropId
)

fun UpgradedGiftAttributeIdModel.toData(): TdApi.UpgradedGiftAttributeIdModel = TdApi.UpgradedGiftAttributeIdModel(
    this.stickerId
)

fun UpgradedGiftAttributeIdSymbol.toData(): TdApi.UpgradedGiftAttributeIdSymbol = TdApi.UpgradedGiftAttributeIdSymbol(
    this.stickerId
)

fun UpgradedGiftBackdrop.toData(): TdApi.UpgradedGiftBackdrop = TdApi.UpgradedGiftBackdrop(
    this.id,
    this.name,
    this.colors.toData(),
    this.rarityPerMille
)

fun UpgradedGiftBackdropColors.toData(): TdApi.UpgradedGiftBackdropColors = TdApi.UpgradedGiftBackdropColors(
    this.centerColor,
    this.edgeColor,
    this.symbolColor,
    this.textColor
)

fun UpgradedGiftBackdropCount.toData(): TdApi.UpgradedGiftBackdropCount = TdApi.UpgradedGiftBackdropCount(
    this.backdrop.toData(),
    this.totalCount
)

fun UpgradedGiftModel.toData(): TdApi.UpgradedGiftModel = TdApi.UpgradedGiftModel(
    this.name,
    this.sticker.toData(),
    this.rarityPerMille
)

fun UpgradedGiftModelCount.toData(): TdApi.UpgradedGiftModelCount = TdApi.UpgradedGiftModelCount(
    this.model.toData(),
    this.totalCount
)

fun UpgradedGiftOrigin.toData(): TdApi.UpgradedGiftOrigin = when(this) {
    is UpgradedGiftOriginUpgrade -> this.toData()
    is UpgradedGiftOriginTransfer -> this.toData()
    is UpgradedGiftOriginResale -> this.toData()
    is UpgradedGiftOriginPrepaidUpgrade -> this.toData()
}

fun UpgradedGiftOriginPrepaidUpgrade.toData(): TdApi.UpgradedGiftOriginPrepaidUpgrade = TdApi.UpgradedGiftOriginPrepaidUpgrade(
)

fun UpgradedGiftOriginResale.toData(): TdApi.UpgradedGiftOriginResale = TdApi.UpgradedGiftOriginResale(
    this.price.toData()
)

fun UpgradedGiftOriginTransfer.toData(): TdApi.UpgradedGiftOriginTransfer = TdApi.UpgradedGiftOriginTransfer(
)

fun UpgradedGiftOriginUpgrade.toData(): TdApi.UpgradedGiftOriginUpgrade = TdApi.UpgradedGiftOriginUpgrade(
    this.giftMessageId
)

fun UpgradedGiftOriginalDetails.toData(): TdApi.UpgradedGiftOriginalDetails = TdApi.UpgradedGiftOriginalDetails(
    this.senderId?.toData(),
    this.receiverId.toData(),
    this.text.toData(),
    this.date
)

fun UpgradedGiftSymbol.toData(): TdApi.UpgradedGiftSymbol = TdApi.UpgradedGiftSymbol(
    this.name,
    this.sticker.toData(),
    this.rarityPerMille
)

fun UpgradedGiftSymbolCount.toData(): TdApi.UpgradedGiftSymbolCount = TdApi.UpgradedGiftSymbolCount(
    this.symbol.toData(),
    this.totalCount
)

fun UpgradedGiftValueInfo.toData(): TdApi.UpgradedGiftValueInfo = TdApi.UpgradedGiftValueInfo(
    this.currency,
    this.value,
    this.isValueAverage,
    this.initialSaleDate,
    this.initialSaleStarCount,
    this.initialSalePrice,
    this.lastSaleDate,
    this.lastSalePrice,
    this.isLastSaleOnFragment,
    this.minimumPrice,
    this.averageSalePrice,
    this.telegramListedGiftCount,
    this.fragmentListedGiftCount,
    this.fragmentUrl
)

fun ValidateOrderInfo.toData(): TdApi.ValidateOrderInfo = TdApi.ValidateOrderInfo(
    this.inputInvoice.toData(),
    this.orderInfo.toData(),
    this.allowSave
)

fun ValidatedOrderInfo.toData(): TdApi.ValidatedOrderInfo = TdApi.ValidatedOrderInfo(
    this.orderInfoId,
    this.shippingOptions.map { it.toData() }.toTypedArray()
)

