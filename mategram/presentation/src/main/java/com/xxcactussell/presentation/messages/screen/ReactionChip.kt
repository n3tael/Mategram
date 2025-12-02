package com.xxcactussell.presentation.messages.screen

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.xxcactussell.domain.messages.model.FormattedText
import com.xxcactussell.domain.messages.model.MessageReaction
import com.xxcactussell.domain.messages.model.ReactionType
import com.xxcactussell.domain.messages.model.TextEntity
import com.xxcactussell.domain.messages.model.TextEntityTypeCustomEmoji
import com.xxcactussell.presentation.tools.FormattedTextView

@Composable
fun ReactionChip(reaction: MessageReaction, onClick: () -> Unit) {
    val icon : @Composable (() -> Unit) = when (val type = reaction.type) {
        is ReactionType.Custom -> {
            {
                val entities = listOf(TextEntity(0,1, TextEntityTypeCustomEmoji(type.id)))

                val textDp = 16.dp

                val textSp = with(LocalDensity.current) {
                    textDp.toSp()
                }

                FormattedTextView(
                    text = FormattedText("❓", entities),
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = textSp),
                    maxLines = 1
                )
            }
        }
        is ReactionType.Emoji -> {
            {
                val textDp = 16.dp

                val textSp = with(LocalDensity.current) {
                    textDp.toSp()
                }

                Text(
                    text = type.emoji,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = textSp),
                    maxLines = 1
                )
            }
        }
        is ReactionType.Paid -> {
            {
                Icon(
                    Icons.Rounded.Star,
                    ""
                )
            }
        }
    }

    FilterChip(
        modifier = Modifier.height(32.dp),
        selected = reaction.isChosen,
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        label = {
            Text(reaction.totalCount.toString())
        },
        leadingIcon = {
            icon.invoke()
        }
    )
}