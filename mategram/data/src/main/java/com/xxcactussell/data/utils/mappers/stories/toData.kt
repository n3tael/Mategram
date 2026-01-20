package com.xxcactussell.data.utils.mappers.stories

import com.xxcactussell.data.utils.mappers.block.toData
import com.xxcactussell.data.utils.mappers.file.toData
import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.input.toData
import com.xxcactussell.data.utils.mappers.location.toData
import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.minithumbnail.toData
import com.xxcactussell.data.utils.mappers.photo.toData
import com.xxcactussell.data.utils.mappers.reaction.toData
import com.xxcactussell.data.utils.mappers.statistical.toData
import com.xxcactussell.data.utils.mappers.venue.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ActivateStoryStealthMode.toData(): TdApi.ActivateStoryStealthMode = TdApi.ActivateStoryStealthMode(
)

fun CloseStory.toData(): TdApi.CloseStory = TdApi.CloseStory(
    this.storyPosterChatId,
    this.storyId
)

fun DeleteStory.toData(): TdApi.DeleteStory = TdApi.DeleteStory(
    this.storyPosterChatId,
    this.storyId
)

fun DeleteStoryAlbum.toData(): TdApi.DeleteStoryAlbum = TdApi.DeleteStoryAlbum(
    this.chatId,
    this.storyAlbumId
)

fun EditStory.toData(): TdApi.EditStory = TdApi.EditStory(
    this.storyPosterChatId,
    this.storyId,
    this.content.toData(),
    this.areas.toData(),
    this.caption.toData()
)

fun EditStoryCover.toData(): TdApi.EditStoryCover = TdApi.EditStoryCover(
    this.storyPosterChatId,
    this.storyId,
    this.coverFrameTimestamp
)

fun GetStory.toData(): TdApi.GetStory = TdApi.GetStory(
    this.storyPosterChatId,
    this.storyId,
    this.onlyLocal
)

fun GetStoryAlbumStories.toData(): TdApi.GetStoryAlbumStories = TdApi.GetStoryAlbumStories(
    this.chatId,
    this.storyAlbumId,
    this.offset,
    this.limit
)

fun GetStoryAvailableReactions.toData(): TdApi.GetStoryAvailableReactions = TdApi.GetStoryAvailableReactions(
    this.rowSize
)

fun GetStoryInteractions.toData(): TdApi.GetStoryInteractions = TdApi.GetStoryInteractions(
    this.storyId,
    this.query,
    this.onlyContacts,
    this.preferForwards,
    this.preferWithReaction,
    this.offset,
    this.limit
)

fun GetStoryNotificationSettingsExceptions.toData(): TdApi.GetStoryNotificationSettingsExceptions = TdApi.GetStoryNotificationSettingsExceptions(
)

fun GetStoryPublicForwards.toData(): TdApi.GetStoryPublicForwards = TdApi.GetStoryPublicForwards(
    this.storyPosterChatId,
    this.storyId,
    this.offset,
    this.limit
)

fun GetStoryStatistics.toData(): TdApi.GetStoryStatistics = TdApi.GetStoryStatistics(
    this.chatId,
    this.storyId,
    this.isDark
)

fun InputStoryArea.toData(): TdApi.InputStoryArea = TdApi.InputStoryArea(
    this.position.toData(),
    this.type.toData()
)

fun InputStoryAreaType.toData(): TdApi.InputStoryAreaType = when(this) {
    is InputStoryAreaTypeLocation -> this.toData()
    is InputStoryAreaTypeFoundVenue -> this.toData()
    is InputStoryAreaTypePreviousVenue -> this.toData()
    is InputStoryAreaTypeSuggestedReaction -> this.toData()
    is InputStoryAreaTypeMessage -> this.toData()
    is InputStoryAreaTypeLink -> this.toData()
    is InputStoryAreaTypeWeather -> this.toData()
    is InputStoryAreaTypeUpgradedGift -> this.toData()
}

