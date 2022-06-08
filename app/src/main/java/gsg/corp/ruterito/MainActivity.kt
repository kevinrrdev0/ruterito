package gsg.corp.ruterito

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.*
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import gsg.corp.driver_presentation.dashboard.DashBoardScreen
import gsg.corp.driver_presentation.login.LoginScreen
import gsg.corp.driver_presentation.route.RouteScreen
import gsg.corp.driver_presentation.route_detail.RouteDetailScreen
import gsg.corp.ruterito.navigation.NavigationRoute
import gsg.corp.ruterito.navigation.ROUTE_DETAIL_ID
import gsg.corp.ruterito.navigation.Route
import gsg.corp.ruterito.ui.theme.RuteritoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RuteritoTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                    NavHost(navController = navController,
                        startDestination = NavigationRoute.Route.route)

                    {
                        addLogin(navController = navController, scaffoldState)
                        addDashBoard(navController = navController)
                        addRoute(navController = navController)
                        addRouteDetail(navController = navController)
                    }
            }
        }
    }
}
//
//enterTransition = {
//    slideInHorizontally(
//        initialOffsetX = { 1000 },
//        animationSpec = tween(500)
//    )
//},
//exitTransition = {
//    slideOutHorizontally(
//        targetOffsetX = { -1000 },
//        animationSpec = tween(500)
//    )
//},
//popEnterTransition = {
//    slideInHorizontally(
//        initialOffsetX = { -1000 },
//        animationSpec = tween(500)
//    )
//},
//popExitTransition = {
//    slideOutHorizontally(
//        targetOffsetX = { 1000 },
//        animationSpec = tween(500)
//    )
//}


fun NavGraphBuilder.addLogin(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
) {
    composable(
        route = NavigationRoute.Login.route

    ) {
        LoginScreen(scaffoldState = scaffoldState,
            onNextClick = { navController.navigate(NavigationRoute.Dashboard.route) })
    }

}

fun NavGraphBuilder.addDashBoard(
    navController: NavHostController
) {
    composable(
        route = NavigationRoute.Dashboard.route
    ) {
        DashBoardScreen(onNextClick = {navController.navigate(NavigationRoute.Route.route)})
    }
}



fun NavGraphBuilder.addRoute(
    navController: NavHostController
) {
    composable(
        route = NavigationRoute.Route.route
    ) {
        RouteScreen(onGoDetail = { id -> navController.navigate(NavigationRoute.RouteDetail.passId(id))})
    }
}



fun NavGraphBuilder.addRouteDetail(
    navController: NavHostController
) {
    composable(
        route = NavigationRoute.RouteDetail.route,
        arguments = listOf(
            navArgument(ROUTE_DETAIL_ID) {
                type = NavType.IntType
                defaultValue = 0
            }
        )
    ) {
        val id = it.arguments?.getInt(ROUTE_DETAIL_ID)!!
        RouteDetailScreen()
    }
}

