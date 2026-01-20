package com.xxcactussell.data.utils.mappers.updates

import com.xxcactussell.data.utils.mappers.accent.toData
import com.xxcactussell.data.utils.mappers.address.toData
import com.xxcactussell.data.utils.mappers.age.toData
import com.xxcactussell.data.utils.mappers.attachment.toData
import com.xxcactussell.data.utils.mappers.auth.toData
import com.xxcactussell.data.utils.mappers.autosave.toData
import com.xxcactussell.data.utils.mappers.background.toData
import com.xxcactussell.data.utils.mappers.basic.toData
import com.xxcactussell.data.utils.mappers.block.toData
import com.xxcactussell.data.utils.mappers.bots.toData
import com.xxcactussell.data.utils.mappers.business.toData
import com.xxcactussell.data.utils.mappers.calls.toData
import com.xxcactussell.data.utils.mappers.can.toData
import com.xxcactussell.data.utils.mappers.chat.toData
import com.xxcactussell.data.utils.mappers.connection.toData
import com.xxcactussell.data.utils.mappers.direct.toData
import com.xxcactussell.data.utils.mappers.downloaded.toData
import com.xxcactussell.data.utils.mappers.emoji.toData
import com.xxcactussell.data.utils.mappers.error.toData
import com.xxcactussell.data.utils.mappers.fact.toData
import com.xxcactussell.data.utils.mappers.file.toData
import com.xxcactussell.data.utils.mappers.forum.toData
import com.xxcactussell.data.utils.mappers.language.toData
import com.xxcactussell.data.utils.mappers.location.toData
import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.monetization.toData
import com.xxcactussell.data.utils.mappers.notification.toData
import com.xxcactussell.data.utils.mappers.option.toData
import com.xxcactussell.data.utils.mappers.paid.toData
import com.xxcactussell.data.utils.mappers.poll.toData
import com.xxcactussell.data.utils.mappers.profile.toData
import com.xxcactussell.data.utils.mappers.quick.toData
import com.xxcactussell.data.utils.mappers.reaction.toData
import com.xxcactussell.data.utils.mappers.saved.toData
import com.xxcactussell.data.utils.mappers.scope.toData
import com.xxcactussell.data.utils.mappers.secret.toData
import com.xxcactussell.data.utils.mappers.star.toData
import com.xxcactussell.data.utils.mappers.sticker.toData
import com.xxcactussell.data.utils.mappers.stories.toData
import com.xxcactussell.data.utils.mappers.suggested.toData
import com.xxcactussell.data.utils.mappers.supergroup.toData
import com.xxcactussell.data.utils.mappers.terms.toData
import com.xxcactussell.data.utils.mappers.ton.toData
import com.xxcactussell.data.utils.mappers.trending.toData
import com.xxcactussell.data.utils.mappers.unconfirmed.toData
import com.xxcactussell.data.utils.mappers.unread.toData
import com.xxcactussell.data.utils.mappers.user.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Update.toData(): TdApi.Update = when(this) {
    is UpdateAuthorizationState -> this.toData()
    is UpdateNewMessage -> this.toData()
    is UpdateMessageSendAcknowledged -> this.toData()
    is UpdateMessageSendSucceeded -> this.toData()
    is UpdateMessageSendFailed -> this.toData()
    is UpdateMessageContent -> this.toData()
    is UpdateMessageEdited -> this.toData()
    is UpdateMessageIsPinned -> this.toData()
    is UpdateMessageInteractionInfo -> this.toData()
    is UpdateMessageContentOpened -> this.toData()
    is UpdateMessageMentionRead -> this.toData()
    is UpdateMessageUnreadReactions -> this.toData()
    is UpdateMessageFactCheck -> this.toData()
    is UpdateMessageSuggestedPostInfo -> this.toData()
    is UpdateMessageLiveLocationViewed -> this.toData()
    is UpdateVideoPublished -> this.toData()
    is UpdateNewChat -> this.toData()
    is UpdateChatTitle -> this.toData()
    is UpdateChatPhoto -> this.toData()
    is UpdateChatAccentColors -> this.toData()
    is UpdateChatPermissions -> this.toData()
    is UpdateChatLastMessage -> this.toData()
    is UpdateChatPosition -> this.toData()
    is UpdateChatAddedToList -> this.toData()
    is UpdateChatRemovedFromList -> this.toData()
    is UpdateChatReadInbox -> this.toData()
    is UpdateChatReadOutbox -> this.toData()
    is UpdateChatActionBar -> this.toData()
    is UpdateChatBusinessBotManageBar -> this.toData()
    is UpdateChatAvailableReactions -> this.toData()
    is UpdateChatDraftMessage -> this.toData()
    is UpdateChatEmojiStatus -> this.toData()
    is UpdateChatMessageSender -> this.toData()
    is UpdateChatMessageAutoDeleteTime -> this.toData()
    is UpdateChatNotificationSettings -> this.toData()
    is UpdateChatPendingJoinRequests -> this.toData()
    is UpdateChatReplyMarkup -> this.toData()
    is UpdateChatBackground -> this.toData()
    is UpdateChatTheme -> this.toData()
    is UpdateChatUnreadMentionCount -> this.toData()
    is UpdateChatUnreadReactionCount -> this.toData()
    is UpdateChatVideoChat -> this.toData()
    is UpdateChatDefaultDisableNotification -> this.toData()
    is UpdateChatHasProtectedContent -> this.toData()
    is UpdateChatIsTranslatable -> this.toData()
    is UpdateChatIsMarkedAsUnread -> this.toData()
    is UpdateChatViewAsTopics -> this.toData()
    is UpdateChatBlockList -> this.toData()
    is UpdateChatHasScheduledMessages -> this.toData()
    is UpdateChatOnlineMemberCount -> this.toData()
    is UpdateSavedMessagesTopic -> this.toData()
    is UpdateSavedMessagesTopicCount -> this.toData()
    is UpdateDirectMessagesChatTopic -> this.toData()
    is UpdateTopicMessageCount -> this.toData()
    is UpdateQuickReplyShortcut -> this.toData()
    is UpdateQuickReplyShortcutDeleted -> this.toData()
    is UpdateQuickReplyShortcuts -> this.toData()
    is UpdateQuickReplyShortcutMessages -> this.toData()
    is UpdateForumTopicInfo -> this.toData()
    is UpdateForumTopic -> this.toData()
    is UpdateScopeNotificationSettings -> this.toData()
    is UpdateReactionNotificationSettings -> this.toData()
    is UpdateNotification -> this.toData()
    is UpdateNotificationGroup -> this.toData()
    is UpdateActiveNotifications -> this.toData()
    is UpdateHavePendingNotifications -> this.toData()
    is UpdateDeleteMessages -> this.toData()
    is UpdateChatAction -> this.toData()
    is UpdateUserStatus -> this.toData()
    is UpdateUser -> this.toData()
    is UpdateBasicGroup -> this.toData()
    is UpdateSupergroup -> this.toData()
    is UpdateSecretChat -> this.toData()
    is UpdateUserFullInfo -> this.toData()
    is UpdateBasicGroupFullInfo -> this.toData()
    is UpdateSupergroupFullInfo -> this.toData()
    is UpdateServiceNotification -> this.toData()
    is UpdateFile -> this.toData()
    is UpdateFileGenerationStart -> this.toData()
    is UpdateFileGenerationStop -> this.toData()
    is UpdateFileDownloads -> this.toData()
    is UpdateFileAddedToDownloads -> this.toData()
    is UpdateFileDownload -> this.toData()
    is UpdateFileRemovedFromDownloads -> this.toData()
    is UpdateApplicationVerificationRequired -> this.toData()
    is UpdateApplicationRecaptchaVerificationRequired -> this.toData()
    is UpdateCall -> this.toData()
    is UpdateGroupCall -> this.toData()
    is UpdateGroupCallParticipant -> this.toData()
    is UpdateGroupCallParticipants -> this.toData()
    is UpdateGroupCallVerificationState -> this.toData()
    is UpdateNewCallSignalingData -> this.toData()
    is UpdateUserPrivacySettingRules -> this.toData()
    is UpdateUnreadMessageCount -> this.toData()
    is UpdateUnreadChatCount -> this.toData()
    is UpdateStory -> this.toData()
    is UpdateStoryDeleted -> this.toData()
    is UpdateStoryPostSucceeded -> this.toData()
    is UpdateStoryPostFailed -> this.toData()
    is UpdateChatActiveStories -> this.toData()
    is UpdateStoryListChatCount -> this.toData()
    is UpdateStoryStealthMode -> this.toData()
    is UpdateOption -> this.toData()
    is UpdateStickerSet -> this.toData()
    is UpdateInstalledStickerSets -> this.toData()
    is UpdateTrendingStickerSets -> this.toData()
    is UpdateRecentStickers -> this.toData()
    is UpdateFavoriteStickers -> this.toData()
    is UpdateSavedAnimations -> this.toData()
    is UpdateSavedNotificationSounds -> this.toData()
    is UpdateDefaultBackground -> this.toData()
    is UpdateEmojiChatThemes -> this.toData()
    is UpdateAccentColors -> this.toData()
    is UpdateProfileAccentColors -> this.toData()
    is UpdateLanguagePackStrings -> this.toData()
    is UpdateConnectionState -> this.toData()
    is UpdateFreezeState -> this.toData()
    is UpdateAgeVerificationParameters -> this.toData()
    is UpdateTermsOfService -> this.toData()
    is UpdateUnconfirmedSession -> this.toData()
    is UpdateAttachmentMenuBots -> this.toData()
    is UpdateWebAppMessageSent -> this.toData()
    is UpdateActiveEmojiReactions -> this.toData()
    is UpdateAvailableMessageEffects -> this.toData()
    is UpdateDefaultReactionType -> this.toData()
    is UpdateDefaultPaidReactionType -> this.toData()
    is UpdateSavedMessagesTags -> this.toData()
    is UpdateActiveLiveLocationMessages -> this.toData()
    is UpdateOwnedStarCount -> this.toData()
    is UpdateOwnedTonCount -> this.toData()
    is UpdateChatRevenueAmount -> this.toData()
    is UpdateStarRevenueStatus -> this.toData()
    is UpdateTonRevenueStatus -> this.toData()
    is UpdateSpeechRecognitionTrial -> this.toData()
    is UpdateDiceEmojis -> this.toData()
    is UpdateAnimatedEmojiMessageClicked -> this.toData()
    is UpdateAnimationSearchParameters -> this.toData()
    is UpdateSuggestedActions -> this.toData()
    is UpdateSpeedLimitNotification -> this.toData()
    is UpdateContactCloseBirthdays -> this.toData()
    is UpdateAutosaveSettings -> this.toData()
    is UpdateBusinessConnection -> this.toData()
    is UpdateNewBusinessMessage -> this.toData()
    is UpdateBusinessMessageEdited -> this.toData()
    is UpdateBusinessMessagesDeleted -> this.toData()
    is UpdateNewInlineQuery -> this.toData()
    is UpdateNewChosenInlineResult -> this.toData()
    is UpdateNewCallbackQuery -> this.toData()
    is UpdateNewInlineCallbackQuery -> this.toData()
    is UpdateNewBusinessCallbackQuery -> this.toData()
    is UpdateNewShippingQuery -> this.toData()
    is UpdateNewPreCheckoutQuery -> this.toData()
    is UpdateNewCustomEvent -> this.toData()
    is UpdateNewCustomQuery -> this.toData()
    is UpdatePoll -> this.toData()
    is UpdatePollAnswer -> this.toData()
    is UpdateChatMember -> this.toData()
    is UpdateNewChatJoinRequest -> this.toData()
    is UpdateChatBoost -> this.toData()
    is UpdateMessageReaction -> this.toData()
    is UpdateMessageReactions -> this.toData()
    is UpdatePaidMediaPurchased -> this.toData()
    is UpdateChatFolders -> this.toData()
}

