package com.xxcactussell.data.utils.mappers.chat

import com.xxcactussell.data.utils.mappers.account.toData
import com.xxcactussell.data.utils.mappers.animated.toData
import com.xxcactussell.data.utils.mappers.background.toData
import com.xxcactussell.data.utils.mappers.block.toData
import com.xxcactussell.data.utils.mappers.business.toData
import com.xxcactussell.data.utils.mappers.date.toData
import com.xxcactussell.data.utils.mappers.emoji.toData
import com.xxcactussell.data.utils.mappers.file.toData
import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.forum.toData
import com.xxcactussell.data.utils.mappers.input.toData
import com.xxcactussell.data.utils.mappers.invite.toData
import com.xxcactussell.data.utils.mappers.location.toData
import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.minithumbnail.toData
import com.xxcactussell.data.utils.mappers.monetization.toData
import com.xxcactussell.data.utils.mappers.notification.toData
import com.xxcactussell.data.utils.mappers.prepaid.toData
import com.xxcactussell.data.utils.mappers.reaction.toData
import com.xxcactussell.data.utils.mappers.revenue.toData
import com.xxcactussell.data.utils.mappers.search.toData
import com.xxcactussell.data.utils.mappers.star.toData
import com.xxcactussell.data.utils.mappers.statistical.toData
import com.xxcactussell.data.utils.mappers.stories.toData
import com.xxcactussell.data.utils.mappers.verification.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ArchiveChatListSettings.toData(): TdApi.ArchiveChatListSettings = TdApi.ArchiveChatListSettings(
    this.archiveAndMuteNewChatsFromUnknownUsers,
    this.keepUnmutedChatsArchived,
    this.keepChatsFromFoldersArchived
)

fun Chat.toData(): TdApi.Chat = TdApi.Chat(
    this.id,
    this.type.toData(),
    this.title,
    this.photo?.toData(),
    this.accentColorId,
    this.backgroundCustomEmojiId,
    this.profileAccentColorId,
    this.profileBackgroundCustomEmojiId,
    this.permissions.toData(),
    this.lastMessage?.toData(),
    this.positions.map { it.toData() }.toTypedArray(),
    this.chatLists.map { it.toData() }.toTypedArray(),
    this.messageSenderId?.toData(),
    this.blockList?.toData(),
    this.hasProtectedContent,
    this.isTranslatable,
    this.isMarkedAsUnread,
    this.viewAsTopics,
    this.hasScheduledMessages,
    this.canBeDeletedOnlyForSelf,
    this.canBeDeletedForAllUsers,
    this.canBeReported,
    this.defaultDisableNotification,
    this.unreadCount,
    this.lastReadInboxMessageId,
    this.lastReadOutboxMessageId,
    this.unreadMentionCount,
    this.unreadReactionCount,
    this.notificationSettings.toData(),
    this.availableReactions.toData(),
    this.messageAutoDeleteTime,
    this.emojiStatus?.toData(),
    this.background?.toData(),
    this.theme?.toData(),
    this.actionBar?.toData(),
    this.businessBotManageBar?.toData(),
    this.videoChat.toData(),
    this.pendingJoinRequests?.toData(),
    this.replyMarkupMessageId,
    this.draftMessage?.toData(),
    this.clientData
)

fun ChatAction.toData(): TdApi.ChatAction = when(this) {
    is ChatActionTyping -> this.toData()
    is ChatActionRecordingVideo -> this.toData()
    is ChatActionUploadingVideo -> this.toData()
    is ChatActionRecordingVoiceNote -> this.toData()
    is ChatActionUploadingVoiceNote -> this.toData()
    is ChatActionUploadingPhoto -> this.toData()
    is ChatActionUploadingDocument -> this.toData()
    is ChatActionChoosingSticker -> this.toData()
    is ChatActionChoosingLocation -> this.toData()
    is ChatActionChoosingContact -> this.toData()
    is ChatActionStartPlayingGame -> this.toData()
    is ChatActionRecordingVideoNote -> this.toData()
    is ChatActionUploadingVideoNote -> this.toData()
    is ChatActionWatchingAnimations -> this.toData()
    is ChatActionCancel -> this.toData()
}

fun ChatActionBar.toData(): TdApi.ChatActionBar = when(this) {
    is ChatActionBarReportSpam -> this.toData()
    is ChatActionBarInviteMembers -> this.toData()
    is ChatActionBarReportAddBlock -> this.toData()
    is ChatActionBarAddContact -> this.toData()
    is ChatActionBarSharePhoneNumber -> this.toData()
    is ChatActionBarJoinRequest -> this.toData()
}

fun ChatActionBarAddContact.toData(): TdApi.ChatActionBarAddContact = TdApi.ChatActionBarAddContact(
)

fun ChatActionBarInviteMembers.toData(): TdApi.ChatActionBarInviteMembers = TdApi.ChatActionBarInviteMembers(
)

fun ChatActionBarJoinRequest.toData(): TdApi.ChatActionBarJoinRequest = TdApi.ChatActionBarJoinRequest(
    this.title,
    this.isChannel,
    this.requestDate
)

fun ChatActionBarReportAddBlock.toData(): TdApi.ChatActionBarReportAddBlock = TdApi.ChatActionBarReportAddBlock(
    this.canUnarchive,
    this.accountInfo?.toData()
)

fun ChatActionBarReportSpam.toData(): TdApi.ChatActionBarReportSpam = TdApi.ChatActionBarReportSpam(
    this.canUnarchive
)

fun ChatActionBarSharePhoneNumber.toData(): TdApi.ChatActionBarSharePhoneNumber = TdApi.ChatActionBarSharePhoneNumber(
)

fun ChatActionCancel.toData(): TdApi.ChatActionCancel = TdApi.ChatActionCancel(
)

fun ChatActionChoosingContact.toData(): TdApi.ChatActionChoosingContact = TdApi.ChatActionChoosingContact(
)

fun ChatActionChoosingLocation.toData(): TdApi.ChatActionChoosingLocation = TdApi.ChatActionChoosingLocation(
)

fun ChatActionChoosingSticker.toData(): TdApi.ChatActionChoosingSticker = TdApi.ChatActionChoosingSticker(
)

fun ChatActionRecordingVideo.toData(): TdApi.ChatActionRecordingVideo = TdApi.ChatActionRecordingVideo(
)

fun ChatActionRecordingVideoNote.toData(): TdApi.ChatActionRecordingVideoNote = TdApi.ChatActionRecordingVideoNote(
)

fun ChatActionRecordingVoiceNote.toData(): TdApi.ChatActionRecordingVoiceNote = TdApi.ChatActionRecordingVoiceNote(
)

fun ChatActionStartPlayingGame.toData(): TdApi.ChatActionStartPlayingGame = TdApi.ChatActionStartPlayingGame(
)

fun ChatActionTyping.toData(): TdApi.ChatActionTyping = TdApi.ChatActionTyping(
)

fun ChatActionUploadingDocument.toData(): TdApi.ChatActionUploadingDocument = TdApi.ChatActionUploadingDocument(
    this.progress
)

fun ChatActionUploadingPhoto.toData(): TdApi.ChatActionUploadingPhoto = TdApi.ChatActionUploadingPhoto(
    this.progress
)

fun ChatActionUploadingVideo.toData(): TdApi.ChatActionUploadingVideo = TdApi.ChatActionUploadingVideo(
    this.progress
)

fun ChatActionUploadingVideoNote.toData(): TdApi.ChatActionUploadingVideoNote = TdApi.ChatActionUploadingVideoNote(
    this.progress
)

fun ChatActionUploadingVoiceNote.toData(): TdApi.ChatActionUploadingVoiceNote = TdApi.ChatActionUploadingVoiceNote(
    this.progress
)

fun ChatActionWatchingAnimations.toData(): TdApi.ChatActionWatchingAnimations = TdApi.ChatActionWatchingAnimations(
    this.emoji
)

fun ChatActiveStories.toData(): TdApi.ChatActiveStories = TdApi.ChatActiveStories(
    this.chatId,
    this.list?.toData(),
    this.order,
    this.canBeArchived,
    this.maxReadStoryId,
    this.stories.map { it.toData() }.toTypedArray()
)

fun ChatAdministrator.toData(): TdApi.ChatAdministrator = TdApi.ChatAdministrator(
    this.userId,
    this.customTitle,
    this.isOwner
)

