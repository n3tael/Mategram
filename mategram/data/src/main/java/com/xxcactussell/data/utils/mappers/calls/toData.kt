package com.xxcactussell.data.utils.mappers.calls

import com.xxcactussell.data.utils.mappers.error.toData
import com.xxcactussell.data.utils.mappers.input.toData
import com.xxcactussell.data.utils.mappers.message.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun AcceptCall.toData(): TdApi.AcceptCall = TdApi.AcceptCall(
    this.callId,
    this.protocol.toData()
)

fun Call.toData(): TdApi.Call = TdApi.Call(
    this.id,
    this.userId,
    this.isOutgoing,
    this.isVideo,
    this.state.toData()
)

fun CallDiscardReason.toData(): TdApi.CallDiscardReason = when(this) {
    is CallDiscardReasonEmpty -> this.toData()
    is CallDiscardReasonMissed -> this.toData()
    is CallDiscardReasonDeclined -> this.toData()
    is CallDiscardReasonDisconnected -> this.toData()
    is CallDiscardReasonHungUp -> this.toData()
    is CallDiscardReasonUpgradeToGroupCall -> this.toData()
}

fun CallDiscardReasonDeclined.toData(): TdApi.CallDiscardReasonDeclined = TdApi.CallDiscardReasonDeclined(
)

fun CallDiscardReasonDisconnected.toData(): TdApi.CallDiscardReasonDisconnected = TdApi.CallDiscardReasonDisconnected(
)

fun CallDiscardReasonEmpty.toData(): TdApi.CallDiscardReasonEmpty = TdApi.CallDiscardReasonEmpty(
)

fun CallDiscardReasonHungUp.toData(): TdApi.CallDiscardReasonHungUp = TdApi.CallDiscardReasonHungUp(
)

fun CallDiscardReasonMissed.toData(): TdApi.CallDiscardReasonMissed = TdApi.CallDiscardReasonMissed(
)

fun CallDiscardReasonUpgradeToGroupCall.toData(): TdApi.CallDiscardReasonUpgradeToGroupCall = TdApi.CallDiscardReasonUpgradeToGroupCall(
    this.inviteLink
)

fun CallId.toData(): TdApi.CallId = TdApi.CallId(
    this.id
)

fun CallProblem.toData(): TdApi.CallProblem = when(this) {
    is CallProblemEcho -> this.toData()
    is CallProblemNoise -> this.toData()
    is CallProblemInterruptions -> this.toData()
    is CallProblemDistortedSpeech -> this.toData()
    is CallProblemSilentLocal -> this.toData()
    is CallProblemSilentRemote -> this.toData()
    is CallProblemDropped -> this.toData()
    is CallProblemDistortedVideo -> this.toData()
    is CallProblemPixelatedVideo -> this.toData()
}

fun CallProblemDistortedSpeech.toData(): TdApi.CallProblemDistortedSpeech = TdApi.CallProblemDistortedSpeech(
)

fun CallProblemDistortedVideo.toData(): TdApi.CallProblemDistortedVideo = TdApi.CallProblemDistortedVideo(
)

fun CallProblemDropped.toData(): TdApi.CallProblemDropped = TdApi.CallProblemDropped(
)

fun CallProblemEcho.toData(): TdApi.CallProblemEcho = TdApi.CallProblemEcho(
)

fun CallProblemInterruptions.toData(): TdApi.CallProblemInterruptions = TdApi.CallProblemInterruptions(
)

fun CallProblemNoise.toData(): TdApi.CallProblemNoise = TdApi.CallProblemNoise(
)

fun CallProblemPixelatedVideo.toData(): TdApi.CallProblemPixelatedVideo = TdApi.CallProblemPixelatedVideo(
)

fun CallProblemSilentLocal.toData(): TdApi.CallProblemSilentLocal = TdApi.CallProblemSilentLocal(
)

