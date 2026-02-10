package com.xxcactussell.data.impl

import com.xxcactussell.data.TdClientManager
import com.xxcactussell.data.utils.mappers.file.toDomain
import com.xxcactussell.domain.File
import com.xxcactussell.repositories.files.repository.FileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.drinkless.tdlib.TdApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FileRepositoryImpl @Inject constructor(
    private val clientManager: TdClientManager
) : FileRepository {

    private val _fileStatuses = MutableStateFlow<Map<Int, File>>(emptyMap())
    private val repositoryScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val _fileUpdates = MutableSharedFlow<File>(
        extraBufferCapacity = 64,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    init {
        clientManager.filesUpdatesFlow
            .buffer(Channel.UNLIMITED)
            .flowOn(Dispatchers.IO)
            .onEach { update ->
                val updatedFile = withContext(Dispatchers.Default) {
                    update.file.toDomain()
                }

                _fileStatuses.update { currentMap ->
                    currentMap + (updatedFile.id to updatedFile)
                }
                _fileUpdates.tryEmit(updatedFile)
            }
            .launchIn(repositoryScope)
    }

    override fun observeFileStatuses(): Flow<Map<Int, File>> = _fileStatuses.asStateFlow()

    override suspend fun downloadFile(fileId: Int) {
        clientManager.send(TdApi.DownloadFile(fileId, 32, 0, 0, false)) { result ->
            if (result is TdApi.File) {
                repositoryScope.launch(Dispatchers.Default) {
                    val updatedFile = result.toDomain()
                    _fileStatuses.update { currentMap ->
                        currentMap + (updatedFile.id to updatedFile)
                    }
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