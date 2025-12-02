package com.xxcactussell.presentation.chats.screen

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import kotlin.math.abs


@Composable
fun ChatAvatar(
    modifier: Modifier = Modifier,
    state: AvatarUiState?,
    isPinned: Boolean,
    isOnline: Boolean
) {
    val rootViewModel = LocalRootViewModel.current
    val fileUpdates = rootViewModel.files.collectAsState()

    val avatarColor = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary
    )[abs(state?.chatId?.rem(3) ?: 0).toInt()]

    val initials = state?.title?.split(" ")
        ?.filter { it.isNotBlank() }?.take(2)?.joinToString("") { it.first().uppercase() }
        ?.ifEmpty { "?" } ?: ""

    val avatar = state?.photo?.small
    Box {
        Box(
            modifier = modifier
                .clip(CircleShape)
                .background(avatarColor),
            contentAlignment = Alignment.Center
        ) {
            if (avatar != null) {
                val file = fileUpdates.value[avatar.id] ?: avatar

                if (file.local.isDownloadingCompleted && file.local.path.isNotEmpty()) {
                    var bitmap by remember { mutableStateOf<ImageBitmap?>(null) }

                    LaunchedEffect(file.local.path) {
                        withContext(Dispatchers.IO) {
                            try {
                                val decodedBitmap = BitmapFactory.decodeFile(file.local.path)
                                bitmap = decodedBitmap?.asImageBitmap()
                            } catch (e: Exception) {
                                // Handle exceptions
                            }
                        }
                    }

                    if (bitmap != null) {
                        Image(
                            bitmap = bitmap!!,
                            contentDescription = "${state.title} avatar",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                        )
                    } else {
                        Text(
                            text = initials,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                } else {
                    Text(
                        text = initials,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    if (!file.local.isDownloadingActive && !file.local.isDownloadingCompleted) {
                        LaunchedEffect(file.id) {
                            rootViewModel.downloadFile(file.id)
                        }
                    }
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