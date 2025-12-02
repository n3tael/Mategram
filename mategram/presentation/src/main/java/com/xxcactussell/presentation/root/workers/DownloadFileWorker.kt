package com.xxcactussell.presentation.root.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues
import android.content.Context
import android.content.pm.ServiceInfo
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.xxcactussell.domain.files.repository.CancelDownloadFileUseCase
import com.xxcactussell.domain.files.repository.DownloadFileUseCase
import com.xxcactussell.domain.files.repository.ObserveFileStatusesUseCase
import com.xxcactussell.mategram.presentation.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withTimeoutOrNull
import java.io.File
import java.io.FileInputStream

@HiltWorker
class DownloadFileWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val downloadFileUseCase: DownloadFileUseCase,
    private val observeFileStatusesUseCase: ObserveFileStatusesUseCase,
    private val cancelDownloadFileUseCase: CancelDownloadFileUseCase
) : CoroutineWorker(appContext, params) {

    companion object {
        const val INPUT_FILE_TD_ID = "input_file_td_id"
        const val INPUT_FILE_NAME = "input_file_name"
        const val NOTIF_CHANNEL_ID = "downloads_channel"
        const val NOTIF_ID_BASE = 10000
    }

    override suspend fun doWork(): Result {
        val tdFileId = inputData.getInt(INPUT_FILE_TD_ID, -1)
        val fileName = inputData.getString(INPUT_FILE_NAME) ?: "mategram_file"

        if (tdFileId == -1) return Result.failure()

        val notificationId = NOTIF_ID_BASE + tdFileId

        try {
            setForeground(createForegroundInfo(0, fileName, notificationId, true))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        downloadFileUseCase(tdFileId)

        val downloadedFile = withTimeoutOrNull(10 * 60 * 1000L) {
            observeFileStatusesUseCase()
                .map { it[tdFileId] }
                .filter { file ->
                    if (file == null) return@filter false

                    val downloaded = file.local.downloadedSize
                    val total = file.expectedSize

                    if (total > 0) {
                        val percent = ((downloaded * 100) / total).toInt()
                        setForeground(createForegroundInfo(percent, fileName, notificationId, true))
                    }
                    file.local.isDownloadingCompleted
                }
                .first()
        }
        if (downloadedFile == null || !downloadedFile.local.isDownloadingCompleted) {
            return Result.failure()
        }
        val sourcePath = downloadedFile.local.path
        if (sourcePath.isEmpty()) return Result.failure()

        val finalUri = copyFileToPublicDownloads(sourcePath, fileName)
            ?: return Result.failure()

        return Result.success(workDataOf("output_uri" to finalUri.toString()))
    }

    private fun copyFileToPublicDownloads(sourcePath: String, fileName: String): Uri? {
        val resolver = applicationContext.contentResolver
        val relativePath = "Download/Mategram"

        val values = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "application/octet-stream")
            put(MediaStore.MediaColumns.RELATIVE_PATH, relativePath)
            put(MediaStore.MediaColumns.IS_PENDING, 1)
        }

        val collection =
            MediaStore.Downloads.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)

        val uri = resolver.insert(collection, values) ?: return null

        return try {
            val sourceFile = File(sourcePath)
            resolver.openOutputStream(uri)?.use { outputStream ->
                FileInputStream(sourceFile).use { inputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            values.clear()
            values.put(MediaStore.MediaColumns.IS_PENDING, 0)
            resolver.update(uri, values, null, null)

            uri
        } catch (e: Exception) {
            e.printStackTrace()
            resolver.delete(uri, null, null)
            null
        }
    }

    private fun createForegroundInfo(
        progressPercent: Int,
        fileName: String,
        notificationId: Int,
        indeterminate: Boolean = false
    ): ForegroundInfo {
        createChannelIfNeeded()

        val cancel = applicationContext.getString(android.R.string.cancel)

        val contentText = if (progressPercent > 0) "Загрузка: $progressPercent%" else "Подготовка..."

        val notification = NotificationCompat.Builder(applicationContext, NOTIF_CHANNEL_ID)
            .setContentTitle(fileName)
            .setContentText(contentText)
            .setSmallIcon(R.drawable.ic_download_24)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .setProgress(100, progressPercent, progressPercent == 0 && indeterminate)
            .addAction(
                android.R.drawable.ic_delete,
                cancel,
                androidx.work.WorkManager.getInstance(applicationContext)
                    .createCancelPendingIntent(id)
            )
            .build()

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            ForegroundInfo(
                notificationId,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
            )
        } else {
            ForegroundInfo(notificationId, notification)
        }
    }

    private fun createChannelIfNeeded() {
        val nm = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (nm.getNotificationChannel(NOTIF_CHANNEL_ID) == null) {
            val ch = NotificationChannel(
                NOTIF_CHANNEL_ID,
                applicationContext.getString(R.string.DownloadsTabs),
                NotificationManager.IMPORTANCE_LOW
            )
            nm.createNotificationChannel(ch)
        }
    }
}