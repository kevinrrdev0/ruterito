package gsg.corp.driver_presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import gsg.corp.core.R
import gsg.corp.core.Util.UiEvent
import gsg.corp.core_ui.global_components_ui.LoadingAnimation
import gsg.corp.driver_presentation.login.components.RoundedButton
import gsg.corp.driver_presentation.login.components.TransparentTextField

@Composable
fun LoginScreen(
    scaffoldState: ScaffoldState,
    onNextClick: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }

    var passwordVisibility by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo_gsg),
            contentDescription = "Login Image",
            contentScale = ContentScale.Inside,
            modifier = Modifier.padding(40.dp)
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            ConstraintLayout {

                val (surface) = createRefs()

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .constrainAs(surface) {
                            bottom.linkTo(parent.bottom)
                        },
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStartPercent = 8,
                        topEndPercent = 8
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "¡Bienvenido Ruterito!",
                            style = MaterialTheme.typography.h4.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )

                        Text(
                            text = "Ingresa a tu cuenta",
                            style = MaterialTheme.typography.h5.copy(
                                color = MaterialTheme.colors.primary
                            )
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            TransparentTextField(
                                textFieldValue = viewModel.username,
                                textLabel = "Usuario",
                                keyboardType = KeyboardType.Email,
                                keyboardActions = KeyboardActions(
                                    onNext = {
                                        focusManager.moveFocus(FocusDirection.Down)
                                    }
                                ),
                                imeAction = ImeAction.Next,
                                onValueChange = { viewModel.onUsernameEnter(it) }
                            )

                            TransparentTextField(
                                textFieldValue = viewModel.password,
                                textLabel = "Contraseña",
                                keyboardType = KeyboardType.Password,
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()

                                        //TODO("LOGIN")
                                    }
                                ),
                                imeAction = ImeAction.Done,
                                trailingIcon = {
                                    IconButton(
                                        onClick = {
                                            passwordVisibility = !passwordVisibility
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (passwordVisibility) {
                                                Icons.Default.Visibility
                                            } else {
                                                Icons.Default.VisibilityOff
                                            },
                                            contentDescription = "Toggle Password Icon"
                                        )
                                    }
                                },
                                visualTransformation = if (passwordVisibility) {
                                    VisualTransformation.None
                                } else {
                                    PasswordVisualTransformation()
                                },
                                onValueChange = { viewModel.onPasswordEnter(it) }
                            )
                            Row(modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = viewModel.saveCredentials,
                                    onCheckedChange = { viewModel.onCheckRemember(it) },
                                    colors = CheckboxDefaults.colors(MaterialTheme.colors.primary)
                                )
                                Text(text = "Recordar usuario")
                            }

                        }

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            if (viewModel.isLoading) {
                                LoadingAnimation()
                            } else {
                                RoundedButton(
                                    text = "Login",
                                    displayProgressBar = false,
                                    onClick = {
                                        viewModel.onLogin()
                                        // TODO("LOGIN")
                                    }
                                )
                            }
                        }
                    }
                }

//                FloatingActionButton(
//                    modifier = Modifier
//                        .size(56.dp)
//                        .constrainAs(fab) {
//                            top.linkTo(surface.top, margin = (-36).dp)
//                            end.linkTo(surface.end, margin = 36.dp)
//                        },
//                    backgroundColor = MaterialTheme.colors.primary,
//                    onClick = {onNextClick()}
//                ) {
//                    Icon(
//                        modifier = Modifier.size(36.dp),
//                        imageVector = Icons.Default.ArrowForward,
//                        contentDescription = "Forward Icon",
//                        tint = Color.White
//                    )
//                }
            }
        }
    }

}
