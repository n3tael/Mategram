package com.xxcactussell.data.utils.mappers.user

import com.xxcactussell.data.utils.mappers.account.toData
import com.xxcactussell.data.utils.mappers.birthdate.toData
import com.xxcactussell.data.utils.mappers.block.toData
import com.xxcactussell.data.utils.mappers.bot.toData
import com.xxcactussell.data.utils.mappers.bots.toData
import com.xxcactussell.data.utils.mappers.chat.toData
import com.xxcactussell.data.utils.mappers.emoji.toData
import com.xxcactussell.data.utils.mappers.file.toData
import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.monetization.toData
import com.xxcactussell.data.utils.mappers.profile.toData
import com.xxcactussell.data.utils.mappers.reaction.toData
import com.xxcactussell.data.utils.mappers.restriction.toData
import com.xxcactussell.data.utils.mappers.supergroup.toData
import com.xxcactussell.data.utils.mappers.verification.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun AddContact.toData(): TdApi.AddContact = TdApi.AddContact(
    this.contact.toData(),
    this.sharePhoneNumber
)

fun BlockMessageSenderFromReplies.toData(): TdApi.BlockMessageSenderFromReplies = TdApi.BlockMessageSenderFromReplies(
    this.messageId,
    this.deleteMessage,
    this.deleteAllMessages,
    this.reportSpam
)

fun ChangeImportedContacts.toData(): TdApi.ChangeImportedContacts = TdApi.ChangeImportedContacts(
    this.contacts.map { it.toData() }.toTypedArray()
)

fun ClearImportedContacts.toData(): TdApi.ClearImportedContacts = TdApi.ClearImportedContacts(
)

fun Contact.toData(): TdApi.Contact = TdApi.Contact(
    this.phoneNumber,
    this.firstName,
    this.lastName,
    this.vcard,
    this.userId
)

fun FoundUsers.toData(): TdApi.FoundUsers = TdApi.FoundUsers(
    this.userIds,
    this.nextOffset
)

fun GetAccountTtl.toData(): TdApi.GetAccountTtl = TdApi.GetAccountTtl(
)

fun GetBasicGroup.toData(): TdApi.GetBasicGroup = TdApi.GetBasicGroup(
    this.basicGroupId
)

fun GetBasicGroupFullInfo.toData(): TdApi.GetBasicGroupFullInfo = TdApi.GetBasicGroupFullInfo(
    this.basicGroupId
)

fun GetBlockedMessageSenders.toData(): TdApi.GetBlockedMessageSenders = TdApi.GetBlockedMessageSenders(
    this.blockList.toData(),
    this.offset,
    this.limit
)

fun GetContacts.toData(): TdApi.GetContacts = TdApi.GetContacts(
)

fun GetMe.toData(): TdApi.GetMe = TdApi.GetMe(
)

fun GetMenuButton.toData(): TdApi.GetMenuButton = TdApi.GetMenuButton(
    this.userId
)

fun GetMessage.toData(): TdApi.GetMessage = TdApi.GetMessage(
    this.chatId,
    this.messageId
)

fun GetMessageAddedReactions.toData(): TdApi.GetMessageAddedReactions = TdApi.GetMessageAddedReactions(
    this.chatId,
    this.messageId,
    this.reactionType.toData(),
    this.offset,
    this.limit
)

fun GetMessageAuthor.toData(): TdApi.GetMessageAuthor = TdApi.GetMessageAuthor(
    this.chatId,
    this.messageId
)

fun GetMessageAvailableReactions.toData(): TdApi.GetMessageAvailableReactions = TdApi.GetMessageAvailableReactions(
    this.chatId,
    this.messageId,
    this.rowSize
)

fun GetMessageEffect.toData(): TdApi.GetMessageEffect = TdApi.GetMessageEffect(
    this.effectId
)

fun GetMessageEmbeddingCode.toData(): TdApi.GetMessageEmbeddingCode = TdApi.GetMessageEmbeddingCode(
    this.chatId,
    this.messageId,
    this.forAlbum
)

fun GetMessageFileType.toData(): TdApi.GetMessageFileType = TdApi.GetMessageFileType(
    this.messageFileHead
)

