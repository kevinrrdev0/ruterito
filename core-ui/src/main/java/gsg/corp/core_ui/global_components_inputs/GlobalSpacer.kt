package gsg.corp.core_ui.global_components_inputs

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import gsg.corp.core_ui.LocalSpacing

@Composable
fun GlobalSpacerMid(isVisible:Boolean = true) {
    if (isVisible){
        val spacing = LocalSpacing.current
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
    }
}

@Composable
fun GlobalSpacerSmall(isVisible:Boolean = true) {
    if (isVisible){
        val spacing = LocalSpacing.current
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
    }
}

@Composable
fun GlobalSpacerLarge(isVisible:Boolean = true) {
    if (isVisible){
        val spacing = LocalSpacing.current
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
    }
}