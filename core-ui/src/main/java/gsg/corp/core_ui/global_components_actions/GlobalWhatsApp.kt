package gsg.corp.core_ui.global_components_actions

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.QuestionAnswer
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun GlobalWhatsApp(isEnable: Boolean = true,phone:String) {
    val context = LocalContext.current
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=51$phone"))
    val callResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()) {
    }

    IconButton(
        onClick = {
            callResultLauncher.launch(intent)
                  },
        enabled = isEnable
    ) {
        Icon(
            imageVector = Icons.Outlined.QuestionAnswer,
            contentDescription = null
        )
    }
}