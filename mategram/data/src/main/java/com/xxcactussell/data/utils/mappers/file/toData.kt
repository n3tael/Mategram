package com.xxcactussell.data.utils.mappers.file

import com.xxcactussell.data.utils.mappers.advertisement.toData
import com.xxcactussell.data.utils.mappers.error.toData
import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.minithumbnail.toData
import com.xxcactussell.data.utils.mappers.speech.toData
import com.xxcactussell.data.utils.mappers.sticker.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun AnimatedEmoji.toData(): TdApi.AnimatedEmoji = TdApi.AnimatedEmoji(
    this.sticker?.toData(),
    this.stickerWidth,
    this.stickerHeight,
    this.fitzpatrickType,
    this.sound?.toData()
)

fun Audio.toData(): TdApi.Audio = TdApi.Audio(
    this.duration,
    this.title,
    this.performer,
    this.fileName,
    this.mimeType,
    this.albumCoverMinithumbnail?.toData(),
    this.albumCoverThumbnail?.toData(),
    this.externalAlbumCovers.map { it.toData() }.toTypedArray(),
    this.audio.toData()
)

fun Audios.toData(): TdApi.Audios = TdApi.Audios(
    this.totalCount,
    this.audios.map { it.toData() }.toTypedArray()
)

fun CancelDownloadFile.toData(): TdApi.CancelDownloadFile = TdApi.CancelDownloadFile(
    this.fileId,
    this.onlyIfPending
)

fun DeleteFile.toData(): TdApi.DeleteFile = TdApi.DeleteFile(
    this.fileId
)

fun Document.toData(): TdApi.Document = TdApi.Document(
    this.fileName,
    this.mimeType,
    this.minithumbnail?.toData(),
    this.thumbnail?.toData(),
    this.document.toData()
)

fun DownloadFile.toData(): TdApi.DownloadFile = TdApi.DownloadFile(
    this.fileId,
    this.priority,
    this.offset,
    this.limit,
    this.synchronous
)

fun File.toData(): TdApi.File = TdApi.File(
    this.id,
    this.size,
    this.expectedSize,
    this.local.toData(),
    this.remote.toData()
)

fun FileDownload.toData(): TdApi.FileDownload = TdApi.FileDownload(
    this.fileId,
    this.message.toData(),
    this.addDate,
    this.completeDate,
    this.isPaused
)

fun FileDownloadedPrefixSize.toData(): TdApi.FileDownloadedPrefixSize = TdApi.FileDownloadedPrefixSize(
    this.size
)

fun FileType.toData(): TdApi.FileType = when(this) {
    is FileTypeNone -> this.toData()
    is FileTypeAnimation -> this.toData()
    is FileTypeAudio -> this.toData()
    is FileTypeDocument -> this.toData()
    is FileTypeNotificationSound -> this.toData()
    is FileTypePhoto -> this.toData()
    is FileTypePhotoStory -> this.toData()
    is FileTypeProfilePhoto -> this.toData()
    is FileTypeSecret -> this.toData()
    is FileTypeSecretThumbnail -> this.toData()
    is FileTypeSecure -> this.toData()
    is FileTypeSelfDestructingPhoto -> this.toData()
    is FileTypeSelfDestructingVideo -> this.toData()
    is FileTypeSelfDestructingVideoNote -> this.toData()
    is FileTypeSelfDestructingVoiceNote -> this.toData()
    is FileTypeSticker -> this.toData()
    is FileTypeThumbnail -> this.toData()
    is FileTypeUnknown -> this.toData()
    is FileTypeVideo -> this.toData()
    is FileTypeVideoNote -> this.toData()
    is FileTypeVideoStory -> this.toData()
    is FileTypeVoiceNote -> this.toData()
    is FileTypeWallpaper -> this.toData()
}

fun FileTypeAnimation.toData(): TdApi.FileTypeAnimation = TdApi.FileTypeAnimation(
)

fun FileTypeAudio.toData(): TdApi.FileTypeAudio = TdApi.FileTypeAudio(
)

fun FileTypeDocument.toData(): TdApi.FileTypeDocument = TdApi.FileTypeDocument(
)

fun FileTypeNone.toData(): TdApi.FileTypeNone = TdApi.FileTypeNone(
)

