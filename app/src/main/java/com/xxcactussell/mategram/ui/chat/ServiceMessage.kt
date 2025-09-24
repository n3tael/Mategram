package com.xxcactussell.mategram.ui.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.LargeFlexibleTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.drinkless.tdlib.TdApi

@Composable
fun ServiceMessage(message: TdApi.Message, senderName: String, spaceBetweenMessage: Dp) {
    val content = message.content
    val serviceMessgeText = when(content) {
        is TdApi.MessageVideoChatScheduled -> {
            content.startDate.toString()
        }
        is TdApi.MessageVideoChatStarted -> {
            content.groupCallId.toString()
        }
        is TdApi.MessageVideoChatEnded -> {
            content.duration.toString()
        }
        is TdApi.MessageInviteVideoChatParticipants ->
            content.userIds.toString()
        is TdApi.MessageBasicGroupChatCreate ->
            "$senderName создал(-а) группу ${content.title}"
        is TdApi.MessageSupergroupChatCreate ->
            "$senderName создал(-а) канал ${content.title}"
        is TdApi.MessageChatChangeTitle ->
            "$senderName изменил(-а) название чата на ${content.title}"
        is TdApi.MessageChatChangePhoto ->
            "$senderName обновил(-а) фотографию чата"
        is TdApi.MessageChatDeletePhoto ->
            "$senderName удалил(-а) фотографию чата"
        is TdApi.MessageChatAddMembers ->
            "$senderName добавил(-а) участников: ${content.memberUserIds}"
        is TdApi.MessageChatJoinByLink ->
            "$senderName присоединился(-ась) по ссылке"
        is TdApi.MessageChatJoinByRequest ->
            "Заявкка от $senderName на вступление принята администратором"
        is TdApi.MessageChatDeleteMember ->
            "$senderName удалил(-а) ${content.userId} из чата"
        is TdApi.MessageChatUpgradeTo,
        is TdApi.MessageChatUpgradeFrom ->
            "Группа обновлена до супергруппы"
        is TdApi.MessagePinMessage ->
            "$senderName закрепил(-а) сообщение: ${content.messageId}"
        is TdApi.MessageScreenshotTaken ->
            "$senderName сделал(-а) скриншот"
        is TdApi.MessageChatSetBackground,
        is TdApi.MessageChatSetTheme ->
            "Тема чата обновлена"
        is TdApi.MessageChatSetMessageAutoDeleteTime ->
            "Автоудаление установлено на ${content.messageAutoDeleteTime}"
        is TdApi.MessageChatBoost ->
            "$senderName забустил(-а) чат"
        is TdApi.MessageForumTopicCreated ->
            "$senderName создал(-а) топик"
        is TdApi.MessageForumTopicEdited ->
            "$senderName изменил(-а) топик"
        is TdApi.MessageForumTopicIsClosedToggled ->
            "$senderName изменил(-а) приватности видимости топика"
        is TdApi.MessageForumTopicIsHiddenToggled ->
            "$senderName изменил(-а) настройки видимости топика"
        is TdApi.MessageSuggestProfilePhoto ->
            "Предложение поменять аватарку"
        is TdApi.MessageCustomServiceAction ->
            content.text
        else -> "Неизвестное событие"
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, spaceBetweenMessage, 16.dp, 0.dp),
    ) {
        Card(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .align(Alignment.Center),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                text = serviceMessgeText,
                modifier = Modifier.padding(16.dp, 4.dp),
                style = MaterialTheme.typography.labelMedium.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}