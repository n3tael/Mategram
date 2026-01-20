package com.xxcactussell.data.impl

import com.xxcactussell.data.TdClientManager
import com.xxcactussell.data.utils.mappers.file.toDomain
import com.xxcactussell.domain.File
import com.xxcactussell.repositories.files.repository.FileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import org.drinkless.tdlib.TdApi
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val clientManager: TdClientManager
) : FileRepository {

    private val _fileStatuses = MutableStateFlow<Map<Int, File>>(emptyMap())
    private val repositoryScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        clientManager.filesUpdatesFlow
            .onEach { update ->
                _fileStatuses.update { currentMap ->
                    val updatedFile = update.file.toDomain()
                    currentMap + (updatedFile.id to updatedFile)
                }
            }
            .launchIn(repositoryScope)
    }

    override fun observeFileStatuses(): Flow<Map<Int, File>> = _fileStatuses.asStateFlow()

    override suspend fun downloadFile(fileId: Int) {
        clientManager.send(TdApi.DownloadFile(fileId, 1, 0, 0, true)) { result ->
            if (result is TdApi.File) {
                _fileStatuses.update { currentMap ->
                    val updatedFile = result.toDomain()
                    currentMap + (updatedFile.id to updatedFile)
                }
            }
        }
    }

    override fun cancelDownload(fileId: Int, onlyIfPending: Boolean) {
        clientManager.send(TdApi.CancelDownloadFile(fileId, onlyIfPending))
    }

    override fun addToDownloads(fileId: Int, chatId: Long, messageId: Long, priority: Int) {
        clientManager.send(TdApi.AddFileToDownloads(fileId, chatId, messageId, priority))
    }
}