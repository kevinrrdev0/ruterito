package gsg.corp.core_ui.global_components_actions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import gsg.corp.core_ui.LightGray
import gsg.corp.core_ui.LocalSpacing
import gsg.corp.core_ui.RedGsg

@Composable
fun GlobalOutLineButton(
    text: String,
    modifier: Modifier = Modifier,
    isEnable: Boolean = true,
    onClick: () -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.SemiBold)
) {
    OutlinedButton(onClick = { onClick() },
        enabled = isEnable,
        border = BorderStroke(1.dp, if (isEnable) RedGsg else LightGray),
        modifier = modifier) {
        Text(text,
            style = textStyle,
            modifier = modifier.padding(LocalSpacing.current.default),
            textAlign = TextAlign.Center)
    }
}