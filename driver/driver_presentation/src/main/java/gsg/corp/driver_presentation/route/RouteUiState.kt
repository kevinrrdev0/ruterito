package gsg.corp.driver_presentation.route

import gsg.corp.driver_domain.model.Route

data class RouteUiState(
    val route: Route,
    val isExpanded: Boolean = false
)