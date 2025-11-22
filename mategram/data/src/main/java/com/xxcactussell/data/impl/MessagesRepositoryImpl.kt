package com.xxcactussell.data.impl

import android.util.Log
import com.xxcactussell.data.TdClientManager
import com.xxcactussell.data.utils.toDomain
import com.xxcactussell.data.utils.todomain.toDomain
import com.xxcactussell.data.utils.todata.toData
import com.xxcactussell.domain.chats.model.Chat
import com.xxcactussell.domain.chats.model.ChatPhoto
import com.xxcactussell.domain.chats.model.ChatType
import com.xxcactussell.domain.chats.model.ProfilePhoto
import com.xxcactussell.domain.chats.model.User
import com.xxcactussell.domain.messages.model.InputMessageContent
import com.xxcactussell.domain.messages.model.Message
import com.xxcactussell.domain.messages.model.MessageSource
import com.xxcactussell.domain.messages.repository.MessagesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.suspendCancellableCoroutine
import org.drinkless.tdlib.Client
import org.drinkless.tdlib.TdApi
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class MessagesRepositoryImpl @Inject constructor(
    private val clientManager: TdClientManager
) : MessagesRepository {
    private val _newMessages = MutableSharedFlow<Message>(
        extraBufferCapacity = 64,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val newMessagesFlow: SharedFlow<Message> = _newMessages.asSharedFlow()

    private val _succeededMessages = MutableSharedFlow<Pair<Long, Message>>(
        extraBufferCapacity = 64,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val _lastReadOutboxIdFlow = MutableSharedFlow<Pair<Long, Long>>(
        extraBufferCapacity = 10,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private val repositoryScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        clientManager.newMessagesFlow
            .onEach { updateNewMessage ->
                when (updateNewMessage) {
                    is TdApi.UpdateNewMessage -> {
                        _newMessages.tryEmit(updateNewMessage.message.toDomain())
                    }
                    is TdApi.UpdateMessageSendSucceeded -> {
                        _succeededMessages.tryEmit(updateNewMessage.oldMessageId to updateNewMessage.message.toDomain())
                    }
                    is TdApi.UpdateMessageSendFailed -> {
                        _succeededMessages.tryEmit(updateNewMessage.oldMessageId to updateNewMessage.message.toDomain())
                    }
                }

            }
            .launchIn(repositoryScope)
        clientManager.chatsUpdatesFlow
            .onEach { update ->
                when(update) {
                    is TdApi.UpdateChatReadOutbox -> {
                        _lastReadOutboxIdFlow.tryEmit(update.chatId to update.lastReadOutboxMessageId)
                    }
                }
            }
            .launchIn(repositoryScope)
    }

    override suspend fun getChatHistory(chatId: Long, fromMessageId: Long, limit: Int): List<Message> = suspendCancellableCoroutine { continuation ->
        clientManager.send(TdApi.GetChatHistory(chatId, fromMessageId, 0, limit, false)) { result ->
            if (continuation.isActive) {
                when (result) {
                    is TdApi.Messages -> {
                        Log.d("MessagesRepository", "Received messages: ${result.messages.size}")
                        val messages = result.messages.map {
                            it.toDomain()
                        }
                        continuation.resume(messages)
                    }
                    else -> continuation.resume(emptyList())
                }
            }
        }
    }

    override fun observeNewMessages(): Flow<Message> = newMessagesFlow

    override fun observeSucceededMessages() : Flow<Pair<Long, Message>> = _succeededMessages.asSharedFlow()

    override fun observeLastReadOutboxMessage(): Flow<Pair<Long, Long>> = _lastReadOutboxIdFlow.asSharedFlow()

    override suspend fun openChat(chatId: Long): Chat? = suspendCancellableCoroutine { continuation ->
        clientManager.send(TdApi.GetChat(chatId)) { result ->
            if (!continuation.isActive) return@send

            when (result.constructor) {
                TdApi.Chat.CONSTRUCTOR -> {
                    val tdChat = result as TdApi.Chat
                    var mgChat = tdChat.toDomain()
                    val type = tdChat.type
                    when (type) {
                        is TdApi.ChatTypeBasicGroup -> {
                            clientManager.send(TdApi.GetBasicGroup(type.basicGroupId)) { groupResult ->
                                if (groupResult.constructor == TdApi.BasicGroup.CONSTRUCTOR) {
                                    val bg = groupResult as TdApi.BasicGroup
                                    mgChat = mgChat.copy(
                                        myMemberStatus = bg.status.toDomain(),
                                        memberCount = bg.memberCount
                                    )
                                }
                                clientManager.send(TdApi.OpenChat(chatId), null)

                                if (continuation.isActive) continuation.resume(mgChat)
                            }
                        }

                        is TdApi.ChatTypeSupergroup -> {
                            clientManager.send(TdApi.GetSupergroup(type.supergroupId)) { superResult ->
                                if (superResult.constructor == TdApi.Supergroup.CONSTRUCTOR) {
                                    val sg = superResult as TdApi.Supergroup
                                    mgChat = mgChat.copy(
                                        myMemberStatus = sg.status.toDomain(),
                                        memberCount = sg.memberCount
                                    )
                                }
                                clientManager.send(TdApi.OpenChat(chatId), null)
                                if (continuation.isActive) continuation.resume(mgChat)
                            }
                        }

                        else -> {
                            clientManager.send(TdApi.OpenChat(chatId), null)
                            if (continuation.isActive) continuation.resume(mgChat)
                        }
                    }
                }

                else -> {
                    if (continuation.isActive) continuation.resume(null)
                }
            }
        }

        continuation.invokeOnCancellation {

        }
    }

    override fun closeChat(chatId: Long) {
        clientManager.send(TdApi.CloseChat(chatId), null)
    }

    override suspend fun getChat(chatId: Long): Chat? = suspendCancellableCoroutine { continuation ->
        clientManager.send(TdApi.GetChat(chatId)) { result ->
            if (continuation.isActive) {
                when (result.constructor) {
                    TdApi.Chat.CONSTRUCTOR -> {
                        val tdChat = result as TdApi.Chat
                        continuation.resume(tdChat.toDomain())
                    }
                    TdApi.Error.CONSTRUCTOR -> {
                        continuation.resume(null)
                    }

                    else -> {
                        continuation.resume(null)
                    }
                }
            }
        }

        continuation.invokeOnCancellation {
            // TODO
        }
    }


    override suspend fun getUser(userId: Long): User? = suspendCancellableCoroutine { continuation ->
        clientManager.send(TdApi.GetUser(userId)) { result ->
            if (continuation.isActive) {
                when (result.constructor) {
                    TdApi.User.CONSTRUCTOR -> {
                        val tdUser = result as TdApi.User
                        continuation.resume(tdUser.toDomain())
                    }
                    TdApi.Error.CONSTRUCTOR -> {
                        continuation.resume(null)
                    }

                    else -> {
                        continuation.resume(null)
                    }
                }
            }
        }

        continuation.invokeOnCancellation {
            // TODO
        }
    }


    override suspend fun sendMessage(
        chatId: Long,
        inputMessageContents: List<InputMessageContent>,
        replyToMessageId: Long,
        messageThreadId: Long
    ): List<Message>  = suspendCancellableCoroutine { continuation ->
        if (inputMessageContents.isEmpty()) {
            continuation.resumeWithException(IllegalArgumentException("Input message contents cannot be empty"))
            return@suspendCancellableCoroutine
        }

        val replyTo = if (replyToMessageId != 0L) TdApi.InputMessageReplyToMessage(replyToMessageId, null, 0) else null

        val handler = Client.ResultHandler { result ->
            if (continuation.isActive) {
                when (result) {
                    is TdApi.Message -> {
                        val message = result.toDomain()
                        continuation.resume(listOf(message))
                    }
                    is TdApi.Messages -> {
                        if (result.messages.isNotEmpty()) {
                            val messages = result.messages.map {
                                it.toDomain()
                            }.toList()
                            continuation.resume(messages)
                        } else {
                            continuation.resumeWithException(Exception("TDLib returned empty Messages object for album"))
                        }
                    }
                    is TdApi.Error -> continuation.resumeWithException(Exception("TDLib error: ${result.code} ${result.message}"))
                    else -> continuation.resumeWithException(Exception("Unexpected result from sendMessage: $result"))
                }
            }
        }

        if (inputMessageContents.size == 1) {
            clientManager.send(
                TdApi.SendMessage(
                    chatId,
                    messageThreadId,
                    replyTo,
                    null,
                    null,
                    inputMessageContents.first().toData()
                ), handler
            )
        } else {
            clientManager.send(
                TdApi.SendMessageAlbum(
                    chatId,
                    messageThreadId,
                    replyTo,
                    null,
                    inputMessageContents.map {
                        it.toData()
                    }.toTypedArray()
                ), handler
            )
        }
    }

    override fun markMessageAsRead(
        chatId: Long,
        messageId: Long,
        source: MessageSource,
        forceRead: Boolean
    ) {
        clientManager.send(TdApi.ViewMessages(chatId, longArrayOf(messageId), source.toData(), forceRead), null)
    }

    override suspend fun searchMediaMessages(
        chatId: Long,
        fromMessageId: Long,
        offset: Int,
        limit: Int,
    ): List<Message> = suspendCancellableCoroutine { continuation ->
        val handler = Client.ResultHandler { result ->
            if (continuation.isActive) {
                when (result) {
                    is TdApi.FoundChatMessages -> {
                        val messages = result.messages.map { it.toDomain() }
                        continuation.resume(messages)
                    }
                    is TdApi.Error -> continuation.resumeWithException(Exception("TDLib error: ${result.code} ${result.message}"))
                    else -> continuation.resumeWithException(Exception("Unexpected result from sendMessage: $result"))
                }
            }
        }

        clientManager.send(TdApi.SearchChatMessages(chatId, null, "", null, fromMessageId, offset, limit,
            TdApi.SearchMessagesFilterPhotoAndVideo()), handler)
    }
}