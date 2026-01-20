package com.xxcactussell.data.utils.mappers.stories

import com.xxcactussell.data.utils.mappers.block.toDomain
import com.xxcactussell.data.utils.mappers.file.toDomain
import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.input.toDomain
import com.xxcactussell.data.utils.mappers.location.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.minithumbnail.toDomain
import com.xxcactussell.data.utils.mappers.photo.toDomain
import com.xxcactussell.data.utils.mappers.reaction.toDomain
import com.xxcactussell.data.utils.mappers.statistical.toDomain
import com.xxcactussell.data.utils.mappers.venue.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ActivateStoryStealthMode.toDomain(): ActivateStoryStealthMode = ActivateStoryStealthMode

fun TdApi.CloseStory.toDomain(): CloseStory = CloseStory(
    storyPosterChatId = this.storyPosterChatId,
    storyId = this.storyId
)

fun TdApi.DeleteStory.toDomain(): DeleteStory = DeleteStory(
    storyPosterChatId = this.storyPosterChatId,
    storyId = this.storyId
)

fun TdApi.DeleteStoryAlbum.toDomain(): DeleteStoryAlbum = DeleteStoryAlbum(
    chatId = this.chatId,
    storyAlbumId = this.storyAlbumId
)

fun TdApi.EditStory.toDomain(): EditStory = EditStory(
    storyPosterChatId = this.storyPosterChatId,
    storyId = this.storyId,
    content = this.content.toDomain(),
    areas = this.areas.toDomain(),
    caption = this.caption.toDomain()
)

fun TdApi.EditStoryCover.toDomain(): EditStoryCover = EditStoryCover(
    storyPosterChatId = this.storyPosterChatId,
    storyId = this.storyId,
    coverFrameTimestamp = this.coverFrameTimestamp
)

fun TdApi.GetStory.toDomain(): GetStory = GetStory(
    storyPosterChatId = this.storyPosterChatId,
    storyId = this.storyId,
    onlyLocal = this.onlyLocal
)

