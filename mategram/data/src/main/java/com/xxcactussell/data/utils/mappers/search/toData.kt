package com.xxcactussell.data.utils.mappers.search

import com.xxcactussell.data.utils.mappers.affiliate.toData
import com.xxcactussell.data.utils.mappers.chat.toData
import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.location.toData
import com.xxcactussell.data.utils.mappers.monetization.toData
import com.xxcactussell.data.utils.mappers.reaction.toData
import com.xxcactussell.data.utils.mappers.sticker.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun SearchAffiliatePrograms.toData(): TdApi.SearchAffiliatePrograms = TdApi.SearchAffiliatePrograms(
    this.affiliate.toData(),
    this.sortOrder.toData(),
    this.offset,
    this.limit
)

fun SearchBackground.toData(): TdApi.SearchBackground = TdApi.SearchBackground(
    this.name
)

fun SearchCallMessages.toData(): TdApi.SearchCallMessages = TdApi.SearchCallMessages(
    this.offset,
    this.limit,
    this.onlyMissed
)

fun SearchChatAffiliateProgram.toData(): TdApi.SearchChatAffiliateProgram = TdApi.SearchChatAffiliateProgram(
    this.username,
    this.referrer
)

fun SearchChatMembers.toData(): TdApi.SearchChatMembers = TdApi.SearchChatMembers(
    this.chatId,
    this.query,
    this.limit,
    this.filter.toData()
)

fun SearchChatRecentLocationMessages.toData(): TdApi.SearchChatRecentLocationMessages = TdApi.SearchChatRecentLocationMessages(
    this.chatId,
    this.limit
)

fun SearchEmojis.toData(): TdApi.SearchEmojis = TdApi.SearchEmojis(
    this.text,
    this.inputLanguageCodes.toTypedArray()
)

fun SearchFileDownloads.toData(): TdApi.SearchFileDownloads = TdApi.SearchFileDownloads(
    this.query,
    this.onlyActive,
    this.onlyCompleted,
    this.offset,
    this.limit
)

fun SearchGiftsForResale.toData(): TdApi.SearchGiftsForResale = TdApi.SearchGiftsForResale(
    this.giftId,
    this.order.toData(),
    this.attributes.map { it.toData() }.toTypedArray(),
    this.offset,
    this.limit
)

fun SearchHashtags.toData(): TdApi.SearchHashtags = TdApi.SearchHashtags(
    this.prefix,
    this.limit
)

fun SearchInstalledStickerSets.toData(): TdApi.SearchInstalledStickerSets = TdApi.SearchInstalledStickerSets(
    this.stickerType.toData(),
    this.query,
    this.limit
)

fun SearchMessages.toData(): TdApi.SearchMessages = TdApi.SearchMessages(
    this.chatList.toData(),
    this.query,
    this.offset,
    this.limit,
    this.filter.toData(),
    this.chatTypeFilter.toData(),
    this.minDate,
    this.maxDate
)

fun SearchMessagesChatTypeFilter.toData(): TdApi.SearchMessagesChatTypeFilter = when(this) {
    is SearchMessagesChatTypeFilterPrivate -> this.toData()
    is SearchMessagesChatTypeFilterGroup -> this.toData()
    is SearchMessagesChatTypeFilterChannel -> this.toData()
}

fun SearchMessagesChatTypeFilterChannel.toData(): TdApi.SearchMessagesChatTypeFilterChannel = TdApi.SearchMessagesChatTypeFilterChannel(
)

fun SearchMessagesChatTypeFilterGroup.toData(): TdApi.SearchMessagesChatTypeFilterGroup = TdApi.SearchMessagesChatTypeFilterGroup(
)

fun SearchMessagesChatTypeFilterPrivate.toData(): TdApi.SearchMessagesChatTypeFilterPrivate = TdApi.SearchMessagesChatTypeFilterPrivate(
)

fun SearchMessagesFilter.toData(): TdApi.SearchMessagesFilter = when(this) {
    is SearchMessagesFilterEmpty -> this.toData()
    is SearchMessagesFilterAnimation -> this.toData()
    is SearchMessagesFilterAudio -> this.toData()
    is SearchMessagesFilterDocument -> this.toData()
    is SearchMessagesFilterPhoto -> this.toData()
    is SearchMessagesFilterVideo -> this.toData()
    is SearchMessagesFilterVoiceNote -> this.toData()
    is SearchMessagesFilterPhotoAndVideo -> this.toData()
    is SearchMessagesFilterUrl -> this.toData()
    is SearchMessagesFilterChatPhoto -> this.toData()
    is SearchMessagesFilterVideoNote -> this.toData()
    is SearchMessagesFilterVoiceAndVideoNote -> this.toData()
    is SearchMessagesFilterMention -> this.toData()
    is SearchMessagesFilterUnreadMention -> this.toData()
    is SearchMessagesFilterUnreadReaction -> this.toData()
    is SearchMessagesFilterFailedToSend -> this.toData()
    is SearchMessagesFilterPinned -> this.toData()
}

fun SearchMessagesFilterAnimation.toData(): TdApi.SearchMessagesFilterAnimation = TdApi.SearchMessagesFilterAnimation(
)

fun SearchMessagesFilterAudio.toData(): TdApi.SearchMessagesFilterAudio = TdApi.SearchMessagesFilterAudio(
)

fun SearchMessagesFilterChatPhoto.toData(): TdApi.SearchMessagesFilterChatPhoto = TdApi.SearchMessagesFilterChatPhoto(
)