fun ChatAdministratorRights.toData(): TdApi.ChatAdministratorRights = TdApi.ChatAdministratorRights(
    this.canManageChat,
    this.canChangeInfo,
    this.canPostMessages,
    this.canEditMessages,
    this.canDeleteMessages,
    this.canInviteUsers,
    this.canRestrictMembers,
    this.canPinMessages,
    this.canManageTopics,
    this.canPromoteMembers,
    this.canManageVideoChats,
    this.canPostStories,
    this.canEditStories,
    this.canDeleteStories,
    this.canManageDirectMessages,
    this.isAnonymous
)

fun ChatAdministrators.toData(): TdApi.ChatAdministrators = TdApi.ChatAdministrators(
    this.administrators.map { it.toData() }.toTypedArray()
)

fun ChatAvailableReactions.toData(): TdApi.ChatAvailableReactions = when(this) {
    is ChatAvailableReactionsAll -> this.toData()
    is ChatAvailableReactionsSome -> this.toData()
}

fun ChatAvailableReactionsAll.toData(): TdApi.ChatAvailableReactionsAll = TdApi.ChatAvailableReactionsAll(
    this.maxReactionCount
)

fun ChatAvailableReactionsSome.toData(): TdApi.ChatAvailableReactionsSome = TdApi.ChatAvailableReactionsSome(
    this.reactions.map { it.toData() }.toTypedArray(),
    this.maxReactionCount
)

fun ChatBackground.toData(): TdApi.ChatBackground = TdApi.ChatBackground(
    this.background.toData(),
    this.darkThemeDimming
)

fun ChatBoost.toData(): TdApi.ChatBoost = TdApi.ChatBoost(
    this.id,
    this.count,
    this.source.toData(),
    this.startDate,
    this.expirationDate
)

fun ChatBoostFeatures.toData(): TdApi.ChatBoostFeatures = TdApi.ChatBoostFeatures(
    this.features.map { it.toData() }.toTypedArray(),
    this.minProfileBackgroundCustomEmojiBoostLevel,
    this.minBackgroundCustomEmojiBoostLevel,
    this.minEmojiStatusBoostLevel,
    this.minChatThemeBackgroundBoostLevel,
    this.minCustomBackgroundBoostLevel,
    this.minCustomEmojiStickerSetBoostLevel,
    this.minAutomaticTranslationBoostLevel,
    this.minSpeechRecognitionBoostLevel,
    this.minSponsoredMessageDisableBoostLevel
)

fun ChatBoostLevelFeatures.toData(): TdApi.ChatBoostLevelFeatures = TdApi.ChatBoostLevelFeatures(
    this.level,
    this.storyPerDayCount,
    this.customEmojiReactionCount,
    this.titleColorCount,
    this.profileAccentColorCount,
    this.canSetProfileBackgroundCustomEmoji,
    this.accentColorCount,
    this.canSetBackgroundCustomEmoji,
    this.canSetEmojiStatus,
    this.chatThemeBackgroundCount,
    this.canSetCustomBackground,
    this.canSetCustomEmojiStickerSet,
    this.canEnableAutomaticTranslation,
    this.canRecognizeSpeech,
    this.canDisableSponsoredMessages
)

fun ChatBoostLink.toData(): TdApi.ChatBoostLink = TdApi.ChatBoostLink(
    this.link,
    this.isPublic
)

fun ChatBoostLinkInfo.toData(): TdApi.ChatBoostLinkInfo = TdApi.ChatBoostLinkInfo(
    this.isPublic,
    this.chatId
)

fun ChatBoostSlot.toData(): TdApi.ChatBoostSlot = TdApi.ChatBoostSlot(
    this.slotId,
    this.currentlyBoostedChatId,
    this.startDate,
    this.expirationDate,
    this.cooldownUntilDate
)

fun ChatBoostSlots.toData(): TdApi.ChatBoostSlots = TdApi.ChatBoostSlots(
    this.slots.map { it.toData() }.toTypedArray()
)

fun ChatBoostSource.toData(): TdApi.ChatBoostSource = when(this) {
    is ChatBoostSourceGiftCode -> this.toData()
    is ChatBoostSourceGiveaway -> this.toData()
    is ChatBoostSourcePremium -> this.toData()
}

fun ChatBoostSourceGiftCode.toData(): TdApi.ChatBoostSourceGiftCode = TdApi.ChatBoostSourceGiftCode(
    this.userId,
    this.giftCode
)

fun ChatBoostSourceGiveaway.toData(): TdApi.ChatBoostSourceGiveaway = TdApi.ChatBoostSourceGiveaway(
    this.userId,
    this.giftCode,
    this.starCount,
    this.giveawayMessageId,
    this.isUnclaimed
)

fun ChatBoostSourcePremium.toData(): TdApi.ChatBoostSourcePremium = TdApi.ChatBoostSourcePremium(
    this.userId
)

fun ChatBoostStatus.toData(): TdApi.ChatBoostStatus = TdApi.ChatBoostStatus(
    this.boostUrl,
    this.appliedSlotIds,
    this.level,
    this.giftCodeBoostCount,
    this.boostCount,
    this.currentLevelBoostCount,
    this.nextLevelBoostCount,
    this.premiumMemberCount,
    this.premiumMemberPercentage,
    this.prepaidGiveaways.map { it.toData() }.toTypedArray()
)

fun ChatEvent.toData(): TdApi.ChatEvent = TdApi.ChatEvent(
    this.id,
    this.date,
    this.memberId.toData(),
    this.action.toData()
)

fun ChatEventAccentColorChanged.toData(): TdApi.ChatEventAccentColorChanged = TdApi.ChatEventAccentColorChanged(
    this.oldAccentColorId,
    this.oldBackgroundCustomEmojiId,
    this.newAccentColorId,
    this.newBackgroundCustomEmojiId
)

fun ChatEventAction.toData(): TdApi.ChatEventAction = when(this) {
    is ChatEventMessageEdited -> this.toData()
    is ChatEventMessageDeleted -> this.toData()
    is ChatEventMessagePinned -> this.toData()
    is ChatEventMessageUnpinned -> this.toData()
    is ChatEventPollStopped -> this.toData()
    is ChatEventMemberJoined -> this.toData()
    is ChatEventMemberJoinedByInviteLink -> this.toData()
    is ChatEventMemberJoinedByRequest -> this.toData()
    is ChatEventMemberInvited -> this.toData()
    is ChatEventMemberLeft -> this.toData()
    is ChatEventMemberPromoted -> this.toData()
    is ChatEventMemberRestricted -> this.toData()
    is ChatEventMemberSubscriptionExtended -> this.toData()
    is ChatEventAvailableReactionsChanged -> this.toData()
    is ChatEventBackgroundChanged -> this.toData()
    is ChatEventDescriptionChanged -> this.toData()
    is ChatEventEmojiStatusChanged -> this.toData()
    is ChatEventLinkedChatChanged -> this.toData()
    is ChatEventLocationChanged -> this.toData()
    is ChatEventMessageAutoDeleteTimeChanged -> this.toData()
    is ChatEventPermissionsChanged -> this.toData()
    is ChatEventPhotoChanged -> this.toData()
    is ChatEventSlowModeDelayChanged -> this.toData()
    is ChatEventStickerSetChanged -> this.toData()
    is ChatEventCustomEmojiStickerSetChanged -> this.toData()
    is ChatEventTitleChanged -> this.toData()
    is ChatEventUsernameChanged -> this.toData()
    is ChatEventActiveUsernamesChanged -> this.toData()
    is ChatEventAccentColorChanged -> this.toData()
    is ChatEventProfileAccentColorChanged -> this.toData()
    is ChatEventHasProtectedContentToggled -> this.toData()
    is ChatEventInvitesToggled -> this.toData()
    is ChatEventIsAllHistoryAvailableToggled -> this.toData()
    is ChatEventHasAggressiveAntiSpamEnabledToggled -> this.toData()
    is ChatEventSignMessagesToggled -> this.toData()
    is ChatEventShowMessageSenderToggled -> this.toData()
    is ChatEventAutomaticTranslationToggled -> this.toData()
    is ChatEventInviteLinkEdited -> this.toData()
    is ChatEventInviteLinkRevoked -> this.toData()
    is ChatEventInviteLinkDeleted -> this.toData()
    is ChatEventVideoChatCreated -> this.toData()
    is ChatEventVideoChatEnded -> this.toData()
    is ChatEventVideoChatMuteNewParticipantsToggled -> this.toData()
    is ChatEventVideoChatParticipantIsMutedToggled -> this.toData()
    is ChatEventVideoChatParticipantVolumeLevelChanged -> this.toData()
    is ChatEventIsForumToggled -> this.toData()
    is ChatEventForumTopicCreated -> this.toData()
    is ChatEventForumTopicEdited -> this.toData()
    is ChatEventForumTopicToggleIsClosed -> this.toData()
    is ChatEventForumTopicToggleIsHidden -> this.toData()
    is ChatEventForumTopicDeleted -> this.toData()
    is ChatEventForumTopicPinned -> this.toData()
}

