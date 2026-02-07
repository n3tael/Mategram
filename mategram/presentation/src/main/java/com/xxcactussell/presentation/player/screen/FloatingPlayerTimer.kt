package com.xxcactussell.presentation.player.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.xxcactussell.mategram.presentation.R
import com.xxcactussell.presentation.tools.AudioWaveform
import com.xxcactussell.utils.toTimeFormat

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FloatingPlayerTimer(
    modifier: Modifier = Modifier,
    currentTime: Int,
    waveform: ByteArray,
    isPlaying: Boolean,
    isExpanded: Boolean,
    playbackProgress: Float,
    onPlayPauseClick: () -> Unit
) {
    val shape = if (isExpanded) {
        if (isPlaying) RoundedCornerShape(16.dp) else RoundedCornerShape(20.dp)
    } else {
        RoundedCornerShape(10.dp)
    }
    val rowWidth = if (isExpanded) 148.dp else 50.dp
    val rowVerticalPadding = if (isExpanded) 4.dp else 2.dp
    val rowStartPadding = if (isExpanded) 4.dp else 8.dp

    val animatedWidth = animateDpAsState(rowWidth)
    val animateStartPadding = animateDpAsState(rowStartPadding)
    val animateVerticalPadding = animateDpAsState(rowVerticalPadding)

    Row(
        modifier = modifier
            .widthIn(min = animatedWidth.value)
            .clip(shape)
            .background(MaterialTheme.colorScheme.primary)
            .padding(
                start = animateStartPadding.value,
                end = 8.dp
            )
            .padding(vertical = animateVerticalPadding.value),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if(isExpanded) {
            FilledTonalIconToggleButton(
                modifier = Modifier.size(ButtonDefaults.ExtraSmallContainerHeight),
                checked = isPlaying,
                shapes = IconButtonDefaults.toggleableShapes(),
                onCheckedChange = { onPlayPauseClick() },
                colors = IconButtonDefaults.filledTonalIconToggleButtonColors().copy(
                    checkedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    checkedContentColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                )
            ) {
                Icon(
                    painter = painterResource(
                        if (isPlaying) R.drawable.pause_24px else R.drawable.play_arrow_24px
                    ),
                    contentDescription = null
                )
            }

            AudioWaveform(
                modifier = Modifier
                    .width(72.dp)
                    .height(24.dp),
                waveform = waveform,
                progress = playbackProgress,
                activeColor = MaterialTheme.colorScheme.onPrimary,
                inactiveColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f)
            )
        }

        Text(
            text = currentTime.toTimeFormat(),
            style = MaterialTheme.typography.bodySmallEmphasized,
            color = MaterialTheme.colorScheme.onPrimary,
            overflow = TextOverflow.Ellipsis
        )
    }
}