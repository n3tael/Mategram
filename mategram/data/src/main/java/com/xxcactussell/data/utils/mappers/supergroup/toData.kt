package com.xxcactussell.data.utils.mappers.supergroup

import com.xxcactussell.data.utils.mappers.bot.toData
import com.xxcactussell.data.utils.mappers.bots.toData
import com.xxcactussell.data.utils.mappers.chat.toData
import com.xxcactussell.data.utils.mappers.profile.toData
import com.xxcactussell.data.utils.mappers.restriction.toData
import com.xxcactussell.data.utils.mappers.user.toData
import com.xxcactussell.data.utils.mappers.verification.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Supergroup.toData(): TdApi.Supergroup = TdApi.Supergroup(
    this.id,
    this.usernames?.toData(),
    this.date,
    this.status.toData(),
    this.memberCount,
    this.boostLevel,
    this.hasAutomaticTranslation,
    this.hasLinkedChat,
    this.hasLocation,
    this.signMessages,
    this.showMessageSender,
    this.joinToSendMessages,
    this.joinByRequest,
    this.isSlowModeEnabled,
    this.isChannel,
    this.isBroadcastGroup,
    this.isForum,
    this.isDirectMessagesGroup,
    this.isAdministeredDirectMessagesGroup,
    this.verificationStatus?.toData(),
    this.hasDirectMessagesGroup,
    this.hasForumTabs,
    this.restrictionInfo?.toData(),
    this.paidMessageStarCount,
    this.hasActiveStories,
    this.hasUnreadActiveStories
)

fun SupergroupFullInfo.toData(): TdApi.SupergroupFullInfo = TdApi.SupergroupFullInfo(
    this.photo?.toData(),
    this.description,
    this.memberCount,
    this.administratorCount,
    this.restrictedCount,
    this.bannedCount,
    this.linkedChatId,
    this.directMessagesChatId,
    this.slowModeDelay,
    this.slowModeDelayExpiresIn,
    this.canEnablePaidMessages,
    this.canEnablePaidReaction,
    this.canGetMembers,
    this.hasHiddenMembers,
    this.canHideMembers,
    this.canSetStickerSet,
    this.canSetLocation,
    this.canGetStatistics,
    this.canGetRevenueStatistics,
    this.canGetStarRevenueStatistics,
    this.canSendGift,
    this.canToggleAggressiveAntiSpam,
    this.isAllHistoryAvailable,
    this.canHaveSponsoredMessages,
    this.hasAggressiveAntiSpamEnabled,
    this.hasPaidMediaAllowed,
    this.hasPinnedStories,
    this.giftCount,
    this.myBoostCount,
    this.unrestrictBoostCount,
    this.outgoingPaidMessageStarCount,
    this.stickerSetId,
    this.customEmojiStickerSetId,
    this.location?.toData(),
    this.inviteLink?.toData(),
    this.botCommands.map { it.toData() }.toTypedArray(),
    this.botVerification?.toData(),
    this.mainProfileTab?.toData(),
    this.upgradedFromBasicGroupId,
    this.upgradedFromMaxMessageId
)

fun SupergroupMembersFilter.toData(): TdApi.SupergroupMembersFilter = when(this) {
    is SupergroupMembersFilterRecent -> this.toData()
    is SupergroupMembersFilterContacts -> this.toData()
    is SupergroupMembersFilterAdministrators -> this.toData()
    is SupergroupMembersFilterSearch -> this.toData()
    is SupergroupMembersFilterRestricted -> this.toData()
    is SupergroupMembersFilterBanned -> this.toData()
    is SupergroupMembersFilterMention -> this.toData()
    is SupergroupMembersFilterBots -> this.toData()
}

fun SupergroupMembersFilterAdministrators.toData(): TdApi.SupergroupMembersFilterAdministrators = TdApi.SupergroupMembersFilterAdministrators(
)

fun SupergroupMembersFilterBanned.toData(): TdApi.SupergroupMembersFilterBanned = TdApi.SupergroupMembersFilterBanned(
    this.query
)

fun SupergroupMembersFilterBots.toData(): TdApi.SupergroupMembersFilterBots = TdApi.SupergroupMembersFilterBots(
)

fun SupergroupMembersFilterContacts.toData(): TdApi.SupergroupMembersFilterContacts = TdApi.SupergroupMembersFilterContacts(
    this.query
)

fun SupergroupMembersFilterMention.toData(): TdApi.SupergroupMembersFilterMention = TdApi.SupergroupMembersFilterMention(
    this.query,
    this.messageThreadId
)

fun SupergroupMembersFilterRecent.toData(): TdApi.SupergroupMembersFilterRecent = TdApi.SupergroupMembersFilterRecent(
)

fun SupergroupMembersFilterRestricted.toData(): TdApi.SupergroupMembersFilterRestricted = TdApi.SupergroupMembersFilterRestricted(
    this.query
)

fun SupergroupMembersFilterSearch.toData(): TdApi.SupergroupMembersFilterSearch = TdApi.SupergroupMembersFilterSearch(
    this.query
)

