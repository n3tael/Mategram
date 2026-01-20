package com.xxcactussell.data.utils.mappers.monetization

import com.xxcactussell.data.utils.mappers.accepted.toDomain
import com.xxcactussell.data.utils.mappers.address.toDomain
import com.xxcactussell.data.utils.mappers.affiliate.toDomain
import com.xxcactussell.data.utils.mappers.animation.toDomain
import com.xxcactussell.data.utils.mappers.business.toDomain
import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.input.toDomain
import com.xxcactussell.data.utils.mappers.internal.toDomain
import com.xxcactussell.data.utils.mappers.labeled.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.paid.toDomain
import com.xxcactussell.data.utils.mappers.payment.toDomain
import com.xxcactussell.data.utils.mappers.premium.toDomain
import com.xxcactussell.data.utils.mappers.product.toDomain
import com.xxcactussell.data.utils.mappers.revenue.toDomain
import com.xxcactussell.data.utils.mappers.saved.toDomain
import com.xxcactussell.data.utils.mappers.shipping.toDomain
import com.xxcactussell.data.utils.mappers.star.toDomain
import com.xxcactussell.data.utils.mappers.sticker.toDomain
import com.xxcactussell.data.utils.mappers.telegram.toDomain
import com.xxcactussell.data.utils.mappers.theme.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.DeleteSavedCredentials.toDomain(): DeleteSavedCredentials = DeleteSavedCredentials

fun TdApi.DeleteSavedOrderInfo.toDomain(): DeleteSavedOrderInfo = DeleteSavedOrderInfo

fun TdApi.GetAvailableGifts.toDomain(): GetAvailableGifts = GetAvailableGifts

fun TdApi.GetPaymentForm.toDomain(): GetPaymentForm = GetPaymentForm(
    inputInvoice = this.inputInvoice.toDomain(),
    theme = this.theme.toDomain()
)