fun ChatEventActiveUsernamesChanged.toData(): TdApi.ChatEventActiveUsernamesChanged = TdApi.ChatEventActiveUsernamesChanged(
    this.oldUsernames.toTypedArray(),
    this.newUsernames.toTypedArray()
)

fun ChatEventAutomaticTranslationToggled.toData(): TdApi.ChatEventAutomaticTranslationToggled = TdApi.ChatEventAutomaticTranslationToggled(
    this.hasAutomaticTranslation
)

fun ChatEventAvailableReactionsChanged.toData(): TdApi.ChatEventAvailableReactionsChanged = TdApi.ChatEventAvailableReactionsChanged(
    this.oldAvailableReactions.toData(),
    this.newAvailableReactions.toData()
)

fun ChatEventBackgroundChanged.toData(): TdApi.ChatEventBackgroundChanged = TdApi.ChatEventBackgroundChanged(
    this.oldBackground?.toData(),
    this.newBackground?.toData()
)

fun ChatEventCustomEmojiStickerSetChanged.toData(): TdApi.ChatEventCustomEmojiStickerSetChanged = TdApi.ChatEventCustomEmojiStickerSetChanged(
    this.oldStickerSetId,
    this.newStickerSetId
)

fun ChatEventDescriptionChanged.toData(): TdApi.ChatEventDescriptionChanged = TdApi.ChatEventDescriptionChanged(
    this.oldDescription,
    this.newDescription
)

fun ChatEventEmojiStatusChanged.toData(): TdApi.ChatEventEmojiStatusChanged = TdApi.ChatEventEmojiStatusChanged(
    this.oldEmojiStatus?.toData(),
    this.newEmojiStatus?.toData()
)

fun ChatEventForumTopicCreated.toData(): TdApi.ChatEventForumTopicCreated = TdApi.ChatEventForumTopicCreated(
    this.topicInfo.toData()
)

fun ChatEventForumTopicDeleted.toData(): TdApi.ChatEventForumTopicDeleted = TdApi.ChatEventForumTopicDeleted(
    this.topicInfo.toData()
)

fun ChatEventForumTopicEdited.toData(): TdApi.ChatEventForumTopicEdited = TdApi.ChatEventForumTopicEdited(
    this.oldTopicInfo.toData(),
    this.newTopicInfo.toData()
)

fun ChatEventForumTopicPinned.toData(): TdApi.ChatEventForumTopicPinned = TdApi.ChatEventForumTopicPinned(
    this.oldTopicInfo?.toData(),
    this.newTopicInfo?.toData()
)

fun ChatEventForumTopicToggleIsClosed.toData(): TdApi.ChatEventForumTopicToggleIsClosed = TdApi.ChatEventForumTopicToggleIsClosed(
    this.topicInfo.toData()
)

fun ChatEventForumTopicToggleIsHidden.toData(): TdApi.ChatEventForumTopicToggleIsHidden = TdApi.ChatEventForumTopicToggleIsHidden(
    this.topicInfo.toData()
)

fun ChatEventHasAggressiveAntiSpamEnabledToggled.toData(): TdApi.ChatEventHasAggressiveAntiSpamEnabledToggled = TdApi.ChatEventHasAggressiveAntiSpamEnabledToggled(
    this.hasAggressiveAntiSpamEnabled
)

fun ChatEventHasProtectedContentToggled.toData(): TdApi.ChatEventHasProtectedContentToggled = TdApi.ChatEventHasProtectedContentToggled(
    this.hasProtectedContent
)

fun ChatEventInviteLinkDeleted.toData(): TdApi.ChatEventInviteLinkDeleted = TdApi.ChatEventInviteLinkDeleted(
    this.inviteLink.toData()
)

fun ChatEventInviteLinkEdited.toData(): TdApi.ChatEventInviteLinkEdited = TdApi.ChatEventInviteLinkEdited(
    this.oldInviteLink.toData(),
    this.newInviteLink.toData()
)

fun ChatEventInviteLinkRevoked.toData(): TdApi.ChatEventInviteLinkRevoked = TdApi.ChatEventInviteLinkRevoked(
    this.inviteLink.toData()
)

fun ChatEventInvitesToggled.toData(): TdApi.ChatEventInvitesToggled = TdApi.ChatEventInvitesToggled(
    this.canInviteUsers
)

fun ChatEventIsAllHistoryAvailableToggled.toData(): TdApi.ChatEventIsAllHistoryAvailableToggled = TdApi.ChatEventIsAllHistoryAvailableToggled(
    this.isAllHistoryAvailable
)

fun ChatEventIsForumToggled.toData(): TdApi.ChatEventIsForumToggled = TdApi.ChatEventIsForumToggled(
    this.isForum
)

fun ChatEventLinkedChatChanged.toData(): TdApi.ChatEventLinkedChatChanged = TdApi.ChatEventLinkedChatChanged(
    this.oldLinkedChatId,
    this.newLinkedChatId
)

fun ChatEventLocationChanged.toData(): TdApi.ChatEventLocationChanged = TdApi.ChatEventLocationChanged(
    this.oldLocation?.toData(),
    this.newLocation?.toData()
)

fun ChatEventLogFilters.toData(): TdApi.ChatEventLogFilters = TdApi.ChatEventLogFilters(
    this.messageEdits,
    this.messageDeletions,
    this.messagePins,
    this.memberJoins,
    this.memberLeaves,
    this.memberInvites,
    this.memberPromotions,
    this.memberRestrictions,
    this.infoChanges,
    this.settingChanges,
    this.inviteLinkChanges,
    this.videoChatChanges,
    this.forumChanges,
    this.subscriptionExtensions
)

fun ChatEventMemberInvited.toData(): TdApi.ChatEventMemberInvited = TdApi.ChatEventMemberInvited(
    this.userId,
    this.status.toData()
)

fun ChatEventMemberJoined.toData(): TdApi.ChatEventMemberJoined = TdApi.ChatEventMemberJoined(
)

fun ChatEventMemberJoinedByInviteLink.toData(): TdApi.ChatEventMemberJoinedByInviteLink = TdApi.ChatEventMemberJoinedByInviteLink(
    this.inviteLink.toData(),
    this.viaChatFolderInviteLink
)

fun ChatEventMemberJoinedByRequest.toData(): TdApi.ChatEventMemberJoinedByRequest = TdApi.ChatEventMemberJoinedByRequest(
    this.approverUserId,
    this.inviteLink?.toData()
)

fun ChatEventMemberLeft.toData(): TdApi.ChatEventMemberLeft = TdApi.ChatEventMemberLeft(
)

fun ChatEventMemberPromoted.toData(): TdApi.ChatEventMemberPromoted = TdApi.ChatEventMemberPromoted(
    this.userId,
    this.oldStatus.toData(),
    this.newStatus.toData()
)

fun ChatEventMemberRestricted.toData(): TdApi.ChatEventMemberRestricted = TdApi.ChatEventMemberRestricted(
    this.memberId.toData(),
    this.oldStatus.toData(),
    this.newStatus.toData()
)

fun ChatEventMemberSubscriptionExtended.toData(): TdApi.ChatEventMemberSubscriptionExtended = TdApi.ChatEventMemberSubscriptionExtended(
    this.userId,
    this.oldStatus.toData(),
    this.newStatus.toData()
)

fun ChatEventMessageAutoDeleteTimeChanged.toData(): TdApi.ChatEventMessageAutoDeleteTimeChanged = TdApi.ChatEventMessageAutoDeleteTimeChanged(
    this.oldMessageAutoDeleteTime,
    this.newMessageAutoDeleteTime
)

fun ChatEventMessageDeleted.toData(): TdApi.ChatEventMessageDeleted = TdApi.ChatEventMessageDeleted(
    this.message.toData(),
    this.canReportAntiSpamFalsePositive
)