fun InputStoryAreaTypeFoundVenue.toData(): TdApi.InputStoryAreaTypeFoundVenue = TdApi.InputStoryAreaTypeFoundVenue(
    this.queryId,
    this.resultId
)

fun InputStoryAreaTypeLink.toData(): TdApi.InputStoryAreaTypeLink = TdApi.InputStoryAreaTypeLink(
    this.url
)

fun InputStoryAreaTypeLocation.toData(): TdApi.InputStoryAreaTypeLocation = TdApi.InputStoryAreaTypeLocation(
    this.location.toData(),
    this.address.toData()
)

fun InputStoryAreaTypeMessage.toData(): TdApi.InputStoryAreaTypeMessage = TdApi.InputStoryAreaTypeMessage(
    this.chatId,
    this.messageId
)

fun InputStoryAreaTypePreviousVenue.toData(): TdApi.InputStoryAreaTypePreviousVenue = TdApi.InputStoryAreaTypePreviousVenue(
    this.venueProvider,
    this.venueId
)

fun InputStoryAreaTypeSuggestedReaction.toData(): TdApi.InputStoryAreaTypeSuggestedReaction = TdApi.InputStoryAreaTypeSuggestedReaction(
    this.reactionType.toData(),
    this.isDark,
    this.isFlipped
)

fun InputStoryAreaTypeUpgradedGift.toData(): TdApi.InputStoryAreaTypeUpgradedGift = TdApi.InputStoryAreaTypeUpgradedGift(
    this.giftName
)

fun InputStoryAreaTypeWeather.toData(): TdApi.InputStoryAreaTypeWeather = TdApi.InputStoryAreaTypeWeather(
    this.temperature,
    this.emoji,
    this.backgroundColor
)

fun InputStoryAreas.toData(): TdApi.InputStoryAreas = TdApi.InputStoryAreas(
    this.areas.map { it.toData() }.toTypedArray()
)

fun OpenStory.toData(): TdApi.OpenStory = TdApi.OpenStory(
    this.storyPosterChatId,
    this.storyId
)

fun PostStory.toData(): TdApi.PostStory = TdApi.PostStory(
    this.chatId,
    this.content.toData(),
    this.areas.toData(),
    this.caption.toData(),
    this.privacySettings.toData(),
    this.albumIds,
    this.activePeriod,
    this.fromStoryFullId.toData(),
    this.isPostedToChatPage,
    this.protectContent
)

fun SetStoryReaction.toData(): TdApi.SetStoryReaction = TdApi.SetStoryReaction(
    this.storyPosterChatId,
    this.storyId,
    this.reactionType.toData(),
    this.updateRecentReactions
)

fun Stories.toData(): TdApi.Stories = TdApi.Stories(
    this.totalCount,
    this.stories.map { it.toData() }.toTypedArray(),
    this.pinnedStoryIds
)

fun Story.toData(): TdApi.Story = TdApi.Story(
    this.id,
    this.posterChatId,
    this.posterId?.toData(),
    this.date,
    this.isBeingPosted,
    this.isBeingEdited,
    this.isEdited,
    this.isPostedToChatPage,
    this.isVisibleOnlyForSelf,
    this.canBeAddedToAlbum,
    this.canBeDeleted,
    this.canBeEdited,
    this.canBeForwarded,
    this.canBeReplied,
    this.canToggleIsPostedToChatPage,
    this.canGetStatistics,
    this.canGetInteractions,
    this.hasExpiredViewers,
    this.repostInfo?.toData(),
    this.interactionInfo?.toData(),
    this.chosenReactionType?.toData(),
    this.privacySettings.toData(),
    this.content.toData(),
    this.areas.map { it.toData() }.toTypedArray(),
    this.caption.toData(),
    this.albumIds
)

fun StoryAlbum.toData(): TdApi.StoryAlbum = TdApi.StoryAlbum(
    this.id,
    this.name,
    this.photoIcon?.toData(),
    this.videoIcon?.toData()
)

