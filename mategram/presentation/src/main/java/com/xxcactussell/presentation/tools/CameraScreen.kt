package com.xxcactussell.presentation.tools

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.MediaStoreOutputOptions
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.PreviewView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.util.Consumer
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.xxcactussell.mategram.presentation.R
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun CameraScreen(
    mode: Int,
    onBackHandled: () -> Unit,
    chatId: Long?,
    onMediaCaptured: (List<Uri>) -> Unit
) {
    val context = LocalContext.current
    var hasPermissions by remember {
        mutableStateOf(
            (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED) &&
                    (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.RECORD_AUDIO
                    ) == PackageManager.PERMISSION_GRANTED)
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            hasPermissions = permissions.values.all { it }
        }
    )

    LaunchedEffect(Unit) {
        if (!hasPermissions) {
            launcher.launch(arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO))
        }
    }

    if (hasPermissions) {
        CameraContent(
            onBackHandled = onBackHandled,
            onMediaCaptured = onMediaCaptured
        )
    } else {
        Box(modifier = Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) {
            Text("Нет доступа к камере или микрофону", color = Color.White)
        }
    }
}

@Composable
fun CameraContent(
    onBackHandled: () -> Unit,
    onMediaCaptured: (List<Uri>) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var lensFacing by remember { mutableIntStateOf(CameraSelector.LENS_FACING_BACK) }
    var selectedMode by remember { mutableIntStateOf(0) } // 0 for Photo, 1 for Video
    var isRecording by remember { mutableStateOf(false) }
    var activeRecording: Recording? by remember { mutableStateOf(null) }

    val preview = remember { Preview.Builder().build() }
    val previewView = remember { PreviewView(context) }
    val imageCapture = remember { ImageCapture.Builder().build() }
    val recorder = remember { Recorder.Builder().build() }
    val videoCapture = remember { VideoCapture.withOutput(recorder) }

    LaunchedEffect(lensFacing) {
        val cameraProvider = context.getCameraProvider()
        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(lensFacing)
            .build()
        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                imageCapture,
                videoCapture
            )
            preview.surfaceProvider = previewView.surfaceProvider
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Column(modifier = Modifier.fillMaxSize().background(Color.Black).screenStyle()) {
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
        ) {
            AndroidView(
                factory = { previewView },
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxSize()
            )
            Row(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .statusBarsPadding()
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onBackHandled() }) {
                    Icon(painterResource(R.drawable.close_24px), contentDescription = "Close", tint = Color.White)
                }

                IconButton(onClick = { /* TODO Настройки яркости/вспышки */ }) {
                    Icon(painterResource(R.drawable.flash_on_24px), contentDescription = "Settings", tint = Color.White)
                }
            }
        }

        BottomControlPanel(
            selectedMode = selectedMode,
            onModeChange = { selectedMode = it },
            isRecording = isRecording,
            onTakePhoto = {
                takePhoto(
                    imageCapture = imageCapture,
                    context = context,
                    onImageCaptured = { uri ->
                        onMediaCaptured(listOf(uri))
                        onBackHandled()
                    },
                    onError = { exception -> /* TODO: Handle error */ }
                )
            },
            onRecordVideo =  {
                if (isRecording) {
                    activeRecording?.stop()
                } else {
                    recordVideo(
                        videoCapture = videoCapture,
                        context = context,
                        onRecordingStarted = { recording ->
                            activeRecording = recording
                            isRecording = true
                        },
                        onRecordingFinalized = { uri ->
                            isRecording = false
                            activeRecording = null
                            uri?.let { onMediaCaptured(listOf(it)) }
                            onBackHandled()
                        },
                        onError = {
                            isRecording = false
                            activeRecording = null
                            // TODO: Handle error
                        }
                    )
                }
            },
            onSwitchCamera = {
                lensFacing = if (lensFacing == CameraSelector.LENS_FACING_BACK)
                    CameraSelector.LENS_FACING_FRONT
                else
                    CameraSelector.LENS_FACING_BACK
            },
            onGalleryPicked = { uris ->
                if (uris.isNotEmpty()) {
                    onMediaCaptured(uris)
                    onBackHandled()
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun BottomControlPanel(
    selectedMode: Int,
    onModeChange: (Int) -> Unit,
    isRecording: Boolean,
    onTakePhoto: () -> Unit,
    onRecordVideo: () -> Unit,
    onSwitchCamera: () -> Unit,
    onGalleryPicked: (List<Uri>) -> Unit
) {
    val mediaPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris ->
            onGalleryPicked(uris)
        }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(bottom = 32.dp, top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val mediumSize = ButtonDefaults.MediumContainerHeight
        val largeSize = ButtonDefaults.LargeContainerHeight
        val xSmallSize = ButtonDefaults.ExtraSmallContainerHeight

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(42.dp)
        ) {
            OutlinedIconButton(
                onClick = {
                    mediaPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
                    )
                },
                modifier = Modifier.size(mediumSize),
                border = BorderStroke(2.dp, Color.White),
                colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)
            ) {
                Icon(
                    painterResource(R.drawable.photo_library_24px),
                    contentDescription = "Gallery",
                    modifier = Modifier.size(ButtonDefaults.iconSizeFor(mediumSize)),
                )
            }

            OutlinedIconButton(
                onClick = {
                    if (selectedMode == 0) onTakePhoto() else onRecordVideo()
                },
                modifier = Modifier.size(largeSize),
                border = BorderStroke(2.dp, Color.White),
                colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(if (isRecording) Color.Red else Color.White)
                ) {
                    if (isRecording) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.White)
                        )
                    }
                }
            }

            OutlinedIconButton(
                onClick = onSwitchCamera,
                modifier = Modifier.size(mediumSize),
                border = BorderStroke(2.dp, Color.White),
                colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)
            ) {
                Icon(painterResource(R.drawable.cameraswitch_24px), contentDescription = "Switch Camera")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        val options = listOf("Photo", "Video")
        val unCheckedIcons = listOf(painterResource(R.drawable.photo_camera_24px), painterResource(R.drawable.videocam_24px))
        val checkedIcons = listOf(painterResource(R.drawable.photo_camera_fill_24px), painterResource(R.drawable.videocam_fill_24px))

        Row(
            horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
        ) {
            options.forEachIndexed { index, label ->
                ToggleButton(
                    checked = selectedMode == index,
                    onCheckedChange = { onModeChange(index) },
                    modifier = Modifier
                        .height(xSmallSize)
                        .semantics { role = Role.RadioButton },
                    contentPadding = ButtonDefaults.contentPaddingFor(xSmallSize),
                    shapes =
                    when (index) {
                        0 -> ButtonGroupDefaults.connectedLeadingButtonShapes()
                        options.lastIndex -> ButtonGroupDefaults.connectedTrailingButtonShapes()
                        else -> ButtonGroupDefaults.connectedMiddleButtonShapes()
                    },
                ) {
                    Icon(
                        if (selectedMode == index) checkedIcons[index] else unCheckedIcons[index],
                        modifier = Modifier.size(ButtonDefaults.iconSizeFor(xSmallSize)),
                        contentDescription = label,
                    )
                    Spacer(Modifier.size(ToggleButtonDefaults.IconSpacing))
                    Text(
                        text = label,
                        style = ButtonDefaults.textStyleFor(xSmallSize)
                    )
                }
            }
        }
    }
}

