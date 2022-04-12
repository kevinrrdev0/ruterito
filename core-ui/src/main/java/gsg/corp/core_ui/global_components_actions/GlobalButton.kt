package gsg.corp.core_ui.global_components_actions

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import gsg.corp.core_ui.LocalSpacing

@Composable
fun GlobalButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.SemiBold, color = Color.White),
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        enabled = isEnabled,
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = text,
            style = textStyle,
            modifier = Modifier.padding(LocalSpacing.current.spaceExtraSmall)
        )
    }
}