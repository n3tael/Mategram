package com.xxcactussell.data.impl

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import android.webkit.MimeTypeMap
import com.xxcactussell.domain.InputFileLocal
import com.xxcactussell.domain.InputThumbnail
import com.xxcactussell.repositories.utils.FileHelper
import com.xxcactussell.repositories.utils.MediaMetadata
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.text.toInt
import kotlin.times
import androidx.core.graphics.scale
import androidx.core.graphics.createBitmap

@Singleton
class FileHelperImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
) : FileHelper {
    override suspend fun getLocalPath(uri: Uri): String? = withContext(Dispatchers.IO) {
        val cursor = context.contentResolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)
        val fileName = cursor?.use {
            if (it.moveToFirst()) it.getString(it.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME)) else null
        } ?: "upload_${System.currentTimeMillis()}"
        try {
            val tempFile = File(context.cacheDir, fileName)
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                FileOutputStream(tempFile).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            return@withContext tempFile.absolutePath
        } catch (e: Exception) {
            return@withContext null
        }
    }

    override fun getMimeType(uri: Uri): String? {
        val resolverMimeType = context.contentResolver.getType(uri)
        if (resolverMimeType != null) {
            return resolverMimeType
        }

        val extension = MimeTypeMap.getFileExtensionFromUrl(uri.toString())
            ?: uri.path?.substringAfterLast('.', "")

        if (!extension.isNullOrEmpty()) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.lowercase())
        }

        return null
    }

    override suspend fun extractMediaMetadata(uri: Uri): MediaMetadata = withContext(Dispatchers.IO) {
        val retriever = MediaMetadataRetriever()
        var thumbnailBytes: ByteArray? = null
        var width = 0
        var height = 0
        var duration = 0

        try {
            retriever.setDataSource(context, uri)
            val frameBitmap = retriever.getFrameAtTime(
                MediaMetadataRetriever.OPTION_CLOSEST_SYNC.toLong()
            )

            width = maxOf(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH)?.toIntOrNull() ?: 0, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_IMAGE_WIDTH)?.toIntOrNull() ?: 0)
            height = maxOf(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT)?.toIntOrNull() ?: 0, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_IMAGE_HEIGHT)?.toIntOrNull() ?: 0)

            val tempDuration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLongOrNull() ?: 0L
            duration = (tempDuration / 1000L).toInt()

            thumbnailBytes = frameBitmap?.let { bitmap ->
                val outputStream = ByteArrayOutputStream()
                bitmap.compress(
                    Bitmap.CompressFormat.JPEG,
                    80,
                    outputStream
                )
                bitmap.recycle()
                outputStream.toByteArray()
            }
        } catch (e: Exception) {
            Log.e("FileHelper", "Error extracting metadata", e)
        } finally {
            try {
                retriever.release()
            } catch (e: IOException) {
                // Ignore
            }
        }

        return@withContext MediaMetadata(
            width = width,
            height = height,
            duration = duration,
            thumbnail = thumbnailBytes
        )
    }

    override suspend fun generateThumbnail(path: String, isVideo: Boolean): InputThumbnail? {
        try {
            val maxDimension = 320
            val bitmap: Bitmap = if (isVideo) {
                val retriever = MediaMetadataRetriever()
                retriever.setDataSource(path)
                val frame = retriever.getFrameAtTime(0)
                retriever.release()
                frame ?: return null
            } else {
                val options = BitmapFactory.Options().apply { inJustDecodeBounds = false }
                BitmapFactory.decodeFile(path, options) ?: return null
            }

            val scale = maxDimension.toFloat() / maxOf(bitmap.width, bitmap.height)
            val newWidth = (bitmap.width * scale).toInt()
            val newHeight = (bitmap.height * scale).toInt()
            val scaledBitmap = bitmap.scale(newWidth, newHeight)

            val thumbFile = File(context.cacheDir, "thumb_${System.currentTimeMillis()}.jpg")
            FileOutputStream(thumbFile).use { out ->
                scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out)
            }

            if (bitmap != scaledBitmap) bitmap.recycle()
            scaledBitmap.recycle()

            return InputThumbnail(InputFileLocal(thumbFile.absolutePath), newWidth, newHeight)

        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    override suspend fun generateDummyThumbnail(): InputThumbnail {
        val thumbFile = File(context.cacheDir, "dummy_thumb.jpg")
        if (!thumbFile.exists()) {
            val bitmap = createBitmap(1, 1)
            FileOutputStream(thumbFile).use { out ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out)
            }
        }
        return InputThumbnail(InputFileLocal(thumbFile.absolutePath), 1, 1)
    }
}