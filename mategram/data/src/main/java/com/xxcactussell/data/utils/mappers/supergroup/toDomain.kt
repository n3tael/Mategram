package com.xxcactussell.data.utils.mappers.supergroup

import com.xxcactussell.data.utils.mappers.bot.toDomain
import com.xxcactussell.data.utils.mappers.bots.toDomain
import com.xxcactussell.data.utils.mappers.chat.toDomain
import com.xxcactussell.data.utils.mappers.profile.toDomain
import com.xxcactussell.data.utils.mappers.restriction.toDomain
import com.xxcactussell.data.utils.mappers.user.toDomain
import com.xxcactussell.data.utils.mappers.verification.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Supergroup.toDomain(): Supergroup = Supergroup(
    id = this.id,
    usernames = this.usernames?.toDomain(),
    date = this.date,
    status = this.status.toDomain(),
    memberCount = this.memberCount,
    boostLevel = this.boostLevel,
    hasAutomaticTranslation = this.hasAutomaticTranslation,
    hasLinkedChat = this.hasLinkedChat,
    hasLocation = this.hasLocation,
    signMessages = this.signMessages,
    showMessageSender = this.showMessageSender,
    joinToSendMessages = this.joinToSendMessages,
    joinByRequest = this.joinByRequest,
    isSlowModeEnabled = this.isSlowModeEnabled,
    isChannel = this.isChannel,
    isBroadcastGroup = this.isBroadcastGroup,
    isForum = this.isForum,
    isDirectMessagesGroup = this.isDirectMessagesGroup,
    isAdministeredDirectMessagesGroup = this.isAdministeredDirectMessagesGroup,
    verificationStatus = this.verificationStatus?.toDomain(),
    hasDirectMessagesGroup = this.hasDirectMessagesGroup,
    hasForumTabs = this.hasForumTabs,
    restrictionInfo = this.restrictionInfo?.toDomain(),
    paidMessageStarCount = this.paidMessageStarCount,
    hasActiveStories = this.hasActiveStories,
    hasUnreadActiveStories = this.hasUnreadActiveStories
)

fun TdApi.SupergroupFullInfo.toDomain(): SupergroupFullInfo = SupergroupFullInfo(
    photo = this.photo?.toDomain(),
    description = this.description,
    memberCount = this.memberCount,
    administratorCount = this.administratorCount,
    restrictedCount = this.restrictedCount,
    bannedCount = this.bannedCount,
    linkedChatId = this.linkedChatId,
    directMessagesChatId = this.directMessagesChatId,
    slowModeDelay = this.slowModeDelay,
    slowModeDelayExpiresIn = this.slowModeDelayExpiresIn,
    canEnablePaidMessages = this.canEnablePaidMessages,
    canEnablePaidReaction = this.canEnablePaidReaction,
    canGetMembers = this.canGetMembers,
    hasHiddenMembers = this.hasHiddenMembers,
    canHideMembers = this.canHideMembers,
    canSetStickerSet = this.canSetStickerSet,
    canSetLocation = this.canSetLocation,
    canGetStatistics = this.canGetStatistics,
    canGetRevenueStatistics = this.canGetRevenueStatistics,
    canGetStarRevenueStatistics = this.canGetStarRevenueStatistics,
    canSendGift = this.canSendGift,
    canToggleAggressiveAntiSpam = this.canToggleAggressiveAntiSpam,
    isAllHistoryAvailable = this.isAllHistoryAvailable,
    canHaveSponsoredMessages = this.canHaveSponsoredMessages,
    hasAggressiveAntiSpamEnabled = this.hasAggressiveAntiSpamEnabled,
    hasPaidMediaAllowed = this.hasPaidMediaAllowed,
    hasPinnedStories = this.hasPinnedStories,
    giftCount = this.giftCount,
    myBoostCount = this.myBoostCount,
    unrestrictBoostCount = this.unrestrictBoostCount,
    outgoingPaidMessageStarCount = this.outgoingPaidMessageStarCount,
    stickerSetId = this.stickerSetId,
    customEmojiStickerSetId = this.customEmojiStickerSetId,
    location = this.location?.toDomain(),
    inviteLink = this.inviteLink?.toDomain(),
    botCommands = this.botCommands.map { it.toDomain() },
    botVerification = this.botVerification?.toDomain(),
    mainProfileTab = this.mainProfileTab?.toDomain(),
    upgradedFromBasicGroupId = this.upgradedFromBasicGroupId,
    upgradedFromMaxMessageId = this.upgradedFromMaxMessageId
)

fun TdApi.SupergroupMembersFilter.toDomain(): SupergroupMembersFilter = when(this) {
    is TdApi.SupergroupMembersFilterRecent -> this.toDomain()
    is TdApi.SupergroupMembersFilterContacts -> this.toDomain()
    is TdApi.SupergroupMembersFilterAdministrators -> this.toDomain()
    is TdApi.SupergroupMembersFilterSearch -> this.toDomain()
    is TdApi.SupergroupMembersFilterRestricted -> this.toDomain()
    is TdApi.SupergroupMembersFilterBanned -> this.toDomain()
    is TdApi.SupergroupMembersFilterMention -> this.toDomain()
    is TdApi.SupergroupMembersFilterBots -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.SupergroupMembersFilterAdministrators.toDomain(): SupergroupMembersFilterAdministrators = SupergroupMembersFilterAdministrators

fun TdApi.SupergroupMembersFilterBanned.toDomain(): SupergroupMembersFilterBanned = SupergroupMembersFilterBanned(
    query = this.query
)

fun TdApi.SupergroupMembersFilterBots.toDomain(): SupergroupMembersFilterBots = SupergroupMembersFilterBots

fun TdApi.SupergroupMembersFilterContacts.toDomain(): SupergroupMembersFilterContacts = SupergroupMembersFilterContacts(
    query = this.query
)

fun TdApi.SupergroupMembersFilterMention.toDomain(): SupergroupMembersFilterMention = SupergroupMembersFilterMention(
    query = this.query,
    messageThreadId = this.messageThreadId
)

fun TdApi.SupergroupMembersFilterRecent.toDomain(): SupergroupMembersFilterRecent = SupergroupMembersFilterRecent

fun TdApi.SupergroupMembersFilterRestricted.toDomain(): SupergroupMembersFilterRestricted = SupergroupMembersFilterRestricted(
    query = this.query
)

fun TdApi.SupergroupMembersFilterSearch.toDomain(): SupergroupMembersFilterSearch = SupergroupMembersFilterSearch(
    query = this.query
)

