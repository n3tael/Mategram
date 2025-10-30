package com.xxcactussell.presentation

import androidx.compose.runtime.staticCompositionLocalOf
import com.xxcactussell.presentation.localization.LocalizationManager
import com.xxcactussell.presentation.root.viewmodel.RootViewModel

val LocalLocalizationManager = staticCompositionLocalOf<LocalizationManager> {
    error("LocalizationManager not provided")
}

val LocalRootViewModel = staticCompositionLocalOf<RootViewModel> {
    error("RootViewModel not provided. Ensure CompositionLocalProvider is set up in MainActivity.")
}