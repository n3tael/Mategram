package com.xxcactussell.presentation.tools

import com.xxcactussell.domain.messages.model.FormattedText
import com.xxcactussell.domain.messages.model.Message
import com.xxcactussell.domain.messages.model.MessageBasicGroupChatCreate
import com.xxcactussell.domain.messages.model.MessageContent
import com.xxcactussell.presentation.localization.localizedString

fun MessageContent.getServiceMessageText() : FormattedText {
    return when (this) {
        is MessageBasicGroupChatCreate -> FormattedText(localizedString("ActionCreateMega"))
        else -> FormattedText("${this.javaClass.simpleName}")
    }
}