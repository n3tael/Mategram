package com.xxcactussell.domain

data class ChatBoostFeatures(
    val features: List<ChatBoostLevelFeatures>,
    val minProfileBackgroundCustomEmojiBoostLevel: Int,
    val minBackgroundCustomEmojiBoostLevel: Int,
    val minEmojiStatusBoostLevel: Int,
    val minChatThemeBackgroundBoostLevel: Int,
    val minCustomBackgroundBoostLevel: Int,
    val minCustomEmojiStickerSetBoostLevel: Int,
    val minAutomaticTranslationBoostLevel: Int,
    val minSpeechRecognitionBoostLevel: Int,
    val minSponsoredMessageDisableBoostLevel: Int
) : Object