fun ChatEventMessageEdited.toData(): TdApi.ChatEventMessageEdited = TdApi.ChatEventMessageEdited(
    this.oldMessage.toData(),
    this.newMessage.toData()
)

fun ChatEventMessagePinned.toData(): TdApi.ChatEventMessagePinned = TdApi.ChatEventMessagePinned(
    this.message.toData()
)

fun ChatEventMessageUnpinned.toData(): TdApi.ChatEventMessageUnpinned = TdApi.ChatEventMessageUnpinned(
    this.message.toData()
)

fun ChatEventPermissionsChanged.toData(): TdApi.ChatEventPermissionsChanged = TdApi.ChatEventPermissionsChanged(
    this.oldPermissions.toData(),
    this.newPermissions.toData()
)

fun ChatEventPhotoChanged.toData(): TdApi.ChatEventPhotoChanged = TdApi.ChatEventPhotoChanged(
    this.oldPhoto?.toData(),
    this.newPhoto?.toData()
)

fun ChatEventPollStopped.toData(): TdApi.ChatEventPollStopped = TdApi.ChatEventPollStopped(
    this.message.toData()
)

fun ChatEventProfileAccentColorChanged.toData(): TdApi.ChatEventProfileAccentColorChanged = TdApi.ChatEventProfileAccentColorChanged(
    this.oldProfileAccentColorId,
    this.oldProfileBackgroundCustomEmojiId,
    this.newProfileAccentColorId,
    this.newProfileBackgroundCustomEmojiId
)

fun ChatEventShowMessageSenderToggled.toData(): TdApi.ChatEventShowMessageSenderToggled = TdApi.ChatEventShowMessageSenderToggled(
    this.showMessageSender
)

fun ChatEventSignMessagesToggled.toData(): TdApi.ChatEventSignMessagesToggled = TdApi.ChatEventSignMessagesToggled(
    this.signMessages
)

fun ChatEventSlowModeDelayChanged.toData(): TdApi.ChatEventSlowModeDelayChanged = TdApi.ChatEventSlowModeDelayChanged(
    this.oldSlowModeDelay,
    this.newSlowModeDelay
)

fun ChatEventStickerSetChanged.toData(): TdApi.ChatEventStickerSetChanged = TdApi.ChatEventStickerSetChanged(
    this.oldStickerSetId,
    this.newStickerSetId
)

fun ChatEventTitleChanged.toData(): TdApi.ChatEventTitleChanged = TdApi.ChatEventTitleChanged(
    this.oldTitle,
    this.newTitle
)

fun ChatEventUsernameChanged.toData(): TdApi.ChatEventUsernameChanged = TdApi.ChatEventUsernameChanged(
    this.oldUsername,
    this.newUsername
)

fun ChatEventVideoChatCreated.toData(): TdApi.ChatEventVideoChatCreated = TdApi.ChatEventVideoChatCreated(
    this.groupCallId
)

fun ChatEventVideoChatEnded.toData(): TdApi.ChatEventVideoChatEnded = TdApi.ChatEventVideoChatEnded(
    this.groupCallId
)

fun ChatEventVideoChatMuteNewParticipantsToggled.toData(): TdApi.ChatEventVideoChatMuteNewParticipantsToggled = TdApi.ChatEventVideoChatMuteNewParticipantsToggled(
    this.muteNewParticipants
)

fun ChatEventVideoChatParticipantIsMutedToggled.toData(): TdApi.ChatEventVideoChatParticipantIsMutedToggled = TdApi.ChatEventVideoChatParticipantIsMutedToggled(
    this.participantId.toData(),
    this.isMuted
)

fun ChatEventVideoChatParticipantVolumeLevelChanged.toData(): TdApi.ChatEventVideoChatParticipantVolumeLevelChanged = TdApi.ChatEventVideoChatParticipantVolumeLevelChanged(
    this.participantId.toData(),
    this.volumeLevel
)

fun ChatEvents.toData(): TdApi.ChatEvents = TdApi.ChatEvents(
    this.events.map { it.toData() }.toTypedArray()
)

fun ChatFolder.toData(): TdApi.ChatFolder = TdApi.ChatFolder(
    this.name.toData(),
    this.icon?.toData(),
    this.colorId,
    this.isShareable,
    this.pinnedChatIds,
    this.includedChatIds,
    this.excludedChatIds,
    this.excludeMuted,
    this.excludeRead,
    this.excludeArchived,
    this.includeContacts,
    this.includeNonContacts,
    this.includeBots,
    this.includeGroups,
    this.includeChannels
)

fun ChatFolderIcon.toData(): TdApi.ChatFolderIcon = TdApi.ChatFolderIcon(
    this.name
)

fun ChatFolderInfo.toData(): TdApi.ChatFolderInfo = TdApi.ChatFolderInfo(
    this.id,
    this.name?.toData(),
    this.icon?.toData(),
    this.colorId ?: 0,
    this.isShareable == true,
    this.hasMyInviteLinks == true
)

fun ChatFolderInviteLink.toData(): TdApi.ChatFolderInviteLink = TdApi.ChatFolderInviteLink(
    this.inviteLink,
    this.name,
    this.chatIds
)

fun ChatFolderInviteLinkInfo.toData(): TdApi.ChatFolderInviteLinkInfo = TdApi.ChatFolderInviteLinkInfo(
    this.chatFolderInfo.toData(),
    this.missingChatIds,
    this.addedChatIds
)

fun ChatFolderInviteLinks.toData(): TdApi.ChatFolderInviteLinks = TdApi.ChatFolderInviteLinks(
    this.inviteLinks.map { it.toData() }.toTypedArray()
)

fun ChatFolderName.toData(): TdApi.ChatFolderName = TdApi.ChatFolderName(
    this.text.toData(),
    this.animateCustomEmoji
)

fun ChatInviteLink.toData(): TdApi.ChatInviteLink = TdApi.ChatInviteLink(
    this.inviteLink,
    this.name,
    this.creatorUserId,
    this.date,
    this.editDate,
    this.expirationDate,
    this.subscriptionPricing?.toData(),
    this.memberLimit,
    this.memberCount,
    this.expiredMemberCount,
    this.pendingJoinRequestCount,
    this.createsJoinRequest,
    this.isPrimary,
    this.isRevoked
)

fun ChatInviteLinkCount.toData(): TdApi.ChatInviteLinkCount = TdApi.ChatInviteLinkCount(
    this.userId,
    this.inviteLinkCount,
    this.revokedInviteLinkCount
)

fun ChatInviteLinkCounts.toData(): TdApi.ChatInviteLinkCounts = TdApi.ChatInviteLinkCounts(
    this.inviteLinkCounts.map { it.toData() }.toTypedArray()
)

fun ChatInviteLinkInfo.toData(): TdApi.ChatInviteLinkInfo = TdApi.ChatInviteLinkInfo(
    this.chatId,
    this.accessibleFor,
    this.type.toData(),
    this.title,
    this.photo?.toData(),
    this.accentColorId,
    this.description,
    this.memberCount,
    this.memberUserIds,
    this.subscriptionInfo?.toData(),
    this.createsJoinRequest,
    this.isPublic,
    this.verificationStatus?.toData()
)

fun ChatInviteLinkMember.toData(): TdApi.ChatInviteLinkMember = TdApi.ChatInviteLinkMember(
    this.userId,
    this.joinedChatDate,
    this.viaChatFolderInviteLink,
    this.approverUserId
)

fun ChatInviteLinkMembers.toData(): TdApi.ChatInviteLinkMembers = TdApi.ChatInviteLinkMembers(
    this.totalCount,
    this.members.map { it.toData() }.toTypedArray()
)

fun ChatInviteLinkSubscriptionInfo.toData(): TdApi.ChatInviteLinkSubscriptionInfo = TdApi.ChatInviteLinkSubscriptionInfo(
    this.pricing.toData(),
    this.canReuse,
    this.formId
)

fun ChatInviteLinks.toData(): TdApi.ChatInviteLinks = TdApi.ChatInviteLinks(
    this.totalCount,
    this.inviteLinks.map { it.toData() }.toTypedArray()
)

fun ChatJoinRequest.toData(): TdApi.ChatJoinRequest = TdApi.ChatJoinRequest(
    this.userId,
    this.date,
    this.bio
)

