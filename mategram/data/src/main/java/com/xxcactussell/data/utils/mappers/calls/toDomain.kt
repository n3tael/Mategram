package com.xxcactussell.data.utils.mappers.calls

import com.xxcactussell.data.utils.mappers.error.toDomain
import com.xxcactussell.data.utils.mappers.input.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AcceptCall.toDomain(): AcceptCall = AcceptCall(
    callId = this.callId,
    protocol = this.protocol.toDomain()
)

fun TdApi.Call.toDomain(): Call = Call(
    id = this.id,
    userId = this.userId,
    isOutgoing = this.isOutgoing,
    isVideo = this.isVideo,
    state = this.state.toDomain()
)

fun TdApi.CallDiscardReason.toDomain(): CallDiscardReason = when(this) {
    is TdApi.CallDiscardReasonEmpty -> this.toDomain()
    is TdApi.CallDiscardReasonMissed -> this.toDomain()
    is TdApi.CallDiscardReasonDeclined -> this.toDomain()
    is TdApi.CallDiscardReasonDisconnected -> this.toDomain()
    is TdApi.CallDiscardReasonHungUp -> this.toDomain()
    is TdApi.CallDiscardReasonUpgradeToGroupCall -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.CallDiscardReasonDeclined.toDomain(): CallDiscardReasonDeclined = CallDiscardReasonDeclined

fun TdApi.CallDiscardReasonDisconnected.toDomain(): CallDiscardReasonDisconnected = CallDiscardReasonDisconnected

fun TdApi.CallDiscardReasonEmpty.toDomain(): CallDiscardReasonEmpty = CallDiscardReasonEmpty

fun TdApi.CallDiscardReasonHungUp.toDomain(): CallDiscardReasonHungUp = CallDiscardReasonHungUp

fun TdApi.CallDiscardReasonMissed.toDomain(): CallDiscardReasonMissed = CallDiscardReasonMissed

fun TdApi.CallDiscardReasonUpgradeToGroupCall.toDomain(): CallDiscardReasonUpgradeToGroupCall = CallDiscardReasonUpgradeToGroupCall(
    inviteLink = this.inviteLink
)

fun TdApi.CallId.toDomain(): CallId = CallId(
    id = this.id
)

fun TdApi.CallProblem.toDomain(): CallProblem = when(this) {
    is TdApi.CallProblemEcho -> this.toDomain()
    is TdApi.CallProblemNoise -> this.toDomain()
    is TdApi.CallProblemInterruptions -> this.toDomain()
    is TdApi.CallProblemDistortedSpeech -> this.toDomain()
    is TdApi.CallProblemSilentLocal -> this.toDomain()
    is TdApi.CallProblemSilentRemote -> this.toDomain()
    is TdApi.CallProblemDropped -> this.toDomain()
    is TdApi.CallProblemDistortedVideo -> this.toDomain()
    is TdApi.CallProblemPixelatedVideo -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.CallProblemDistortedSpeech.toDomain(): CallProblemDistortedSpeech = CallProblemDistortedSpeech

fun TdApi.CallProblemDistortedVideo.toDomain(): CallProblemDistortedVideo = CallProblemDistortedVideo

fun TdApi.CallProblemDropped.toDomain(): CallProblemDropped = CallProblemDropped

fun TdApi.CallProblemEcho.toDomain(): CallProblemEcho = CallProblemEcho

fun TdApi.CallProblemInterruptions.toDomain(): CallProblemInterruptions = CallProblemInterruptions

fun TdApi.CallProblemNoise.toDomain(): CallProblemNoise = CallProblemNoise

fun TdApi.CallProblemPixelatedVideo.toDomain(): CallProblemPixelatedVideo = CallProblemPixelatedVideo

fun TdApi.CallProblemSilentLocal.toDomain(): CallProblemSilentLocal = CallProblemSilentLocal

fun TdApi.CallProblemSilentRemote.toDomain(): CallProblemSilentRemote = CallProblemSilentRemote

fun TdApi.CallProtocol.toDomain(): CallProtocol = CallProtocol(
    udpP2p = this.udpP2p,
    udpReflector = this.udpReflector,
    minLayer = this.minLayer,
    maxLayer = this.maxLayer,
    libraryVersions = this.libraryVersions.toList()
)

fun TdApi.CallServer.toDomain(): CallServer = CallServer(
    id = this.id,
    ipAddress = this.ipAddress,
    ipv6Address = this.ipv6Address,
    port = this.port,
    type = this.type.toDomain()
)

fun TdApi.CallServerType.toDomain(): CallServerType = when(this) {
    is TdApi.CallServerTypeTelegramReflector -> this.toDomain()
    is TdApi.CallServerTypeWebrtc -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.CallServerTypeTelegramReflector.toDomain(): CallServerTypeTelegramReflector = CallServerTypeTelegramReflector(
    peerTag = this.peerTag,
    isTcp = this.isTcp
)

fun TdApi.CallServerTypeWebrtc.toDomain(): CallServerTypeWebrtc = CallServerTypeWebrtc(
    username = this.username,
    password = this.password,
    supportsTurn = this.supportsTurn,
    supportsStun = this.supportsStun
)

fun TdApi.CallState.toDomain(): CallState = when(this) {
    is TdApi.CallStatePending -> this.toDomain()
    is TdApi.CallStateExchangingKeys -> this.toDomain()
    is TdApi.CallStateReady -> this.toDomain()
    is TdApi.CallStateHangingUp -> this.toDomain()
    is TdApi.CallStateDiscarded -> this.toDomain()
    is TdApi.CallStateError -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.CallStateDiscarded.toDomain(): CallStateDiscarded = CallStateDiscarded(
    reason = this.reason.toDomain(),
    needRating = this.needRating,
    needDebugInformation = this.needDebugInformation,
    needLog = this.needLog
)

fun TdApi.CallStateError.toDomain(): CallStateError = CallStateError(
    error = this.error.toDomain()
)

fun TdApi.CallStateExchangingKeys.toDomain(): CallStateExchangingKeys = CallStateExchangingKeys

fun TdApi.CallStateHangingUp.toDomain(): CallStateHangingUp = CallStateHangingUp

fun TdApi.CallStatePending.toDomain(): CallStatePending = CallStatePending(
    isCreated = this.isCreated,
    isReceived = this.isReceived
)

fun TdApi.CallStateReady.toDomain(): CallStateReady = CallStateReady(
    protocol = this.protocol.toDomain(),
    servers = this.servers.map { it.toDomain() },
    config = this.config,
    encryptionKey = this.encryptionKey,
    emojis = this.emojis.toList(),
    allowP2p = this.allowP2p,
    isGroupCallSupported = this.isGroupCallSupported,
    customParameters = this.customParameters
)

fun TdApi.CallbackQueryAnswer.toDomain(): CallbackQueryAnswer = CallbackQueryAnswer(
    text = this.text,
    showAlert = this.showAlert,
    url = this.url
)

fun TdApi.CallbackQueryPayload.toDomain(): CallbackQueryPayload = when(this) {
    is TdApi.CallbackQueryPayloadData -> this.toDomain()
    is TdApi.CallbackQueryPayloadDataWithPassword -> this.toDomain()
    is TdApi.CallbackQueryPayloadGame -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.CallbackQueryPayloadData.toDomain(): CallbackQueryPayloadData = CallbackQueryPayloadData(
    data = this.data
)

fun TdApi.CallbackQueryPayloadDataWithPassword.toDomain(): CallbackQueryPayloadDataWithPassword = CallbackQueryPayloadDataWithPassword(
    password = this.password,
    data = this.data
)

fun TdApi.CallbackQueryPayloadGame.toDomain(): CallbackQueryPayloadGame = CallbackQueryPayloadGame(
    gameShortName = this.gameShortName
)

fun TdApi.CreateCall.toDomain(): CreateCall = CreateCall(
    userId = this.userId,
    protocol = this.protocol.toDomain(),
    isVideo = this.isVideo
)

fun TdApi.CreateGroupCall.toDomain(): CreateGroupCall = CreateGroupCall(
    joinParameters = this.joinParameters.toDomain()
)

fun TdApi.DiscardCall.toDomain(): DiscardCall = DiscardCall(
    callId = this.callId,
    isDisconnected = this.isDisconnected,
    inviteLink = this.inviteLink,
    duration = this.duration,
    isVideo = this.isVideo,
    connectionId = this.connectionId
)

fun TdApi.EndGroupCall.toDomain(): EndGroupCall = EndGroupCall(
    groupCallId = this.groupCallId
)

fun TdApi.EndGroupCallRecording.toDomain(): EndGroupCallRecording = EndGroupCallRecording(
    groupCallId = this.groupCallId
)

fun TdApi.EndGroupCallScreenSharing.toDomain(): EndGroupCallScreenSharing = EndGroupCallScreenSharing(
    groupCallId = this.groupCallId
)

fun TdApi.GetGroupCall.toDomain(): GetGroupCall = GetGroupCall(
    groupCallId = this.groupCallId
)

fun TdApi.GetGroupCallParticipants.toDomain(): GetGroupCallParticipants = GetGroupCallParticipants(
    inputGroupCall = this.inputGroupCall.toDomain(),
    limit = this.limit
)

fun TdApi.GroupCall.toDomain(): GroupCall = GroupCall(
    id = this.id,
    title = this.title,
    inviteLink = this.inviteLink,
    scheduledStartDate = this.scheduledStartDate,
    enabledStartNotification = this.enabledStartNotification,
    isActive = this.isActive,
    isVideoChat = this.isVideoChat,
    isRtmpStream = this.isRtmpStream,
    isJoined = this.isJoined,
    needRejoin = this.needRejoin,
    isOwned = this.isOwned,
    canBeManaged = this.canBeManaged,
    participantCount = this.participantCount,
    hasHiddenListeners = this.hasHiddenListeners,
    loadedAllParticipants = this.loadedAllParticipants,
    recentSpeakers = this.recentSpeakers.map { it.toDomain() },
    isMyVideoEnabled = this.isMyVideoEnabled,
    isMyVideoPaused = this.isMyVideoPaused,
    canEnableVideo = this.canEnableVideo,
    muteNewParticipants = this.muteNewParticipants,
    canToggleMuteNewParticipants = this.canToggleMuteNewParticipants,
    recordDuration = this.recordDuration,
    isVideoRecorded = this.isVideoRecorded,
    duration = this.duration
)

fun TdApi.GroupCallDataChannel.toDomain(): GroupCallDataChannel = when(this) {
    is TdApi.GroupCallDataChannelMain -> this.toDomain()
    is TdApi.GroupCallDataChannelScreenSharing -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.GroupCallDataChannelMain.toDomain(): GroupCallDataChannelMain = GroupCallDataChannelMain

fun TdApi.GroupCallDataChannelScreenSharing.toDomain(): GroupCallDataChannelScreenSharing = GroupCallDataChannelScreenSharing

fun TdApi.GroupCallId.toDomain(): GroupCallId = GroupCallId(
    id = this.id
)

fun TdApi.GroupCallInfo.toDomain(): GroupCallInfo = GroupCallInfo(
    groupCallId = this.groupCallId,
    joinPayload = this.joinPayload
)

fun TdApi.GroupCallJoinParameters.toDomain(): GroupCallJoinParameters = GroupCallJoinParameters(
    audioSourceId = this.audioSourceId,
    payload = this.payload,
    isMuted = this.isMuted,
    isMyVideoEnabled = this.isMyVideoEnabled
)

fun TdApi.GroupCallParticipant.toDomain(): GroupCallParticipant = GroupCallParticipant(
    participantId = this.participantId.toDomain(),
    audioSourceId = this.audioSourceId,
    screenSharingAudioSourceId = this.screenSharingAudioSourceId,
    videoInfo = this.videoInfo?.toDomain(),
    screenSharingVideoInfo = this.screenSharingVideoInfo?.toDomain(),
    bio = this.bio,
    isCurrentUser = this.isCurrentUser,
    isSpeaking = this.isSpeaking,
    isHandRaised = this.isHandRaised,
    canBeMutedForAllUsers = this.canBeMutedForAllUsers,
    canBeUnmutedForAllUsers = this.canBeUnmutedForAllUsers,
    canBeMutedForCurrentUser = this.canBeMutedForCurrentUser,
    canBeUnmutedForCurrentUser = this.canBeUnmutedForCurrentUser,
    isMutedForAllUsers = this.isMutedForAllUsers,
    isMutedForCurrentUser = this.isMutedForCurrentUser,
    canUnmuteSelf = this.canUnmuteSelf,
    volumeLevel = this.volumeLevel,
    order = this.order
)

fun TdApi.GroupCallParticipantVideoInfo.toDomain(): GroupCallParticipantVideoInfo = GroupCallParticipantVideoInfo(
    sourceGroups = this.sourceGroups.map { it.toDomain() },
    endpointId = this.endpointId,
    isPaused = this.isPaused
)

fun TdApi.GroupCallParticipants.toDomain(): GroupCallParticipants = GroupCallParticipants(
    totalCount = this.totalCount,
    participantIds = this.participantIds.map { it.toDomain() }
)

fun TdApi.GroupCallRecentSpeaker.toDomain(): GroupCallRecentSpeaker = GroupCallRecentSpeaker(
    participantId = this.participantId.toDomain(),
    isSpeaking = this.isSpeaking
)

fun TdApi.GroupCallVideoQuality.toDomain(): GroupCallVideoQuality = when(this) {
    is TdApi.GroupCallVideoQualityThumbnail -> this.toDomain()
    is TdApi.GroupCallVideoQualityMedium -> this.toDomain()
    is TdApi.GroupCallVideoQualityFull -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.GroupCallVideoQualityFull.toDomain(): GroupCallVideoQualityFull = GroupCallVideoQualityFull

fun TdApi.GroupCallVideoQualityMedium.toDomain(): GroupCallVideoQualityMedium = GroupCallVideoQualityMedium

fun TdApi.GroupCallVideoQualityThumbnail.toDomain(): GroupCallVideoQualityThumbnail = GroupCallVideoQualityThumbnail

fun TdApi.GroupCallVideoSourceGroup.toDomain(): GroupCallVideoSourceGroup = GroupCallVideoSourceGroup(
    semantics = this.semantics,
    sourceIds = this.sourceIds
)

fun TdApi.JoinGroupCall.toDomain(): JoinGroupCall = JoinGroupCall(
    inputGroupCall = this.inputGroupCall.toDomain(),
    joinParameters = this.joinParameters.toDomain()
)

fun TdApi.LeaveGroupCall.toDomain(): LeaveGroupCall = LeaveGroupCall(
    groupCallId = this.groupCallId
)

fun TdApi.LoadGroupCallParticipants.toDomain(): LoadGroupCallParticipants = LoadGroupCallParticipants(
    groupCallId = this.groupCallId,
    limit = this.limit
)

fun TdApi.SendCallDebugInformation.toDomain(): SendCallDebugInformation = SendCallDebugInformation(
    callId = this.callId,
    debugInformation = this.debugInformation
)

fun TdApi.SendCallRating.toDomain(): SendCallRating = SendCallRating(
    callId = this.callId,
    rating = this.rating,
    comment = this.comment,
    problems = this.problems.map { it.toDomain() }
)

fun TdApi.SendCallSignalingData.toDomain(): SendCallSignalingData = SendCallSignalingData(
    callId = this.callId,
    data = this.data
)

fun TdApi.SetGroupCallParticipantVolumeLevel.toDomain(): SetGroupCallParticipantVolumeLevel = SetGroupCallParticipantVolumeLevel(
    groupCallId = this.groupCallId,
    participantId = this.participantId.toDomain(),
    volumeLevel = this.volumeLevel
)

fun TdApi.StartGroupCallRecording.toDomain(): StartGroupCallRecording = StartGroupCallRecording(
    groupCallId = this.groupCallId,
    title = this.title,
    recordVideo = this.recordVideo,
    usePortraitOrientation = this.usePortraitOrientation
)

fun TdApi.StartGroupCallScreenSharing.toDomain(): StartGroupCallScreenSharing = StartGroupCallScreenSharing(
    groupCallId = this.groupCallId,
    audioSourceId = this.audioSourceId,
    payload = this.payload
)

fun TdApi.ToggleGroupCallParticipantIsHandRaised.toDomain(): ToggleGroupCallParticipantIsHandRaised = ToggleGroupCallParticipantIsHandRaised(
    groupCallId = this.groupCallId,
    participantId = this.participantId.toDomain(),
    isHandRaised = this.isHandRaised
)

fun TdApi.ToggleGroupCallParticipantIsMuted.toDomain(): ToggleGroupCallParticipantIsMuted = ToggleGroupCallParticipantIsMuted(
    groupCallId = this.groupCallId,
    participantId = this.participantId.toDomain(),
    isMuted = this.isMuted
)