fun TdApi.GetStoryAlbumStories.toDomain(): GetStoryAlbumStories = GetStoryAlbumStories(
    chatId = this.chatId,
    storyAlbumId = this.storyAlbumId,
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetStoryAvailableReactions.toDomain(): GetStoryAvailableReactions = GetStoryAvailableReactions(
    rowSize = this.rowSize
)

fun TdApi.GetStoryInteractions.toDomain(): GetStoryInteractions = GetStoryInteractions(
    storyId = this.storyId,
    query = this.query,
    onlyContacts = this.onlyContacts,
    preferForwards = this.preferForwards,
    preferWithReaction = this.preferWithReaction,
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetStoryNotificationSettingsExceptions.toDomain(): GetStoryNotificationSettingsExceptions = GetStoryNotificationSettingsExceptions

fun TdApi.GetStoryPublicForwards.toDomain(): GetStoryPublicForwards = GetStoryPublicForwards(
    storyPosterChatId = this.storyPosterChatId,
    storyId = this.storyId,
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetStoryStatistics.toDomain(): GetStoryStatistics = GetStoryStatistics(
    chatId = this.chatId,
    storyId = this.storyId,
    isDark = this.isDark
)

fun TdApi.InputStoryArea.toDomain(): InputStoryArea = InputStoryArea(
    position = this.position.toDomain(),
    type = this.type.toDomain()
)

fun TdApi.InputStoryAreaType.toDomain(): InputStoryAreaType = when(this) {
    is TdApi.InputStoryAreaTypeLocation -> this.toDomain()
    is TdApi.InputStoryAreaTypeFoundVenue -> this.toDomain()
    is TdApi.InputStoryAreaTypePreviousVenue -> this.toDomain()
    is TdApi.InputStoryAreaTypeSuggestedReaction -> this.toDomain()
    is TdApi.InputStoryAreaTypeMessage -> this.toDomain()
    is TdApi.InputStoryAreaTypeLink -> this.toDomain()
    is TdApi.InputStoryAreaTypeWeather -> this.toDomain()
    is TdApi.InputStoryAreaTypeUpgradedGift -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InputStoryAreaTypeFoundVenue.toDomain(): InputStoryAreaTypeFoundVenue = InputStoryAreaTypeFoundVenue(
    queryId = this.queryId,
    resultId = this.resultId
)

fun TdApi.InputStoryAreaTypeLink.toDomain(): InputStoryAreaTypeLink = InputStoryAreaTypeLink(
    url = this.url
)

fun TdApi.InputStoryAreaTypeLocation.toDomain(): InputStoryAreaTypeLocation = InputStoryAreaTypeLocation(
    location = this.location.toDomain(),
    address = this.address.toDomain()
)

fun TdApi.InputStoryAreaTypeMessage.toDomain(): InputStoryAreaTypeMessage = InputStoryAreaTypeMessage(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.InputStoryAreaTypePreviousVenue.toDomain(): InputStoryAreaTypePreviousVenue = InputStoryAreaTypePreviousVenue(
    venueProvider = this.venueProvider,
    venueId = this.venueId
)

fun TdApi.InputStoryAreaTypeSuggestedReaction.toDomain(): InputStoryAreaTypeSuggestedReaction = InputStoryAreaTypeSuggestedReaction(
    reactionType = this.reactionType.toDomain(),
    isDark = this.isDark,
    isFlipped = this.isFlipped
)

fun TdApi.InputStoryAreaTypeUpgradedGift.toDomain(): InputStoryAreaTypeUpgradedGift = InputStoryAreaTypeUpgradedGift(
    giftName = this.giftName
)

fun TdApi.InputStoryAreaTypeWeather.toDomain(): InputStoryAreaTypeWeather = InputStoryAreaTypeWeather(
    temperature = this.temperature,
    emoji = this.emoji,
    backgroundColor = this.backgroundColor
)

fun TdApi.InputStoryAreas.toDomain(): InputStoryAreas = InputStoryAreas(
    areas = this.areas.map { it.toDomain() }
)

fun TdApi.OpenStory.toDomain(): OpenStory = OpenStory(
    storyPosterChatId = this.storyPosterChatId,
    storyId = this.storyId
)

fun TdApi.PostStory.toDomain(): PostStory = PostStory(
    chatId = this.chatId,
    content = this.content.toDomain(),
    areas = this.areas.toDomain(),
    caption = this.caption.toDomain(),
    privacySettings = this.privacySettings.toDomain(),
    albumIds = this.albumIds,
    activePeriod = this.activePeriod,
    fromStoryFullId = this.fromStoryFullId.toDomain(),
    isPostedToChatPage = this.isPostedToChatPage,
    protectContent = this.protectContent
)

fun TdApi.SetStoryReaction.toDomain(): SetStoryReaction = SetStoryReaction(
    storyPosterChatId = this.storyPosterChatId,
    storyId = this.storyId,
    reactionType = this.reactionType.toDomain(),
    updateRecentReactions = this.updateRecentReactions
)

fun TdApi.Stories.toDomain(): Stories = Stories(
    totalCount = this.totalCount,
    stories = this.stories.map { it.toDomain() },
    pinnedStoryIds = this.pinnedStoryIds
)

fun TdApi.Story.toDomain(): Story = Story(
    id = this.id,
    posterChatId = this.posterChatId,
    posterId = this.posterId?.toDomain(),
    date = this.date,
    isBeingPosted = this.isBeingPosted,
    isBeingEdited = this.isBeingEdited,
    isEdited = this.isEdited,
    isPostedToChatPage = this.isPostedToChatPage,
    isVisibleOnlyForSelf = this.isVisibleOnlyForSelf,
    canBeAddedToAlbum = this.canBeAddedToAlbum,
    canBeDeleted = this.canBeDeleted,
    canBeEdited = this.canBeEdited,
    canBeForwarded = this.canBeForwarded,
    canBeReplied = this.canBeReplied,
    canToggleIsPostedToChatPage = this.canToggleIsPostedToChatPage,
    canGetStatistics = this.canGetStatistics,
    canGetInteractions = this.canGetInteractions,
    hasExpiredViewers = this.hasExpiredViewers,
    repostInfo = this.repostInfo?.toDomain(),
    interactionInfo = this.interactionInfo?.toDomain(),
    chosenReactionType = this.chosenReactionType?.toDomain(),
    privacySettings = this.privacySettings.toDomain(),
    content = this.content.toDomain(),
    areas = this.areas.map { it.toDomain() },
    caption = this.caption.toDomain(),
    albumIds = this.albumIds
)

fun TdApi.StoryAlbum.toDomain(): StoryAlbum = StoryAlbum(
    id = this.id,
    name = this.name,
    photoIcon = this.photoIcon?.toDomain(),
    videoIcon = this.videoIcon?.toDomain()
)

fun TdApi.StoryAlbums.toDomain(): StoryAlbums = StoryAlbums(
    albums = this.albums.map { it.toDomain() }
)

fun TdApi.StoryArea.toDomain(): StoryArea = StoryArea(
    position = this.position.toDomain(),
    type = this.type.toDomain()
)

fun TdApi.StoryAreaPosition.toDomain(): StoryAreaPosition = StoryAreaPosition(
    xPercentage = this.xPercentage,
    yPercentage = this.yPercentage,
    widthPercentage = this.widthPercentage,
    heightPercentage = this.heightPercentage,
    rotationAngle = this.rotationAngle,
    cornerRadiusPercentage = this.cornerRadiusPercentage
)

fun TdApi.StoryAreaType.toDomain(): StoryAreaType = when(this) {
    is TdApi.StoryAreaTypeLocation -> this.toDomain()
    is TdApi.StoryAreaTypeVenue -> this.toDomain()
    is TdApi.StoryAreaTypeSuggestedReaction -> this.toDomain()
    is TdApi.StoryAreaTypeMessage -> this.toDomain()
    is TdApi.StoryAreaTypeLink -> this.toDomain()
    is TdApi.StoryAreaTypeWeather -> this.toDomain()
    is TdApi.StoryAreaTypeUpgradedGift -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.StoryAreaTypeLink.toDomain(): StoryAreaTypeLink = StoryAreaTypeLink(
    url = this.url
)

fun TdApi.StoryAreaTypeLocation.toDomain(): StoryAreaTypeLocation = StoryAreaTypeLocation(
    location = this.location.toDomain(),
    address = this.address?.toDomain()
)

fun TdApi.StoryAreaTypeMessage.toDomain(): StoryAreaTypeMessage = StoryAreaTypeMessage(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.StoryAreaTypeSuggestedReaction.toDomain(): StoryAreaTypeSuggestedReaction = StoryAreaTypeSuggestedReaction(
    reactionType = this.reactionType.toDomain(),
    totalCount = this.totalCount,
    isDark = this.isDark,
    isFlipped = this.isFlipped
)

fun TdApi.StoryAreaTypeUpgradedGift.toDomain(): StoryAreaTypeUpgradedGift = StoryAreaTypeUpgradedGift(
    giftName = this.giftName
)

fun TdApi.StoryAreaTypeVenue.toDomain(): StoryAreaTypeVenue = StoryAreaTypeVenue(
    venue = this.venue.toDomain()
)

fun TdApi.StoryAreaTypeWeather.toDomain(): StoryAreaTypeWeather = StoryAreaTypeWeather(
    temperature = this.temperature,
    emoji = this.emoji,
    backgroundColor = this.backgroundColor
)

fun TdApi.StoryContent.toDomain(): StoryContent = when(this) {
    is TdApi.StoryContentPhoto -> this.toDomain()
    is TdApi.StoryContentVideo -> this.toDomain()
    is TdApi.StoryContentUnsupported -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.StoryContentPhoto.toDomain(): StoryContentPhoto = StoryContentPhoto(
    photo = this.photo.toDomain()
)

fun TdApi.StoryContentUnsupported.toDomain(): StoryContentUnsupported = StoryContentUnsupported

fun TdApi.StoryContentVideo.toDomain(): StoryContentVideo = StoryContentVideo(
    video = this.video.toDomain(),
    alternativeVideo = this.alternativeVideo?.toDomain()
)

fun TdApi.StoryFullId.toDomain(): StoryFullId = StoryFullId(
    posterChatId = this.posterChatId,
    storyId = this.storyId
)

fun TdApi.StoryInfo.toDomain(): StoryInfo = StoryInfo(
    storyId = this.storyId,
    date = this.date,
    isForCloseFriends = this.isForCloseFriends
)

fun TdApi.StoryInteraction.toDomain(): StoryInteraction = StoryInteraction(
    actorId = this.actorId.toDomain(),
    interactionDate = this.interactionDate,
    blockList = this.blockList?.toDomain(),
    type = this.type.toDomain()
)

fun TdApi.StoryInteractionInfo.toDomain(): StoryInteractionInfo = StoryInteractionInfo(
    viewCount = this.viewCount,
    forwardCount = this.forwardCount,
    reactionCount = this.reactionCount,
    recentViewerUserIds = this.recentViewerUserIds
)

fun TdApi.StoryInteractionType.toDomain(): StoryInteractionType = when(this) {
    is TdApi.StoryInteractionTypeView -> this.toDomain()
    is TdApi.StoryInteractionTypeForward -> this.toDomain()
    is TdApi.StoryInteractionTypeRepost -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.StoryInteractionTypeForward.toDomain(): StoryInteractionTypeForward = StoryInteractionTypeForward(
    message = this.message.toDomain()
)

fun TdApi.StoryInteractionTypeRepost.toDomain(): StoryInteractionTypeRepost = StoryInteractionTypeRepost(
    story = this.story.toDomain()
)

fun TdApi.StoryInteractionTypeView.toDomain(): StoryInteractionTypeView = StoryInteractionTypeView(
    chosenReactionType = this.chosenReactionType?.toDomain()
)

fun TdApi.StoryInteractions.toDomain(): StoryInteractions = StoryInteractions(
    totalCount = this.totalCount,
    totalForwardCount = this.totalForwardCount,
    totalReactionCount = this.totalReactionCount,
    interactions = this.interactions.map { it.toDomain() },
    nextOffset = this.nextOffset
)

fun TdApi.StoryList.toDomain(): StoryList = when(this) {
    is TdApi.StoryListMain -> this.toDomain()
    is TdApi.StoryListArchive -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.StoryListArchive.toDomain(): StoryListArchive = StoryListArchive

fun TdApi.StoryListMain.toDomain(): StoryListMain = StoryListMain

fun TdApi.StoryOrigin.toDomain(): StoryOrigin = when(this) {
    is TdApi.StoryOriginPublicStory -> this.toDomain()
    is TdApi.StoryOriginHiddenUser -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.StoryOriginHiddenUser.toDomain(): StoryOriginHiddenUser = StoryOriginHiddenUser(
    posterName = this.posterName
)

fun TdApi.StoryOriginPublicStory.toDomain(): StoryOriginPublicStory = StoryOriginPublicStory(
    chatId = this.chatId,
    storyId = this.storyId
)

fun TdApi.StoryPrivacySettings.toDomain(): StoryPrivacySettings = when(this) {
    is TdApi.StoryPrivacySettingsEveryone -> this.toDomain()
    is TdApi.StoryPrivacySettingsContacts -> this.toDomain()
    is TdApi.StoryPrivacySettingsCloseFriends -> this.toDomain()
    is TdApi.StoryPrivacySettingsSelectedUsers -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.StoryPrivacySettingsCloseFriends.toDomain(): StoryPrivacySettingsCloseFriends = StoryPrivacySettingsCloseFriends

fun TdApi.StoryPrivacySettingsContacts.toDomain(): StoryPrivacySettingsContacts = StoryPrivacySettingsContacts(
    exceptUserIds = this.exceptUserIds
)

fun TdApi.StoryPrivacySettingsEveryone.toDomain(): StoryPrivacySettingsEveryone = StoryPrivacySettingsEveryone(
    exceptUserIds = this.exceptUserIds
)

fun TdApi.StoryPrivacySettingsSelectedUsers.toDomain(): StoryPrivacySettingsSelectedUsers = StoryPrivacySettingsSelectedUsers(
    userIds = this.userIds
)

fun TdApi.StoryRepostInfo.toDomain(): StoryRepostInfo = StoryRepostInfo(
    origin = this.origin.toDomain(),
    isContentModified = this.isContentModified
)

fun TdApi.StoryStatistics.toDomain(): StoryStatistics = StoryStatistics(
    storyInteractionGraph = this.storyInteractionGraph.toDomain(),
    storyReactionGraph = this.storyReactionGraph.toDomain()
)

fun TdApi.StoryVideo.toDomain(): StoryVideo = StoryVideo(
    duration = this.duration,
    width = this.width,
    height = this.height,
    hasStickers = this.hasStickers,
    isAnimation = this.isAnimation,
    minithumbnail = this.minithumbnail?.toDomain(),
    thumbnail = this.thumbnail?.toDomain(),
    preloadPrefixSize = this.preloadPrefixSize,
    coverFrameTimestamp = this.coverFrameTimestamp,
    video = this.video.toDomain()
)

