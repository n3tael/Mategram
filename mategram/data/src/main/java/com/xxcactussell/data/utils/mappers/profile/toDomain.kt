package com.xxcactussell.data.utils.mappers.profile

import com.xxcactussell.data.utils.mappers.file.toDomain
import com.xxcactussell.data.utils.mappers.minithumbnail.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ProfileAccentColor.toDomain(): ProfileAccentColor = ProfileAccentColor(
    id = this.id,
    lightThemeColors = this.lightThemeColors.toDomain(),
    darkThemeColors = this.darkThemeColors.toDomain(),
    minSupergroupChatBoostLevel = this.minSupergroupChatBoostLevel,
    minChannelChatBoostLevel = this.minChannelChatBoostLevel
)

fun TdApi.ProfileAccentColors.toDomain(): ProfileAccentColors = ProfileAccentColors(
    paletteColors = this.paletteColors,
    backgroundColors = this.backgroundColors,
    storyColors = this.storyColors
)

fun TdApi.ProfilePhoto.toDomain(): ProfilePhoto = ProfilePhoto(
    id = this.id,
    small = this.small.toDomain(),
    big = this.big.toDomain(),
    minithumbnail = this.minithumbnail?.toDomain(),
    hasAnimation = this.hasAnimation,
    isPersonal = this.isPersonal
)

fun TdApi.ProfileTab.toDomain(): ProfileTab = when(this) {
    is TdApi.ProfileTabPosts -> this.toDomain()
    is TdApi.ProfileTabGifts -> this.toDomain()
    is TdApi.ProfileTabMedia -> this.toDomain()
    is TdApi.ProfileTabFiles -> this.toDomain()
    is TdApi.ProfileTabLinks -> this.toDomain()
    is TdApi.ProfileTabMusic -> this.toDomain()
    is TdApi.ProfileTabVoice -> this.toDomain()
    is TdApi.ProfileTabGifs -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ProfileTabFiles.toDomain(): ProfileTabFiles = ProfileTabFiles

fun TdApi.ProfileTabGifs.toDomain(): ProfileTabGifs = ProfileTabGifs

fun TdApi.ProfileTabGifts.toDomain(): ProfileTabGifts = ProfileTabGifts

fun TdApi.ProfileTabLinks.toDomain(): ProfileTabLinks = ProfileTabLinks

fun TdApi.ProfileTabMedia.toDomain(): ProfileTabMedia = ProfileTabMedia

fun TdApi.ProfileTabMusic.toDomain(): ProfileTabMusic = ProfileTabMusic

fun TdApi.ProfileTabPosts.toDomain(): ProfileTabPosts = ProfileTabPosts

fun TdApi.ProfileTabVoice.toDomain(): ProfileTabVoice = ProfileTabVoice

