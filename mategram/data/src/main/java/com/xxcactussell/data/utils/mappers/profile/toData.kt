package com.xxcactussell.data.utils.mappers.profile

import com.xxcactussell.data.utils.mappers.file.toData
import com.xxcactussell.data.utils.mappers.minithumbnail.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ProfileAccentColor.toData(): TdApi.ProfileAccentColor = TdApi.ProfileAccentColor(
    this.id,
    this.lightThemeColors.toData(),
    this.darkThemeColors.toData(),
    this.minSupergroupChatBoostLevel,
    this.minChannelChatBoostLevel
)

fun ProfileAccentColors.toData(): TdApi.ProfileAccentColors = TdApi.ProfileAccentColors(
    this.paletteColors,
    this.backgroundColors,
    this.storyColors
)

fun ProfilePhoto.toData(): TdApi.ProfilePhoto = TdApi.ProfilePhoto(
    this.id,
    this.small.toData(),
    this.big.toData(),
    this.minithumbnail?.toData(),
    this.hasAnimation,
    this.isPersonal
)

fun ProfileTab.toData(): TdApi.ProfileTab = when(this) {
    is ProfileTabPosts -> this.toData()
    is ProfileTabGifts -> this.toData()
    is ProfileTabMedia -> this.toData()
    is ProfileTabFiles -> this.toData()
    is ProfileTabLinks -> this.toData()
    is ProfileTabMusic -> this.toData()
    is ProfileTabVoice -> this.toData()
    is ProfileTabGifs -> this.toData()
}

fun ProfileTabFiles.toData(): TdApi.ProfileTabFiles = TdApi.ProfileTabFiles(
)

fun ProfileTabGifs.toData(): TdApi.ProfileTabGifs = TdApi.ProfileTabGifs(
)

fun ProfileTabGifts.toData(): TdApi.ProfileTabGifts = TdApi.ProfileTabGifts(
)

fun ProfileTabLinks.toData(): TdApi.ProfileTabLinks = TdApi.ProfileTabLinks(
)

fun ProfileTabMedia.toData(): TdApi.ProfileTabMedia = TdApi.ProfileTabMedia(
)

fun ProfileTabMusic.toData(): TdApi.ProfileTabMusic = TdApi.ProfileTabMusic(
)

fun ProfileTabPosts.toData(): TdApi.ProfileTabPosts = TdApi.ProfileTabPosts(
)

fun ProfileTabVoice.toData(): TdApi.ProfileTabVoice = TdApi.ProfileTabVoice(
)

