package com.xxcactussell.player

import com.xxcactussell.player.playablemedia.PlayableMedia

interface MediaRepository {
    suspend fun getMediaById(chatId: Long, messageId: Long): PlayableMedia?
    suspend fun getNextMedia(chatId: Long, currentMessageId: Long): PlayableMedia?
    suspend fun getPreviousMedia(chatId: Long, currentMessageId: Long): PlayableMedia?
    suspend fun downloadMedia(fileId: Int): String
    fun markVoiceVideoAsViewed(chatId: Long, messageId: Long)
}