package com.xxcactussell.data.impl

import android.util.Log
import com.xxcactussell.data.TdClientManager
import com.xxcactussell.data.TelegramCredentials
import com.xxcactussell.data.utils.mappers.auth.toDomain
import com.xxcactussell.domain.AuthorizationState
import com.xxcactussell.repositories.auth.repository.AuthRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.Ok

class AuthRepositoryImpl @Inject constructor(
    private val clientManager: TdClientManager
) : AuthRepository {
    override fun observeAuthState(): Flow<AuthorizationState> {
        if (clientManager.authUpdatesFlow.value is TdApi.AuthorizationStateWaitTdlibParameters) {
            setTdlibParameters()
        }
        return clientManager.authUpdatesFlow
            .map { tdLibState ->
                mapTdLibStateToDomain(tdLibState)
            }
    }

    override fun setTdlibParameters() {
        clientManager.send(TdApi.SetTdlibParameters(
            TelegramCredentials.USE_TEST_DC,
            TelegramCredentials.databaseDirectory,
            TelegramCredentials.filesDirectory,
            TelegramCredentials.encryptionKey,
            TelegramCredentials.USE_FILE_DATABASE,
            TelegramCredentials.USE_CHAT_INFO_DATABASE,
            TelegramCredentials.USE_MESSAGE_DATABASE,
            TelegramCredentials.USE_SECRET_CHATS,
            TelegramCredentials.API_ID,
            TelegramCredentials.API_HASH,
            TelegramCredentials.systemLanguageCode,
            TelegramCredentials.deviceModel,
            TelegramCredentials.systemVersion,
            TelegramCredentials.APPLICATION_VERSION
        )
        ) { result ->
            if (result is Ok) {
                Log.d("TdLibRepo", "TDLib parameters set successfully")
                clientManager.send(TdApi.SetOption("localization_target", TdApi.OptionValueString("android"))) { optionResult ->
                    if (optionResult is TdApi.Error) {
                        Log.e("TdLibRepo", "Failed to set localization_target option: ${optionResult.message}")
                    } else {
                        Log.d("TdLibRepo", "Successfully set localization_target option.")
                    }
                }
            } else {
                Log.e("TdLibRepo", "TDLib parameters not set successfully")
            }
        }
    }

    override fun setPhoneNumber(phone: String) {
        clientManager.send(
            TdApi.SetAuthenticationPhoneNumber(phone, null),
            null
        )
    }

    override fun checkAuthCode(code: String) {
        clientManager.send(
            TdApi.CheckAuthenticationCode(code),
            null
        )
    }

    override fun resendAuthCode() {
    }

    override fun checkAuthPassword(password: String) {
        clientManager.send(
            TdApi.CheckAuthenticationPassword(password),
            null
        )
    }

    override fun requestRecoveryCodePassword() {
        TODO("Not yet implemented")
    }

    override fun checkRecoveryCodePassword(recoveryCode: String) {
        TODO("Not yet implemented")
    }

    override fun setOnlineStatus(status: Boolean) {
        clientManager.send(
            TdApi.SetOption("online", TdApi.OptionValueBoolean(status))
        )
    }

    private fun mapTdLibStateToDomain(tdLibState: TdApi.AuthorizationState): AuthorizationState {
        return tdLibState.toDomain()
    }
}