package com.xxcactussell.data.utils.mappers.emoji

import com.xxcactussell.data.utils.mappers.monetization.toData
import com.xxcactussell.data.utils.mappers.sticker.toData
import com.xxcactussell.data.utils.mappers.theme.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun EmojiCategories.toData(): TdApi.EmojiCategories = TdApi.EmojiCategories(
    this.categories.map { it.toData() }.toTypedArray()
)

fun EmojiCategory.toData(): TdApi.EmojiCategory = TdApi.EmojiCategory(
    this.name,
    this.icon.toData(),
    this.source.toData(),
    this.isGreeting
)

fun EmojiCategorySource.toData(): TdApi.EmojiCategorySource = when(this) {
    is EmojiCategorySourceSearch -> this.toData()
    is EmojiCategorySourcePremium -> this.toData()
}

fun EmojiCategorySourcePremium.toData(): TdApi.EmojiCategorySourcePremium = TdApi.EmojiCategorySourcePremium(
)

fun EmojiCategorySourceSearch.toData(): TdApi.EmojiCategorySourceSearch = TdApi.EmojiCategorySourceSearch(
    this.emojis.toTypedArray()
)

fun EmojiCategoryType.toData(): TdApi.EmojiCategoryType = when(this) {
    is EmojiCategoryTypeDefault -> this.toData()
    is EmojiCategoryTypeRegularStickers -> this.toData()
    is EmojiCategoryTypeEmojiStatus -> this.toData()
    is EmojiCategoryTypeChatPhoto -> this.toData()
}

fun EmojiCategoryTypeChatPhoto.toData(): TdApi.EmojiCategoryTypeChatPhoto = TdApi.EmojiCategoryTypeChatPhoto(
)

fun EmojiCategoryTypeDefault.toData(): TdApi.EmojiCategoryTypeDefault = TdApi.EmojiCategoryTypeDefault(
)

fun EmojiCategoryTypeEmojiStatus.toData(): TdApi.EmojiCategoryTypeEmojiStatus = TdApi.EmojiCategoryTypeEmojiStatus(
)

fun EmojiCategoryTypeRegularStickers.toData(): TdApi.EmojiCategoryTypeRegularStickers = TdApi.EmojiCategoryTypeRegularStickers(
)

fun EmojiChatTheme.toData(): TdApi.EmojiChatTheme = TdApi.EmojiChatTheme(
    this.name,
    this.lightSettings.toData(),
    this.darkSettings.toData()
)

fun EmojiKeyword.toData(): TdApi.EmojiKeyword = TdApi.EmojiKeyword(
    this.emoji,
    this.keyword
)

fun EmojiKeywords.toData(): TdApi.EmojiKeywords = TdApi.EmojiKeywords(
    this.emojiKeywords.map { it.toData() }.toTypedArray()
)

fun EmojiReaction.toData(): TdApi.EmojiReaction = TdApi.EmojiReaction(
    this.emoji,
    this.title,
    this.isActive,
    this.staticIcon.toData(),
    this.appearAnimation.toData(),
    this.selectAnimation.toData(),
    this.activateAnimation.toData(),
    this.effectAnimation.toData(),
    this.aroundAnimation?.toData(),
    this.centerAnimation?.toData()
)

fun EmojiStatus.toData(): TdApi.EmojiStatus = TdApi.EmojiStatus(
    this.type.toData(),
    this.expirationDate
)

fun EmojiStatusCustomEmojis.toData(): TdApi.EmojiStatusCustomEmojis = TdApi.EmojiStatusCustomEmojis(
    this.customEmojiIds
)

fun EmojiStatusType.toData(): TdApi.EmojiStatusType = when(this) {
    is EmojiStatusTypeCustomEmoji -> this.toData()
    is EmojiStatusTypeUpgradedGift -> this.toData()
}

fun EmojiStatusTypeCustomEmoji.toData(): TdApi.EmojiStatusTypeCustomEmoji = TdApi.EmojiStatusTypeCustomEmoji(
    this.customEmojiId
)

fun EmojiStatusTypeUpgradedGift.toData(): TdApi.EmojiStatusTypeUpgradedGift = TdApi.EmojiStatusTypeUpgradedGift(
    this.upgradedGiftId,
    this.giftTitle,
    this.giftName,
    this.modelCustomEmojiId,
    this.symbolCustomEmojiId,
    this.backdropColors.toData()
)

fun EmojiStatuses.toData(): TdApi.EmojiStatuses = TdApi.EmojiStatuses(
    this.emojiStatuses.map { it.toData() }.toTypedArray()
)