fun StoryAlbums.toData(): TdApi.StoryAlbums = TdApi.StoryAlbums(
    this.albums.map { it.toData() }.toTypedArray()
)

fun StoryArea.toData(): TdApi.StoryArea = TdApi.StoryArea(
    this.position.toData(),
    this.type.toData()
)

fun StoryAreaPosition.toData(): TdApi.StoryAreaPosition = TdApi.StoryAreaPosition(
    this.xPercentage,
    this.yPercentage,
    this.widthPercentage,
    this.heightPercentage,
    this.rotationAngle,
    this.cornerRadiusPercentage
)

fun StoryAreaType.toData(): TdApi.StoryAreaType = when(this) {
    is StoryAreaTypeLocation -> this.toData()
    is StoryAreaTypeVenue -> this.toData()
    is StoryAreaTypeSuggestedReaction -> this.toData()
    is StoryAreaTypeMessage -> this.toData()
    is StoryAreaTypeLink -> this.toData()
    is StoryAreaTypeWeather -> this.toData()
    is StoryAreaTypeUpgradedGift -> this.toData()
}

fun StoryAreaTypeLink.toData(): TdApi.StoryAreaTypeLink = TdApi.StoryAreaTypeLink(
    this.url
)

fun StoryAreaTypeLocation.toData(): TdApi.StoryAreaTypeLocation = TdApi.StoryAreaTypeLocation(
    this.location.toData(),
    this.address?.toData()
)

fun StoryAreaTypeMessage.toData(): TdApi.StoryAreaTypeMessage = TdApi.StoryAreaTypeMessage(
    this.chatId,
    this.messageId
)

fun StoryAreaTypeSuggestedReaction.toData(): TdApi.StoryAreaTypeSuggestedReaction = TdApi.StoryAreaTypeSuggestedReaction(
    this.reactionType.toData(),
    this.totalCount,
    this.isDark,
    this.isFlipped
)

fun StoryAreaTypeUpgradedGift.toData(): TdApi.StoryAreaTypeUpgradedGift = TdApi.StoryAreaTypeUpgradedGift(
    this.giftName
)

fun StoryAreaTypeVenue.toData(): TdApi.StoryAreaTypeVenue = TdApi.StoryAreaTypeVenue(
    this.venue.toData()
)

fun StoryAreaTypeWeather.toData(): TdApi.StoryAreaTypeWeather = TdApi.StoryAreaTypeWeather(
    this.temperature,
    this.emoji,
    this.backgroundColor
)

fun StoryContent.toData(): TdApi.StoryContent = when(this) {
    is StoryContentPhoto -> this.toData()
    is StoryContentVideo -> this.toData()
    is StoryContentUnsupported -> this.toData()
}

fun StoryContentPhoto.toData(): TdApi.StoryContentPhoto = TdApi.StoryContentPhoto(
    this.photo.toData()
)

fun StoryContentUnsupported.toData(): TdApi.StoryContentUnsupported = TdApi.StoryContentUnsupported(
)

fun StoryContentVideo.toData(): TdApi.StoryContentVideo = TdApi.StoryContentVideo(
    this.video.toData(),
    this.alternativeVideo?.toData()
)

fun StoryFullId.toData(): TdApi.StoryFullId = TdApi.StoryFullId(
    this.posterChatId,
    this.storyId
)

fun StoryInfo.toData(): TdApi.StoryInfo = TdApi.StoryInfo(
    this.storyId,
    this.date,
    this.isForCloseFriends
)

fun StoryInteraction.toData(): TdApi.StoryInteraction = TdApi.StoryInteraction(
    this.actorId.toData(),
    this.interactionDate,
    this.blockList?.toData(),
    this.type.toData()
)

fun StoryInteractionInfo.toData(): TdApi.StoryInteractionInfo = TdApi.StoryInteractionInfo(
    this.viewCount,
    this.forwardCount,
    this.reactionCount,
    this.recentViewerUserIds
)