fun UpdateAccentColors.toData(): TdApi.UpdateAccentColors = TdApi.UpdateAccentColors(
    this.colors.map { it.toData() }.toTypedArray(),
    this.availableAccentColorIds
)

fun UpdateActiveEmojiReactions.toData(): TdApi.UpdateActiveEmojiReactions = TdApi.UpdateActiveEmojiReactions(
    this.emojis.toTypedArray()
)

fun UpdateActiveLiveLocationMessages.toData(): TdApi.UpdateActiveLiveLocationMessages = TdApi.UpdateActiveLiveLocationMessages(
    this.messages.map { it.toData() }.toTypedArray()
)

fun UpdateActiveNotifications.toData(): TdApi.UpdateActiveNotifications = TdApi.UpdateActiveNotifications(
    this.groups.map { it.toData() }.toTypedArray()
)

fun UpdateAgeVerificationParameters.toData(): TdApi.UpdateAgeVerificationParameters = TdApi.UpdateAgeVerificationParameters(
    this.parameters?.toData()
)

fun UpdateAnimatedEmojiMessageClicked.toData(): TdApi.UpdateAnimatedEmojiMessageClicked = TdApi.UpdateAnimatedEmojiMessageClicked(
    this.chatId,
    this.messageId,
    this.sticker.toData()
)

