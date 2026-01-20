package com.xxcactussell.domain

data class UserPrivacySettingRuleRestrictUsers(
    val userIds: LongArray
) : UserPrivacySettingRule
