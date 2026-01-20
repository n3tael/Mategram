package com.xxcactussell.domain

data class AttachmentMenuBot(
    val botUserId: Long,
    val supportsSelfChat: Boolean,
    val supportsUserChats: Boolean,
    val supportsBotChats: Boolean,
    val supportsGroupChats: Boolean,
    val supportsChannelChats: Boolean,
    val requestWriteAccess: Boolean,
    val isAdded: Boolean,
    val showInAttachmentMenu: Boolean,
    val showInSideMenu: Boolean,
    val showDisclaimerInSideMenu: Boolean,
    val name: String,
    val nameColor: AttachmentMenuBotColor? = null,
    val defaultIcon: File? = null,
    val iosStaticIcon: File? = null,
    val iosAnimatedIcon: File? = null,
    val iosSideMenuIcon: File? = null,
    val androidIcon: File? = null,
    val androidSideMenuIcon: File? = null,
    val macosIcon: File? = null,
    val macosSideMenuIcon: File? = null,
    val iconColor: AttachmentMenuBotColor? = null,
    val webAppPlaceholder: File? = null
) : Object
