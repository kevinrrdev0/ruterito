package gsg.corp.core_ui.global_components_actions


import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Upgrade
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import gsg.corp.core_ui.RedGsg
import gsg.corp.core_ui.global_components_ui.GlobalErrorCaption
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import java.io.ByteArrayOutputStream

var isCameradelected = false

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CameraGalleryDialog(
    label: String,
    is_photo: Boolean,
    is_document: Boolean,
    isError: Boolean = false,
    msgError:String,
    isVisible: Boolean = true,
    modifier: Modifier = Modifier,
    color: Color = RedGsg,
    textStyle: TextStyle = MaterialTheme.typography.body1,
    exposeUri:(String)->Unit
) {
    if (isVisible) {
        val context = LocalContext.current
        val firebase_context= context.findActivity() as ComponentActivity
        var imageUri by remember {
            mutableStateOf<Uri?>(null)
        }
        val bitmap = remember {
            mutableStateOf<Bitmap?>(null)
        }
        val bottomSheetModalState =
            rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val coroutineScope = rememberCoroutineScope()

        val galleryLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
        ) { uri: Uri? ->
            imageUri = uri
            uri?.let{
                 //exposeUri(it)
            }
        }

        val cameraLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview()
        ) { btm: Bitmap? ->
            bitmap.value = btm
            imageUri = null

            exposeUri(compressCamera(
                firebase_context,
                bitmap.value!!,
                label
            )?:"")
        }

        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                if (isCameradelected) {
                    cameraLauncher.launch()
                } else {
                    galleryLauncher.launch("image/*")
                }
                coroutineScope.launch {
                    bottomSheetModalState.hide()
                }
            } else {
                Toast.makeText(context, "Permission Denied!", Toast.LENGTH_SHORT).show()
            }
        }
        val isShowAlert = remember { mutableStateOf(false) }

        MaterialTheme {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                if (is_photo) {
                    Column() {
                        Row() {
                            Card(
                                modifier = Modifier
                                    .size(80.dp),
                                elevation = 8.dp
                            ) {
                                Icon(
                                    Icons.Default.AccountCircle,
                                    contentDescription = "",
                                    tint= RedGsg
                                )
                                imageUri?.let {
                                    if (!isCameradelected) {
                                        bitmap.value = if (Build.VERSION.SDK_INT < 28) {
                                            MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                                        } else {
                                            val source = ImageDecoder.createSource(context.contentResolver, it)
                                            ImageDecoder.decodeBitmap(source)
                                        }
                                    }
                                    bitmap.value?.let { btm ->
                                        Image(
                                            bitmap = btm.asImageBitmap(),
                                            contentDescription = "Image",
                                            alignment = Alignment.TopCenter,
                                            modifier = Modifier
                                                .size(128.dp),
                                            contentScale = ContentScale.Fit
                                        )
                                    }
                                }
                                bitmap.value?.let { btm ->
                                    Image(
                                        bitmap = btm.asImageBitmap(),
                                        contentDescription = "Image",
                                        alignment = Alignment.TopCenter,
                                        modifier = Modifier
                                            .size(128.dp)
                                            .clip(CircleShape),
                                        contentScale = ContentScale.Fit
                                    )
                                }
                            }
                            Text(
                                text = label,
                                modifier = modifier
                                    .padding(start = 5.dp)
                                    .padding(top = 30.dp)
                                    .clickable(onClick = {
                                        isShowAlert.value = true
                                    }),
                                style = textStyle, color = color
                            )
                        }
                        if (isError) {
                            GlobalErrorCaption(msgError)
                        }
                    }
                }

                if (isShowAlert.value) {
                    AlertDialog(
                        onDismissRequest = { isShowAlert.value = false },
                        title = { Text(text = "Elegir foto") },
                        text = {
                            Column() {
                                Text(
                                    text = "Desde camara",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            when (PackageManager.PERMISSION_GRANTED) {
                                                ContextCompat.checkSelfPermission(
                                                    context, Manifest.permission.CAMERA
                                                ) -> {
                                                    cameraLauncher.launch()
                                                    coroutineScope.launch {
                                                        bottomSheetModalState.hide()
                                                    }
                                                }
                                                else -> {
                                                    isCameradelected = true
                                                    permissionLauncher.launch(Manifest.permission.CAMERA)
                                                    permissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)

                                                }
                                            }
                                            isShowAlert.value = false
                                            if (bitmap.value != null) {
                                                GlobalScope.launch(Dispatchers.IO) {
//                                                    val compressedCamera = compressCamera(
//                                                        firebase_context,
//                                                        bitmap.value!!,
//                                                        label
//                                                    )


                                                }
                                            }
                                        }
                                        .padding(15.dp),
                                )
                                Text(
                                    text = "Desde galeria",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            when (PackageManager.PERMISSION_GRANTED) {
                                                ContextCompat.checkSelfPermission(
                                                    context,
                                                    Manifest.permission.READ_EXTERNAL_STORAGE
                                                ) -> {
                                                    galleryLauncher.launch("image/*")
                                                    coroutineScope.launch {
                                                        bottomSheetModalState.hide()
                                                    }
                                                }
                                                else -> {
                                                    isCameradelected = false
                                                    permissionLauncher.launch(
                                                        Manifest.permission.READ_EXTERNAL_STORAGE
                                                    )
                                                }
                                            }
                                            isShowAlert.value = false
                                            if (imageUri != null) {
                                                GlobalScope.launch(Dispatchers.IO) {
//                                                    val compressedImage = compressImage(
//                                                        firebase_context,
//                                                        imageUri!!,
//                                                        label
//                                                    )

//                                                    uploadPhoto(
//                                                        compressedImage!!,
//                                                        "$label.jpg",
//                                                        "image/jpg"
//                                                    ) {
//                                                        GlobalScope.launch(Dispatchers.Main) {
//                                                            Toast
//                                                                .makeText(
//                                                                    firebase_context,
//                                                                    "File uploaded!",
//                                                                    Toast.LENGTH_SHORT
//                                                                )
//                                                                .show()
//                                                        }
//                                                    }
                                                }
                                            }
                                        }
                                        .padding(15.dp),
                                )
                            }
                        },
                        buttons = {
                            Column() {
                                Divider(color = Color.Gray, thickness = 1.dp)
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.Top,
                                    horizontalArrangement = Arrangement.End
                                ){
                                    TextButton(
                                        onClick = {
                                            isShowAlert.value = false
                                        }
                                    ) {
                                        Text("CANCELAR", color = RedGsg)
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

private fun compressCamera(context: ComponentActivity, bitmap: Bitmap, name: String):String? {
    val bytes = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 60, bytes)
    val path: String = MediaStore.Images.Media.insertImage(
        context.contentResolver,
        bitmap,
        name,
        null
    )
    return path
}

private fun compressImage(context: ComponentActivity, uri: Uri, name: String):Uri? {
    val bitmap = if(Build.VERSION.SDK_INT<28) {
        MediaStore.Images.Media.getBitmap(
            context.contentResolver,
            uri
        )
    } else {
        val source = ImageDecoder.createSource(context.contentResolver, uri)
        ImageDecoder.decodeBitmap(source)
    }
    val bytes = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 60, bytes)
    val path: String = MediaStore.Images.Media.insertImage(
        context.contentResolver,
        bitmap,
        name,
        null
    )
    return Uri.parse(path)
}

//private suspend fun uploadPhoto(
//    uri: Uri,
//    name: String,
//    mimeType: String?,
//    callback: (url: String) ->Unit
//){
//    val storage = FirebaseStorage.getInstance()
//    val storageRef = storage.reference
//    val fileRef = storageRef.child("images/$name")
//
//    val metadata = mimeType?.let {
//        StorageMetadata.Builder()
//            .setContentType(mimeType)
//            .build()
//    }
//    if (metadata != null) {
//        fileRef.putFile(uri, metadata).await()
//    } else {
//        fileRef.putFile(uri).await()
//    }
//
//    callback(fileRef.downloadUrl.await().toString())
//}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}