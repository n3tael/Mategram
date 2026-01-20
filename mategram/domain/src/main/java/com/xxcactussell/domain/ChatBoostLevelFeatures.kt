package com.xxcactussell.domain

data class ChatBoostLevelFeatures(
    val level: Int,
    val storyPerDayCount: Int,
    val customEmojiReactionCount: Int,
    val titleColorCount: Int,
    val profileAccentColorCount: Int,
    val canSetProfileBackgroundCustomEmoji: Boolean,
    val accentColorCount: Int,
    val canSetBackgroundCustomEmoji: Boolean,
    val canSetEmojiStatus: Boolean,
    val chatThemeBackgroundCount: Int,
    val canSetCustomBackground: Boolean,
    val canSetCustomEmojiStickerSet: Boolean,
    val canEnableAutomaticTranslation: Boolean,
    val canRecognizeSpeech: Boolean,
    val canDisableSponsoredMessages: Boolean
) : Object
