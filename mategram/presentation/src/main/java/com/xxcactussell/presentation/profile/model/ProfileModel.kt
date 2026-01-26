package com.xxcactussell.presentation.profile.model

import com.xxcactussell.domain.BasicGroupFullInfo
import com.xxcactussell.domain.SupergroupFullInfo
import com.xxcactussell.domain.UserFullInfo

interface ProfileModel {
    data class UserProfileModel(
        val userFullInfo: UserFullInfo
    ) : ProfileModel

    data class BasicGroupModel(
        val basicGroupFullInfo: BasicGroupFullInfo
    ) : ProfileModel

    data class SupergroupModel(
        val supergroupFullInfo: SupergroupFullInfo
    ) : ProfileModel
}

data class ProfileUiState(
    val profileModel: ProfileModel? = null
)