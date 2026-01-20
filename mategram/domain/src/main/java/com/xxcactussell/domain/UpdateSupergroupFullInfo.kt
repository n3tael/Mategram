package com.xxcactussell.domain

data class UpdateSupergroupFullInfo(
    val supergroupId: Long,
    val supergroupFullInfo: SupergroupFullInfo
) : Update
