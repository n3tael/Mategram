package com.xxcactussell.data.utils.mappers.edit

import com.xxcactussell.data.utils.mappers.bots.toDomain
import com.xxcactussell.data.utils.mappers.chat.toDomain
import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.input.toDomain
import com.xxcactussell.data.utils.mappers.language.toDomain
import com.xxcactussell.data.utils.mappers.location.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.proxy.toDomain
import com.xxcactussell.data.utils.mappers.stories.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.EditBotMediaPreview.toDomain(): EditBotMediaPreview = EditBotMediaPreview(
    botUserId = this.botUserId,
    languageCode = this.languageCode,
    fileId = this.fileId,
    content = this.content.toDomain()
)

fun TdApi.EditBusinessChatLink.toDomain(): EditBusinessChatLink = EditBusinessChatLink(
    link = this.link,
    linkInfo = this.linkInfo.toDomain()
)

fun TdApi.EditBusinessMessageChecklist.toDomain(): EditBusinessMessageChecklist = EditBusinessMessageChecklist(
    businessConnectionId = this.businessConnectionId,
    chatId = this.chatId,
    messageId = this.messageId,
    replyMarkup = this.replyMarkup.toDomain(),
    checklist = this.checklist.toDomain()
)

fun TdApi.EditBusinessStory.toDomain(): EditBusinessStory = EditBusinessStory(
    storyPosterChatId = this.storyPosterChatId,
    storyId = this.storyId,
    content = this.content.toDomain(),
    areas = this.areas.toDomain(),
    caption = this.caption.toDomain(),
    privacySettings = this.privacySettings.toDomain()
)

fun TdApi.EditChatFolder.toDomain(): EditChatFolder = EditChatFolder(
    chatFolderId = this.chatFolderId,
    folder = this.folder.toDomain()
)

fun TdApi.EditChatInviteLink.toDomain(): EditChatInviteLink = EditChatInviteLink(
    chatId = this.chatId,
    inviteLink = this.inviteLink,
    name = this.name,
    expirationDate = this.expirationDate,
    memberLimit = this.memberLimit,
    createsJoinRequest = this.createsJoinRequest
)

fun TdApi.EditChatSubscriptionInviteLink.toDomain(): EditChatSubscriptionInviteLink = EditChatSubscriptionInviteLink(
    chatId = this.chatId,
    inviteLink = this.inviteLink,
    name = this.name
)

fun TdApi.EditCustomLanguagePackInfo.toDomain(): EditCustomLanguagePackInfo = EditCustomLanguagePackInfo(
    info = this.info.toDomain()
)

fun TdApi.EditInlineMessageCaption.toDomain(): EditInlineMessageCaption = EditInlineMessageCaption(
    inlineMessageId = this.inlineMessageId,
    replyMarkup = this.replyMarkup.toDomain(),
    caption = this.caption.toDomain(),
    showCaptionAboveMedia = this.showCaptionAboveMedia
)

fun TdApi.EditInlineMessageLiveLocation.toDomain(): EditInlineMessageLiveLocation = EditInlineMessageLiveLocation(
    inlineMessageId = this.inlineMessageId,
    replyMarkup = this.replyMarkup.toDomain(),
    location = this.location.toDomain(),
    livePeriod = this.livePeriod,
    heading = this.heading,
    proximityAlertRadius = this.proximityAlertRadius
)

fun TdApi.EditInlineMessageMedia.toDomain(): EditInlineMessageMedia = EditInlineMessageMedia(
    inlineMessageId = this.inlineMessageId,
    replyMarkup = this.replyMarkup.toDomain(),
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.EditInlineMessageReplyMarkup.toDomain(): EditInlineMessageReplyMarkup = EditInlineMessageReplyMarkup(
    inlineMessageId = this.inlineMessageId,
    replyMarkup = this.replyMarkup.toDomain()
)

fun TdApi.EditInlineMessageText.toDomain(): EditInlineMessageText = EditInlineMessageText(
    inlineMessageId = this.inlineMessageId,
    replyMarkup = this.replyMarkup.toDomain(),
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.EditMessageChecklist.toDomain(): EditMessageChecklist = EditMessageChecklist(
    chatId = this.chatId,
    messageId = this.messageId,
    replyMarkup = this.replyMarkup.toDomain(),
    checklist = this.checklist.toDomain()
)

fun TdApi.EditMessageSchedulingState.toDomain(): EditMessageSchedulingState = EditMessageSchedulingState(
    chatId = this.chatId,
    messageId = this.messageId,
    schedulingState = this.schedulingState.toDomain()
)

fun TdApi.EditProxy.toDomain(): EditProxy = EditProxy(
    proxyId = this.proxyId,
    server = this.server,
    port = this.port,
    enable = this.enable,
    type = this.type.toDomain()
)

fun TdApi.EditQuickReplyMessage.toDomain(): EditQuickReplyMessage = EditQuickReplyMessage(
    shortcutId = this.shortcutId,
    messageId = this.messageId,
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.EditStarSubscription.toDomain(): EditStarSubscription = EditStarSubscription(
    subscriptionId = this.subscriptionId,
    isCanceled = this.isCanceled
)

fun TdApi.EditUserStarSubscription.toDomain(): EditUserStarSubscription = EditUserStarSubscription(
    userId = this.userId,
    telegramPaymentChargeId = this.telegramPaymentChargeId,
    isCanceled = this.isCanceled
)

