package com.xxcactussell.data.utils.mappers.edit

import com.xxcactussell.data.utils.mappers.bots.toData
import com.xxcactussell.data.utils.mappers.chat.toData
import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.input.toData
import com.xxcactussell.data.utils.mappers.language.toData
import com.xxcactussell.data.utils.mappers.location.toData
import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.proxy.toData
import com.xxcactussell.data.utils.mappers.stories.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun EditBotMediaPreview.toData(): TdApi.EditBotMediaPreview = TdApi.EditBotMediaPreview(
    this.botUserId,
    this.languageCode,
    this.fileId,
    this.content.toData()
)

fun EditBusinessChatLink.toData(): TdApi.EditBusinessChatLink = TdApi.EditBusinessChatLink(
    this.link,
    this.linkInfo.toData()
)

fun EditBusinessMessageChecklist.toData(): TdApi.EditBusinessMessageChecklist = TdApi.EditBusinessMessageChecklist(
    this.businessConnectionId,
    this.chatId,
    this.messageId,
    this.replyMarkup.toData(),
    this.checklist.toData()
)

fun EditBusinessStory.toData(): TdApi.EditBusinessStory = TdApi.EditBusinessStory(
    this.storyPosterChatId,
    this.storyId,
    this.content.toData(),
    this.areas.toData(),
    this.caption.toData(),
    this.privacySettings.toData()
)

fun EditChatFolder.toData(): TdApi.EditChatFolder = TdApi.EditChatFolder(
    this.chatFolderId,
    this.folder.toData()
)

fun EditChatInviteLink.toData(): TdApi.EditChatInviteLink = TdApi.EditChatInviteLink(
    this.chatId,
    this.inviteLink,
    this.name,
    this.expirationDate,
    this.memberLimit,
    this.createsJoinRequest
)

fun EditChatSubscriptionInviteLink.toData(): TdApi.EditChatSubscriptionInviteLink = TdApi.EditChatSubscriptionInviteLink(
    this.chatId,
    this.inviteLink,
    this.name
)

fun EditCustomLanguagePackInfo.toData(): TdApi.EditCustomLanguagePackInfo = TdApi.EditCustomLanguagePackInfo(
    this.info.toData()
)

fun EditInlineMessageCaption.toData(): TdApi.EditInlineMessageCaption = TdApi.EditInlineMessageCaption(
    this.inlineMessageId,
    this.replyMarkup.toData(),
    this.caption.toData(),
    this.showCaptionAboveMedia
)

fun EditInlineMessageLiveLocation.toData(): TdApi.EditInlineMessageLiveLocation = TdApi.EditInlineMessageLiveLocation(
    this.inlineMessageId,
    this.replyMarkup.toData(),
    this.location.toData(),
    this.livePeriod,
    this.heading,
    this.proximityAlertRadius
)

fun EditInlineMessageMedia.toData(): TdApi.EditInlineMessageMedia = TdApi.EditInlineMessageMedia(
    this.inlineMessageId,
    this.replyMarkup.toData(),
    this.inputMessageContent.toData()
)

fun EditInlineMessageReplyMarkup.toData(): TdApi.EditInlineMessageReplyMarkup = TdApi.EditInlineMessageReplyMarkup(
    this.inlineMessageId,
    this.replyMarkup.toData()
)

fun EditInlineMessageText.toData(): TdApi.EditInlineMessageText = TdApi.EditInlineMessageText(
    this.inlineMessageId,
    this.replyMarkup.toData(),
    this.inputMessageContent.toData()
)

fun EditMessageChecklist.toData(): TdApi.EditMessageChecklist = TdApi.EditMessageChecklist(
    this.chatId,
    this.messageId,
    this.replyMarkup.toData(),
    this.checklist.toData()
)

fun EditMessageSchedulingState.toData(): TdApi.EditMessageSchedulingState = TdApi.EditMessageSchedulingState(
    this.chatId,
    this.messageId,
    this.schedulingState.toData()
)

fun EditProxy.toData(): TdApi.EditProxy = TdApi.EditProxy(
    this.proxyId,
    this.server,
    this.port,
    this.enable,
    this.type.toData()
)

fun EditQuickReplyMessage.toData(): TdApi.EditQuickReplyMessage = TdApi.EditQuickReplyMessage(
    this.shortcutId,
    this.messageId,
    this.inputMessageContent.toData()
)

fun EditStarSubscription.toData(): TdApi.EditStarSubscription = TdApi.EditStarSubscription(
    this.subscriptionId,
    this.isCanceled
)

fun EditUserStarSubscription.toData(): TdApi.EditUserStarSubscription = TdApi.EditUserStarSubscription(
    this.userId,
    this.telegramPaymentChargeId,
    this.isCanceled
)

