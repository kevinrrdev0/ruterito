package gsg.corp.core_ui.global_components_inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import gsg.corp.core_ui.DarkGray
import gsg.corp.core_ui.LightGray
import gsg.corp.core_ui.RedGsg

@Composable
fun GlobalInputPassword(
    text: String,
    hint:String,
    msgError:String="",
    modifier: Modifier = Modifier,
    action: ImeAction = ImeAction.Next,
    type: KeyboardType = KeyboardType.Text,
    maxLines: Int = 1,
    maxChar: Int = 25,
    isError: Boolean = false,
    focusDir:FocusDirection = FocusDirection.Down,
    onAction: () -> Unit = { /* TODO */ },
    onValueChange: (String) -> Unit,
) {
    val localFocusManager = LocalFocusManager.current
    var hidden by remember { mutableStateOf(true) }
    Column(modifier = modifier
        .fillMaxWidth()) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                if (it.length <= maxChar) {
                    onValueChange(it)
                }
            },
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
            }),
            visualTransformation =
            if (hidden) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(onClick = { hidden = !hidden }) {
                    val vector = if (hidden) Icons.Outlined.Visibility
                    else Icons.Outlined.VisibilityOff
                    Icon(imageVector =  vector, contentDescription = "password_icon")
                }
            },
            colors = if (isError) TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Red,
                unfocusedBorderColor = Red) else TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = RedGsg,
                unfocusedBorderColor = DarkGray)
        )
        if (isError) {
            val mayusRegex = "^(?=.*[A-Z]).{8,20}\$".toRegex()
            val numbersRegex = "^(?=.*[0-9]).{8,20}\$".toRegex()
            val specialCharactersRegex = "^(?=.*[!@#&()–[{}]:;',?/*~\$^+=<>]).{8,20}\$".toRegex()
            val minusRegex = "^(?=.*[a-z]).{8,20}$".toRegex();
            Text(
                text = "La contraseña debe contener al menos 8 caracteres.",
                color = if (text.length > 8) {
                    Color.Green
                } else MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = "La contraseña debe contener caracteres especiales.",
                color = if (specialCharactersRegex.matches(text)) {
                    Color.Green
                } else MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = "La contraseña debe contener letras mayúsculas.",
                color = if (mayusRegex.matches(text)) {
                    Color.Green
                } else MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = "La contraseña debe contener letras minúsculas.",
                color = if (minusRegex.matches(text)) {
                    Color.Green
                } else MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = "La contraseña debe contener números.",
                color = if (numbersRegex.matches(text)) {
                    Color.Green
                } else MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

