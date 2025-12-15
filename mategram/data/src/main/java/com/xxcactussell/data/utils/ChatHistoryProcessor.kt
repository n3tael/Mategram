package com.xxcactussell.data.utils

import android.util.Log
import com.xxcactussell.data.TdClientManager
import com.xxcactussell.data.impl.MessagesRepositoryImpl
import com.xxcactussell.domain.messages.model.Message
import com.xxcactussell.domain.messages.model.MessageInteractionInfo
import com.xxcactussell.domain.messages.model.MessageListItem
import com.xxcactussell.domain.messages.model.getId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.drinkless.tdlib.TdApi
import java.util.concurrent.ConcurrentHashMap

class ChatHistoryProcessor(
    private val chatId: Long,
    private val clientManager: TdClientManager,
    private val messagesRepository: MessagesRepositoryImpl,
    private val scope: CoroutineScope
) {
    init {
        Log.d("ChatProcessor", "CREATED Instance: ${System.identityHashCode(this)}")
    }
    private val _items = MutableStateFlow<List<MessageListItem>>(emptyList())
    val items: StateFlow<List<MessageListItem>> = _items.asStateFlow()

    private val albumMessageBuffer = mutableMapOf<Long, MutableList<Message>>()
    private val albumProcessingJobs = mutableMapOf<Long, Job>()
    private val albumBufferMutex = Mutex()
    private val ALBUM_GROUPING_DELAY_MS = 500L
    private val sendersCache = ConcurrentHashMap<Long, Any>()

    private var isLoadingHistory = false
    private var canLoadMore = true

    fun processNewMessage(message: Message) {
        scope.launch {
            if (message.mediaAlbumId != 0L) {
                albumBufferMutex.withLock {
                    albumMessageBuffer.getOrPut(message.mediaAlbumId) { mutableListOf() }.add(message)
                }
                albumProcessingJobs[message.mediaAlbumId]?.cancel()
                albumProcessingJobs[message.mediaAlbumId] = scope.launch {
                    delay(ALBUM_GROUPING_DELAY_MS)
                    processBufferedAlbum(message.mediaAlbumId)
                }
            } else {
                processAndEmitMessages(listOf(message))
            }
        }
    }

    fun processUpdateInteractionInfo(messageId: Long, interaction: MessageInteractionInfo?) {
        val currentList = _items.value
        val updatedList = currentList.map { item ->
            when (item) {
                is MessageListItem.MessageItem -> {
                    if (item.message.id == messageId) item.copy(message = item.message.copy(interactionInfo = interaction)) else item
                }

                is MessageListItem.AlbumItem -> {
                    if (item.messages.any { it.id == messageId }) {
                        val newMsgs =
                            item.messages.map { if (it.id == messageId) it.copy(interactionInfo = interaction) else it }
                        item.copy(messages = newMsgs)
                    } else {
                        item
                    }
                }
            }
        }

        if (updatedList != currentList) {
            _items.value = updatedList
        }
    }

    fun processUpdatedMessage(oldId: Long, updatedMessage: Message) {
        val currentList = _items.value
        val updatedList = currentList.map { item ->
            when (item) {
                is MessageListItem.MessageItem -> {
                    if (item.message.id == oldId) item.copy(message = updatedMessage) else item
                }
                is MessageListItem.AlbumItem -> {
                    if (item.messages.any { it.id == oldId }) {
                        val newMsgs = item.messages.map { if (it.id == oldId) updatedMessage else it }
                        item.copy(messages = newMsgs)
                    } else {
                        item
                    }
                }
            }
        }

        if (updatedList != currentList) {
            _items.value = updatedList
        }
    }

    private suspend fun processBufferedAlbum(albumId: Long) {
        val bufferedMessages = albumBufferMutex.withLock {
            albumProcessingJobs.remove(albumId)
            albumMessageBuffer.remove(albumId)
        }
        if (!bufferedMessages.isNullOrEmpty()) {
            processAndEmitMessages(bufferedMessages)
        }
    }

    fun loadMoreHistory(limit: Int = 50) {
        if (isLoadingHistory || !canLoadMore) return
        isLoadingHistory = true

        scope.launch {
            try {
                val lastItem = _items.value.lastOrNull()
                val fromMessageId = when (lastItem) {
                    is MessageListItem.MessageItem -> lastItem.message.id
                    is MessageListItem.AlbumItem -> lastItem.messages.last().id // Последнее в альбоме - самое старое
                    null -> 0L
                }

                val rawMessages = messagesRepository.getMessagesWithAlbumBoundary(chatId, fromMessageId, limit)

                if (rawMessages.isEmpty()) {
                    canLoadMore = false
                    isLoadingHistory = false
                    return@launch
                }

                processAndEmitMessages(rawMessages, appendToBottom = true)

                if (canLoadMore && _items.value.size < 15) {
                    isLoadingHistory = false
                    loadMoreHistory(limit)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoadingHistory = false
            }
        }
    }

    private suspend fun processAndEmitMessages(
        newMessages: List<Message>,
        appendToBottom: Boolean = false,
        baseList: List<MessageListItem>? = null
    ) {
        val missingSenders = newMessages.map { it.senderId }
            .filter { !sendersCache.containsKey(it.getId()) }
            .toSet()

        if (missingSenders.isNotEmpty()) {
            val newSenders = messagesRepository.resolveSenders(missingSenders)
            sendersCache.putAll(newSenders)
        }

        val currentUiList = baseList ?: _items.value
        val currentRawMessages = currentUiList.flatMap { item ->
            when(item) {
                is MessageListItem.MessageItem -> listOf(item.message)
                is MessageListItem.AlbumItem -> item.messages
            }
        }

        val allMessages = currentRawMessages + newMessages

        val uniqueMessages = allMessages.distinctBy { it.id }
        val finalUiList = groupMessages(uniqueMessages)

        if (finalUiList != _items.value) {
            _items.value = finalUiList
        }
    }

    private fun groupMessages(messages: List<Message>): List<MessageListItem> {
        val groups = messages.groupBy { it.mediaAlbumId }
        val result = mutableListOf<Pair<Int, MessageListItem>>()

        groups.forEach { (albumId, msgs) ->
            if (albumId == 0L) {
                msgs.forEach { msg ->
                    val sender = sendersCache[msg.senderId.getId()]
                    result.add(msg.date to MessageListItem.MessageItem(msg, sender))
                }
            } else {
                val sortedMessages = msgs.sortedBy { it.id }
                val firstMsg = sortedMessages.first()
                val sender = sendersCache[firstMsg.senderId.getId()]
                result.add(firstMsg.date to MessageListItem.AlbumItem(sortedMessages, sender))
            }
        }
        return result.sortedByDescending { it.first }.map { it.second }
    }

    fun clear() {
        albumProcessingJobs.values.forEach { it.cancel() }
    }
}