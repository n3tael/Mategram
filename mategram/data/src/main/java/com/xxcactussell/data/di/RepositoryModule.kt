package com.xxcactussell.data.di

import com.xxcactussell.data.TdClientManager
import com.xxcactussell.data.impl.AuthRepositoryImpl
import com.xxcactussell.data.impl.ChatsRepositoryImpl
import com.xxcactussell.data.impl.CustomEmojiStickerRepositoryImpl
import com.xxcactussell.data.impl.FileHelperImpl
import com.xxcactussell.data.impl.FileRepositoryImpl
import com.xxcactussell.data.impl.LanguageRepositoryImpl
import com.xxcactussell.data.impl.MessagesRepositoryImpl
import com.xxcactussell.data.impl.TdClientManagerImpl
import com.xxcactussell.repositories.auth.repository.AuthRepository
import com.xxcactussell.repositories.chats.repository.ChatsRepository
import com.xxcactussell.repositories.files.repository.FileRepository
import com.xxcactussell.repositories.localization.repository.LanguageRepository
import com.xxcactussell.repositories.messages.repository.MessagesRepository
import com.xxcactussell.repositories.root.repository.CustomEmojiStickerRepository
import com.xxcactussell.repositories.utils.FileHelper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindTdClientManager(impl: TdClientManagerImpl) : TdClientManager

    @Binds
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl) : AuthRepository

    @Singleton
    @Binds
    abstract fun bindCustomEmojiStickerRepository(impl: CustomEmojiStickerRepositoryImpl) : CustomEmojiStickerRepository

    @Binds
    abstract fun bindMessagesRepository(impl: MessagesRepositoryImpl) : MessagesRepository

    @Singleton
    @Binds
    abstract fun bindChatsRepository(impl: ChatsRepositoryImpl) : ChatsRepository

    @Singleton
    @Binds
    abstract fun bindFileRepository(impl: FileRepositoryImpl) : FileRepository

    @Singleton
    @Binds
    abstract fun bindLanguageRepository(impl: LanguageRepositoryImpl) : LanguageRepository

    @Singleton
    @Binds
    abstract fun bindFileHelper(impl: FileHelperImpl): FileHelper
}