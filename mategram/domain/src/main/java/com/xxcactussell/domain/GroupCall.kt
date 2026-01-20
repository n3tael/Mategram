package com.xxcactussell.domain

data class GroupCall(
    val id: Int,
    val title: String,
    val inviteLink: String,
    val scheduledStartDate: Int,
    val enabledStartNotification: Boolean,
    val isActive: Boolean,
    val isVideoChat: Boolean,
    val isRtmpStream: Boolean,
    val isJoined: Boolean,
    val needRejoin: Boolean,
    val isOwned: Boolean,
    val canBeManaged: Boolean,
    val participantCount: Int,
    val hasHiddenListeners: Boolean,
    val loadedAllParticipants: Boolean,
    val recentSpeakers: List<GroupCallRecentSpeaker>,
    val isMyVideoEnabled: Boolean,
    val isMyVideoPaused: Boolean,
    val canEnableVideo: Boolean,
    val muteNewParticipants: Boolean,
    val canToggleMuteNewParticipants: Boolean,
    val recordDuration: Int,
    val isVideoRecorded: Boolean,
    val duration: Int
) : Object
