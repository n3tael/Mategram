package com.xxcactussell.domain

data class UpdateAvailableMessageEffects(
    val reactionEffectIds: LongArray,
    val stickerEffectIds: LongArray
) : Update