private fun takePhoto(
    imageCapture: ImageCapture,
    context: Context,
    onImageCaptured: (Uri) -> Unit,
    onError: (ImageCaptureException) -> Unit
) {
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, "IMG_${System.currentTimeMillis()}.jpg")
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
    }

    val outputOptions = ImageCapture.OutputFileOptions.Builder(
        context.contentResolver,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        contentValues
    ).build()

    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                outputFileResults.savedUri?.let(onImageCaptured)
            }

            override fun onError(exception: ImageCaptureException) {
                onError(exception)
            }
        }
    )
}

@SuppressLint("MissingPermission")
private fun recordVideo(
    videoCapture: VideoCapture<Recorder>,
    context: Context,
    onRecordingStarted: (Recording) -> Unit,
    onRecordingFinalized: (Uri?) -> Unit,
    onError: (Throwable) -> Unit
) {
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, "VID_${System.currentTimeMillis()}.mp4")
        put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
    }

    val mediaStoreOutputOptions = MediaStoreOutputOptions.Builder(
        context.contentResolver,
        MediaStore.Video.Media.EXTERNAL_CONTENT_URI
    ).setContentValues(contentValues).build()

    val recording = videoCapture.output
        .prepareRecording(context, mediaStoreOutputOptions)
        .withAudioEnabled()
        .start(ContextCompat.getMainExecutor(context), Consumer { recordEvent ->
            when (recordEvent) {
                is VideoRecordEvent.Finalize -> {
                    if (!recordEvent.hasError()) {
                        onRecordingFinalized(recordEvent.outputResults.outputUri)
                    } else {
                        onRecordingFinalized(null)
                        onError(recordEvent.cause ?: UnknownError())
                    }
                }
            }
        })
    onRecordingStarted(recording)
}

suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine { continuation ->
    val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
    cameraProviderFuture.addListener({
        continuation.resume(cameraProviderFuture.get())
    }, ContextCompat.getMainExecutor(this))
}