fun CallProblemSilentRemote.toData(): TdApi.CallProblemSilentRemote = TdApi.CallProblemSilentRemote(
)

fun CallProtocol.toData(): TdApi.CallProtocol = TdApi.CallProtocol(
    this.udpP2p,
    this.udpReflector,
    this.minLayer,
    this.maxLayer,
    this.libraryVersions.toTypedArray()
)

fun CallServer.toData(): TdApi.CallServer = TdApi.CallServer(
    this.id,
    this.ipAddress,
    this.ipv6Address,
    this.port,
    this.type.toData()
)

fun CallServerType.toData(): TdApi.CallServerType = when(this) {
    is CallServerTypeTelegramReflector -> this.toData()
    is CallServerTypeWebrtc -> this.toData()
}

fun CallServerTypeTelegramReflector.toData(): TdApi.CallServerTypeTelegramReflector = TdApi.CallServerTypeTelegramReflector(
    this.peerTag,
    this.isTcp
)

fun CallServerTypeWebrtc.toData(): TdApi.CallServerTypeWebrtc = TdApi.CallServerTypeWebrtc(
    this.username,
    this.password,
    this.supportsTurn,
    this.supportsStun
)

fun CallState.toData(): TdApi.CallState = when(this) {
    is CallStatePending -> this.toData()
    is CallStateExchangingKeys -> this.toData()
    is CallStateReady -> this.toData()
    is CallStateHangingUp -> this.toData()
    is CallStateDiscarded -> this.toData()
    is CallStateError -> this.toData()
}

fun CallStateDiscarded.toData(): TdApi.CallStateDiscarded = TdApi.CallStateDiscarded(
    this.reason.toData(),
    this.needRating,
    this.needDebugInformation,
    this.needLog
)

fun CallStateError.toData(): TdApi.CallStateError = TdApi.CallStateError(
    this.error.toData()
)

fun CallStateExchangingKeys.toData(): TdApi.CallStateExchangingKeys = TdApi.CallStateExchangingKeys(
)

fun CallStateHangingUp.toData(): TdApi.CallStateHangingUp = TdApi.CallStateHangingUp(
)

fun CallStatePending.toData(): TdApi.CallStatePending = TdApi.CallStatePending(
    this.isCreated,
    this.isReceived
)

fun CallStateReady.toData(): TdApi.CallStateReady = TdApi.CallStateReady(
    this.protocol.toData(),
    this.servers.map { it.toData() }.toTypedArray(),
    this.config,
    this.encryptionKey,
    this.emojis.toTypedArray(),
    this.allowP2p,
    this.isGroupCallSupported,
    this.customParameters
)

fun CallbackQueryAnswer.toData(): TdApi.CallbackQueryAnswer = TdApi.CallbackQueryAnswer(
    this.text,
    this.showAlert,
    this.url
)

fun CallbackQueryPayload.toData(): TdApi.CallbackQueryPayload = when(this) {
    is CallbackQueryPayloadData -> this.toData()
    is CallbackQueryPayloadDataWithPassword -> this.toData()
    is CallbackQueryPayloadGame -> this.toData()
}

fun CallbackQueryPayloadData.toData(): TdApi.CallbackQueryPayloadData = TdApi.CallbackQueryPayloadData(
    this.data
)

fun CallbackQueryPayloadDataWithPassword.toData(): TdApi.CallbackQueryPayloadDataWithPassword = TdApi.CallbackQueryPayloadDataWithPassword(
    this.password,
    this.data
)

fun CallbackQueryPayloadGame.toData(): TdApi.CallbackQueryPayloadGame = TdApi.CallbackQueryPayloadGame(
    this.gameShortName
)

fun CreateCall.toData(): TdApi.CreateCall = TdApi.CreateCall(
    this.userId,
    this.protocol.toData(),
    this.isVideo
)

fun CreateGroupCall.toData(): TdApi.CreateGroupCall = TdApi.CreateGroupCall(
    this.joinParameters.toData()
)

