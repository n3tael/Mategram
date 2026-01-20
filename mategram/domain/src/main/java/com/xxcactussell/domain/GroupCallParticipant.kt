package com.xxcactussell.domain

data class GroupCallParticipant(
    val participantId: MessageSender,
    val audioSourceId: Int,
    val screenSharingAudioSourceId: Int,
    val videoInfo: GroupCallParticipantVideoInfo? = null,
    val screenSharingVideoInfo: GroupCallParticipantVideoInfo? = null,
    val bio: String,
    val isCurrentUser: Boolean,
    val isSpeaking: Boolean,
    val isHandRaised: Boolean,
    val canBeMutedForAllUsers: Boolean,
    val canBeUnmutedForAllUsers: Boolean,
    val canBeMutedForCurrentUser: Boolean,
    val canBeUnmutedForCurrentUser: Boolean,
    val isMutedForAllUsers: Boolean,
    val isMutedForCurrentUser: Boolean,
    val canUnmuteSelf: Boolean,
    val volumeLevel: Int,
    val order: String
) : Object