fun UpdateAnimationSearchParameters.toData(): TdApi.UpdateAnimationSearchParameters = TdApi.UpdateAnimationSearchParameters(
    this.provider,
    this.emojis.toTypedArray()
)

fun UpdateApplicationRecaptchaVerificationRequired.toData(): TdApi.UpdateApplicationRecaptchaVerificationRequired = TdApi.UpdateApplicationRecaptchaVerificationRequired(
    this.verificationId,
    this.action,
    this.recaptchaKeyId
)

fun UpdateApplicationVerificationRequired.toData(): TdApi.UpdateApplicationVerificationRequired = TdApi.UpdateApplicationVerificationRequired(
    this.verificationId,
    this.nonce,
    this.cloudProjectNumber
)

fun UpdateAttachmentMenuBots.toData(): TdApi.UpdateAttachmentMenuBots = TdApi.UpdateAttachmentMenuBots(
    this.bots.map { it.toData() }.toTypedArray()
)

fun UpdateAuthorizationState.toData(): TdApi.UpdateAuthorizationState = TdApi.UpdateAuthorizationState(
    this.authorizationState.toData()
)

fun UpdateAutosaveSettings.toData(): TdApi.UpdateAutosaveSettings = TdApi.UpdateAutosaveSettings(
    this.scope.toData(),
    this.settings?.toData()
)

fun UpdateAvailableMessageEffects.toData(): TdApi.UpdateAvailableMessageEffects = TdApi.UpdateAvailableMessageEffects(
    this.reactionEffectIds,
    this.stickerEffectIds
)

fun UpdateBasicGroup.toData(): TdApi.UpdateBasicGroup = TdApi.UpdateBasicGroup(
    this.basicGroup.toData()
)

fun UpdateBasicGroupFullInfo.toData(): TdApi.UpdateBasicGroupFullInfo = TdApi.UpdateBasicGroupFullInfo(
    this.basicGroupId,
    this.basicGroupFullInfo.toData()
)

fun UpdateBusinessConnection.toData(): TdApi.UpdateBusinessConnection = TdApi.UpdateBusinessConnection(
    this.connection.toData()
)

fun UpdateBusinessMessageEdited.toData(): TdApi.UpdateBusinessMessageEdited = TdApi.UpdateBusinessMessageEdited(
    this.connectionId,
    this.message.toData()
)

fun UpdateBusinessMessagesDeleted.toData(): TdApi.UpdateBusinessMessagesDeleted = TdApi.UpdateBusinessMessagesDeleted(
    this.connectionId,
    this.chatId,
    this.messageIds
)

fun UpdateCall.toData(): TdApi.UpdateCall = TdApi.UpdateCall(
    this.call.toData()
)

fun UpdateChatAccentColors.toData(): TdApi.UpdateChatAccentColors = TdApi.UpdateChatAccentColors(
    this.chatId,
    this.accentColorId,
    this.backgroundCustomEmojiId,
    this.profileAccentColorId,
    this.profileBackgroundCustomEmojiId
)

fun UpdateChatAction.toData(): TdApi.UpdateChatAction = TdApi.UpdateChatAction(
    this.chatId,
    this.messageThreadId,
    this.senderId.toData(),
    this.action.toData()
)

fun UpdateChatActionBar.toData(): TdApi.UpdateChatActionBar = TdApi.UpdateChatActionBar(
    this.chatId,
    this.actionBar?.toData()
)

fun UpdateChatActiveStories.toData(): TdApi.UpdateChatActiveStories = TdApi.UpdateChatActiveStories(
    this.activeStories.toData()
)

fun UpdateChatAddedToList.toData(): TdApi.UpdateChatAddedToList = TdApi.UpdateChatAddedToList(
    this.chatId,
    this.chatList.toData()
)

fun UpdateChatAvailableReactions.toData(): TdApi.UpdateChatAvailableReactions = TdApi.UpdateChatAvailableReactions(
    this.chatId,
    this.availableReactions.toData()
)

