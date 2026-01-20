package com.xxcactussell.domain

data class UpdateBasicGroupFullInfo(
    val basicGroupId: Long,
    val basicGroupFullInfo: BasicGroupFullInfo
) : Update