fun TdApi.GetPaymentReceipt.toDomain(): GetPaymentReceipt = GetPaymentReceipt(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.GetPremiumFeatures.toDomain(): GetPremiumFeatures = GetPremiumFeatures(
    source = this.source.toDomain()
)

fun TdApi.GetPremiumLimit.toDomain(): GetPremiumLimit = GetPremiumLimit(
    limitType = this.limitType.toDomain()
)

fun TdApi.GetPremiumState.toDomain(): GetPremiumState = GetPremiumState

fun TdApi.GetSavedOrderInfo.toDomain(): GetSavedOrderInfo = GetSavedOrderInfo

fun TdApi.GetStarRevenueStatistics.toDomain(): GetStarRevenueStatistics = GetStarRevenueStatistics(
    ownerId = this.ownerId.toDomain(),
    isDark = this.isDark
)

fun TdApi.GetStarWithdrawalUrl.toDomain(): GetStarWithdrawalUrl = GetStarWithdrawalUrl(
    ownerId = this.ownerId.toDomain(),
    starCount = this.starCount,
    password = this.password
)

fun TdApi.Gift.toDomain(): Gift = Gift(
    id = this.id,
    publisherChatId = this.publisherChatId,
    sticker = this.sticker.toDomain(),
    starCount = this.starCount,
    defaultSellStarCount = this.defaultSellStarCount,
    upgradeStarCount = this.upgradeStarCount,
    isForBirthday = this.isForBirthday,
    isPremium = this.isPremium,
    nextSendDate = this.nextSendDate,
    userLimits = this.userLimits?.toDomain(),
    overallLimits = this.overallLimits?.toDomain(),
    firstSendDate = this.firstSendDate,
    lastSendDate = this.lastSendDate
)

fun TdApi.GiftChatTheme.toDomain(): GiftChatTheme = GiftChatTheme(
    gift = this.gift.toDomain(),
    lightSettings = this.lightSettings.toDomain(),
    darkSettings = this.darkSettings.toDomain()
)

fun TdApi.GiftChatThemes.toDomain(): GiftChatThemes = GiftChatThemes(
    themes = this.themes.map { it.toDomain() },
    nextOffset = this.nextOffset
)

fun TdApi.GiftCollection.toDomain(): GiftCollection = GiftCollection(
    id = this.id,
    name = this.name,
    icon = this.icon?.toDomain(),
    giftCount = this.giftCount
)

fun TdApi.GiftCollections.toDomain(): GiftCollections = GiftCollections(
    collections = this.collections.map { it.toDomain() }
)

fun TdApi.GiftForResale.toDomain(): GiftForResale = GiftForResale(
    gift = this.gift.toDomain(),
    receivedGiftId = this.receivedGiftId
)

fun TdApi.GiftForResaleOrder.toDomain(): GiftForResaleOrder = when(this) {
    is TdApi.GiftForResaleOrderPrice -> this.toDomain()
    is TdApi.GiftForResaleOrderPriceChangeDate -> this.toDomain()
    is TdApi.GiftForResaleOrderNumber -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.GiftForResaleOrderNumber.toDomain(): GiftForResaleOrderNumber = GiftForResaleOrderNumber

fun TdApi.GiftForResaleOrderPrice.toDomain(): GiftForResaleOrderPrice = GiftForResaleOrderPrice

fun TdApi.GiftForResaleOrderPriceChangeDate.toDomain(): GiftForResaleOrderPriceChangeDate = GiftForResaleOrderPriceChangeDate

fun TdApi.GiftPremiumWithStars.toDomain(): GiftPremiumWithStars = GiftPremiumWithStars(
    userId = this.userId,
    starCount = this.starCount,
    monthCount = this.monthCount,
    text = this.text.toDomain()
)

fun TdApi.GiftPurchaseLimits.toDomain(): GiftPurchaseLimits = GiftPurchaseLimits(
    totalCount = this.totalCount,
    remainingCount = this.remainingCount
)

fun TdApi.GiftResaleParameters.toDomain(): GiftResaleParameters = GiftResaleParameters(
    starCount = this.starCount,
    toncoinCentCount = this.toncoinCentCount,
    toncoinOnly = this.toncoinOnly
)

fun TdApi.GiftResalePrice.toDomain(): GiftResalePrice = when(this) {
    is TdApi.GiftResalePriceStar -> this.toDomain()
    is TdApi.GiftResalePriceTon -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.GiftResalePriceStar.toDomain(): GiftResalePriceStar = GiftResalePriceStar(
    starCount = this.starCount
)

fun TdApi.GiftResalePriceTon.toDomain(): GiftResalePriceTon = GiftResalePriceTon(
    toncoinCentCount = this.toncoinCentCount
)

fun TdApi.GiftResaleResult.toDomain(): GiftResaleResult = when(this) {
    is TdApi.GiftResaleResultOk -> this.toDomain()
    is TdApi.GiftResaleResultPriceIncreased -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.GiftResaleResultOk.toDomain(): GiftResaleResultOk = GiftResaleResultOk

fun TdApi.GiftResaleResultPriceIncreased.toDomain(): GiftResaleResultPriceIncreased = GiftResaleResultPriceIncreased(
    price = this.price.toDomain()
)

fun TdApi.GiftSettings.toDomain(): GiftSettings = GiftSettings(
    showGiftButton = this.showGiftButton,
    acceptedGiftTypes = this.acceptedGiftTypes.toDomain()
)

fun TdApi.GiftUpgradePreview.toDomain(): GiftUpgradePreview = GiftUpgradePreview(
    models = this.models.map { it.toDomain() },
    symbols = this.symbols.map { it.toDomain() },
    backdrops = this.backdrops.map { it.toDomain() }
)

fun TdApi.GiftsForResale.toDomain(): GiftsForResale = GiftsForResale(
    totalCount = this.totalCount,
    gifts = this.gifts.map { it.toDomain() },
    models = this.models.map { it.toDomain() },
    symbols = this.symbols.map { it.toDomain() },
    backdrops = this.backdrops.map { it.toDomain() },
    nextOffset = this.nextOffset
)

fun TdApi.InputInvoice.toDomain(): InputInvoice = when(this) {
    is TdApi.InputInvoiceMessage -> this.toDomain()
    is TdApi.InputInvoiceName -> this.toDomain()
    is TdApi.InputInvoiceTelegram -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InputInvoiceMessage.toDomain(): InputInvoiceMessage = InputInvoiceMessage(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.InputInvoiceName.toDomain(): InputInvoiceName = InputInvoiceName(
    name = this.name
)

fun TdApi.InputInvoiceTelegram.toDomain(): InputInvoiceTelegram = InputInvoiceTelegram(
    purpose = this.purpose.toDomain()
)

fun TdApi.InternalLinkTypePremiumFeatures.toDomain(): InternalLinkTypePremiumFeatures = InternalLinkTypePremiumFeatures(
    referrer = this.referrer
)

fun TdApi.Invoice.toDomain(): Invoice = Invoice(
    currency = this.currency,
    priceParts = this.priceParts.map { it.toDomain() },
    subscriptionPeriod = this.subscriptionPeriod,
    maxTipAmount = this.maxTipAmount,
    suggestedTipAmounts = this.suggestedTipAmounts,
    recurringPaymentTermsOfServiceUrl = this.recurringPaymentTermsOfServiceUrl,
    termsOfServiceUrl = this.termsOfServiceUrl,
    isTest = this.isTest,
    needName = this.needName,
    needPhoneNumber = this.needPhoneNumber,
    needEmailAddress = this.needEmailAddress,
    needShippingAddress = this.needShippingAddress,
    sendPhoneNumberToProvider = this.sendPhoneNumberToProvider,
    sendEmailAddressToProvider = this.sendEmailAddressToProvider,
    isFlexible = this.isFlexible
)

fun TdApi.OrderInfo.toDomain(): OrderInfo = OrderInfo(
    name = this.name,
    phoneNumber = this.phoneNumber,
    emailAddress = this.emailAddress,
    shippingAddress = this.shippingAddress?.toDomain()
)

fun TdApi.PaymentForm.toDomain(): PaymentForm = PaymentForm(
    id = this.id,
    type = this.type.toDomain(),
    sellerBotUserId = this.sellerBotUserId,
    productInfo = this.productInfo.toDomain()
)

fun TdApi.PaymentFormType.toDomain(): PaymentFormType = when(this) {
    is TdApi.PaymentFormTypeRegular -> this.toDomain()
    is TdApi.PaymentFormTypeStars -> this.toDomain()
    is TdApi.PaymentFormTypeStarSubscription -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PaymentFormTypeRegular.toDomain(): PaymentFormTypeRegular = PaymentFormTypeRegular(
    invoice = this.invoice.toDomain(),
    paymentProviderUserId = this.paymentProviderUserId,
    paymentProvider = this.paymentProvider.toDomain(),
    additionalPaymentOptions = this.additionalPaymentOptions.map { it.toDomain() },
    savedOrderInfo = this.savedOrderInfo?.toDomain(),
    savedCredentials = this.savedCredentials.map { it.toDomain() },
    canSaveCredentials = this.canSaveCredentials,
    needPassword = this.needPassword
)

fun TdApi.PaymentFormTypeStarSubscription.toDomain(): PaymentFormTypeStarSubscription = PaymentFormTypeStarSubscription(
    pricing = this.pricing.toDomain()
)

fun TdApi.PaymentFormTypeStars.toDomain(): PaymentFormTypeStars = PaymentFormTypeStars(
    starCount = this.starCount
)

fun TdApi.PaymentOption.toDomain(): PaymentOption = PaymentOption(
    title = this.title,
    url = this.url
)

fun TdApi.PaymentReceipt.toDomain(): PaymentReceipt = PaymentReceipt(
    productInfo = this.productInfo.toDomain(),
    date = this.date,
    sellerBotUserId = this.sellerBotUserId,
    type = this.type.toDomain()
)

fun TdApi.PaymentReceiptType.toDomain(): PaymentReceiptType = when(this) {
    is TdApi.PaymentReceiptTypeRegular -> this.toDomain()
    is TdApi.PaymentReceiptTypeStars -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PaymentReceiptTypeRegular.toDomain(): PaymentReceiptTypeRegular = PaymentReceiptTypeRegular(
    paymentProviderUserId = this.paymentProviderUserId,
    invoice = this.invoice.toDomain(),
    orderInfo = this.orderInfo?.toDomain(),
    shippingOption = this.shippingOption?.toDomain(),
    credentialsTitle = this.credentialsTitle,
    tipAmount = this.tipAmount
)

fun TdApi.PaymentReceiptTypeStars.toDomain(): PaymentReceiptTypeStars = PaymentReceiptTypeStars(
    starCount = this.starCount,
    transactionId = this.transactionId
)

fun TdApi.PaymentResult.toDomain(): PaymentResult = PaymentResult(
    success = this.success,
    verificationUrl = this.verificationUrl
)

fun TdApi.PremiumFeature.toDomain(): PremiumFeature = when(this) {
    is TdApi.PremiumFeatureIncreasedLimits -> this.toDomain()
    is TdApi.PremiumFeatureIncreasedUploadFileSize -> this.toDomain()
    is TdApi.PremiumFeatureImprovedDownloadSpeed -> this.toDomain()
    is TdApi.PremiumFeatureVoiceRecognition -> this.toDomain()
    is TdApi.PremiumFeatureDisabledAds -> this.toDomain()
    is TdApi.PremiumFeatureUniqueReactions -> this.toDomain()
    is TdApi.PremiumFeatureUniqueStickers -> this.toDomain()
    is TdApi.PremiumFeatureCustomEmoji -> this.toDomain()
    is TdApi.PremiumFeatureAdvancedChatManagement -> this.toDomain()
    is TdApi.PremiumFeatureProfileBadge -> this.toDomain()
    is TdApi.PremiumFeatureEmojiStatus -> this.toDomain()
    is TdApi.PremiumFeatureAnimatedProfilePhoto -> this.toDomain()
    is TdApi.PremiumFeatureForumTopicIcon -> this.toDomain()
    is TdApi.PremiumFeatureAppIcons -> this.toDomain()
    is TdApi.PremiumFeatureRealTimeChatTranslation -> this.toDomain()
    is TdApi.PremiumFeatureUpgradedStories -> this.toDomain()
    is TdApi.PremiumFeatureChatBoost -> this.toDomain()
    is TdApi.PremiumFeatureAccentColor -> this.toDomain()
    is TdApi.PremiumFeatureBackgroundForBoth -> this.toDomain()
    is TdApi.PremiumFeatureSavedMessagesTags -> this.toDomain()
    is TdApi.PremiumFeatureMessagePrivacy -> this.toDomain()
    is TdApi.PremiumFeatureLastSeenTimes -> this.toDomain()
    is TdApi.PremiumFeatureBusiness -> this.toDomain()
    is TdApi.PremiumFeatureMessageEffects -> this.toDomain()
    is TdApi.PremiumFeatureChecklists -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PremiumFeatureAccentColor.toDomain(): PremiumFeatureAccentColor = PremiumFeatureAccentColor

fun TdApi.PremiumFeatureAdvancedChatManagement.toDomain(): PremiumFeatureAdvancedChatManagement = PremiumFeatureAdvancedChatManagement

fun TdApi.PremiumFeatureAnimatedProfilePhoto.toDomain(): PremiumFeatureAnimatedProfilePhoto = PremiumFeatureAnimatedProfilePhoto

fun TdApi.PremiumFeatureAppIcons.toDomain(): PremiumFeatureAppIcons = PremiumFeatureAppIcons

fun TdApi.PremiumFeatureBackgroundForBoth.toDomain(): PremiumFeatureBackgroundForBoth = PremiumFeatureBackgroundForBoth

fun TdApi.PremiumFeatureBusiness.toDomain(): PremiumFeatureBusiness = PremiumFeatureBusiness

fun TdApi.PremiumFeatureChatBoost.toDomain(): PremiumFeatureChatBoost = PremiumFeatureChatBoost

fun TdApi.PremiumFeatureChecklists.toDomain(): PremiumFeatureChecklists = PremiumFeatureChecklists

fun TdApi.PremiumFeatureCustomEmoji.toDomain(): PremiumFeatureCustomEmoji = PremiumFeatureCustomEmoji

fun TdApi.PremiumFeatureDisabledAds.toDomain(): PremiumFeatureDisabledAds = PremiumFeatureDisabledAds

fun TdApi.PremiumFeatureEmojiStatus.toDomain(): PremiumFeatureEmojiStatus = PremiumFeatureEmojiStatus

fun TdApi.PremiumFeatureForumTopicIcon.toDomain(): PremiumFeatureForumTopicIcon = PremiumFeatureForumTopicIcon

fun TdApi.PremiumFeatureImprovedDownloadSpeed.toDomain(): PremiumFeatureImprovedDownloadSpeed = PremiumFeatureImprovedDownloadSpeed

fun TdApi.PremiumFeatureIncreasedLimits.toDomain(): PremiumFeatureIncreasedLimits = PremiumFeatureIncreasedLimits

fun TdApi.PremiumFeatureIncreasedUploadFileSize.toDomain(): PremiumFeatureIncreasedUploadFileSize = PremiumFeatureIncreasedUploadFileSize

fun TdApi.PremiumFeatureLastSeenTimes.toDomain(): PremiumFeatureLastSeenTimes = PremiumFeatureLastSeenTimes

fun TdApi.PremiumFeatureMessageEffects.toDomain(): PremiumFeatureMessageEffects = PremiumFeatureMessageEffects

fun TdApi.PremiumFeatureMessagePrivacy.toDomain(): PremiumFeatureMessagePrivacy = PremiumFeatureMessagePrivacy

fun TdApi.PremiumFeatureProfileBadge.toDomain(): PremiumFeatureProfileBadge = PremiumFeatureProfileBadge

fun TdApi.PremiumFeaturePromotionAnimation.toDomain(): PremiumFeaturePromotionAnimation = PremiumFeaturePromotionAnimation(
    feature = this.feature.toDomain(),
    animation = this.animation.toDomain()
)

fun TdApi.PremiumFeatureRealTimeChatTranslation.toDomain(): PremiumFeatureRealTimeChatTranslation = PremiumFeatureRealTimeChatTranslation

fun TdApi.PremiumFeatureSavedMessagesTags.toDomain(): PremiumFeatureSavedMessagesTags = PremiumFeatureSavedMessagesTags

fun TdApi.PremiumFeatureUniqueReactions.toDomain(): PremiumFeatureUniqueReactions = PremiumFeatureUniqueReactions

fun TdApi.PremiumFeatureUniqueStickers.toDomain(): PremiumFeatureUniqueStickers = PremiumFeatureUniqueStickers

fun TdApi.PremiumFeatureUpgradedStories.toDomain(): PremiumFeatureUpgradedStories = PremiumFeatureUpgradedStories

fun TdApi.PremiumFeatureVoiceRecognition.toDomain(): PremiumFeatureVoiceRecognition = PremiumFeatureVoiceRecognition

fun TdApi.PremiumFeatures.toDomain(): PremiumFeatures = PremiumFeatures(
    features = this.features.map { it.toDomain() },
    limits = this.limits.map { it.toDomain() },
    paymentLink = this.paymentLink?.toDomain()
)

fun TdApi.PremiumGiveawayPaymentOption.toDomain(): PremiumGiveawayPaymentOption = PremiumGiveawayPaymentOption(
    currency = this.currency,
    amount = this.amount,
    winnerCount = this.winnerCount,
    monthCount = this.monthCount,
    storeProductId = this.storeProductId,
    storeProductQuantity = this.storeProductQuantity
)

fun TdApi.PremiumGiveawayPaymentOptions.toDomain(): PremiumGiveawayPaymentOptions = PremiumGiveawayPaymentOptions(
    options = this.options.map { it.toDomain() }
)

fun TdApi.PremiumLimit.toDomain(): PremiumLimit = PremiumLimit(
    type = this.type.toDomain(),
    defaultValue = this.defaultValue,
    premiumValue = this.premiumValue
)

fun TdApi.PremiumLimitType.toDomain(): PremiumLimitType = when(this) {
    is TdApi.PremiumLimitTypeSupergroupCount -> this.toDomain()
    is TdApi.PremiumLimitTypePinnedChatCount -> this.toDomain()
    is TdApi.PremiumLimitTypeCreatedPublicChatCount -> this.toDomain()
    is TdApi.PremiumLimitTypeSavedAnimationCount -> this.toDomain()
    is TdApi.PremiumLimitTypeFavoriteStickerCount -> this.toDomain()
    is TdApi.PremiumLimitTypeChatFolderCount -> this.toDomain()
    is TdApi.PremiumLimitTypeChatFolderChosenChatCount -> this.toDomain()
    is TdApi.PremiumLimitTypePinnedArchivedChatCount -> this.toDomain()
    is TdApi.PremiumLimitTypePinnedSavedMessagesTopicCount -> this.toDomain()
    is TdApi.PremiumLimitTypeCaptionLength -> this.toDomain()
    is TdApi.PremiumLimitTypeBioLength -> this.toDomain()
    is TdApi.PremiumLimitTypeChatFolderInviteLinkCount -> this.toDomain()
    is TdApi.PremiumLimitTypeShareableChatFolderCount -> this.toDomain()
    is TdApi.PremiumLimitTypeActiveStoryCount -> this.toDomain()
    is TdApi.PremiumLimitTypeWeeklyPostedStoryCount -> this.toDomain()
    is TdApi.PremiumLimitTypeMonthlyPostedStoryCount -> this.toDomain()
    is TdApi.PremiumLimitTypeStoryCaptionLength -> this.toDomain()
    is TdApi.PremiumLimitTypeStorySuggestedReactionAreaCount -> this.toDomain()
    is TdApi.PremiumLimitTypeSimilarChatCount -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PremiumLimitTypeActiveStoryCount.toDomain(): PremiumLimitTypeActiveStoryCount = PremiumLimitTypeActiveStoryCount

fun TdApi.PremiumLimitTypeBioLength.toDomain(): PremiumLimitTypeBioLength = PremiumLimitTypeBioLength

fun TdApi.PremiumLimitTypeCaptionLength.toDomain(): PremiumLimitTypeCaptionLength = PremiumLimitTypeCaptionLength

fun TdApi.PremiumLimitTypeChatFolderChosenChatCount.toDomain(): PremiumLimitTypeChatFolderChosenChatCount = PremiumLimitTypeChatFolderChosenChatCount

fun TdApi.PremiumLimitTypeChatFolderCount.toDomain(): PremiumLimitTypeChatFolderCount = PremiumLimitTypeChatFolderCount

fun TdApi.PremiumLimitTypeChatFolderInviteLinkCount.toDomain(): PremiumLimitTypeChatFolderInviteLinkCount = PremiumLimitTypeChatFolderInviteLinkCount

fun TdApi.PremiumLimitTypeCreatedPublicChatCount.toDomain(): PremiumLimitTypeCreatedPublicChatCount = PremiumLimitTypeCreatedPublicChatCount

fun TdApi.PremiumLimitTypeFavoriteStickerCount.toDomain(): PremiumLimitTypeFavoriteStickerCount = PremiumLimitTypeFavoriteStickerCount

fun TdApi.PremiumLimitTypeMonthlyPostedStoryCount.toDomain(): PremiumLimitTypeMonthlyPostedStoryCount = PremiumLimitTypeMonthlyPostedStoryCount

fun TdApi.PremiumLimitTypePinnedArchivedChatCount.toDomain(): PremiumLimitTypePinnedArchivedChatCount = PremiumLimitTypePinnedArchivedChatCount

fun TdApi.PremiumLimitTypePinnedChatCount.toDomain(): PremiumLimitTypePinnedChatCount = PremiumLimitTypePinnedChatCount

fun TdApi.PremiumLimitTypePinnedSavedMessagesTopicCount.toDomain(): PremiumLimitTypePinnedSavedMessagesTopicCount = PremiumLimitTypePinnedSavedMessagesTopicCount

fun TdApi.PremiumLimitTypeSavedAnimationCount.toDomain(): PremiumLimitTypeSavedAnimationCount = PremiumLimitTypeSavedAnimationCount

fun TdApi.PremiumLimitTypeShareableChatFolderCount.toDomain(): PremiumLimitTypeShareableChatFolderCount = PremiumLimitTypeShareableChatFolderCount

fun TdApi.PremiumLimitTypeSimilarChatCount.toDomain(): PremiumLimitTypeSimilarChatCount = PremiumLimitTypeSimilarChatCount

fun TdApi.PremiumLimitTypeStoryCaptionLength.toDomain(): PremiumLimitTypeStoryCaptionLength = PremiumLimitTypeStoryCaptionLength

fun TdApi.PremiumLimitTypeStorySuggestedReactionAreaCount.toDomain(): PremiumLimitTypeStorySuggestedReactionAreaCount = PremiumLimitTypeStorySuggestedReactionAreaCount

fun TdApi.PremiumLimitTypeSupergroupCount.toDomain(): PremiumLimitTypeSupergroupCount = PremiumLimitTypeSupergroupCount

fun TdApi.PremiumLimitTypeWeeklyPostedStoryCount.toDomain(): PremiumLimitTypeWeeklyPostedStoryCount = PremiumLimitTypeWeeklyPostedStoryCount

fun TdApi.PremiumState.toDomain(): PremiumState = PremiumState(
    state = this.state.toDomain(),
    paymentOptions = this.paymentOptions.map { it.toDomain() },
    animations = this.animations.map { it.toDomain() },
    businessAnimations = this.businessAnimations.map { it.toDomain() }
)

fun TdApi.PremiumStatePaymentOption.toDomain(): PremiumStatePaymentOption = PremiumStatePaymentOption(
    paymentOption = this.paymentOption.toDomain(),
    isCurrent = this.isCurrent,
    isUpgrade = this.isUpgrade,
    lastTransactionId = this.lastTransactionId
)

fun TdApi.SendGift.toDomain(): SendGift = SendGift(
    giftId = this.giftId,
    ownerId = this.ownerId.toDomain(),
    text = this.text.toDomain(),
    isPrivate = this.isPrivate,
    payForUpgrade = this.payForUpgrade
)

fun TdApi.SendPaymentForm.toDomain(): SendPaymentForm = SendPaymentForm(
    inputInvoice = this.inputInvoice.toDomain(),
    paymentFormId = this.paymentFormId,
    orderInfoId = this.orderInfoId,
    shippingOptionId = this.shippingOptionId,
    credentials = this.credentials.toDomain(),
    tipAmount = this.tipAmount
)

fun TdApi.StarTransaction.toDomain(): StarTransaction = StarTransaction(
    id = this.id,
    starAmount = this.starAmount.toDomain(),
    isRefund = this.isRefund,
    date = this.date,
    type = this.type.toDomain()
)

fun TdApi.StarTransactionType.toDomain(): StarTransactionType = when(this) {
    is TdApi.StarTransactionTypePremiumBotDeposit -> this.toDomain()
    is TdApi.StarTransactionTypeAppStoreDeposit -> this.toDomain()
    is TdApi.StarTransactionTypeGooglePlayDeposit -> this.toDomain()
    is TdApi.StarTransactionTypeFragmentDeposit -> this.toDomain()
    is TdApi.StarTransactionTypeUserDeposit -> this.toDomain()
    is TdApi.StarTransactionTypeGiveawayDeposit -> this.toDomain()
    is TdApi.StarTransactionTypeFragmentWithdrawal -> this.toDomain()
    is TdApi.StarTransactionTypeTelegramAdsWithdrawal -> this.toDomain()
    is TdApi.StarTransactionTypeTelegramApiUsage -> this.toDomain()
    is TdApi.StarTransactionTypeBotPaidMediaPurchase -> this.toDomain()
    is TdApi.StarTransactionTypeBotPaidMediaSale -> this.toDomain()
    is TdApi.StarTransactionTypeChannelPaidMediaPurchase -> this.toDomain()
    is TdApi.StarTransactionTypeChannelPaidMediaSale -> this.toDomain()
    is TdApi.StarTransactionTypeBotInvoicePurchase -> this.toDomain()
    is TdApi.StarTransactionTypeBotInvoiceSale -> this.toDomain()
    is TdApi.StarTransactionTypeBotSubscriptionPurchase -> this.toDomain()
    is TdApi.StarTransactionTypeBotSubscriptionSale -> this.toDomain()
    is TdApi.StarTransactionTypeChannelSubscriptionPurchase -> this.toDomain()
    is TdApi.StarTransactionTypeChannelSubscriptionSale -> this.toDomain()
    is TdApi.StarTransactionTypeGiftPurchase -> this.toDomain()
    is TdApi.StarTransactionTypeGiftTransfer -> this.toDomain()
    is TdApi.StarTransactionTypeGiftSale -> this.toDomain()
    is TdApi.StarTransactionTypeGiftUpgrade -> this.toDomain()
    is TdApi.StarTransactionTypeGiftUpgradePurchase -> this.toDomain()
    is TdApi.StarTransactionTypeUpgradedGiftPurchase -> this.toDomain()
    is TdApi.StarTransactionTypeUpgradedGiftSale -> this.toDomain()
    is TdApi.StarTransactionTypeChannelPaidReactionSend -> this.toDomain()
    is TdApi.StarTransactionTypeChannelPaidReactionReceive -> this.toDomain()
    is TdApi.StarTransactionTypeAffiliateProgramCommission -> this.toDomain()
    is TdApi.StarTransactionTypePaidMessageSend -> this.toDomain()
    is TdApi.StarTransactionTypePaidMessageReceive -> this.toDomain()
    is TdApi.StarTransactionTypeSuggestedPostPaymentSend -> this.toDomain()
    is TdApi.StarTransactionTypeSuggestedPostPaymentReceive -> this.toDomain()
    is TdApi.StarTransactionTypePremiumPurchase -> this.toDomain()
    is TdApi.StarTransactionTypeBusinessBotTransferSend -> this.toDomain()
    is TdApi.StarTransactionTypeBusinessBotTransferReceive -> this.toDomain()
    is TdApi.StarTransactionTypePublicPostSearch -> this.toDomain()
    is TdApi.StarTransactionTypeUnsupported -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.StarTransactionTypeAffiliateProgramCommission.toDomain(): StarTransactionTypeAffiliateProgramCommission = StarTransactionTypeAffiliateProgramCommission(
    chatId = this.chatId,
    commissionPerMille = this.commissionPerMille
)

fun TdApi.StarTransactionTypeAppStoreDeposit.toDomain(): StarTransactionTypeAppStoreDeposit = StarTransactionTypeAppStoreDeposit

fun TdApi.StarTransactionTypeBotInvoicePurchase.toDomain(): StarTransactionTypeBotInvoicePurchase = StarTransactionTypeBotInvoicePurchase(
    userId = this.userId,
    productInfo = this.productInfo.toDomain()
)

fun TdApi.StarTransactionTypeBotInvoiceSale.toDomain(): StarTransactionTypeBotInvoiceSale = StarTransactionTypeBotInvoiceSale(
    userId = this.userId,
    productInfo = this.productInfo.toDomain(),
    invoicePayload = this.invoicePayload,
    affiliate = this.affiliate?.toDomain()
)

fun TdApi.StarTransactionTypeBotPaidMediaPurchase.toDomain(): StarTransactionTypeBotPaidMediaPurchase = StarTransactionTypeBotPaidMediaPurchase(
    userId = this.userId,
    media = this.media.map { it.toDomain() }
)

fun TdApi.StarTransactionTypeBotPaidMediaSale.toDomain(): StarTransactionTypeBotPaidMediaSale = StarTransactionTypeBotPaidMediaSale(
    userId = this.userId,
    media = this.media.map { it.toDomain() },
    payload = this.payload,
    affiliate = this.affiliate?.toDomain()
)

fun TdApi.StarTransactionTypeBotSubscriptionPurchase.toDomain(): StarTransactionTypeBotSubscriptionPurchase = StarTransactionTypeBotSubscriptionPurchase(
    userId = this.userId,
    subscriptionPeriod = this.subscriptionPeriod,
    productInfo = this.productInfo.toDomain()
)

fun TdApi.StarTransactionTypeBotSubscriptionSale.toDomain(): StarTransactionTypeBotSubscriptionSale = StarTransactionTypeBotSubscriptionSale(
    userId = this.userId,
    subscriptionPeriod = this.subscriptionPeriod,
    productInfo = this.productInfo.toDomain(),
    invoicePayload = this.invoicePayload,
    affiliate = this.affiliate?.toDomain()
)

fun TdApi.StarTransactionTypeBusinessBotTransferReceive.toDomain(): StarTransactionTypeBusinessBotTransferReceive = StarTransactionTypeBusinessBotTransferReceive(
    userId = this.userId
)

fun TdApi.StarTransactionTypeBusinessBotTransferSend.toDomain(): StarTransactionTypeBusinessBotTransferSend = StarTransactionTypeBusinessBotTransferSend(
    userId = this.userId
)

fun TdApi.StarTransactionTypeChannelPaidMediaPurchase.toDomain(): StarTransactionTypeChannelPaidMediaPurchase = StarTransactionTypeChannelPaidMediaPurchase(
    chatId = this.chatId,
    messageId = this.messageId,
    media = this.media.map { it.toDomain() }
)

fun TdApi.StarTransactionTypeChannelPaidMediaSale.toDomain(): StarTransactionTypeChannelPaidMediaSale = StarTransactionTypeChannelPaidMediaSale(
    userId = this.userId,
    messageId = this.messageId,
    media = this.media.map { it.toDomain() }
)

fun TdApi.StarTransactionTypeChannelPaidReactionReceive.toDomain(): StarTransactionTypeChannelPaidReactionReceive = StarTransactionTypeChannelPaidReactionReceive(
    userId = this.userId,
    messageId = this.messageId
)

fun TdApi.StarTransactionTypeChannelPaidReactionSend.toDomain(): StarTransactionTypeChannelPaidReactionSend = StarTransactionTypeChannelPaidReactionSend(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.StarTransactionTypeChannelSubscriptionPurchase.toDomain(): StarTransactionTypeChannelSubscriptionPurchase = StarTransactionTypeChannelSubscriptionPurchase(
    chatId = this.chatId,
    subscriptionPeriod = this.subscriptionPeriod
)

fun TdApi.StarTransactionTypeChannelSubscriptionSale.toDomain(): StarTransactionTypeChannelSubscriptionSale = StarTransactionTypeChannelSubscriptionSale(
    userId = this.userId,
    subscriptionPeriod = this.subscriptionPeriod
)

fun TdApi.StarTransactionTypeFragmentDeposit.toDomain(): StarTransactionTypeFragmentDeposit = StarTransactionTypeFragmentDeposit

fun TdApi.StarTransactionTypeFragmentWithdrawal.toDomain(): StarTransactionTypeFragmentWithdrawal = StarTransactionTypeFragmentWithdrawal(
    withdrawalState = this.withdrawalState?.toDomain()
)

fun TdApi.StarTransactionTypeGiftPurchase.toDomain(): StarTransactionTypeGiftPurchase = StarTransactionTypeGiftPurchase(
    ownerId = this.ownerId.toDomain(),
    gift = this.gift.toDomain()
)

fun TdApi.StarTransactionTypeGiftSale.toDomain(): StarTransactionTypeGiftSale = StarTransactionTypeGiftSale(
    userId = this.userId,
    gift = this.gift.toDomain()
)

fun TdApi.StarTransactionTypeGiftTransfer.toDomain(): StarTransactionTypeGiftTransfer = StarTransactionTypeGiftTransfer(
    ownerId = this.ownerId.toDomain(),
    gift = this.gift.toDomain()
)

fun TdApi.StarTransactionTypeGiftUpgrade.toDomain(): StarTransactionTypeGiftUpgrade = StarTransactionTypeGiftUpgrade(
    userId = this.userId,
    gift = this.gift.toDomain()
)

fun TdApi.StarTransactionTypeGiftUpgradePurchase.toDomain(): StarTransactionTypeGiftUpgradePurchase = StarTransactionTypeGiftUpgradePurchase(
    ownerId = this.ownerId.toDomain(),
    gift = this.gift.toDomain()
)

fun TdApi.StarTransactionTypeGiveawayDeposit.toDomain(): StarTransactionTypeGiveawayDeposit = StarTransactionTypeGiveawayDeposit(
    chatId = this.chatId,
    giveawayMessageId = this.giveawayMessageId
)

fun TdApi.StarTransactionTypeGooglePlayDeposit.toDomain(): StarTransactionTypeGooglePlayDeposit = StarTransactionTypeGooglePlayDeposit

fun TdApi.StarTransactionTypePaidMessageReceive.toDomain(): StarTransactionTypePaidMessageReceive = StarTransactionTypePaidMessageReceive(
    senderId = this.senderId.toDomain(),
    messageCount = this.messageCount,
    commissionPerMille = this.commissionPerMille,
    commissionStarAmount = this.commissionStarAmount.toDomain()
)

fun TdApi.StarTransactionTypePaidMessageSend.toDomain(): StarTransactionTypePaidMessageSend = StarTransactionTypePaidMessageSend(
    chatId = this.chatId,
    messageCount = this.messageCount
)

fun TdApi.StarTransactionTypePremiumBotDeposit.toDomain(): StarTransactionTypePremiumBotDeposit = StarTransactionTypePremiumBotDeposit

fun TdApi.StarTransactionTypePremiumPurchase.toDomain(): StarTransactionTypePremiumPurchase = StarTransactionTypePremiumPurchase(
    userId = this.userId,
    monthCount = this.monthCount,
    sticker = this.sticker?.toDomain()
)

fun TdApi.StarTransactionTypePublicPostSearch.toDomain(): StarTransactionTypePublicPostSearch = StarTransactionTypePublicPostSearch

fun TdApi.StarTransactionTypeSuggestedPostPaymentReceive.toDomain(): StarTransactionTypeSuggestedPostPaymentReceive = StarTransactionTypeSuggestedPostPaymentReceive(
    userId = this.userId
)

fun TdApi.StarTransactionTypeSuggestedPostPaymentSend.toDomain(): StarTransactionTypeSuggestedPostPaymentSend = StarTransactionTypeSuggestedPostPaymentSend(
    chatId = this.chatId
)

fun TdApi.StarTransactionTypeTelegramAdsWithdrawal.toDomain(): StarTransactionTypeTelegramAdsWithdrawal = StarTransactionTypeTelegramAdsWithdrawal

fun TdApi.StarTransactionTypeTelegramApiUsage.toDomain(): StarTransactionTypeTelegramApiUsage = StarTransactionTypeTelegramApiUsage(
    requestCount = this.requestCount
)

fun TdApi.StarTransactionTypeUnsupported.toDomain(): StarTransactionTypeUnsupported = StarTransactionTypeUnsupported

fun TdApi.StarTransactionTypeUpgradedGiftPurchase.toDomain(): StarTransactionTypeUpgradedGiftPurchase = StarTransactionTypeUpgradedGiftPurchase(
    userId = this.userId,
    gift = this.gift.toDomain()
)

fun TdApi.StarTransactionTypeUpgradedGiftSale.toDomain(): StarTransactionTypeUpgradedGiftSale = StarTransactionTypeUpgradedGiftSale(
    userId = this.userId,
    gift = this.gift.toDomain(),
    commissionPerMille = this.commissionPerMille,
    commissionStarAmount = this.commissionStarAmount.toDomain()
)

fun TdApi.StarTransactionTypeUserDeposit.toDomain(): StarTransactionTypeUserDeposit = StarTransactionTypeUserDeposit(
    userId = this.userId,
    sticker = this.sticker?.toDomain()
)

fun TdApi.StarTransactions.toDomain(): StarTransactions = StarTransactions(
    starAmount = this.starAmount.toDomain(),
    transactions = this.transactions.map { it.toDomain() },
    nextOffset = this.nextOffset
)

fun TdApi.UpgradeGift.toDomain(): UpgradeGift = UpgradeGift(
    businessConnectionId = this.businessConnectionId,
    receivedGiftId = this.receivedGiftId,
    keepOriginalDetails = this.keepOriginalDetails,
    starCount = this.starCount
)

fun TdApi.UpgradeGiftResult.toDomain(): UpgradeGiftResult = UpgradeGiftResult(
    gift = this.gift.toDomain(),
    receivedGiftId = this.receivedGiftId,
    isSaved = this.isSaved,
    canBeTransferred = this.canBeTransferred,
    transferStarCount = this.transferStarCount,
    nextTransferDate = this.nextTransferDate,
    nextResaleDate = this.nextResaleDate,
    exportDate = this.exportDate
)

fun TdApi.UpgradedGift.toDomain(): UpgradedGift = UpgradedGift(
    id = this.id,
    regularGiftId = this.regularGiftId,
    publisherChatId = this.publisherChatId,
    title = this.title,
    name = this.name,
    number = this.number,
    totalUpgradedCount = this.totalUpgradedCount,
    maxUpgradedCount = this.maxUpgradedCount,
    isPremium = this.isPremium,
    isThemeAvailable = this.isThemeAvailable,
    usedThemeChatId = this.usedThemeChatId,
    ownerId = this.ownerId?.toDomain(),
    ownerAddress = this.ownerAddress,
    ownerName = this.ownerName,
    giftAddress = this.giftAddress,
    model = this.model.toDomain(),
    symbol = this.symbol.toDomain(),
    backdrop = this.backdrop.toDomain(),
    originalDetails = this.originalDetails?.toDomain(),
    resaleParameters = this.resaleParameters?.toDomain(),
    valueCurrency = this.valueCurrency,
    valueAmount = this.valueAmount
)

fun TdApi.UpgradedGiftAttributeId.toDomain(): UpgradedGiftAttributeId = when(this) {
    is TdApi.UpgradedGiftAttributeIdModel -> this.toDomain()
    is TdApi.UpgradedGiftAttributeIdSymbol -> this.toDomain()
    is TdApi.UpgradedGiftAttributeIdBackdrop -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.UpgradedGiftAttributeIdBackdrop.toDomain(): UpgradedGiftAttributeIdBackdrop = UpgradedGiftAttributeIdBackdrop(
    backdropId = this.backdropId
)

fun TdApi.UpgradedGiftAttributeIdModel.toDomain(): UpgradedGiftAttributeIdModel = UpgradedGiftAttributeIdModel(
    stickerId = this.stickerId
)

fun TdApi.UpgradedGiftAttributeIdSymbol.toDomain(): UpgradedGiftAttributeIdSymbol = UpgradedGiftAttributeIdSymbol(
    stickerId = this.stickerId
)

fun TdApi.UpgradedGiftBackdrop.toDomain(): UpgradedGiftBackdrop = UpgradedGiftBackdrop(
    id = this.id,
    name = this.name,
    colors = this.colors.toDomain(),
    rarityPerMille = this.rarityPerMille
)

fun TdApi.UpgradedGiftBackdropColors.toDomain(): UpgradedGiftBackdropColors = UpgradedGiftBackdropColors(
    centerColor = this.centerColor,
    edgeColor = this.edgeColor,
    symbolColor = this.symbolColor,
    textColor = this.textColor
)

fun TdApi.UpgradedGiftBackdropCount.toDomain(): UpgradedGiftBackdropCount = UpgradedGiftBackdropCount(
    backdrop = this.backdrop.toDomain(),
    totalCount = this.totalCount
)

fun TdApi.UpgradedGiftModel.toDomain(): UpgradedGiftModel = UpgradedGiftModel(
    name = this.name,
    sticker = this.sticker.toDomain(),
    rarityPerMille = this.rarityPerMille
)

fun TdApi.UpgradedGiftModelCount.toDomain(): UpgradedGiftModelCount = UpgradedGiftModelCount(
    model = this.model.toDomain(),
    totalCount = this.totalCount
)

fun TdApi.UpgradedGiftOrigin.toDomain(): UpgradedGiftOrigin = when(this) {
    is TdApi.UpgradedGiftOriginUpgrade -> this.toDomain()
    is TdApi.UpgradedGiftOriginTransfer -> this.toDomain()
    is TdApi.UpgradedGiftOriginResale -> this.toDomain()
    is TdApi.UpgradedGiftOriginPrepaidUpgrade -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.UpgradedGiftOriginPrepaidUpgrade.toDomain(): UpgradedGiftOriginPrepaidUpgrade = UpgradedGiftOriginPrepaidUpgrade

fun TdApi.UpgradedGiftOriginResale.toDomain(): UpgradedGiftOriginResale = UpgradedGiftOriginResale(
    price = this.price.toDomain()
)

fun TdApi.UpgradedGiftOriginTransfer.toDomain(): UpgradedGiftOriginTransfer = UpgradedGiftOriginTransfer

fun TdApi.UpgradedGiftOriginUpgrade.toDomain(): UpgradedGiftOriginUpgrade = UpgradedGiftOriginUpgrade(
    giftMessageId = this.giftMessageId
)

fun TdApi.UpgradedGiftOriginalDetails.toDomain(): UpgradedGiftOriginalDetails = UpgradedGiftOriginalDetails(
    senderId = this.senderId?.toDomain(),
    receiverId = this.receiverId.toDomain(),
    text = this.text.toDomain(),
    date = this.date
)

fun TdApi.UpgradedGiftSymbol.toDomain(): UpgradedGiftSymbol = UpgradedGiftSymbol(
    name = this.name,
    sticker = this.sticker.toDomain(),
    rarityPerMille = this.rarityPerMille
)

fun TdApi.UpgradedGiftSymbolCount.toDomain(): UpgradedGiftSymbolCount = UpgradedGiftSymbolCount(
    symbol = this.symbol.toDomain(),
    totalCount = this.totalCount
)

fun TdApi.UpgradedGiftValueInfo.toDomain(): UpgradedGiftValueInfo = UpgradedGiftValueInfo(
    currency = this.currency,
    value = this.value,
    isValueAverage = this.isValueAverage,
    initialSaleDate = this.initialSaleDate,
    initialSaleStarCount = this.initialSaleStarCount,
    initialSalePrice = this.initialSalePrice,
    lastSaleDate = this.lastSaleDate,
    lastSalePrice = this.lastSalePrice,
    isLastSaleOnFragment = this.isLastSaleOnFragment,
    minimumPrice = this.minimumPrice,
    averageSalePrice = this.averageSalePrice,
    telegramListedGiftCount = this.telegramListedGiftCount,
    fragmentListedGiftCount = this.fragmentListedGiftCount,
    fragmentUrl = this.fragmentUrl
)

fun TdApi.ValidateOrderInfo.toDomain(): ValidateOrderInfo = ValidateOrderInfo(
    inputInvoice = this.inputInvoice.toDomain(),
    orderInfo = this.orderInfo.toDomain(),
    allowSave = this.allowSave
)

fun TdApi.ValidatedOrderInfo.toDomain(): ValidatedOrderInfo = ValidatedOrderInfo(
    orderInfoId = this.orderInfoId,
    shippingOptions = this.shippingOptions.map { it.toDomain() }
)

