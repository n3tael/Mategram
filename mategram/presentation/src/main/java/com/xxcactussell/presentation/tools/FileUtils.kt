package com.xxcactussell.presentation.tools

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.webkit.MimeTypeMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.UUID

object FileUtils {
    suspend fun copyUriToCache(context: Context, uri: Uri): Uri? = withContext(Dispatchers.IO) {
        try {
            val contentResolver = context.contentResolver
            var extension: String? = null

            if (uri.scheme == "content") {
                val cursor = contentResolver.query(uri, null, null, null, null)
                cursor?.use {
                    if (it.moveToFirst()) {
                        val displayNameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                        if (displayNameIndex != -1) {
                            val displayName = it.getString(displayNameIndex)
                            if (!displayName.isNullOrEmpty() && displayName.contains(".")) {
                                extension = displayName.substringAfterLast('.')
                            }
                        }
                    }
                }
            }

            if (extension.isNullOrEmpty()) {
                val mimeType = contentResolver.getType(uri)
                if (mimeType != null) {
                    extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType)
                    if (extension == "jpeg") extension = "jpg"
                }
            }

            if (extension.isNullOrEmpty()) {
                extension = "bin"
            }

            val fileName = "upload_${UUID.randomUUID()}.$extension"
            val cacheFile = File(context.cacheDir, fileName)

            contentResolver.openInputStream(uri)?.use { input ->
                cacheFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }

            Uri.fromFile(cacheFile)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun clearOldCacheFiles(context: Context) {
        val cacheDir = context.cacheDir
        val timeThreshold = System.currentTimeMillis() - (24 * 60 * 60 * 1000)

        cacheDir.listFiles()?.forEach { file ->
            if (file.name.startsWith("upload_") && file.lastModified() < timeThreshold) {
                file.delete()
            }
        }
    }
}