fun UpdateChatBackground.toData(): TdApi.UpdateChatBackground = TdApi.UpdateChatBackground(
    this.chatId,
    this.background?.toData()
)

fun UpdateChatBlockList.toData(): TdApi.UpdateChatBlockList = TdApi.UpdateChatBlockList(
    this.chatId,
    this.blockList?.toData()
)

fun UpdateChatBoost.toData(): TdApi.UpdateChatBoost = TdApi.UpdateChatBoost(
    this.chatId,
    this.boost.toData()
)

fun UpdateChatBusinessBotManageBar.toData(): TdApi.UpdateChatBusinessBotManageBar = TdApi.UpdateChatBusinessBotManageBar(
    this.chatId,
    this.businessBotManageBar?.toData()
)

fun UpdateChatDefaultDisableNotification.toData(): TdApi.UpdateChatDefaultDisableNotification = TdApi.UpdateChatDefaultDisableNotification(
    this.chatId,
    this.defaultDisableNotification
)

fun UpdateChatDraftMessage.toData(): TdApi.UpdateChatDraftMessage = TdApi.UpdateChatDraftMessage(
    this.chatId,
    this.draftMessage?.toData(),
    this.positions.map { it.toData() }.toTypedArray()
)

fun UpdateChatEmojiStatus.toData(): TdApi.UpdateChatEmojiStatus = TdApi.UpdateChatEmojiStatus(
    this.chatId,
    this.emojiStatus?.toData()
)

fun UpdateChatHasProtectedContent.toData(): TdApi.UpdateChatHasProtectedContent = TdApi.UpdateChatHasProtectedContent(
    this.chatId,
    this.hasProtectedContent
)

fun UpdateChatHasScheduledMessages.toData(): TdApi.UpdateChatHasScheduledMessages = TdApi.UpdateChatHasScheduledMessages(
    this.chatId,
    this.hasScheduledMessages
)

fun UpdateChatIsMarkedAsUnread.toData(): TdApi.UpdateChatIsMarkedAsUnread = TdApi.UpdateChatIsMarkedAsUnread(
    this.chatId,
    this.isMarkedAsUnread
)

fun UpdateChatIsTranslatable.toData(): TdApi.UpdateChatIsTranslatable = TdApi.UpdateChatIsTranslatable(
    this.chatId,
    this.isTranslatable
)

fun UpdateChatLastMessage.toData(): TdApi.UpdateChatLastMessage = TdApi.UpdateChatLastMessage(
    this.chatId,
    this.lastMessage?.toData(),
    this.positions.map { it.toData() }.toTypedArray()
)

fun UpdateChatMember.toData(): TdApi.UpdateChatMember = TdApi.UpdateChatMember(
    this.chatId,
    this.actorUserId,
    this.date,
    this.inviteLink?.toData(),
    this.viaJoinRequest,
    this.viaChatFolderInviteLink,
    this.oldChatMember.toData(),
    this.newChatMember.toData()
)

fun UpdateChatMessageAutoDeleteTime.toData(): TdApi.UpdateChatMessageAutoDeleteTime = TdApi.UpdateChatMessageAutoDeleteTime(
    this.chatId,
    this.messageAutoDeleteTime
)

fun UpdateChatMessageSender.toData(): TdApi.UpdateChatMessageSender = TdApi.UpdateChatMessageSender(
    this.chatId,
    this.messageSenderId?.toData()
)

fun UpdateChatNotificationSettings.toData(): TdApi.UpdateChatNotificationSettings = TdApi.UpdateChatNotificationSettings(
    this.chatId,
    this.notificationSettings.toData()
)

fun UpdateChatOnlineMemberCount.toData(): TdApi.UpdateChatOnlineMemberCount = TdApi.UpdateChatOnlineMemberCount(
    this.chatId,
    this.onlineMemberCount
)

fun UpdateChatPendingJoinRequests.toData(): TdApi.UpdateChatPendingJoinRequests = TdApi.UpdateChatPendingJoinRequests(
    this.chatId,
    this.pendingJoinRequests?.toData()
)

fun UpdateChatPermissions.toData(): TdApi.UpdateChatPermissions = TdApi.UpdateChatPermissions(
    this.chatId,
    this.permissions.toData()
)

fun UpdateChatPhoto.toData(): TdApi.UpdateChatPhoto = TdApi.UpdateChatPhoto(
    this.chatId,
    this.photo?.toData()
)

fun UpdateChatPosition.toData(): TdApi.UpdateChatPosition = TdApi.UpdateChatPosition(
    this.chatId,
    this.position.toData()
)

fun UpdateChatReadInbox.toData(): TdApi.UpdateChatReadInbox = TdApi.UpdateChatReadInbox(
    this.chatId,
    this.lastReadInboxMessageId,
    this.unreadCount
)

fun UpdateChatReadOutbox.toData(): TdApi.UpdateChatReadOutbox = TdApi.UpdateChatReadOutbox(
    this.chatId,
    this.lastReadOutboxMessageId
)

fun UpdateChatRemovedFromList.toData(): TdApi.UpdateChatRemovedFromList = TdApi.UpdateChatRemovedFromList(
    this.chatId,
    this.chatList.toData()
)

fun UpdateChatReplyMarkup.toData(): TdApi.UpdateChatReplyMarkup = TdApi.UpdateChatReplyMarkup(
    this.chatId,
    this.replyMarkupMessageId
)

fun UpdateChatRevenueAmount.toData(): TdApi.UpdateChatRevenueAmount = TdApi.UpdateChatRevenueAmount(
    this.chatId,
    this.revenueAmount.toData()
)

fun UpdateChatTheme.toData(): TdApi.UpdateChatTheme = TdApi.UpdateChatTheme(
    this.chatId,
    this.theme?.toData()
)

fun UpdateChatTitle.toData(): TdApi.UpdateChatTitle = TdApi.UpdateChatTitle(
    this.chatId,
    this.title
)