fun ChatJoinRequests.toData(): TdApi.ChatJoinRequests = TdApi.ChatJoinRequests(
    this.totalCount,
    this.requests.map { it.toData() }.toTypedArray()
)

fun ChatJoinRequestsInfo.toData(): TdApi.ChatJoinRequestsInfo = TdApi.ChatJoinRequestsInfo(
    this.totalCount,
    this.userIds
)

fun ChatList.toData(): TdApi.ChatList = when(this) {
    is ChatListMain -> this.toData()
    is ChatListArchive -> this.toData()
    is ChatListFolder -> this.toData()
}

fun ChatListArchive.toData(): TdApi.ChatListArchive = TdApi.ChatListArchive(
)

fun ChatListFolder.toData(): TdApi.ChatListFolder = TdApi.ChatListFolder(
    this.chatFolderId
)

fun ChatListMain.toData(): TdApi.ChatListMain = TdApi.ChatListMain(
)

fun ChatLists.toData(): TdApi.ChatLists = TdApi.ChatLists(
    this.chatLists.map { it.toData() }.toTypedArray()
)

fun ChatLocation.toData(): TdApi.ChatLocation = TdApi.ChatLocation(
    this.location.toData(),
    this.address
)

fun ChatMember.toData(): TdApi.ChatMember = TdApi.ChatMember(
    this.memberId.toData(),
    this.inviterUserId,
    this.joinedChatDate,
    this.status.toData()
)

fun ChatMemberStatus.toData(): TdApi.ChatMemberStatus = when(this) {
    is ChatMemberStatusCreator -> this.toData()
    is ChatMemberStatusAdministrator -> this.toData()
    is ChatMemberStatusMember -> this.toData()
    is ChatMemberStatusRestricted -> this.toData()
    is ChatMemberStatusLeft -> this.toData()
    is ChatMemberStatusBanned -> this.toData()
}

fun ChatMemberStatusAdministrator.toData(): TdApi.ChatMemberStatusAdministrator = TdApi.ChatMemberStatusAdministrator(
    this.customTitle,
    this.canBeEdited,
    this.rights.toData()
)

fun ChatMemberStatusBanned.toData(): TdApi.ChatMemberStatusBanned = TdApi.ChatMemberStatusBanned(
    this.bannedUntilDate
)

fun ChatMemberStatusCreator.toData(): TdApi.ChatMemberStatusCreator = TdApi.ChatMemberStatusCreator(
    this.customTitle,
    this.isAnonymous,
    this.isMember
)

fun ChatMemberStatusLeft.toData(): TdApi.ChatMemberStatusLeft = TdApi.ChatMemberStatusLeft(
)

fun ChatMemberStatusMember.toData(): TdApi.ChatMemberStatusMember = TdApi.ChatMemberStatusMember(
    this.memberUntilDate
)

fun ChatMemberStatusRestricted.toData(): TdApi.ChatMemberStatusRestricted = TdApi.ChatMemberStatusRestricted(
    this.isMember,
    this.restrictedUntilDate,
    this.permissions.toData()
)

fun ChatMembers.toData(): TdApi.ChatMembers = TdApi.ChatMembers(
    this.totalCount,
    this.members.map { it.toData() }.toTypedArray()
)

fun ChatMembersFilter.toData(): TdApi.ChatMembersFilter = when(this) {
    is ChatMembersFilterContacts -> this.toData()
    is ChatMembersFilterAdministrators -> this.toData()
    is ChatMembersFilterMembers -> this.toData()
    is ChatMembersFilterMention -> this.toData()
    is ChatMembersFilterRestricted -> this.toData()
    is ChatMembersFilterBanned -> this.toData()
    is ChatMembersFilterBots -> this.toData()
}

fun ChatMembersFilterAdministrators.toData(): TdApi.ChatMembersFilterAdministrators = TdApi.ChatMembersFilterAdministrators(
)

fun ChatMembersFilterBanned.toData(): TdApi.ChatMembersFilterBanned = TdApi.ChatMembersFilterBanned(
)

fun ChatMembersFilterBots.toData(): TdApi.ChatMembersFilterBots = TdApi.ChatMembersFilterBots(
)

fun ChatMembersFilterContacts.toData(): TdApi.ChatMembersFilterContacts = TdApi.ChatMembersFilterContacts(
)

fun ChatMembersFilterMembers.toData(): TdApi.ChatMembersFilterMembers = TdApi.ChatMembersFilterMembers(
)

fun ChatMembersFilterMention.toData(): TdApi.ChatMembersFilterMention = TdApi.ChatMembersFilterMention(
    this.messageThreadId
)

fun ChatMembersFilterRestricted.toData(): TdApi.ChatMembersFilterRestricted = TdApi.ChatMembersFilterRestricted(
)

fun ChatMessageSender.toData(): TdApi.ChatMessageSender = TdApi.ChatMessageSender(
    this.sender.toData(),
    this.needsPremium
)

fun ChatMessageSenders.toData(): TdApi.ChatMessageSenders = TdApi.ChatMessageSenders(
    this.senders.map { it.toData() }.toTypedArray()
)

fun ChatNotificationSettings.toData(): TdApi.ChatNotificationSettings = TdApi.ChatNotificationSettings(
    this.useDefaultMuteFor,
    this.muteFor,
    this.useDefaultSound,
    this.soundId,
    this.useDefaultShowPreview,
    this.showPreview,
    this.useDefaultMuteStories,
    this.muteStories,
    this.useDefaultStorySound,
    this.storySoundId,
    this.useDefaultShowStoryPoster,
    this.showStoryPoster,
    this.useDefaultDisablePinnedMessageNotifications,
    this.disablePinnedMessageNotifications,
    this.useDefaultDisableMentionNotifications,
    this.disableMentionNotifications
)

fun ChatPermissions.toData(): TdApi.ChatPermissions = TdApi.ChatPermissions(
    this.canSendBasicMessages,
    this.canSendAudios,
    this.canSendDocuments,
    this.canSendPhotos,
    this.canSendVideos,
    this.canSendVideoNotes,
    this.canSendVoiceNotes,
    this.canSendPolls,
    this.canSendOtherMessages,
    this.canAddLinkPreviews,
    this.canChangeInfo,
    this.canInviteUsers,
    this.canPinMessages,
    this.canCreateTopics
)

fun ChatPhoto.toData(): TdApi.ChatPhoto = TdApi.ChatPhoto(
    this.id,
    this.addedDate,
    this.minithumbnail?.toData(),
    this.sizes.map { it.toData() }.toTypedArray(),
    this.animation?.toData(),
    this.smallAnimation?.toData(),
    this.sticker?.toData()
)

fun ChatPhotoInfo.toData(): TdApi.ChatPhotoInfo = TdApi.ChatPhotoInfo(
    this.small.toData(),
    this.big.toData(),
    this.minithumbnail?.toData(),
    this.hasAnimation,
    this.isPersonal
)

fun ChatPhotoSticker.toData(): TdApi.ChatPhotoSticker = TdApi.ChatPhotoSticker(
    this.type.toData(),
    this.backgroundFill.toData()
)

fun ChatPhotoStickerType.toData(): TdApi.ChatPhotoStickerType = when(this) {
    is ChatPhotoStickerTypeRegularOrMask -> this.toData()
    is ChatPhotoStickerTypeCustomEmoji -> this.toData()
}

fun ChatPhotoStickerTypeCustomEmoji.toData(): TdApi.ChatPhotoStickerTypeCustomEmoji = TdApi.ChatPhotoStickerTypeCustomEmoji(
    this.customEmojiId
)

fun ChatPhotoStickerTypeRegularOrMask.toData(): TdApi.ChatPhotoStickerTypeRegularOrMask = TdApi.ChatPhotoStickerTypeRegularOrMask(
    this.stickerSetId,
    this.stickerId
)

fun ChatPhotos.toData(): TdApi.ChatPhotos = TdApi.ChatPhotos(
    this.totalCount,
    this.photos.map { it.toData() }.toTypedArray()
)

fun ChatPosition.toData(): TdApi.ChatPosition = TdApi.ChatPosition(
    this.list.toData(),
    this.order,
    this.isPinned,
    this.source?.toData()
)

fun ChatRevenueAmount.toData(): TdApi.ChatRevenueAmount = TdApi.ChatRevenueAmount(
    this.cryptocurrency,
    this.totalAmount,
    this.balanceAmount,
    this.availableAmount,
    this.withdrawalEnabled
)

