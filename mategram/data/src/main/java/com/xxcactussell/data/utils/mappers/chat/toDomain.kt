package com.xxcactussell.data.utils.mappers.chat

import com.xxcactussell.data.utils.mappers.account.toDomain
import com.xxcactussell.data.utils.mappers.animated.toDomain
import com.xxcactussell.data.utils.mappers.background.toDomain
import com.xxcactussell.data.utils.mappers.block.toDomain
import com.xxcactussell.data.utils.mappers.business.toDomain
import com.xxcactussell.data.utils.mappers.date.toDomain
import com.xxcactussell.data.utils.mappers.emoji.toDomain
import com.xxcactussell.data.utils.mappers.file.toDomain
import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.forum.toDomain
import com.xxcactussell.data.utils.mappers.input.toDomain
import com.xxcactussell.data.utils.mappers.invite.toDomain
import com.xxcactussell.data.utils.mappers.location.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.minithumbnail.toDomain
import com.xxcactussell.data.utils.mappers.monetization.toDomain
import com.xxcactussell.data.utils.mappers.notification.toDomain
import com.xxcactussell.data.utils.mappers.prepaid.toDomain
import com.xxcactussell.data.utils.mappers.reaction.toDomain
import com.xxcactussell.data.utils.mappers.revenue.toDomain
import com.xxcactussell.data.utils.mappers.search.toDomain
import com.xxcactussell.data.utils.mappers.star.toDomain
import com.xxcactussell.data.utils.mappers.statistical.toDomain
import com.xxcactussell.data.utils.mappers.stories.toDomain
import com.xxcactussell.data.utils.mappers.verification.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ArchiveChatListSettings.toDomain(): ArchiveChatListSettings = ArchiveChatListSettings(
    archiveAndMuteNewChatsFromUnknownUsers = this.archiveAndMuteNewChatsFromUnknownUsers,
    keepUnmutedChatsArchived = this.keepUnmutedChatsArchived,
    keepChatsFromFoldersArchived = this.keepChatsFromFoldersArchived
)

fun TdApi.Chat.toDomain(): Chat = Chat(
    id = this.id,
    type = this.type.toDomain(),
    title = this.title,
    photo = this.photo?.toDomain(),
    accentColorId = this.accentColorId,
    backgroundCustomEmojiId = this.backgroundCustomEmojiId,
    profileAccentColorId = this.profileAccentColorId,
    profileBackgroundCustomEmojiId = this.profileBackgroundCustomEmojiId,
    permissions = this.permissions.toDomain(),
    lastMessage = this.lastMessage?.toDomain(),
    positions = this.positions.map { it.toDomain() },
    chatLists = this.chatLists.map { it.toDomain() },
    messageSenderId = this.messageSenderId?.toDomain(),
    blockList = this.blockList?.toDomain(),
    hasProtectedContent = this.hasProtectedContent,
    isTranslatable = this.isTranslatable,
    isMarkedAsUnread = this.isMarkedAsUnread,
    viewAsTopics = this.viewAsTopics,
    hasScheduledMessages = this.hasScheduledMessages,
    canBeDeletedOnlyForSelf = this.canBeDeletedOnlyForSelf,
    canBeDeletedForAllUsers = this.canBeDeletedForAllUsers,
    canBeReported = this.canBeReported,
    defaultDisableNotification = this.defaultDisableNotification,
    unreadCount = this.unreadCount,
    lastReadInboxMessageId = this.lastReadInboxMessageId,
    lastReadOutboxMessageId = this.lastReadOutboxMessageId,
    unreadMentionCount = this.unreadMentionCount,
    unreadReactionCount = this.unreadReactionCount,
    notificationSettings = this.notificationSettings.toDomain(),
    availableReactions = this.availableReactions.toDomain(),
    messageAutoDeleteTime = this.messageAutoDeleteTime,
    emojiStatus = this.emojiStatus?.toDomain(),
    background = this.background?.toDomain(),
    theme = this.theme?.toDomain(),
    actionBar = this.actionBar?.toDomain(),
    businessBotManageBar = this.businessBotManageBar?.toDomain(),
    videoChat = this.videoChat.toDomain(),
    pendingJoinRequests = this.pendingJoinRequests?.toDomain(),
    replyMarkupMessageId = this.replyMarkupMessageId,
    draftMessage = this.draftMessage?.toDomain(),
    clientData = this.clientData
)

