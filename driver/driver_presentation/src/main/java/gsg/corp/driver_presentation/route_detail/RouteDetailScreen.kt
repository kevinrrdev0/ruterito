package gsg.corp.driver_presentation.route_detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import gsg.corp.core.Util.getFilePath
import gsg.corp.core_ui.global_components_actions.CameraGalleryDialog
import gsg.corp.core_ui.global_components_actions.CommonType
import gsg.corp.core_ui.global_components_actions.GlobalCamera
import gsg.corp.core_ui.global_components_actions.GlobalSelect

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RouteDetailScreen(
    viewModel: RouteDetailViewModel = hiltViewModel(),
) {

    LazyColumn(Modifier.fillMaxSize()) {

        item {
            Text(text = "Prueba hilt pass parameter")
            GlobalSelect("Hola",
                "pruebahint",
                "inputtext",
                onFieldSelected = { _, id ->
                    run{
                        viewModel.onEvent(RouteDetailEvent.OnStateSelected(id))
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

}