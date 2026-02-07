package com.xxcactussell.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import com.xxcactussell.presentation.localization.LocalizationManager
import com.xxcactussell.presentation.player.viewmodel.GlobalPlayerViewModel
import com.xxcactussell.presentation.root.viewmodel.RootViewModel

val LocalLocalizationManager = staticCompositionLocalOf<LocalizationManager> {
    error("LocalizationManager not provided")
}

val LocalRootViewModel = staticCompositionLocalOf<RootViewModel> {
    error("RootViewModel not provided. Ensure CompositionLocalProvider is set up in MainActivity.")
}

val LocalPlayerViewModel = staticCompositionLocalOf<GlobalPlayerViewModel> {
    error("GlobalPlayerViewModel not provided. Ensure CompositionLocalProvider is set up in MainActivity.")
}

val LocalNavAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope?> { null }

@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }