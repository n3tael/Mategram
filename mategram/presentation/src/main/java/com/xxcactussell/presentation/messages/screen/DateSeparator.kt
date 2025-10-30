package com.xxcactussell.presentation.messages.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.xxcactussell.presentation.tools.formatTimestampToDate

@Composable
fun DateSeparator(timestamp: Int) {
    val date = formatTimestampToDate(timestamp)
    Box(
        Modifier.Companion.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.Companion.align(Alignment.Companion.Center),
            text = date,
            style = MaterialTheme.typography.labelSmall
        )
    }
}