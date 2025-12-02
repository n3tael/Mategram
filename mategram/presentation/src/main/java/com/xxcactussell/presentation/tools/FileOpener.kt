package com.xxcactussell.presentation.tools

import android.content.Context
import android.content.Intent
import android.webkit.MimeTypeMap
import androidx.core.content.FileProvider
import java.io.File
import java.util.Locale

object FileOpener {

    fun openFile(context: Context, file: File) {
        if (!file.exists()) {
            return
        }

        val extension = file.extension.lowercase(Locale.ROOT)

        openRegularFile(context, file, extension)
    }

    private fun openRegularFile(context: Context, file: File, extension: String) {
        try {
            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                file
            )

            val mimeType = if (extension == "apk") {
                "application/vnd.android.package-archive"
            } else {
                MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension) ?: "*/*"
            }

            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, mimeType)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}