fun StoryInteractionType.toData(): TdApi.StoryInteractionType = when(this) {
    is StoryInteractionTypeView -> this.toData()
    is StoryInteractionTypeForward -> this.toData()
    is StoryInteractionTypeRepost -> this.toData()
}

fun StoryInteractionTypeForward.toData(): TdApi.StoryInteractionTypeForward = TdApi.StoryInteractionTypeForward(
    this.message.toData()
)

fun StoryInteractionTypeRepost.toData(): TdApi.StoryInteractionTypeRepost = TdApi.StoryInteractionTypeRepost(
    this.story.toData()
)

fun StoryInteractionTypeView.toData(): TdApi.StoryInteractionTypeView = TdApi.StoryInteractionTypeView(
    this.chosenReactionType?.toData()
)

fun StoryInteractions.toData(): TdApi.StoryInteractions = TdApi.StoryInteractions(
    this.totalCount,
    this.totalForwardCount,
    this.totalReactionCount,
    this.interactions.map { it.toData() }.toTypedArray(),
    this.nextOffset
)

fun StoryList.toData(): TdApi.StoryList = when(this) {
    is StoryListMain -> this.toData()
    is StoryListArchive -> this.toData()
}

fun StoryListArchive.toData(): TdApi.StoryListArchive = TdApi.StoryListArchive(
)

fun StoryListMain.toData(): TdApi.StoryListMain = TdApi.StoryListMain(
)

fun StoryOrigin.toData(): TdApi.StoryOrigin = when(this) {
    is StoryOriginPublicStory -> this.toData()
    is StoryOriginHiddenUser -> this.toData()
}

fun StoryOriginHiddenUser.toData(): TdApi.StoryOriginHiddenUser = TdApi.StoryOriginHiddenUser(
    this.posterName
)

fun StoryOriginPublicStory.toData(): TdApi.StoryOriginPublicStory = TdApi.StoryOriginPublicStory(
    this.chatId,
    this.storyId
)

fun StoryPrivacySettings.toData(): TdApi.StoryPrivacySettings = when(this) {
    is StoryPrivacySettingsEveryone -> this.toData()
    is StoryPrivacySettingsContacts -> this.toData()
    is StoryPrivacySettingsCloseFriends -> this.toData()
    is StoryPrivacySettingsSelectedUsers -> this.toData()
}

fun StoryPrivacySettingsCloseFriends.toData(): TdApi.StoryPrivacySettingsCloseFriends = TdApi.StoryPrivacySettingsCloseFriends(
)

fun StoryPrivacySettingsContacts.toData(): TdApi.StoryPrivacySettingsContacts = TdApi.StoryPrivacySettingsContacts(
    this.exceptUserIds
)

fun StoryPrivacySettingsEveryone.toData(): TdApi.StoryPrivacySettingsEveryone = TdApi.StoryPrivacySettingsEveryone(
    this.exceptUserIds
)

fun StoryPrivacySettingsSelectedUsers.toData(): TdApi.StoryPrivacySettingsSelectedUsers = TdApi.StoryPrivacySettingsSelectedUsers(
    this.userIds
)

fun StoryRepostInfo.toData(): TdApi.StoryRepostInfo = TdApi.StoryRepostInfo(
    this.origin.toData(),
    this.isContentModified
)

fun StoryStatistics.toData(): TdApi.StoryStatistics = TdApi.StoryStatistics(
    this.storyInteractionGraph.toData(),
    this.storyReactionGraph.toData()
)

fun StoryVideo.toData(): TdApi.StoryVideo = TdApi.StoryVideo(
    this.duration,
    this.width,
    this.height,
    this.hasStickers,
    this.isAnimation,
    this.minithumbnail?.toData(),
    this.thumbnail?.toData(),
    this.preloadPrefixSize,
    this.coverFrameTimestamp,
    this.video.toData()
)

