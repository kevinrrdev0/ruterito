package gsg.corp.driver_presentation.route_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import gsg.corp.core_ui.global_components_actions.CommonType
import gsg.corp.core_ui.global_components_actions.GlobalCamera
import gsg.corp.core_ui.global_components_actions.GlobalSelect

@Composable
fun RouteDetailScreen(
    viewModel: RouteDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    Column( modifier = Modifier
        .fillMaxSize()) {
        Text(text = "Prueba hilt pass parameter")
        GlobalSelect(
            text = "Status",
            hint = "Seleccionar Status",
            inputText = state.state.name,
            selectedField = state.listState.map { CommonType(id = it.id, name = it.name) }.sortedBy{it.name},
            isError = false,
            isVisible = true,
            msgError = "",
            onFieldSelected = {name, id ->
                run {
                    viewModel.onEvent(RouteDetailEvent.OnStateSelected(id,name))
                }
            }
        )

        GlobalCamera(text = "Tomar Foto Cliente",
            onImageSelect = { viewModel.onPathChange(it) })
        GlobalCamera(text = "Tomar Foto Pago",
            onImageSelect = { viewModel.onPathChange(it) })
        Button(onClick = { viewModel.onUploadPhoto() }) {
            Text(text = "subir fotito")
        }
    }

}