package com.xxcactussell.data.impl

import com.xxcactussell.data.TdClientManager
import com.xxcactussell.data.utils.mappers.basic.toDomain
import com.xxcactussell.data.utils.mappers.chat.toDomain
import com.xxcactussell.data.utils.mappers.supergroup.toDomain
import com.xxcactussell.data.utils.mappers.user.toDomain
import com.xxcactussell.domain.BasicGroupFullInfo
import com.xxcactussell.domain.Chat
import com.xxcactussell.domain.SupergroupFullInfo
import com.xxcactussell.domain.User
import com.xxcactussell.domain.UserFullInfo
import com.xxcactussell.repositories.profiles.repository.ProfilesRepository
import jakarta.inject.Inject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.drinkless.tdlib.TdApi

class ProfilesRepositoryImpl @Inject constructor(
    private val clientManager: TdClientManager
) : ProfilesRepository {
    override fun getUser(userId: Long): Flow<User?> = callbackFlow {
        clientManager.send(TdApi.GetUser(userId)) { result ->
            trySend((result as? TdApi.User)?.toDomain())
            close()
        }

        awaitClose {

        }
    }

    override fun getChat(chatId: Long): Flow<Chat?> = callbackFlow {
        clientManager.send(TdApi.GetChat(chatId)) { result ->
            trySend((result as? TdApi.Chat)?.toDomain())
            close()
        }

        awaitClose {

        }
    }

    override fun getUserFullInfo(userId: Long): Flow<UserFullInfo?> = callbackFlow {
        clientManager.send(TdApi.GetUserFullInfo(userId)) { result ->
            trySend((result as? TdApi.UserFullInfo)?.toDomain())
            close()
        }

        awaitClose {

        }
    }

    override fun getBasicGroupFullInfo(groupId: Long): Flow<BasicGroupFullInfo?> = callbackFlow {
        clientManager.send(TdApi.GetBasicGroupFullInfo(groupId)) { result ->
            trySend((result as? TdApi.BasicGroupFullInfo)?.toDomain())
            close()
        }

        awaitClose {

        }
    }

    override fun getSupergroupFullInfo(supergroupId: Long): Flow<SupergroupFullInfo?> = callbackFlow {
        clientManager.send(TdApi.GetSupergroupFullInfo(supergroupId)) { result ->
            trySend((result as? TdApi.SupergroupFullInfo)?.toDomain())
            close()
        }

        awaitClose {

        }
    }
}