fun GetMessageImportConfirmationText.toData(): TdApi.GetMessageImportConfirmationText = TdApi.GetMessageImportConfirmationText(
    this.chatId
)

fun GetMessageLink.toData(): TdApi.GetMessageLink = TdApi.GetMessageLink(
    this.chatId,
    this.messageId,
    this.mediaTimestamp,
    this.forAlbum,
    this.inMessageThread
)

fun GetMessageLinkInfo.toData(): TdApi.GetMessageLinkInfo = TdApi.GetMessageLinkInfo(
    this.url
)

fun GetMessageLocally.toData(): TdApi.GetMessageLocally = TdApi.GetMessageLocally(
    this.chatId,
    this.messageId
)

fun GetMessageProperties.toData(): TdApi.GetMessageProperties = TdApi.GetMessageProperties(
    this.chatId,
    this.messageId
)

fun GetMessagePublicForwards.toData(): TdApi.GetMessagePublicForwards = TdApi.GetMessagePublicForwards(
    this.chatId,
    this.messageId,
    this.offset,
    this.limit
)

fun GetMessageReadDate.toData(): TdApi.GetMessageReadDate = TdApi.GetMessageReadDate(
    this.chatId,
    this.messageId
)

fun GetMessageStatistics.toData(): TdApi.GetMessageStatistics = TdApi.GetMessageStatistics(
    this.chatId,
    this.messageId,
    this.isDark
)

fun GetMessageViewers.toData(): TdApi.GetMessageViewers = TdApi.GetMessageViewers(
    this.chatId,
    this.messageId
)

fun GetSupergroup.toData(): TdApi.GetSupergroup = TdApi.GetSupergroup(
    this.supergroupId
)

fun GetSupergroupFullInfo.toData(): TdApi.GetSupergroupFullInfo = TdApi.GetSupergroupFullInfo(
    this.supergroupId
)

fun GetSupergroupMembers.toData(): TdApi.GetSupergroupMembers = TdApi.GetSupergroupMembers(
    this.supergroupId,
    this.filter.toData(),
    this.offset,
    this.limit
)

fun GetUser.toData(): TdApi.GetUser = TdApi.GetUser(
    this.userId
)

fun GetUserChatBoosts.toData(): TdApi.GetUserChatBoosts = TdApi.GetUserChatBoosts(
    this.chatId,
    this.userId
)

fun GetUserFullInfo.toData(): TdApi.GetUserFullInfo = TdApi.GetUserFullInfo(
    this.userId
)

fun GetUserLink.toData(): TdApi.GetUserLink = TdApi.GetUserLink(
)

fun GetUserPrivacySettingRules.toData(): TdApi.GetUserPrivacySettingRules = TdApi.GetUserPrivacySettingRules(
    this.setting.toData()
)

fun GetUserProfileAudios.toData(): TdApi.GetUserProfileAudios = TdApi.GetUserProfileAudios(
    this.userId,
    this.offset,
    this.limit
)

fun GetUserProfilePhotos.toData(): TdApi.GetUserProfilePhotos = TdApi.GetUserProfilePhotos(
    this.userId,
    this.offset,
    this.limit
)

fun GetUserSupportInfo.toData(): TdApi.GetUserSupportInfo = TdApi.GetUserSupportInfo(
    this.userId
)

fun ImportContacts.toData(): TdApi.ImportContacts = TdApi.ImportContacts(
    this.contacts.map { it.toData() }.toTypedArray()
)

fun ImportedContacts.toData(): TdApi.ImportedContacts = TdApi.ImportedContacts(
    this.userIds,
    this.importerCount
)

fun RemoveContacts.toData(): TdApi.RemoveContacts = TdApi.RemoveContacts(
    this.userIds
)

fun SearchContacts.toData(): TdApi.SearchContacts = TdApi.SearchContacts(
    this.query,
    this.limit
)

fun SearchUserByPhoneNumber.toData(): TdApi.SearchUserByPhoneNumber = TdApi.SearchUserByPhoneNumber(
    this.phoneNumber,
    this.onlyLocal
)

fun SearchUserByToken.toData(): TdApi.SearchUserByToken = TdApi.SearchUserByToken(
    this.token
)

