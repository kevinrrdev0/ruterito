package gsg.corp.core_ui.global_components_actions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import gsg.corp.core_ui.BuildConfig
import gsg.corp.core_ui.RedGsg
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerSmall
import kotlinx.android.extensions.CacheImplementation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*

@Composable
fun GlobalCamera(text: String, onImageSelect: (String) -> Unit) {
    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }
    var latestTmpUri: Uri? = null
    val coroutineScope = rememberCoroutineScope()
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) {
        if (it) {
            latestTmpUri?.let { uri ->
                val bit = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver,
                        uri))
                } else {
                    MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                }
                bit?.let {
                    coroutineScope.launch(Dispatchers.IO) {
                        bitmap.value = bit
                        val pathEnd = context.getExternalFilesDir(null)?.absolutePath ?: ""
                        val dir = File(pathEnd)
                        if (!dir.exists()) dir.mkdirs()
                        val file = File(dir, "img-${System.currentTimeMillis()}.jpg")
                        val fOut = FileOutputStream(file)

                        bit.compress(Bitmap.CompressFormat.JPEG, 70, fOut)
                        fOut.flush()
                        fOut.close()
                        onImageSelect(file.absolutePath ?: "noPath")
                        latestTmpUri = null
                    }
                }
            }
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        var flk = true
        permissions.entries.forEach {
            flk = it.value
        }
        if (flk) {
            latestTmpUri = getUri(context = context)
            cameraLauncher.launch(latestTmpUri)
        } else {
            Toast.makeText(context, "Permission Denied!", Toast.LENGTH_SHORT).show()
        }
    }

    val isShowAlert = remember { mutableStateOf(false) }
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column {
            Card(modifier = Modifier
                .size(120.dp),
                elevation = 0.dp) {
                Icon(
                    Icons.Default.PhotoCamera,
                    contentDescription = "",
                    tint = RedGsg
                )

                bitmap.value?.let { btm ->
                    Image(
                        bitmap = btm.asImageBitmap(),
                        contentDescription = "Image",
                        alignment = Alignment.TopCenter,
                        modifier = Modifier
                            .clip(RectangleShape),
                        contentScale = ContentScale.Inside
                    )
                }
            }
            GlobalSpacerSmall()
            Text(
                text = text,
                modifier = Modifier
                    .clickable(onClick = {
                        isShowAlert.value = true
                    }),
                style = MaterialTheme.typography.body1, color = RedGsg
            )
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
                                    ),
                                    -> {
                                        if (ContextCompat.checkSelfPermission(
                                                context, Manifest.permission.WRITE_EXTERNAL_STORAGE
                                            ) == 1
                                        ) {
                                            latestTmpUri = getUri(context = context)
                                            cameraLauncher.launch(latestTmpUri)
                                        } else {
                                            permissionLauncher.launch(arrayOf(Manifest.permission.CAMERA,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE))
                                        }
                                    }
                                    else -> {
                                        permissionLauncher.launch(arrayOf(Manifest.permission.CAMERA,
                                            Manifest.permission.WRITE_EXTERNAL_STORAGE))
                                    }
                                }
                                isShowAlert.value = false
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
                    ) {
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

private fun getUri(context: Context): Uri {
    val tmpFile = File.createTempFile("img", ".png", context.cacheDir).apply {
        createNewFile()
        deleteOnExit()
    }
    return FileProvider.getUriForFile(context.applicationContext,
        "${context.packageName}.provider",
        tmpFile)
}