fun FileTypeNotificationSound.toData(): TdApi.FileTypeNotificationSound = TdApi.FileTypeNotificationSound(
)

fun FileTypePhoto.toData(): TdApi.FileTypePhoto = TdApi.FileTypePhoto(
)

fun FileTypePhotoStory.toData(): TdApi.FileTypePhotoStory = TdApi.FileTypePhotoStory(
)

fun FileTypeProfilePhoto.toData(): TdApi.FileTypeProfilePhoto = TdApi.FileTypeProfilePhoto(
)

fun FileTypeSecret.toData(): TdApi.FileTypeSecret = TdApi.FileTypeSecret(
)

fun FileTypeSecretThumbnail.toData(): TdApi.FileTypeSecretThumbnail = TdApi.FileTypeSecretThumbnail(
)

fun FileTypeSecure.toData(): TdApi.FileTypeSecure = TdApi.FileTypeSecure(
)

fun FileTypeSelfDestructingPhoto.toData(): TdApi.FileTypeSelfDestructingPhoto = TdApi.FileTypeSelfDestructingPhoto(
)

fun FileTypeSelfDestructingVideo.toData(): TdApi.FileTypeSelfDestructingVideo = TdApi.FileTypeSelfDestructingVideo(
)

fun FileTypeSelfDestructingVideoNote.toData(): TdApi.FileTypeSelfDestructingVideoNote = TdApi.FileTypeSelfDestructingVideoNote(
)

fun FileTypeSelfDestructingVoiceNote.toData(): TdApi.FileTypeSelfDestructingVoiceNote = TdApi.FileTypeSelfDestructingVoiceNote(
)

fun FileTypeSticker.toData(): TdApi.FileTypeSticker = TdApi.FileTypeSticker(
)

fun FileTypeThumbnail.toData(): TdApi.FileTypeThumbnail = TdApi.FileTypeThumbnail(
)

fun FileTypeUnknown.toData(): TdApi.FileTypeUnknown = TdApi.FileTypeUnknown(
)

fun FileTypeVideo.toData(): TdApi.FileTypeVideo = TdApi.FileTypeVideo(
)

fun FileTypeVideoNote.toData(): TdApi.FileTypeVideoNote = TdApi.FileTypeVideoNote(
)

fun FileTypeVideoStory.toData(): TdApi.FileTypeVideoStory = TdApi.FileTypeVideoStory(
)

fun FileTypeVoiceNote.toData(): TdApi.FileTypeVoiceNote = TdApi.FileTypeVoiceNote(
)

fun FileTypeWallpaper.toData(): TdApi.FileTypeWallpaper = TdApi.FileTypeWallpaper(
)

fun FinishFileGeneration.toData(): TdApi.FinishFileGeneration = TdApi.FinishFileGeneration(
    this.generationId,
    this.error.toData()
)

fun GetFile.toData(): TdApi.GetFile = TdApi.GetFile(
    this.fileId
)

fun GetFileDownloadedPrefixSize.toData(): TdApi.GetFileDownloadedPrefixSize = TdApi.GetFileDownloadedPrefixSize(
    this.fileId,
    this.offset
)

fun GetFileExtension.toData(): TdApi.GetFileExtension = TdApi.GetFileExtension(
    this.mimeType
)

fun GetFileMimeType.toData(): TdApi.GetFileMimeType = TdApi.GetFileMimeType(
    this.fileName
)

fun GetRemoteFile.toData(): TdApi.GetRemoteFile = TdApi.GetRemoteFile(
    this.remoteFileId,
    this.fileType.toData()
)

fun LocalFile.toData(): TdApi.LocalFile = TdApi.LocalFile(
    this.path,
    this.canBeDownloaded,
    this.canBeDeleted,
    this.isDownloadingActive,
    this.isDownloadingCompleted,
    this.downloadOffset,
    this.downloadedPrefixSize,
    this.downloadedSize
)

fun PhotoSize.toData(): TdApi.PhotoSize = TdApi.PhotoSize(
    this.type,
    this.photo.toData(),
    this.width,
    this.height,
    this.progressiveSizes
)

fun RemoteFile.toData(): TdApi.RemoteFile = TdApi.RemoteFile(
    this.id,
    this.uniqueId,
    this.isUploadingActive,
    this.isUploadingCompleted,
    this.uploadedSize
)

