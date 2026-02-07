package com.xxcactussell.data.impl

import com.xxcactussell.data.TdClientManager
import com.xxcactussell.data.utils.mappers.chat.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.user.toDomain
import com.xxcactussell.domain.Chat
import com.xxcactussell.domain.Message
import com.xxcactussell.domain.MessageReplyToMessage
import com.xxcactussell.domain.MessageSenderChat
import com.xxcactussell.domain.MessageSenderUser
import com.xxcactussell.domain.MessageVideoNote
import com.xxcactussell.domain.MessageVoiceNote
import com.xxcactussell.domain.User
import com.xxcactussell.domain.toChatPhotoInfo
import com.xxcactussell.player.MediaRepository
import com.xxcactussell.player.playablemedia.PlayableMedia
import com.xxcactussell.repositories.messages.utils.getId
import jakarta.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.suspendCancellableCoroutine
import org.drinkless.tdlib.TdApi
import kotlin.coroutines.resume

class MediaRepositoryImpl @Inject constructor(
    private val clientManager: TdClientManager,
    private val messagesRepository: MessagesRepositoryImpl,
    private val fileRepository: FileRepositoryImpl
) : MediaRepository {
    override suspend fun getMediaById(chatId: Long, messageId: Long): PlayableMedia? {
        val message: Message? = suspendCancellableCoroutine { continuation ->
            clientManager.send(TdApi.GetMessage(chatId, messageId)) { result ->
                if (continuation.isActive) {
                    when (result) {
                        is TdApi.Message -> continuation.resumeWith(Result.success(result.toDomain()))
                        else -> continuation.resumeWith(Result.failure(Exception("Message not found")))
                    }
                }
            }
        }

        val chat : Any? = when (message?.senderId) {
            is MessageSenderChat -> {
                message.senderId.getId().let {
                    if (it != null) {
                        suspendCancellableCoroutine { continuation ->
                            clientManager.send(TdApi.GetChat(it)) { result ->
                                if (continuation.isActive) {
                                    when (result) {
                                        is TdApi.Chat -> continuation.resumeWith(
                                            Result.success(
                                                result.toDomain()
                                            )
                                        )

                                        else -> continuation.resumeWith(Result.failure(Exception("Chat not found")))
                                    }
                                }
                            }
                        }
                    } else null
                }
            }
            is MessageSenderUser -> {
                message.senderId.getId().let {
                    if (it != null) {
                        suspendCancellableCoroutine { continuation ->
                            clientManager.send(TdApi.GetUser(it)) { result ->
                                if (continuation.isActive) {
                                    when (result) {
                                        is TdApi.User -> continuation.resumeWith(
                                            Result.success(
                                                result.toDomain()
                                            )
                                        )
                                        else -> continuation.resumeWith(Result.failure(Exception("User not found")))
                                    }
                                }
                            }
                        }
                    } else null
                }
            }
            else -> null
        }

        return coroutineScope {
            if (message != null && (message.content is MessageVoiceNote || message.content is MessageVideoNote)) {
                when (chat) {
                    is Chat -> PlayableMedia.fromMessage(chat, message)
                    is User -> PlayableMedia.fromMessage(chat, message)
                    else -> PlayableMedia.fromMessage(message)
                }
            } else {
                null
            }
        }
    }

    private fun PlayableMedia.Companion.fromMessage(chat: Chat?, message: Message): PlayableMedia? = when(message.content) {
        is MessageVoiceNote -> PlayableMedia.Voice(
            chatId = message.chatId,
            id = message.id.toString(),
            fileId = (message.content as MessageVoiceNote).voiceNote.voice.id,
            url = (message.content as MessageVoiceNote).voiceNote.voice.local.path,
            artist = chat?.title ?: "",
            waveform = (message.content as MessageVoiceNote).voiceNote.waveform,
            artworkUri = null,
            avatar = chat?.photo
        )
        is MessageVideoNote -> PlayableMedia.Video(
            chatId = message.chatId,
            id = message.id.toString(),
            fileId = (message.content as MessageVideoNote).videoNote.video.id,
            url = (message.content as MessageVideoNote).videoNote.video.local.path,
            artist = chat?.title ?: "",
            artworkUri = null,
            width = (message.content as MessageVideoNote).videoNote.length,
            height = (message.content as MessageVideoNote).videoNote.length,
            avatar = chat?.photo
        )
        else -> null
    }

    private fun PlayableMedia.Companion.fromMessage(user: User?, message: Message): PlayableMedia? = when(message.content) {
        is MessageVoiceNote -> PlayableMedia.Voice(
            chatId = message.chatId,
            id = message.id.toString(),
            fileId = (message.content as MessageVoiceNote).voiceNote.voice.id,
            url = (message.content as MessageVoiceNote).voiceNote.voice.local.path,
            artist = "${user?.firstName} ${user?.lastName}",
            waveform = (message.content as MessageVoiceNote).voiceNote.waveform,
            artworkUri = null,
            avatar = user?.profilePhoto?.toChatPhotoInfo()
        )
        is MessageVideoNote -> PlayableMedia.Video(
            chatId = message.chatId,
            id = message.id.toString(),
            fileId = (message.content as MessageVideoNote).videoNote.video.id,
            url = (message.content as MessageVideoNote).videoNote.video.local.path,
            artist = "${user?.firstName} ${user?.lastName}",
            artworkUri = null,
            width = (message.content as MessageVideoNote).videoNote.length,
            height = (message.content as MessageVideoNote).videoNote.length,
            avatar = user?.profilePhoto?.toChatPhotoInfo()
        )
        else -> null
    }

    private fun PlayableMedia.Companion.fromMessage(message: Message): PlayableMedia? = when(message.content) {
        is MessageVoiceNote -> PlayableMedia.Voice(
            chatId = message.chatId,
            id = message.id.toString(),
            fileId = (message.content as MessageVoiceNote).voiceNote.voice.id,
            url = (message.content as MessageVoiceNote).voiceNote.voice.local.path,
            artist = "",
            waveform = (message.content as MessageVoiceNote).voiceNote.waveform,
            artworkUri = null,
            avatar = null
        )
        is MessageVideoNote -> PlayableMedia.Video(
            chatId = message.chatId,
            id = message.id.toString(),
            fileId = (message.content as MessageVideoNote).videoNote.video.id,
            url = (message.content as MessageVideoNote).videoNote.video.local.path,
            artist = "",
            artworkUri = null,
            width = (message.content as MessageVideoNote).videoNote.length,
            height = (message.content as MessageVideoNote).videoNote.length,
            avatar = null
        )
        else -> null
    }

    override suspend fun getNextMedia(
        chatId: Long,
        currentMessageId: Long,
    ): PlayableMedia? {
        val messages = messagesRepository.getProcessor(chatId).getMessagesToId(currentMessageId)
        return messages.lastOrNull {
            (it.content is MessageVoiceNote || it.content is MessageVideoNote) && it.id != currentMessageId
        }?.let {
            getMediaById(chatId, it.id)
        }
    }

    override suspend fun getPreviousMedia(
        chatId: Long,
        currentMessageId: Long,
    ): PlayableMedia? {
        val messages = messagesRepository.getProcessor(chatId).getMessagesFromId(currentMessageId)
        return messages.firstOrNull {
            (it.content is MessageVoiceNote || it.content is MessageVideoNote) && it.id != currentMessageId
        }?.let {
            getMediaById(chatId, it.id)
        }
    }

    override suspend fun downloadMedia(fileId: Int): String {
        val completedFile = suspendCancellableCoroutine { continuation ->
            clientManager.send(
                TdApi.DownloadFile(fileId, 32, 0, 0, true)
            ) { result ->
                if (continuation.isActive) {
                    when (result) {
                        is TdApi.File -> {
                            continuation.resume(result)
                        }

                        else -> continuation.resume(null)
                    }
                }
            }
        }
        return coroutineScope {
            completedFile?.local?.path ?: ""
        }
    }

    override fun markVoiceVideoAsViewed(chatId: Long, messageId: Long) {
        clientManager.send(TdApi.OpenMessageContent(chatId, messageId))
    }
}