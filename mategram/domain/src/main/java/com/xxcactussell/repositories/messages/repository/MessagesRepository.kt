package com.xxcactussell.repositories.messages.repository

import android.net.Uri
import android.os.FileUtils
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
import java.io.File
import javax.inject.Inject

interface MessagesRepository {
    suspend fun getChatHistory(chatId: Long, fromMessageId: Long, offset: Int, limit: Int): List<Message>
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
    fun loadMoreHistory(chatId: Long, messageId: Long? = null)
    fun loadMoreNewer(chatId: Long)
    fun clearChatSession(chatId: Long)

    fun addReactionToMessage(chatId: Long, messageId: Long, reactionType: ReactionType)

    fun removeReactionFromMessage(chatId: Long, messageId: Long, reactionType: ReactionType)
    fun getChatActionFlow(chatId: Long): Flow<ChatAction>
    fun cancelSendMessageAndUploadFile(messageId: Long, chatId: Long)
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
    operator fun invoke(chatId: Long, messageId: Long? = null) = messageRepository.loadMoreHistory(chatId, messageId)
}

class LoadMoreNewerUseCase @Inject constructor(
    private val messageRepository: MessagesRepository
) {
    operator fun invoke(chatId: Long) = messageRepository.loadMoreNewer(chatId)
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

class ObserveLastReadOutboxMessageUseCase @Inject constructor(private val repository: MessagesRepository) {
    operator fun invoke() : Flow<Pair<Long, Long>> = repository.observeLastReadOutboxMessage()
}
class OpenChatUseCase @Inject constructor(private val repository: MessagesRepository) {
    suspend operator fun invoke(chatId: Long) : Chat? = repository.openChat(chatId)
}

class CancelSendMessageAndUploadFileUseCase @Inject constructor(private val repository: MessagesRepository) {
    operator fun invoke(messageId: Long, chatId: Long) = repository.cancelSendMessageAndUploadFile(messageId, chatId)
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

        val showCaptionAboveMedia = false

        val contentList = uris.mapNotNull { uri ->
            val path = fileHelper.getLocalPath(uri) ?: return@mapNotNull null

            if (File(path).length() == 0L) return@mapNotNull null

            val mimeType = fileHelper.getMimeType(uri) ?: "application/octet-stream"
            val inputFile = InputFileLocal(path)

            val caption = FormattedText(
                captionText,
                captionEntities
            )

            val meta = fileHelper.extractMediaMetadata(uri)

            when (attachmentsType) {
                "Media" -> {
                    when {
                        mimeType.startsWith("image/") && !mimeType.endsWith("/gif") -> {
                            val thumbnail = fileHelper.generateThumbnail(path, isVideo = false)
                                ?: fileHelper.generateDummyThumbnail()

                            InputMessagePhoto(
                                photo = inputFile,
                                thumbnail = thumbnail,
                                addedStickerFileIds = intArrayOf(),
                                width = meta.width,
                                height = meta.height,
                                caption = caption,
                                selfDestructType = null,
                                hasSpoiler = false,
                                showCaptionAboveMedia = showCaptionAboveMedia
                            )
                        }

                        mimeType.startsWith("video/") -> {
                            val thumbnail = fileHelper.generateThumbnail(path, isVideo = true)
                                ?: fileHelper.generateDummyThumbnail()

                            InputMessageVideo(
                                video = inputFile,
                                thumbnail = thumbnail,
                                cover = thumbnail.thumbnail,
                                startTimestamp = 0,
                                duration = meta.duration,
                                width = meta.width,
                                height = meta.height,
                                caption = caption,
                                addedStickerFileIds = intArrayOf(),
                                supportsStreaming = true,
                                hasSpoiler = false,
                                selfDestructType = null,
                                showCaptionAboveMedia = showCaptionAboveMedia
                            )
                        }

                        mimeType.startsWith("audio/") -> {
                            InputMessageAudio(
                                audio = inputFile,
                                albumCoverThumbnail = null,
                                duration = meta.duration,
                                title = "",
                                performer = "",
                                caption = caption
                            )
                        }

                        else -> {
                            // Если тип не распознан как фото/видео/аудио, отправляем как документ
                            InputMessageDocument(
                                document = inputFile,
                                thumbnail = null,
                                disableContentTypeDetection = false,
                                caption = caption
                            )
                        }
                    }
                }

                else -> {
                    InputMessageDocument(
                        document = inputFile,
                        thumbnail = null,
                        disableContentTypeDetection = false,
                        caption = caption
                    )
                }
            }
        }

        val finalContentList = mutableListOf<InputMessageContent>()

        finalContentList.addAll(contentList)

        if (finalContentList.isEmpty() && captionText.isNotBlank()) {
            finalContentList.add(
                InputMessageText(
                    FormattedText(captionText, captionEntities),
                    linkPreviewOptions = null,
                    clearDraft = true
                )
            )
        }

        return@withContext finalContentList
    }
}