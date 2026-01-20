package com.xxcactussell.domain

data class StorageStatisticsFast(
    val filesSize: Long,
    val fileCount: Int,
    val databaseSize: Long,
    val languagePackDatabaseSize: Long,
    val logSize: Long
) : Object
