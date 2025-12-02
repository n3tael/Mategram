package com.xxcactussell.presentation.tools

import android.R.attr.path
import android.graphics.Bitmap
import android.graphics.SurfaceTexture
import android.view.Surface
import android.view.TextureView
import androidx.camera.camera2.compat.workaround.TargetAspectRatio
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.createBitmap
import coil.compose.AsyncImage
import coil3.request.ImageRequest
import com.xxcactussell.rlottie.RLottiePlayer
import com.xxcactussell.vpplayer.VPPlayer
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.util.zip.GZIPInputStream
import kotlin.math.roundToInt

@Composable
fun LottieSticker(
    path: String,
    size: Dp,
    modifier: Modifier = Modifier
) {
    val sizePx = getMaxRenderSize(size)

    val bitmapState = remember(path) {
        SharedStickerEngine.getFlowFor(path, sizePx)
    }
        .collectAsState(initial = null)

    val currentBitmap = bitmapState.value

    if (currentBitmap != null) {
        Image(
            bitmap = currentBitmap,
            contentDescription = null,
            modifier = modifier.size(size),
            contentScale = ContentScale.Fit
        )
    } else {
        Box(modifier = modifier)
    }
}

@Composable
fun WebPImage(
    path: String,
    size: Dp,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = File(path),
        contentDescription = "WebP Sticker",
        contentScale = ContentScale.Fit,
        modifier = modifier.size(size)
    )
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun StickerWEBMPlayer(
    modifier: Modifier = Modifier,
    path: String,
    aspectRatio: Float = 1f,
    size: Dp
) {
    val sizePx = getMaxRenderSize(size)

    val bitmapState = remember(path) {
        SharedWebMEngine.getFlowFor(path)
    }
        .collectAsState(initial = null)

    val currentBitmap = bitmapState.value

    if (currentBitmap != null) {
        Image(
            bitmap = currentBitmap,
            contentDescription = "WebM Sticker",
            modifier = modifier.size(size).aspectRatio(aspectRatio),
            contentScale = ContentScale.Fit
        )
    } else {
        Box(modifier = modifier.size(size))
    }
}