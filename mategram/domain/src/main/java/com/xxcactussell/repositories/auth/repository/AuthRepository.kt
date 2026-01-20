package com.xxcactussell.repositories.auth.repository

import com.xxcactussell.domain.AuthorizationState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AuthRepository {
    fun observeAuthState(): Flow<AuthorizationState>

    fun setTdlibParameters()
    fun setPhoneNumber(phone: String)

    fun checkAuthCode(code: String)
    fun resendAuthCode()

    fun checkAuthPassword(password: String)
    fun requestRecoveryCodePassword()
    fun checkRecoveryCodePassword(recoveryCode: String)
    fun setOnlineStatus(status: Boolean)
}

class SetTDLibParametersUseCase @Inject constructor(private val repo: AuthRepository) {
    operator fun invoke() = repo.setTdlibParameters()
}
class GetAuthStateUseCase @Inject constructor(private val repo: AuthRepository) {
    operator fun invoke(): Flow<AuthorizationState> = repo.observeAuthState()
}

class SetPhoneNumberUseCase @Inject constructor(private val repo: AuthRepository) {
    operator fun invoke(phone: String) = repo.setPhoneNumber(phone)
}

class CheckAuthCodeUseCase @Inject constructor(private val repo: AuthRepository) {
    operator fun invoke(code: String) = repo.checkAuthCode(code)
}

class CheckAuthPasswordUseCase @Inject constructor(private val repo: AuthRepository) {
    operator fun invoke(password: String) = repo.checkAuthPassword(password)
}

class SetOnlineStatusUseCase @Inject constructor(private val repo: AuthRepository) {
    operator fun invoke(status: Boolean) = repo.setOnlineStatus(status)
}