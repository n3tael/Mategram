package com.xxcactussell.domain.files.repository

import com.xxcactussell.domain.files.model.File
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FileRepository {
    fun observeFileStatuses(): Flow<Map<Int, File>>
    suspend fun downloadFile(fileId: Int)
}

class ObserveFileStatusesUseCase @Inject constructor(private val repo: FileRepository) {
    operator fun invoke(): Flow<Map<Int, File>> = repo.observeFileStatuses()
}

class DownloadFileUseCase @Inject constructor(private val repo: FileRepository) {
    suspend operator fun invoke(fileId: Int) = repo.downloadFile(fileId)
}