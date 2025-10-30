package com.xxcactussell.mategram

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import com.xxcactussell.mategram.navigation.MategramNavigation
import com.xxcactussell.mategram.ui.theme.MategramTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MategramActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val pickMultipleMedia =
            registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(10)) { uris ->
                if (uris.isNotEmpty()) {
                    // putSelectedMediaUris(uris)
                } else {
                    Log.d("PhotoPicker", "No media selected")
                }
            }

        setContent {
            MategramTheme {
                MategramNavigation(pickMultipleMedia)
            }
        }
    }
}