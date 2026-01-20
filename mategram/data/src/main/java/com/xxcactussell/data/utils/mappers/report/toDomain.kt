package com.xxcactussell.data.utils.mappers.report

import com.xxcactussell.data.utils.mappers.message.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ReportAuthenticationCodeMissing.toDomain(): ReportAuthenticationCodeMissing = ReportAuthenticationCodeMissing(
    mobileNetworkCode = this.mobileNetworkCode
)

fun TdApi.ReportChat.toDomain(): ReportChat = ReportChat(
    chatId = this.chatId,
    optionId = this.optionId,
    messageIds = this.messageIds,
    text = this.text
)

fun TdApi.ReportChatPhoto.toDomain(): ReportChatPhoto = ReportChatPhoto(
    chatId = this.chatId,
    fileId = this.fileId,
    reason = this.reason.toDomain(),
    text = this.text
)

fun TdApi.ReportChatResult.toDomain(): ReportChatResult = when(this) {
    is TdApi.ReportChatResultOk -> this.toDomain()
    is TdApi.ReportChatResultOptionRequired -> this.toDomain()
    is TdApi.ReportChatResultTextRequired -> this.toDomain()
    is TdApi.ReportChatResultMessagesRequired -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ReportChatResultMessagesRequired.toDomain(): ReportChatResultMessagesRequired = ReportChatResultMessagesRequired

fun TdApi.ReportChatResultOk.toDomain(): ReportChatResultOk = ReportChatResultOk

fun TdApi.ReportChatResultOptionRequired.toDomain(): ReportChatResultOptionRequired = ReportChatResultOptionRequired(
    title = this.title,
    options = this.options.map { it.toDomain() }
)

fun TdApi.ReportChatResultTextRequired.toDomain(): ReportChatResultTextRequired = ReportChatResultTextRequired(
    optionId = this.optionId,
    isOptional = this.isOptional
)

fun TdApi.ReportChatSponsoredMessage.toDomain(): ReportChatSponsoredMessage = ReportChatSponsoredMessage(
    chatId = this.chatId,
    messageId = this.messageId,
    optionId = this.optionId
)

fun TdApi.ReportMessageReactions.toDomain(): ReportMessageReactions = ReportMessageReactions(
    chatId = this.chatId,
    messageId = this.messageId,
    senderId = this.senderId.toDomain()
)

fun TdApi.ReportOption.toDomain(): ReportOption = ReportOption(
    id = this.id,
    text = this.text
)

fun TdApi.ReportPhoneNumberCodeMissing.toDomain(): ReportPhoneNumberCodeMissing = ReportPhoneNumberCodeMissing(
    mobileNetworkCode = this.mobileNetworkCode
)

fun TdApi.ReportReason.toDomain(): ReportReason = when(this) {
    is TdApi.ReportReasonSpam -> this.toDomain()
    is TdApi.ReportReasonViolence -> this.toDomain()
    is TdApi.ReportReasonPornography -> this.toDomain()
    is TdApi.ReportReasonChildAbuse -> this.toDomain()
    is TdApi.ReportReasonCopyright -> this.toDomain()
    is TdApi.ReportReasonUnrelatedLocation -> this.toDomain()
    is TdApi.ReportReasonFake -> this.toDomain()
    is TdApi.ReportReasonIllegalDrugs -> this.toDomain()
    is TdApi.ReportReasonPersonalDetails -> this.toDomain()
    is TdApi.ReportReasonCustom -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ReportReasonChildAbuse.toDomain(): ReportReasonChildAbuse = ReportReasonChildAbuse

fun TdApi.ReportReasonCopyright.toDomain(): ReportReasonCopyright = ReportReasonCopyright

fun TdApi.ReportReasonCustom.toDomain(): ReportReasonCustom = ReportReasonCustom

fun TdApi.ReportReasonFake.toDomain(): ReportReasonFake = ReportReasonFake

fun TdApi.ReportReasonIllegalDrugs.toDomain(): ReportReasonIllegalDrugs = ReportReasonIllegalDrugs

fun TdApi.ReportReasonPersonalDetails.toDomain(): ReportReasonPersonalDetails = ReportReasonPersonalDetails

fun TdApi.ReportReasonPornography.toDomain(): ReportReasonPornography = ReportReasonPornography

fun TdApi.ReportReasonSpam.toDomain(): ReportReasonSpam = ReportReasonSpam

fun TdApi.ReportReasonUnrelatedLocation.toDomain(): ReportReasonUnrelatedLocation = ReportReasonUnrelatedLocation

fun TdApi.ReportReasonViolence.toDomain(): ReportReasonViolence = ReportReasonViolence

fun TdApi.ReportSponsoredChat.toDomain(): ReportSponsoredChat = ReportSponsoredChat(
    sponsoredChatUniqueId = this.sponsoredChatUniqueId,
    optionId = this.optionId
)

fun TdApi.ReportSponsoredResult.toDomain(): ReportSponsoredResult = when(this) {
    is TdApi.ReportSponsoredResultOk -> this.toDomain()
    is TdApi.ReportSponsoredResultFailed -> this.toDomain()
    is TdApi.ReportSponsoredResultOptionRequired -> this.toDomain()
    is TdApi.ReportSponsoredResultAdsHidden -> this.toDomain()
    is TdApi.ReportSponsoredResultPremiumRequired -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ReportSponsoredResultAdsHidden.toDomain(): ReportSponsoredResultAdsHidden = ReportSponsoredResultAdsHidden

fun TdApi.ReportSponsoredResultFailed.toDomain(): ReportSponsoredResultFailed = ReportSponsoredResultFailed

fun TdApi.ReportSponsoredResultOk.toDomain(): ReportSponsoredResultOk = ReportSponsoredResultOk

fun TdApi.ReportSponsoredResultOptionRequired.toDomain(): ReportSponsoredResultOptionRequired = ReportSponsoredResultOptionRequired(
    title = this.title,
    options = this.options.map { it.toDomain() }
)

fun TdApi.ReportSponsoredResultPremiumRequired.toDomain(): ReportSponsoredResultPremiumRequired = ReportSponsoredResultPremiumRequired

fun TdApi.ReportStory.toDomain(): ReportStory = ReportStory(
    storyPosterChatId = this.storyPosterChatId,
    storyId = this.storyId,
    optionId = this.optionId,
    text = this.text
)

fun TdApi.ReportStoryResult.toDomain(): ReportStoryResult = when(this) {
    is TdApi.ReportStoryResultOk -> this.toDomain()
    is TdApi.ReportStoryResultOptionRequired -> this.toDomain()
    is TdApi.ReportStoryResultTextRequired -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ReportStoryResultOk.toDomain(): ReportStoryResultOk = ReportStoryResultOk

fun TdApi.ReportStoryResultOptionRequired.toDomain(): ReportStoryResultOptionRequired = ReportStoryResultOptionRequired(
    title = this.title,
    options = this.options.map { it.toDomain() }
)

fun TdApi.ReportStoryResultTextRequired.toDomain(): ReportStoryResultTextRequired = ReportStoryResultTextRequired(
    optionId = this.optionId,
    isOptional = this.isOptional
)

fun TdApi.ReportSupergroupAntiSpamFalsePositive.toDomain(): ReportSupergroupAntiSpamFalsePositive = ReportSupergroupAntiSpamFalsePositive(
    supergroupId = this.supergroupId,
    messageId = this.messageId
)

fun TdApi.ReportSupergroupSpam.toDomain(): ReportSupergroupSpam = ReportSupergroupSpam(
    supergroupId = this.supergroupId,
    messageIds = this.messageIds
)

fun TdApi.ReportVideoMessageAdvertisement.toDomain(): ReportVideoMessageAdvertisement = ReportVideoMessageAdvertisement(
    advertisementUniqueId = this.advertisementUniqueId,
    optionId = this.optionId
)

