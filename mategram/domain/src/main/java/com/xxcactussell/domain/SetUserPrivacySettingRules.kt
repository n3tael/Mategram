package com.xxcactussell.domain

data class SetUserPrivacySettingRules(
    val setting: UserPrivacySetting,
    val rules: UserPrivacySettingRules
) : Function
