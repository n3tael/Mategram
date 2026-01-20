package com.xxcactussell.domain

data class UserPrivacySettingRuleAllowUsers(
    val userIds: LongArray
) : UserPrivacySettingRule
