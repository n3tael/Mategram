package com.xxcactussell.domain.files.repository

import com.xxcactussell.domain.messages.model.File
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FileRepository {
    fun observeFileStatuses(): Flow<Map<Int, File>>
    suspend fun downloadFile(fileId: Int)
    fun cancelDownload(fileId: Int, onlyIfPending: Boolean = false)
    fun addToDownloads(fileId: Int, chatId: Long, messageId: Long, priority: Int = 16)
}

class ObserveFileStatusesUseCase @Inject constructor(private val repo: FileRepository) {
    operator fun invoke(): Flow<Map<Int, File>> = repo.observeFileStatuses()
}

class DownloadFileUseCase @Inject constructor(private val repo: FileRepository) {
    suspend operator fun invoke(fileId: Int) = repo.downloadFile(fileId)
}

class AddFileToDownloadsUseCase @Inject constructor(private val repo: FileRepository) {
    operator fun invoke(fileId: Int, chatId: Long, messageId: Long, priority: Int) = repo.addToDownloads(fileId, chatId, messageId, priority)
}

class CancelDownloadFileUseCase @Inject constructor(private val repo: FileRepository) {
    operator fun invoke(fileId: Int, onlyIfPending: Boolean = false) = repo.cancelDownload(fileId, onlyIfPending)

}