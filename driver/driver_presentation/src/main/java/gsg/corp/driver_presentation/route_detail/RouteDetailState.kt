package gsg.corp.driver_presentation.route_detail

import android.net.Uri
import gsg.corp.core.domain.model.GeneralType

data class RouteDetailState(
    val idRoute: Int = 0,
    val uri: Uri = Uri.EMPTY,
    val state: GeneralType = GeneralType(0,""),
    val listState: List<GeneralType> = listOf(
        GeneralType(9, "Registrado"),
        GeneralType(4, "En Ruta"),
        GeneralType(3, "Pendiente"),
        GeneralType(5, "Perdido"),
        GeneralType(6, "Reprogramado"),
        GeneralType(7, "Rechazado"),
        GeneralType(8, "Entregado")
    ),
    val comment: String = "",
    val pathPhotoState: String = "",
    val pathPhotoCollect: String = "",
    val isLoading: Boolean = false,
)