fun SetAccountTtl.toData(): TdApi.SetAccountTtl = TdApi.SetAccountTtl(
    this.ttl.toData()
)

fun SetUserPrivacySettingRules.toData(): TdApi.SetUserPrivacySettingRules = TdApi.SetUserPrivacySettingRules(
    this.setting.toData(),
    this.rules.toData()
)

fun User.toData(): TdApi.User = TdApi.User(
    this.id,
    this.firstName,
    this.lastName,
    this.usernames?.toData(),
    this.phoneNumber,
    this.status.toData(),
    this.profilePhoto?.toData(),
    this.accentColorId,
    this.backgroundCustomEmojiId,
    this.profileAccentColorId,
    this.profileBackgroundCustomEmojiId,
    this.emojiStatus?.toData(),
    this.isContact,
    this.isMutualContact,
    this.isCloseFriend,
    this.verificationStatus?.toData(),
    this.isPremium,
    this.isSupport,
    this.restrictionInfo?.toData(),
    this.hasActiveStories,
    this.hasUnreadActiveStories,
    this.restrictsNewChats,
    this.paidMessageStarCount,
    this.haveAccess,
    this.type.toData(),
    this.languageCode,
    this.addedToAttachmentMenu
)

fun UserFullInfo.toData(): TdApi.UserFullInfo = TdApi.UserFullInfo(
    this.personalPhoto?.toData(),
    this.photo?.toData(),
    this.publicPhoto?.toData(),
    this.blockList?.toData(),
    this.canBeCalled,
    this.supportsVideoCalls,
    this.hasPrivateCalls,
    this.hasPrivateForwards,
    this.hasRestrictedVoiceAndVideoNoteMessages,
    this.hasPostedToProfileStories,
    this.hasSponsoredMessagesEnabled,
    this.needPhoneNumberPrivacyException,
    this.setChatBackground,
    this.bio?.toData(),
    this.birthdate?.toData(),
    this.personalChatId,
    this.giftCount,
    this.groupInCommonCount,
    this.incomingPaidMessageStarCount,
    this.outgoingPaidMessageStarCount,
    this.giftSettings.toData(),
    this.botVerification?.toData(),
    this.mainProfileTab?.toData(),
    this.firstProfileAudio?.toData(),
    this.rating?.toData(),
    this.pendingRating?.toData(),
    this.pendingRatingDate,
    this.businessInfo?.toData(),
    this.botInfo?.toData()
)

fun UserLink.toData(): TdApi.UserLink = TdApi.UserLink(
    this.url,
    this.expiresIn
)

fun UserPrivacySetting.toData(): TdApi.UserPrivacySetting = when(this) {
    is UserPrivacySettingShowStatus -> this.toData()
    is UserPrivacySettingShowProfilePhoto -> this.toData()
    is UserPrivacySettingShowLinkInForwardedMessages -> this.toData()
    is UserPrivacySettingShowPhoneNumber -> this.toData()
    is UserPrivacySettingShowBio -> this.toData()
    is UserPrivacySettingShowBirthdate -> this.toData()
    is UserPrivacySettingAllowChatInvites -> this.toData()
    is UserPrivacySettingAllowCalls -> this.toData()
    is UserPrivacySettingAllowPeerToPeerCalls -> this.toData()
    is UserPrivacySettingAllowFindingByPhoneNumber -> this.toData()
    is UserPrivacySettingAllowPrivateVoiceAndVideoNoteMessages -> this.toData()
    is UserPrivacySettingAutosaveGifts -> this.toData()
    is UserPrivacySettingAllowUnpaidMessages -> this.toData()
}

fun UserPrivacySettingAllowCalls.toData(): TdApi.UserPrivacySettingAllowCalls = TdApi.UserPrivacySettingAllowCalls(
)

fun UserPrivacySettingAllowChatInvites.toData(): TdApi.UserPrivacySettingAllowChatInvites = TdApi.UserPrivacySettingAllowChatInvites(
)

fun UserPrivacySettingAllowFindingByPhoneNumber.toData(): TdApi.UserPrivacySettingAllowFindingByPhoneNumber = TdApi.UserPrivacySettingAllowFindingByPhoneNumber(
)

