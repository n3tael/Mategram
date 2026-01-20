package com.xxcactussell.domain

data class UserPrivacySettingRuleRestrictChatMembers(
    val chatIds: LongArray
) : UserPrivacySettingRule
