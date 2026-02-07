package com.xxcactussell.presentation.messages.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.xxcactussell.customdomain.ForwardFullInfo
import com.xxcactussell.mategram.presentation.R
import com.xxcactussell.presentation.localization.localizedString
import com.xxcactussell.presentation.tools.FormattedTextView

@Composable
fun ForwardMarkup(
    forwardInfo: ForwardFullInfo,
    isOutgoing: Boolean,
    onLinkClicked: (Long, Long?) -> Unit
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (isOutgoing) {
            Icon(
                painterResource(R.drawable.forward_24px),
                "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(16.dp)
            )
        }
        Surface(
            modifier = Modifier
                .height(36.dp)
                .widthIn(max = 200.dp)
                .clip(RoundedCornerShape(18.dp))
                .clickable {
                    if (forwardInfo.link?.chatId != null) onLinkClicked(
                        forwardInfo.link?.chatId!!,
                        forwardInfo.link?.messageId
                    )
                },
            color = MaterialTheme.colorScheme.outlineVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            shape = RoundedCornerShape(18.dp)
        ) {
            val text = when {
                !forwardInfo.chat.isNullOrEmpty() && !forwardInfo.signature.isNullOrEmpty() -> "${forwardInfo.chat} (${forwardInfo.signature})"
                !forwardInfo.chat.isNullOrEmpty() -> forwardInfo.chat!!
                !forwardInfo.signature.isNullOrEmpty() -> forwardInfo.signature!!
                else -> null
            }
            Row(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp).padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text ?: if (forwardInfo.isHidden) localizedString("AnonymousForward") else localizedString("HiddenName"),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }
        if (!isOutgoing) {
            Icon(
                painterResource(R.drawable.forward_24px),
                "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}