fun UserPrivacySettingAllowPeerToPeerCalls.toData(): TdApi.UserPrivacySettingAllowPeerToPeerCalls = TdApi.UserPrivacySettingAllowPeerToPeerCalls(
)

fun UserPrivacySettingAllowPrivateVoiceAndVideoNoteMessages.toData(): TdApi.UserPrivacySettingAllowPrivateVoiceAndVideoNoteMessages = TdApi.UserPrivacySettingAllowPrivateVoiceAndVideoNoteMessages(
)

fun UserPrivacySettingAllowUnpaidMessages.toData(): TdApi.UserPrivacySettingAllowUnpaidMessages = TdApi.UserPrivacySettingAllowUnpaidMessages(
)

fun UserPrivacySettingAutosaveGifts.toData(): TdApi.UserPrivacySettingAutosaveGifts = TdApi.UserPrivacySettingAutosaveGifts(
)

fun UserPrivacySettingRule.toData(): TdApi.UserPrivacySettingRule = when(this) {
    is UserPrivacySettingRuleAllowAll -> this.toData()
    is UserPrivacySettingRuleAllowContacts -> this.toData()
    is UserPrivacySettingRuleAllowBots -> this.toData()
    is UserPrivacySettingRuleAllowPremiumUsers -> this.toData()
    is UserPrivacySettingRuleAllowUsers -> this.toData()
    is UserPrivacySettingRuleAllowChatMembers -> this.toData()
    is UserPrivacySettingRuleRestrictAll -> this.toData()
    is UserPrivacySettingRuleRestrictContacts -> this.toData()
    is UserPrivacySettingRuleRestrictBots -> this.toData()
    is UserPrivacySettingRuleRestrictUsers -> this.toData()
    is UserPrivacySettingRuleRestrictChatMembers -> this.toData()
}

fun UserPrivacySettingRuleAllowAll.toData(): TdApi.UserPrivacySettingRuleAllowAll = TdApi.UserPrivacySettingRuleAllowAll(
)

fun UserPrivacySettingRuleAllowBots.toData(): TdApi.UserPrivacySettingRuleAllowBots = TdApi.UserPrivacySettingRuleAllowBots(
)

fun UserPrivacySettingRuleAllowChatMembers.toData(): TdApi.UserPrivacySettingRuleAllowChatMembers = TdApi.UserPrivacySettingRuleAllowChatMembers(
    this.chatIds
)

fun UserPrivacySettingRuleAllowContacts.toData(): TdApi.UserPrivacySettingRuleAllowContacts = TdApi.UserPrivacySettingRuleAllowContacts(
)

fun UserPrivacySettingRuleAllowPremiumUsers.toData(): TdApi.UserPrivacySettingRuleAllowPremiumUsers = TdApi.UserPrivacySettingRuleAllowPremiumUsers(
)

fun UserPrivacySettingRuleAllowUsers.toData(): TdApi.UserPrivacySettingRuleAllowUsers = TdApi.UserPrivacySettingRuleAllowUsers(
    this.userIds
)

fun UserPrivacySettingRuleRestrictAll.toData(): TdApi.UserPrivacySettingRuleRestrictAll = TdApi.UserPrivacySettingRuleRestrictAll(
)

fun UserPrivacySettingRuleRestrictBots.toData(): TdApi.UserPrivacySettingRuleRestrictBots = TdApi.UserPrivacySettingRuleRestrictBots(
)

fun UserPrivacySettingRuleRestrictChatMembers.toData(): TdApi.UserPrivacySettingRuleRestrictChatMembers = TdApi.UserPrivacySettingRuleRestrictChatMembers(
    this.chatIds
)

fun UserPrivacySettingRuleRestrictContacts.toData(): TdApi.UserPrivacySettingRuleRestrictContacts = TdApi.UserPrivacySettingRuleRestrictContacts(
)

fun UserPrivacySettingRuleRestrictUsers.toData(): TdApi.UserPrivacySettingRuleRestrictUsers = TdApi.UserPrivacySettingRuleRestrictUsers(
    this.userIds
)

fun UserPrivacySettingRules.toData(): TdApi.UserPrivacySettingRules = TdApi.UserPrivacySettingRules(
    this.rules.map { it.toData() }.toTypedArray()
)