fun SetFileGenerationProgress.toData(): TdApi.SetFileGenerationProgress = TdApi.SetFileGenerationProgress(
    this.generationId,
    this.expectedSize,
    this.localPrefixSize
)

fun Thumbnail.toData(): TdApi.Thumbnail = TdApi.Thumbnail(
    this.format.toData(),
    this.width,
    this.height,
    this.file.toData()
)

fun ThumbnailFormat.toData(): TdApi.ThumbnailFormat = when(this) {
    is ThumbnailFormatJpeg -> this.toData()
    is ThumbnailFormatGif -> this.toData()
    is ThumbnailFormatMpeg4 -> this.toData()
    is ThumbnailFormatPng -> this.toData()
    is ThumbnailFormatTgs -> this.toData()
    is ThumbnailFormatWebm -> this.toData()
    is ThumbnailFormatWebp -> this.toData()
}

fun ThumbnailFormatGif.toData(): TdApi.ThumbnailFormatGif = TdApi.ThumbnailFormatGif(
)

fun ThumbnailFormatJpeg.toData(): TdApi.ThumbnailFormatJpeg = TdApi.ThumbnailFormatJpeg(
)

fun ThumbnailFormatMpeg4.toData(): TdApi.ThumbnailFormatMpeg4 = TdApi.ThumbnailFormatMpeg4(
)

fun ThumbnailFormatPng.toData(): TdApi.ThumbnailFormatPng = TdApi.ThumbnailFormatPng(
)

fun ThumbnailFormatTgs.toData(): TdApi.ThumbnailFormatTgs = TdApi.ThumbnailFormatTgs(
)

fun ThumbnailFormatWebm.toData(): TdApi.ThumbnailFormatWebm = TdApi.ThumbnailFormatWebm(
)

fun ThumbnailFormatWebp.toData(): TdApi.ThumbnailFormatWebp = TdApi.ThumbnailFormatWebp(
)

fun Video.toData(): TdApi.Video = TdApi.Video(
    this.duration,
    this.width,
    this.height,
    this.fileName,
    this.mimeType,
    this.hasStickers,
    this.supportsStreaming,
    this.minithumbnail?.toData(),
    this.thumbnail?.toData(),
    this.video.toData()
)

fun VideoChat.toData(): TdApi.VideoChat = TdApi.VideoChat(
    this.groupCallId,
    this.hasParticipants,
    this.defaultParticipantId?.toData()
)

fun VideoChatStream.toData(): TdApi.VideoChatStream = TdApi.VideoChatStream(
    this.channelId,
    this.scale,
    this.timeOffset
)

fun VideoChatStreams.toData(): TdApi.VideoChatStreams = TdApi.VideoChatStreams(
    this.streams.map { it.toData() }.toTypedArray()
)

fun VideoMessageAdvertisement.toData(): TdApi.VideoMessageAdvertisement = TdApi.VideoMessageAdvertisement(
    this.uniqueId,
    this.text,
    this.minDisplayDuration,
    this.maxDisplayDuration,
    this.canBeReported,
    this.sponsor.toData(),
    this.title,
    this.additionalInfo
)

fun VideoMessageAdvertisements.toData(): TdApi.VideoMessageAdvertisements = TdApi.VideoMessageAdvertisements(
    this.advertisements.map { it.toData() }.toTypedArray(),
    this.startDelay,
    this.betweenDelay
)

fun VideoNote.toData(): TdApi.VideoNote = TdApi.VideoNote(
    this.duration,
    this.waveform,
    this.length,
    this.minithumbnail?.toData(),
    this.thumbnail?.toData(),
    this.speechRecognitionResult?.toData(),
    this.video.toData()
)

fun VideoStoryboard.toData(): TdApi.VideoStoryboard = TdApi.VideoStoryboard(
    this.storyboardFile.toData(),
    this.width,
    this.height,
    this.mapFile.toData()
)

fun VoiceNote.toData(): TdApi.VoiceNote = TdApi.VoiceNote(
    this.duration,
    this.waveform,
    this.mimeType,
    this.speechRecognitionResult?.toData(),
    this.voice.toData()
)

