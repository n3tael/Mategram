package com.xxcactussell.repositories.messages.repository

import android.net.Uri
import com.xxcactussell.domain.Chat
import com.xxcactussell.domain.ChatAction
import com.xxcactussell.domain.FormattedText
import com.xxcactussell.domain.InputFileLocal
import com.xxcactussell.domain.InputMessageAudio
import com.xxcactussell.domain.InputMessageContent
import com.xxcactussell.domain.InputMessageDocument
import com.xxcactussell.domain.InputMessagePhoto
import com.xxcactussell.domain.InputMessageText
import com.xxcactussell.domain.InputMessageVideo
import com.xxcactussell.domain.InputThumbnail
import com.xxcactussell.domain.Message
import com.xxcactussell.domain.MessageSender
import com.xxcactussell.domain.MessageSenderChat
import com.xxcactussell.domain.MessageSenderUser
import com.xxcactussell.domain.MessageSource
import com.xxcactussell.domain.ReactionType
import com.xxcactussell.domain.TextEntity
import com.xxcactussell.domain.User
import com.xxcactussell.repositories.messages.model.MessageListItem
import com.xxcactussell.repositories.messages.utils.getId
import com.xxcactussell.repositories.utils.FileHelper
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
    fun getChatActionFlow(chatId: Long): Flow<ChatAction>
}


class GetChatActionFlowUseCase @Inject constructor(
    private val messageRepository: MessagesRepository
) {
    operator fun invoke(chatId: Long): Flow<ChatAction> = messageRepository.getChatActionFlow(chatId)
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

class GetUserUseCase @Inject constructor(
    private val chatsRepo: MessagesRepository
) {
    suspend operator fun invoke(userId: Long): User? {
        return chatsRepo.getUser(userId)
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

            val inputFile = InputFileLocal(path)

            val caption = FormattedText(
                captionText,
                captionEntities
            )

            when(attachmentsType) {
                "Media" -> {
                    when {
                        mimeType.startsWith("image/") && !mimeType.endsWith("/gif") -> {
                            val meta = fileHelper.extractMediaMetadata(uri)
                            InputMessagePhoto(
                                photo = inputFile,
                                thumbnail = InputThumbnail(inputFile, meta.width, meta.height),
                                width = meta.width,
                                height = meta.height,
                                caption = caption,
                                hasSpoiler = false,
                                addedStickerFileIds = intArrayOf(),
                                showCaptionAboveMedia = false,
                                selfDestructType = null
                            )
                        }

                        mimeType.startsWith("video/") -> {
                            val meta = fileHelper.extractMediaMetadata(uri)
                            InputMessageVideo(
                                video = inputFile,
                                thumbnail = InputThumbnail(inputFile, meta.width, meta.height),
                                cover = inputFile,
                                startTimestamp = 0,
                                duration = meta.duration,
                                width = meta.width,
                                height = meta.height,
                                caption = caption,
                                addedStickerFileIds = intArrayOf(),
                                supportsStreaming = false,
                                showCaptionAboveMedia = false,
                                selfDestructType = null,
                                hasSpoiler = false
                            )
                        }

                        mimeType.startsWith("audio/") -> {
                            InputMessageAudio(
                                audio = inputFile,
                                albumCoverThumbnail = InputThumbnail(inputFile, 0, 0),
                                duration = 0, title = "", performer = "", caption = caption
                            )
                        }

                        else -> {
                            InputMessageDocument(
                                document = inputFile,
                                thumbnail = InputThumbnail(inputFile, 0, 0),
                                disableContentTypeDetection = false,
                                caption = caption
                            )
                        }
                    }
                }

                else -> {
                    InputMessageDocument(
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
            finalContentList.add(InputMessageText(
                FormattedText(captionText, captionEntities),
                linkPreviewOptions = null,
                clearDraft = true
            ))
        }
        return@withContext finalContentList
    }
}