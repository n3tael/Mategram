package com.xxcactussell.data.utils.mappers.search

import com.xxcactussell.data.utils.mappers.affiliate.toDomain
import com.xxcactussell.data.utils.mappers.chat.toDomain
import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.location.toDomain
import com.xxcactussell.data.utils.mappers.monetization.toDomain
import com.xxcactussell.data.utils.mappers.reaction.toDomain
import com.xxcactussell.data.utils.mappers.sticker.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.SearchAffiliatePrograms.toDomain(): SearchAffiliatePrograms = SearchAffiliatePrograms(
    affiliate = this.affiliate.toDomain(),
    sortOrder = this.sortOrder.toDomain(),
    offset = this.offset,
    limit = this.limit
)

fun TdApi.SearchBackground.toDomain(): SearchBackground = SearchBackground(
    name = this.name
)

fun TdApi.SearchCallMessages.toDomain(): SearchCallMessages = SearchCallMessages(
    offset = this.offset,
    limit = this.limit,
    onlyMissed = this.onlyMissed
)

fun TdApi.SearchChatAffiliateProgram.toDomain(): SearchChatAffiliateProgram = SearchChatAffiliateProgram(
    username = this.username,
    referrer = this.referrer
)

fun TdApi.SearchChatMembers.toDomain(): SearchChatMembers = SearchChatMembers(
    chatId = this.chatId,
    query = this.query,
    limit = this.limit,
    filter = this.filter.toDomain()
)

fun TdApi.SearchChatRecentLocationMessages.toDomain(): SearchChatRecentLocationMessages = SearchChatRecentLocationMessages(
    chatId = this.chatId,
    limit = this.limit
)

fun TdApi.SearchEmojis.toDomain(): SearchEmojis = SearchEmojis(
    text = this.text,
    inputLanguageCodes = this.inputLanguageCodes.toList()
)

fun TdApi.SearchFileDownloads.toDomain(): SearchFileDownloads = SearchFileDownloads(
    query = this.query,
    onlyActive = this.onlyActive,
    onlyCompleted = this.onlyCompleted,
    offset = this.offset,
    limit = this.limit
)

fun TdApi.SearchGiftsForResale.toDomain(): SearchGiftsForResale = SearchGiftsForResale(
    giftId = this.giftId,
    order = this.order.toDomain(),
    attributes = this.attributes.map { it.toDomain() },
    offset = this.offset,
    limit = this.limit
)

fun TdApi.SearchHashtags.toDomain(): SearchHashtags = SearchHashtags(
    prefix = this.prefix,
    limit = this.limit
)

fun TdApi.SearchInstalledStickerSets.toDomain(): SearchInstalledStickerSets = SearchInstalledStickerSets(
    stickerType = this.stickerType.toDomain(),
    query = this.query,
    limit = this.limit
)

fun TdApi.SearchMessages.toDomain(): SearchMessages = SearchMessages(
    chatList = this.chatList.toDomain(),
    query = this.query,
    offset = this.offset,
    limit = this.limit,
    filter = this.filter.toDomain(),
    chatTypeFilter = this.chatTypeFilter.toDomain(),
    minDate = this.minDate,
    maxDate = this.maxDate
)

