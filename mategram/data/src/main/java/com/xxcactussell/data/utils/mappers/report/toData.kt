package com.xxcactussell.data.utils.mappers.report

import com.xxcactussell.data.utils.mappers.message.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ReportAuthenticationCodeMissing.toData(): TdApi.ReportAuthenticationCodeMissing = TdApi.ReportAuthenticationCodeMissing(
    this.mobileNetworkCode
)

fun ReportChat.toData(): TdApi.ReportChat = TdApi.ReportChat(
    this.chatId,
    this.optionId,
    this.messageIds,
    this.text
)

fun ReportChatPhoto.toData(): TdApi.ReportChatPhoto = TdApi.ReportChatPhoto(
    this.chatId,
    this.fileId,
    this.reason.toData(),
    this.text
)

fun ReportChatResult.toData(): TdApi.ReportChatResult = when(this) {
    is ReportChatResultOk -> this.toData()
    is ReportChatResultOptionRequired -> this.toData()
    is ReportChatResultTextRequired -> this.toData()
    is ReportChatResultMessagesRequired -> this.toData()
}

fun ReportChatResultMessagesRequired.toData(): TdApi.ReportChatResultMessagesRequired = TdApi.ReportChatResultMessagesRequired(
)

fun ReportChatResultOk.toData(): TdApi.ReportChatResultOk = TdApi.ReportChatResultOk(
)

fun ReportChatResultOptionRequired.toData(): TdApi.ReportChatResultOptionRequired = TdApi.ReportChatResultOptionRequired(
    this.title,
    this.options.map { it.toData() }.toTypedArray()
)

fun ReportChatResultTextRequired.toData(): TdApi.ReportChatResultTextRequired = TdApi.ReportChatResultTextRequired(
    this.optionId,
    this.isOptional
)

fun ReportChatSponsoredMessage.toData(): TdApi.ReportChatSponsoredMessage = TdApi.ReportChatSponsoredMessage(
    this.chatId,
    this.messageId,
    this.optionId
)

fun ReportMessageReactions.toData(): TdApi.ReportMessageReactions = TdApi.ReportMessageReactions(
    this.chatId,
    this.messageId,
    this.senderId.toData()
)

fun ReportOption.toData(): TdApi.ReportOption = TdApi.ReportOption(
    this.id,
    this.text
)

fun ReportPhoneNumberCodeMissing.toData(): TdApi.ReportPhoneNumberCodeMissing = TdApi.ReportPhoneNumberCodeMissing(
    this.mobileNetworkCode
)

fun ReportReason.toData(): TdApi.ReportReason = when(this) {
    is ReportReasonSpam -> this.toData()
    is ReportReasonViolence -> this.toData()
    is ReportReasonPornography -> this.toData()
    is ReportReasonChildAbuse -> this.toData()
    is ReportReasonCopyright -> this.toData()
    is ReportReasonUnrelatedLocation -> this.toData()
    is ReportReasonFake -> this.toData()
    is ReportReasonIllegalDrugs -> this.toData()
    is ReportReasonPersonalDetails -> this.toData()
    is ReportReasonCustom -> this.toData()
}

fun ReportReasonChildAbuse.toData(): TdApi.ReportReasonChildAbuse = TdApi.ReportReasonChildAbuse(
)

fun ReportReasonCopyright.toData(): TdApi.ReportReasonCopyright = TdApi.ReportReasonCopyright(
)

fun ReportReasonCustom.toData(): TdApi.ReportReasonCustom = TdApi.ReportReasonCustom(
)

fun ReportReasonFake.toData(): TdApi.ReportReasonFake = TdApi.ReportReasonFake(
)

fun ReportReasonIllegalDrugs.toData(): TdApi.ReportReasonIllegalDrugs = TdApi.ReportReasonIllegalDrugs(
)

fun ReportReasonPersonalDetails.toData(): TdApi.ReportReasonPersonalDetails = TdApi.ReportReasonPersonalDetails(
)

fun ReportReasonPornography.toData(): TdApi.ReportReasonPornography = TdApi.ReportReasonPornography(
)

fun ReportReasonSpam.toData(): TdApi.ReportReasonSpam = TdApi.ReportReasonSpam(
)

fun ReportReasonUnrelatedLocation.toData(): TdApi.ReportReasonUnrelatedLocation = TdApi.ReportReasonUnrelatedLocation(
)

fun ReportReasonViolence.toData(): TdApi.ReportReasonViolence = TdApi.ReportReasonViolence(
)

fun ReportSponsoredChat.toData(): TdApi.ReportSponsoredChat = TdApi.ReportSponsoredChat(
    this.sponsoredChatUniqueId,
    this.optionId
)

fun ReportSponsoredResult.toData(): TdApi.ReportSponsoredResult = when(this) {
    is ReportSponsoredResultOk -> this.toData()
    is ReportSponsoredResultFailed -> this.toData()
    is ReportSponsoredResultOptionRequired -> this.toData()
    is ReportSponsoredResultAdsHidden -> this.toData()
    is ReportSponsoredResultPremiumRequired -> this.toData()
}

fun ReportSponsoredResultAdsHidden.toData(): TdApi.ReportSponsoredResultAdsHidden = TdApi.ReportSponsoredResultAdsHidden(
)

fun ReportSponsoredResultFailed.toData(): TdApi.ReportSponsoredResultFailed = TdApi.ReportSponsoredResultFailed(
)

fun ReportSponsoredResultOk.toData(): TdApi.ReportSponsoredResultOk = TdApi.ReportSponsoredResultOk(
)

fun ReportSponsoredResultOptionRequired.toData(): TdApi.ReportSponsoredResultOptionRequired = TdApi.ReportSponsoredResultOptionRequired(
    this.title,
    this.options.map { it.toData() }.toTypedArray()
)

fun ReportSponsoredResultPremiumRequired.toData(): TdApi.ReportSponsoredResultPremiumRequired = TdApi.ReportSponsoredResultPremiumRequired(
)

fun ReportStory.toData(): TdApi.ReportStory = TdApi.ReportStory(
    this.storyPosterChatId,
    this.storyId,
    this.optionId,
    this.text
)

fun ReportStoryResult.toData(): TdApi.ReportStoryResult = when(this) {
    is ReportStoryResultOk -> this.toData()
    is ReportStoryResultOptionRequired -> this.toData()
    is ReportStoryResultTextRequired -> this.toData()
}

fun ReportStoryResultOk.toData(): TdApi.ReportStoryResultOk = TdApi.ReportStoryResultOk(
)

fun ReportStoryResultOptionRequired.toData(): TdApi.ReportStoryResultOptionRequired = TdApi.ReportStoryResultOptionRequired(
    this.title,
    this.options.map { it.toData() }.toTypedArray()
)

fun ReportStoryResultTextRequired.toData(): TdApi.ReportStoryResultTextRequired = TdApi.ReportStoryResultTextRequired(
    this.optionId,
    this.isOptional
)

fun ReportSupergroupAntiSpamFalsePositive.toData(): TdApi.ReportSupergroupAntiSpamFalsePositive = TdApi.ReportSupergroupAntiSpamFalsePositive(
    this.supergroupId,
    this.messageId
)

fun ReportSupergroupSpam.toData(): TdApi.ReportSupergroupSpam = TdApi.ReportSupergroupSpam(
    this.supergroupId,
    this.messageIds
)

fun ReportVideoMessageAdvertisement.toData(): TdApi.ReportVideoMessageAdvertisement = TdApi.ReportVideoMessageAdvertisement(
    this.advertisementUniqueId,
    this.optionId
)

