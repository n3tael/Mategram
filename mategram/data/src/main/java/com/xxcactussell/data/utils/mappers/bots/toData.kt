package com.xxcactussell.data.utils.mappers.bots

import com.xxcactussell.data.utils.mappers.animation.toData
import com.xxcactussell.data.utils.mappers.business.toData
import com.xxcactussell.data.utils.mappers.chat.toData
import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.input.toData
import com.xxcactussell.data.utils.mappers.location.toData
import com.xxcactussell.data.utils.mappers.photo.toData
import com.xxcactussell.data.utils.mappers.target.toData
import com.xxcactussell.data.utils.mappers.theme.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun BotCommand.toData(): TdApi.BotCommand = TdApi.BotCommand(
    this.command,
    this.description
)

fun BotCommandScope.toData(): TdApi.BotCommandScope = when(this) {
    is BotCommandScopeDefault -> this.toData()
    is BotCommandScopeAllPrivateChats -> this.toData()
    is BotCommandScopeAllGroupChats -> this.toData()
    is BotCommandScopeAllChatAdministrators -> this.toData()
    is BotCommandScopeChat -> this.toData()
    is BotCommandScopeChatAdministrators -> this.toData()
    is BotCommandScopeChatMember -> this.toData()
}

fun BotCommandScopeAllChatAdministrators.toData(): TdApi.BotCommandScopeAllChatAdministrators = TdApi.BotCommandScopeAllChatAdministrators(
)

fun BotCommandScopeAllGroupChats.toData(): TdApi.BotCommandScopeAllGroupChats = TdApi.BotCommandScopeAllGroupChats(
)

fun BotCommandScopeAllPrivateChats.toData(): TdApi.BotCommandScopeAllPrivateChats = TdApi.BotCommandScopeAllPrivateChats(
)

fun BotCommandScopeChat.toData(): TdApi.BotCommandScopeChat = TdApi.BotCommandScopeChat(
    this.chatId
)

fun BotCommandScopeChatAdministrators.toData(): TdApi.BotCommandScopeChatAdministrators = TdApi.BotCommandScopeChatAdministrators(
    this.chatId
)

fun BotCommandScopeChatMember.toData(): TdApi.BotCommandScopeChatMember = TdApi.BotCommandScopeChatMember(
    this.chatId,
    this.userId
)

fun BotCommandScopeDefault.toData(): TdApi.BotCommandScopeDefault = TdApi.BotCommandScopeDefault(
)

fun BotCommands.toData(): TdApi.BotCommands = TdApi.BotCommands(
    this.botUserId,
    this.commands.map { it.toData() }.toTypedArray()
)

fun BotMenuButton.toData(): TdApi.BotMenuButton = TdApi.BotMenuButton(
    this.text,
    this.url
)

fun BusinessConnection.toData(): TdApi.BusinessConnection = TdApi.BusinessConnection(
    this.id,
    this.userId,
    this.userChatId,
    this.date,
    this.rights?.toData(),
    this.isEnabled
)

fun BusinessInfo.toData(): TdApi.BusinessInfo = TdApi.BusinessInfo(
    this.location?.toData(),
    this.openingHours?.toData(),
    this.localOpeningHours?.toData(),
    this.nextOpenIn,
    this.nextCloseIn,
    this.greetingMessageSettings?.toData(),
    this.awayMessageSettings?.toData(),
    this.startPage?.toData()
)

fun CloseWebApp.toData(): TdApi.CloseWebApp = TdApi.CloseWebApp(
    this.webAppLaunchId
)

fun DeleteBusinessMessages.toData(): TdApi.DeleteBusinessMessages = TdApi.DeleteBusinessMessages(
    this.businessConnectionId,
    this.messageIds
)

fun EditBusinessMessageCaption.toData(): TdApi.EditBusinessMessageCaption = TdApi.EditBusinessMessageCaption(
    this.businessConnectionId,
    this.chatId,
    this.messageId,
    this.replyMarkup.toData(),
    this.caption.toData(),
    this.showCaptionAboveMedia
)