fun ChatRevenueStatistics.toData(): TdApi.ChatRevenueStatistics = TdApi.ChatRevenueStatistics(
    this.revenueByHourGraph.toData(),
    this.revenueGraph.toData(),
    this.revenueAmount.toData(),
    this.usdRate
)

fun ChatRevenueTransaction.toData(): TdApi.ChatRevenueTransaction = TdApi.ChatRevenueTransaction(
    this.cryptocurrency,
    this.cryptocurrencyAmount,
    this.type.toData()
)

fun ChatRevenueTransactionType.toData(): TdApi.ChatRevenueTransactionType = when(this) {
    is ChatRevenueTransactionTypeUnsupported -> this.toData()
    is ChatRevenueTransactionTypeSponsoredMessageEarnings -> this.toData()
    is ChatRevenueTransactionTypeSuggestedPostEarnings -> this.toData()
    is ChatRevenueTransactionTypeFragmentWithdrawal -> this.toData()
    is ChatRevenueTransactionTypeFragmentRefund -> this.toData()
}

fun ChatRevenueTransactionTypeFragmentRefund.toData(): TdApi.ChatRevenueTransactionTypeFragmentRefund = TdApi.ChatRevenueTransactionTypeFragmentRefund(
    this.refundDate
)

fun ChatRevenueTransactionTypeFragmentWithdrawal.toData(): TdApi.ChatRevenueTransactionTypeFragmentWithdrawal = TdApi.ChatRevenueTransactionTypeFragmentWithdrawal(
    this.withdrawalDate,
    this.state.toData()
)

fun ChatRevenueTransactionTypeSponsoredMessageEarnings.toData(): TdApi.ChatRevenueTransactionTypeSponsoredMessageEarnings = TdApi.ChatRevenueTransactionTypeSponsoredMessageEarnings(
    this.startDate,
    this.endDate
)

fun ChatRevenueTransactionTypeSuggestedPostEarnings.toData(): TdApi.ChatRevenueTransactionTypeSuggestedPostEarnings = TdApi.ChatRevenueTransactionTypeSuggestedPostEarnings(
    this.userId
)

fun ChatRevenueTransactionTypeUnsupported.toData(): TdApi.ChatRevenueTransactionTypeUnsupported = TdApi.ChatRevenueTransactionTypeUnsupported(
)

fun ChatRevenueTransactions.toData(): TdApi.ChatRevenueTransactions = TdApi.ChatRevenueTransactions(
    this.tonAmount,
    this.transactions.map { it.toData() }.toTypedArray(),
    this.nextOffset
)

fun ChatSource.toData(): TdApi.ChatSource = when(this) {
    is ChatSourceMtprotoProxy -> this.toData()
    is ChatSourcePublicServiceAnnouncement -> this.toData()
}

fun ChatSourceMtprotoProxy.toData(): TdApi.ChatSourceMtprotoProxy = TdApi.ChatSourceMtprotoProxy(
)

fun ChatSourcePublicServiceAnnouncement.toData(): TdApi.ChatSourcePublicServiceAnnouncement = TdApi.ChatSourcePublicServiceAnnouncement(
    this.type,
    this.text
)

fun ChatStatistics.toData(): TdApi.ChatStatistics = when(this) {
    is ChatStatisticsSupergroup -> this.toData()
    is ChatStatisticsChannel -> this.toData()
}

fun ChatStatisticsAdministratorActionsInfo.toData(): TdApi.ChatStatisticsAdministratorActionsInfo = TdApi.ChatStatisticsAdministratorActionsInfo(
    this.userId,
    this.deletedMessageCount,
    this.bannedUserCount,
    this.restrictedUserCount
)

fun ChatStatisticsChannel.toData(): TdApi.ChatStatisticsChannel = TdApi.ChatStatisticsChannel(
    this.period.toData(),
    this.memberCount.toData(),
    this.meanMessageViewCount.toData(),
    this.meanMessageShareCount.toData(),
    this.meanMessageReactionCount.toData(),
    this.meanStoryViewCount.toData(),
    this.meanStoryShareCount.toData(),
    this.meanStoryReactionCount.toData(),
    this.enabledNotificationsPercentage,
    this.memberCountGraph.toData(),
    this.joinGraph.toData(),
    this.muteGraph.toData(),
    this.viewCountByHourGraph.toData(),
    this.viewCountBySourceGraph.toData(),
    this.joinBySourceGraph.toData(),
    this.languageGraph.toData(),
    this.messageInteractionGraph.toData(),
    this.messageReactionGraph.toData(),
    this.storyInteractionGraph.toData(),
    this.storyReactionGraph.toData(),
    this.instantViewInteractionGraph.toData(),
    this.recentInteractions.map { it.toData() }.toTypedArray()
)

fun ChatStatisticsInteractionInfo.toData(): TdApi.ChatStatisticsInteractionInfo = TdApi.ChatStatisticsInteractionInfo(
    this.objectType.toData(),
    this.viewCount,
    this.forwardCount,
    this.reactionCount
)

fun ChatStatisticsInviterInfo.toData(): TdApi.ChatStatisticsInviterInfo = TdApi.ChatStatisticsInviterInfo(
    this.userId,
    this.addedMemberCount
)

fun ChatStatisticsMessageSenderInfo.toData(): TdApi.ChatStatisticsMessageSenderInfo = TdApi.ChatStatisticsMessageSenderInfo(
    this.userId,
    this.sentMessageCount,
    this.averageCharacterCount
)

fun ChatStatisticsObjectType.toData(): TdApi.ChatStatisticsObjectType = when(this) {
    is ChatStatisticsObjectTypeMessage -> this.toData()
    is ChatStatisticsObjectTypeStory -> this.toData()
}

fun ChatStatisticsObjectTypeMessage.toData(): TdApi.ChatStatisticsObjectTypeMessage = TdApi.ChatStatisticsObjectTypeMessage(
    this.messageId
)

fun ChatStatisticsObjectTypeStory.toData(): TdApi.ChatStatisticsObjectTypeStory = TdApi.ChatStatisticsObjectTypeStory(
    this.storyId
)

fun ChatStatisticsSupergroup.toData(): TdApi.ChatStatisticsSupergroup = TdApi.ChatStatisticsSupergroup(
    this.period.toData(),
    this.memberCount.toData(),
    this.messageCount.toData(),
    this.viewerCount.toData(),
    this.senderCount.toData(),
    this.memberCountGraph.toData(),
    this.joinGraph.toData(),
    this.joinBySourceGraph.toData(),
    this.languageGraph.toData(),
    this.messageContentGraph.toData(),
    this.actionGraph.toData(),
    this.dayGraph.toData(),
    this.weekGraph.toData(),
    this.topSenders.map { it.toData() }.toTypedArray(),
    this.topAdministrators.map { it.toData() }.toTypedArray(),
    this.topInviters.map { it.toData() }.toTypedArray()
)

fun ChatTheme.toData(): TdApi.ChatTheme = when(this) {
    is ChatThemeEmoji -> this.toData()
    is ChatThemeGift -> this.toData()
}

fun ChatThemeEmoji.toData(): TdApi.ChatThemeEmoji = TdApi.ChatThemeEmoji(
    this.name
)

fun ChatThemeGift.toData(): TdApi.ChatThemeGift = TdApi.ChatThemeGift(
    this.giftTheme.toData()
)

fun ChatType.toData(): TdApi.ChatType = when(this) {
    is ChatTypePrivate -> this.toData()
    is ChatTypeBasicGroup -> this.toData()
    is ChatTypeSupergroup -> this.toData()
    is ChatTypeSecret -> this.toData()
}

fun ChatTypeBasicGroup.toData(): TdApi.ChatTypeBasicGroup = TdApi.ChatTypeBasicGroup(
    this.basicGroupId
)

fun ChatTypePrivate.toData(): TdApi.ChatTypePrivate = TdApi.ChatTypePrivate(
    this.userId
)

fun ChatTypeSecret.toData(): TdApi.ChatTypeSecret = TdApi.ChatTypeSecret(
    this.secretChatId,
    this.userId
)

fun ChatTypeSupergroup.toData(): TdApi.ChatTypeSupergroup = TdApi.ChatTypeSupergroup(
    this.supergroupId,
    this.isChannel
)

fun Chats.toData(): TdApi.Chats = TdApi.Chats(
    this.totalCount,
    this.chatIds
)