fun UpdateChatUnreadMentionCount.toData(): TdApi.UpdateChatUnreadMentionCount = TdApi.UpdateChatUnreadMentionCount(
    this.chatId,
    this.unreadMentionCount
)

fun UpdateChatUnreadReactionCount.toData(): TdApi.UpdateChatUnreadReactionCount = TdApi.UpdateChatUnreadReactionCount(
    this.chatId,
    this.unreadReactionCount
)

fun UpdateChatVideoChat.toData(): TdApi.UpdateChatVideoChat = TdApi.UpdateChatVideoChat(
    this.chatId,
    this.videoChat.toData()
)

fun UpdateChatViewAsTopics.toData(): TdApi.UpdateChatViewAsTopics = TdApi.UpdateChatViewAsTopics(
    this.chatId,
    this.viewAsTopics
)

fun UpdateConnectionState.toData(): TdApi.UpdateConnectionState = TdApi.UpdateConnectionState(
    this.state.toData()
)

fun UpdateContactCloseBirthdays.toData(): TdApi.UpdateContactCloseBirthdays = TdApi.UpdateContactCloseBirthdays(
    this.closeBirthdayUsers.map { it.toData() }.toTypedArray()
)

fun UpdateDefaultBackground.toData(): TdApi.UpdateDefaultBackground = TdApi.UpdateDefaultBackground(
    this.forDarkTheme,
    this.background?.toData()
)

fun UpdateDefaultPaidReactionType.toData(): TdApi.UpdateDefaultPaidReactionType = TdApi.UpdateDefaultPaidReactionType(
    this.type.toData()
)

fun UpdateDefaultReactionType.toData(): TdApi.UpdateDefaultReactionType = TdApi.UpdateDefaultReactionType(
    this.reactionType.toData()
)

fun UpdateDeleteMessages.toData(): TdApi.UpdateDeleteMessages = TdApi.UpdateDeleteMessages(
    this.chatId,
    this.messageIds,
    this.isPermanent,
    this.fromCache
)

fun UpdateDiceEmojis.toData(): TdApi.UpdateDiceEmojis = TdApi.UpdateDiceEmojis(
    this.emojis.toTypedArray()
)

fun UpdateDirectMessagesChatTopic.toData(): TdApi.UpdateDirectMessagesChatTopic = TdApi.UpdateDirectMessagesChatTopic(
    this.topic.toData()
)

fun UpdateEmojiChatThemes.toData(): TdApi.UpdateEmojiChatThemes = TdApi.UpdateEmojiChatThemes(
    this.chatThemes.map { it.toData() }.toTypedArray()
)

fun UpdateFavoriteStickers.toData(): TdApi.UpdateFavoriteStickers = TdApi.UpdateFavoriteStickers(
    this.stickerIds
)

fun UpdateFile.toData(): TdApi.UpdateFile = TdApi.UpdateFile(
    this.file.toData()
)

fun UpdateFileAddedToDownloads.toData(): TdApi.UpdateFileAddedToDownloads = TdApi.UpdateFileAddedToDownloads(
    this.fileDownload.toData(),
    this.counts.toData()
)

fun UpdateFileDownload.toData(): TdApi.UpdateFileDownload = TdApi.UpdateFileDownload(
    this.fileId,
    this.completeDate,
    this.isPaused,
    this.counts.toData()
)

fun UpdateFileDownloads.toData(): TdApi.UpdateFileDownloads = TdApi.UpdateFileDownloads(
    this.totalSize,
    this.totalCount,
    this.downloadedSize
)

fun UpdateFileGenerationStart.toData(): TdApi.UpdateFileGenerationStart = TdApi.UpdateFileGenerationStart(
    this.generationId,
    this.originalPath,
    this.destinationPath,
    this.conversion
)

fun UpdateFileGenerationStop.toData(): TdApi.UpdateFileGenerationStop = TdApi.UpdateFileGenerationStop(
    this.generationId
)

fun UpdateFileRemovedFromDownloads.toData(): TdApi.UpdateFileRemovedFromDownloads = TdApi.UpdateFileRemovedFromDownloads(
    this.fileId,
    this.counts.toData()
)

fun UpdateForumTopic.toData(): TdApi.UpdateForumTopic = TdApi.UpdateForumTopic(
    this.chatId,
    this.messageThreadId,
    this.isPinned,
    this.lastReadInboxMessageId,
    this.lastReadOutboxMessageId,
    this.unreadMentionCount,
    this.unreadReactionCount,
    this.notificationSettings.toData()
)

fun UpdateForumTopicInfo.toData(): TdApi.UpdateForumTopicInfo = TdApi.UpdateForumTopicInfo(
    this.info.toData()
)

fun UpdateFreezeState.toData(): TdApi.UpdateFreezeState = TdApi.UpdateFreezeState(
    this.isFrozen,
    this.freezingDate,
    this.deletionDate,
    this.appealLink
)

fun UpdateGroupCall.toData(): TdApi.UpdateGroupCall = TdApi.UpdateGroupCall(
    this.groupCall.toData()
)

fun UpdateGroupCallParticipant.toData(): TdApi.UpdateGroupCallParticipant = TdApi.UpdateGroupCallParticipant(
    this.groupCallId,
    this.participant.toData()
)

fun UpdateGroupCallParticipants.toData(): TdApi.UpdateGroupCallParticipants = TdApi.UpdateGroupCallParticipants(
    this.groupCallId,
    this.participantUserIds
)

fun UpdateGroupCallVerificationState.toData(): TdApi.UpdateGroupCallVerificationState = TdApi.UpdateGroupCallVerificationState(
    this.groupCallId,
    this.generation,
    this.emojis.toTypedArray()
)

