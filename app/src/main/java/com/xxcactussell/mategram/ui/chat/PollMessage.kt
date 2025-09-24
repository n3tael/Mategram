package com.xxcactussell.mategram.ui.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xxcactussell.mategram.MainViewModel
import kotlinx.coroutines.launch
import org.drinkless.tdlib.TdApi.FormattedText
import org.drinkless.tdlib.TdApi.Message
import org.drinkless.tdlib.TdApi.Poll
import org.drinkless.tdlib.TdApi.PollTypeQuiz
import org.drinkless.tdlib.TdApi.PollTypeRegular

class ExplanationSnackbarVisuals(
    override val message: String,
    val annotatedMessage: FormattedText,
    val inlineContent: Map<String, InlineTextContent>,
    override val actionLabel: String? = "ОК",
    override val withDismissAction: Boolean = false,
    override val duration: SnackbarDuration = SnackbarDuration.Short
) : SnackbarVisuals

@Composable
fun ExplanationSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { snackbarData ->
            val visuals = snackbarData.visuals as? ExplanationSnackbarVisuals
            if (visuals != null) {
                Snackbar(
                    modifier = Modifier.padding(8.dp),
                    action = {
                        Button(onClick = { snackbarHostState.currentSnackbarData?.dismiss() }) {
                            Text(visuals.actionLabel ?: "ОК")
                        }
                    }
                ) {
                    Text(
                        text = getAnnotatedString(visuals.annotatedMessage),
                        inlineContent = visuals.inlineContent
                    )
                }
            }
        },
        modifier = modifier
    )
}

@Composable
fun PollMessage(
    poll: Poll,
    inlineContent: MutableMap<String, InlineTextContent>,
    item: Message,
    viewModel: MainViewModel,
    snackBarHostState: SnackbarHostState
) {
    val headline = poll.question
    val options = poll.options
    var selectedOptions by remember { mutableStateOf<Set<Int>>(emptySet()) }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            modifier = Modifier.padding(bottom = 4.dp),
            text = getAnnotatedString(headline),
            style = MaterialTheme.typography.bodyLarge,
            inlineContent = inlineContent
        )
        val type =
            if (poll.isAnonymous && poll.type is PollTypeRegular) {
                "Анонимное голосование"
            } else if(poll.isAnonymous && poll.type is PollTypeQuiz) {
                "Анонимный квиз"
            } else if (!poll.isAnonymous && poll.type is PollTypeRegular) {
                "Голосование"
            } else if(!poll.isAnonymous && poll.type is PollTypeQuiz) {
                "Квиз"
            }else {
                ""
            }

        Text(
            modifier = Modifier.padding(bottom = 4.dp),
            text = "$type - Голосов: ${poll.totalVoterCount}",
            style = MaterialTheme.typography.labelSmall
        )
        val showIconButton = remember { mutableStateOf(
                if (poll.type is PollTypeQuiz) {
                    true
                } else if (poll.type is PollTypeRegular) {
                    (poll.type as PollTypeRegular).allowMultipleAnswers
                } else {
                    false
                }
            )
        }
        val showVoices = remember {
            mutableStateOf(
                if (poll.isClosed) {
                    true
                } else if (options.any { it.isChosen }) {
                    true
                } else {
                    false
                }
            )
        }
        options.forEachIndexed { index, option ->
            FilledTonalButton (
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (poll.type is PollTypeRegular && !(poll.type as PollTypeRegular).allowMultipleAnswers && !poll.isClosed && options.none { it.isChosen }) {
                        scope.launch {
                            selectedOptions = setOf(index)
                            viewModel.choseAnswer(
                                selectedOptions.toIntArray(),
                                item.chatId,
                                item.id
                            )
                            viewModel.getMessagesForChat(item.chatId)
                        }
                    } else if (poll.type is PollTypeQuiz && !poll.isClosed && options.none { it.isChosen })  {
                        selectedOptions = setOf(index)
                    } else if (poll.type is PollTypeRegular && (poll.type as PollTypeRegular).allowMultipleAnswers && !poll.isClosed && options.none { it.isChosen }) {
                        if (index in selectedOptions) {
                            selectedOptions -= index
                        } else {
                            selectedOptions += index
                        }
                    }
                },
                contentPadding = if(showIconButton.value) {
                    ButtonDefaults.TextButtonWithIconContentPadding
                } else {
                    ButtonDefaults.TextButtonContentPadding
                },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor =
                    when (poll.type) {
                        is PollTypeQuiz -> {
                            if ((poll.type as PollTypeQuiz).correctOptionId == index && !option.isChosen && options.any { it.isChosen }) {
                                MaterialTheme.colorScheme.primaryContainer
                            } else if ((poll.type as PollTypeQuiz).correctOptionId != index && option.isChosen) {
                                MaterialTheme.colorScheme.errorContainer
                            } else {
                                MaterialTheme.colorScheme.secondaryContainer
                            }
                        }
                        else -> {
                            if (option.isChosen) {
                                MaterialTheme.colorScheme.primaryContainer
                            } else {
                                MaterialTheme.colorScheme.secondaryContainer
                            }
                        }
                    }
                )
            )  {
                if (showIconButton.value) {
                    Icon(
                        if (option.isBeingChosen || option.isChosen || index in selectedOptions) {
                            Icons.Default.RadioButtonChecked
                        } else {
                            Icons.Default.RadioButtonUnchecked
                        },
                        "Checked"
                    )
                    Spacer(Modifier.width(4.dp))
                }
                Text(
                    text = getAnnotatedString(option.text),
                    style = MaterialTheme.typography.bodyMedium,
                    inlineContent = inlineContent,
                    modifier = Modifier.weight(1f)
                )
                if (showVoices.value) {
                    Text(
                        text = "${option.votePercentage}%",
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
            }
        }
        if (options.none { it.isChosen }) (
            when (val pollType = poll.type) {
                is PollTypeQuiz -> {
                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if (selectedOptions.isNotEmpty()) {
                                scope.launch {
                                    viewModel.choseAnswer(
                                        selectedOptions.toIntArray(),
                                        item.chatId,
                                        item.id
                                    )
                                    viewModel.getMessagesForChat(item.chatId)
                                    if ((poll.type as PollTypeQuiz).correctOptionId !in selectedOptions) {
                                        snackBarHostState.showSnackbar(
                                            visuals = ExplanationSnackbarVisuals(
                                                message = "Важное сообщение!",
                                                annotatedMessage = (poll.type as PollTypeQuiz).explanation,
                                                inlineContent = inlineContent
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    ) {
                        Text(
                            "Ответить"
                        )
                    }
                }
                is PollTypeRegular -> {
                    if (pollType.allowMultipleAnswers) {
                        TextButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                if (selectedOptions.isNotEmpty()) {
                                    scope.launch {
                                        viewModel.choseAnswer(
                                            selectedOptions.toIntArray(),
                                            item.chatId,
                                            item.id
                                        )
                                        viewModel.getMessagesForChat(item.chatId)
                                    }
                                }
                            }
                        ) {
                            Text(
                                "Проголосовать"
                            )
                        }
                    } else {
                        // ничего не делаем
                    }
                }
                else -> {
                    // ничего не делаем
                }
            }
        )
    }
}