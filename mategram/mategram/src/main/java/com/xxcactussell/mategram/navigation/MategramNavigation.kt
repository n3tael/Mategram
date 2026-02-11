package com.xxcactussell.mategram.navigation

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.windowInsetsPadding
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.xxcactussell.presentation.LocalLocalizationManager
import com.xxcactussell.presentation.LocalPlayerViewModel
import com.xxcactussell.presentation.LocalRootViewModel
import com.xxcactussell.presentation.LocalSharedTransitionScope
import com.xxcactussell.presentation.auth.screen.navigation.AuthScreen
import com.xxcactussell.presentation.chats.screen.ChatsListScreen
import com.xxcactussell.presentation.chats.viewmodel.ChatsViewModel
import com.xxcactussell.presentation.localization.LocalizationManager
import com.xxcactussell.presentation.mediaviewer.screen.MediaViewerScreen
import com.xxcactussell.presentation.messages.model.InputEvent
import com.xxcactussell.presentation.messages.screen.MessagesScreen
import com.xxcactussell.presentation.messages.viewmodel.MessagesViewModel
import com.xxcactussell.presentation.messages.viewmodel.MessagesViewModelFactory
import com.xxcactussell.presentation.player.screen.DraggableAreaForFloatingPlayer
import com.xxcactussell.presentation.player.screen.FloatingPlayerWidget
import com.xxcactussell.presentation.player.viewmodel.GlobalPlayerViewModel
import com.xxcactussell.presentation.profile.screen.ProfileScreen
import com.xxcactussell.presentation.root.viewmodel.RootViewModel
import com.xxcactussell.presentation.tools.CameraScreen

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalSharedTransitionApi::class)
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

    val playerViewModel = hiltViewModel<GlobalPlayerViewModel>()
    val playerState by playerViewModel.playerState.collectAsState()

    SharedTransitionLayout {
        CompositionLocalProvider(
            LocalLocalizationManager provides localizationManager,
            LocalRootViewModel provides rootViewModel,
            LocalPlayerViewModel provides playerViewModel,
            LocalSharedTransitionScope provides this
        ) {
            Box (
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .windowInsetsPadding(WindowInsets.ime),
                content = {
                    NavDisplay(
                        modifier = Modifier.fillMaxSize(),
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
                            entry<RouteChatList>(
                                metadata = ListDetailSceneStrategy.listPane(
                                    sceneKey = "ChatLDPane"
                                )
                            ) {
                                val entryOwner = LocalViewModelStoreOwner.current ?: throw IllegalStateException("ViewModelStoreOwner not found")
                                val viewModel: ChatsViewModel = hiltViewModel<ChatsViewModel>(viewModelStoreOwner = entryOwner)
                                ChatsListScreen(
                                    viewModel = viewModel,
                                    onChatClick = { chatId, messageId, lastReadInboxMessageId ->
                                        if (backStack.last() is RouteChatDetail) {
                                            backStack[backStack.lastIndex] = RouteChatDetail(chatId, messageId, lastReadInboxMessageId)
                                        } else {
                                            backStack.add(RouteChatDetail(chatId, messageId, lastReadInboxMessageId))
                                        }
                                    },
                                    onFabClicked = { },
                                    onAvatarClicked = { }
                                )
                            }
                            entry<RouteChatDetail>(
                                metadata = ListDetailSceneStrategy.detailPane(
                                    sceneKey = "ChatLDPane"
                                ) + NavDisplay.transitionSpec {
                                    slideInHorizontally(
                                        initialOffsetX = { (it * 1.1).toInt() },
                                    ) togetherWith slideOutHorizontally(
                                        targetOffsetX = { (it * 1.1).toInt() }
                                    )
                                } + NavDisplay.popTransitionSpec {
                                    slideInHorizontally(
                                        initialOffsetX = { -(it * 0.1).toInt() },
                                    ) + scaleIn(
                                        initialScale = 0.92f,
                                        transformOrigin = TransformOrigin(0.5f, 0.5f)
                                    ) togetherWith slideOutHorizontally(
                                        targetOffsetX = { (it * 1.1).toInt() }
                                    )
                                } + NavDisplay.predictivePopTransitionSpec {
                                    slideInHorizontally(
                                        initialOffsetX = { -(it * 0.1).toInt() },
                                    ) + scaleIn(
                                        initialScale = 0.92f,
                                        transformOrigin = TransformOrigin(0.5f, 0.5f)
                                    ) togetherWith slideOutHorizontally(
                                        targetOffsetX = { (it * 1.1).toInt() }
                                    )
                                },
                            ) { chat ->
                                val entryOwner = LocalViewModelStoreOwner.current ?: throw IllegalStateException("ViewModelStoreOwner not found")

                                MessagesScreen(
                                    chatId = chat.chatId,
                                    startMessageId = chat.messageId,
                                    owner = entryOwner,
                                    lastReadInboxMessageId = chat.lastReadInboxMessageId,
                                    onProfileClicked = { },
                                    onCameraClicked = {
                                        backStack.add(
                                            CameraRoute(
                                                0,
                                                chat.chatId
                                            )
                                        )
                                    },
                                    onBackHandled = {
                                        if (backStack.size > 1) {
                                            backStack.removeLastOrNull()
                                        }
                                    },
                                    onLinkClicked = { chatId, messageId ->
                                        backStack.add(RouteChatDetail(chatId, messageId))
                                    },
                                    onMediaClicked = { messageId ->
                                        backStack.add(
                                            RouteMediaViewer(
                                                messageId,
                                                chat.chatId
                                            )
                                        )
                                    }
                                )
                            }

                            entry<RouteMediaViewer> { media ->
                                MediaViewerScreen(
                                    messageId = media.messageId,
                                    chatId = media.chatId,
                                    onBack = {
                                        if (backStack.size > 1) {
                                            backStack.removeLastOrNull()
                                        }
                                    }
                                )
                            }

                            entry<CameraRoute> { it ->
                                val viewModel: MessagesViewModel = hiltViewModel(
                                    creationCallback = { factory: MessagesViewModelFactory ->
                                        factory.create(it.chatId)
                                    },
                                    key = "messages_screen_${it.chatId!!}"
                                )
                                CameraScreen(
                                    mode = it.mode,
                                    onBackHandled = {
                                        if (backStack.size > 1) {
                                            backStack.removeLastOrNull()
                                        }
                                    },
                                    chatId = it.chatId,
                                    onMediaCaptured = { uris ->
                                        viewModel.onEvent(InputEvent.MediaSelected(uris))
                                    }
                                )
                            }

                            entry<RouteProfile> { profile ->
                                ProfileScreen(
                                    id = profile.id,
                                    type = profile.type,
                                    onBackHandled = {
                                        if (backStack.size > 1) {
                                            backStack.removeLastOrNull()
                                        }
                                    }
                                )
                            }
                        }
                    )
                    AnimatedVisibility (
                        visible = playerState.currentMediaId != null,
                        enter = slideInHorizontally { -it },
                        exit = fadeOut()
                    ) {
                        DraggableAreaForFloatingPlayer(
                            viewModel = playerViewModel,
                            onWidgetClick = { chatId, messageId ->
                                if (backStack.last() is RouteChatDetail && backStack.size > 1) {
                                    backStack.removeLastOrNull()
                                }
                                backStack.add(RouteChatDetail(chatId, messageId))
                            },
                            modifier = Modifier
                        )
                    }
                }
            )
        }
    }
}
