package com.xxcactussell.data.utils.mappers.emoji

import com.xxcactussell.data.utils.mappers.monetization.toDomain
import com.xxcactussell.data.utils.mappers.sticker.toDomain
import com.xxcactussell.data.utils.mappers.theme.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.EmojiCategories.toDomain(): EmojiCategories = EmojiCategories(
    categories = this.categories.map { it.toDomain() }
)

fun TdApi.EmojiCategory.toDomain(): EmojiCategory = EmojiCategory(
    name = this.name,
    icon = this.icon.toDomain(),
    source = this.source.toDomain(),
    isGreeting = this.isGreeting
)

fun TdApi.EmojiCategorySource.toDomain(): EmojiCategorySource = when(this) {
    is TdApi.EmojiCategorySourceSearch -> this.toDomain()
    is TdApi.EmojiCategorySourcePremium -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.EmojiCategorySourcePremium.toDomain(): EmojiCategorySourcePremium = EmojiCategorySourcePremium

fun TdApi.EmojiCategorySourceSearch.toDomain(): EmojiCategorySourceSearch = EmojiCategorySourceSearch(
    emojis = this.emojis.toList()
)

fun TdApi.EmojiCategoryType.toDomain(): EmojiCategoryType = when(this) {
    is TdApi.EmojiCategoryTypeDefault -> this.toDomain()
    is TdApi.EmojiCategoryTypeRegularStickers -> this.toDomain()
    is TdApi.EmojiCategoryTypeEmojiStatus -> this.toDomain()
    is TdApi.EmojiCategoryTypeChatPhoto -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.EmojiCategoryTypeChatPhoto.toDomain(): EmojiCategoryTypeChatPhoto = EmojiCategoryTypeChatPhoto

fun TdApi.EmojiCategoryTypeDefault.toDomain(): EmojiCategoryTypeDefault = EmojiCategoryTypeDefault

fun TdApi.EmojiCategoryTypeEmojiStatus.toDomain(): EmojiCategoryTypeEmojiStatus = EmojiCategoryTypeEmojiStatus

fun TdApi.EmojiCategoryTypeRegularStickers.toDomain(): EmojiCategoryTypeRegularStickers = EmojiCategoryTypeRegularStickers

fun TdApi.EmojiChatTheme.toDomain(): EmojiChatTheme = EmojiChatTheme(
    name = this.name,
    lightSettings = this.lightSettings.toDomain(),
    darkSettings = this.darkSettings.toDomain()
)

fun TdApi.EmojiKeyword.toDomain(): EmojiKeyword = EmojiKeyword(
    emoji = this.emoji,
    keyword = this.keyword
)

fun TdApi.EmojiKeywords.toDomain(): EmojiKeywords = EmojiKeywords(
    emojiKeywords = this.emojiKeywords.map { it.toDomain() }
)

fun TdApi.EmojiReaction.toDomain(): EmojiReaction = EmojiReaction(
    emoji = this.emoji,
    title = this.title,
    isActive = this.isActive,
    staticIcon = this.staticIcon.toDomain(),
    appearAnimation = this.appearAnimation.toDomain(),
    selectAnimation = this.selectAnimation.toDomain(),
    activateAnimation = this.activateAnimation.toDomain(),
    effectAnimation = this.effectAnimation.toDomain(),
    aroundAnimation = this.aroundAnimation?.toDomain(),
    centerAnimation = this.centerAnimation?.toDomain()
)

fun TdApi.EmojiStatus.toDomain(): EmojiStatus = EmojiStatus(
    type = this.type.toDomain(),
    expirationDate = this.expirationDate
)

fun TdApi.EmojiStatusCustomEmojis.toDomain(): EmojiStatusCustomEmojis = EmojiStatusCustomEmojis(
    customEmojiIds = this.customEmojiIds
)

fun TdApi.EmojiStatusType.toDomain(): EmojiStatusType = when(this) {
    is TdApi.EmojiStatusTypeCustomEmoji -> this.toDomain()
    is TdApi.EmojiStatusTypeUpgradedGift -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.EmojiStatusTypeCustomEmoji.toDomain(): EmojiStatusTypeCustomEmoji = EmojiStatusTypeCustomEmoji(
    customEmojiId = this.customEmojiId
)

fun TdApi.EmojiStatusTypeUpgradedGift.toDomain(): EmojiStatusTypeUpgradedGift = EmojiStatusTypeUpgradedGift(
    upgradedGiftId = this.upgradedGiftId,
    giftTitle = this.giftTitle,
    giftName = this.giftName,
    modelCustomEmojiId = this.modelCustomEmojiId,
    symbolCustomEmojiId = this.symbolCustomEmojiId,
    backdropColors = this.backdropColors.toDomain()
)

fun TdApi.EmojiStatuses.toDomain(): EmojiStatuses = EmojiStatuses(
    emojiStatuses = this.emojiStatuses.map { it.toDomain() }
)