fun DiscardCall.toData(): TdApi.DiscardCall = TdApi.DiscardCall(
    this.callId,
    this.isDisconnected,
    this.inviteLink,
    this.duration,
    this.isVideo,
    this.connectionId
)

fun EndGroupCall.toData(): TdApi.EndGroupCall = TdApi.EndGroupCall(
    this.groupCallId
)

fun EndGroupCallRecording.toData(): TdApi.EndGroupCallRecording = TdApi.EndGroupCallRecording(
    this.groupCallId
)

fun EndGroupCallScreenSharing.toData(): TdApi.EndGroupCallScreenSharing = TdApi.EndGroupCallScreenSharing(
    this.groupCallId
)

fun GetGroupCall.toData(): TdApi.GetGroupCall = TdApi.GetGroupCall(
    this.groupCallId
)

fun GetGroupCallParticipants.toData(): TdApi.GetGroupCallParticipants = TdApi.GetGroupCallParticipants(
    this.inputGroupCall.toData(),
    this.limit
)

fun GroupCall.toData(): TdApi.GroupCall = TdApi.GroupCall(
    this.id,
    this.title,
    this.inviteLink,
    this.scheduledStartDate,
    this.enabledStartNotification,
    this.isActive,
    this.isVideoChat,
    this.isRtmpStream,
    this.isJoined,
    this.needRejoin,
    this.isOwned,
    this.canBeManaged,
    this.participantCount,
    this.hasHiddenListeners,
    this.loadedAllParticipants,
    this.recentSpeakers.map { it.toData() }.toTypedArray(),
    this.isMyVideoEnabled,
    this.isMyVideoPaused,
    this.canEnableVideo,
    this.muteNewParticipants,
    this.canToggleMuteNewParticipants,
    this.recordDuration,
    this.isVideoRecorded,
    this.duration
)

fun GroupCallDataChannel.toData(): TdApi.GroupCallDataChannel = when(this) {
    is GroupCallDataChannelMain -> this.toData()
    is GroupCallDataChannelScreenSharing -> this.toData()
}

fun GroupCallDataChannelMain.toData(): TdApi.GroupCallDataChannelMain = TdApi.GroupCallDataChannelMain(
)

fun GroupCallDataChannelScreenSharing.toData(): TdApi.GroupCallDataChannelScreenSharing = TdApi.GroupCallDataChannelScreenSharing(
)

fun GroupCallId.toData(): TdApi.GroupCallId = TdApi.GroupCallId(
    this.id
)

fun GroupCallInfo.toData(): TdApi.GroupCallInfo = TdApi.GroupCallInfo(
    this.groupCallId,
    this.joinPayload
)

fun GroupCallJoinParameters.toData(): TdApi.GroupCallJoinParameters = TdApi.GroupCallJoinParameters(
    this.audioSourceId,
    this.payload,
    this.isMuted,
    this.isMyVideoEnabled
)

fun GroupCallParticipant.toData(): TdApi.GroupCallParticipant = TdApi.GroupCallParticipant(
    this.participantId.toData(),
    this.audioSourceId,
    this.screenSharingAudioSourceId,
    this.videoInfo?.toData(),
    this.screenSharingVideoInfo?.toData(),
    this.bio,
    this.isCurrentUser,
    this.isSpeaking,
    this.isHandRaised,
    this.canBeMutedForAllUsers,
    this.canBeUnmutedForAllUsers,
    this.canBeMutedForCurrentUser,
    this.canBeUnmutedForCurrentUser,
    this.isMutedForAllUsers,
    this.isMutedForCurrentUser,
    this.canUnmuteSelf,
    this.volumeLevel,
    this.order
)

fun GroupCallParticipantVideoInfo.toData(): TdApi.GroupCallParticipantVideoInfo = TdApi.GroupCallParticipantVideoInfo(
    this.sourceGroups.map { it.toData() }.toTypedArray(),
    this.endpointId,
    this.isPaused
)

