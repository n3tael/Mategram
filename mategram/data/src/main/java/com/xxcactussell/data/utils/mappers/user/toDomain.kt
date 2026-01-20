package com.xxcactussell.data.utils.mappers.user

import com.xxcactussell.data.utils.mappers.account.toDomain
import com.xxcactussell.data.utils.mappers.birthdate.toDomain
import com.xxcactussell.data.utils.mappers.block.toDomain
import com.xxcactussell.data.utils.mappers.bot.toDomain
import com.xxcactussell.data.utils.mappers.bots.toDomain
import com.xxcactussell.data.utils.mappers.chat.toDomain
import com.xxcactussell.data.utils.mappers.emoji.toDomain
import com.xxcactussell.data.utils.mappers.file.toDomain
import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.monetization.toDomain
import com.xxcactussell.data.utils.mappers.profile.toDomain
import com.xxcactussell.data.utils.mappers.reaction.toDomain
import com.xxcactussell.data.utils.mappers.restriction.toDomain
import com.xxcactussell.data.utils.mappers.supergroup.toDomain
import com.xxcactussell.data.utils.mappers.verification.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AddContact.toDomain(): AddContact = AddContact(
    contact = this.contact.toDomain(),
    sharePhoneNumber = this.sharePhoneNumber
)

fun TdApi.BlockMessageSenderFromReplies.toDomain(): BlockMessageSenderFromReplies = BlockMessageSenderFromReplies(
    messageId = this.messageId,
    deleteMessage = this.deleteMessage,
    deleteAllMessages = this.deleteAllMessages,
    reportSpam = this.reportSpam
)

fun TdApi.ChangeImportedContacts.toDomain(): ChangeImportedContacts = ChangeImportedContacts(
    contacts = this.contacts.map { it.toDomain() }
)

fun TdApi.ClearImportedContacts.toDomain(): ClearImportedContacts = ClearImportedContacts

fun TdApi.Contact.toDomain(): Contact = Contact(
    phoneNumber = this.phoneNumber,
    firstName = this.firstName,
    lastName = this.lastName,
    vcard = this.vcard,
    userId = this.userId
)

fun TdApi.FoundUsers.toDomain(): FoundUsers = FoundUsers(
    userIds = this.userIds,
    nextOffset = this.nextOffset
)

fun TdApi.GetAccountTtl.toDomain(): GetAccountTtl = GetAccountTtl

fun TdApi.GetBasicGroup.toDomain(): GetBasicGroup = GetBasicGroup(
    basicGroupId = this.basicGroupId
)

fun TdApi.GetBasicGroupFullInfo.toDomain(): GetBasicGroupFullInfo = GetBasicGroupFullInfo(
    basicGroupId = this.basicGroupId
)