fun EditBusinessMessageLiveLocation.toData(): TdApi.EditBusinessMessageLiveLocation = TdApi.EditBusinessMessageLiveLocation(
    this.businessConnectionId,
    this.chatId,
    this.messageId,
    this.replyMarkup.toData(),
    this.location.toData(),
    this.livePeriod,
    this.heading,
    this.proximityAlertRadius
)

fun EditBusinessMessageMedia.toData(): TdApi.EditBusinessMessageMedia = TdApi.EditBusinessMessageMedia(
    this.businessConnectionId,
    this.chatId,
    this.messageId,
    this.replyMarkup.toData(),
    this.inputMessageContent.toData()
)

fun EditBusinessMessageReplyMarkup.toData(): TdApi.EditBusinessMessageReplyMarkup = TdApi.EditBusinessMessageReplyMarkup(
    this.businessConnectionId,
    this.chatId,
    this.messageId,
    this.replyMarkup.toData()
)

fun EditBusinessMessageText.toData(): TdApi.EditBusinessMessageText = TdApi.EditBusinessMessageText(
    this.businessConnectionId,
    this.chatId,
    this.messageId,
    this.replyMarkup.toData(),
    this.inputMessageContent.toData()
)

fun GetBotName.toData(): TdApi.GetBotName = TdApi.GetBotName(
    this.botUserId,
    this.languageCode
)

fun GetBusinessConnection.toData(): TdApi.GetBusinessConnection = TdApi.GetBusinessConnection(
    this.connectionId
)

fun GetWebAppUrl.toData(): TdApi.GetWebAppUrl = TdApi.GetWebAppUrl(
    this.botUserId,
    this.url,
    this.parameters.toData()
)

fun InlineKeyboardButton.toData(): TdApi.InlineKeyboardButton = TdApi.InlineKeyboardButton(
    this.text,
    this.type.toData()
)

fun InlineKeyboardButtonType.toData(): TdApi.InlineKeyboardButtonType = when(this) {
    is InlineKeyboardButtonTypeUrl -> this.toData()
    is InlineKeyboardButtonTypeLoginUrl -> this.toData()
    is InlineKeyboardButtonTypeWebApp -> this.toData()
    is InlineKeyboardButtonTypeCallback -> this.toData()
    is InlineKeyboardButtonTypeCallbackWithPassword -> this.toData()
    is InlineKeyboardButtonTypeCallbackGame -> this.toData()
    is InlineKeyboardButtonTypeSwitchInline -> this.toData()
    is InlineKeyboardButtonTypeBuy -> this.toData()
    is InlineKeyboardButtonTypeUser -> this.toData()
    is InlineKeyboardButtonTypeCopyText -> this.toData()
}

fun InlineKeyboardButtonTypeBuy.toData(): TdApi.InlineKeyboardButtonTypeBuy = TdApi.InlineKeyboardButtonTypeBuy(
)

fun InlineKeyboardButtonTypeCallback.toData(): TdApi.InlineKeyboardButtonTypeCallback = TdApi.InlineKeyboardButtonTypeCallback(
    this.data
)

fun InlineKeyboardButtonTypeCallbackGame.toData(): TdApi.InlineKeyboardButtonTypeCallbackGame = TdApi.InlineKeyboardButtonTypeCallbackGame(
)

fun InlineKeyboardButtonTypeCallbackWithPassword.toData(): TdApi.InlineKeyboardButtonTypeCallbackWithPassword = TdApi.InlineKeyboardButtonTypeCallbackWithPassword(
    this.data
)

fun InlineKeyboardButtonTypeCopyText.toData(): TdApi.InlineKeyboardButtonTypeCopyText = TdApi.InlineKeyboardButtonTypeCopyText(
    this.text
)

fun InlineKeyboardButtonTypeLoginUrl.toData(): TdApi.InlineKeyboardButtonTypeLoginUrl = TdApi.InlineKeyboardButtonTypeLoginUrl(
    this.url,
    this.id,
    this.forwardText
)

fun InlineKeyboardButtonTypeSwitchInline.toData(): TdApi.InlineKeyboardButtonTypeSwitchInline = TdApi.InlineKeyboardButtonTypeSwitchInline(
    this.query,
    this.targetChat.toData()
)

