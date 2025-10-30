package com.xxcactussell.presentation.chats.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BrokenImage
import androidx.compose.material.icons.rounded.PushPin
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.xxcactussell.presentation.LocalRootViewModel
import com.xxcactussell.presentation.chats.model.AvatarUiState
import kotlinx.coroutines.flow.flowOf
import kotlin.math.abs


@Composable
fun ChatAvatar(
    modifier: Modifier = Modifier,
    state: AvatarUiState?,
    isPinned: Boolean,
    isOnline: Boolean
) {
    val context = LocalContext.current
    val rootViewModel = LocalRootViewModel.current
    val fileId = state?.photo?.small?.id

    val fileFlow = remember(fileId) {
        if (fileId != null) rootViewModel.observeFileStatus(fileId) else flowOf(null)
    }
    val currentFile by fileFlow.collectAsState(initial = null)
    val localPath = currentFile?.local?.path
    val isDownloadingComplete = currentFile?.local?.isDownloadingComplete == true
    val avatarColor = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary
    )[abs(state?.chatId?.rem(3) ?: 0).toInt()]

    val initials = state?.title?.split(" ")
        ?.filter { it.isNotBlank() }?.take(2)?.joinToString("") { it.first().uppercase() }?.ifEmpty { "?" } ?: ""

    Box {
        Box(
            modifier = modifier
                .clip(CircleShape)
                .background(avatarColor),
            contentAlignment = Alignment.Center
        ) {

            val shouldLoad = isDownloadingComplete && !localPath.isNullOrEmpty()

            if (shouldLoad) {
                AsyncImage(
                    model = ImageRequest.Builder(context).data(localPath).build(),
                    contentDescription = "${state?.title} avatar",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    error = rememberVectorPainter(Icons.Rounded.BrokenImage)
                )
            } else {
                Text(text = initials, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                LaunchedEffect(key1 = state?.photo?.small?.id) {
                    rootViewModel.downloadFile(state?.photo?.small?.id)
                }
            }
        }
        if (isPinned) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.PushPin,
                    contentDescription = "Pinned",
                    modifier = Modifier.size(10.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        if (isOnline) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {

            }
        }
    }
}