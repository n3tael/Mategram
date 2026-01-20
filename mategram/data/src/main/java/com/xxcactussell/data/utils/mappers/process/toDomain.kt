package com.xxcactussell.data.utils.mappers.process

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ProcessChatFolderNewChats.toDomain(): ProcessChatFolderNewChats = ProcessChatFolderNewChats(
    chatFolderId = this.chatFolderId,
    addedChatIds = this.addedChatIds
)

fun TdApi.ProcessChatJoinRequest.toDomain(): ProcessChatJoinRequest = ProcessChatJoinRequest(
    chatId = this.chatId,
    userId = this.userId,
    approve = this.approve
)

fun TdApi.ProcessChatJoinRequests.toDomain(): ProcessChatJoinRequests = ProcessChatJoinRequests(
    chatId = this.chatId,
    inviteLink = this.inviteLink,
    approve = this.approve
)

fun TdApi.ProcessPushNotification.toDomain(): ProcessPushNotification = ProcessPushNotification(
    payload = this.payload
)

