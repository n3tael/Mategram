package com.xxcactussell.data.utils.mappers.bots

import com.xxcactussell.data.utils.mappers.animation.toDomain
import com.xxcactussell.data.utils.mappers.business.toDomain
import com.xxcactussell.data.utils.mappers.chat.toDomain
import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.input.toDomain
import com.xxcactussell.data.utils.mappers.location.toDomain
import com.xxcactussell.data.utils.mappers.photo.toDomain
import com.xxcactussell.data.utils.mappers.target.toDomain
import com.xxcactussell.data.utils.mappers.theme.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.BotCommand.toDomain(): BotCommand = BotCommand(
    command = this.command,
    description = this.description
)

fun TdApi.BotCommandScope.toDomain(): BotCommandScope = when(this) {
    is TdApi.BotCommandScopeDefault -> this.toDomain()
    is TdApi.BotCommandScopeAllPrivateChats -> this.toDomain()
    is TdApi.BotCommandScopeAllGroupChats -> this.toDomain()
    is TdApi.BotCommandScopeAllChatAdministrators -> this.toDomain()
    is TdApi.BotCommandScopeChat -> this.toDomain()
    is TdApi.BotCommandScopeChatAdministrators -> this.toDomain()
    is TdApi.BotCommandScopeChatMember -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.BotCommandScopeAllChatAdministrators.toDomain(): BotCommandScopeAllChatAdministrators = BotCommandScopeAllChatAdministrators

fun TdApi.BotCommandScopeAllGroupChats.toDomain(): BotCommandScopeAllGroupChats = BotCommandScopeAllGroupChats

fun TdApi.BotCommandScopeAllPrivateChats.toDomain(): BotCommandScopeAllPrivateChats = BotCommandScopeAllPrivateChats

fun TdApi.BotCommandScopeChat.toDomain(): BotCommandScopeChat = BotCommandScopeChat(
    chatId = this.chatId
)

fun TdApi.BotCommandScopeChatAdministrators.toDomain(): BotCommandScopeChatAdministrators = BotCommandScopeChatAdministrators(
    chatId = this.chatId
)

fun TdApi.BotCommandScopeChatMember.toDomain(): BotCommandScopeChatMember = BotCommandScopeChatMember(
    chatId = this.chatId,
    userId = this.userId
)

fun TdApi.BotCommandScopeDefault.toDomain(): BotCommandScopeDefault = BotCommandScopeDefault

fun TdApi.BotCommands.toDomain(): BotCommands = BotCommands(
    botUserId = this.botUserId,
    commands = this.commands.map { it.toDomain() }
)

fun TdApi.BotMenuButton.toDomain(): BotMenuButton = BotMenuButton(
    text = this.text,
    url = this.url
)

fun TdApi.BusinessConnection.toDomain(): BusinessConnection = BusinessConnection(
    id = this.id,
    userId = this.userId,
    userChatId = this.userChatId,
    date = this.date,
    rights = this.rights?.toDomain(),
    isEnabled = this.isEnabled
)

fun TdApi.BusinessInfo.toDomain(): BusinessInfo = BusinessInfo(
    location = this.location?.toDomain(),
    openingHours = this.openingHours?.toDomain(),
    localOpeningHours = this.localOpeningHours?.toDomain(),
    nextOpenIn = this.nextOpenIn,
    nextCloseIn = this.nextCloseIn,
    greetingMessageSettings = this.greetingMessageSettings?.toDomain(),
    awayMessageSettings = this.awayMessageSettings?.toDomain(),
    startPage = this.startPage?.toDomain()
)

fun TdApi.CloseWebApp.toDomain(): CloseWebApp = CloseWebApp(
    webAppLaunchId = this.webAppLaunchId
)

fun TdApi.DeleteBusinessMessages.toDomain(): DeleteBusinessMessages = DeleteBusinessMessages(
    businessConnectionId = this.businessConnectionId,
    messageIds = this.messageIds
)

fun TdApi.EditBusinessMessageCaption.toDomain(): EditBusinessMessageCaption = EditBusinessMessageCaption(
    businessConnectionId = this.businessConnectionId,
    chatId = this.chatId,
    messageId = this.messageId,
    replyMarkup = this.replyMarkup.toDomain(),
    caption = this.caption.toDomain(),
    showCaptionAboveMedia = this.showCaptionAboveMedia
)

fun TdApi.EditBusinessMessageLiveLocation.toDomain(): EditBusinessMessageLiveLocation = EditBusinessMessageLiveLocation(
    businessConnectionId = this.businessConnectionId,
    chatId = this.chatId,
    messageId = this.messageId,
    replyMarkup = this.replyMarkup.toDomain(),
    location = this.location.toDomain(),
    livePeriod = this.livePeriod,
    heading = this.heading,
    proximityAlertRadius = this.proximityAlertRadius
)

fun TdApi.EditBusinessMessageMedia.toDomain(): EditBusinessMessageMedia = EditBusinessMessageMedia(
    businessConnectionId = this.businessConnectionId,
    chatId = this.chatId,
    messageId = this.messageId,
    replyMarkup = this.replyMarkup.toDomain(),
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.EditBusinessMessageReplyMarkup.toDomain(): EditBusinessMessageReplyMarkup = EditBusinessMessageReplyMarkup(
    businessConnectionId = this.businessConnectionId,
    chatId = this.chatId,
    messageId = this.messageId,
    replyMarkup = this.replyMarkup.toDomain()
)

fun TdApi.EditBusinessMessageText.toDomain(): EditBusinessMessageText = EditBusinessMessageText(
    businessConnectionId = this.businessConnectionId,
    chatId = this.chatId,
    messageId = this.messageId,
    replyMarkup = this.replyMarkup.toDomain(),
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.GetBotName.toDomain(): GetBotName = GetBotName(
    botUserId = this.botUserId,
    languageCode = this.languageCode
)

fun TdApi.GetBusinessConnection.toDomain(): GetBusinessConnection = GetBusinessConnection(
    connectionId = this.connectionId
)

fun TdApi.GetWebAppUrl.toDomain(): GetWebAppUrl = GetWebAppUrl(
    botUserId = this.botUserId,
    url = this.url,
    parameters = this.parameters.toDomain()
)

fun TdApi.InlineKeyboardButton.toDomain(): InlineKeyboardButton = InlineKeyboardButton(
    text = this.text,
    type = this.type.toDomain()
)

fun TdApi.InlineKeyboardButtonType.toDomain(): InlineKeyboardButtonType = when(this) {
    is TdApi.InlineKeyboardButtonTypeUrl -> this.toDomain()
    is TdApi.InlineKeyboardButtonTypeLoginUrl -> this.toDomain()
    is TdApi.InlineKeyboardButtonTypeWebApp -> this.toDomain()
    is TdApi.InlineKeyboardButtonTypeCallback -> this.toDomain()
    is TdApi.InlineKeyboardButtonTypeCallbackWithPassword -> this.toDomain()
    is TdApi.InlineKeyboardButtonTypeCallbackGame -> this.toDomain()
    is TdApi.InlineKeyboardButtonTypeSwitchInline -> this.toDomain()
    is TdApi.InlineKeyboardButtonTypeBuy -> this.toDomain()
    is TdApi.InlineKeyboardButtonTypeUser -> this.toDomain()
    is TdApi.InlineKeyboardButtonTypeCopyText -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InlineKeyboardButtonTypeBuy.toDomain(): InlineKeyboardButtonTypeBuy = InlineKeyboardButtonTypeBuy

fun TdApi.InlineKeyboardButtonTypeCallback.toDomain(): InlineKeyboardButtonTypeCallback = InlineKeyboardButtonTypeCallback(
    data = this.data
)

fun TdApi.InlineKeyboardButtonTypeCallbackGame.toDomain(): InlineKeyboardButtonTypeCallbackGame = InlineKeyboardButtonTypeCallbackGame

fun TdApi.InlineKeyboardButtonTypeCallbackWithPassword.toDomain(): InlineKeyboardButtonTypeCallbackWithPassword = InlineKeyboardButtonTypeCallbackWithPassword(
    data = this.data
)

fun TdApi.InlineKeyboardButtonTypeCopyText.toDomain(): InlineKeyboardButtonTypeCopyText = InlineKeyboardButtonTypeCopyText(
    text = this.text
)

fun TdApi.InlineKeyboardButtonTypeLoginUrl.toDomain(): InlineKeyboardButtonTypeLoginUrl = InlineKeyboardButtonTypeLoginUrl(
    url = this.url,
    id = this.id,
    forwardText = this.forwardText
)

fun TdApi.InlineKeyboardButtonTypeSwitchInline.toDomain(): InlineKeyboardButtonTypeSwitchInline = InlineKeyboardButtonTypeSwitchInline(
    query = this.query,
    targetChat = this.targetChat.toDomain()
)

fun TdApi.InlineKeyboardButtonTypeUrl.toDomain(): InlineKeyboardButtonTypeUrl = InlineKeyboardButtonTypeUrl(
    url = this.url
)

fun TdApi.InlineKeyboardButtonTypeUser.toDomain(): InlineKeyboardButtonTypeUser = InlineKeyboardButtonTypeUser(
    userId = this.userId
)

fun TdApi.InlineKeyboardButtonTypeWebApp.toDomain(): InlineKeyboardButtonTypeWebApp = InlineKeyboardButtonTypeWebApp(
    url = this.url
)

fun TdApi.KeyboardButton.toDomain(): KeyboardButton = KeyboardButton(
    text = this.text,
    type = this.type.toDomain()
)

fun TdApi.KeyboardButtonType.toDomain(): KeyboardButtonType = when(this) {
    is TdApi.KeyboardButtonTypeText -> this.toDomain()
    is TdApi.KeyboardButtonTypeRequestPhoneNumber -> this.toDomain()
    is TdApi.KeyboardButtonTypeRequestLocation -> this.toDomain()
    is TdApi.KeyboardButtonTypeRequestPoll -> this.toDomain()
    is TdApi.KeyboardButtonTypeRequestUsers -> this.toDomain()
    is TdApi.KeyboardButtonTypeRequestChat -> this.toDomain()
    is TdApi.KeyboardButtonTypeWebApp -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.KeyboardButtonTypeRequestChat.toDomain(): KeyboardButtonTypeRequestChat = KeyboardButtonTypeRequestChat(
    id = this.id,
    chatIsChannel = this.chatIsChannel,
    restrictChatIsForum = this.restrictChatIsForum,
    chatIsForum = this.chatIsForum,
    restrictChatHasUsername = this.restrictChatHasUsername,
    chatHasUsername = this.chatHasUsername,
    chatIsCreated = this.chatIsCreated,
    userAdministratorRights = this.userAdministratorRights?.toDomain(),
    botAdministratorRights = this.botAdministratorRights?.toDomain(),
    botIsMember = this.botIsMember,
    requestTitle = this.requestTitle,
    requestUsername = this.requestUsername,
    requestPhoto = this.requestPhoto
)

fun TdApi.KeyboardButtonTypeRequestLocation.toDomain(): KeyboardButtonTypeRequestLocation = KeyboardButtonTypeRequestLocation

fun TdApi.KeyboardButtonTypeRequestPhoneNumber.toDomain(): KeyboardButtonTypeRequestPhoneNumber = KeyboardButtonTypeRequestPhoneNumber

fun TdApi.KeyboardButtonTypeRequestPoll.toDomain(): KeyboardButtonTypeRequestPoll = KeyboardButtonTypeRequestPoll(
    forceRegular = this.forceRegular,
    forceQuiz = this.forceQuiz
)

fun TdApi.KeyboardButtonTypeRequestUsers.toDomain(): KeyboardButtonTypeRequestUsers = KeyboardButtonTypeRequestUsers(
    id = this.id,
    restrictUserIsBot = this.restrictUserIsBot,
    userIsBot = this.userIsBot,
    restrictUserIsPremium = this.restrictUserIsPremium,
    userIsPremium = this.userIsPremium,
    maxQuantity = this.maxQuantity,
    requestName = this.requestName,
    requestUsername = this.requestUsername,
    requestPhoto = this.requestPhoto
)

fun TdApi.KeyboardButtonTypeText.toDomain(): KeyboardButtonTypeText = KeyboardButtonTypeText

fun TdApi.KeyboardButtonTypeWebApp.toDomain(): KeyboardButtonTypeWebApp = KeyboardButtonTypeWebApp(
    url = this.url
)

fun TdApi.LoginUrlInfo.toDomain(): LoginUrlInfo = when(this) {
    is TdApi.LoginUrlInfoOpen -> this.toDomain()
    is TdApi.LoginUrlInfoRequestConfirmation -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.LoginUrlInfoOpen.toDomain(): LoginUrlInfoOpen = LoginUrlInfoOpen(
    url = this.url,
    skipConfirmation = this.skipConfirmation
)

fun TdApi.LoginUrlInfoRequestConfirmation.toDomain(): LoginUrlInfoRequestConfirmation = LoginUrlInfoRequestConfirmation(
    url = this.url,
    domain = this.domain,
    botUserId = this.botUserId,
    requestWriteAccess = this.requestWriteAccess
)

fun TdApi.OpenWebApp.toDomain(): OpenWebApp = OpenWebApp(
    chatId = this.chatId,
    botUserId = this.botUserId,
    url = this.url,
    messageThreadId = this.messageThreadId,
    directMessagesChatTopicId = this.directMessagesChatTopicId,
    replyTo = this.replyTo.toDomain(),
    parameters = this.parameters.toDomain()
)

fun TdApi.ReplyMarkup.toDomain(): ReplyMarkup = when(this) {
    is TdApi.ReplyMarkupRemoveKeyboard -> this.toDomain()
    is TdApi.ReplyMarkupForceReply -> this.toDomain()
    is TdApi.ReplyMarkupShowKeyboard -> this.toDomain()
    is TdApi.ReplyMarkupInlineKeyboard -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ReplyMarkupForceReply.toDomain(): ReplyMarkupForceReply = ReplyMarkupForceReply(
    isPersonal = this.isPersonal,
    inputFieldPlaceholder = this.inputFieldPlaceholder
)

fun TdApi.ReplyMarkupInlineKeyboard.toDomain(): ReplyMarkupInlineKeyboard = ReplyMarkupInlineKeyboard(
    rows = this.rows.map { row -> row.map { it.toDomain() } },
)

fun TdApi.ReplyMarkupRemoveKeyboard.toDomain(): ReplyMarkupRemoveKeyboard = ReplyMarkupRemoveKeyboard(
    isPersonal = this.isPersonal
)

fun TdApi.ReplyMarkupShowKeyboard.toDomain(): ReplyMarkupShowKeyboard = ReplyMarkupShowKeyboard(
    rows = this.rows.map { row -> row.map { it.toDomain() } },
    isPersistent = this.isPersistent,
    resizeKeyboard = this.resizeKeyboard,
    oneTime = this.oneTime,
    isPersonal = this.isPersonal,
    inputFieldPlaceholder = this.inputFieldPlaceholder
)

fun TdApi.SendBusinessMessage.toDomain(): SendBusinessMessage = SendBusinessMessage(
    businessConnectionId = this.businessConnectionId,
    chatId = this.chatId,
    replyTo = this.replyTo.toDomain(),
    disableNotification = this.disableNotification,
    protectContent = this.protectContent,
    effectId = this.effectId,
    replyMarkup = this.replyMarkup.toDomain(),
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.SendBusinessMessageAlbum.toDomain(): SendBusinessMessageAlbum = SendBusinessMessageAlbum(
    businessConnectionId = this.businessConnectionId,
    chatId = this.chatId,
    replyTo = this.replyTo.toDomain(),
    disableNotification = this.disableNotification,
    protectContent = this.protectContent,
    effectId = this.effectId,
    inputMessageContents = this.inputMessageContents.map { it.toDomain() }
)

fun TdApi.SendWebAppData.toDomain(): SendWebAppData = SendWebAppData(
    botUserId = this.botUserId,
    buttonText = this.buttonText,
    data = this.data
)

fun TdApi.SetBotName.toDomain(): SetBotName = SetBotName(
    botUserId = this.botUserId,
    languageCode = this.languageCode,
    name = this.name
)

fun TdApi.ToggleGeneralForumTopicIsHidden.toDomain(): ToggleGeneralForumTopicIsHidden = ToggleGeneralForumTopicIsHidden(
    chatId = this.chatId,
    isHidden = this.isHidden
)

fun TdApi.WebApp.toDomain(): WebApp = WebApp(
    shortName = this.shortName,
    title = this.title,
    description = this.description,
    photo = this.photo.toDomain(),
    animation = this.animation?.toDomain()
)

fun TdApi.WebAppInfo.toDomain(): WebAppInfo = WebAppInfo(
    launchId = this.launchId,
    url = this.url
)

fun TdApi.WebAppOpenMode.toDomain(): WebAppOpenMode = when(this) {
    is TdApi.WebAppOpenModeCompact -> this.toDomain()
    is TdApi.WebAppOpenModeFullSize -> this.toDomain()
    is TdApi.WebAppOpenModeFullScreen -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.WebAppOpenModeCompact.toDomain(): WebAppOpenModeCompact = WebAppOpenModeCompact

fun TdApi.WebAppOpenModeFullScreen.toDomain(): WebAppOpenModeFullScreen = WebAppOpenModeFullScreen

fun TdApi.WebAppOpenModeFullSize.toDomain(): WebAppOpenModeFullSize = WebAppOpenModeFullSize

fun TdApi.WebAppOpenParameters.toDomain(): WebAppOpenParameters = WebAppOpenParameters(
    theme = this.theme.toDomain(),
    applicationName = this.applicationName,
    mode = this.mode.toDomain()
)

