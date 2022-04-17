package gsg.corp.driver_presentation.route_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import gsg.corp.core_ui.global_components_actions.CameraGalleryDialog

@Composable
fun RouteDetailScreen(
    id: Int,
    routeDetailViewModel: RouteDetailViewModel = hiltViewModel()
) {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Prueba hilt pass parameter")

        CameraGalleryDialog(
            "Sube tu foto" ,
            is_photo = true,
            is_document = false,
            isVisible = true,
            isError = false,
            msgError = "",
            exposeUri = {
                routeDetailViewModel.onUriChange(it)
            }
        )

        Button(onClick = { routeDetailViewModel.onUploadPhoto() }) {
            Text(text = "subir fotito")
        }
    }
    




}