fun UpdateHavePendingNotifications.toData(): TdApi.UpdateHavePendingNotifications = TdApi.UpdateHavePendingNotifications(
    this.haveDelayedNotifications,
    this.haveUnreceivedNotifications
)

fun UpdateInstalledStickerSets.toData(): TdApi.UpdateInstalledStickerSets = TdApi.UpdateInstalledStickerSets(
    this.stickerType.toData(),
    this.stickerSetIds
)

fun UpdateLanguagePackStrings.toData(): TdApi.UpdateLanguagePackStrings = TdApi.UpdateLanguagePackStrings(
    this.localizationTarget,
    this.languagePackId,
    this.strings.map { it.toData() }.toTypedArray()
)

fun UpdateMessageContent.toData(): TdApi.UpdateMessageContent = TdApi.UpdateMessageContent(
    this.chatId,
    this.messageId,
    this.newContent.toData()
)

fun UpdateMessageContentOpened.toData(): TdApi.UpdateMessageContentOpened = TdApi.UpdateMessageContentOpened(
    this.chatId,
    this.messageId
)

fun UpdateMessageEdited.toData(): TdApi.UpdateMessageEdited = TdApi.UpdateMessageEdited(
    this.chatId,
    this.messageId,
    this.editDate,
    this.replyMarkup?.toData()
)

fun UpdateMessageFactCheck.toData(): TdApi.UpdateMessageFactCheck = TdApi.UpdateMessageFactCheck(
    this.chatId,
    this.messageId,
    this.factCheck.toData()
)

fun UpdateMessageInteractionInfo.toData(): TdApi.UpdateMessageInteractionInfo = TdApi.UpdateMessageInteractionInfo(
    this.chatId,
    this.messageId,
    this.interactionInfo?.toData()
)

fun UpdateMessageIsPinned.toData(): TdApi.UpdateMessageIsPinned = TdApi.UpdateMessageIsPinned(
    this.chatId,
    this.messageId,
    this.isPinned
)

fun UpdateMessageLiveLocationViewed.toData(): TdApi.UpdateMessageLiveLocationViewed = TdApi.UpdateMessageLiveLocationViewed(
    this.chatId,
    this.messageId
)

fun UpdateMessageMentionRead.toData(): TdApi.UpdateMessageMentionRead = TdApi.UpdateMessageMentionRead(
    this.chatId,
    this.messageId,
    this.unreadMentionCount
)

fun UpdateMessageReaction.toData(): TdApi.UpdateMessageReaction = TdApi.UpdateMessageReaction(
    this.chatId,
    this.messageId,
    this.actorId.toData(),
    this.date,
    this.oldReactionTypes.map { it.toData() }.toTypedArray(),
    this.newReactionTypes.map { it.toData() }.toTypedArray()
)

fun UpdateMessageReactions.toData(): TdApi.UpdateMessageReactions = TdApi.UpdateMessageReactions(
    this.chatId,
    this.messageId,
    this.date,
    this.reactions.map { it.toData() }.toTypedArray()
)

fun UpdateMessageSendAcknowledged.toData(): TdApi.UpdateMessageSendAcknowledged = TdApi.UpdateMessageSendAcknowledged(
    this.chatId,
    this.messageId
)

fun UpdateMessageSendFailed.toData(): TdApi.UpdateMessageSendFailed = TdApi.UpdateMessageSendFailed(
    this.message.toData(),
    this.oldMessageId,
    this.error.toData()
)

fun UpdateMessageSendSucceeded.toData(): TdApi.UpdateMessageSendSucceeded = TdApi.UpdateMessageSendSucceeded(
    this.message.toData(),
    this.oldMessageId
)

fun UpdateMessageSuggestedPostInfo.toData(): TdApi.UpdateMessageSuggestedPostInfo = TdApi.UpdateMessageSuggestedPostInfo(
    this.chatId,
    this.messageId,
    this.suggestedPostInfo.toData()
)

fun UpdateMessageUnreadReactions.toData(): TdApi.UpdateMessageUnreadReactions = TdApi.UpdateMessageUnreadReactions(
    this.chatId,
    this.messageId,
    this.unreadReactions.map { it.toData() }.toTypedArray(),
    this.unreadReactionCount
)

fun UpdateNewBusinessCallbackQuery.toData(): TdApi.UpdateNewBusinessCallbackQuery = TdApi.UpdateNewBusinessCallbackQuery(
    this.id,
    this.senderUserId,
    this.connectionId,
    this.message.toData(),
    this.chatInstance,
    this.payload.toData()
)

fun UpdateNewBusinessMessage.toData(): TdApi.UpdateNewBusinessMessage = TdApi.UpdateNewBusinessMessage(
    this.connectionId,
    this.message.toData()
)

fun UpdateNewCallSignalingData.toData(): TdApi.UpdateNewCallSignalingData = TdApi.UpdateNewCallSignalingData(
    this.callId,
    this.data
)

fun UpdateNewCallbackQuery.toData(): TdApi.UpdateNewCallbackQuery = TdApi.UpdateNewCallbackQuery(
    this.id,
    this.senderUserId,
    this.chatId,
    this.messageId,
    this.chatInstance,
    this.payload.toData()
)

fun UpdateNewChat.toData(): TdApi.UpdateNewChat = TdApi.UpdateNewChat(
    this.chat.toData()
)

fun UpdateNewChatJoinRequest.toData(): TdApi.UpdateNewChatJoinRequest = TdApi.UpdateNewChatJoinRequest(
    this.chatId,
    this.request.toData(),
    this.userChatId,
    this.inviteLink?.toData()
)

fun UpdateNewChosenInlineResult.toData(): TdApi.UpdateNewChosenInlineResult = TdApi.UpdateNewChosenInlineResult(
    this.senderUserId,
    this.userLocation?.toData(),
    this.query,
    this.resultId,
    this.inlineMessageId
)