fun TdApi.SearchMessagesChatTypeFilter.toDomain(): SearchMessagesChatTypeFilter = when(this) {
    is TdApi.SearchMessagesChatTypeFilterPrivate -> this.toDomain()
    is TdApi.SearchMessagesChatTypeFilterGroup -> this.toDomain()
    is TdApi.SearchMessagesChatTypeFilterChannel -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.SearchMessagesChatTypeFilterChannel.toDomain(): SearchMessagesChatTypeFilterChannel = SearchMessagesChatTypeFilterChannel

fun TdApi.SearchMessagesChatTypeFilterGroup.toDomain(): SearchMessagesChatTypeFilterGroup = SearchMessagesChatTypeFilterGroup

fun TdApi.SearchMessagesChatTypeFilterPrivate.toDomain(): SearchMessagesChatTypeFilterPrivate = SearchMessagesChatTypeFilterPrivate

fun TdApi.SearchMessagesFilter.toDomain(): SearchMessagesFilter = when(this) {
    is TdApi.SearchMessagesFilterEmpty -> this.toDomain()
    is TdApi.SearchMessagesFilterAnimation -> this.toDomain()
    is TdApi.SearchMessagesFilterAudio -> this.toDomain()
    is TdApi.SearchMessagesFilterDocument -> this.toDomain()
    is TdApi.SearchMessagesFilterPhoto -> this.toDomain()
    is TdApi.SearchMessagesFilterVideo -> this.toDomain()
    is TdApi.SearchMessagesFilterVoiceNote -> this.toDomain()
    is TdApi.SearchMessagesFilterPhotoAndVideo -> this.toDomain()
    is TdApi.SearchMessagesFilterUrl -> this.toDomain()
    is TdApi.SearchMessagesFilterChatPhoto -> this.toDomain()
    is TdApi.SearchMessagesFilterVideoNote -> this.toDomain()
    is TdApi.SearchMessagesFilterVoiceAndVideoNote -> this.toDomain()
    is TdApi.SearchMessagesFilterMention -> this.toDomain()
    is TdApi.SearchMessagesFilterUnreadMention -> this.toDomain()
    is TdApi.SearchMessagesFilterUnreadReaction -> this.toDomain()
    is TdApi.SearchMessagesFilterFailedToSend -> this.toDomain()
    is TdApi.SearchMessagesFilterPinned -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.SearchMessagesFilterAnimation.toDomain(): SearchMessagesFilterAnimation = SearchMessagesFilterAnimation

fun TdApi.SearchMessagesFilterAudio.toDomain(): SearchMessagesFilterAudio = SearchMessagesFilterAudio

fun TdApi.SearchMessagesFilterChatPhoto.toDomain(): SearchMessagesFilterChatPhoto = SearchMessagesFilterChatPhoto

fun TdApi.SearchMessagesFilterDocument.toDomain(): SearchMessagesFilterDocument = SearchMessagesFilterDocument

fun TdApi.SearchMessagesFilterEmpty.toDomain(): SearchMessagesFilterEmpty = SearchMessagesFilterEmpty

fun TdApi.SearchMessagesFilterFailedToSend.toDomain(): SearchMessagesFilterFailedToSend = SearchMessagesFilterFailedToSend

fun TdApi.SearchMessagesFilterMention.toDomain(): SearchMessagesFilterMention = SearchMessagesFilterMention

fun TdApi.SearchMessagesFilterPhoto.toDomain(): SearchMessagesFilterPhoto = SearchMessagesFilterPhoto

fun TdApi.SearchMessagesFilterPhotoAndVideo.toDomain(): SearchMessagesFilterPhotoAndVideo = SearchMessagesFilterPhotoAndVideo

fun TdApi.SearchMessagesFilterPinned.toDomain(): SearchMessagesFilterPinned = SearchMessagesFilterPinned

fun TdApi.SearchMessagesFilterUnreadMention.toDomain(): SearchMessagesFilterUnreadMention = SearchMessagesFilterUnreadMention

fun TdApi.SearchMessagesFilterUnreadReaction.toDomain(): SearchMessagesFilterUnreadReaction = SearchMessagesFilterUnreadReaction

fun TdApi.SearchMessagesFilterUrl.toDomain(): SearchMessagesFilterUrl = SearchMessagesFilterUrl

fun TdApi.SearchMessagesFilterVideo.toDomain(): SearchMessagesFilterVideo = SearchMessagesFilterVideo

fun TdApi.SearchMessagesFilterVideoNote.toDomain(): SearchMessagesFilterVideoNote = SearchMessagesFilterVideoNote

fun TdApi.SearchMessagesFilterVoiceAndVideoNote.toDomain(): SearchMessagesFilterVoiceAndVideoNote = SearchMessagesFilterVoiceAndVideoNote

fun TdApi.SearchMessagesFilterVoiceNote.toDomain(): SearchMessagesFilterVoiceNote = SearchMessagesFilterVoiceNote

fun TdApi.SearchOutgoingDocumentMessages.toDomain(): SearchOutgoingDocumentMessages = SearchOutgoingDocumentMessages(
    query = this.query,
    limit = this.limit
)

fun TdApi.SearchPublicMessagesByTag.toDomain(): SearchPublicMessagesByTag = SearchPublicMessagesByTag(
    tag = this.tag,
    offset = this.offset,
    limit = this.limit
)

fun TdApi.SearchPublicPosts.toDomain(): SearchPublicPosts = SearchPublicPosts(
    query = this.query,
    offset = this.offset,
    limit = this.limit,
    starCount = this.starCount
)

fun TdApi.SearchPublicStoriesByLocation.toDomain(): SearchPublicStoriesByLocation = SearchPublicStoriesByLocation(
    address = this.address.toDomain(),
    offset = this.offset,
    limit = this.limit
)

fun TdApi.SearchPublicStoriesByTag.toDomain(): SearchPublicStoriesByTag = SearchPublicStoriesByTag(
    storyPosterChatId = this.storyPosterChatId,
    tag = this.tag,
    offset = this.offset,
    limit = this.limit
)

fun TdApi.SearchPublicStoriesByVenue.toDomain(): SearchPublicStoriesByVenue = SearchPublicStoriesByVenue(
    venueProvider = this.venueProvider,
    venueId = this.venueId,
    offset = this.offset,
    limit = this.limit
)

fun TdApi.SearchQuote.toDomain(): SearchQuote = SearchQuote(
    text = this.text.toDomain(),
    quote = this.quote.toDomain(),
    quotePosition = this.quotePosition
)

fun TdApi.SearchRecentlyFoundChats.toDomain(): SearchRecentlyFoundChats = SearchRecentlyFoundChats(
    query = this.query,
    limit = this.limit
)

fun TdApi.SearchSavedMessages.toDomain(): SearchSavedMessages = SearchSavedMessages(
    savedMessagesTopicId = this.savedMessagesTopicId,
    tag = this.tag.toDomain(),
    query = this.query,
    fromMessageId = this.fromMessageId,
    offset = this.offset,
    limit = this.limit
)

fun TdApi.SearchSecretMessages.toDomain(): SearchSecretMessages = SearchSecretMessages(
    chatId = this.chatId,
    query = this.query,
    offset = this.offset,
    limit = this.limit,
    filter = this.filter.toDomain()
)

fun TdApi.SearchStickerSet.toDomain(): SearchStickerSet = SearchStickerSet(
    name = this.name,
    ignoreCache = this.ignoreCache
)

fun TdApi.SearchStickerSets.toDomain(): SearchStickerSets = SearchStickerSets(
    stickerType = this.stickerType.toDomain(),
    query = this.query
)

fun TdApi.SearchStickers.toDomain(): SearchStickers = SearchStickers(
    stickerType = this.stickerType.toDomain(),
    emojis = this.emojis,
    query = this.query,
    inputLanguageCodes = this.inputLanguageCodes.toList(),
    offset = this.offset,
    limit = this.limit
)

fun TdApi.SearchStringsByPrefix.toDomain(): SearchStringsByPrefix = SearchStringsByPrefix(
    strings = this.strings.toList(),
    query = this.query,
    limit = this.limit,
    returnNoneForEmptyQuery = this.returnNoneForEmptyQuery
)

fun TdApi.SearchWebApp.toDomain(): SearchWebApp = SearchWebApp(
    botUserId = this.botUserId,
    webAppShortName = this.webAppShortName
)

