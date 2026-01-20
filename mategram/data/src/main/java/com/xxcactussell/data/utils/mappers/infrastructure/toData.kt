package com.xxcactussell.data.utils.mappers.infrastructure

import com.xxcactussell.data.utils.mappers.file.toData
import com.xxcactussell.data.utils.mappers.log.toData
import com.xxcactussell.data.utils.mappers.network.toData
import com.xxcactussell.data.utils.mappers.option.toData
import com.xxcactussell.data.utils.mappers.proxy.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun AddProxy.toData(): TdApi.AddProxy = TdApi.AddProxy(
    this.server,
    this.port,
    this.enable,
    this.type.toData()
)

fun DisableProxy.toData(): TdApi.DisableProxy = TdApi.DisableProxy(
)

fun EnableProxy.toData(): TdApi.EnableProxy = TdApi.EnableProxy(
    this.proxyId
)

fun GetLogStream.toData(): TdApi.GetLogStream = TdApi.GetLogStream(
)

fun GetLogTags.toData(): TdApi.GetLogTags = TdApi.GetLogTags(
)

fun GetLogVerbosityLevel.toData(): TdApi.GetLogVerbosityLevel = TdApi.GetLogVerbosityLevel(
)

fun GetNetworkStatistics.toData(): TdApi.GetNetworkStatistics = TdApi.GetNetworkStatistics(
    this.onlyCurrent
)

fun GetOption.toData(): TdApi.GetOption = TdApi.GetOption(
    this.name
)

fun GetProxies.toData(): TdApi.GetProxies = TdApi.GetProxies(
)

fun GetProxyLink.toData(): TdApi.GetProxyLink = TdApi.GetProxyLink(
    this.proxyId
)

fun NetworkStatistics.toData(): TdApi.NetworkStatistics = TdApi.NetworkStatistics(
    this.sinceDate,
    this.entries.map { it.toData() }.toTypedArray()
)

fun NetworkStatisticsEntry.toData(): TdApi.NetworkStatisticsEntry = when(this) {
    is NetworkStatisticsEntryFile -> this.toData()
    is NetworkStatisticsEntryCall -> this.toData()
}

fun NetworkStatisticsEntryCall.toData(): TdApi.NetworkStatisticsEntryCall = TdApi.NetworkStatisticsEntryCall(
    this.networkType.toData(),
    this.sentBytes,
    this.receivedBytes,
    this.duration
)

fun NetworkStatisticsEntryFile.toData(): TdApi.NetworkStatisticsEntryFile = TdApi.NetworkStatisticsEntryFile(
    this.fileType.toData(),
    this.networkType.toData(),
    this.sentBytes,
    this.receivedBytes
)

fun PingProxy.toData(): TdApi.PingProxy = TdApi.PingProxy(
    this.proxyId
)

fun RemoveProxy.toData(): TdApi.RemoveProxy = TdApi.RemoveProxy(
    this.proxyId
)

fun ResetNetworkStatistics.toData(): TdApi.ResetNetworkStatistics = TdApi.ResetNetworkStatistics(
)

fun SetLogStream.toData(): TdApi.SetLogStream = TdApi.SetLogStream(
    this.logStream.toData()
)

fun SetLogTagVerbosityLevel.toData(): TdApi.SetLogTagVerbosityLevel = TdApi.SetLogTagVerbosityLevel(
    this.tag,
    this.newVerbosityLevel
)

fun SetLogVerbosityLevel.toData(): TdApi.SetLogVerbosityLevel = TdApi.SetLogVerbosityLevel(
    this.newVerbosityLevel
)

fun SetOption.toData(): TdApi.SetOption = TdApi.SetOption(
    this.name,
    this.value.toData()
)

fun SetTdlibParameters.toData(): TdApi.SetTdlibParameters = TdApi.SetTdlibParameters(
    this.useTestDc,
    this.databaseDirectory,
    this.filesDirectory,
    this.databaseEncryptionKey,
    this.useFileDatabase,
    this.useChatInfoDatabase,
    this.useMessageDatabase,
    this.useSecretChats,
    this.apiId,
    this.apiHash,
    this.systemLanguageCode,
    this.deviceModel,
    this.systemVersion,
    this.applicationVersion
)

