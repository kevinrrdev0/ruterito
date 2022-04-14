package gsg.corp.driver_domain.model

data class RouteUiState (
    val listRoutes:List<Route> = emptyList(),
    val loading: Boolean = false
        )