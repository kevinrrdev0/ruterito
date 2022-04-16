package gsg.corp.ruterito.navigation

const val ROUTE_DETAIL_ID = "id"

sealed class NavigationRoute(val route: String) {
    object Login : NavigationRoute("login")
    object Dashboard : NavigationRoute("dashboard")
    object Route : NavigationRoute("route")
    object RouteDetail : NavigationRoute("route_detail?$ROUTE_DETAIL_ID={$ROUTE_DETAIL_ID}") {
        fun passId(id: Int): String {
            return "route_detail?$ROUTE_DETAIL_ID=$id"
        }
    }


}