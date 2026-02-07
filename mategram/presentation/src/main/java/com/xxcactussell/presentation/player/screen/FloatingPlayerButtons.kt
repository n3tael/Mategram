package com.xxcactussell.presentation.player.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xxcactussell.mategram.presentation.R
import com.xxcactussell.presentation.localization.localizedString
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FloatingPlayerButtons(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    onCloseClick: () -> Unit,
    onGoToMessageClick: () -> Unit
) {
    val springSpec = spring<IntOffset>(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessLow
    )
    val fadeSpec = spring<Float>(
        dampingRatio = Spring.DampingRatioNoBouncy,
        stiffness = Spring.StiffnessLow
    )

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        AnimatedVisibility(
            visible = isExpanded,
            enter = fadeIn(animationSpec = fadeSpec) +
                    slideInVertically(animationSpec = springSpec) { -it },
            exit = fadeOut(animationSpec = fadeSpec) +
                    slideOutVertically(animationSpec = springSpec) { -it }
        ) {
            Button(
                onClick = onGoToMessageClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                modifier = Modifier.height(40.dp)
            ) {
                Icon(
                    painterResource(id = R.drawable.chat_paste_go_24px),
                    contentDescription = "Go to message",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = localizedString("AccDescrGoToMessage"),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

        AnimatedVisibility(
            visible = isExpanded,
            enter = fadeIn(animationSpec = fadeSpec) +
                    slideInVertically(animationSpec = springSpec) { -2*it },
            exit = fadeOut(animationSpec = fadeSpec) +
                    slideOutVertically(animationSpec = springSpec) { -2*it }
        ) {
            Button(
                onClick = onCloseClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                ),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                modifier = Modifier.height(40.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.close_24px),
                    contentDescription = "close",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = localizedString("Close"),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Preview
@Composable
fun FloatingPlayerButtonsExpandedPreview() {
        FloatingPlayerButtons(
            isExpanded = true,
            onCloseClick = {},
            onGoToMessageClick = {}
        )
}