fun UpdateNewCustomEvent.toData(): TdApi.UpdateNewCustomEvent = TdApi.UpdateNewCustomEvent(
    this.event
)

fun UpdateNewCustomQuery.toData(): TdApi.UpdateNewCustomQuery = TdApi.UpdateNewCustomQuery(
    this.id,
    this.data,
    this.timeout
)

fun UpdateNewInlineCallbackQuery.toData(): TdApi.UpdateNewInlineCallbackQuery = TdApi.UpdateNewInlineCallbackQuery(
    this.id,
    this.senderUserId,
    this.inlineMessageId,
    this.chatInstance,
    this.payload.toData()
)

fun UpdateNewInlineQuery.toData(): TdApi.UpdateNewInlineQuery = TdApi.UpdateNewInlineQuery(
    this.id,
    this.senderUserId,
    this.userLocation?.toData(),
    this.chatType?.toData(),
    this.query,
    this.offset
)

fun UpdateNewMessage.toData(): TdApi.UpdateNewMessage = TdApi.UpdateNewMessage(
    this.message.toData()
)

fun UpdateNewPreCheckoutQuery.toData(): TdApi.UpdateNewPreCheckoutQuery = TdApi.UpdateNewPreCheckoutQuery(
    this.id,
    this.senderUserId,
    this.currency,
    this.totalAmount,
    this.invoicePayload,
    this.shippingOptionId,
    this.orderInfo?.toData()
)

fun UpdateNewShippingQuery.toData(): TdApi.UpdateNewShippingQuery = TdApi.UpdateNewShippingQuery(
    this.id,
    this.senderUserId,
    this.invoicePayload,
    this.shippingAddress.toData()
)

fun UpdateNotification.toData(): TdApi.UpdateNotification = TdApi.UpdateNotification(
    this.notificationGroupId,
    this.notification.toData()
)

fun UpdateNotificationGroup.toData(): TdApi.UpdateNotificationGroup = TdApi.UpdateNotificationGroup(
    this.notificationGroupId,
    this.type.toData(),
    this.chatId,
    this.notificationSettingsChatId,
    this.notificationSoundId,
    this.totalCount,
    this.addedNotifications.map { it.toData() }.toTypedArray(),
    this.removedNotificationIds
)

fun UpdateOption.toData(): TdApi.UpdateOption = TdApi.UpdateOption(
    this.name,
    this.value.toData()
)

fun UpdateOwnedStarCount.toData(): TdApi.UpdateOwnedStarCount = TdApi.UpdateOwnedStarCount(
    this.starAmount.toData()
)

fun UpdateOwnedTonCount.toData(): TdApi.UpdateOwnedTonCount = TdApi.UpdateOwnedTonCount(
    this.tonAmount
)

fun UpdatePaidMediaPurchased.toData(): TdApi.UpdatePaidMediaPurchased = TdApi.UpdatePaidMediaPurchased(
    this.userId,
    this.payload
)

fun UpdatePoll.toData(): TdApi.UpdatePoll = TdApi.UpdatePoll(
    this.poll.toData()
)

fun UpdatePollAnswer.toData(): TdApi.UpdatePollAnswer = TdApi.UpdatePollAnswer(
    this.pollId,
    this.voterId.toData(),
    this.optionIds
)

fun UpdateProfileAccentColors.toData(): TdApi.UpdateProfileAccentColors = TdApi.UpdateProfileAccentColors(
    this.colors.map { it.toData() }.toTypedArray(),
    this.availableAccentColorIds
)

fun UpdateQuickReplyShortcut.toData(): TdApi.UpdateQuickReplyShortcut = TdApi.UpdateQuickReplyShortcut(
    this.shortcut.toData()
)

fun UpdateQuickReplyShortcutDeleted.toData(): TdApi.UpdateQuickReplyShortcutDeleted = TdApi.UpdateQuickReplyShortcutDeleted(
    this.shortcutId
)

fun UpdateQuickReplyShortcutMessages.toData(): TdApi.UpdateQuickReplyShortcutMessages = TdApi.UpdateQuickReplyShortcutMessages(
    this.shortcutId,
    this.messages.map { it.toData() }.toTypedArray()
)

fun UpdateQuickReplyShortcuts.toData(): TdApi.UpdateQuickReplyShortcuts = TdApi.UpdateQuickReplyShortcuts(
    this.shortcutIds
)

fun UpdateReactionNotificationSettings.toData(): TdApi.UpdateReactionNotificationSettings = TdApi.UpdateReactionNotificationSettings(
    this.notificationSettings.toData()
)

fun UpdateRecentStickers.toData(): TdApi.UpdateRecentStickers = TdApi.UpdateRecentStickers(
    this.isAttached,
    this.stickerIds
)

fun UpdateSavedAnimations.toData(): TdApi.UpdateSavedAnimations = TdApi.UpdateSavedAnimations(
    this.animationIds
)

fun UpdateSavedMessagesTags.toData(): TdApi.UpdateSavedMessagesTags = TdApi.UpdateSavedMessagesTags(
    this.savedMessagesTopicId,
    this.tags.toData()
)

fun UpdateSavedMessagesTopic.toData(): TdApi.UpdateSavedMessagesTopic = TdApi.UpdateSavedMessagesTopic(
    this.topic.toData()
)

fun UpdateSavedMessagesTopicCount.toData(): TdApi.UpdateSavedMessagesTopicCount = TdApi.UpdateSavedMessagesTopicCount(
    this.topicCount
)

fun UpdateSavedNotificationSounds.toData(): TdApi.UpdateSavedNotificationSounds = TdApi.UpdateSavedNotificationSounds(
    this.notificationSoundIds
)

fun UpdateScopeNotificationSettings.toData(): TdApi.UpdateScopeNotificationSettings = TdApi.UpdateScopeNotificationSettings(
    this.scope.toData(),
    this.notificationSettings.toData()
)

