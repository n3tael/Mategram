package com.xxcactussell.data.utils.mappers.attachment

import com.xxcactussell.data.utils.mappers.file.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AttachmentMenuBot.toDomain(): AttachmentMenuBot = AttachmentMenuBot(
    botUserId = this.botUserId,
    supportsSelfChat = this.supportsSelfChat,
    supportsUserChats = this.supportsUserChats,
    supportsBotChats = this.supportsBotChats,
    supportsGroupChats = this.supportsGroupChats,
    supportsChannelChats = this.supportsChannelChats,
    requestWriteAccess = this.requestWriteAccess,
    isAdded = this.isAdded,
    showInAttachmentMenu = this.showInAttachmentMenu,
    showInSideMenu = this.showInSideMenu,
    showDisclaimerInSideMenu = this.showDisclaimerInSideMenu,
    name = this.name,
    nameColor = this.nameColor?.toDomain(),
    defaultIcon = this.defaultIcon?.toDomain(),
    iosStaticIcon = this.iosStaticIcon?.toDomain(),
    iosAnimatedIcon = this.iosAnimatedIcon?.toDomain(),
    iosSideMenuIcon = this.iosSideMenuIcon?.toDomain(),
    androidIcon = this.androidIcon?.toDomain(),
    androidSideMenuIcon = this.androidSideMenuIcon?.toDomain(),
    macosIcon = this.macosIcon?.toDomain(),
    macosSideMenuIcon = this.macosSideMenuIcon?.toDomain(),
    iconColor = this.iconColor?.toDomain(),
    webAppPlaceholder = this.webAppPlaceholder?.toDomain()
)

fun TdApi.AttachmentMenuBotColor.toDomain(): AttachmentMenuBotColor = AttachmentMenuBotColor(
    lightColor = this.lightColor,
    darkColor = this.darkColor
)

