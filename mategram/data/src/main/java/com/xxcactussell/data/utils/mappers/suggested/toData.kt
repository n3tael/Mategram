package com.xxcactussell.data.utils.mappers.suggested

import com.xxcactussell.data.utils.mappers.formatted.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun SuggestedAction.toData(): TdApi.SuggestedAction = when(this) {
    is SuggestedActionEnableArchiveAndMuteNewChats -> this.toData()
    is SuggestedActionCheckPassword -> this.toData()
    is SuggestedActionCheckPhoneNumber -> this.toData()
    is SuggestedActionViewChecksHint -> this.toData()
    is SuggestedActionConvertToBroadcastGroup -> this.toData()
    is SuggestedActionSetPassword -> this.toData()
    is SuggestedActionUpgradePremium -> this.toData()
    is SuggestedActionRestorePremium -> this.toData()
    is SuggestedActionSubscribeToAnnualPremium -> this.toData()
    is SuggestedActionGiftPremiumForChristmas -> this.toData()
    is SuggestedActionSetBirthdate -> this.toData()
    is SuggestedActionSetProfilePhoto -> this.toData()
    is SuggestedActionExtendPremium -> this.toData()
    is SuggestedActionExtendStarSubscriptions -> this.toData()
    is SuggestedActionCustom -> this.toData()
}

fun SuggestedActionCheckPassword.toData(): TdApi.SuggestedActionCheckPassword = TdApi.SuggestedActionCheckPassword(
)

fun SuggestedActionCheckPhoneNumber.toData(): TdApi.SuggestedActionCheckPhoneNumber = TdApi.SuggestedActionCheckPhoneNumber(
)

fun SuggestedActionConvertToBroadcastGroup.toData(): TdApi.SuggestedActionConvertToBroadcastGroup = TdApi.SuggestedActionConvertToBroadcastGroup(
    this.supergroupId
)

fun SuggestedActionCustom.toData(): TdApi.SuggestedActionCustom = TdApi.SuggestedActionCustom(
    this.name,
    this.title.toData(),
    this.description.toData(),
    this.url
)

fun SuggestedActionEnableArchiveAndMuteNewChats.toData(): TdApi.SuggestedActionEnableArchiveAndMuteNewChats = TdApi.SuggestedActionEnableArchiveAndMuteNewChats(
)

fun SuggestedActionExtendPremium.toData(): TdApi.SuggestedActionExtendPremium = TdApi.SuggestedActionExtendPremium(
    this.managePremiumSubscriptionUrl
)

fun SuggestedActionExtendStarSubscriptions.toData(): TdApi.SuggestedActionExtendStarSubscriptions = TdApi.SuggestedActionExtendStarSubscriptions(
)

fun SuggestedActionGiftPremiumForChristmas.toData(): TdApi.SuggestedActionGiftPremiumForChristmas = TdApi.SuggestedActionGiftPremiumForChristmas(
)

fun SuggestedActionRestorePremium.toData(): TdApi.SuggestedActionRestorePremium = TdApi.SuggestedActionRestorePremium(
)

fun SuggestedActionSetBirthdate.toData(): TdApi.SuggestedActionSetBirthdate = TdApi.SuggestedActionSetBirthdate(
)

fun SuggestedActionSetPassword.toData(): TdApi.SuggestedActionSetPassword = TdApi.SuggestedActionSetPassword(
    this.authorizationDelay
)

fun SuggestedActionSetProfilePhoto.toData(): TdApi.SuggestedActionSetProfilePhoto = TdApi.SuggestedActionSetProfilePhoto(
)

fun SuggestedActionSubscribeToAnnualPremium.toData(): TdApi.SuggestedActionSubscribeToAnnualPremium = TdApi.SuggestedActionSubscribeToAnnualPremium(
)

fun SuggestedActionUpgradePremium.toData(): TdApi.SuggestedActionUpgradePremium = TdApi.SuggestedActionUpgradePremium(
)

fun SuggestedActionViewChecksHint.toData(): TdApi.SuggestedActionViewChecksHint = TdApi.SuggestedActionViewChecksHint(
)

fun SuggestedPostInfo.toData(): TdApi.SuggestedPostInfo = TdApi.SuggestedPostInfo(
    this.price?.toData(),
    this.sendDate,
    this.state.toData(),
    this.canBeApproved,
    this.canBeDeclined
)

fun SuggestedPostPrice.toData(): TdApi.SuggestedPostPrice = when(this) {
    is SuggestedPostPriceStar -> this.toData()
    is SuggestedPostPriceTon -> this.toData()
}

fun SuggestedPostPriceStar.toData(): TdApi.SuggestedPostPriceStar = TdApi.SuggestedPostPriceStar(
    this.starCount
)

fun SuggestedPostPriceTon.toData(): TdApi.SuggestedPostPriceTon = TdApi.SuggestedPostPriceTon(
    this.toncoinCentCount
)

fun SuggestedPostRefundReason.toData(): TdApi.SuggestedPostRefundReason = when(this) {
    is SuggestedPostRefundReasonPostDeleted -> this.toData()
    is SuggestedPostRefundReasonPaymentRefunded -> this.toData()
}

fun SuggestedPostRefundReasonPaymentRefunded.toData(): TdApi.SuggestedPostRefundReasonPaymentRefunded = TdApi.SuggestedPostRefundReasonPaymentRefunded(
)

fun SuggestedPostRefundReasonPostDeleted.toData(): TdApi.SuggestedPostRefundReasonPostDeleted = TdApi.SuggestedPostRefundReasonPostDeleted(
)

fun SuggestedPostState.toData(): TdApi.SuggestedPostState = when(this) {
    is SuggestedPostStatePending -> this.toData()
    is SuggestedPostStateApproved -> this.toData()
    is SuggestedPostStateDeclined -> this.toData()
}

fun SuggestedPostStateApproved.toData(): TdApi.SuggestedPostStateApproved = TdApi.SuggestedPostStateApproved(
)

fun SuggestedPostStateDeclined.toData(): TdApi.SuggestedPostStateDeclined = TdApi.SuggestedPostStateDeclined(
)

fun SuggestedPostStatePending.toData(): TdApi.SuggestedPostStatePending = TdApi.SuggestedPostStatePending(
)