fun UpdateSecretChat.toData(): TdApi.UpdateSecretChat = TdApi.UpdateSecretChat(
    this.secretChat.toData()
)

fun UpdateServiceNotification.toData(): TdApi.UpdateServiceNotification = TdApi.UpdateServiceNotification(
    this.type,
    this.content.toData()
)

fun UpdateSpeechRecognitionTrial.toData(): TdApi.UpdateSpeechRecognitionTrial = TdApi.UpdateSpeechRecognitionTrial(
    this.maxMediaDuration,
    this.weeklyCount,
    this.leftCount,
    this.nextResetDate
)

fun UpdateSpeedLimitNotification.toData(): TdApi.UpdateSpeedLimitNotification = TdApi.UpdateSpeedLimitNotification(
    this.isUpload
)

fun UpdateStarRevenueStatus.toData(): TdApi.UpdateStarRevenueStatus = TdApi.UpdateStarRevenueStatus(
    this.ownerId.toData(),
    this.status.toData()
)

fun UpdateStickerSet.toData(): TdApi.UpdateStickerSet = TdApi.UpdateStickerSet(
    this.stickerSet.toData()
)

fun UpdateStory.toData(): TdApi.UpdateStory = TdApi.UpdateStory(
    this.story.toData()
)

fun UpdateStoryDeleted.toData(): TdApi.UpdateStoryDeleted = TdApi.UpdateStoryDeleted(
    this.storyPosterChatId,
    this.storyId
)

fun UpdateStoryListChatCount.toData(): TdApi.UpdateStoryListChatCount = TdApi.UpdateStoryListChatCount(
    this.storyList.toData(),
    this.chatCount
)

fun UpdateStoryPostFailed.toData(): TdApi.UpdateStoryPostFailed = TdApi.UpdateStoryPostFailed(
    this.story.toData(),
    this.error.toData(),
    this.errorType?.toData()
)

fun UpdateStoryPostSucceeded.toData(): TdApi.UpdateStoryPostSucceeded = TdApi.UpdateStoryPostSucceeded(
    this.story.toData(),
    this.oldStoryId
)

fun UpdateStoryStealthMode.toData(): TdApi.UpdateStoryStealthMode = TdApi.UpdateStoryStealthMode(
    this.activeUntilDate,
    this.cooldownUntilDate
)

fun UpdateSuggestedActions.toData(): TdApi.UpdateSuggestedActions = TdApi.UpdateSuggestedActions(
    this.addedActions.map { it.toData() }.toTypedArray(),
    this.removedActions.map { it.toData() }.toTypedArray()
)

fun UpdateSupergroup.toData(): TdApi.UpdateSupergroup = TdApi.UpdateSupergroup(
    this.supergroup.toData()
)

fun UpdateSupergroupFullInfo.toData(): TdApi.UpdateSupergroupFullInfo = TdApi.UpdateSupergroupFullInfo(
    this.supergroupId,
    this.supergroupFullInfo.toData()
)

fun UpdateTermsOfService.toData(): TdApi.UpdateTermsOfService = TdApi.UpdateTermsOfService(
    this.termsOfServiceId,
    this.termsOfService.toData()
)

fun UpdateTonRevenueStatus.toData(): TdApi.UpdateTonRevenueStatus = TdApi.UpdateTonRevenueStatus(
    this.status.toData()
)

fun UpdateTopicMessageCount.toData(): TdApi.UpdateTopicMessageCount = TdApi.UpdateTopicMessageCount(
    this.chatId,
    this.topicId.toData(),
    this.messageCount
)

fun UpdateTrendingStickerSets.toData(): TdApi.UpdateTrendingStickerSets = TdApi.UpdateTrendingStickerSets(
    this.stickerType.toData(),
    this.stickerSets.toData()
)

fun UpdateUnconfirmedSession.toData(): TdApi.UpdateUnconfirmedSession = TdApi.UpdateUnconfirmedSession(
    this.session?.toData()
)

fun UpdateUnreadChatCount.toData(): TdApi.UpdateUnreadChatCount = TdApi.UpdateUnreadChatCount(
    this.chatList.toData(),
    this.totalCount,
    this.unreadCount,
    this.unreadUnmutedCount,
    this.markedAsUnreadCount,
    this.markedAsUnreadUnmutedCount
)

fun UpdateUnreadMessageCount.toData(): TdApi.UpdateUnreadMessageCount = TdApi.UpdateUnreadMessageCount(
    this.chatList.toData(),
    this.unreadCount,
    this.unreadUnmutedCount
)

fun UpdateUser.toData(): TdApi.UpdateUser = TdApi.UpdateUser(
    this.user.toData()
)

fun UpdateUserFullInfo.toData(): TdApi.UpdateUserFullInfo = TdApi.UpdateUserFullInfo(
    this.userId,
    this.userFullInfo.toData()
)

fun UpdateUserPrivacySettingRules.toData(): TdApi.UpdateUserPrivacySettingRules = TdApi.UpdateUserPrivacySettingRules(
    this.setting.toData(),
    this.rules.toData()
)

fun UpdateUserStatus.toData(): TdApi.UpdateUserStatus = TdApi.UpdateUserStatus(
    this.userId,
    this.status.toData()
)

fun UpdateVideoPublished.toData(): TdApi.UpdateVideoPublished = TdApi.UpdateVideoPublished(
    this.chatId,
    this.messageId
)

fun UpdateWebAppMessageSent.toData(): TdApi.UpdateWebAppMessageSent = TdApi.UpdateWebAppMessageSent(
    this.webAppLaunchId
)

fun Updates.toData(): TdApi.Updates = TdApi.Updates(
    this.updates.map { it.toData() }.toTypedArray()
)

