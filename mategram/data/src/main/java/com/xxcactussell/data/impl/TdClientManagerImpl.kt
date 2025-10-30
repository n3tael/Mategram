package com.xxcactussell.data.impl

import android.content.Context
import android.util.Log
import com.xxcactussell.data.TdClientManager
import com.xxcactussell.data.TelegramCredentials
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.drinkless.tdlib.Client
import org.drinkless.tdlib.TdApi
import java.io.File

@Singleton
class TdClientManagerImpl @Inject constructor() : TdClientManager {
    private lateinit var _client: Client
    override val client: Client
        get() = _client

    private val _authUpdatesFlow = MutableStateFlow<TdApi.AuthorizationState>(TdApi.AuthorizationStateWaitTdlibParameters())
    override val authUpdatesFlow: StateFlow<TdApi.AuthorizationState> = _authUpdatesFlow.asStateFlow()

    private val _newMessagesFlow = MutableSharedFlow<TdApi.UpdateNewMessage>(
        replay = 25,
        extraBufferCapacity = Int.MAX_VALUE,
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    override val newMessagesFlow: SharedFlow<TdApi.UpdateNewMessage> = _newMessagesFlow

    private val _chatsUpdatesFlow = MutableSharedFlow<TdApi.Update>(
        replay = 25,
        extraBufferCapacity = Int.MAX_VALUE,
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    override val chatsUpdatesFlow: SharedFlow<TdApi.Update> = _chatsUpdatesFlow.asSharedFlow()

    private val _chatFoldersUpdatesFlow = MutableSharedFlow<TdApi.Update>(
        replay = 25,
        extraBufferCapacity = Int.MAX_VALUE,
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    override val chatFoldersUpdatesFlow: SharedFlow<TdApi.Update> = _chatFoldersUpdatesFlow.asSharedFlow()

    private val _filesUpdatesFlow = MutableSharedFlow<TdApi.UpdateFile>(
        replay = 25,
        extraBufferCapacity = Int.MAX_VALUE,
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    override val filesUpdatesFlow: SharedFlow<TdApi.UpdateFile> = _filesUpdatesFlow.asSharedFlow()

    private val _updatesFlow = MutableSharedFlow<TdApi.Object>(
        replay = 25,
        extraBufferCapacity = Int.MAX_VALUE,
        onBufferOverflow = BufferOverflow.SUSPEND
    )

    override val updatesFlow: SharedFlow<TdApi.Object> = _updatesFlow.asSharedFlow()

    init {
        try {
            System.loadLibrary("tdjni")
        } catch (e: UnsatisfiedLinkError) {
            e.printStackTrace()
        }
    }

    private val tdClientManagerScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun initialize(context: Context) {
        if (this::_client.isInitialized) return

        _client = Client.create({ update ->
            tdClientManagerScope.launch {
                when (update) {
                    is TdApi.UpdateAuthorizationState -> {
                        _authUpdatesFlow.value = update.authorizationState
                    }
                    is TdApi.UpdateChatFolders,
                    is TdApi.UpdateUnreadChatCount ->
                        _chatFoldersUpdatesFlow.tryEmit(update)
                    is TdApi.UpdateNewChat,
                    is TdApi.UpdateChatPosition,
                    is TdApi.UpdateChatLastMessage,
                    is TdApi.UpdateChatReadInbox,
                    is TdApi.UpdateChatTitle,
                    is TdApi.UpdateChatPhoto,
                    is TdApi.UpdateUser,
                    is TdApi.UpdateUserStatus ->
                        _chatsUpdatesFlow.tryEmit(update)
                    is TdApi.UpdateFile ->
                        _filesUpdatesFlow.tryEmit(update)
                    is TdApi.UpdateNewMessage ->
                        _newMessagesFlow.tryEmit(update)
                }
                _updatesFlow.tryEmit(update)
            }
        }, null, null)

        TelegramCredentials.initialize(context)

        val languagePackDbPath = File(context.filesDir, "language_pack.sqlite").absolutePath

        send(TdApi.SetOption("language_pack_database_path", TdApi.OptionValueString(languagePackDbPath))) { result ->
            if (result is TdApi.Error) {
                Log.e("TdLibRepo", "Failed to set language_pack_database_path option: ${result.message}")
            } else {
                Log.d("TdLibRepo", "Successfully set language_pack_database_path option to $languagePackDbPath.")
            }
        }
    }

    override fun send(query: TdApi.Function<*>, handler: Client.ResultHandler?) {
        _client.send(query, handler)
    }
}