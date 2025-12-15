package com.xxcactussell.domain.messages.repository

import android.net.Uri
import com.xxcactussell.domain.chats.model.Chat
import com.xxcactussell.domain.chats.model.User
import com.xxcactussell.domain.messages.model.FormattedText
import com.xxcactussell.domain.messages.model.InputFile
import com.xxcactussell.domain.messages.model.InputMessageContent
import com.xxcactussell.domain.messages.model.InputThumbnail
import com.xxcactussell.domain.messages.model.Message
import com.xxcactussell.domain.messages.model.MessageListItem
import com.xxcactussell.domain.messages.model.MessageSender
import com.xxcactussell.domain.messages.model.MessageSenderChat
import com.xxcactussell.domain.messages.model.MessageSenderUser
import com.xxcactussell.domain.messages.model.MessageSource
import com.xxcactussell.domain.messages.model.ReactionType
import com.xxcactussell.domain.messages.model.TextEntity
import com.xxcactussell.domain.messages.model.getId
import com.xxcactussell.domain.utils.FileHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MessagesRepository {
    suspend fun getChatHistory(chatId: Long, fromMessageId: Long, limit: Int) : List<Message>
    fun observeSucceededMessages() : Flow<Pair<Long, Message>>
    fun observeLastReadOutboxMessage() : Flow<Pair<Long, Long>>
    suspend fun openChat(chatId: Long) : Chat?

    fun closeChat(chatId: Long)
    suspend fun getChat(chatId: Long) : Chat?
    suspend fun getUser(userId: Long) : User?

    suspend fun sendMessage(chatId: Long, inputMessageContents: List<InputMessageContent>, replyToMessageId: Long = 0, messageThreadId: Long = 0) : List<Message>

    fun markMessageAsRead(chatId: Long, messageId: Long, source: MessageSource, forceRead: Boolean = false)
    suspend fun searchMediaMessages(chatId: Long, fromMessageId: Long, offset: Int, limit: Int) : List<Message>
    suspend fun getReplyMessage(chatId: Long, messageId: Long): Message?
    fun getChatFlow(chatId: Long): Flow<List<MessageListItem>>
    fun loadMoreHistory(chatId: Long)
    fun clearChatSession(chatId: Long)

    fun addReactionToMessage(chatId: Long, messageId: Long, reactionType: ReactionType)

    fun removeReactionFromMessage(chatId: Long, messageId: Long, reactionType: ReactionType)
}


class AddReactionToMessageUseCase @Inject constructor(
    private val messageRepository: MessagesRepository
) {
    operator fun invoke(chatId: Long, messageId: Long, reactionType: ReactionType) = messageRepository.addReactionToMessage(chatId, messageId, reactionType)
}

class RemoveReactionFromMessageUseCase @Inject constructor(
    private val messageRepository: MessagesRepository
) {
    operator fun invoke(chatId: Long, messageId: Long, reactionType: ReactionType) = messageRepository.removeReactionFromMessage(chatId, messageId, reactionType)
}
class LoadMoreHistoryUseCase @Inject constructor(
    private val messageRepository: MessagesRepository
) {
    operator fun invoke(chatId: Long) = messageRepository.loadMoreHistory(chatId)
}

class GetReplyMessageUseCase @Inject constructor(
    private val messageRepository: MessagesRepository
) {
    suspend operator fun invoke(chatId: Long, messageId: Long): Message? {
        return messageRepository.getReplyMessage(chatId, messageId)
    }
}

class GetChatFlowUseCase @Inject constructor(
    private val messageRepository: MessagesRepository
) {
    operator fun invoke(chatId: Long): Flow<List<MessageListItem>> {
        return messageRepository.getChatFlow(chatId)
    }
}

class GetChatMediaHistoryUseCase @Inject constructor(
    private val messageRepository: MessagesRepository
) {
    suspend operator fun invoke(chatId: Long, aroundMessageId: Long, limit: Int = 30): List<Message> {
        return messageRepository.searchMediaMessages(
            chatId = chatId,
            fromMessageId = aroundMessageId,
            offset = -(limit / 2),
            limit = limit
        )
    }
}

class GetSendersInfoUseCase @Inject constructor(
    private val chatsRepo: MessagesRepository
) {
    suspend operator fun invoke(senderIds: Set<MessageSender>): Map<Long, Any> {
        return coroutineScope {
            val deferredSenders = senderIds.associateWith { senderId ->
                async {
                    when (senderId) {
                        is MessageSenderChat -> chatsRepo.getChat(senderId.chatId)
                        is MessageSenderUser -> chatsRepo.getUser(senderId.userId)
                    }
                }
            }

            val resultMap = mutableMapOf<Long, Any>()

            deferredSenders.forEach { (senderId, deferredChat) ->
                val chat = deferredChat.await()
                if (chat != null) {
                    resultMap[senderId.getId() ?: 0L] = chat
                }
            }

            return@coroutineScope resultMap.toMap()
        }
    }
}
class ObserveSucceededMessagesUseCase @Inject constructor(private val repository: MessagesRepository) {
    operator fun invoke() : Flow<Pair<Long, Message>> = repository.observeSucceededMessages()
}

