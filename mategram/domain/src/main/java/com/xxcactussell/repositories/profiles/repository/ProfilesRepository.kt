package com.xxcactussell.repositories.profiles.repository

import com.xxcactussell.domain.BasicGroupFullInfo
import com.xxcactussell.domain.Chat
import com.xxcactussell.domain.SupergroupFullInfo
import com.xxcactussell.domain.User
import com.xxcactussell.domain.UserFullInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ProfilesRepository {
    fun getUser(userId: Long): Flow<User?>
    fun getChat(chatId: Long): Flow<Chat?>
    fun getUserFullInfo(userId: Long): Flow<UserFullInfo?>
    fun getBasicGroupFullInfo(groupId: Long): Flow<BasicGroupFullInfo?>
    fun getSupergroupFullInfo(supergroupId: Long): Flow<SupergroupFullInfo?>
}

class GetUserUseCase @Inject constructor(
    private val profileRepo: ProfilesRepository
) {
    operator fun invoke(userId: Long): Flow<User?> {
        return profileRepo.getUser(userId)
    }
}

class GetChatUseCase @Inject constructor(
    private val profileRepo: ProfilesRepository
) {
    operator fun invoke(chatId: Long): Flow<Chat?> {
        return profileRepo.getChat(chatId)
    }
}

class GetUserFullInfoUseCase @Inject constructor(
    private val profileRepo: ProfilesRepository
) {
    operator fun invoke(userId: Long): Flow<UserFullInfo?> {
        return profileRepo.getUserFullInfo(userId)
    }
}

class GetBasicGroupFullInfoUseCase @Inject constructor(
    private val profileRepo: ProfilesRepository
) {
    operator fun invoke(groupId: Long): Flow<BasicGroupFullInfo?> {
        return profileRepo.getBasicGroupFullInfo(groupId)
    }
}

class GetSupergroupFullInfoUseCase @Inject constructor(
    private val profileRepo: ProfilesRepository
) {
    operator fun invoke(supergroupId: Long): Flow<SupergroupFullInfo?> {
        return profileRepo.getSupergroupFullInfo(supergroupId)
    }
}