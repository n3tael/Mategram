package com.xxcactussell.presentation.profile.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.xxcactussell.presentation.profile.viewmodel.ProfilesViewModel
import com.xxcactussell.presentation.profile.viewmodel.ProfilesViewModelFactory

@Composable
fun ProfileScreen(
    id: Long,
    type: String,
    onBackHandled: () -> Unit
) {
    val viewModel: ProfilesViewModel = hiltViewModel(
        creationCallback = { factory: ProfilesViewModelFactory ->
            factory.create(id, type)
        },
        key = "profile_screen_$id"
    )
    val state = viewModel.uiState.collectAsState()

    ProfileContent(
        state = state,
        onBackHandle = onBackHandled
    )
}