class ObserveLastReadOutboxMessageUseCase @Inject constructor(private val repository: MessagesRepository) {
    operator fun invoke() : Flow<Pair<Long, Long>> = repository.observeLastReadOutboxMessage()
}

class GetChatHistoryUseCase @Inject constructor(private val repository: MessagesRepository) {
    suspend operator fun invoke(chatId: Long, fromMessageId: Long, limit: Int) : List<Message> = repository.getChatHistory(chatId, fromMessageId, limit)
}

class GetMessagesWithAlbumBoundaryUseCase @Inject constructor(
    private val getChatHistory: GetChatHistoryUseCase
) {
    suspend operator fun invoke(chatId: Long, fromMessageId: Long, limit: Int): List<Message> {
        var messages = getChatHistory(chatId, fromMessageId, limit)
        if (messages.isNotEmpty()) {
            val oldestMessage = messages.last()
            if (oldestMessage.mediaAlbumId != 0L) {
                val lastAlbumIndex = messages.indexOfFirst { it.mediaAlbumId == oldestMessage.mediaAlbumId }
                if (lastAlbumIndex > 0) {
                    messages = messages.subList(0, lastAlbumIndex)
                }
            }
        }
        return messages
    }
}

class OpenChatUseCase @Inject constructor(private val repository: MessagesRepository) {
    suspend operator fun invoke(chatId: Long) : Chat? = repository.openChat(chatId)
}

class CloseChatUseCase @Inject constructor(private val repository: MessagesRepository) {
    operator fun invoke(chatId: Long) = repository.closeChat(chatId)
}

class SendMessageUseCase @Inject constructor(private val repository: MessagesRepository) {
    suspend operator fun invoke(chatId: Long, inputMessageContents: List<InputMessageContent>, replyToMessageId: Long = 0, messageThreadId: Long = 0) : List<Message> = repository.sendMessage(chatId, inputMessageContents, replyToMessageId, messageThreadId)
}

class MarkMessageAsRead @Inject constructor(private val repository: MessagesRepository) {
    operator fun invoke(chatId: Long, messageId: Long, source: MessageSource, forceRead: Boolean = false) = repository.markMessageAsRead(chatId, messageId, source, forceRead)
}

class BuildMessageContentUseCase @Inject constructor(
    private val fileHelper: FileHelper
) {
    suspend operator fun invoke(
        uris: List<Uri>,
        captionText: String,
        captionEntities: List<TextEntity> = emptyList(),
        attachmentsType: String? = null
    ): List<InputMessageContent> = withContext(Dispatchers.IO) {

        val contentList = uris.mapNotNull { uri ->
            val path = fileHelper.getLocalPath(uri) ?: return@mapNotNull null
            val mimeType = fileHelper.getMimeType(uri) ?: "application/octet-stream"

            val inputFile = InputFile.Local(path)

            val caption = FormattedText(
                captionText,
                captionEntities
            )

            when(attachmentsType) {
                "Media" -> {
                    when {
                        mimeType.startsWith("image/") && !mimeType.endsWith("/gif") -> {
                            val meta = fileHelper.extractMediaMetadata(uri)
                            InputMessageContent.Photo(
                                photo = inputFile,
                                thumbnail = InputThumbnail(inputFile, meta.width, meta.height),
                                width = meta.width,
                                height = meta.height,
                                caption = caption,
                                hasSpoiler = false
                            )
                        }

                        mimeType.startsWith("video/") -> {
                            val meta = fileHelper.extractMediaMetadata(uri)
                            InputMessageContent.Video(
                                video = inputFile,
                                thumbnail = InputThumbnail(inputFile, meta.width, meta.height),
                                cover = inputFile,
                                startTimestamp = 0,
                                duration = meta.duration,
                                width = meta.width,
                                height = meta.height,
                                caption = caption
                            )
                        }

                        mimeType.startsWith("audio/") -> {
                            InputMessageContent.Audio(
                                audio = inputFile,
                                albumCoverThumbnail = InputThumbnail(inputFile, 0, 0),
                                duration = 0, title = "", performer = "", caption = caption
                            )
                        }

                        else -> {
                            InputMessageContent.Document(
                                document = inputFile,
                                thumbnail = InputThumbnail(inputFile, 0, 0),
                                disableContentTypeDetection = false,
                                caption = caption
                            )
                        }
                    }
                }

                else -> {
                    InputMessageContent.Document(
                        document = inputFile,
                        thumbnail = InputThumbnail(inputFile, 0, 0),
                        disableContentTypeDetection = false,
                        caption = caption
                    )
                }
            }
        }

        val finalContentList = mutableListOf<InputMessageContent>()

        if (uris.size > 1) {
            finalContentList.addAll(contentList)
        } else if (uris.size == 1) {
            finalContentList.addAll(contentList)
        } else if (captionText.isNotBlank()) {
            finalContentList.add(InputMessageContent.Text(FormattedText(captionText, captionEntities)))
        }
        return@withContext finalContentList
    }
}