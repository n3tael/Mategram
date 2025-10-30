package com.xxcactussell.presentation.auth.screen.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.xxcactussell.presentation.auth.model.AuthEvent
import com.xxcactussell.presentation.auth.model.AuthStep
import com.xxcactussell.presentation.auth.screen.view.AuthInputCodeView
import com.xxcactussell.presentation.auth.screen.view.AuthInputPasswordView
import com.xxcactussell.presentation.auth.screen.view.AuthInputPhoneView
import com.xxcactussell.presentation.auth.screen.view.AuthWelcomeContent
import com.xxcactussell.presentation.auth.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onAuthSuccess: () -> Unit,
    onSettingsLanguageClick: () -> Unit
) {
    val authBackStack = rememberNavBackStack(RouteLoading)
    val state by viewModel.uiState.collectAsState()
    val haptic = LocalHapticFeedback.current

    LaunchedEffect(state) {
        when(state.currentStep) {
            is AuthStep.Loading -> {
                if (authBackStack.last() !is RouteLoading) {
                    authBackStack.clear()
                    authBackStack.add(RouteLoading)
                }
            }
            is AuthStep.Success -> {
                (viewModel::onEvent) (AuthEvent.SuccessAuth(true))
                onAuthSuccess()
            }
            is AuthStep.InputPhone -> {
                if (authBackStack.last() is RouteLoading) {
                    authBackStack.clear()
                    authBackStack.add(RouteWelcome)
                }
            }
            is AuthStep.InputCode -> {
                if (authBackStack.last() !is RouteAuthCode) {
                    authBackStack.add(RouteAuthCode)
                }
            }
            is AuthStep.InputPassword -> {
                if (authBackStack.last() !is RouteAuthPassword) {
                    authBackStack.remove(RouteAuthCode)
                    authBackStack.add(RouteAuthPassword)
                }
            }
            is AuthStep.Error -> {
                authBackStack.clear()
                authBackStack.add(RouteLoading)
            }
        }
    }

    NavDisplay(
        backStack = authBackStack,
        entryProvider = entryProvider {

            entry<RouteLoading> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LoadingIndicator()
                }
            }

            entry<RouteWelcome> {
                AuthWelcomeContent(
                    onSignInClick = {
                        haptic.performHapticFeedback(HapticFeedbackType.Confirm)
                        authBackStack.remove(RouteWelcome)
                        authBackStack.add(RouteAuthPhone)
                    },
                    onSettingsLanguageClick = {
                        onSettingsLanguageClick()
                    }
                )
            }

            entry<RouteAuthPhone> {
                AuthInputPhoneView(
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }

            entry<RouteAuthCode> {
                AuthInputCodeView(
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }

            entry<RouteAuthPassword> {
                AuthInputPasswordView(
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }

        }
    )
}
