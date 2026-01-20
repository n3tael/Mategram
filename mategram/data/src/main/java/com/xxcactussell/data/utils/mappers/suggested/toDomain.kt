package com.xxcactussell.data.utils.mappers.suggested

import com.xxcactussell.data.utils.mappers.formatted.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.SuggestedAction.toDomain(): SuggestedAction = when(this) {
    is TdApi.SuggestedActionEnableArchiveAndMuteNewChats -> this.toDomain()
    is TdApi.SuggestedActionCheckPassword -> this.toDomain()
    is TdApi.SuggestedActionCheckPhoneNumber -> this.toDomain()
    is TdApi.SuggestedActionViewChecksHint -> this.toDomain()
    is TdApi.SuggestedActionConvertToBroadcastGroup -> this.toDomain()
    is TdApi.SuggestedActionSetPassword -> this.toDomain()
    is TdApi.SuggestedActionUpgradePremium -> this.toDomain()
    is TdApi.SuggestedActionRestorePremium -> this.toDomain()
    is TdApi.SuggestedActionSubscribeToAnnualPremium -> this.toDomain()
    is TdApi.SuggestedActionGiftPremiumForChristmas -> this.toDomain()
    is TdApi.SuggestedActionSetBirthdate -> this.toDomain()
    is TdApi.SuggestedActionSetProfilePhoto -> this.toDomain()
    is TdApi.SuggestedActionExtendPremium -> this.toDomain()
    is TdApi.SuggestedActionExtendStarSubscriptions -> this.toDomain()
    is TdApi.SuggestedActionCustom -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.SuggestedActionCheckPassword.toDomain(): SuggestedActionCheckPassword = SuggestedActionCheckPassword

fun TdApi.SuggestedActionCheckPhoneNumber.toDomain(): SuggestedActionCheckPhoneNumber = SuggestedActionCheckPhoneNumber

fun TdApi.SuggestedActionConvertToBroadcastGroup.toDomain(): SuggestedActionConvertToBroadcastGroup = SuggestedActionConvertToBroadcastGroup(
    supergroupId = this.supergroupId
)

fun TdApi.SuggestedActionCustom.toDomain(): SuggestedActionCustom = SuggestedActionCustom(
    name = this.name,
    title = this.title.toDomain(),
    description = this.description.toDomain(),
    url = this.url
)

fun TdApi.SuggestedActionEnableArchiveAndMuteNewChats.toDomain(): SuggestedActionEnableArchiveAndMuteNewChats = SuggestedActionEnableArchiveAndMuteNewChats

fun TdApi.SuggestedActionExtendPremium.toDomain(): SuggestedActionExtendPremium = SuggestedActionExtendPremium(
    managePremiumSubscriptionUrl = this.managePremiumSubscriptionUrl
)

fun TdApi.SuggestedActionExtendStarSubscriptions.toDomain(): SuggestedActionExtendStarSubscriptions = SuggestedActionExtendStarSubscriptions

fun TdApi.SuggestedActionGiftPremiumForChristmas.toDomain(): SuggestedActionGiftPremiumForChristmas = SuggestedActionGiftPremiumForChristmas

fun TdApi.SuggestedActionRestorePremium.toDomain(): SuggestedActionRestorePremium = SuggestedActionRestorePremium

fun TdApi.SuggestedActionSetBirthdate.toDomain(): SuggestedActionSetBirthdate = SuggestedActionSetBirthdate

fun TdApi.SuggestedActionSetPassword.toDomain(): SuggestedActionSetPassword = SuggestedActionSetPassword(
    authorizationDelay = this.authorizationDelay
)

fun TdApi.SuggestedActionSetProfilePhoto.toDomain(): SuggestedActionSetProfilePhoto = SuggestedActionSetProfilePhoto

fun TdApi.SuggestedActionSubscribeToAnnualPremium.toDomain(): SuggestedActionSubscribeToAnnualPremium = SuggestedActionSubscribeToAnnualPremium

fun TdApi.SuggestedActionUpgradePremium.toDomain(): SuggestedActionUpgradePremium = SuggestedActionUpgradePremium

fun TdApi.SuggestedActionViewChecksHint.toDomain(): SuggestedActionViewChecksHint = SuggestedActionViewChecksHint

fun TdApi.SuggestedPostInfo.toDomain(): SuggestedPostInfo = SuggestedPostInfo(
    price = this.price?.toDomain(),
    sendDate = this.sendDate,
    state = this.state.toDomain(),
    canBeApproved = this.canBeApproved,
    canBeDeclined = this.canBeDeclined
)

fun TdApi.SuggestedPostPrice.toDomain(): SuggestedPostPrice = when(this) {
    is TdApi.SuggestedPostPriceStar -> this.toDomain()
    is TdApi.SuggestedPostPriceTon -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.SuggestedPostPriceStar.toDomain(): SuggestedPostPriceStar = SuggestedPostPriceStar(
    starCount = this.starCount
)

fun TdApi.SuggestedPostPriceTon.toDomain(): SuggestedPostPriceTon = SuggestedPostPriceTon(
    toncoinCentCount = this.toncoinCentCount
)

fun TdApi.SuggestedPostRefundReason.toDomain(): SuggestedPostRefundReason = when(this) {
    is TdApi.SuggestedPostRefundReasonPostDeleted -> this.toDomain()
    is TdApi.SuggestedPostRefundReasonPaymentRefunded -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.SuggestedPostRefundReasonPaymentRefunded.toDomain(): SuggestedPostRefundReasonPaymentRefunded = SuggestedPostRefundReasonPaymentRefunded

fun TdApi.SuggestedPostRefundReasonPostDeleted.toDomain(): SuggestedPostRefundReasonPostDeleted = SuggestedPostRefundReasonPostDeleted

fun TdApi.SuggestedPostState.toDomain(): SuggestedPostState = when(this) {
    is TdApi.SuggestedPostStatePending -> this.toDomain()
    is TdApi.SuggestedPostStateApproved -> this.toDomain()
    is TdApi.SuggestedPostStateDeclined -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.SuggestedPostStateApproved.toDomain(): SuggestedPostStateApproved = SuggestedPostStateApproved

fun TdApi.SuggestedPostStateDeclined.toDomain(): SuggestedPostStateDeclined = SuggestedPostStateDeclined

fun TdApi.SuggestedPostStatePending.toDomain(): SuggestedPostStatePending = SuggestedPostStatePending

