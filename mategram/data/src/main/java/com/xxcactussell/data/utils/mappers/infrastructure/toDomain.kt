package com.xxcactussell.data.utils.mappers.infrastructure

import com.xxcactussell.data.utils.mappers.file.toDomain
import com.xxcactussell.data.utils.mappers.log.toDomain
import com.xxcactussell.data.utils.mappers.network.toDomain
import com.xxcactussell.data.utils.mappers.option.toDomain
import com.xxcactussell.data.utils.mappers.proxy.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AddProxy.toDomain(): AddProxy = AddProxy(
    server = this.server,
    port = this.port,
    enable = this.enable,
    type = this.type.toDomain()
)

fun TdApi.DisableProxy.toDomain(): DisableProxy = DisableProxy

fun TdApi.EnableProxy.toDomain(): EnableProxy = EnableProxy(
    proxyId = this.proxyId
)

fun TdApi.GetLogStream.toDomain(): GetLogStream = GetLogStream

fun TdApi.GetLogTags.toDomain(): GetLogTags = GetLogTags

fun TdApi.GetLogVerbosityLevel.toDomain(): GetLogVerbosityLevel = GetLogVerbosityLevel

fun TdApi.GetNetworkStatistics.toDomain(): GetNetworkStatistics = GetNetworkStatistics(
    onlyCurrent = this.onlyCurrent
)

fun TdApi.GetOption.toDomain(): GetOption = GetOption(
    name = this.name
)

fun TdApi.GetProxies.toDomain(): GetProxies = GetProxies

fun TdApi.GetProxyLink.toDomain(): GetProxyLink = GetProxyLink(
    proxyId = this.proxyId
)

fun TdApi.NetworkStatistics.toDomain(): NetworkStatistics = NetworkStatistics(
    sinceDate = this.sinceDate,
    entries = this.entries.map { it.toDomain() }
)

fun TdApi.NetworkStatisticsEntry.toDomain(): NetworkStatisticsEntry = when(this) {
    is TdApi.NetworkStatisticsEntryFile -> this.toDomain()
    is TdApi.NetworkStatisticsEntryCall -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.NetworkStatisticsEntryCall.toDomain(): NetworkStatisticsEntryCall = NetworkStatisticsEntryCall(
    networkType = this.networkType.toDomain(),
    sentBytes = this.sentBytes,
    receivedBytes = this.receivedBytes,
    duration = this.duration
)

fun TdApi.NetworkStatisticsEntryFile.toDomain(): NetworkStatisticsEntryFile = NetworkStatisticsEntryFile(
    fileType = this.fileType.toDomain(),
    networkType = this.networkType.toDomain(),
    sentBytes = this.sentBytes,
    receivedBytes = this.receivedBytes
)

fun TdApi.PingProxy.toDomain(): PingProxy = PingProxy(
    proxyId = this.proxyId
)

fun TdApi.RemoveProxy.toDomain(): RemoveProxy = RemoveProxy(
    proxyId = this.proxyId
)

fun TdApi.ResetNetworkStatistics.toDomain(): ResetNetworkStatistics = ResetNetworkStatistics

fun TdApi.SetLogStream.toDomain(): SetLogStream = SetLogStream(
    logStream = this.logStream.toDomain()
)

fun TdApi.SetLogTagVerbosityLevel.toDomain(): SetLogTagVerbosityLevel = SetLogTagVerbosityLevel(
    tag = this.tag,
    newVerbosityLevel = this.newVerbosityLevel
)

fun TdApi.SetLogVerbosityLevel.toDomain(): SetLogVerbosityLevel = SetLogVerbosityLevel(
    newVerbosityLevel = this.newVerbosityLevel
)

fun TdApi.SetOption.toDomain(): SetOption = SetOption(
    name = this.name,
    value = this.value.toDomain()
)

fun TdApi.SetTdlibParameters.toDomain(): SetTdlibParameters = SetTdlibParameters(
    useTestDc = this.useTestDc,
    databaseDirectory = this.databaseDirectory,
    filesDirectory = this.filesDirectory,
    databaseEncryptionKey = this.databaseEncryptionKey,
    useFileDatabase = this.useFileDatabase,
    useChatInfoDatabase = this.useChatInfoDatabase,
    useMessageDatabase = this.useMessageDatabase,
    useSecretChats = this.useSecretChats,
    apiId = this.apiId,
    apiHash = this.apiHash,
    systemLanguageCode = this.systemLanguageCode,
    deviceModel = this.deviceModel,
    systemVersion = this.systemVersion,
    applicationVersion = this.applicationVersion
)

