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
import gsg.corp.core_ui.global_components_ui.GlobalErrorCaption

@Composable
fun GlobalSelect(
    text: String,
    hint: String,
    inputText: String,
    selectedField: List<CommonType>,
    isError: Boolean = false,
    msgError:String,
    isVisible:Boolean=true,
    onFieldSelected: (String, Int) -> Unit
) {

    if (isVisible){
        MaterialTheme {
            Column {
                val openDialog = remember { mutableStateOf(false)  }
                val (selectedOption, onOptionSelected) = remember {
                    mutableStateOf(selectedField[0].name)
                }
                Button(
                    border = if (isError) BorderStroke(1.dp, Color.Red) else BorderStroke(1.dp, Color.LightGray),
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
                        color = if (inputText.length>1) Color.Black else Color.DarkGray,
                        style = MaterialTheme.typography.h6.copy(fontWeight = if (inputText.length>1) FontWeight.W600 else FontWeight.W400))
                    Spacer(modifier = Modifier.weight(1.0f))
                    Icon(
                        imageVector = Icons.Filled.NavigateNext,
                        contentDescription = "",
                    )
                }
                if (isError) {
                    GlobalErrorCaption(msgError)
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
                                items(selectedField)
                                {data ->
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                    ) {
                                        Column() {
                                            Row(
                                                Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 16.dp)
                                                    .selectable(data.name == selectedOption) {
                                                        onFieldSelected(data.name, data.id)
                                                        onOptionSelected(data.name)
                                                    }
                                            ) {
                                                Text(
                                                    text = data.name,
                                                    modifier = Modifier.padding(start = 2.dp, top = 14.dp)
                                                )
                                                Spacer(modifier = Modifier.weight(1.0f))
                                                RadioButton(
                                                    selected = data.name == selectedOption,
                                                    onClick = {
                                                        onFieldSelected(data.name, data.id)
                                                        onOptionSelected(data.name)
                                                    },
                                                    colors = RadioButtonDefaults.colors(
                                                        selectedColor = Color.DarkGray,
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
                                        Text("CANCELAR", color = Color.DarkGray)
                                    }
                                    TextButton(
                                        onClick = {
                                            openDialog.value = false
                                        }
                                    ) {
                                        Text("SELECCIONAR", color = Color.DarkGray)
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }

}