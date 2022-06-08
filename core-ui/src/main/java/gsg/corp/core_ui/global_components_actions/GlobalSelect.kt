package gsg.corp.core_ui.global_components_actions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun GlobalSelect(
    text: String,
    hint: String,
    inputText: String,
    onFieldSelected: (String,Int) -> Unit,
) {

    MaterialTheme {
        Column() {

            val selectedField = listOf(
            CommonType(id=0, name=""),
                CommonType(id=9, name="Registrado"),
                CommonType(id=4, name="En Ruta"),
                CommonType(id=3, name="Pendiente"),
                CommonType(id=5, name="Perdido"),
                CommonType(id=6, name="Reprogramado"),
                CommonType(id=7, name="Rechazado"),
                CommonType(id=8, name="Entregado")
            )

            val openDialog = remember { mutableStateOf(false)  }
            val (selectedOption, onOptionSelected) = remember {
                mutableStateOf(selectedField[0].name)
            }
            Button(
                border = BorderStroke(1.dp, Color.LightGray),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.DarkGray,
                    backgroundColor = Color.White
                ),
                onClick = {
                    openDialog.value = true
                },
                modifier = Modifier
                    .height(55.dp)
            ) {
                Text(
                    if (inputText.length>1) inputText else hint,
                    color = if (inputText.length>1) Color.Black else Color.Black,
                    style = MaterialTheme.typography.h6.copy(fontWeight = if (inputText.length>1) FontWeight.W600 else FontWeight.W400))
                Spacer(modifier = Modifier.weight(1.0f))
                Icon(
                    imageVector = Icons.Filled.NavigateNext,
                    contentDescription = "",
                )
            }
            if (openDialog.value) {

                AlertDialog(
                    modifier = if(selectedField.count()>5) Modifier.height(450.dp) else Modifier.padding(5.dp),
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(text = text)
                    },
                    text = {
                        LazyColumn(
                            modifier = if(selectedField.count()>5) Modifier.height(350.dp) else Modifier.padding(5.dp)
                        )
                        {
                            items(selectedField){datos ->
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Column() {
                                        Row(
                                            Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 16.dp)
                                                .selectable(datos.name == selectedOption) {
                                                    onFieldSelected(datos.name, datos.id)
                                                    onOptionSelected(datos.name)
                                                }
                                        ) {
                                            Text(
                                                text = datos.name,
                                                modifier = Modifier.padding(start = 2.dp, top = 14.dp)
                                            )
                                            Spacer(modifier = Modifier.weight(1.0f))
                                            RadioButton(
                                                selected = datos.name == selectedOption,
                                                onClick = {
                                                    onFieldSelected(datos.name, datos.id)
                                                    onOptionSelected(datos.name)
                                                },
                                                colors = RadioButtonDefaults.colors(
                                                    selectedColor = Color.Black,
                                                    unselectedColor = Color.LightGray
                                                )
                                            )
                                        }
                                    }
                                }
                            }
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
                                        openDialog.value = false
                                    }
                                ) {
                                    Text("CANCELAR", color = Color.Black)
                                }
                                TextButton(
                                    onClick = {
                                        openDialog.value = false
                                    }
                                ) {
                                    Text("SELECCIONAR", color = Color.Black)
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}