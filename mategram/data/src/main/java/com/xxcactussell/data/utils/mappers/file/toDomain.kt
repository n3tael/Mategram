package com.xxcactussell.data.utils.mappers.file

import com.xxcactussell.data.utils.mappers.advertisement.toDomain
import com.xxcactussell.data.utils.mappers.error.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.minithumbnail.toDomain
import com.xxcactussell.data.utils.mappers.speech.toDomain
import com.xxcactussell.data.utils.mappers.sticker.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AnimatedEmoji.toDomain(): AnimatedEmoji = AnimatedEmoji(
    sticker = this.sticker?.toDomain(),
    stickerWidth = this.stickerWidth,
    stickerHeight = this.stickerHeight,
    fitzpatrickType = this.fitzpatrickType,
    sound = this.sound?.toDomain()
)

fun TdApi.Audio.toDomain(): Audio = Audio(
    duration = this.duration,
    title = this.title,
    performer = this.performer,
    fileName = this.fileName,
    mimeType = this.mimeType,
    albumCoverMinithumbnail = this.albumCoverMinithumbnail?.toDomain(),
    albumCoverThumbnail = this.albumCoverThumbnail?.toDomain(),
    externalAlbumCovers = this.externalAlbumCovers.map { it.toDomain() },
    audio = this.audio.toDomain()
)

fun TdApi.Audios.toDomain(): Audios = Audios(
    totalCount = this.totalCount,
    audios = this.audios.map { it.toDomain() }
)

fun TdApi.CancelDownloadFile.toDomain(): CancelDownloadFile = CancelDownloadFile(
    fileId = this.fileId,
    onlyIfPending = this.onlyIfPending
)

fun TdApi.DeleteFile.toDomain(): DeleteFile = DeleteFile(
    fileId = this.fileId
)

fun TdApi.Document.toDomain(): Document = Document(
    fileName = this.fileName,
    mimeType = this.mimeType,
    minithumbnail = this.minithumbnail?.toDomain(),
    thumbnail = this.thumbnail?.toDomain(),
    document = this.document.toDomain()
)

fun TdApi.DownloadFile.toDomain(): DownloadFile = DownloadFile(
    fileId = this.fileId,
    priority = this.priority,
    offset = this.offset,
    limit = this.limit,
    synchronous = this.synchronous
)

fun TdApi.File.toDomain(): File = File(
    id = this.id,
    size = this.size,
    expectedSize = this.expectedSize,
    local = this.local.toDomain(),
    remote = this.remote.toDomain()
)

fun TdApi.FileDownload.toDomain(): FileDownload = FileDownload(
    fileId = this.fileId,
    message = this.message.toDomain(),
    addDate = this.addDate,
    completeDate = this.completeDate,
    isPaused = this.isPaused
)

fun TdApi.FileDownloadedPrefixSize.toDomain(): FileDownloadedPrefixSize = FileDownloadedPrefixSize(
    size = this.size
)