fun InlineKeyboardButtonTypeUrl.toData(): TdApi.InlineKeyboardButtonTypeUrl = TdApi.InlineKeyboardButtonTypeUrl(
    this.url
)

fun InlineKeyboardButtonTypeUser.toData(): TdApi.InlineKeyboardButtonTypeUser = TdApi.InlineKeyboardButtonTypeUser(
    this.userId
)

fun InlineKeyboardButtonTypeWebApp.toData(): TdApi.InlineKeyboardButtonTypeWebApp = TdApi.InlineKeyboardButtonTypeWebApp(
    this.url
)

fun KeyboardButton.toData(): TdApi.KeyboardButton = TdApi.KeyboardButton(
    this.text,
    this.type.toData()
)

fun KeyboardButtonType.toData(): TdApi.KeyboardButtonType = when(this) {
    is KeyboardButtonTypeText -> this.toData()
    is KeyboardButtonTypeRequestPhoneNumber -> this.toData()
    is KeyboardButtonTypeRequestLocation -> this.toData()
    is KeyboardButtonTypeRequestPoll -> this.toData()
    is KeyboardButtonTypeRequestUsers -> this.toData()
    is KeyboardButtonTypeRequestChat -> this.toData()
    is KeyboardButtonTypeWebApp -> this.toData()
}

fun KeyboardButtonTypeRequestChat.toData(): TdApi.KeyboardButtonTypeRequestChat = TdApi.KeyboardButtonTypeRequestChat(
    this.id,
    this.chatIsChannel,
    this.restrictChatIsForum,
    this.chatIsForum,
    this.restrictChatHasUsername,
    this.chatHasUsername,
    this.chatIsCreated,
    this.userAdministratorRights?.toData(),
    this.botAdministratorRights?.toData(),
    this.botIsMember,
    this.requestTitle,
    this.requestUsername,
    this.requestPhoto
)

fun KeyboardButtonTypeRequestLocation.toData(): TdApi.KeyboardButtonTypeRequestLocation = TdApi.KeyboardButtonTypeRequestLocation(
)

fun KeyboardButtonTypeRequestPhoneNumber.toData(): TdApi.KeyboardButtonTypeRequestPhoneNumber = TdApi.KeyboardButtonTypeRequestPhoneNumber(
)

fun KeyboardButtonTypeRequestPoll.toData(): TdApi.KeyboardButtonTypeRequestPoll = TdApi.KeyboardButtonTypeRequestPoll(
    this.forceRegular,
    this.forceQuiz
)

fun KeyboardButtonTypeRequestUsers.toData(): TdApi.KeyboardButtonTypeRequestUsers = TdApi.KeyboardButtonTypeRequestUsers(
    this.id,
    this.restrictUserIsBot,
    this.userIsBot,
    this.restrictUserIsPremium,
    this.userIsPremium,
    this.maxQuantity,
    this.requestName,
    this.requestUsername,
    this.requestPhoto
)

fun KeyboardButtonTypeText.toData(): TdApi.KeyboardButtonTypeText = TdApi.KeyboardButtonTypeText(
)

fun KeyboardButtonTypeWebApp.toData(): TdApi.KeyboardButtonTypeWebApp = TdApi.KeyboardButtonTypeWebApp(
    this.url
)

fun LoginUrlInfo.toData(): TdApi.LoginUrlInfo = when(this) {
    is LoginUrlInfoOpen -> this.toData()
    is LoginUrlInfoRequestConfirmation -> this.toData()
}

fun LoginUrlInfoOpen.toData(): TdApi.LoginUrlInfoOpen = TdApi.LoginUrlInfoOpen(
    this.url,
    this.skipConfirmation
)

fun LoginUrlInfoRequestConfirmation.toData(): TdApi.LoginUrlInfoRequestConfirmation = TdApi.LoginUrlInfoRequestConfirmation(
    this.url,
    this.domain,
    this.botUserId,
    this.requestWriteAccess
)

fun OpenWebApp.toData(): TdApi.OpenWebApp = TdApi.OpenWebApp(
    this.chatId,
    this.botUserId,
    this.url,
    this.messageThreadId,
    this.directMessagesChatTopicId,
    this.replyTo.toData(),
    this.parameters.toData()
)

