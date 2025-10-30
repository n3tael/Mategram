package com.xxcactussell.presentation.messages.model

import android.net.Uri

interface AttachmentEvent {
    object ChatGallery : AttachmentEvent
    object ChatDocument : AttachmentEvent
    object Poll : AttachmentEvent
    object Todo : AttachmentEvent
    object AttachMusic : AttachmentEvent
    object AttachContact : AttachmentEvent
    object ChatLocation : AttachmentEvent
}