fun TdApi.ChatAction.toDomain(): ChatAction = when(this) {
    is TdApi.ChatActionTyping -> this.toDomain()
    is TdApi.ChatActionRecordingVideo -> this.toDomain()
    is TdApi.ChatActionUploadingVideo -> this.toDomain()
    is TdApi.ChatActionRecordingVoiceNote -> this.toDomain()
    is TdApi.ChatActionUploadingVoiceNote -> this.toDomain()
    is TdApi.ChatActionUploadingPhoto -> this.toDomain()
    is TdApi.ChatActionUploadingDocument -> this.toDomain()
    is TdApi.ChatActionChoosingSticker -> this.toDomain()
    is TdApi.ChatActionChoosingLocation -> this.toDomain()
    is TdApi.ChatActionChoosingContact -> this.toDomain()
    is TdApi.ChatActionStartPlayingGame -> this.toDomain()
    is TdApi.ChatActionRecordingVideoNote -> this.toDomain()
    is TdApi.ChatActionUploadingVideoNote -> this.toDomain()
    is TdApi.ChatActionWatchingAnimations -> this.toDomain()
    is TdApi.ChatActionCancel -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ChatActionBar.toDomain(): ChatActionBar = when(this) {
    is TdApi.ChatActionBarReportSpam -> this.toDomain()
    is TdApi.ChatActionBarInviteMembers -> this.toDomain()
    is TdApi.ChatActionBarReportAddBlock -> this.toDomain()
    is TdApi.ChatActionBarAddContact -> this.toDomain()
    is TdApi.ChatActionBarSharePhoneNumber -> this.toDomain()
    is TdApi.ChatActionBarJoinRequest -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ChatActionBarAddContact.toDomain(): ChatActionBarAddContact = ChatActionBarAddContact

fun TdApi.ChatActionBarInviteMembers.toDomain(): ChatActionBarInviteMembers = ChatActionBarInviteMembers

fun TdApi.ChatActionBarJoinRequest.toDomain(): ChatActionBarJoinRequest = ChatActionBarJoinRequest(
    title = this.title,
    isChannel = this.isChannel,
    requestDate = this.requestDate
)

fun TdApi.ChatActionBarReportAddBlock.toDomain(): ChatActionBarReportAddBlock = ChatActionBarReportAddBlock(
    canUnarchive = this.canUnarchive,
    accountInfo = this.accountInfo?.toDomain()
)

fun TdApi.ChatActionBarReportSpam.toDomain(): ChatActionBarReportSpam = ChatActionBarReportSpam(
    canUnarchive = this.canUnarchive
)

fun TdApi.ChatActionBarSharePhoneNumber.toDomain(): ChatActionBarSharePhoneNumber = ChatActionBarSharePhoneNumber

fun TdApi.ChatActionCancel.toDomain(): ChatActionCancel = ChatActionCancel

fun TdApi.ChatActionChoosingContact.toDomain(): ChatActionChoosingContact = ChatActionChoosingContact

fun TdApi.ChatActionChoosingLocation.toDomain(): ChatActionChoosingLocation = ChatActionChoosingLocation

fun TdApi.ChatActionChoosingSticker.toDomain(): ChatActionChoosingSticker = ChatActionChoosingSticker

fun TdApi.ChatActionRecordingVideo.toDomain(): ChatActionRecordingVideo = ChatActionRecordingVideo

fun TdApi.ChatActionRecordingVideoNote.toDomain(): ChatActionRecordingVideoNote = ChatActionRecordingVideoNote

fun TdApi.ChatActionRecordingVoiceNote.toDomain(): ChatActionRecordingVoiceNote = ChatActionRecordingVoiceNote

fun TdApi.ChatActionStartPlayingGame.toDomain(): ChatActionStartPlayingGame = ChatActionStartPlayingGame

fun TdApi.ChatActionTyping.toDomain(): ChatActionTyping = ChatActionTyping

fun TdApi.ChatActionUploadingDocument.toDomain(): ChatActionUploadingDocument = ChatActionUploadingDocument(
    progress = this.progress
)

fun TdApi.ChatActionUploadingPhoto.toDomain(): ChatActionUploadingPhoto = ChatActionUploadingPhoto(
    progress = this.progress
)

fun TdApi.ChatActionUploadingVideo.toDomain(): ChatActionUploadingVideo = ChatActionUploadingVideo(
    progress = this.progress
)

fun TdApi.ChatActionUploadingVideoNote.toDomain(): ChatActionUploadingVideoNote = ChatActionUploadingVideoNote(
    progress = this.progress
)

fun TdApi.ChatActionUploadingVoiceNote.toDomain(): ChatActionUploadingVoiceNote = ChatActionUploadingVoiceNote(
    progress = this.progress
)

fun TdApi.ChatActionWatchingAnimations.toDomain(): ChatActionWatchingAnimations = ChatActionWatchingAnimations(
    emoji = this.emoji
)

fun TdApi.ChatActiveStories.toDomain(): ChatActiveStories = ChatActiveStories(
    chatId = this.chatId,
    list = this.list?.toDomain(),
    order = this.order,
    canBeArchived = this.canBeArchived,
    maxReadStoryId = this.maxReadStoryId,
    stories = this.stories.map { it.toDomain() }
)

fun TdApi.ChatAdministrator.toDomain(): ChatAdministrator = ChatAdministrator(
    userId = this.userId,
    customTitle = this.customTitle,
    isOwner = this.isOwner
)

fun TdApi.ChatAdministratorRights.toDomain(): ChatAdministratorRights = ChatAdministratorRights(
    canManageChat = this.canManageChat,
    canChangeInfo = this.canChangeInfo,
    canPostMessages = this.canPostMessages,
    canEditMessages = this.canEditMessages,
    canDeleteMessages = this.canDeleteMessages,
    canInviteUsers = this.canInviteUsers,
    canRestrictMembers = this.canRestrictMembers,
    canPinMessages = this.canPinMessages,
    canManageTopics = this.canManageTopics,
    canPromoteMembers = this.canPromoteMembers,
    canManageVideoChats = this.canManageVideoChats,
    canPostStories = this.canPostStories,
    canEditStories = this.canEditStories,
    canDeleteStories = this.canDeleteStories,
    canManageDirectMessages = this.canManageDirectMessages,
    isAnonymous = this.isAnonymous
)

fun TdApi.ChatAdministrators.toDomain(): ChatAdministrators = ChatAdministrators(
    administrators = this.administrators.map { it.toDomain() }
)

fun TdApi.ChatAvailableReactions.toDomain(): ChatAvailableReactions = when(this) {
    is TdApi.ChatAvailableReactionsAll -> this.toDomain()
    is TdApi.ChatAvailableReactionsSome -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ChatAvailableReactionsAll.toDomain(): ChatAvailableReactionsAll = ChatAvailableReactionsAll(
    maxReactionCount = this.maxReactionCount
)

fun TdApi.ChatAvailableReactionsSome.toDomain(): ChatAvailableReactionsSome = ChatAvailableReactionsSome(
    reactions = this.reactions.map { it.toDomain() },
    maxReactionCount = this.maxReactionCount
)

fun TdApi.ChatBackground.toDomain(): ChatBackground = ChatBackground(
    background = this.background.toDomain(),
    darkThemeDimming = this.darkThemeDimming
)

fun TdApi.ChatBoost.toDomain(): ChatBoost = ChatBoost(
    id = this.id,
    count = this.count,
    source = this.source.toDomain(),
    startDate = this.startDate,
    expirationDate = this.expirationDate
)

fun TdApi.ChatBoostFeatures.toDomain(): ChatBoostFeatures = ChatBoostFeatures(
    features = this.features.map { it.toDomain() },
    minProfileBackgroundCustomEmojiBoostLevel = this.minProfileBackgroundCustomEmojiBoostLevel,
    minBackgroundCustomEmojiBoostLevel = this.minBackgroundCustomEmojiBoostLevel,
    minEmojiStatusBoostLevel = this.minEmojiStatusBoostLevel,
    minChatThemeBackgroundBoostLevel = this.minChatThemeBackgroundBoostLevel,
    minCustomBackgroundBoostLevel = this.minCustomBackgroundBoostLevel,
    minCustomEmojiStickerSetBoostLevel = this.minCustomEmojiStickerSetBoostLevel,
    minAutomaticTranslationBoostLevel = this.minAutomaticTranslationBoostLevel,
    minSpeechRecognitionBoostLevel = this.minSpeechRecognitionBoostLevel,
    minSponsoredMessageDisableBoostLevel = this.minSponsoredMessageDisableBoostLevel
)

fun TdApi.ChatBoostLevelFeatures.toDomain(): ChatBoostLevelFeatures = ChatBoostLevelFeatures(
    level = this.level,
    storyPerDayCount = this.storyPerDayCount,
    customEmojiReactionCount = this.customEmojiReactionCount,
    titleColorCount = this.titleColorCount,
    profileAccentColorCount = this.profileAccentColorCount,
    canSetProfileBackgroundCustomEmoji = this.canSetProfileBackgroundCustomEmoji,
    accentColorCount = this.accentColorCount,
    canSetBackgroundCustomEmoji = this.canSetBackgroundCustomEmoji,
    canSetEmojiStatus = this.canSetEmojiStatus,
    chatThemeBackgroundCount = this.chatThemeBackgroundCount,
    canSetCustomBackground = this.canSetCustomBackground,
    canSetCustomEmojiStickerSet = this.canSetCustomEmojiStickerSet,
    canEnableAutomaticTranslation = this.canEnableAutomaticTranslation,
    canRecognizeSpeech = this.canRecognizeSpeech,
    canDisableSponsoredMessages = this.canDisableSponsoredMessages
)

fun TdApi.ChatBoostLink.toDomain(): ChatBoostLink = ChatBoostLink(
    link = this.link,
    isPublic = this.isPublic
)

fun TdApi.ChatBoostLinkInfo.toDomain(): ChatBoostLinkInfo = ChatBoostLinkInfo(
    isPublic = this.isPublic,
    chatId = this.chatId
)

fun TdApi.ChatBoostSlot.toDomain(): ChatBoostSlot = ChatBoostSlot(
    slotId = this.slotId,
    currentlyBoostedChatId = this.currentlyBoostedChatId,
    startDate = this.startDate,
    expirationDate = this.expirationDate,
    cooldownUntilDate = this.cooldownUntilDate
)

fun TdApi.ChatBoostSlots.toDomain(): ChatBoostSlots = ChatBoostSlots(
    slots = this.slots.map { it.toDomain() }
)

fun TdApi.ChatBoostSource.toDomain(): ChatBoostSource = when(this) {
    is TdApi.ChatBoostSourceGiftCode -> this.toDomain()
    is TdApi.ChatBoostSourceGiveaway -> this.toDomain()
    is TdApi.ChatBoostSourcePremium -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ChatBoostSourceGiftCode.toDomain(): ChatBoostSourceGiftCode = ChatBoostSourceGiftCode(
    userId = this.userId,
    giftCode = this.giftCode
)

fun TdApi.ChatBoostSourceGiveaway.toDomain(): ChatBoostSourceGiveaway = ChatBoostSourceGiveaway(
    userId = this.userId,
    giftCode = this.giftCode,
    starCount = this.starCount,
    giveawayMessageId = this.giveawayMessageId,
    isUnclaimed = this.isUnclaimed
)

fun TdApi.ChatBoostSourcePremium.toDomain(): ChatBoostSourcePremium = ChatBoostSourcePremium(
    userId = this.userId
)

fun TdApi.ChatBoostStatus.toDomain(): ChatBoostStatus = ChatBoostStatus(
    boostUrl = this.boostUrl,
    appliedSlotIds = this.appliedSlotIds,
    level = this.level,
    giftCodeBoostCount = this.giftCodeBoostCount,
    boostCount = this.boostCount,
    currentLevelBoostCount = this.currentLevelBoostCount,
    nextLevelBoostCount = this.nextLevelBoostCount,
    premiumMemberCount = this.premiumMemberCount,
    premiumMemberPercentage = this.premiumMemberPercentage,
    prepaidGiveaways = this.prepaidGiveaways.map { it.toDomain() }
)

fun TdApi.ChatEvent.toDomain(): ChatEvent = ChatEvent(
    id = this.id,
    date = this.date,
    memberId = this.memberId.toDomain(),
    action = this.action.toDomain()
)

fun TdApi.ChatEventAccentColorChanged.toDomain(): ChatEventAccentColorChanged = ChatEventAccentColorChanged(
    oldAccentColorId = this.oldAccentColorId,
    oldBackgroundCustomEmojiId = this.oldBackgroundCustomEmojiId,
    newAccentColorId = this.newAccentColorId,
    newBackgroundCustomEmojiId = this.newBackgroundCustomEmojiId
)

fun TdApi.ChatEventAction.toDomain(): ChatEventAction = when(this) {
    is TdApi.ChatEventMessageEdited -> this.toDomain()
    is TdApi.ChatEventMessageDeleted -> this.toDomain()
    is TdApi.ChatEventMessagePinned -> this.toDomain()
    is TdApi.ChatEventMessageUnpinned -> this.toDomain()
    is TdApi.ChatEventPollStopped -> this.toDomain()
    is TdApi.ChatEventMemberJoined -> this.toDomain()
    is TdApi.ChatEventMemberJoinedByInviteLink -> this.toDomain()
    is TdApi.ChatEventMemberJoinedByRequest -> this.toDomain()
    is TdApi.ChatEventMemberInvited -> this.toDomain()
    is TdApi.ChatEventMemberLeft -> this.toDomain()
    is TdApi.ChatEventMemberPromoted -> this.toDomain()
    is TdApi.ChatEventMemberRestricted -> this.toDomain()
    is TdApi.ChatEventMemberSubscriptionExtended -> this.toDomain()
    is TdApi.ChatEventAvailableReactionsChanged -> this.toDomain()
    is TdApi.ChatEventBackgroundChanged -> this.toDomain()
    is TdApi.ChatEventDescriptionChanged -> this.toDomain()
    is TdApi.ChatEventEmojiStatusChanged -> this.toDomain()
    is TdApi.ChatEventLinkedChatChanged -> this.toDomain()
    is TdApi.ChatEventLocationChanged -> this.toDomain()
    is TdApi.ChatEventMessageAutoDeleteTimeChanged -> this.toDomain()
    is TdApi.ChatEventPermissionsChanged -> this.toDomain()
    is TdApi.ChatEventPhotoChanged -> this.toDomain()
    is TdApi.ChatEventSlowModeDelayChanged -> this.toDomain()
    is TdApi.ChatEventStickerSetChanged -> this.toDomain()
    is TdApi.ChatEventCustomEmojiStickerSetChanged -> this.toDomain()
    is TdApi.ChatEventTitleChanged -> this.toDomain()
    is TdApi.ChatEventUsernameChanged -> this.toDomain()
    is TdApi.ChatEventActiveUsernamesChanged -> this.toDomain()
    is TdApi.ChatEventAccentColorChanged -> this.toDomain()
    is TdApi.ChatEventProfileAccentColorChanged -> this.toDomain()
    is TdApi.ChatEventHasProtectedContentToggled -> this.toDomain()
    is TdApi.ChatEventInvitesToggled -> this.toDomain()
    is TdApi.ChatEventIsAllHistoryAvailableToggled -> this.toDomain()
    is TdApi.ChatEventHasAggressiveAntiSpamEnabledToggled -> this.toDomain()
    is TdApi.ChatEventSignMessagesToggled -> this.toDomain()
    is TdApi.ChatEventShowMessageSenderToggled -> this.toDomain()
    is TdApi.ChatEventAutomaticTranslationToggled -> this.toDomain()
    is TdApi.ChatEventInviteLinkEdited -> this.toDomain()
    is TdApi.ChatEventInviteLinkRevoked -> this.toDomain()
    is TdApi.ChatEventInviteLinkDeleted -> this.toDomain()
    is TdApi.ChatEventVideoChatCreated -> this.toDomain()
    is TdApi.ChatEventVideoChatEnded -> this.toDomain()
    is TdApi.ChatEventVideoChatMuteNewParticipantsToggled -> this.toDomain()
    is TdApi.ChatEventVideoChatParticipantIsMutedToggled -> this.toDomain()
    is TdApi.ChatEventVideoChatParticipantVolumeLevelChanged -> this.toDomain()
    is TdApi.ChatEventIsForumToggled -> this.toDomain()
    is TdApi.ChatEventForumTopicCreated -> this.toDomain()
    is TdApi.ChatEventForumTopicEdited -> this.toDomain()
    is TdApi.ChatEventForumTopicToggleIsClosed -> this.toDomain()
    is TdApi.ChatEventForumTopicToggleIsHidden -> this.toDomain()
    is TdApi.ChatEventForumTopicDeleted -> this.toDomain()
    is TdApi.ChatEventForumTopicPinned -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ChatEventActiveUsernamesChanged.toDomain(): ChatEventActiveUsernamesChanged = ChatEventActiveUsernamesChanged(
    oldUsernames = this.oldUsernames.toList(),
    newUsernames = this.newUsernames.toList()
)

fun TdApi.ChatEventAutomaticTranslationToggled.toDomain(): ChatEventAutomaticTranslationToggled = ChatEventAutomaticTranslationToggled(
    hasAutomaticTranslation = this.hasAutomaticTranslation
)

fun TdApi.ChatEventAvailableReactionsChanged.toDomain(): ChatEventAvailableReactionsChanged = ChatEventAvailableReactionsChanged(
    oldAvailableReactions = this.oldAvailableReactions.toDomain(),
    newAvailableReactions = this.newAvailableReactions.toDomain()
)

fun TdApi.ChatEventBackgroundChanged.toDomain(): ChatEventBackgroundChanged = ChatEventBackgroundChanged(
    oldBackground = this.oldBackground?.toDomain(),
    newBackground = this.newBackground?.toDomain()
)

fun TdApi.ChatEventCustomEmojiStickerSetChanged.toDomain(): ChatEventCustomEmojiStickerSetChanged = ChatEventCustomEmojiStickerSetChanged(
    oldStickerSetId = this.oldStickerSetId,
    newStickerSetId = this.newStickerSetId
)

fun TdApi.ChatEventDescriptionChanged.toDomain(): ChatEventDescriptionChanged = ChatEventDescriptionChanged(
    oldDescription = this.oldDescription,
    newDescription = this.newDescription
)

fun TdApi.ChatEventEmojiStatusChanged.toDomain(): ChatEventEmojiStatusChanged = ChatEventEmojiStatusChanged(
    oldEmojiStatus = this.oldEmojiStatus?.toDomain(),
    newEmojiStatus = this.newEmojiStatus?.toDomain()
)

fun TdApi.ChatEventForumTopicCreated.toDomain(): ChatEventForumTopicCreated = ChatEventForumTopicCreated(
    topicInfo = this.topicInfo.toDomain()
)

fun TdApi.ChatEventForumTopicDeleted.toDomain(): ChatEventForumTopicDeleted = ChatEventForumTopicDeleted(
    topicInfo = this.topicInfo.toDomain()
)

fun TdApi.ChatEventForumTopicEdited.toDomain(): ChatEventForumTopicEdited = ChatEventForumTopicEdited(
    oldTopicInfo = this.oldTopicInfo.toDomain(),
    newTopicInfo = this.newTopicInfo.toDomain()
)

fun TdApi.ChatEventForumTopicPinned.toDomain(): ChatEventForumTopicPinned = ChatEventForumTopicPinned(
    oldTopicInfo = this.oldTopicInfo?.toDomain(),
    newTopicInfo = this.newTopicInfo?.toDomain()
)

fun TdApi.ChatEventForumTopicToggleIsClosed.toDomain(): ChatEventForumTopicToggleIsClosed = ChatEventForumTopicToggleIsClosed(
    topicInfo = this.topicInfo.toDomain()
)

fun TdApi.ChatEventForumTopicToggleIsHidden.toDomain(): ChatEventForumTopicToggleIsHidden = ChatEventForumTopicToggleIsHidden(
    topicInfo = this.topicInfo.toDomain()
)

fun TdApi.ChatEventHasAggressiveAntiSpamEnabledToggled.toDomain(): ChatEventHasAggressiveAntiSpamEnabledToggled = ChatEventHasAggressiveAntiSpamEnabledToggled(
    hasAggressiveAntiSpamEnabled = this.hasAggressiveAntiSpamEnabled
)

fun TdApi.ChatEventHasProtectedContentToggled.toDomain(): ChatEventHasProtectedContentToggled = ChatEventHasProtectedContentToggled(
    hasProtectedContent = this.hasProtectedContent
)

fun TdApi.ChatEventInviteLinkDeleted.toDomain(): ChatEventInviteLinkDeleted = ChatEventInviteLinkDeleted(
    inviteLink = this.inviteLink.toDomain()
)

fun TdApi.ChatEventInviteLinkEdited.toDomain(): ChatEventInviteLinkEdited = ChatEventInviteLinkEdited(
    oldInviteLink = this.oldInviteLink.toDomain(),
    newInviteLink = this.newInviteLink.toDomain()
)

fun TdApi.ChatEventInviteLinkRevoked.toDomain(): ChatEventInviteLinkRevoked = ChatEventInviteLinkRevoked(
    inviteLink = this.inviteLink.toDomain()
)

fun TdApi.ChatEventInvitesToggled.toDomain(): ChatEventInvitesToggled = ChatEventInvitesToggled(
    canInviteUsers = this.canInviteUsers
)

fun TdApi.ChatEventIsAllHistoryAvailableToggled.toDomain(): ChatEventIsAllHistoryAvailableToggled = ChatEventIsAllHistoryAvailableToggled(
    isAllHistoryAvailable = this.isAllHistoryAvailable
)

fun TdApi.ChatEventIsForumToggled.toDomain(): ChatEventIsForumToggled = ChatEventIsForumToggled(
    isForum = this.isForum
)

fun TdApi.ChatEventLinkedChatChanged.toDomain(): ChatEventLinkedChatChanged = ChatEventLinkedChatChanged(
    oldLinkedChatId = this.oldLinkedChatId,
    newLinkedChatId = this.newLinkedChatId
)

fun TdApi.ChatEventLocationChanged.toDomain(): ChatEventLocationChanged = ChatEventLocationChanged(
    oldLocation = this.oldLocation?.toDomain(),
    newLocation = this.newLocation?.toDomain()
)

fun TdApi.ChatEventLogFilters.toDomain(): ChatEventLogFilters = ChatEventLogFilters(
    messageEdits = this.messageEdits,
    messageDeletions = this.messageDeletions,
    messagePins = this.messagePins,
    memberJoins = this.memberJoins,
    memberLeaves = this.memberLeaves,
    memberInvites = this.memberInvites,
    memberPromotions = this.memberPromotions,
    memberRestrictions = this.memberRestrictions,
    infoChanges = this.infoChanges,
    settingChanges = this.settingChanges,
    inviteLinkChanges = this.inviteLinkChanges,
    videoChatChanges = this.videoChatChanges,
    forumChanges = this.forumChanges,
    subscriptionExtensions = this.subscriptionExtensions
)

fun TdApi.ChatEventMemberInvited.toDomain(): ChatEventMemberInvited = ChatEventMemberInvited(
    userId = this.userId,
    status = this.status.toDomain()
)

fun TdApi.ChatEventMemberJoined.toDomain(): ChatEventMemberJoined = ChatEventMemberJoined

fun TdApi.ChatEventMemberJoinedByInviteLink.toDomain(): ChatEventMemberJoinedByInviteLink = ChatEventMemberJoinedByInviteLink(
    inviteLink = this.inviteLink.toDomain(),
    viaChatFolderInviteLink = this.viaChatFolderInviteLink
)

fun TdApi.ChatEventMemberJoinedByRequest.toDomain(): ChatEventMemberJoinedByRequest = ChatEventMemberJoinedByRequest(
    approverUserId = this.approverUserId,
    inviteLink = this.inviteLink?.toDomain()
)

fun TdApi.ChatEventMemberLeft.toDomain(): ChatEventMemberLeft = ChatEventMemberLeft

fun TdApi.ChatEventMemberPromoted.toDomain(): ChatEventMemberPromoted = ChatEventMemberPromoted(
    userId = this.userId,
    oldStatus = this.oldStatus.toDomain(),
    newStatus = this.newStatus.toDomain()
)

fun TdApi.ChatEventMemberRestricted.toDomain(): ChatEventMemberRestricted = ChatEventMemberRestricted(
    memberId = this.memberId.toDomain(),
    oldStatus = this.oldStatus.toDomain(),
    newStatus = this.newStatus.toDomain()
)

fun TdApi.ChatEventMemberSubscriptionExtended.toDomain(): ChatEventMemberSubscriptionExtended = ChatEventMemberSubscriptionExtended(
    userId = this.userId,
    oldStatus = this.oldStatus.toDomain(),
    newStatus = this.newStatus.toDomain()
)

fun TdApi.ChatEventMessageAutoDeleteTimeChanged.toDomain(): ChatEventMessageAutoDeleteTimeChanged = ChatEventMessageAutoDeleteTimeChanged(
    oldMessageAutoDeleteTime = this.oldMessageAutoDeleteTime,
    newMessageAutoDeleteTime = this.newMessageAutoDeleteTime
)

fun TdApi.ChatEventMessageDeleted.toDomain(): ChatEventMessageDeleted = ChatEventMessageDeleted(
    message = this.message.toDomain(),
    canReportAntiSpamFalsePositive = this.canReportAntiSpamFalsePositive
)

fun TdApi.ChatEventMessageEdited.toDomain(): ChatEventMessageEdited = ChatEventMessageEdited(
    oldMessage = this.oldMessage.toDomain(),
    newMessage = this.newMessage.toDomain()
)

fun TdApi.ChatEventMessagePinned.toDomain(): ChatEventMessagePinned = ChatEventMessagePinned(
    message = this.message.toDomain()
)

fun TdApi.ChatEventMessageUnpinned.toDomain(): ChatEventMessageUnpinned = ChatEventMessageUnpinned(
    message = this.message.toDomain()
)

fun TdApi.ChatEventPermissionsChanged.toDomain(): ChatEventPermissionsChanged = ChatEventPermissionsChanged(
    oldPermissions = this.oldPermissions.toDomain(),
    newPermissions = this.newPermissions.toDomain()
)

fun TdApi.ChatEventPhotoChanged.toDomain(): ChatEventPhotoChanged = ChatEventPhotoChanged(
    oldPhoto = this.oldPhoto?.toDomain(),
    newPhoto = this.newPhoto?.toDomain()
)

fun TdApi.ChatEventPollStopped.toDomain(): ChatEventPollStopped = ChatEventPollStopped(
    message = this.message.toDomain()
)

fun TdApi.ChatEventProfileAccentColorChanged.toDomain(): ChatEventProfileAccentColorChanged = ChatEventProfileAccentColorChanged(
    oldProfileAccentColorId = this.oldProfileAccentColorId,
    oldProfileBackgroundCustomEmojiId = this.oldProfileBackgroundCustomEmojiId,
    newProfileAccentColorId = this.newProfileAccentColorId,
    newProfileBackgroundCustomEmojiId = this.newProfileBackgroundCustomEmojiId
)

fun TdApi.ChatEventShowMessageSenderToggled.toDomain(): ChatEventShowMessageSenderToggled = ChatEventShowMessageSenderToggled(
    showMessageSender = this.showMessageSender
)

fun TdApi.ChatEventSignMessagesToggled.toDomain(): ChatEventSignMessagesToggled = ChatEventSignMessagesToggled(
    signMessages = this.signMessages
)

fun TdApi.ChatEventSlowModeDelayChanged.toDomain(): ChatEventSlowModeDelayChanged = ChatEventSlowModeDelayChanged(
    oldSlowModeDelay = this.oldSlowModeDelay,
    newSlowModeDelay = this.newSlowModeDelay
)

fun TdApi.ChatEventStickerSetChanged.toDomain(): ChatEventStickerSetChanged = ChatEventStickerSetChanged(
    oldStickerSetId = this.oldStickerSetId,
    newStickerSetId = this.newStickerSetId
)

fun TdApi.ChatEventTitleChanged.toDomain(): ChatEventTitleChanged = ChatEventTitleChanged(
    oldTitle = this.oldTitle,
    newTitle = this.newTitle
)

fun TdApi.ChatEventUsernameChanged.toDomain(): ChatEventUsernameChanged = ChatEventUsernameChanged(
    oldUsername = this.oldUsername,
    newUsername = this.newUsername
)

fun TdApi.ChatEventVideoChatCreated.toDomain(): ChatEventVideoChatCreated = ChatEventVideoChatCreated(
    groupCallId = this.groupCallId
)

fun TdApi.ChatEventVideoChatEnded.toDomain(): ChatEventVideoChatEnded = ChatEventVideoChatEnded(
    groupCallId = this.groupCallId
)

fun TdApi.ChatEventVideoChatMuteNewParticipantsToggled.toDomain(): ChatEventVideoChatMuteNewParticipantsToggled = ChatEventVideoChatMuteNewParticipantsToggled(
    muteNewParticipants = this.muteNewParticipants
)

fun TdApi.ChatEventVideoChatParticipantIsMutedToggled.toDomain(): ChatEventVideoChatParticipantIsMutedToggled = ChatEventVideoChatParticipantIsMutedToggled(
    participantId = this.participantId.toDomain(),
    isMuted = this.isMuted
)

fun TdApi.ChatEventVideoChatParticipantVolumeLevelChanged.toDomain(): ChatEventVideoChatParticipantVolumeLevelChanged = ChatEventVideoChatParticipantVolumeLevelChanged(
    participantId = this.participantId.toDomain(),
    volumeLevel = this.volumeLevel
)

fun TdApi.ChatEvents.toDomain(): ChatEvents = ChatEvents(
    events = this.events.map { it.toDomain() }
)

fun TdApi.ChatFolder.toDomain(): ChatFolder = ChatFolder(
    name = this.name.toDomain(),
    icon = this.icon?.toDomain(),
    colorId = this.colorId,
    isShareable = this.isShareable,
    pinnedChatIds = this.pinnedChatIds,
    includedChatIds = this.includedChatIds,
    excludedChatIds = this.excludedChatIds,
    excludeMuted = this.excludeMuted,
    excludeRead = this.excludeRead,
    excludeArchived = this.excludeArchived,
    includeContacts = this.includeContacts,
    includeNonContacts = this.includeNonContacts,
    includeBots = this.includeBots,
    includeGroups = this.includeGroups,
    includeChannels = this.includeChannels
)

fun TdApi.ChatFolderIcon.toDomain(): ChatFolderIcon = ChatFolderIcon(
    name = this.name
)

fun TdApi.ChatFolderInfo.toDomain(): ChatFolderInfo = ChatFolderInfo(
    id = this.id,
    name = this.name.toDomain(),
    icon = this.icon.toDomain(),
    colorId = this.colorId,
    isShareable = this.isShareable,
    hasMyInviteLinks = this.hasMyInviteLinks
)

fun TdApi.ChatFolderInviteLink.toDomain(): ChatFolderInviteLink = ChatFolderInviteLink(
    inviteLink = this.inviteLink,
    name = this.name,
    chatIds = this.chatIds
)

fun TdApi.ChatFolderInviteLinkInfo.toDomain(): ChatFolderInviteLinkInfo = ChatFolderInviteLinkInfo(
    chatFolderInfo = this.chatFolderInfo.toDomain(),
    missingChatIds = this.missingChatIds,
    addedChatIds = this.addedChatIds
)

fun TdApi.ChatFolderInviteLinks.toDomain(): ChatFolderInviteLinks = ChatFolderInviteLinks(
    inviteLinks = this.inviteLinks.map { it.toDomain() }
)

fun TdApi.ChatFolderName.toDomain(): ChatFolderName = ChatFolderName(
    text = this.text.toDomain(),
    animateCustomEmoji = this.animateCustomEmoji
)

fun TdApi.ChatInviteLink.toDomain(): ChatInviteLink = ChatInviteLink(
    inviteLink = this.inviteLink,
    name = this.name,
    creatorUserId = this.creatorUserId,
    date = this.date,
    editDate = this.editDate,
    expirationDate = this.expirationDate,
    subscriptionPricing = this.subscriptionPricing?.toDomain(),
    memberLimit = this.memberLimit,
    memberCount = this.memberCount,
    expiredMemberCount = this.expiredMemberCount,
    pendingJoinRequestCount = this.pendingJoinRequestCount,
    createsJoinRequest = this.createsJoinRequest,
    isPrimary = this.isPrimary,
    isRevoked = this.isRevoked
)

fun TdApi.ChatInviteLinkCount.toDomain(): ChatInviteLinkCount = ChatInviteLinkCount(
    userId = this.userId,
    inviteLinkCount = this.inviteLinkCount,
    revokedInviteLinkCount = this.revokedInviteLinkCount
)

fun TdApi.ChatInviteLinkCounts.toDomain(): ChatInviteLinkCounts = ChatInviteLinkCounts(
    inviteLinkCounts = this.inviteLinkCounts.map { it.toDomain() }
)

fun TdApi.ChatInviteLinkInfo.toDomain(): ChatInviteLinkInfo = ChatInviteLinkInfo(
    chatId = this.chatId,
    accessibleFor = this.accessibleFor,
    type = this.type.toDomain(),
    title = this.title,
    photo = this.photo?.toDomain(),
    accentColorId = this.accentColorId,
    description = this.description,
    memberCount = this.memberCount,
    memberUserIds = this.memberUserIds,
    subscriptionInfo = this.subscriptionInfo?.toDomain(),
    createsJoinRequest = this.createsJoinRequest,
    isPublic = this.isPublic,
    verificationStatus = this.verificationStatus?.toDomain()
)

fun TdApi.ChatInviteLinkMember.toDomain(): ChatInviteLinkMember = ChatInviteLinkMember(
    userId = this.userId,
    joinedChatDate = this.joinedChatDate,
    viaChatFolderInviteLink = this.viaChatFolderInviteLink,
    approverUserId = this.approverUserId
)

fun TdApi.ChatInviteLinkMembers.toDomain(): ChatInviteLinkMembers = ChatInviteLinkMembers(
    totalCount = this.totalCount,
    members = this.members.map { it.toDomain() }
)

fun TdApi.ChatInviteLinkSubscriptionInfo.toDomain(): ChatInviteLinkSubscriptionInfo = ChatInviteLinkSubscriptionInfo(
    pricing = this.pricing.toDomain(),
    canReuse = this.canReuse,
    formId = this.formId
)

fun TdApi.ChatInviteLinks.toDomain(): ChatInviteLinks = ChatInviteLinks(
    totalCount = this.totalCount,
    inviteLinks = this.inviteLinks.map { it.toDomain() }
)

fun TdApi.ChatJoinRequest.toDomain(): ChatJoinRequest = ChatJoinRequest(
    userId = this.userId,
    date = this.date,
    bio = this.bio
)

fun TdApi.ChatJoinRequests.toDomain(): ChatJoinRequests = ChatJoinRequests(
    totalCount = this.totalCount,
    requests = this.requests.map { it.toDomain() }
)

fun TdApi.ChatJoinRequestsInfo.toDomain(): ChatJoinRequestsInfo = ChatJoinRequestsInfo(
    totalCount = this.totalCount,
    userIds = this.userIds
)

fun TdApi.ChatList.toDomain(): ChatList = when(this) {
    is TdApi.ChatListMain -> this.toDomain()
    is TdApi.ChatListArchive -> this.toDomain()
    is TdApi.ChatListFolder -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ChatListArchive.toDomain(): ChatListArchive = ChatListArchive

fun TdApi.ChatListFolder.toDomain(): ChatListFolder = ChatListFolder(
    chatFolderId = this.chatFolderId
)

fun TdApi.ChatListMain.toDomain(): ChatListMain = ChatListMain

fun TdApi.ChatLists.toDomain(): ChatLists = ChatLists(
    chatLists = this.chatLists.map { it.toDomain() }
)

fun TdApi.ChatLocation.toDomain(): ChatLocation = ChatLocation(
    location = this.location.toDomain(),
    address = this.address
)

fun TdApi.ChatMember.toDomain(): ChatMember = ChatMember(
    memberId = this.memberId.toDomain(),
    inviterUserId = this.inviterUserId,
    joinedChatDate = this.joinedChatDate,
    status = this.status.toDomain()
)

fun TdApi.ChatMemberStatus.toDomain(): ChatMemberStatus = when(this) {
    is TdApi.ChatMemberStatusCreator -> this.toDomain()
    is TdApi.ChatMemberStatusAdministrator -> this.toDomain()
    is TdApi.ChatMemberStatusMember -> this.toDomain()
    is TdApi.ChatMemberStatusRestricted -> this.toDomain()
    is TdApi.ChatMemberStatusLeft -> this.toDomain()
    is TdApi.ChatMemberStatusBanned -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ChatMemberStatusAdministrator.toDomain(): ChatMemberStatusAdministrator = ChatMemberStatusAdministrator(
    customTitle = this.customTitle,
    canBeEdited = this.canBeEdited,
    rights = this.rights.toDomain()
)

fun TdApi.ChatMemberStatusBanned.toDomain(): ChatMemberStatusBanned = ChatMemberStatusBanned(
    bannedUntilDate = this.bannedUntilDate
)

fun TdApi.ChatMemberStatusCreator.toDomain(): ChatMemberStatusCreator = ChatMemberStatusCreator(
    customTitle = this.customTitle,
    isAnonymous = this.isAnonymous,
    isMember = this.isMember
)

fun TdApi.ChatMemberStatusLeft.toDomain(): ChatMemberStatusLeft = ChatMemberStatusLeft

fun TdApi.ChatMemberStatusMember.toDomain(): ChatMemberStatusMember = ChatMemberStatusMember(
    memberUntilDate = this.memberUntilDate
)

fun TdApi.ChatMemberStatusRestricted.toDomain(): ChatMemberStatusRestricted = ChatMemberStatusRestricted(
    isMember = this.isMember,
    restrictedUntilDate = this.restrictedUntilDate,
    permissions = this.permissions.toDomain()
)

fun TdApi.ChatMembers.toDomain(): ChatMembers = ChatMembers(
    totalCount = this.totalCount,
    members = this.members.map { it.toDomain() }
)

fun TdApi.ChatMembersFilter.toDomain(): ChatMembersFilter = when(this) {
    is TdApi.ChatMembersFilterContacts -> this.toDomain()
    is TdApi.ChatMembersFilterAdministrators -> this.toDomain()
    is TdApi.ChatMembersFilterMembers -> this.toDomain()
    is TdApi.ChatMembersFilterMention -> this.toDomain()
    is TdApi.ChatMembersFilterRestricted -> this.toDomain()
    is TdApi.ChatMembersFilterBanned -> this.toDomain()
    is TdApi.ChatMembersFilterBots -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ChatMembersFilterAdministrators.toDomain(): ChatMembersFilterAdministrators = ChatMembersFilterAdministrators

fun TdApi.ChatMembersFilterBanned.toDomain(): ChatMembersFilterBanned = ChatMembersFilterBanned

fun TdApi.ChatMembersFilterBots.toDomain(): ChatMembersFilterBots = ChatMembersFilterBots

fun TdApi.ChatMembersFilterContacts.toDomain(): ChatMembersFilterContacts = ChatMembersFilterContacts

fun TdApi.ChatMembersFilterMembers.toDomain(): ChatMembersFilterMembers = ChatMembersFilterMembers

fun TdApi.ChatMembersFilterMention.toDomain(): ChatMembersFilterMention = ChatMembersFilterMention(
    messageThreadId = this.messageThreadId
)

fun TdApi.ChatMembersFilterRestricted.toDomain(): ChatMembersFilterRestricted = ChatMembersFilterRestricted

fun TdApi.ChatMessageSender.toDomain(): ChatMessageSender = ChatMessageSender(
    sender = this.sender.toDomain(),
    needsPremium = this.needsPremium
)

fun TdApi.ChatMessageSenders.toDomain(): ChatMessageSenders = ChatMessageSenders(
    senders = this.senders.map { it.toDomain() }
)

fun TdApi.ChatNotificationSettings.toDomain(): ChatNotificationSettings = ChatNotificationSettings(
    useDefaultMuteFor = this.useDefaultMuteFor,
    muteFor = this.muteFor,
    useDefaultSound = this.useDefaultSound,
    soundId = this.soundId,
    useDefaultShowPreview = this.useDefaultShowPreview,
    showPreview = this.showPreview,
    useDefaultMuteStories = this.useDefaultMuteStories,
    muteStories = this.muteStories,
    useDefaultStorySound = this.useDefaultStorySound,
    storySoundId = this.storySoundId,
    useDefaultShowStoryPoster = this.useDefaultShowStoryPoster,
    showStoryPoster = this.showStoryPoster,
    useDefaultDisablePinnedMessageNotifications = this.useDefaultDisablePinnedMessageNotifications,
    disablePinnedMessageNotifications = this.disablePinnedMessageNotifications,
    useDefaultDisableMentionNotifications = this.useDefaultDisableMentionNotifications,
    disableMentionNotifications = this.disableMentionNotifications
)

fun TdApi.ChatPermissions.toDomain(): ChatPermissions = ChatPermissions(
    canSendBasicMessages = this.canSendBasicMessages,
    canSendAudios = this.canSendAudios,
    canSendDocuments = this.canSendDocuments,
    canSendPhotos = this.canSendPhotos,
    canSendVideos = this.canSendVideos,
    canSendVideoNotes = this.canSendVideoNotes,
    canSendVoiceNotes = this.canSendVoiceNotes,
    canSendPolls = this.canSendPolls,
    canSendOtherMessages = this.canSendOtherMessages,
    canAddLinkPreviews = this.canAddLinkPreviews,
    canChangeInfo = this.canChangeInfo,
    canInviteUsers = this.canInviteUsers,
    canPinMessages = this.canPinMessages,
    canCreateTopics = this.canCreateTopics
)

fun TdApi.ChatPhoto.toDomain(): ChatPhoto = ChatPhoto(
    id = this.id,
    addedDate = this.addedDate,
    minithumbnail = this.minithumbnail?.toDomain(),
    sizes = this.sizes.map { it.toDomain() },
    animation = this.animation?.toDomain(),
    smallAnimation = this.smallAnimation?.toDomain(),
    sticker = this.sticker?.toDomain()
)

fun TdApi.ChatPhotoInfo.toDomain(): ChatPhotoInfo = ChatPhotoInfo(
    small = this.small.toDomain(),
    big = this.big.toDomain(),
    minithumbnail = this.minithumbnail?.toDomain(),
    hasAnimation = this.hasAnimation,
    isPersonal = this.isPersonal
)

fun TdApi.ChatPhotoSticker.toDomain(): ChatPhotoSticker = ChatPhotoSticker(
    type = this.type.toDomain(),
    backgroundFill = this.backgroundFill.toDomain()
)

fun TdApi.ChatPhotoStickerType.toDomain(): ChatPhotoStickerType = when(this) {
    is TdApi.ChatPhotoStickerTypeRegularOrMask -> this.toDomain()
    is TdApi.ChatPhotoStickerTypeCustomEmoji -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ChatPhotoStickerTypeCustomEmoji.toDomain(): ChatPhotoStickerTypeCustomEmoji = ChatPhotoStickerTypeCustomEmoji(
    customEmojiId = this.customEmojiId
)

fun TdApi.ChatPhotoStickerTypeRegularOrMask.toDomain(): ChatPhotoStickerTypeRegularOrMask = ChatPhotoStickerTypeRegularOrMask(
    stickerSetId = this.stickerSetId,
    stickerId = this.stickerId
)

fun TdApi.ChatPhotos.toDomain(): ChatPhotos = ChatPhotos(
    totalCount = this.totalCount,
    photos = this.photos.map { it.toDomain() }
)

fun TdApi.ChatPosition.toDomain(): ChatPosition = ChatPosition(
    list = this.list.toDomain(),
    order = this.order,
    isPinned = this.isPinned,
    source = this.source?.toDomain()
)

fun TdApi.ChatRevenueAmount.toDomain(): ChatRevenueAmount = ChatRevenueAmount(
    cryptocurrency = this.cryptocurrency,
    totalAmount = this.totalAmount,
    balanceAmount = this.balanceAmount,
    availableAmount = this.availableAmount,
    withdrawalEnabled = this.withdrawalEnabled
)

fun TdApi.ChatRevenueStatistics.toDomain(): ChatRevenueStatistics = ChatRevenueStatistics(
    revenueByHourGraph = this.revenueByHourGraph.toDomain(),
    revenueGraph = this.revenueGraph.toDomain(),
    revenueAmount = this.revenueAmount.toDomain(),
    usdRate = this.usdRate
)

fun TdApi.ChatRevenueTransaction.toDomain(): ChatRevenueTransaction = ChatRevenueTransaction(
    cryptocurrency = this.cryptocurrency,
    cryptocurrencyAmount = this.cryptocurrencyAmount,
    type = this.type.toDomain()
)

fun TdApi.ChatRevenueTransactionType.toDomain(): ChatRevenueTransactionType = when(this) {
    is TdApi.ChatRevenueTransactionTypeUnsupported -> this.toDomain()
    is TdApi.ChatRevenueTransactionTypeSponsoredMessageEarnings -> this.toDomain()
    is TdApi.ChatRevenueTransactionTypeSuggestedPostEarnings -> this.toDomain()
    is TdApi.ChatRevenueTransactionTypeFragmentWithdrawal -> this.toDomain()
    is TdApi.ChatRevenueTransactionTypeFragmentRefund -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ChatRevenueTransactionTypeFragmentRefund.toDomain(): ChatRevenueTransactionTypeFragmentRefund = ChatRevenueTransactionTypeFragmentRefund(
    refundDate = this.refundDate
)

fun TdApi.ChatRevenueTransactionTypeFragmentWithdrawal.toDomain(): ChatRevenueTransactionTypeFragmentWithdrawal = ChatRevenueTransactionTypeFragmentWithdrawal(
    withdrawalDate = this.withdrawalDate,
    state = this.state.toDomain()
)

fun TdApi.ChatRevenueTransactionTypeSponsoredMessageEarnings.toDomain(): ChatRevenueTransactionTypeSponsoredMessageEarnings = ChatRevenueTransactionTypeSponsoredMessageEarnings(
    startDate = this.startDate,
    endDate = this.endDate
)

fun TdApi.ChatRevenueTransactionTypeSuggestedPostEarnings.toDomain(): ChatRevenueTransactionTypeSuggestedPostEarnings = ChatRevenueTransactionTypeSuggestedPostEarnings(
    userId = this.userId
)

fun TdApi.ChatRevenueTransactionTypeUnsupported.toDomain(): ChatRevenueTransactionTypeUnsupported = ChatRevenueTransactionTypeUnsupported

fun TdApi.ChatRevenueTransactions.toDomain(): ChatRevenueTransactions = ChatRevenueTransactions(
    tonAmount = this.tonAmount,
    transactions = this.transactions.map { it.toDomain() },
    nextOffset = this.nextOffset
)

fun TdApi.ChatSource.toDomain(): ChatSource = when(this) {
    is TdApi.ChatSourceMtprotoProxy -> this.toDomain()
    is TdApi.ChatSourcePublicServiceAnnouncement -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ChatSourceMtprotoProxy.toDomain(): ChatSourceMtprotoProxy = ChatSourceMtprotoProxy

fun TdApi.ChatSourcePublicServiceAnnouncement.toDomain(): ChatSourcePublicServiceAnnouncement = ChatSourcePublicServiceAnnouncement(
    type = this.type,
    text = this.text
)

fun TdApi.ChatStatistics.toDomain(): ChatStatistics = when(this) {
    is TdApi.ChatStatisticsSupergroup -> this.toDomain()
    is TdApi.ChatStatisticsChannel -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ChatStatisticsAdministratorActionsInfo.toDomain(): ChatStatisticsAdministratorActionsInfo = ChatStatisticsAdministratorActionsInfo(
    userId = this.userId,
    deletedMessageCount = this.deletedMessageCount,
    bannedUserCount = this.bannedUserCount,
    restrictedUserCount = this.restrictedUserCount
)

fun TdApi.ChatStatisticsChannel.toDomain(): ChatStatisticsChannel = ChatStatisticsChannel(
    period = this.period.toDomain(),
    memberCount = this.memberCount.toDomain(),
    meanMessageViewCount = this.meanMessageViewCount.toDomain(),
    meanMessageShareCount = this.meanMessageShareCount.toDomain(),
    meanMessageReactionCount = this.meanMessageReactionCount.toDomain(),
    meanStoryViewCount = this.meanStoryViewCount.toDomain(),
    meanStoryShareCount = this.meanStoryShareCount.toDomain(),
    meanStoryReactionCount = this.meanStoryReactionCount.toDomain(),
    enabledNotificationsPercentage = this.enabledNotificationsPercentage,
    memberCountGraph = this.memberCountGraph.toDomain(),
    joinGraph = this.joinGraph.toDomain(),
    muteGraph = this.muteGraph.toDomain(),
    viewCountByHourGraph = this.viewCountByHourGraph.toDomain(),
    viewCountBySourceGraph = this.viewCountBySourceGraph.toDomain(),
    joinBySourceGraph = this.joinBySourceGraph.toDomain(),
    languageGraph = this.languageGraph.toDomain(),
    messageInteractionGraph = this.messageInteractionGraph.toDomain(),
    messageReactionGraph = this.messageReactionGraph.toDomain(),
    storyInteractionGraph = this.storyInteractionGraph.toDomain(),
    storyReactionGraph = this.storyReactionGraph.toDomain(),
    instantViewInteractionGraph = this.instantViewInteractionGraph.toDomain(),
    recentInteractions = this.recentInteractions.map { it.toDomain() }
)

fun TdApi.ChatStatisticsInteractionInfo.toDomain(): ChatStatisticsInteractionInfo = ChatStatisticsInteractionInfo(
    objectType = this.objectType.toDomain(),
    viewCount = this.viewCount,
    forwardCount = this.forwardCount,
    reactionCount = this.reactionCount
)

fun TdApi.ChatStatisticsInviterInfo.toDomain(): ChatStatisticsInviterInfo = ChatStatisticsInviterInfo(
    userId = this.userId,
    addedMemberCount = this.addedMemberCount
)

fun TdApi.ChatStatisticsMessageSenderInfo.toDomain(): ChatStatisticsMessageSenderInfo = ChatStatisticsMessageSenderInfo(
    userId = this.userId,
    sentMessageCount = this.sentMessageCount,
    averageCharacterCount = this.averageCharacterCount
)

fun TdApi.ChatStatisticsObjectType.toDomain(): ChatStatisticsObjectType = when(this) {
    is TdApi.ChatStatisticsObjectTypeMessage -> this.toDomain()
    is TdApi.ChatStatisticsObjectTypeStory -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ChatStatisticsObjectTypeMessage.toDomain(): ChatStatisticsObjectTypeMessage = ChatStatisticsObjectTypeMessage(
    messageId = this.messageId
)

fun TdApi.ChatStatisticsObjectTypeStory.toDomain(): ChatStatisticsObjectTypeStory = ChatStatisticsObjectTypeStory(
    storyId = this.storyId
)

fun TdApi.ChatStatisticsSupergroup.toDomain(): ChatStatisticsSupergroup = ChatStatisticsSupergroup(
    period = this.period.toDomain(),
    memberCount = this.memberCount.toDomain(),
    messageCount = this.messageCount.toDomain(),
    viewerCount = this.viewerCount.toDomain(),
    senderCount = this.senderCount.toDomain(),
    memberCountGraph = this.memberCountGraph.toDomain(),
    joinGraph = this.joinGraph.toDomain(),
    joinBySourceGraph = this.joinBySourceGraph.toDomain(),
    languageGraph = this.languageGraph.toDomain(),
    messageContentGraph = this.messageContentGraph.toDomain(),
    actionGraph = this.actionGraph.toDomain(),
    dayGraph = this.dayGraph.toDomain(),
    weekGraph = this.weekGraph.toDomain(),
    topSenders = this.topSenders.map { it.toDomain() },
    topAdministrators = this.topAdministrators.map { it.toDomain() },
    topInviters = this.topInviters.map { it.toDomain() }
)

fun TdApi.ChatTheme.toDomain(): ChatTheme = when(this) {
    is TdApi.ChatThemeEmoji -> this.toDomain()
    is TdApi.ChatThemeGift -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ChatThemeEmoji.toDomain(): ChatThemeEmoji = ChatThemeEmoji(
    name = this.name
)

fun TdApi.ChatThemeGift.toDomain(): ChatThemeGift = ChatThemeGift(
    giftTheme = this.giftTheme.toDomain()
)

fun TdApi.ChatType.toDomain(): ChatType = when(this) {
    is TdApi.ChatTypePrivate -> this.toDomain()
    is TdApi.ChatTypeBasicGroup -> this.toDomain()
    is TdApi.ChatTypeSupergroup -> this.toDomain()
    is TdApi.ChatTypeSecret -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ChatTypeBasicGroup.toDomain(): ChatTypeBasicGroup = ChatTypeBasicGroup(
    basicGroupId = this.basicGroupId
)

fun TdApi.ChatTypePrivate.toDomain(): ChatTypePrivate = ChatTypePrivate(
    userId = this.userId
)

fun TdApi.ChatTypeSecret.toDomain(): ChatTypeSecret = ChatTypeSecret(
    secretChatId = this.secretChatId,
    userId = this.userId
)

fun TdApi.ChatTypeSupergroup.toDomain(): ChatTypeSupergroup = ChatTypeSupergroup(
    supergroupId = this.supergroupId,
    isChannel = this.isChannel
)

fun TdApi.Chats.toDomain(): Chats = Chats(
    totalCount = this.totalCount,
    chatIds = this.chatIds
)

fun TdApi.CheckChatFolderInviteLink.toDomain(): CheckChatFolderInviteLink = CheckChatFolderInviteLink(
    inviteLink = this.inviteLink
)

fun TdApi.CreateChatFolder.toDomain(): CreateChatFolder = CreateChatFolder(
    folder = this.folder.toDomain()
)

fun TdApi.CreateChatFolderInviteLink.toDomain(): CreateChatFolderInviteLink = CreateChatFolderInviteLink(
    chatFolderId = this.chatFolderId,
    name = this.name,
    chatIds = this.chatIds
)

fun TdApi.CreateForumTopic.toDomain(): CreateForumTopic = CreateForumTopic(
    chatId = this.chatId,
    name = this.name,
    icon = this.icon.toDomain()
)

fun TdApi.CreateNewBasicGroupChat.toDomain(): CreateNewBasicGroupChat = CreateNewBasicGroupChat(
    userIds = this.userIds,
    title = this.title,
    messageAutoDeleteTime = this.messageAutoDeleteTime
)

fun TdApi.CreateNewSupergroupChat.toDomain(): CreateNewSupergroupChat = CreateNewSupergroupChat(
    title = this.title,
    isForum = this.isForum,
    isChannel = this.isChannel,
    description = this.description,
    location = this.location.toDomain(),
    messageAutoDeleteTime = this.messageAutoDeleteTime,
    forImport = this.forImport
)

fun TdApi.CreatePrivateChat.toDomain(): CreatePrivateChat = CreatePrivateChat(
    userId = this.userId,
    force = this.force
)

fun TdApi.CreateSecretChat.toDomain(): CreateSecretChat = CreateSecretChat(
    secretChatId = this.secretChatId
)

fun TdApi.DeleteChat.toDomain(): DeleteChat = DeleteChat(
    chatId = this.chatId
)

fun TdApi.DeleteChatBackground.toDomain(): DeleteChatBackground = DeleteChatBackground(
    chatId = this.chatId,
    restorePrevious = this.restorePrevious
)

fun TdApi.DeleteChatFolder.toDomain(): DeleteChatFolder = DeleteChatFolder(
    chatFolderId = this.chatFolderId,
    leaveChatIds = this.leaveChatIds
)

fun TdApi.DeleteChatFolderInviteLink.toDomain(): DeleteChatFolderInviteLink = DeleteChatFolderInviteLink(
    chatFolderId = this.chatFolderId,
    inviteLink = this.inviteLink
)

fun TdApi.DeleteChatHistory.toDomain(): DeleteChatHistory = DeleteChatHistory(
    chatId = this.chatId,
    removeFromChatList = this.removeFromChatList,
    revoke = this.revoke
)

fun TdApi.DeleteChatMessagesBySender.toDomain(): DeleteChatMessagesBySender = DeleteChatMessagesBySender(
    chatId = this.chatId,
    senderId = this.senderId.toDomain()
)

fun TdApi.DeleteChatReplyMarkup.toDomain(): DeleteChatReplyMarkup = DeleteChatReplyMarkup(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.DeleteForumTopic.toDomain(): DeleteForumTopic = DeleteForumTopic(
    chatId = this.chatId,
    messageThreadId = this.messageThreadId
)

fun TdApi.EditChatFolderInviteLink.toDomain(): EditChatFolderInviteLink = EditChatFolderInviteLink(
    chatFolderId = this.chatFolderId,
    inviteLink = this.inviteLink,
    name = this.name,
    chatIds = this.chatIds
)

fun TdApi.EditForumTopic.toDomain(): EditForumTopic = EditForumTopic(
    chatId = this.chatId,
    messageThreadId = this.messageThreadId,
    name = this.name,
    editIconCustomEmoji = this.editIconCustomEmoji,
    iconCustomEmojiId = this.iconCustomEmojiId
)

fun TdApi.GetChat.toDomain(): GetChat = GetChat(
    chatId = this.chatId
)

fun TdApi.GetChatActiveStories.toDomain(): GetChatActiveStories = GetChatActiveStories(
    chatId = this.chatId
)

fun TdApi.GetChatAdministrators.toDomain(): GetChatAdministrators = GetChatAdministrators(
    chatId = this.chatId
)

fun TdApi.GetChatArchivedStories.toDomain(): GetChatArchivedStories = GetChatArchivedStories(
    chatId = this.chatId,
    fromStoryId = this.fromStoryId,
    limit = this.limit
)

fun TdApi.GetChatAvailableMessageSenders.toDomain(): GetChatAvailableMessageSenders = GetChatAvailableMessageSenders(
    chatId = this.chatId
)

fun TdApi.GetChatAvailablePaidMessageReactionSenders.toDomain(): GetChatAvailablePaidMessageReactionSenders = GetChatAvailablePaidMessageReactionSenders(
    chatId = this.chatId
)

fun TdApi.GetChatBoostFeatures.toDomain(): GetChatBoostFeatures = GetChatBoostFeatures(
    isChannel = this.isChannel
)

fun TdApi.GetChatBoostLevelFeatures.toDomain(): GetChatBoostLevelFeatures = GetChatBoostLevelFeatures(
    isChannel = this.isChannel,
    level = this.level
)

fun TdApi.GetChatBoostLink.toDomain(): GetChatBoostLink = GetChatBoostLink(
    chatId = this.chatId
)

fun TdApi.GetChatBoostLinkInfo.toDomain(): GetChatBoostLinkInfo = GetChatBoostLinkInfo(
    url = this.url
)

fun TdApi.GetChatBoostStatus.toDomain(): GetChatBoostStatus = GetChatBoostStatus(
    chatId = this.chatId
)

fun TdApi.GetChatBoosts.toDomain(): GetChatBoosts = GetChatBoosts(
    chatId = this.chatId,
    onlyGiftCodes = this.onlyGiftCodes,
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetChatEventLog.toDomain(): GetChatEventLog = GetChatEventLog(
    chatId = this.chatId,
    query = this.query,
    fromEventId = this.fromEventId,
    limit = this.limit,
    filters = this.filters.toDomain(),
    userIds = this.userIds
)

fun TdApi.GetChatFolder.toDomain(): GetChatFolder = GetChatFolder(
    chatFolderId = this.chatFolderId
)

fun TdApi.GetChatFolderChatCount.toDomain(): GetChatFolderChatCount = GetChatFolderChatCount(
    folder = this.folder.toDomain()
)

fun TdApi.GetChatFolderChatsToLeave.toDomain(): GetChatFolderChatsToLeave = GetChatFolderChatsToLeave(
    chatFolderId = this.chatFolderId
)

fun TdApi.GetChatFolderDefaultIconName.toDomain(): GetChatFolderDefaultIconName = GetChatFolderDefaultIconName(
    folder = this.folder.toDomain()
)

fun TdApi.GetChatFolderInviteLinks.toDomain(): GetChatFolderInviteLinks = GetChatFolderInviteLinks(
    chatFolderId = this.chatFolderId
)

fun TdApi.GetChatFolderNewChats.toDomain(): GetChatFolderNewChats = GetChatFolderNewChats(
    chatFolderId = this.chatFolderId
)

fun TdApi.GetChatHistory.toDomain(): GetChatHistory = GetChatHistory(
    chatId = this.chatId,
    fromMessageId = this.fromMessageId,
    offset = this.offset,
    limit = this.limit,
    onlyLocal = this.onlyLocal
)

fun TdApi.GetChatInviteLink.toDomain(): GetChatInviteLink = GetChatInviteLink(
    chatId = this.chatId,
    inviteLink = this.inviteLink
)

fun TdApi.GetChatInviteLinkCounts.toDomain(): GetChatInviteLinkCounts = GetChatInviteLinkCounts(
    chatId = this.chatId
)

fun TdApi.GetChatInviteLinkMembers.toDomain(): GetChatInviteLinkMembers = GetChatInviteLinkMembers(
    chatId = this.chatId,
    inviteLink = this.inviteLink,
    onlyWithExpiredSubscription = this.onlyWithExpiredSubscription,
    offsetMember = this.offsetMember.toDomain(),
    limit = this.limit
)

fun TdApi.GetChatInviteLinks.toDomain(): GetChatInviteLinks = GetChatInviteLinks(
    chatId = this.chatId,
    creatorUserId = this.creatorUserId,
    isRevoked = this.isRevoked,
    offsetDate = this.offsetDate,
    offsetInviteLink = this.offsetInviteLink,
    limit = this.limit
)

fun TdApi.GetChatJoinRequests.toDomain(): GetChatJoinRequests = GetChatJoinRequests(
    chatId = this.chatId,
    inviteLink = this.inviteLink,
    query = this.query,
    offsetRequest = this.offsetRequest.toDomain(),
    limit = this.limit
)

fun TdApi.GetChatListsToAddChat.toDomain(): GetChatListsToAddChat = GetChatListsToAddChat(
    chatId = this.chatId
)

fun TdApi.GetChatMember.toDomain(): GetChatMember = GetChatMember(
    chatId = this.chatId,
    memberId = this.memberId.toDomain()
)

fun TdApi.GetChatMessageCalendar.toDomain(): GetChatMessageCalendar = GetChatMessageCalendar(
    chatId = this.chatId,
    topicId = this.topicId.toDomain(),
    filter = this.filter.toDomain(),
    fromMessageId = this.fromMessageId
)

fun TdApi.GetChatMessageCount.toDomain(): GetChatMessageCount = GetChatMessageCount(
    chatId = this.chatId,
    topicId = this.topicId.toDomain(),
    filter = this.filter.toDomain(),
    returnLocal = this.returnLocal
)

fun TdApi.GetChatNotificationSettingsExceptions.toDomain(): GetChatNotificationSettingsExceptions = GetChatNotificationSettingsExceptions(
    scope = this.scope.toDomain(),
    compareSound = this.compareSound
)

fun TdApi.GetChatPinnedMessage.toDomain(): GetChatPinnedMessage = GetChatPinnedMessage(
    chatId = this.chatId
)

fun TdApi.GetChatPostedToChatPageStories.toDomain(): GetChatPostedToChatPageStories = GetChatPostedToChatPageStories(
    chatId = this.chatId,
    fromStoryId = this.fromStoryId,
    limit = this.limit
)

fun TdApi.GetChatRevenueStatistics.toDomain(): GetChatRevenueStatistics = GetChatRevenueStatistics(
    chatId = this.chatId,
    isDark = this.isDark
)

fun TdApi.GetChatRevenueTransactions.toDomain(): GetChatRevenueTransactions = GetChatRevenueTransactions(
    chatId = this.chatId,
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetChatRevenueWithdrawalUrl.toDomain(): GetChatRevenueWithdrawalUrl = GetChatRevenueWithdrawalUrl(
    chatId = this.chatId,
    password = this.password
)

fun TdApi.GetChatScheduledMessages.toDomain(): GetChatScheduledMessages = GetChatScheduledMessages(
    chatId = this.chatId
)

fun TdApi.GetChatSimilarChatCount.toDomain(): GetChatSimilarChatCount = GetChatSimilarChatCount(
    chatId = this.chatId,
    returnLocal = this.returnLocal
)

fun TdApi.GetChatSimilarChats.toDomain(): GetChatSimilarChats = GetChatSimilarChats(
    chatId = this.chatId
)

fun TdApi.GetChatSparseMessagePositions.toDomain(): GetChatSparseMessagePositions = GetChatSparseMessagePositions(
    chatId = this.chatId,
    filter = this.filter.toDomain(),
    fromMessageId = this.fromMessageId,
    limit = this.limit,
    savedMessagesTopicId = this.savedMessagesTopicId
)

fun TdApi.GetChatSponsoredMessages.toDomain(): GetChatSponsoredMessages = GetChatSponsoredMessages(
    chatId = this.chatId
)

fun TdApi.GetChatStatistics.toDomain(): GetChatStatistics = GetChatStatistics(
    chatId = this.chatId,
    isDark = this.isDark
)

fun TdApi.GetChatStoryAlbums.toDomain(): GetChatStoryAlbums = GetChatStoryAlbums(
    chatId = this.chatId
)

fun TdApi.GetChatStoryInteractions.toDomain(): GetChatStoryInteractions = GetChatStoryInteractions(
    storyPosterChatId = this.storyPosterChatId,
    storyId = this.storyId,
    reactionType = this.reactionType.toDomain(),
    preferForwards = this.preferForwards,
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetChats.toDomain(): GetChats = GetChats(
    chatList = this.chatList.toDomain(),
    limit = this.limit
)

fun TdApi.GetChatsForChatFolderInviteLink.toDomain(): GetChatsForChatFolderInviteLink = GetChatsForChatFolderInviteLink(
    chatFolderId = this.chatFolderId
)

fun TdApi.GetChatsToPostStories.toDomain(): GetChatsToPostStories = GetChatsToPostStories

fun TdApi.GetForumTopic.toDomain(): GetForumTopic = GetForumTopic(
    chatId = this.chatId,
    messageThreadId = this.messageThreadId
)

fun TdApi.GetForumTopicDefaultIcons.toDomain(): GetForumTopicDefaultIcons = GetForumTopicDefaultIcons

fun TdApi.GetForumTopicLink.toDomain(): GetForumTopicLink = GetForumTopicLink(
    chatId = this.chatId,
    messageThreadId = this.messageThreadId
)

fun TdApi.GetForumTopics.toDomain(): GetForumTopics = GetForumTopics(
    chatId = this.chatId,
    query = this.query,
    offsetDate = this.offsetDate,
    offsetMessageId = this.offsetMessageId,
    offsetMessageThreadId = this.offsetMessageThreadId,
    limit = this.limit
)

fun TdApi.JoinChat.toDomain(): JoinChat = JoinChat(
    chatId = this.chatId
)

fun TdApi.JoinChatByInviteLink.toDomain(): JoinChatByInviteLink = JoinChatByInviteLink(
    inviteLink = this.inviteLink
)

fun TdApi.LeaveChat.toDomain(): LeaveChat = LeaveChat(
    chatId = this.chatId
)

fun TdApi.LoadChats.toDomain(): LoadChats = LoadChats(
    chatList = this.chatList.toDomain(),
    limit = this.limit
)

fun TdApi.PinChatMessage.toDomain(): PinChatMessage = PinChatMessage(
    chatId = this.chatId,
    messageId = this.messageId,
    disableNotification = this.disableNotification,
    onlyForSelf = this.onlyForSelf
)

fun TdApi.SearchChatMessages.toDomain(): SearchChatMessages = SearchChatMessages(
    chatId = this.chatId,
    topicId = this.topicId.toDomain(),
    query = this.query,
    senderId = this.senderId.toDomain(),
    fromMessageId = this.fromMessageId,
    offset = this.offset,
    limit = this.limit,
    filter = this.filter.toDomain()
)

fun TdApi.SearchChats.toDomain(): SearchChats = SearchChats(
    query = this.query,
    limit = this.limit
)

fun TdApi.SearchChatsOnServer.toDomain(): SearchChatsOnServer = SearchChatsOnServer(
    query = this.query,
    limit = this.limit
)

fun TdApi.SearchPublicChat.toDomain(): SearchPublicChat = SearchPublicChat(
    username = this.username
)

fun TdApi.SearchPublicChats.toDomain(): SearchPublicChats = SearchPublicChats(
    query = this.query
)

fun TdApi.SetChatAvailableReactions.toDomain(): SetChatAvailableReactions = SetChatAvailableReactions(
    chatId = this.chatId,
    availableReactions = this.availableReactions.toDomain()
)

fun TdApi.SetChatClientData.toDomain(): SetChatClientData = SetChatClientData(
    chatId = this.chatId,
    clientData = this.clientData
)

fun TdApi.SetChatDescription.toDomain(): SetChatDescription = SetChatDescription(
    chatId = this.chatId,
    description = this.description
)

fun TdApi.SetChatDiscussionGroup.toDomain(): SetChatDiscussionGroup = SetChatDiscussionGroup(
    chatId = this.chatId,
    discussionChatId = this.discussionChatId
)

fun TdApi.SetChatNotificationSettings.toDomain(): SetChatNotificationSettings = SetChatNotificationSettings(
    chatId = this.chatId,
    notificationSettings = this.notificationSettings.toDomain()
)

fun TdApi.SetChatPermissions.toDomain(): SetChatPermissions = SetChatPermissions(
    chatId = this.chatId,
    permissions = this.permissions.toDomain()
)

fun TdApi.SetChatPhoto.toDomain(): SetChatPhoto = SetChatPhoto(
    chatId = this.chatId,
    photo = this.photo.toDomain()
)

fun TdApi.SetChatTitle.toDomain(): SetChatTitle = SetChatTitle(
    chatId = this.chatId,
    title = this.title
)

fun TdApi.SetForumTopicNotificationSettings.toDomain(): SetForumTopicNotificationSettings = SetForumTopicNotificationSettings(
    chatId = this.chatId,
    messageThreadId = this.messageThreadId,
    notificationSettings = this.notificationSettings.toDomain()
)

fun TdApi.ToggleChatIsMarkedAsUnread.toDomain(): ToggleChatIsMarkedAsUnread = ToggleChatIsMarkedAsUnread(
    chatId = this.chatId,
    isMarkedAsUnread = this.isMarkedAsUnread
)

fun TdApi.UnpinAllChatMessages.toDomain(): UnpinAllChatMessages = UnpinAllChatMessages(
    chatId = this.chatId
)

fun TdApi.UnpinAllMessageThreadMessages.toDomain(): UnpinAllMessageThreadMessages = UnpinAllMessageThreadMessages(
    chatId = this.chatId,
    messageThreadId = this.messageThreadId
)

fun TdApi.UnpinChatMessage.toDomain(): UnpinChatMessage = UnpinChatMessage(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.UpdateChatFolders.toDomain(): UpdateChatFolders = UpdateChatFolders(
    chatFolders = this.chatFolders.map { it.toDomain() },
    mainChatListPosition = this.mainChatListPosition,
    areTagsEnabled = this.areTagsEnabled
)

