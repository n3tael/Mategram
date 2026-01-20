package com.xxcactussell.domain

data class UpdateUserPrivacySettingRules(
    val setting: UserPrivacySetting,
    val rules: UserPrivacySettingRules
) : Update
