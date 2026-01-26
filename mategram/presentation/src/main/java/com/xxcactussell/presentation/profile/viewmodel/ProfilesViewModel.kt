package com.xxcactussell.presentation.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxcactussell.presentation.profile.model.ProfileModel
import com.xxcactussell.presentation.profile.model.ProfileUiState
import com.xxcactussell.repositories.profiles.repository.GetBasicGroupFullInfoUseCase
import com.xxcactussell.repositories.profiles.repository.GetChatUseCase
import com.xxcactussell.repositories.profiles.repository.GetSupergroupFullInfoUseCase
import com.xxcactussell.repositories.profiles.repository.GetUserFullInfoUseCase
import com.xxcactussell.repositories.profiles.repository.GetUserUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@AssistedFactory
interface ProfilesViewModelFactory {
    fun create(id: Long, type: String): ProfilesViewModel
}

@HiltViewModel(assistedFactory = ProfilesViewModelFactory::class)
class ProfilesViewModel @AssistedInject constructor(
    private val getUser : GetUserUseCase,
    private val getChat : GetChatUseCase,
    private val getUserFullInfo : GetUserFullInfoUseCase,
    private val getBasicGroupFullInfo : GetBasicGroupFullInfoUseCase,
    private val getSupergroupFullInfo : GetSupergroupFullInfoUseCase,
    @Assisted private val id: Long,
    @Assisted private val type: String
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

    init {
        when(type) {
            "user" -> getUserFullInfo(id)
                .onEach { user ->
                    if (user != null) {
                        _uiState.update {
                            it.copy(
                                profileModel = ProfileModel.UserProfileModel(user)
                            )
                        }
                    }
                }
                .launchIn(viewModelScope)
            "basicgroup" -> getBasicGroupFullInfo(id)
                .onEach { group ->
                    if (group != null) {
                        _uiState.update {
                            it.copy(
                                profileModel = ProfileModel.BasicGroupModel(group)
                            )
                        }
                    }
                }
                .launchIn(viewModelScope)
            "supergroup" -> getSupergroupFullInfo(id)
                .onEach { group ->
                    if (group != null) {
                        _uiState.update {
                            it.copy(
                                profileModel = ProfileModel.SupergroupModel(group)
                            )
                        }
                    }
                }
                .launchIn(viewModelScope)
        }
    }
}