fun TdApi.FileType.toDomain(): FileType = when(this) {
    is TdApi.FileTypeNone -> this.toDomain()
    is TdApi.FileTypeAnimation -> this.toDomain()
    is TdApi.FileTypeAudio -> this.toDomain()
    is TdApi.FileTypeDocument -> this.toDomain()
    is TdApi.FileTypeNotificationSound -> this.toDomain()
    is TdApi.FileTypePhoto -> this.toDomain()
    is TdApi.FileTypePhotoStory -> this.toDomain()
    is TdApi.FileTypeProfilePhoto -> this.toDomain()
    is TdApi.FileTypeSecret -> this.toDomain()
    is TdApi.FileTypeSecretThumbnail -> this.toDomain()
    is TdApi.FileTypeSecure -> this.toDomain()
    is TdApi.FileTypeSelfDestructingPhoto -> this.toDomain()
    is TdApi.FileTypeSelfDestructingVideo -> this.toDomain()
    is TdApi.FileTypeSelfDestructingVideoNote -> this.toDomain()
    is TdApi.FileTypeSelfDestructingVoiceNote -> this.toDomain()
    is TdApi.FileTypeSticker -> this.toDomain()
    is TdApi.FileTypeThumbnail -> this.toDomain()
    is TdApi.FileTypeUnknown -> this.toDomain()
    is TdApi.FileTypeVideo -> this.toDomain()
    is TdApi.FileTypeVideoNote -> this.toDomain()
    is TdApi.FileTypeVideoStory -> this.toDomain()
    is TdApi.FileTypeVoiceNote -> this.toDomain()
    is TdApi.FileTypeWallpaper -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.FileTypeAnimation.toDomain(): FileTypeAnimation = FileTypeAnimation

fun TdApi.FileTypeAudio.toDomain(): FileTypeAudio = FileTypeAudio

fun TdApi.FileTypeDocument.toDomain(): FileTypeDocument = FileTypeDocument

fun TdApi.FileTypeNone.toDomain(): FileTypeNone = FileTypeNone

fun TdApi.FileTypeNotificationSound.toDomain(): FileTypeNotificationSound = FileTypeNotificationSound

fun TdApi.FileTypePhoto.toDomain(): FileTypePhoto = FileTypePhoto

fun TdApi.FileTypePhotoStory.toDomain(): FileTypePhotoStory = FileTypePhotoStory

fun TdApi.FileTypeProfilePhoto.toDomain(): FileTypeProfilePhoto = FileTypeProfilePhoto

fun TdApi.FileTypeSecret.toDomain(): FileTypeSecret = FileTypeSecret

fun TdApi.FileTypeSecretThumbnail.toDomain(): FileTypeSecretThumbnail = FileTypeSecretThumbnail

fun TdApi.FileTypeSecure.toDomain(): FileTypeSecure = FileTypeSecure

fun TdApi.FileTypeSelfDestructingPhoto.toDomain(): FileTypeSelfDestructingPhoto = FileTypeSelfDestructingPhoto

fun TdApi.FileTypeSelfDestructingVideo.toDomain(): FileTypeSelfDestructingVideo = FileTypeSelfDestructingVideo

fun TdApi.FileTypeSelfDestructingVideoNote.toDomain(): FileTypeSelfDestructingVideoNote = FileTypeSelfDestructingVideoNote

fun TdApi.FileTypeSelfDestructingVoiceNote.toDomain(): FileTypeSelfDestructingVoiceNote = FileTypeSelfDestructingVoiceNote

fun TdApi.FileTypeSticker.toDomain(): FileTypeSticker = FileTypeSticker

fun TdApi.FileTypeThumbnail.toDomain(): FileTypeThumbnail = FileTypeThumbnail

fun TdApi.FileTypeUnknown.toDomain(): FileTypeUnknown = FileTypeUnknown

fun TdApi.FileTypeVideo.toDomain(): FileTypeVideo = FileTypeVideo

fun TdApi.FileTypeVideoNote.toDomain(): FileTypeVideoNote = FileTypeVideoNote

fun TdApi.FileTypeVideoStory.toDomain(): FileTypeVideoStory = FileTypeVideoStory

fun TdApi.FileTypeVoiceNote.toDomain(): FileTypeVoiceNote = FileTypeVoiceNote

fun TdApi.FileTypeWallpaper.toDomain(): FileTypeWallpaper = FileTypeWallpaper

fun TdApi.FinishFileGeneration.toDomain(): FinishFileGeneration = FinishFileGeneration(
    generationId = this.generationId,
    error = this.error.toDomain()
)

fun TdApi.GetFile.toDomain(): GetFile = GetFile(
    fileId = this.fileId
)

fun TdApi.GetFileDownloadedPrefixSize.toDomain(): GetFileDownloadedPrefixSize = GetFileDownloadedPrefixSize(
    fileId = this.fileId,
    offset = this.offset
)

fun TdApi.GetFileExtension.toDomain(): GetFileExtension = GetFileExtension(
    mimeType = this.mimeType
)

fun TdApi.GetFileMimeType.toDomain(): GetFileMimeType = GetFileMimeType(
    fileName = this.fileName
)

fun TdApi.GetRemoteFile.toDomain(): GetRemoteFile = GetRemoteFile(
    remoteFileId = this.remoteFileId,
    fileType = this.fileType.toDomain()
)

fun TdApi.LocalFile.toDomain(): LocalFile = LocalFile(
    path = this.path,
    canBeDownloaded = this.canBeDownloaded,
    canBeDeleted = this.canBeDeleted,
    isDownloadingActive = this.isDownloadingActive,
    isDownloadingCompleted = this.isDownloadingCompleted,
    downloadOffset = this.downloadOffset,
    downloadedPrefixSize = this.downloadedPrefixSize,
    downloadedSize = this.downloadedSize
)

fun TdApi.PhotoSize.toDomain(): PhotoSize = PhotoSize(
    type = this.type,
    photo = this.photo.toDomain(),
    width = this.width,
    height = this.height,
    progressiveSizes = this.progressiveSizes
)

fun TdApi.RemoteFile.toDomain(): RemoteFile = RemoteFile(
    id = this.id,
    uniqueId = this.uniqueId,
    isUploadingActive = this.isUploadingActive,
    isUploadingCompleted = this.isUploadingCompleted,
    uploadedSize = this.uploadedSize
)

fun TdApi.SetFileGenerationProgress.toDomain(): SetFileGenerationProgress = SetFileGenerationProgress(
    generationId = this.generationId,
    expectedSize = this.expectedSize,
    localPrefixSize = this.localPrefixSize
)

fun TdApi.Thumbnail.toDomain(): Thumbnail = Thumbnail(
    format = this.format.toDomain(),
    width = this.width,
    height = this.height,
    file = this.file.toDomain()
)

fun TdApi.ThumbnailFormat.toDomain(): ThumbnailFormat = when(this) {
    is TdApi.ThumbnailFormatJpeg -> this.toDomain()
    is TdApi.ThumbnailFormatGif -> this.toDomain()
    is TdApi.ThumbnailFormatMpeg4 -> this.toDomain()
    is TdApi.ThumbnailFormatPng -> this.toDomain()
    is TdApi.ThumbnailFormatTgs -> this.toDomain()
    is TdApi.ThumbnailFormatWebm -> this.toDomain()
    is TdApi.ThumbnailFormatWebp -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ThumbnailFormatGif.toDomain(): ThumbnailFormatGif = ThumbnailFormatGif

fun TdApi.ThumbnailFormatJpeg.toDomain(): ThumbnailFormatJpeg = ThumbnailFormatJpeg

fun TdApi.ThumbnailFormatMpeg4.toDomain(): ThumbnailFormatMpeg4 = ThumbnailFormatMpeg4

fun TdApi.ThumbnailFormatPng.toDomain(): ThumbnailFormatPng = ThumbnailFormatPng

fun TdApi.ThumbnailFormatTgs.toDomain(): ThumbnailFormatTgs = ThumbnailFormatTgs

fun TdApi.ThumbnailFormatWebm.toDomain(): ThumbnailFormatWebm = ThumbnailFormatWebm

fun TdApi.ThumbnailFormatWebp.toDomain(): ThumbnailFormatWebp = ThumbnailFormatWebp

fun TdApi.Video.toDomain(): Video = Video(
    duration = this.duration,
    width = this.width,
    height = this.height,
    fileName = this.fileName,
    mimeType = this.mimeType,
    hasStickers = this.hasStickers,
    supportsStreaming = this.supportsStreaming,
    minithumbnail = this.minithumbnail?.toDomain(),
    thumbnail = this.thumbnail?.toDomain(),
    video = this.video.toDomain()
)

fun TdApi.VideoChat.toDomain(): VideoChat = VideoChat(
    groupCallId = this.groupCallId,
    hasParticipants = this.hasParticipants,
    defaultParticipantId = this.defaultParticipantId?.toDomain()
)

fun TdApi.VideoChatStream.toDomain(): VideoChatStream = VideoChatStream(
    channelId = this.channelId,
    scale = this.scale,
    timeOffset = this.timeOffset
)

fun TdApi.VideoChatStreams.toDomain(): VideoChatStreams = VideoChatStreams(
    streams = this.streams.map { it.toDomain() }
)

fun TdApi.VideoMessageAdvertisement.toDomain(): VideoMessageAdvertisement = VideoMessageAdvertisement(
    uniqueId = this.uniqueId,
    text = this.text,
    minDisplayDuration = this.minDisplayDuration,
    maxDisplayDuration = this.maxDisplayDuration,
    canBeReported = this.canBeReported,
    sponsor = this.sponsor.toDomain(),
    title = this.title,
    additionalInfo = this.additionalInfo
)

fun TdApi.VideoMessageAdvertisements.toDomain(): VideoMessageAdvertisements = VideoMessageAdvertisements(
    advertisements = this.advertisements.map { it.toDomain() },
    startDelay = this.startDelay,
    betweenDelay = this.betweenDelay
)

fun TdApi.VideoNote.toDomain(): VideoNote = VideoNote(
    duration = this.duration,
    waveform = this.waveform,
    length = this.length,
    minithumbnail = this.minithumbnail?.toDomain(),
    thumbnail = this.thumbnail?.toDomain(),
    speechRecognitionResult = this.speechRecognitionResult?.toDomain(),
    video = this.video.toDomain()
)

fun TdApi.VideoStoryboard.toDomain(): VideoStoryboard = VideoStoryboard(
    storyboardFile = this.storyboardFile.toDomain(),
    width = this.width,
    height = this.height,
    mapFile = this.mapFile.toDomain()
)

fun TdApi.VoiceNote.toDomain(): VoiceNote = VoiceNote(
    duration = this.duration,
    waveform = this.waveform,
    mimeType = this.mimeType,
    speechRecognitionResult = this.speechRecognitionResult?.toDomain(),
    voice = this.voice.toDomain()
)