fun SearchMessagesFilterDocument.toData(): TdApi.SearchMessagesFilterDocument = TdApi.SearchMessagesFilterDocument(
)

fun SearchMessagesFilterEmpty.toData(): TdApi.SearchMessagesFilterEmpty = TdApi.SearchMessagesFilterEmpty(
)

fun SearchMessagesFilterFailedToSend.toData(): TdApi.SearchMessagesFilterFailedToSend = TdApi.SearchMessagesFilterFailedToSend(
)

fun SearchMessagesFilterMention.toData(): TdApi.SearchMessagesFilterMention = TdApi.SearchMessagesFilterMention(
)

fun SearchMessagesFilterPhoto.toData(): TdApi.SearchMessagesFilterPhoto = TdApi.SearchMessagesFilterPhoto(
)

fun SearchMessagesFilterPhotoAndVideo.toData(): TdApi.SearchMessagesFilterPhotoAndVideo = TdApi.SearchMessagesFilterPhotoAndVideo(
)

fun SearchMessagesFilterPinned.toData(): TdApi.SearchMessagesFilterPinned = TdApi.SearchMessagesFilterPinned(
)

fun SearchMessagesFilterUnreadMention.toData(): TdApi.SearchMessagesFilterUnreadMention = TdApi.SearchMessagesFilterUnreadMention(
)

fun SearchMessagesFilterUnreadReaction.toData(): TdApi.SearchMessagesFilterUnreadReaction = TdApi.SearchMessagesFilterUnreadReaction(
)

fun SearchMessagesFilterUrl.toData(): TdApi.SearchMessagesFilterUrl = TdApi.SearchMessagesFilterUrl(
)

fun SearchMessagesFilterVideo.toData(): TdApi.SearchMessagesFilterVideo = TdApi.SearchMessagesFilterVideo(
)

fun SearchMessagesFilterVideoNote.toData(): TdApi.SearchMessagesFilterVideoNote = TdApi.SearchMessagesFilterVideoNote(
)

fun SearchMessagesFilterVoiceAndVideoNote.toData(): TdApi.SearchMessagesFilterVoiceAndVideoNote = TdApi.SearchMessagesFilterVoiceAndVideoNote(
)

fun SearchMessagesFilterVoiceNote.toData(): TdApi.SearchMessagesFilterVoiceNote = TdApi.SearchMessagesFilterVoiceNote(
)

fun SearchOutgoingDocumentMessages.toData(): TdApi.SearchOutgoingDocumentMessages = TdApi.SearchOutgoingDocumentMessages(
    this.query,
    this.limit
)

fun SearchPublicMessagesByTag.toData(): TdApi.SearchPublicMessagesByTag = TdApi.SearchPublicMessagesByTag(
    this.tag,
    this.offset,
    this.limit
)

fun SearchPublicPosts.toData(): TdApi.SearchPublicPosts = TdApi.SearchPublicPosts(
    this.query,
    this.offset,
    this.limit,
    this.starCount
)

fun SearchPublicStoriesByLocation.toData(): TdApi.SearchPublicStoriesByLocation = TdApi.SearchPublicStoriesByLocation(
    this.address.toData(),
    this.offset,
    this.limit
)

fun SearchPublicStoriesByTag.toData(): TdApi.SearchPublicStoriesByTag = TdApi.SearchPublicStoriesByTag(
    this.storyPosterChatId,
    this.tag,
    this.offset,
    this.limit
)

fun SearchPublicStoriesByVenue.toData(): TdApi.SearchPublicStoriesByVenue = TdApi.SearchPublicStoriesByVenue(
    this.venueProvider,
    this.venueId,
    this.offset,
    this.limit
)

fun SearchQuote.toData(): TdApi.SearchQuote = TdApi.SearchQuote(
    this.text.toData(),
    this.quote.toData(),
    this.quotePosition
)

fun SearchRecentlyFoundChats.toData(): TdApi.SearchRecentlyFoundChats = TdApi.SearchRecentlyFoundChats(
    this.query,
    this.limit
)

fun SearchSavedMessages.toData(): TdApi.SearchSavedMessages = TdApi.SearchSavedMessages(
    this.savedMessagesTopicId,
    this.tag.toData(),
    this.query,
    this.fromMessageId,
    this.offset,
    this.limit
)

fun SearchSecretMessages.toData(): TdApi.SearchSecretMessages = TdApi.SearchSecretMessages(
    this.chatId,
    this.query,
    this.offset,
    this.limit,
    this.filter.toData()
)

fun SearchStickerSet.toData(): TdApi.SearchStickerSet = TdApi.SearchStickerSet(
    this.name,
    this.ignoreCache
)

fun SearchStickerSets.toData(): TdApi.SearchStickerSets = TdApi.SearchStickerSets(
    this.stickerType.toData(),
    this.query
)

fun SearchStickers.toData(): TdApi.SearchStickers = TdApi.SearchStickers(
    this.stickerType.toData(),
    this.emojis,
    this.query,
    this.inputLanguageCodes.toTypedArray(),
    this.offset,
    this.limit
)

fun SearchStringsByPrefix.toData(): TdApi.SearchStringsByPrefix = TdApi.SearchStringsByPrefix(
    this.strings.toTypedArray(),
    this.query,
    this.limit,
    this.returnNoneForEmptyQuery
)

fun SearchWebApp.toData(): TdApi.SearchWebApp = TdApi.SearchWebApp(
    this.botUserId,
    this.webAppShortName
)