fun CheckChatFolderInviteLink.toData(): TdApi.CheckChatFolderInviteLink = TdApi.CheckChatFolderInviteLink(
    this.inviteLink
)

fun CreateChatFolder.toData(): TdApi.CreateChatFolder = TdApi.CreateChatFolder(
    this.folder.toData()
)

fun CreateChatFolderInviteLink.toData(): TdApi.CreateChatFolderInviteLink = TdApi.CreateChatFolderInviteLink(
    this.chatFolderId,
    this.name,
    this.chatIds
)

fun CreateForumTopic.toData(): TdApi.CreateForumTopic = TdApi.CreateForumTopic(
    this.chatId,
    this.name,
    this.icon.toData()
)

fun CreateNewBasicGroupChat.toData(): TdApi.CreateNewBasicGroupChat = TdApi.CreateNewBasicGroupChat(
    this.userIds,
    this.title,
    this.messageAutoDeleteTime
)

fun CreateNewSupergroupChat.toData(): TdApi.CreateNewSupergroupChat = TdApi.CreateNewSupergroupChat(
    this.title,
    this.isForum,
    this.isChannel,
    this.description,
    this.location.toData(),
    this.messageAutoDeleteTime,
    this.forImport
)

fun CreatePrivateChat.toData(): TdApi.CreatePrivateChat = TdApi.CreatePrivateChat(
    this.userId,
    this.force
)

fun CreateSecretChat.toData(): TdApi.CreateSecretChat = TdApi.CreateSecretChat(
    this.secretChatId
)

fun DeleteChat.toData(): TdApi.DeleteChat = TdApi.DeleteChat(
    this.chatId
)

fun DeleteChatBackground.toData(): TdApi.DeleteChatBackground = TdApi.DeleteChatBackground(
    this.chatId,
    this.restorePrevious
)

fun DeleteChatFolder.toData(): TdApi.DeleteChatFolder = TdApi.DeleteChatFolder(
    this.chatFolderId,
    this.leaveChatIds
)

fun DeleteChatFolderInviteLink.toData(): TdApi.DeleteChatFolderInviteLink = TdApi.DeleteChatFolderInviteLink(
    this.chatFolderId,
    this.inviteLink
)

fun DeleteChatHistory.toData(): TdApi.DeleteChatHistory = TdApi.DeleteChatHistory(
    this.chatId,
    this.removeFromChatList,
    this.revoke
)

fun DeleteChatMessagesBySender.toData(): TdApi.DeleteChatMessagesBySender = TdApi.DeleteChatMessagesBySender(
    this.chatId,
    this.senderId.toData()
)

fun DeleteChatReplyMarkup.toData(): TdApi.DeleteChatReplyMarkup = TdApi.DeleteChatReplyMarkup(
    this.chatId,
    this.messageId
)

fun DeleteForumTopic.toData(): TdApi.DeleteForumTopic = TdApi.DeleteForumTopic(
    this.chatId,
    this.messageThreadId
)

fun EditChatFolderInviteLink.toData(): TdApi.EditChatFolderInviteLink = TdApi.EditChatFolderInviteLink(
    this.chatFolderId,
    this.inviteLink,
    this.name,
    this.chatIds
)

fun EditForumTopic.toData(): TdApi.EditForumTopic = TdApi.EditForumTopic(
    this.chatId,
    this.messageThreadId,
    this.name,
    this.editIconCustomEmoji,
    this.iconCustomEmojiId
)

fun GetChat.toData(): TdApi.GetChat = TdApi.GetChat(
    this.chatId
)

fun GetChatActiveStories.toData(): TdApi.GetChatActiveStories = TdApi.GetChatActiveStories(
    this.chatId
)

fun GetChatAdministrators.toData(): TdApi.GetChatAdministrators = TdApi.GetChatAdministrators(
    this.chatId
)

fun GetChatArchivedStories.toData(): TdApi.GetChatArchivedStories = TdApi.GetChatArchivedStories(
    this.chatId,
    this.fromStoryId,
    this.limit
)

fun GetChatAvailableMessageSenders.toData(): TdApi.GetChatAvailableMessageSenders = TdApi.GetChatAvailableMessageSenders(
    this.chatId
)

fun GetChatAvailablePaidMessageReactionSenders.toData(): TdApi.GetChatAvailablePaidMessageReactionSenders = TdApi.GetChatAvailablePaidMessageReactionSenders(
    this.chatId
)

fun GetChatBoostFeatures.toData(): TdApi.GetChatBoostFeatures = TdApi.GetChatBoostFeatures(
    this.isChannel
)

fun GetChatBoostLevelFeatures.toData(): TdApi.GetChatBoostLevelFeatures = TdApi.GetChatBoostLevelFeatures(
    this.isChannel,
    this.level
)

fun GetChatBoostLink.toData(): TdApi.GetChatBoostLink = TdApi.GetChatBoostLink(
    this.chatId
)

fun GetChatBoostLinkInfo.toData(): TdApi.GetChatBoostLinkInfo = TdApi.GetChatBoostLinkInfo(
    this.url
)

fun GetChatBoostStatus.toData(): TdApi.GetChatBoostStatus = TdApi.GetChatBoostStatus(
    this.chatId
)

fun GetChatBoosts.toData(): TdApi.GetChatBoosts = TdApi.GetChatBoosts(
    this.chatId,
    this.onlyGiftCodes,
    this.offset,
    this.limit
)

fun GetChatEventLog.toData(): TdApi.GetChatEventLog = TdApi.GetChatEventLog(
    this.chatId,
    this.query,
    this.fromEventId,
    this.limit,
    this.filters.toData(),
    this.userIds
)

fun GetChatFolder.toData(): TdApi.GetChatFolder = TdApi.GetChatFolder(
    this.chatFolderId
)

fun GetChatFolderChatCount.toData(): TdApi.GetChatFolderChatCount = TdApi.GetChatFolderChatCount(
    this.folder.toData()
)

fun GetChatFolderChatsToLeave.toData(): TdApi.GetChatFolderChatsToLeave = TdApi.GetChatFolderChatsToLeave(
    this.chatFolderId
)

fun GetChatFolderDefaultIconName.toData(): TdApi.GetChatFolderDefaultIconName = TdApi.GetChatFolderDefaultIconName(
    this.folder.toData()
)

fun GetChatFolderInviteLinks.toData(): TdApi.GetChatFolderInviteLinks = TdApi.GetChatFolderInviteLinks(
    this.chatFolderId
)

fun GetChatFolderNewChats.toData(): TdApi.GetChatFolderNewChats = TdApi.GetChatFolderNewChats(
    this.chatFolderId
)

fun GetChatHistory.toData(): TdApi.GetChatHistory = TdApi.GetChatHistory(
    this.chatId,
    this.fromMessageId,
    this.offset,
    this.limit,
    this.onlyLocal
)

fun GetChatInviteLink.toData(): TdApi.GetChatInviteLink = TdApi.GetChatInviteLink(
    this.chatId,
    this.inviteLink
)

fun GetChatInviteLinkCounts.toData(): TdApi.GetChatInviteLinkCounts = TdApi.GetChatInviteLinkCounts(
    this.chatId
)

fun GetChatInviteLinkMembers.toData(): TdApi.GetChatInviteLinkMembers = TdApi.GetChatInviteLinkMembers(
    this.chatId,
    this.inviteLink,
    this.onlyWithExpiredSubscription,
    this.offsetMember.toData(),
    this.limit
)

fun GetChatInviteLinks.toData(): TdApi.GetChatInviteLinks = TdApi.GetChatInviteLinks(
    this.chatId,
    this.creatorUserId,
    this.isRevoked,
    this.offsetDate,
    this.offsetInviteLink,
    this.limit
)

fun GetChatJoinRequests.toData(): TdApi.GetChatJoinRequests = TdApi.GetChatJoinRequests(
    this.chatId,
    this.inviteLink,
    this.query,
    this.offsetRequest.toData(),
    this.limit
)

fun GetChatListsToAddChat.toData(): TdApi.GetChatListsToAddChat = TdApi.GetChatListsToAddChat(
    this.chatId
)

fun GetChatMember.toData(): TdApi.GetChatMember = TdApi.GetChatMember(
    this.chatId,
    this.memberId.toData()
)

