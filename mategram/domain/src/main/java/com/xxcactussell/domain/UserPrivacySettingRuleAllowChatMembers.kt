package com.xxcactussell.domain

data class UserPrivacySettingRuleAllowChatMembers(
    val chatIds: LongArray
) : UserPrivacySettingRule