fun GroupCallParticipants.toData(): TdApi.GroupCallParticipants = TdApi.GroupCallParticipants(
    this.totalCount,
    this.participantIds.map { it.toData() }.toTypedArray()
)

fun GroupCallRecentSpeaker.toData(): TdApi.GroupCallRecentSpeaker = TdApi.GroupCallRecentSpeaker(
    this.participantId.toData(),
    this.isSpeaking
)

fun GroupCallVideoQuality.toData(): TdApi.GroupCallVideoQuality = when(this) {
    is GroupCallVideoQualityThumbnail -> this.toData()
    is GroupCallVideoQualityMedium -> this.toData()
    is GroupCallVideoQualityFull -> this.toData()
}

fun GroupCallVideoQualityFull.toData(): TdApi.GroupCallVideoQualityFull = TdApi.GroupCallVideoQualityFull(
)

fun GroupCallVideoQualityMedium.toData(): TdApi.GroupCallVideoQualityMedium = TdApi.GroupCallVideoQualityMedium(
)

fun GroupCallVideoQualityThumbnail.toData(): TdApi.GroupCallVideoQualityThumbnail = TdApi.GroupCallVideoQualityThumbnail(
)

fun GroupCallVideoSourceGroup.toData(): TdApi.GroupCallVideoSourceGroup = TdApi.GroupCallVideoSourceGroup(
    this.semantics,
    this.sourceIds
)

fun JoinGroupCall.toData(): TdApi.JoinGroupCall = TdApi.JoinGroupCall(
    this.inputGroupCall.toData(),
    this.joinParameters.toData()
)

fun LeaveGroupCall.toData(): TdApi.LeaveGroupCall = TdApi.LeaveGroupCall(
    this.groupCallId
)

fun LoadGroupCallParticipants.toData(): TdApi.LoadGroupCallParticipants = TdApi.LoadGroupCallParticipants(
    this.groupCallId,
    this.limit
)

fun SendCallDebugInformation.toData(): TdApi.SendCallDebugInformation = TdApi.SendCallDebugInformation(
    this.callId,
    this.debugInformation
)

fun SendCallRating.toData(): TdApi.SendCallRating = TdApi.SendCallRating(
    this.callId,
    this.rating,
    this.comment,
    this.problems.map { it.toData() }.toTypedArray()
)

fun SendCallSignalingData.toData(): TdApi.SendCallSignalingData = TdApi.SendCallSignalingData(
    this.callId,
    this.data
)

fun SetGroupCallParticipantVolumeLevel.toData(): TdApi.SetGroupCallParticipantVolumeLevel = TdApi.SetGroupCallParticipantVolumeLevel(
    this.groupCallId,
    this.participantId.toData(),
    this.volumeLevel
)

fun StartGroupCallRecording.toData(): TdApi.StartGroupCallRecording = TdApi.StartGroupCallRecording(
    this.groupCallId,
    this.title,
    this.recordVideo,
    this.usePortraitOrientation
)

fun StartGroupCallScreenSharing.toData(): TdApi.StartGroupCallScreenSharing = TdApi.StartGroupCallScreenSharing(
    this.groupCallId,
    this.audioSourceId,
    this.payload
)

fun ToggleGroupCallParticipantIsHandRaised.toData(): TdApi.ToggleGroupCallParticipantIsHandRaised = TdApi.ToggleGroupCallParticipantIsHandRaised(
    this.groupCallId,
    this.participantId.toData(),
    this.isHandRaised
)

fun ToggleGroupCallParticipantIsMuted.toData(): TdApi.ToggleGroupCallParticipantIsMuted = TdApi.ToggleGroupCallParticipantIsMuted(
    this.groupCallId,
    this.participantId.toData(),
    this.isMuted
)

