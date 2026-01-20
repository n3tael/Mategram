package com.xxcactussell.data.utils.mappers.attachment

import com.xxcactussell.data.utils.mappers.file.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun AttachmentMenuBot.toData(): TdApi.AttachmentMenuBot = TdApi.AttachmentMenuBot(
    this.botUserId,
    this.supportsSelfChat,
    this.supportsUserChats,
    this.supportsBotChats,
    this.supportsGroupChats,
    this.supportsChannelChats,
    this.requestWriteAccess,
    this.isAdded,
    this.showInAttachmentMenu,
    this.showInSideMenu,
    this.showDisclaimerInSideMenu,
    this.name,
    this.nameColor?.toData(),
    this.defaultIcon?.toData(),
    this.iosStaticIcon?.toData(),
    this.iosAnimatedIcon?.toData(),
    this.iosSideMenuIcon?.toData(),
    this.androidIcon?.toData(),
    this.androidSideMenuIcon?.toData(),
    this.macosIcon?.toData(),
    this.macosSideMenuIcon?.toData(),
    this.iconColor?.toData(),
    this.webAppPlaceholder?.toData()
)

fun AttachmentMenuBotColor.toData(): TdApi.AttachmentMenuBotColor = TdApi.AttachmentMenuBotColor(
    this.lightColor,
    this.darkColor
)