fun GetChatMessageCalendar.toData(): TdApi.GetChatMessageCalendar = TdApi.GetChatMessageCalendar(
    this.chatId,
    this.topicId.toData(),
    this.filter.toData(),
    this.fromMessageId
)

fun GetChatMessageCount.toData(): TdApi.GetChatMessageCount = TdApi.GetChatMessageCount(
    this.chatId,
    this.topicId.toData(),
    this.filter.toData(),
    this.returnLocal
)

fun GetChatNotificationSettingsExceptions.toData(): TdApi.GetChatNotificationSettingsExceptions = TdApi.GetChatNotificationSettingsExceptions(
    this.scope.toData(),
    this.compareSound
)

fun GetChatPinnedMessage.toData(): TdApi.GetChatPinnedMessage = TdApi.GetChatPinnedMessage(
    this.chatId
)

fun GetChatPostedToChatPageStories.toData(): TdApi.GetChatPostedToChatPageStories = TdApi.GetChatPostedToChatPageStories(
    this.chatId,
    this.fromStoryId,
    this.limit
)

fun GetChatRevenueStatistics.toData(): TdApi.GetChatRevenueStatistics = TdApi.GetChatRevenueStatistics(
    this.chatId,
    this.isDark
)

fun GetChatRevenueTransactions.toData(): TdApi.GetChatRevenueTransactions = TdApi.GetChatRevenueTransactions(
    this.chatId,
    this.offset,
    this.limit
)

fun GetChatRevenueWithdrawalUrl.toData(): TdApi.GetChatRevenueWithdrawalUrl = TdApi.GetChatRevenueWithdrawalUrl(
    this.chatId,
    this.password
)

fun GetChatScheduledMessages.toData(): TdApi.GetChatScheduledMessages = TdApi.GetChatScheduledMessages(
    this.chatId
)

fun GetChatSimilarChatCount.toData(): TdApi.GetChatSimilarChatCount = TdApi.GetChatSimilarChatCount(
    this.chatId,
    this.returnLocal
)

fun GetChatSimilarChats.toData(): TdApi.GetChatSimilarChats = TdApi.GetChatSimilarChats(
    this.chatId
)

fun GetChatSparseMessagePositions.toData(): TdApi.GetChatSparseMessagePositions = TdApi.GetChatSparseMessagePositions(
    this.chatId,
    this.filter.toData(),
    this.fromMessageId,
    this.limit,
    this.savedMessagesTopicId
)

fun GetChatSponsoredMessages.toData(): TdApi.GetChatSponsoredMessages = TdApi.GetChatSponsoredMessages(
    this.chatId
)

fun GetChatStatistics.toData(): TdApi.GetChatStatistics = TdApi.GetChatStatistics(
    this.chatId,
    this.isDark
)

fun GetChatStoryAlbums.toData(): TdApi.GetChatStoryAlbums = TdApi.GetChatStoryAlbums(
    this.chatId
)

fun GetChatStoryInteractions.toData(): TdApi.GetChatStoryInteractions = TdApi.GetChatStoryInteractions(
    this.storyPosterChatId,
    this.storyId,
    this.reactionType.toData(),
    this.preferForwards,
    this.offset,
    this.limit
)

fun GetChats.toData(): TdApi.GetChats = TdApi.GetChats(
    this.chatList.toData(),
    this.limit
)

fun GetChatsForChatFolderInviteLink.toData(): TdApi.GetChatsForChatFolderInviteLink = TdApi.GetChatsForChatFolderInviteLink(
    this.chatFolderId
)

fun GetChatsToPostStories.toData(): TdApi.GetChatsToPostStories = TdApi.GetChatsToPostStories(
)

fun GetForumTopic.toData(): TdApi.GetForumTopic = TdApi.GetForumTopic(
    this.chatId,
    this.messageThreadId
)

fun GetForumTopicDefaultIcons.toData(): TdApi.GetForumTopicDefaultIcons = TdApi.GetForumTopicDefaultIcons(
)

fun GetForumTopicLink.toData(): TdApi.GetForumTopicLink = TdApi.GetForumTopicLink(
    this.chatId,
    this.messageThreadId
)

fun GetForumTopics.toData(): TdApi.GetForumTopics = TdApi.GetForumTopics(
    this.chatId,
    this.query,
    this.offsetDate,
    this.offsetMessageId,
    this.offsetMessageThreadId,
    this.limit
)

fun JoinChat.toData(): TdApi.JoinChat = TdApi.JoinChat(
    this.chatId
)

fun JoinChatByInviteLink.toData(): TdApi.JoinChatByInviteLink = TdApi.JoinChatByInviteLink(
    this.inviteLink
)

fun LeaveChat.toData(): TdApi.LeaveChat = TdApi.LeaveChat(
    this.chatId
)

fun LoadChats.toData(): TdApi.LoadChats = TdApi.LoadChats(
    this.chatList.toData(),
    this.limit
)

fun PinChatMessage.toData(): TdApi.PinChatMessage = TdApi.PinChatMessage(
    this.chatId,
    this.messageId,
    this.disableNotification,
    this.onlyForSelf
)

fun SearchChatMessages.toData(): TdApi.SearchChatMessages = TdApi.SearchChatMessages(
    this.chatId,
    this.topicId.toData(),
    this.query,
    this.senderId.toData(),
    this.fromMessageId,
    this.offset,
    this.limit,
    this.filter.toData()
)

fun SearchChats.toData(): TdApi.SearchChats = TdApi.SearchChats(
    this.query,
    this.limit
)

fun SearchChatsOnServer.toData(): TdApi.SearchChatsOnServer = TdApi.SearchChatsOnServer(
    this.query,
    this.limit
)

fun SearchPublicChat.toData(): TdApi.SearchPublicChat = TdApi.SearchPublicChat(
    this.username
)

fun SearchPublicChats.toData(): TdApi.SearchPublicChats = TdApi.SearchPublicChats(
    this.query
)

fun SetChatAvailableReactions.toData(): TdApi.SetChatAvailableReactions = TdApi.SetChatAvailableReactions(
    this.chatId,
    this.availableReactions.toData()
)

fun SetChatClientData.toData(): TdApi.SetChatClientData = TdApi.SetChatClientData(
    this.chatId,
    this.clientData
)

fun SetChatDescription.toData(): TdApi.SetChatDescription = TdApi.SetChatDescription(
    this.chatId,
    this.description
)

fun SetChatDiscussionGroup.toData(): TdApi.SetChatDiscussionGroup = TdApi.SetChatDiscussionGroup(
    this.chatId,
    this.discussionChatId
)

fun SetChatNotificationSettings.toData(): TdApi.SetChatNotificationSettings = TdApi.SetChatNotificationSettings(
    this.chatId,
    this.notificationSettings.toData()
)

fun SetChatPermissions.toData(): TdApi.SetChatPermissions = TdApi.SetChatPermissions(
    this.chatId,
    this.permissions.toData()
)

fun SetChatPhoto.toData(): TdApi.SetChatPhoto = TdApi.SetChatPhoto(
    this.chatId,
    this.photo.toData()
)

fun SetChatTitle.toData(): TdApi.SetChatTitle = TdApi.SetChatTitle(
    this.chatId,
    this.title
)

fun SetForumTopicNotificationSettings.toData(): TdApi.SetForumTopicNotificationSettings = TdApi.SetForumTopicNotificationSettings(
    this.chatId,
    this.messageThreadId,
    this.notificationSettings.toData()
)

fun ToggleChatIsMarkedAsUnread.toData(): TdApi.ToggleChatIsMarkedAsUnread = TdApi.ToggleChatIsMarkedAsUnread(
    this.chatId,
    this.isMarkedAsUnread
)

fun UnpinAllChatMessages.toData(): TdApi.UnpinAllChatMessages = TdApi.UnpinAllChatMessages(
    this.chatId
)

fun UnpinAllMessageThreadMessages.toData(): TdApi.UnpinAllMessageThreadMessages = TdApi.UnpinAllMessageThreadMessages(
    this.chatId,
    this.messageThreadId
)

fun UnpinChatMessage.toData(): TdApi.UnpinChatMessage = TdApi.UnpinChatMessage(
    this.chatId,
    this.messageId
)

fun UpdateChatFolders.toData(): TdApi.UpdateChatFolders = TdApi.UpdateChatFolders(
    this.chatFolders.map { it.toData() }.toTypedArray(),
    this.mainChatListPosition,
    this.areTagsEnabled
)

