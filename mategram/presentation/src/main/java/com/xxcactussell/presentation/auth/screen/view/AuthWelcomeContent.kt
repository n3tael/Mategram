package com.xxcactussell.presentation.auth.screen.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.xxcactussell.mategram.presentation.R
import com.xxcactussell.presentation.localization.localizedString

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AuthWelcomeContent(onSignInClick: () -> Boolean, onSettingsLanguageClick: () -> Unit) {
    val scrollState = rememberScrollState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                title = {
                    Image(
                        painterResource(id = R.drawable.ic_mategram_logo_circle),
                        modifier = Modifier.size(40.dp),
                        contentDescription = null
                    )
                }
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Surface(
                    modifier = Modifier
                        .widthIn(max = 600.dp)
                        .align(Alignment.BottomCenter),
                    shape = RoundedCornerShape(topStart = 64.dp, topEnd = 64.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 42.dp)
                            .windowInsetsPadding(
                                WindowInsets.navigationBars
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val size = ButtonDefaults.MediumContainerHeight
                        Button(
                            onClick = { onSignInClick() },
                            modifier = Modifier.heightIn(size),
                            shape = ButtonDefaults.squareShape,
                            contentPadding = ButtonDefaults.contentPaddingFor(size),
                        ) {
                            Text(localizedString("StartMessaging"), style = ButtonDefaults.textStyleFor(size))
                        }
                        TextButton(
                            { onSettingsLanguageClick() }
                        ) {
                            Text(
                                localizedString("LanguageTitle")
                            )
                        }
                    }
                }
            }
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 24.dp, end = 24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        localizedString("greeting"),
                        style = MaterialTheme.typography.displayMedium
                    )
                    Spacer(Modifier.height(24.dp))
                    Text(
                        localizedString("welcome_message"),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(Modifier.height(24.dp))
                    Card(
                        shape = RoundedCornerShape(64.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(42.dp),
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painterResource(id = R.drawable.ic_welcome_jetpack_compose),
                                    modifier = Modifier.size(48.dp),
                                    contentDescription = null
                                )
                                Spacer(Modifier.width(24.dp))
                                Text(
                                    localizedString("welcome_jc"),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            Spacer(Modifier.height(42.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painterResource(id = R.drawable.ic_welcome_kotlin),
                                    modifier = Modifier.size(48.dp),
                                    contentDescription = null
                                )
                                Spacer(Modifier.width(24.dp))
                                Text(
                                    localizedString("welcome_kotlin"),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            Spacer(Modifier.height(42.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painterResource(id = R.drawable.ic_welcome_material_design),
                                    modifier = Modifier.size(48.dp),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Spacer(Modifier.width(24.dp))
                                Text(
                                    localizedString("welcome_md"),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(42.dp))
                }
            }
        }
    )
}