fun ReplyMarkup.toData(): TdApi.ReplyMarkup = when(this) {
    is ReplyMarkupRemoveKeyboard -> this.toData()
    is ReplyMarkupForceReply -> this.toData()
    is ReplyMarkupShowKeyboard -> this.toData()
    is ReplyMarkupInlineKeyboard -> this.toData()
}

fun ReplyMarkupForceReply.toData(): TdApi.ReplyMarkupForceReply = TdApi.ReplyMarkupForceReply(
    this.isPersonal,
    this.inputFieldPlaceholder
)

fun ReplyMarkupInlineKeyboard.toData(): TdApi.ReplyMarkupInlineKeyboard = TdApi.ReplyMarkupInlineKeyboard(
    this.rows.map { row -> row.map { it.toData() }.toTypedArray() }.toTypedArray(),
)

fun ReplyMarkupRemoveKeyboard.toData(): TdApi.ReplyMarkupRemoveKeyboard = TdApi.ReplyMarkupRemoveKeyboard(
    this.isPersonal
)

fun ReplyMarkupShowKeyboard.toData(): TdApi.ReplyMarkupShowKeyboard = TdApi.ReplyMarkupShowKeyboard(
    this.rows.map { row -> row.map { it.toData() }.toTypedArray() }.toTypedArray(),
    this.isPersistent,
    this.resizeKeyboard,
    this.oneTime,
    this.isPersonal,
    this.inputFieldPlaceholder
)

fun SendBusinessMessage.toData(): TdApi.SendBusinessMessage = TdApi.SendBusinessMessage(
    this.businessConnectionId,
    this.chatId,
    this.replyTo.toData(),
    this.disableNotification,
    this.protectContent,
    this.effectId,
    this.replyMarkup.toData(),
    this.inputMessageContent.toData()
)

fun SendBusinessMessageAlbum.toData(): TdApi.SendBusinessMessageAlbum = TdApi.SendBusinessMessageAlbum(
    this.businessConnectionId,
    this.chatId,
    this.replyTo.toData(),
    this.disableNotification,
    this.protectContent,
    this.effectId,
    this.inputMessageContents.map { it.toData() }.toTypedArray()
)

fun SendWebAppData.toData(): TdApi.SendWebAppData = TdApi.SendWebAppData(
    this.botUserId,
    this.buttonText,
    this.data
)

fun SetBotName.toData(): TdApi.SetBotName = TdApi.SetBotName(
    this.botUserId,
    this.languageCode,
    this.name
)

fun ToggleGeneralForumTopicIsHidden.toData(): TdApi.ToggleGeneralForumTopicIsHidden = TdApi.ToggleGeneralForumTopicIsHidden(
    this.chatId,
    this.isHidden
)

fun WebApp.toData(): TdApi.WebApp = TdApi.WebApp(
    this.shortName,
    this.title,
    this.description,
    this.photo.toData(),
    this.animation?.toData()
)

fun WebAppInfo.toData(): TdApi.WebAppInfo = TdApi.WebAppInfo(
    this.launchId,
    this.url
)

fun WebAppOpenMode.toData(): TdApi.WebAppOpenMode = when(this) {
    is WebAppOpenModeCompact -> this.toData()
    is WebAppOpenModeFullSize -> this.toData()
    is WebAppOpenModeFullScreen -> this.toData()
}

fun WebAppOpenModeCompact.toData(): TdApi.WebAppOpenModeCompact = TdApi.WebAppOpenModeCompact(
)

fun WebAppOpenModeFullScreen.toData(): TdApi.WebAppOpenModeFullScreen = TdApi.WebAppOpenModeFullScreen(
)

fun WebAppOpenModeFullSize.toData(): TdApi.WebAppOpenModeFullSize = TdApi.WebAppOpenModeFullSize(
)

fun WebAppOpenParameters.toData(): TdApi.WebAppOpenParameters = TdApi.WebAppOpenParameters(
    this.theme.toData(),
    this.applicationName,
    this.mode.toData()
)

