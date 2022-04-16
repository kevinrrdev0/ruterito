package gsg.corp.driver_presentation.route

sealed class RouteEvent{
    data class OnToggleRouteClick(val routeUiState: RouteUiState):RouteEvent()
}
