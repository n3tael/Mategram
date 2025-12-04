package com.xxcactussell.presentation.tools

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

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
    val imageState = produceState<ImageBitmap?>(initialValue = null, key1 = path) {
        val cached = StickerFrameCache.get(path)
        if (cached != null) {
            value = cached
            return@produceState
        }
        val loadedImage = withContext(Dispatchers.IO) {
            val file = File(path)
            if (file.exists()) {
                try {
                    val options = BitmapFactory.Options().apply {
                        inPreferredConfig = Bitmap.Config.HARDWARE
                    }
                    val bitmap = BitmapFactory.decodeFile(file.absolutePath, options)
                    bitmap?.asImageBitmap()
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            } else {
                null
            }
        }
        if (loadedImage != null) {
            StickerFrameCache.put(path, loadedImage)
            value = loadedImage
        }
    }

    if (imageState.value != null) {
        Image(
            bitmap = imageState.value!!,
            contentDescription = "WebP Sticker",
            contentScale = ContentScale.Fit,
            modifier = modifier.size(size)
        )
    } else {
        Box(modifier = modifier.size(size))
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun StickerWEBMPlayer(
    modifier: Modifier = Modifier,
    path: String,
    aspectRatio: Float = 1f,
    size: Dp
) {
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