package gsg.corp.core_ui.global_components_inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GlobalErrorCaption(msg:String,modifier: Modifier=Modifier){
    Text(
        text = msg,
        color = MaterialTheme.colors.error,
        style = MaterialTheme.typography.caption,
        modifier = modifier
            .fillMaxWidth()
    )
}