package gsg.corp.ruterito.navigation

const val ROUTE_DETAIL_ID = "id"
const val MOVIE_DETAIL_ID = "id"
sealed class NavigationRoute(val route: String) {
    object Login : NavigationRoute("login")
    object MovieLogin : NavigationRoute("movie_login")
    object MovieHome : NavigationRoute("movie_home")
    object MovieDetail: NavigationRoute("movie_detail?$MOVIE_DETAIL_ID={$MOVIE_DETAIL_ID}") {
        fun passId(id: Int): String {
            return "movie_detail?$MOVIE_DETAIL_ID=$id"
        }
    }
    object Dashboard : NavigationRoute("dashboard")
    object Route : NavigationRoute("route")
    object RouteDetail : NavigationRoute("route_detail?$ROUTE_DETAIL_ID={$ROUTE_DETAIL_ID}") {
        fun passId(id: Int): String {
            return "route_detail?$ROUTE_DETAIL_ID=$id"
        }
    }


}