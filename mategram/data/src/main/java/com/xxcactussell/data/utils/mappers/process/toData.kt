package com.xxcactussell.data.utils.mappers.process

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ProcessChatFolderNewChats.toData(): TdApi.ProcessChatFolderNewChats = TdApi.ProcessChatFolderNewChats(
    this.chatFolderId,
    this.addedChatIds
)

fun ProcessChatJoinRequest.toData(): TdApi.ProcessChatJoinRequest = TdApi.ProcessChatJoinRequest(
    this.chatId,
    this.userId,
    this.approve
)

fun ProcessChatJoinRequests.toData(): TdApi.ProcessChatJoinRequests = TdApi.ProcessChatJoinRequests(
    this.chatId,
    this.inviteLink,
    this.approve
)

fun ProcessPushNotification.toData(): TdApi.ProcessPushNotification = TdApi.ProcessPushNotification(
    this.payload
)

