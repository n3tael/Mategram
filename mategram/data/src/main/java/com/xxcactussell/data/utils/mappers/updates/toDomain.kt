package com.xxcactussell.data.utils.mappers.updates

import com.xxcactussell.data.utils.mappers.accent.toDomain
import com.xxcactussell.data.utils.mappers.address.toDomain
import com.xxcactussell.data.utils.mappers.age.toDomain
import com.xxcactussell.data.utils.mappers.attachment.toDomain
import com.xxcactussell.data.utils.mappers.auth.toDomain
import com.xxcactussell.data.utils.mappers.autosave.toDomain
import com.xxcactussell.data.utils.mappers.background.toDomain
import com.xxcactussell.data.utils.mappers.basic.toDomain
import com.xxcactussell.data.utils.mappers.block.toDomain
import com.xxcactussell.data.utils.mappers.bots.toDomain
import com.xxcactussell.data.utils.mappers.business.toDomain
import com.xxcactussell.data.utils.mappers.calls.toDomain
import com.xxcactussell.data.utils.mappers.can.toDomain
import com.xxcactussell.data.utils.mappers.chat.toDomain
import com.xxcactussell.data.utils.mappers.connection.toDomain
import com.xxcactussell.data.utils.mappers.direct.toDomain
import com.xxcactussell.data.utils.mappers.downloaded.toDomain
import com.xxcactussell.data.utils.mappers.emoji.toDomain
import com.xxcactussell.data.utils.mappers.error.toDomain
import com.xxcactussell.data.utils.mappers.fact.toDomain
import com.xxcactussell.data.utils.mappers.file.toDomain
import com.xxcactussell.data.utils.mappers.forum.toDomain
import com.xxcactussell.data.utils.mappers.language.toDomain
import com.xxcactussell.data.utils.mappers.location.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.monetization.toDomain
import com.xxcactussell.data.utils.mappers.notification.toDomain
import com.xxcactussell.data.utils.mappers.option.toDomain
import com.xxcactussell.data.utils.mappers.paid.toDomain
import com.xxcactussell.data.utils.mappers.poll.toDomain
import com.xxcactussell.data.utils.mappers.profile.toDomain
import com.xxcactussell.data.utils.mappers.quick.toDomain
import com.xxcactussell.data.utils.mappers.reaction.toDomain
import com.xxcactussell.data.utils.mappers.saved.toDomain
import com.xxcactussell.data.utils.mappers.scope.toDomain
import com.xxcactussell.data.utils.mappers.secret.toDomain
import com.xxcactussell.data.utils.mappers.star.toDomain
import com.xxcactussell.data.utils.mappers.sticker.toDomain
import com.xxcactussell.data.utils.mappers.stories.toDomain
import com.xxcactussell.data.utils.mappers.suggested.toDomain
import com.xxcactussell.data.utils.mappers.supergroup.toDomain
import com.xxcactussell.data.utils.mappers.terms.toDomain
import com.xxcactussell.data.utils.mappers.ton.toDomain
import com.xxcactussell.data.utils.mappers.trending.toDomain
import com.xxcactussell.data.utils.mappers.unconfirmed.toDomain
import com.xxcactussell.data.utils.mappers.unread.toDomain
import com.xxcactussell.data.utils.mappers.user.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Update.toDomain(): Update = when(this) {
    is TdApi.UpdateAuthorizationState -> this.toDomain()
    is TdApi.UpdateNewMessage -> this.toDomain()
    is TdApi.UpdateMessageSendAcknowledged -> this.toDomain()
    is TdApi.UpdateMessageSendSucceeded -> this.toDomain()
    is TdApi.UpdateMessageSendFailed -> this.toDomain()
    is TdApi.UpdateMessageContent -> this.toDomain()
    is TdApi.UpdateMessageEdited -> this.toDomain()
    is TdApi.UpdateMessageIsPinned -> this.toDomain()
    is TdApi.UpdateMessageInteractionInfo -> this.toDomain()
    is TdApi.UpdateMessageContentOpened -> this.toDomain()
    is TdApi.UpdateMessageMentionRead -> this.toDomain()
    is TdApi.UpdateMessageUnreadReactions -> this.toDomain()
    is TdApi.UpdateMessageFactCheck -> this.toDomain()
    is TdApi.UpdateMessageSuggestedPostInfo -> this.toDomain()
    is TdApi.UpdateMessageLiveLocationViewed -> this.toDomain()
    is TdApi.UpdateVideoPublished -> this.toDomain()
    is TdApi.UpdateNewChat -> this.toDomain()
    is TdApi.UpdateChatTitle -> this.toDomain()
    is TdApi.UpdateChatPhoto -> this.toDomain()
    is TdApi.UpdateChatAccentColors -> this.toDomain()
    is TdApi.UpdateChatPermissions -> this.toDomain()
    is TdApi.UpdateChatLastMessage -> this.toDomain()
    is TdApi.UpdateChatPosition -> this.toDomain()
    is TdApi.UpdateChatAddedToList -> this.toDomain()
    is TdApi.UpdateChatRemovedFromList -> this.toDomain()
    is TdApi.UpdateChatReadInbox -> this.toDomain()
    is TdApi.UpdateChatReadOutbox -> this.toDomain()
    is TdApi.UpdateChatActionBar -> this.toDomain()
    is TdApi.UpdateChatBusinessBotManageBar -> this.toDomain()
    is TdApi.UpdateChatAvailableReactions -> this.toDomain()
    is TdApi.UpdateChatDraftMessage -> this.toDomain()
    is TdApi.UpdateChatEmojiStatus -> this.toDomain()
    is TdApi.UpdateChatMessageSender -> this.toDomain()
    is TdApi.UpdateChatMessageAutoDeleteTime -> this.toDomain()
    is TdApi.UpdateChatNotificationSettings -> this.toDomain()
    is TdApi.UpdateChatPendingJoinRequests -> this.toDomain()
    is TdApi.UpdateChatReplyMarkup -> this.toDomain()
    is TdApi.UpdateChatBackground -> this.toDomain()
    is TdApi.UpdateChatTheme -> this.toDomain()
    is TdApi.UpdateChatUnreadMentionCount -> this.toDomain()
    is TdApi.UpdateChatUnreadReactionCount -> this.toDomain()
    is TdApi.UpdateChatVideoChat -> this.toDomain()
    is TdApi.UpdateChatDefaultDisableNotification -> this.toDomain()
    is TdApi.UpdateChatHasProtectedContent -> this.toDomain()
    is TdApi.UpdateChatIsTranslatable -> this.toDomain()
    is TdApi.UpdateChatIsMarkedAsUnread -> this.toDomain()
    is TdApi.UpdateChatViewAsTopics -> this.toDomain()
    is TdApi.UpdateChatBlockList -> this.toDomain()
    is TdApi.UpdateChatHasScheduledMessages -> this.toDomain()
    is TdApi.UpdateChatOnlineMemberCount -> this.toDomain()
    is TdApi.UpdateSavedMessagesTopic -> this.toDomain()
    is TdApi.UpdateSavedMessagesTopicCount -> this.toDomain()
    is TdApi.UpdateDirectMessagesChatTopic -> this.toDomain()
    is TdApi.UpdateTopicMessageCount -> this.toDomain()
    is TdApi.UpdateQuickReplyShortcut -> this.toDomain()
    is TdApi.UpdateQuickReplyShortcutDeleted -> this.toDomain()
    is TdApi.UpdateQuickReplyShortcuts -> this.toDomain()
    is TdApi.UpdateQuickReplyShortcutMessages -> this.toDomain()
    is TdApi.UpdateForumTopicInfo -> this.toDomain()
    is TdApi.UpdateForumTopic -> this.toDomain()
    is TdApi.UpdateScopeNotificationSettings -> this.toDomain()
    is TdApi.UpdateReactionNotificationSettings -> this.toDomain()
    is TdApi.UpdateNotification -> this.toDomain()
    is TdApi.UpdateNotificationGroup -> this.toDomain()
    is TdApi.UpdateActiveNotifications -> this.toDomain()
    is TdApi.UpdateHavePendingNotifications -> this.toDomain()
    is TdApi.UpdateDeleteMessages -> this.toDomain()
    is TdApi.UpdateChatAction -> this.toDomain()
    is TdApi.UpdateUserStatus -> this.toDomain()
    is TdApi.UpdateUser -> this.toDomain()
    is TdApi.UpdateBasicGroup -> this.toDomain()
    is TdApi.UpdateSupergroup -> this.toDomain()
    is TdApi.UpdateSecretChat -> this.toDomain()
    is TdApi.UpdateUserFullInfo -> this.toDomain()
    is TdApi.UpdateBasicGroupFullInfo -> this.toDomain()
    is TdApi.UpdateSupergroupFullInfo -> this.toDomain()
    is TdApi.UpdateServiceNotification -> this.toDomain()
    is TdApi.UpdateFile -> this.toDomain()
    is TdApi.UpdateFileGenerationStart -> this.toDomain()
    is TdApi.UpdateFileGenerationStop -> this.toDomain()
    is TdApi.UpdateFileDownloads -> this.toDomain()
    is TdApi.UpdateFileAddedToDownloads -> this.toDomain()
    is TdApi.UpdateFileDownload -> this.toDomain()
    is TdApi.UpdateFileRemovedFromDownloads -> this.toDomain()
    is TdApi.UpdateApplicationVerificationRequired -> this.toDomain()
    is TdApi.UpdateApplicationRecaptchaVerificationRequired -> this.toDomain()
    is TdApi.UpdateCall -> this.toDomain()
    is TdApi.UpdateGroupCall -> this.toDomain()
    is TdApi.UpdateGroupCallParticipant -> this.toDomain()
    is TdApi.UpdateGroupCallParticipants -> this.toDomain()
    is TdApi.UpdateGroupCallVerificationState -> this.toDomain()
    is TdApi.UpdateNewCallSignalingData -> this.toDomain()
    is TdApi.UpdateUserPrivacySettingRules -> this.toDomain()
    is TdApi.UpdateUnreadMessageCount -> this.toDomain()
    is TdApi.UpdateUnreadChatCount -> this.toDomain()
    is TdApi.UpdateStory -> this.toDomain()
    is TdApi.UpdateStoryDeleted -> this.toDomain()
    is TdApi.UpdateStoryPostSucceeded -> this.toDomain()
    is TdApi.UpdateStoryPostFailed -> this.toDomain()
    is TdApi.UpdateChatActiveStories -> this.toDomain()
    is TdApi.UpdateStoryListChatCount -> this.toDomain()
    is TdApi.UpdateStoryStealthMode -> this.toDomain()
    is TdApi.UpdateOption -> this.toDomain()
    is TdApi.UpdateStickerSet -> this.toDomain()
    is TdApi.UpdateInstalledStickerSets -> this.toDomain()
    is TdApi.UpdateTrendingStickerSets -> this.toDomain()
    is TdApi.UpdateRecentStickers -> this.toDomain()
    is TdApi.UpdateFavoriteStickers -> this.toDomain()
    is TdApi.UpdateSavedAnimations -> this.toDomain()
    is TdApi.UpdateSavedNotificationSounds -> this.toDomain()
    is TdApi.UpdateDefaultBackground -> this.toDomain()
    is TdApi.UpdateEmojiChatThemes -> this.toDomain()
    is TdApi.UpdateAccentColors -> this.toDomain()
    is TdApi.UpdateProfileAccentColors -> this.toDomain()
    is TdApi.UpdateLanguagePackStrings -> this.toDomain()
    is TdApi.UpdateConnectionState -> this.toDomain()
    is TdApi.UpdateFreezeState -> this.toDomain()
    is TdApi.UpdateAgeVerificationParameters -> this.toDomain()
    is TdApi.UpdateTermsOfService -> this.toDomain()
    is TdApi.UpdateUnconfirmedSession -> this.toDomain()
    is TdApi.UpdateAttachmentMenuBots -> this.toDomain()
    is TdApi.UpdateWebAppMessageSent -> this.toDomain()
    is TdApi.UpdateActiveEmojiReactions -> this.toDomain()
    is TdApi.UpdateAvailableMessageEffects -> this.toDomain()
    is TdApi.UpdateDefaultReactionType -> this.toDomain()
    is TdApi.UpdateDefaultPaidReactionType -> this.toDomain()
    is TdApi.UpdateSavedMessagesTags -> this.toDomain()
    is TdApi.UpdateActiveLiveLocationMessages -> this.toDomain()
    is TdApi.UpdateOwnedStarCount -> this.toDomain()
    is TdApi.UpdateOwnedTonCount -> this.toDomain()
    is TdApi.UpdateChatRevenueAmount -> this.toDomain()
    is TdApi.UpdateStarRevenueStatus -> this.toDomain()
    is TdApi.UpdateTonRevenueStatus -> this.toDomain()
    is TdApi.UpdateSpeechRecognitionTrial -> this.toDomain()
    is TdApi.UpdateDiceEmojis -> this.toDomain()
    is TdApi.UpdateAnimatedEmojiMessageClicked -> this.toDomain()
    is TdApi.UpdateAnimationSearchParameters -> this.toDomain()
    is TdApi.UpdateSuggestedActions -> this.toDomain()
    is TdApi.UpdateSpeedLimitNotification -> this.toDomain()
    is TdApi.UpdateContactCloseBirthdays -> this.toDomain()
    is TdApi.UpdateAutosaveSettings -> this.toDomain()
    is TdApi.UpdateBusinessConnection -> this.toDomain()
    is TdApi.UpdateNewBusinessMessage -> this.toDomain()
    is TdApi.UpdateBusinessMessageEdited -> this.toDomain()
    is TdApi.UpdateBusinessMessagesDeleted -> this.toDomain()
    is TdApi.UpdateNewInlineQuery -> this.toDomain()
    is TdApi.UpdateNewChosenInlineResult -> this.toDomain()
    is TdApi.UpdateNewCallbackQuery -> this.toDomain()
    is TdApi.UpdateNewInlineCallbackQuery -> this.toDomain()
    is TdApi.UpdateNewBusinessCallbackQuery -> this.toDomain()
    is TdApi.UpdateNewShippingQuery -> this.toDomain()
    is TdApi.UpdateNewPreCheckoutQuery -> this.toDomain()
    is TdApi.UpdateNewCustomEvent -> this.toDomain()
    is TdApi.UpdateNewCustomQuery -> this.toDomain()
    is TdApi.UpdatePoll -> this.toDomain()
    is TdApi.UpdatePollAnswer -> this.toDomain()
    is TdApi.UpdateChatMember -> this.toDomain()
    is TdApi.UpdateNewChatJoinRequest -> this.toDomain()
    is TdApi.UpdateChatBoost -> this.toDomain()
    is TdApi.UpdateMessageReaction -> this.toDomain()
    is TdApi.UpdateMessageReactions -> this.toDomain()
    is TdApi.UpdatePaidMediaPurchased -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.UpdateAccentColors.toDomain(): UpdateAccentColors = UpdateAccentColors(
    colors = this.colors.map { it.toDomain() },
    availableAccentColorIds = this.availableAccentColorIds
)

fun TdApi.UpdateActiveEmojiReactions.toDomain(): UpdateActiveEmojiReactions = UpdateActiveEmojiReactions(
    emojis = this.emojis.toList()
)

fun TdApi.UpdateActiveLiveLocationMessages.toDomain(): UpdateActiveLiveLocationMessages = UpdateActiveLiveLocationMessages(
    messages = this.messages.map { it.toDomain() }
)

fun TdApi.UpdateActiveNotifications.toDomain(): UpdateActiveNotifications = UpdateActiveNotifications(
    groups = this.groups.map { it.toDomain() }
)

fun TdApi.UpdateAgeVerificationParameters.toDomain(): UpdateAgeVerificationParameters = UpdateAgeVerificationParameters(
    parameters = this.parameters?.toDomain()
)

fun TdApi.UpdateAnimatedEmojiMessageClicked.toDomain(): UpdateAnimatedEmojiMessageClicked = UpdateAnimatedEmojiMessageClicked(
    chatId = this.chatId,
    messageId = this.messageId,
    sticker = this.sticker.toDomain()
)

fun TdApi.UpdateAnimationSearchParameters.toDomain(): UpdateAnimationSearchParameters = UpdateAnimationSearchParameters(
    provider = this.provider,
    emojis = this.emojis.toList()
)

fun TdApi.UpdateApplicationRecaptchaVerificationRequired.toDomain(): UpdateApplicationRecaptchaVerificationRequired = UpdateApplicationRecaptchaVerificationRequired(
    verificationId = this.verificationId,
    action = this.action,
    recaptchaKeyId = this.recaptchaKeyId
)

fun TdApi.UpdateApplicationVerificationRequired.toDomain(): UpdateApplicationVerificationRequired = UpdateApplicationVerificationRequired(
    verificationId = this.verificationId,
    nonce = this.nonce,
    cloudProjectNumber = this.cloudProjectNumber
)

fun TdApi.UpdateAttachmentMenuBots.toDomain(): UpdateAttachmentMenuBots = UpdateAttachmentMenuBots(
    bots = this.bots.map { it.toDomain() }
)

fun TdApi.UpdateAuthorizationState.toDomain(): UpdateAuthorizationState = UpdateAuthorizationState(
    authorizationState = this.authorizationState.toDomain()
)

fun TdApi.UpdateAutosaveSettings.toDomain(): UpdateAutosaveSettings = UpdateAutosaveSettings(
    scope = this.scope.toDomain(),
    settings = this.settings?.toDomain()
)

fun TdApi.UpdateAvailableMessageEffects.toDomain(): UpdateAvailableMessageEffects = UpdateAvailableMessageEffects(
    reactionEffectIds = this.reactionEffectIds,
    stickerEffectIds = this.stickerEffectIds
)

fun TdApi.UpdateBasicGroup.toDomain(): UpdateBasicGroup = UpdateBasicGroup(
    basicGroup = this.basicGroup.toDomain()
)

fun TdApi.UpdateBasicGroupFullInfo.toDomain(): UpdateBasicGroupFullInfo = UpdateBasicGroupFullInfo(
    basicGroupId = this.basicGroupId,
    basicGroupFullInfo = this.basicGroupFullInfo.toDomain()
)

fun TdApi.UpdateBusinessConnection.toDomain(): UpdateBusinessConnection = UpdateBusinessConnection(
    connection = this.connection.toDomain()
)

fun TdApi.UpdateBusinessMessageEdited.toDomain(): UpdateBusinessMessageEdited = UpdateBusinessMessageEdited(
    connectionId = this.connectionId,
    message = this.message.toDomain()
)

fun TdApi.UpdateBusinessMessagesDeleted.toDomain(): UpdateBusinessMessagesDeleted = UpdateBusinessMessagesDeleted(
    connectionId = this.connectionId,
    chatId = this.chatId,
    messageIds = this.messageIds
)

fun TdApi.UpdateCall.toDomain(): UpdateCall = UpdateCall(
    call = this.call.toDomain()
)

fun TdApi.UpdateChatAccentColors.toDomain(): UpdateChatAccentColors = UpdateChatAccentColors(
    chatId = this.chatId,
    accentColorId = this.accentColorId,
    backgroundCustomEmojiId = this.backgroundCustomEmojiId,
    profileAccentColorId = this.profileAccentColorId,
    profileBackgroundCustomEmojiId = this.profileBackgroundCustomEmojiId
)

fun TdApi.UpdateChatAction.toDomain(): UpdateChatAction = UpdateChatAction(
    chatId = this.chatId,
    messageThreadId = this.messageThreadId,
    senderId = this.senderId.toDomain(),
    action = this.action.toDomain()
)

fun TdApi.UpdateChatActionBar.toDomain(): UpdateChatActionBar = UpdateChatActionBar(
    chatId = this.chatId,
    actionBar = this.actionBar?.toDomain()
)

fun TdApi.UpdateChatActiveStories.toDomain(): UpdateChatActiveStories = UpdateChatActiveStories(
    activeStories = this.activeStories.toDomain()
)

fun TdApi.UpdateChatAddedToList.toDomain(): UpdateChatAddedToList = UpdateChatAddedToList(
    chatId = this.chatId,
    chatList = this.chatList.toDomain()
)

fun TdApi.UpdateChatAvailableReactions.toDomain(): UpdateChatAvailableReactions = UpdateChatAvailableReactions(
    chatId = this.chatId,
    availableReactions = this.availableReactions.toDomain()
)

fun TdApi.UpdateChatBackground.toDomain(): UpdateChatBackground = UpdateChatBackground(
    chatId = this.chatId,
    background = this.background?.toDomain()
)

fun TdApi.UpdateChatBlockList.toDomain(): UpdateChatBlockList = UpdateChatBlockList(
    chatId = this.chatId,
    blockList = this.blockList?.toDomain()
)

fun TdApi.UpdateChatBoost.toDomain(): UpdateChatBoost = UpdateChatBoost(
    chatId = this.chatId,
    boost = this.boost.toDomain()
)

fun TdApi.UpdateChatBusinessBotManageBar.toDomain(): UpdateChatBusinessBotManageBar = UpdateChatBusinessBotManageBar(
    chatId = this.chatId,
    businessBotManageBar = this.businessBotManageBar?.toDomain()
)

fun TdApi.UpdateChatDefaultDisableNotification.toDomain(): UpdateChatDefaultDisableNotification = UpdateChatDefaultDisableNotification(
    chatId = this.chatId,
    defaultDisableNotification = this.defaultDisableNotification
)

fun TdApi.UpdateChatDraftMessage.toDomain(): UpdateChatDraftMessage = UpdateChatDraftMessage(
    chatId = this.chatId,
    draftMessage = this.draftMessage?.toDomain(),
    positions = this.positions.map { it.toDomain() }
)

fun TdApi.UpdateChatEmojiStatus.toDomain(): UpdateChatEmojiStatus = UpdateChatEmojiStatus(
    chatId = this.chatId,
    emojiStatus = this.emojiStatus?.toDomain()
)

fun TdApi.UpdateChatHasProtectedContent.toDomain(): UpdateChatHasProtectedContent = UpdateChatHasProtectedContent(
    chatId = this.chatId,
    hasProtectedContent = this.hasProtectedContent
)

fun TdApi.UpdateChatHasScheduledMessages.toDomain(): UpdateChatHasScheduledMessages = UpdateChatHasScheduledMessages(
    chatId = this.chatId,
    hasScheduledMessages = this.hasScheduledMessages
)

fun TdApi.UpdateChatIsMarkedAsUnread.toDomain(): UpdateChatIsMarkedAsUnread = UpdateChatIsMarkedAsUnread(
    chatId = this.chatId,
    isMarkedAsUnread = this.isMarkedAsUnread
)

fun TdApi.UpdateChatIsTranslatable.toDomain(): UpdateChatIsTranslatable = UpdateChatIsTranslatable(
    chatId = this.chatId,
    isTranslatable = this.isTranslatable
)

fun TdApi.UpdateChatLastMessage.toDomain(): UpdateChatLastMessage = UpdateChatLastMessage(
    chatId = this.chatId,
    lastMessage = this.lastMessage?.toDomain(),
    positions = this.positions.map { it.toDomain() }
)

fun TdApi.UpdateChatMember.toDomain(): UpdateChatMember = UpdateChatMember(
    chatId = this.chatId,
    actorUserId = this.actorUserId,
    date = this.date,
    inviteLink = this.inviteLink?.toDomain(),
    viaJoinRequest = this.viaJoinRequest,
    viaChatFolderInviteLink = this.viaChatFolderInviteLink,
    oldChatMember = this.oldChatMember.toDomain(),
    newChatMember = this.newChatMember.toDomain()
)

fun TdApi.UpdateChatMessageAutoDeleteTime.toDomain(): UpdateChatMessageAutoDeleteTime = UpdateChatMessageAutoDeleteTime(
    chatId = this.chatId,
    messageAutoDeleteTime = this.messageAutoDeleteTime
)

fun TdApi.UpdateChatMessageSender.toDomain(): UpdateChatMessageSender = UpdateChatMessageSender(
    chatId = this.chatId,
    messageSenderId = this.messageSenderId?.toDomain()
)

fun TdApi.UpdateChatNotificationSettings.toDomain(): UpdateChatNotificationSettings = UpdateChatNotificationSettings(
    chatId = this.chatId,
    notificationSettings = this.notificationSettings.toDomain()
)

fun TdApi.UpdateChatOnlineMemberCount.toDomain(): UpdateChatOnlineMemberCount = UpdateChatOnlineMemberCount(
    chatId = this.chatId,
    onlineMemberCount = this.onlineMemberCount
)

fun TdApi.UpdateChatPendingJoinRequests.toDomain(): UpdateChatPendingJoinRequests = UpdateChatPendingJoinRequests(
    chatId = this.chatId,
    pendingJoinRequests = this.pendingJoinRequests?.toDomain()
)

fun TdApi.UpdateChatPermissions.toDomain(): UpdateChatPermissions = UpdateChatPermissions(
    chatId = this.chatId,
    permissions = this.permissions.toDomain()
)

fun TdApi.UpdateChatPhoto.toDomain(): UpdateChatPhoto = UpdateChatPhoto(
    chatId = this.chatId,
    photo = this.photo?.toDomain()
)

fun TdApi.UpdateChatPosition.toDomain(): UpdateChatPosition = UpdateChatPosition(
    chatId = this.chatId,
    position = this.position.toDomain()
)

fun TdApi.UpdateChatReadInbox.toDomain(): UpdateChatReadInbox = UpdateChatReadInbox(
    chatId = this.chatId,
    lastReadInboxMessageId = this.lastReadInboxMessageId,
    unreadCount = this.unreadCount
)

fun TdApi.UpdateChatReadOutbox.toDomain(): UpdateChatReadOutbox = UpdateChatReadOutbox(
    chatId = this.chatId,
    lastReadOutboxMessageId = this.lastReadOutboxMessageId
)

fun TdApi.UpdateChatRemovedFromList.toDomain(): UpdateChatRemovedFromList = UpdateChatRemovedFromList(
    chatId = this.chatId,
    chatList = this.chatList.toDomain()
)

fun TdApi.UpdateChatReplyMarkup.toDomain(): UpdateChatReplyMarkup = UpdateChatReplyMarkup(
    chatId = this.chatId,
    replyMarkupMessageId = this.replyMarkupMessageId
)

fun TdApi.UpdateChatRevenueAmount.toDomain(): UpdateChatRevenueAmount = UpdateChatRevenueAmount(
    chatId = this.chatId,
    revenueAmount = this.revenueAmount.toDomain()
)

fun TdApi.UpdateChatTheme.toDomain(): UpdateChatTheme = UpdateChatTheme(
    chatId = this.chatId,
    theme = this.theme?.toDomain()
)

fun TdApi.UpdateChatTitle.toDomain(): UpdateChatTitle = UpdateChatTitle(
    chatId = this.chatId,
    title = this.title
)

fun TdApi.UpdateChatUnreadMentionCount.toDomain(): UpdateChatUnreadMentionCount = UpdateChatUnreadMentionCount(
    chatId = this.chatId,
    unreadMentionCount = this.unreadMentionCount
)

fun TdApi.UpdateChatUnreadReactionCount.toDomain(): UpdateChatUnreadReactionCount = UpdateChatUnreadReactionCount(
    chatId = this.chatId,
    unreadReactionCount = this.unreadReactionCount
)

fun TdApi.UpdateChatVideoChat.toDomain(): UpdateChatVideoChat = UpdateChatVideoChat(
    chatId = this.chatId,
    videoChat = this.videoChat.toDomain()
)

fun TdApi.UpdateChatViewAsTopics.toDomain(): UpdateChatViewAsTopics = UpdateChatViewAsTopics(
    chatId = this.chatId,
    viewAsTopics = this.viewAsTopics
)

fun TdApi.UpdateConnectionState.toDomain(): UpdateConnectionState = UpdateConnectionState(
    state = this.state.toDomain()
)

fun TdApi.UpdateContactCloseBirthdays.toDomain(): UpdateContactCloseBirthdays = UpdateContactCloseBirthdays(
    closeBirthdayUsers = this.closeBirthdayUsers.map { it.toDomain() }
)

fun TdApi.UpdateDefaultBackground.toDomain(): UpdateDefaultBackground = UpdateDefaultBackground(
    forDarkTheme = this.forDarkTheme,
    background = this.background?.toDomain()
)

fun TdApi.UpdateDefaultPaidReactionType.toDomain(): UpdateDefaultPaidReactionType = UpdateDefaultPaidReactionType(
    type = this.type.toDomain()
)

fun TdApi.UpdateDefaultReactionType.toDomain(): UpdateDefaultReactionType = UpdateDefaultReactionType(
    reactionType = this.reactionType.toDomain()
)

fun TdApi.UpdateDeleteMessages.toDomain(): UpdateDeleteMessages = UpdateDeleteMessages(
    chatId = this.chatId,
    messageIds = this.messageIds,
    isPermanent = this.isPermanent,
    fromCache = this.fromCache
)

fun TdApi.UpdateDiceEmojis.toDomain(): UpdateDiceEmojis = UpdateDiceEmojis(
    emojis = this.emojis.toList()
)

fun TdApi.UpdateDirectMessagesChatTopic.toDomain(): UpdateDirectMessagesChatTopic = UpdateDirectMessagesChatTopic(
    topic = this.topic.toDomain()
)

fun TdApi.UpdateEmojiChatThemes.toDomain(): UpdateEmojiChatThemes = UpdateEmojiChatThemes(
    chatThemes = this.chatThemes.map { it.toDomain() }
)

fun TdApi.UpdateFavoriteStickers.toDomain(): UpdateFavoriteStickers = UpdateFavoriteStickers(
    stickerIds = this.stickerIds
)

fun TdApi.UpdateFile.toDomain(): UpdateFile = UpdateFile(
    file = this.file.toDomain()
)

fun TdApi.UpdateFileAddedToDownloads.toDomain(): UpdateFileAddedToDownloads = UpdateFileAddedToDownloads(
    fileDownload = this.fileDownload.toDomain(),
    counts = this.counts.toDomain()
)

fun TdApi.UpdateFileDownload.toDomain(): UpdateFileDownload = UpdateFileDownload(
    fileId = this.fileId,
    completeDate = this.completeDate,
    isPaused = this.isPaused,
    counts = this.counts.toDomain()
)

fun TdApi.UpdateFileDownloads.toDomain(): UpdateFileDownloads = UpdateFileDownloads(
    totalSize = this.totalSize,
    totalCount = this.totalCount,
    downloadedSize = this.downloadedSize
)

fun TdApi.UpdateFileGenerationStart.toDomain(): UpdateFileGenerationStart = UpdateFileGenerationStart(
    generationId = this.generationId,
    originalPath = this.originalPath,
    destinationPath = this.destinationPath,
    conversion = this.conversion
)

fun TdApi.UpdateFileGenerationStop.toDomain(): UpdateFileGenerationStop = UpdateFileGenerationStop(
    generationId = this.generationId
)

fun TdApi.UpdateFileRemovedFromDownloads.toDomain(): UpdateFileRemovedFromDownloads = UpdateFileRemovedFromDownloads(
    fileId = this.fileId,
    counts = this.counts.toDomain()
)

fun TdApi.UpdateForumTopic.toDomain(): UpdateForumTopic = UpdateForumTopic(
    chatId = this.chatId,
    messageThreadId = this.messageThreadId,
    isPinned = this.isPinned,
    lastReadInboxMessageId = this.lastReadInboxMessageId,
    lastReadOutboxMessageId = this.lastReadOutboxMessageId,
    unreadMentionCount = this.unreadMentionCount,
    unreadReactionCount = this.unreadReactionCount,
    notificationSettings = this.notificationSettings.toDomain()
)

fun TdApi.UpdateForumTopicInfo.toDomain(): UpdateForumTopicInfo = UpdateForumTopicInfo(
    info = this.info.toDomain()
)

fun TdApi.UpdateFreezeState.toDomain(): UpdateFreezeState = UpdateFreezeState(
    isFrozen = this.isFrozen,
    freezingDate = this.freezingDate,
    deletionDate = this.deletionDate,
    appealLink = this.appealLink
)

fun TdApi.UpdateGroupCall.toDomain(): UpdateGroupCall = UpdateGroupCall(
    groupCall = this.groupCall.toDomain()
)

fun TdApi.UpdateGroupCallParticipant.toDomain(): UpdateGroupCallParticipant = UpdateGroupCallParticipant(
    groupCallId = this.groupCallId,
    participant = this.participant.toDomain()
)

fun TdApi.UpdateGroupCallParticipants.toDomain(): UpdateGroupCallParticipants = UpdateGroupCallParticipants(
    groupCallId = this.groupCallId,
    participantUserIds = this.participantUserIds
)

fun TdApi.UpdateGroupCallVerificationState.toDomain(): UpdateGroupCallVerificationState = UpdateGroupCallVerificationState(
    groupCallId = this.groupCallId,
    generation = this.generation,
    emojis = this.emojis.toList()
)

fun TdApi.UpdateHavePendingNotifications.toDomain(): UpdateHavePendingNotifications = UpdateHavePendingNotifications(
    haveDelayedNotifications = this.haveDelayedNotifications,
    haveUnreceivedNotifications = this.haveUnreceivedNotifications
)

fun TdApi.UpdateInstalledStickerSets.toDomain(): UpdateInstalledStickerSets = UpdateInstalledStickerSets(
    stickerType = this.stickerType.toDomain(),
    stickerSetIds = this.stickerSetIds
)

fun TdApi.UpdateLanguagePackStrings.toDomain(): UpdateLanguagePackStrings = UpdateLanguagePackStrings(
    localizationTarget = this.localizationTarget,
    languagePackId = this.languagePackId,
    strings = this.strings.map { it.toDomain() }
)

fun TdApi.UpdateMessageContent.toDomain(): UpdateMessageContent = UpdateMessageContent(
    chatId = this.chatId,
    messageId = this.messageId,
    newContent = this.newContent.toDomain()
)

fun TdApi.UpdateMessageContentOpened.toDomain(): UpdateMessageContentOpened = UpdateMessageContentOpened(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.UpdateMessageEdited.toDomain(): UpdateMessageEdited = UpdateMessageEdited(
    chatId = this.chatId,
    messageId = this.messageId,
    editDate = this.editDate,
    replyMarkup = this.replyMarkup?.toDomain()
)

fun TdApi.UpdateMessageFactCheck.toDomain(): UpdateMessageFactCheck = UpdateMessageFactCheck(
    chatId = this.chatId,
    messageId = this.messageId,
    factCheck = this.factCheck.toDomain()
)

fun TdApi.UpdateMessageInteractionInfo.toDomain(): UpdateMessageInteractionInfo = UpdateMessageInteractionInfo(
    chatId = this.chatId,
    messageId = this.messageId,
    interactionInfo = this.interactionInfo?.toDomain()
)

fun TdApi.UpdateMessageIsPinned.toDomain(): UpdateMessageIsPinned = UpdateMessageIsPinned(
    chatId = this.chatId,
    messageId = this.messageId,
    isPinned = this.isPinned
)

fun TdApi.UpdateMessageLiveLocationViewed.toDomain(): UpdateMessageLiveLocationViewed = UpdateMessageLiveLocationViewed(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.UpdateMessageMentionRead.toDomain(): UpdateMessageMentionRead = UpdateMessageMentionRead(
    chatId = this.chatId,
    messageId = this.messageId,
    unreadMentionCount = this.unreadMentionCount
)

fun TdApi.UpdateMessageReaction.toDomain(): UpdateMessageReaction = UpdateMessageReaction(
    chatId = this.chatId,
    messageId = this.messageId,
    actorId = this.actorId.toDomain(),
    date = this.date,
    oldReactionTypes = this.oldReactionTypes.map { it.toDomain() },
    newReactionTypes = this.newReactionTypes.map { it.toDomain() }
)

fun TdApi.UpdateMessageReactions.toDomain(): UpdateMessageReactions = UpdateMessageReactions(
    chatId = this.chatId,
    messageId = this.messageId,
    date = this.date,
    reactions = this.reactions.map { it.toDomain() }
)

fun TdApi.UpdateMessageSendAcknowledged.toDomain(): UpdateMessageSendAcknowledged = UpdateMessageSendAcknowledged(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.UpdateMessageSendFailed.toDomain(): UpdateMessageSendFailed = UpdateMessageSendFailed(
    message = this.message.toDomain(),
    oldMessageId = this.oldMessageId,
    error = this.error.toDomain()
)

fun TdApi.UpdateMessageSendSucceeded.toDomain(): UpdateMessageSendSucceeded = UpdateMessageSendSucceeded(
    message = this.message.toDomain(),
    oldMessageId = this.oldMessageId
)

fun TdApi.UpdateMessageSuggestedPostInfo.toDomain(): UpdateMessageSuggestedPostInfo = UpdateMessageSuggestedPostInfo(
    chatId = this.chatId,
    messageId = this.messageId,
    suggestedPostInfo = this.suggestedPostInfo.toDomain()
)

fun TdApi.UpdateMessageUnreadReactions.toDomain(): UpdateMessageUnreadReactions = UpdateMessageUnreadReactions(
    chatId = this.chatId,
    messageId = this.messageId,
    unreadReactions = this.unreadReactions.map { it.toDomain() },
    unreadReactionCount = this.unreadReactionCount
)

fun TdApi.UpdateNewBusinessCallbackQuery.toDomain(): UpdateNewBusinessCallbackQuery = UpdateNewBusinessCallbackQuery(
    id = this.id,
    senderUserId = this.senderUserId,
    connectionId = this.connectionId,
    message = this.message.toDomain(),
    chatInstance = this.chatInstance,
    payload = this.payload.toDomain()
)

fun TdApi.UpdateNewBusinessMessage.toDomain(): UpdateNewBusinessMessage = UpdateNewBusinessMessage(
    connectionId = this.connectionId,
    message = this.message.toDomain()
)

fun TdApi.UpdateNewCallSignalingData.toDomain(): UpdateNewCallSignalingData = UpdateNewCallSignalingData(
    callId = this.callId,
    data = this.data
)

fun TdApi.UpdateNewCallbackQuery.toDomain(): UpdateNewCallbackQuery = UpdateNewCallbackQuery(
    id = this.id,
    senderUserId = this.senderUserId,
    chatId = this.chatId,
    messageId = this.messageId,
    chatInstance = this.chatInstance,
    payload = this.payload.toDomain()
)

fun TdApi.UpdateNewChat.toDomain(): UpdateNewChat = UpdateNewChat(
    chat = this.chat.toDomain()
)

fun TdApi.UpdateNewChatJoinRequest.toDomain(): UpdateNewChatJoinRequest = UpdateNewChatJoinRequest(
    chatId = this.chatId,
    request = this.request.toDomain(),
    userChatId = this.userChatId,
    inviteLink = this.inviteLink?.toDomain()
)

fun TdApi.UpdateNewChosenInlineResult.toDomain(): UpdateNewChosenInlineResult = UpdateNewChosenInlineResult(
    senderUserId = this.senderUserId,
    userLocation = this.userLocation?.toDomain(),
    query = this.query,
    resultId = this.resultId,
    inlineMessageId = this.inlineMessageId
)

fun TdApi.UpdateNewCustomEvent.toDomain(): UpdateNewCustomEvent = UpdateNewCustomEvent(
    event = this.event
)

fun TdApi.UpdateNewCustomQuery.toDomain(): UpdateNewCustomQuery = UpdateNewCustomQuery(
    id = this.id,
    data = this.data,
    timeout = this.timeout
)

fun TdApi.UpdateNewInlineCallbackQuery.toDomain(): UpdateNewInlineCallbackQuery = UpdateNewInlineCallbackQuery(
    id = this.id,
    senderUserId = this.senderUserId,
    inlineMessageId = this.inlineMessageId,
    chatInstance = this.chatInstance,
    payload = this.payload.toDomain()
)

fun TdApi.UpdateNewInlineQuery.toDomain(): UpdateNewInlineQuery = UpdateNewInlineQuery(
    id = this.id,
    senderUserId = this.senderUserId,
    userLocation = this.userLocation?.toDomain(),
    chatType = this.chatType?.toDomain(),
    query = this.query,
    offset = this.offset
)

fun TdApi.UpdateNewMessage.toDomain(): UpdateNewMessage = UpdateNewMessage(
    message = this.message.toDomain()
)

fun TdApi.UpdateNewPreCheckoutQuery.toDomain(): UpdateNewPreCheckoutQuery = UpdateNewPreCheckoutQuery(
    id = this.id,
    senderUserId = this.senderUserId,
    currency = this.currency,
    totalAmount = this.totalAmount,
    invoicePayload = this.invoicePayload,
    shippingOptionId = this.shippingOptionId,
    orderInfo = this.orderInfo?.toDomain()
)

fun TdApi.UpdateNewShippingQuery.toDomain(): UpdateNewShippingQuery = UpdateNewShippingQuery(
    id = this.id,
    senderUserId = this.senderUserId,
    invoicePayload = this.invoicePayload,
    shippingAddress = this.shippingAddress.toDomain()
)

fun TdApi.UpdateNotification.toDomain(): UpdateNotification = UpdateNotification(
    notificationGroupId = this.notificationGroupId,
    notification = this.notification.toDomain()
)

fun TdApi.UpdateNotificationGroup.toDomain(): UpdateNotificationGroup = UpdateNotificationGroup(
    notificationGroupId = this.notificationGroupId,
    type = this.type.toDomain(),
    chatId = this.chatId,
    notificationSettingsChatId = this.notificationSettingsChatId,
    notificationSoundId = this.notificationSoundId,
    totalCount = this.totalCount,
    addedNotifications = this.addedNotifications.map { it.toDomain() },
    removedNotificationIds = this.removedNotificationIds
)

fun TdApi.UpdateOption.toDomain(): UpdateOption = UpdateOption(
    name = this.name,
    value = this.value.toDomain()
)

fun TdApi.UpdateOwnedStarCount.toDomain(): UpdateOwnedStarCount = UpdateOwnedStarCount(
    starAmount = this.starAmount.toDomain()
)

fun TdApi.UpdateOwnedTonCount.toDomain(): UpdateOwnedTonCount = UpdateOwnedTonCount(
    tonAmount = this.tonAmount
)

fun TdApi.UpdatePaidMediaPurchased.toDomain(): UpdatePaidMediaPurchased = UpdatePaidMediaPurchased(
    userId = this.userId,
    payload = this.payload
)

fun TdApi.UpdatePoll.toDomain(): UpdatePoll = UpdatePoll(
    poll = this.poll.toDomain()
)

fun TdApi.UpdatePollAnswer.toDomain(): UpdatePollAnswer = UpdatePollAnswer(
    pollId = this.pollId,
    voterId = this.voterId.toDomain(),
    optionIds = this.optionIds
)

fun TdApi.UpdateProfileAccentColors.toDomain(): UpdateProfileAccentColors = UpdateProfileAccentColors(
    colors = this.colors.map { it.toDomain() },
    availableAccentColorIds = this.availableAccentColorIds
)

fun TdApi.UpdateQuickReplyShortcut.toDomain(): UpdateQuickReplyShortcut = UpdateQuickReplyShortcut(
    shortcut = this.shortcut.toDomain()
)

fun TdApi.UpdateQuickReplyShortcutDeleted.toDomain(): UpdateQuickReplyShortcutDeleted = UpdateQuickReplyShortcutDeleted(
    shortcutId = this.shortcutId
)

fun TdApi.UpdateQuickReplyShortcutMessages.toDomain(): UpdateQuickReplyShortcutMessages = UpdateQuickReplyShortcutMessages(
    shortcutId = this.shortcutId,
    messages = this.messages.map { it.toDomain() }
)

fun TdApi.UpdateQuickReplyShortcuts.toDomain(): UpdateQuickReplyShortcuts = UpdateQuickReplyShortcuts(
    shortcutIds = this.shortcutIds
)

fun TdApi.UpdateReactionNotificationSettings.toDomain(): UpdateReactionNotificationSettings = UpdateReactionNotificationSettings(
    notificationSettings = this.notificationSettings.toDomain()
)

fun TdApi.UpdateRecentStickers.toDomain(): UpdateRecentStickers = UpdateRecentStickers(
    isAttached = this.isAttached,
    stickerIds = this.stickerIds
)

fun TdApi.UpdateSavedAnimations.toDomain(): UpdateSavedAnimations = UpdateSavedAnimations(
    animationIds = this.animationIds
)

fun TdApi.UpdateSavedMessagesTags.toDomain(): UpdateSavedMessagesTags = UpdateSavedMessagesTags(
    savedMessagesTopicId = this.savedMessagesTopicId,
    tags = this.tags.toDomain()
)

fun TdApi.UpdateSavedMessagesTopic.toDomain(): UpdateSavedMessagesTopic = UpdateSavedMessagesTopic(
    topic = this.topic.toDomain()
)

fun TdApi.UpdateSavedMessagesTopicCount.toDomain(): UpdateSavedMessagesTopicCount = UpdateSavedMessagesTopicCount(
    topicCount = this.topicCount
)

fun TdApi.UpdateSavedNotificationSounds.toDomain(): UpdateSavedNotificationSounds = UpdateSavedNotificationSounds(
    notificationSoundIds = this.notificationSoundIds
)

fun TdApi.UpdateScopeNotificationSettings.toDomain(): UpdateScopeNotificationSettings = UpdateScopeNotificationSettings(
    scope = this.scope.toDomain(),
    notificationSettings = this.notificationSettings.toDomain()
)

fun TdApi.UpdateSecretChat.toDomain(): UpdateSecretChat = UpdateSecretChat(
    secretChat = this.secretChat.toDomain()
)

fun TdApi.UpdateServiceNotification.toDomain(): UpdateServiceNotification = UpdateServiceNotification(
    type = this.type,
    content = this.content.toDomain()
)

fun TdApi.UpdateSpeechRecognitionTrial.toDomain(): UpdateSpeechRecognitionTrial = UpdateSpeechRecognitionTrial(
    maxMediaDuration = this.maxMediaDuration,
    weeklyCount = this.weeklyCount,
    leftCount = this.leftCount,
    nextResetDate = this.nextResetDate
)

fun TdApi.UpdateSpeedLimitNotification.toDomain(): UpdateSpeedLimitNotification = UpdateSpeedLimitNotification(
    isUpload = this.isUpload
)

fun TdApi.UpdateStarRevenueStatus.toDomain(): UpdateStarRevenueStatus = UpdateStarRevenueStatus(
    ownerId = this.ownerId.toDomain(),
    status = this.status.toDomain()
)

fun TdApi.UpdateStickerSet.toDomain(): UpdateStickerSet = UpdateStickerSet(
    stickerSet = this.stickerSet.toDomain()
)

fun TdApi.UpdateStory.toDomain(): UpdateStory = UpdateStory(
    story = this.story.toDomain()
)

fun TdApi.UpdateStoryDeleted.toDomain(): UpdateStoryDeleted = UpdateStoryDeleted(
    storyPosterChatId = this.storyPosterChatId,
    storyId = this.storyId
)

fun TdApi.UpdateStoryListChatCount.toDomain(): UpdateStoryListChatCount = UpdateStoryListChatCount(
    storyList = this.storyList.toDomain(),
    chatCount = this.chatCount
)

fun TdApi.UpdateStoryPostFailed.toDomain(): UpdateStoryPostFailed = UpdateStoryPostFailed(
    story = this.story.toDomain(),
    error = this.error.toDomain(),
    errorType = this.errorType?.toDomain()
)

fun TdApi.UpdateStoryPostSucceeded.toDomain(): UpdateStoryPostSucceeded = UpdateStoryPostSucceeded(
    story = this.story.toDomain(),
    oldStoryId = this.oldStoryId
)

fun TdApi.UpdateStoryStealthMode.toDomain(): UpdateStoryStealthMode = UpdateStoryStealthMode(
    activeUntilDate = this.activeUntilDate,
    cooldownUntilDate = this.cooldownUntilDate
)

fun TdApi.UpdateSuggestedActions.toDomain(): UpdateSuggestedActions = UpdateSuggestedActions(
    addedActions = this.addedActions.map { it.toDomain() },
    removedActions = this.removedActions.map { it.toDomain() }
)

fun TdApi.UpdateSupergroup.toDomain(): UpdateSupergroup = UpdateSupergroup(
    supergroup = this.supergroup.toDomain()
)

fun TdApi.UpdateSupergroupFullInfo.toDomain(): UpdateSupergroupFullInfo = UpdateSupergroupFullInfo(
    supergroupId = this.supergroupId,
    supergroupFullInfo = this.supergroupFullInfo.toDomain()
)

fun TdApi.UpdateTermsOfService.toDomain(): UpdateTermsOfService = UpdateTermsOfService(
    termsOfServiceId = this.termsOfServiceId,
    termsOfService = this.termsOfService.toDomain()
)

fun TdApi.UpdateTonRevenueStatus.toDomain(): UpdateTonRevenueStatus = UpdateTonRevenueStatus(
    status = this.status.toDomain()
)

fun TdApi.UpdateTopicMessageCount.toDomain(): UpdateTopicMessageCount = UpdateTopicMessageCount(
    chatId = this.chatId,
    topicId = this.topicId.toDomain(),
    messageCount = this.messageCount
)

fun TdApi.UpdateTrendingStickerSets.toDomain(): UpdateTrendingStickerSets = UpdateTrendingStickerSets(
    stickerType = this.stickerType.toDomain(),
    stickerSets = this.stickerSets.toDomain()
)

fun TdApi.UpdateUnconfirmedSession.toDomain(): UpdateUnconfirmedSession = UpdateUnconfirmedSession(
    session = this.session?.toDomain()
)

fun TdApi.UpdateUnreadChatCount.toDomain(): UpdateUnreadChatCount = UpdateUnreadChatCount(
    chatList = this.chatList.toDomain(),
    totalCount = this.totalCount,
    unreadCount = this.unreadCount,
    unreadUnmutedCount = this.unreadUnmutedCount,
    markedAsUnreadCount = this.markedAsUnreadCount,
    markedAsUnreadUnmutedCount = this.markedAsUnreadUnmutedCount
)

fun TdApi.UpdateUnreadMessageCount.toDomain(): UpdateUnreadMessageCount = UpdateUnreadMessageCount(
    chatList = this.chatList.toDomain(),
    unreadCount = this.unreadCount,
    unreadUnmutedCount = this.unreadUnmutedCount
)

fun TdApi.UpdateUser.toDomain(): UpdateUser = UpdateUser(
    user = this.user.toDomain()
)

fun TdApi.UpdateUserFullInfo.toDomain(): UpdateUserFullInfo = UpdateUserFullInfo(
    userId = this.userId,
    userFullInfo = this.userFullInfo.toDomain()
)

fun TdApi.UpdateUserPrivacySettingRules.toDomain(): UpdateUserPrivacySettingRules = UpdateUserPrivacySettingRules(
    setting = this.setting.toDomain(),
    rules = this.rules.toDomain()
)

fun TdApi.UpdateUserStatus.toDomain(): UpdateUserStatus = UpdateUserStatus(
    userId = this.userId,
    status = this.status.toDomain()
)

fun TdApi.UpdateVideoPublished.toDomain(): UpdateVideoPublished = UpdateVideoPublished(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.UpdateWebAppMessageSent.toDomain(): UpdateWebAppMessageSent = UpdateWebAppMessageSent(
    webAppLaunchId = this.webAppLaunchId
)

fun TdApi.Updates.toDomain(): Updates = Updates(
    updates = this.updates.map { it.toDomain() }
)

