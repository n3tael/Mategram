package com.xxcactussell.presentation.tools

import com.xxcactussell.domain.FormattedText
import com.xxcactussell.domain.MessageBasicGroupChatCreate
import com.xxcactussell.domain.MessageContent
import com.xxcactussell.presentation.localization.localizedString

fun MessageContent.getServiceMessageText() : FormattedText {
    return when (this) {
        is MessageBasicGroupChatCreate -> FormattedText(localizedString("ActionCreateMega"))
        else -> FormattedText("${this.javaClass.simpleName}")
    }
}