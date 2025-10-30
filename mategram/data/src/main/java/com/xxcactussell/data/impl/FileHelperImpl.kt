package com.xxcactussell.data.impl

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import android.webkit.MimeTypeMap
import com.xxcactussell.domain.utils.FileHelper
import com.xxcactussell.domain.utils.MediaMetadata
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@Singleton
class FileHelperImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
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
        val mimeType = context.contentResolver.getType(uri)
        return mimeType ?: MimeTypeMap.getFileExtensionFromUrl(uri.toString())
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

            width = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH)?.toIntOrNull() ?: 0
            height = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT)?.toIntOrNull() ?: 0
            duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toIntOrNull()?.div(1000) ?: 0
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
}