fun UserPrivacySettingShowBio.toData(): TdApi.UserPrivacySettingShowBio = TdApi.UserPrivacySettingShowBio(
)

fun UserPrivacySettingShowBirthdate.toData(): TdApi.UserPrivacySettingShowBirthdate = TdApi.UserPrivacySettingShowBirthdate(
)

fun UserPrivacySettingShowLinkInForwardedMessages.toData(): TdApi.UserPrivacySettingShowLinkInForwardedMessages = TdApi.UserPrivacySettingShowLinkInForwardedMessages(
)

fun UserPrivacySettingShowPhoneNumber.toData(): TdApi.UserPrivacySettingShowPhoneNumber = TdApi.UserPrivacySettingShowPhoneNumber(
)

fun UserPrivacySettingShowProfilePhoto.toData(): TdApi.UserPrivacySettingShowProfilePhoto = TdApi.UserPrivacySettingShowProfilePhoto(
)

fun UserPrivacySettingShowStatus.toData(): TdApi.UserPrivacySettingShowStatus = TdApi.UserPrivacySettingShowStatus(
)

fun UserRating.toData(): TdApi.UserRating = TdApi.UserRating(
    this.level,
    this.isMaximumLevelReached,
    this.rating,
    this.currentLevelRating,
    this.nextLevelRating
)

fun UserStatus.toData(): TdApi.UserStatus = when(this) {
    is UserStatusEmpty -> this.toData()
    is UserStatusOnline -> this.toData()
    is UserStatusOffline -> this.toData()
    is UserStatusRecently -> this.toData()
    is UserStatusLastWeek -> this.toData()
    is UserStatusLastMonth -> this.toData()
}

fun UserStatusEmpty.toData(): TdApi.UserStatusEmpty = TdApi.UserStatusEmpty(
)

fun UserStatusLastMonth.toData(): TdApi.UserStatusLastMonth = TdApi.UserStatusLastMonth(
    this.byMyPrivacySettings
)

fun UserStatusLastWeek.toData(): TdApi.UserStatusLastWeek = TdApi.UserStatusLastWeek(
    this.byMyPrivacySettings
)

fun UserStatusOffline.toData(): TdApi.UserStatusOffline = TdApi.UserStatusOffline(
    this.wasOnline
)

fun UserStatusOnline.toData(): TdApi.UserStatusOnline = TdApi.UserStatusOnline(
    this.expires
)

fun UserStatusRecently.toData(): TdApi.UserStatusRecently = TdApi.UserStatusRecently(
    this.byMyPrivacySettings
)

fun UserSupportInfo.toData(): TdApi.UserSupportInfo = TdApi.UserSupportInfo(
    this.message.toData(),
    this.author,
    this.date
)

fun UserType.toData(): TdApi.UserType = when(this) {
    is UserTypeRegular -> this.toData()
    is UserTypeDeleted -> this.toData()
    is UserTypeBot -> this.toData()
    is UserTypeUnknown -> this.toData()
}

fun UserTypeBot.toData(): TdApi.UserTypeBot = TdApi.UserTypeBot(
    this.canBeEdited,
    this.canJoinGroups,
    this.canReadAllGroupMessages,
    this.hasMainWebApp,
    this.isInline,
    this.inlineQueryPlaceholder,
    this.needLocation,
    this.canConnectToBusiness,
    this.canBeAddedToAttachmentMenu,
    this.activeUserCount
)

fun UserTypeDeleted.toData(): TdApi.UserTypeDeleted = TdApi.UserTypeDeleted(
)

fun UserTypeRegular.toData(): TdApi.UserTypeRegular = TdApi.UserTypeRegular(
)

fun UserTypeUnknown.toData(): TdApi.UserTypeUnknown = TdApi.UserTypeUnknown(
)

fun Usernames.toData(): TdApi.Usernames = TdApi.Usernames(
    this.activeUsernames.toTypedArray(),
    this.disabledUsernames.toTypedArray(),
    this.editableUsername
)

fun Users.toData(): TdApi.Users = TdApi.Users(
    this.totalCount,
    this.userIds
)

