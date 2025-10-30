package com.xxcactussell.mategram.navigation

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation.BackNavigationBehavior
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.xxcactussell.presentation.root.viewmodel.RootViewModel
import com.xxcactussell.presentation.auth.screen.navigation.AuthScreen
import com.xxcactussell.presentation.chats.screen.ChatsListScreen
import com.xxcactussell.presentation.LocalLocalizationManager
import com.xxcactussell.presentation.LocalRootViewModel
import com.xxcactussell.presentation.localization.LocalizationManager
import com.xxcactussell.presentation.messages.screen.MessagesScreen

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MategramNavigation(
    pickMultipleMedia: ActivityResultLauncher<PickVisualMediaRequest>,
    localizationManager: LocalizationManager = hiltViewModel<RootViewModel>().localizationManager
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        localizationManager.initialize(context, this)
    }

    val backStack = rememberNavBackStack(RouteAuth)

    val windowAdaptiveInfo = currentWindowAdaptiveInfo()

    val directive = remember(windowAdaptiveInfo) {
        calculatePaneScaffoldDirective(windowAdaptiveInfo)
            .copy(horizontalPartitionSpacerSize = 24.dp)
            .copy(maxVerticalPartitions = 1)
    }

    val listDetailStrategy = rememberListDetailSceneStrategy<NavKey>(
        backNavigationBehavior = BackNavigationBehavior.PopLatest,
        directive = directive,
    )

    val rootViewModel: RootViewModel = hiltViewModel(LocalActivity.current as ComponentActivity)

    CompositionLocalProvider(
        LocalLocalizationManager provides localizationManager, LocalRootViewModel provides rootViewModel
    ) {
        NavDisplay(
            modifier = Modifier.background(
                MaterialTheme.colorScheme.surfaceContainerHighest
            ),
            backStack = backStack,
            sceneStrategy = listDetailStrategy,
            entryProvider = entryProvider {
                entry<RouteAuth> {
                    AuthScreen(
                        onAuthSuccess = {
                            backStack.clear()
                            backStack.add(RouteChatList)
                        },
                        onSettingsLanguageClick = {
                            //TODO
                        }
                    )
                }
                entry<RouteChatList> (
                    metadata = ListDetailSceneStrategy.listPane(
                        sceneKey = "ChatLDPane"
                    )
                ) {
                    ChatsListScreen(
                        onChatClick = { chatId ->
                            if (backStack.last() is RouteChatDetail) {
                                backStack[backStack.lastIndex] = RouteChatDetail(chatId)
                            } else {
                                backStack.add(RouteChatDetail(chatId))
                            }
                        },
                        onFabClicked = { },
                        onAvatarClicked = { }
                    )
                }
                entry<RouteChatDetail>(
                    metadata = ListDetailSceneStrategy.detailPane(
                        sceneKey = "ChatLDPane"
                    ),
                ) { chat ->
                    MessagesScreen(
                        chatId = chat.chatId,
                        onProfileClicked = { },
                        onBackHandled = { backStack.removeLastOrNull() }
                    )
                }
            }
        )
    }
}