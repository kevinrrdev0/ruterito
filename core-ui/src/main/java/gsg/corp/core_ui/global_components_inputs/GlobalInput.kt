package gsg.corp.core_ui.global_components_inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import gsg.corp.core_ui.LightGray
import gsg.corp.core_ui.global_components_ui.GlobalErrorCaption

@Composable
fun GlobalInput(
                text: String,
                hint:String,
                modifier: Modifier = Modifier,
                msgError:String ="",
                action: ImeAction = ImeAction.Next,
                type: KeyboardType = KeyboardType.Text,
                maxLines: Int = 1,
                maxChar: Int = 100,
                isError: Boolean = false,
                isVisible:Boolean = true,
                focusDir: FocusDirection = FocusDirection.Down,
                onAction: () -> Unit = { /* TODO */ },
                onValueChange: (String) -> Unit,
) {
    val localFocusManager = LocalFocusManager.current
    if (isVisible){
        Column(modifier = modifier
            .fillMaxWidth()) {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    if (it.length <= maxChar) {
                        onValueChange(it)
                    }
                },
                isError = isError,
                placeholder = { Text(hint, color = LightGray, fontWeight = FontWeight.W600) },
                modifier = modifier
                    .fillMaxWidth(),
                textStyle = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.W600),
                maxLines = maxLines,
                keyboardOptions = KeyboardOptions(keyboardType = type,
                    imeAction = action),
                keyboardActions = KeyboardActions(onNext = {
                    localFocusManager.moveFocus(focusDir)
                }, onDone = {
                    localFocusManager.clearFocus()
                    onAction()
                    defaultKeyboardAction(action)
                })
            )
            if (isError) {
                GlobalErrorCaption(msgError)
            }
        }
    }

}