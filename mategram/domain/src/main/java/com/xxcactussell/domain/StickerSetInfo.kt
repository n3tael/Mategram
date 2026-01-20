package com.xxcactussell.domain

data class StickerSetInfo(
    val id: Long,
    val title: String,
    val name: String,
    val thumbnail: Thumbnail? = null,
    val thumbnailOutline: Outline? = null,
    val isOwned: Boolean,
    val isInstalled: Boolean,
    val isArchived: Boolean,
    val isOfficial: Boolean,
    val stickerType: StickerType,
    val needsRepainting: Boolean,
    val isAllowedAsChatEmojiStatus: Boolean,
    val isViewed: Boolean,
    val size: Int,
    val covers: List<Sticker>
) : Object