fun TdApi.GetBlockedMessageSenders.toDomain(): GetBlockedMessageSenders = GetBlockedMessageSenders(
    blockList = this.blockList.toDomain(),
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetContacts.toDomain(): GetContacts = GetContacts

fun TdApi.GetMe.toDomain(): GetMe = GetMe

fun TdApi.GetMenuButton.toDomain(): GetMenuButton = GetMenuButton(
    userId = this.userId
)

fun TdApi.GetMessage.toDomain(): GetMessage = GetMessage(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.GetMessageAddedReactions.toDomain(): GetMessageAddedReactions = GetMessageAddedReactions(
    chatId = this.chatId,
    messageId = this.messageId,
    reactionType = this.reactionType.toDomain(),
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetMessageAuthor.toDomain(): GetMessageAuthor = GetMessageAuthor(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.GetMessageAvailableReactions.toDomain(): GetMessageAvailableReactions = GetMessageAvailableReactions(
    chatId = this.chatId,
    messageId = this.messageId,
    rowSize = this.rowSize
)

fun TdApi.GetMessageEffect.toDomain(): GetMessageEffect = GetMessageEffect(
    effectId = this.effectId
)

fun TdApi.GetMessageEmbeddingCode.toDomain(): GetMessageEmbeddingCode = GetMessageEmbeddingCode(
    chatId = this.chatId,
    messageId = this.messageId,
    forAlbum = this.forAlbum
)

fun TdApi.GetMessageFileType.toDomain(): GetMessageFileType = GetMessageFileType(
    messageFileHead = this.messageFileHead
)

fun TdApi.GetMessageImportConfirmationText.toDomain(): GetMessageImportConfirmationText = GetMessageImportConfirmationText(
    chatId = this.chatId
)

fun TdApi.GetMessageLink.toDomain(): GetMessageLink = GetMessageLink(
    chatId = this.chatId,
    messageId = this.messageId,
    mediaTimestamp = this.mediaTimestamp,
    forAlbum = this.forAlbum,
    inMessageThread = this.inMessageThread
)

fun TdApi.GetMessageLinkInfo.toDomain(): GetMessageLinkInfo = GetMessageLinkInfo(
    url = this.url
)

fun TdApi.GetMessageLocally.toDomain(): GetMessageLocally = GetMessageLocally(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.GetMessageProperties.toDomain(): GetMessageProperties = GetMessageProperties(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.GetMessagePublicForwards.toDomain(): GetMessagePublicForwards = GetMessagePublicForwards(
    chatId = this.chatId,
    messageId = this.messageId,
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetMessageReadDate.toDomain(): GetMessageReadDate = GetMessageReadDate(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.GetMessageStatistics.toDomain(): GetMessageStatistics = GetMessageStatistics(
    chatId = this.chatId,
    messageId = this.messageId,
    isDark = this.isDark
)

fun TdApi.GetMessageViewers.toDomain(): GetMessageViewers = GetMessageViewers(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.GetSupergroup.toDomain(): GetSupergroup = GetSupergroup(
    supergroupId = this.supergroupId
)

fun TdApi.GetSupergroupFullInfo.toDomain(): GetSupergroupFullInfo = GetSupergroupFullInfo(
    supergroupId = this.supergroupId
)

fun TdApi.GetSupergroupMembers.toDomain(): GetSupergroupMembers = GetSupergroupMembers(
    supergroupId = this.supergroupId,
    filter = this.filter.toDomain(),
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetUser.toDomain(): GetUser = GetUser(
    userId = this.userId
)

fun TdApi.GetUserChatBoosts.toDomain(): GetUserChatBoosts = GetUserChatBoosts(
    chatId = this.chatId,
    userId = this.userId
)

fun TdApi.GetUserFullInfo.toDomain(): GetUserFullInfo = GetUserFullInfo(
    userId = this.userId
)

fun TdApi.GetUserLink.toDomain(): GetUserLink = GetUserLink

fun TdApi.GetUserPrivacySettingRules.toDomain(): GetUserPrivacySettingRules = GetUserPrivacySettingRules(
    setting = this.setting.toDomain()
)

fun TdApi.GetUserProfileAudios.toDomain(): GetUserProfileAudios = GetUserProfileAudios(
    userId = this.userId,
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetUserProfilePhotos.toDomain(): GetUserProfilePhotos = GetUserProfilePhotos(
    userId = this.userId,
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetUserSupportInfo.toDomain(): GetUserSupportInfo = GetUserSupportInfo(
    userId = this.userId
)

fun TdApi.ImportContacts.toDomain(): ImportContacts = ImportContacts(
    contacts = this.contacts.map { it.toDomain() }
)

fun TdApi.ImportedContacts.toDomain(): ImportedContacts = ImportedContacts(
    userIds = this.userIds,
    importerCount = this.importerCount
)

fun TdApi.RemoveContacts.toDomain(): RemoveContacts = RemoveContacts(
    userIds = this.userIds
)

fun TdApi.SearchContacts.toDomain(): SearchContacts = SearchContacts(
    query = this.query,
    limit = this.limit
)

fun TdApi.SearchUserByPhoneNumber.toDomain(): SearchUserByPhoneNumber = SearchUserByPhoneNumber(
    phoneNumber = this.phoneNumber,
    onlyLocal = this.onlyLocal
)

fun TdApi.SearchUserByToken.toDomain(): SearchUserByToken = SearchUserByToken(
    token = this.token
)

fun TdApi.SetAccountTtl.toDomain(): SetAccountTtl = SetAccountTtl(
    ttl = this.ttl.toDomain()
)

fun TdApi.SetUserPrivacySettingRules.toDomain(): SetUserPrivacySettingRules = SetUserPrivacySettingRules(
    setting = this.setting.toDomain(),
    rules = this.rules.toDomain()
)

fun TdApi.User.toDomain(): User = User(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    usernames = this.usernames?.toDomain(),
    phoneNumber = this.phoneNumber,
    status = this.status.toDomain(),
    profilePhoto = this.profilePhoto?.toDomain(),
    accentColorId = this.accentColorId,
    backgroundCustomEmojiId = this.backgroundCustomEmojiId,
    profileAccentColorId = this.profileAccentColorId,
    profileBackgroundCustomEmojiId = this.profileBackgroundCustomEmojiId,
    emojiStatus = this.emojiStatus?.toDomain(),
    isContact = this.isContact,
    isMutualContact = this.isMutualContact,
    isCloseFriend = this.isCloseFriend,
    verificationStatus = this.verificationStatus?.toDomain(),
    isPremium = this.isPremium,
    isSupport = this.isSupport,
    restrictionInfo = this.restrictionInfo?.toDomain(),
    hasActiveStories = this.hasActiveStories,
    hasUnreadActiveStories = this.hasUnreadActiveStories,
    restrictsNewChats = this.restrictsNewChats,
    paidMessageStarCount = this.paidMessageStarCount,
    haveAccess = this.haveAccess,
    type = this.type.toDomain(),
    languageCode = this.languageCode,
    addedToAttachmentMenu = this.addedToAttachmentMenu
)

fun TdApi.UserFullInfo.toDomain(): UserFullInfo = UserFullInfo(
    personalPhoto = this.personalPhoto?.toDomain(),
    photo = this.photo?.toDomain(),
    publicPhoto = this.publicPhoto?.toDomain(),
    blockList = this.blockList?.toDomain(),
    canBeCalled = this.canBeCalled,
    supportsVideoCalls = this.supportsVideoCalls,
    hasPrivateCalls = this.hasPrivateCalls,
    hasPrivateForwards = this.hasPrivateForwards,
    hasRestrictedVoiceAndVideoNoteMessages = this.hasRestrictedVoiceAndVideoNoteMessages,
    hasPostedToProfileStories = this.hasPostedToProfileStories,
    hasSponsoredMessagesEnabled = this.hasSponsoredMessagesEnabled,
    needPhoneNumberPrivacyException = this.needPhoneNumberPrivacyException,
    setChatBackground = this.setChatBackground,
    bio = this.bio?.toDomain(),
    birthdate = this.birthdate?.toDomain(),
    personalChatId = this.personalChatId,
    giftCount = this.giftCount,
    groupInCommonCount = this.groupInCommonCount,
    incomingPaidMessageStarCount = this.incomingPaidMessageStarCount,
    outgoingPaidMessageStarCount = this.outgoingPaidMessageStarCount,
    giftSettings = this.giftSettings.toDomain(),
    botVerification = this.botVerification?.toDomain(),
    mainProfileTab = this.mainProfileTab?.toDomain(),
    firstProfileAudio = this.firstProfileAudio?.toDomain(),
    rating = this.rating?.toDomain(),
    pendingRating = this.pendingRating?.toDomain(),
    pendingRatingDate = this.pendingRatingDate,
    businessInfo = this.businessInfo?.toDomain(),
    botInfo = this.botInfo?.toDomain()
)

fun TdApi.UserLink.toDomain(): UserLink = UserLink(
    url = this.url,
    expiresIn = this.expiresIn
)

fun TdApi.UserPrivacySetting.toDomain(): UserPrivacySetting = when(this) {
    is TdApi.UserPrivacySettingShowStatus -> this.toDomain()
    is TdApi.UserPrivacySettingShowProfilePhoto -> this.toDomain()
    is TdApi.UserPrivacySettingShowLinkInForwardedMessages -> this.toDomain()
    is TdApi.UserPrivacySettingShowPhoneNumber -> this.toDomain()
    is TdApi.UserPrivacySettingShowBio -> this.toDomain()
    is TdApi.UserPrivacySettingShowBirthdate -> this.toDomain()
    is TdApi.UserPrivacySettingAllowChatInvites -> this.toDomain()
    is TdApi.UserPrivacySettingAllowCalls -> this.toDomain()
    is TdApi.UserPrivacySettingAllowPeerToPeerCalls -> this.toDomain()
    is TdApi.UserPrivacySettingAllowFindingByPhoneNumber -> this.toDomain()
    is TdApi.UserPrivacySettingAllowPrivateVoiceAndVideoNoteMessages -> this.toDomain()
    is TdApi.UserPrivacySettingAutosaveGifts -> this.toDomain()
    is TdApi.UserPrivacySettingAllowUnpaidMessages -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.UserPrivacySettingAllowCalls.toDomain(): UserPrivacySettingAllowCalls = UserPrivacySettingAllowCalls

fun TdApi.UserPrivacySettingAllowChatInvites.toDomain(): UserPrivacySettingAllowChatInvites = UserPrivacySettingAllowChatInvites

fun TdApi.UserPrivacySettingAllowFindingByPhoneNumber.toDomain(): UserPrivacySettingAllowFindingByPhoneNumber = UserPrivacySettingAllowFindingByPhoneNumber

fun TdApi.UserPrivacySettingAllowPeerToPeerCalls.toDomain(): UserPrivacySettingAllowPeerToPeerCalls = UserPrivacySettingAllowPeerToPeerCalls

fun TdApi.UserPrivacySettingAllowPrivateVoiceAndVideoNoteMessages.toDomain(): UserPrivacySettingAllowPrivateVoiceAndVideoNoteMessages = UserPrivacySettingAllowPrivateVoiceAndVideoNoteMessages

fun TdApi.UserPrivacySettingAllowUnpaidMessages.toDomain(): UserPrivacySettingAllowUnpaidMessages = UserPrivacySettingAllowUnpaidMessages

fun TdApi.UserPrivacySettingAutosaveGifts.toDomain(): UserPrivacySettingAutosaveGifts = UserPrivacySettingAutosaveGifts

fun TdApi.UserPrivacySettingRule.toDomain(): UserPrivacySettingRule = when(this) {
    is TdApi.UserPrivacySettingRuleAllowAll -> this.toDomain()
    is TdApi.UserPrivacySettingRuleAllowContacts -> this.toDomain()
    is TdApi.UserPrivacySettingRuleAllowBots -> this.toDomain()
    is TdApi.UserPrivacySettingRuleAllowPremiumUsers -> this.toDomain()
    is TdApi.UserPrivacySettingRuleAllowUsers -> this.toDomain()
    is TdApi.UserPrivacySettingRuleAllowChatMembers -> this.toDomain()
    is TdApi.UserPrivacySettingRuleRestrictAll -> this.toDomain()
    is TdApi.UserPrivacySettingRuleRestrictContacts -> this.toDomain()
    is TdApi.UserPrivacySettingRuleRestrictBots -> this.toDomain()
    is TdApi.UserPrivacySettingRuleRestrictUsers -> this.toDomain()
    is TdApi.UserPrivacySettingRuleRestrictChatMembers -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.UserPrivacySettingRuleAllowAll.toDomain(): UserPrivacySettingRuleAllowAll = UserPrivacySettingRuleAllowAll

fun TdApi.UserPrivacySettingRuleAllowBots.toDomain(): UserPrivacySettingRuleAllowBots = UserPrivacySettingRuleAllowBots

fun TdApi.UserPrivacySettingRuleAllowChatMembers.toDomain(): UserPrivacySettingRuleAllowChatMembers = UserPrivacySettingRuleAllowChatMembers(
    chatIds = this.chatIds
)

fun TdApi.UserPrivacySettingRuleAllowContacts.toDomain(): UserPrivacySettingRuleAllowContacts = UserPrivacySettingRuleAllowContacts

fun TdApi.UserPrivacySettingRuleAllowPremiumUsers.toDomain(): UserPrivacySettingRuleAllowPremiumUsers = UserPrivacySettingRuleAllowPremiumUsers

fun TdApi.UserPrivacySettingRuleAllowUsers.toDomain(): UserPrivacySettingRuleAllowUsers = UserPrivacySettingRuleAllowUsers(
    userIds = this.userIds
)

fun TdApi.UserPrivacySettingRuleRestrictAll.toDomain(): UserPrivacySettingRuleRestrictAll = UserPrivacySettingRuleRestrictAll

fun TdApi.UserPrivacySettingRuleRestrictBots.toDomain(): UserPrivacySettingRuleRestrictBots = UserPrivacySettingRuleRestrictBots

fun TdApi.UserPrivacySettingRuleRestrictChatMembers.toDomain(): UserPrivacySettingRuleRestrictChatMembers = UserPrivacySettingRuleRestrictChatMembers(
    chatIds = this.chatIds
)

fun TdApi.UserPrivacySettingRuleRestrictContacts.toDomain(): UserPrivacySettingRuleRestrictContacts = UserPrivacySettingRuleRestrictContacts

fun TdApi.UserPrivacySettingRuleRestrictUsers.toDomain(): UserPrivacySettingRuleRestrictUsers = UserPrivacySettingRuleRestrictUsers(
    userIds = this.userIds
)

fun TdApi.UserPrivacySettingRules.toDomain(): UserPrivacySettingRules = UserPrivacySettingRules(
    rules = this.rules.map { it.toDomain() }
)

fun TdApi.UserPrivacySettingShowBio.toDomain(): UserPrivacySettingShowBio = UserPrivacySettingShowBio

fun TdApi.UserPrivacySettingShowBirthdate.toDomain(): UserPrivacySettingShowBirthdate = UserPrivacySettingShowBirthdate

fun TdApi.UserPrivacySettingShowLinkInForwardedMessages.toDomain(): UserPrivacySettingShowLinkInForwardedMessages = UserPrivacySettingShowLinkInForwardedMessages

fun TdApi.UserPrivacySettingShowPhoneNumber.toDomain(): UserPrivacySettingShowPhoneNumber = UserPrivacySettingShowPhoneNumber

fun TdApi.UserPrivacySettingShowProfilePhoto.toDomain(): UserPrivacySettingShowProfilePhoto = UserPrivacySettingShowProfilePhoto

fun TdApi.UserPrivacySettingShowStatus.toDomain(): UserPrivacySettingShowStatus = UserPrivacySettingShowStatus

fun TdApi.UserRating.toDomain(): UserRating = UserRating(
    level = this.level,
    isMaximumLevelReached = this.isMaximumLevelReached,
    rating = this.rating,
    currentLevelRating = this.currentLevelRating,
    nextLevelRating = this.nextLevelRating
)

fun TdApi.UserStatus.toDomain(): UserStatus = when(this) {
    is TdApi.UserStatusEmpty -> this.toDomain()
    is TdApi.UserStatusOnline -> this.toDomain()
    is TdApi.UserStatusOffline -> this.toDomain()
    is TdApi.UserStatusRecently -> this.toDomain()
    is TdApi.UserStatusLastWeek -> this.toDomain()
    is TdApi.UserStatusLastMonth -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.UserStatusEmpty.toDomain(): UserStatusEmpty = UserStatusEmpty

fun TdApi.UserStatusLastMonth.toDomain(): UserStatusLastMonth = UserStatusLastMonth(
    byMyPrivacySettings = this.byMyPrivacySettings
)

fun TdApi.UserStatusLastWeek.toDomain(): UserStatusLastWeek = UserStatusLastWeek(
    byMyPrivacySettings = this.byMyPrivacySettings
)

fun TdApi.UserStatusOffline.toDomain(): UserStatusOffline = UserStatusOffline(
    wasOnline = this.wasOnline
)

fun TdApi.UserStatusOnline.toDomain(): UserStatusOnline = UserStatusOnline(
    expires = this.expires
)

fun TdApi.UserStatusRecently.toDomain(): UserStatusRecently = UserStatusRecently(
    byMyPrivacySettings = this.byMyPrivacySettings
)

fun TdApi.UserSupportInfo.toDomain(): UserSupportInfo = UserSupportInfo(
    message = this.message.toDomain(),
    author = this.author,
    date = this.date
)

fun TdApi.UserType.toDomain(): UserType = when(this) {
    is TdApi.UserTypeRegular -> this.toDomain()
    is TdApi.UserTypeDeleted -> this.toDomain()
    is TdApi.UserTypeBot -> this.toDomain()
    is TdApi.UserTypeUnknown -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.UserTypeBot.toDomain(): UserTypeBot = UserTypeBot(
    canBeEdited = this.canBeEdited,
    canJoinGroups = this.canJoinGroups,
    canReadAllGroupMessages = this.canReadAllGroupMessages,
    hasMainWebApp = this.hasMainWebApp,
    isInline = this.isInline,
    inlineQueryPlaceholder = this.inlineQueryPlaceholder,
    needLocation = this.needLocation,
    canConnectToBusiness = this.canConnectToBusiness,
    canBeAddedToAttachmentMenu = this.canBeAddedToAttachmentMenu,
    activeUserCount = this.activeUserCount
)

fun TdApi.UserTypeDeleted.toDomain(): UserTypeDeleted = UserTypeDeleted

fun TdApi.UserTypeRegular.toDomain(): UserTypeRegular = UserTypeRegular

fun TdApi.UserTypeUnknown.toDomain(): UserTypeUnknown = UserTypeUnknown

fun TdApi.Usernames.toDomain(): Usernames = Usernames(
    activeUsernames = this.activeUsernames.toList(),
    disabledUsernames = this.disabledUsernames.toList(),
    editableUsername = this.editableUsername
)

fun TdApi.Users.toDomain(): Users = Users(
    totalCount = this.totalCount,
    userIds = this.userIds
)

