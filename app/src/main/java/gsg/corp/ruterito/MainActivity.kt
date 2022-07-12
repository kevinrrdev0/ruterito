package gsg.corp.ruterito

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
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
import gsg.corp.movie_presentation.detail.MovieDetailScreen
import gsg.corp.movie_presentation.home.MovieHomeScreen
import gsg.corp.ruterito.navigation.NavigationRoute
import gsg.corp.ruterito.navigation.ROUTE_DETAIL_ID
import gsg.corp.ruterito.navigation.Route
import gsg.corp.ruterito.ui.theme.RuteritoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RuteritoTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState) {
                    NavHost(navController = navController,
                        startDestination = NavigationRoute.Login.route)
                    {
                        addLogin(navController = navController, scaffoldState)
                        addDashBoard(navController = navController)
                        addRoute(navController = navController)
                        addRouteDetail(navController = navController)
                        //all navigation for movie module

                        addMovieHome(navController,scaffoldState)
                        addMovieDetail(navController)
                    }
                }


            }
        }
    }
}

fun NavGraphBuilder.addLogin(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
) {
    composable(
        route = NavigationRoute.Login.route

    ) {
        LoginScreen(scaffoldState = scaffoldState,
            onNextClick = { navController.navigate(NavigationRoute.MovieHome.route) })
    }

}

//fun NavGraphBuilder.addMovieLogin(
//    navController: NavHostController,
//    scaffoldState: ScaffoldState,
//) {
//    composable(
//        route = NavigationRoute.MovieLogin.route
//
//    ) {
//        MovieLoginScreen(scaffoldState = scaffoldState,
//            onNextClick = { navController.navigate(NavigationRoute.MovieHome.route) })
//    }
//}

fun NavGraphBuilder.addMovieHome(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
) {
    composable(
        route = NavigationRoute.MovieHome.route
    ) {
        MovieHomeScreen(scaffoldState = scaffoldState,
            onDetail = { id -> navController.navigate(NavigationRoute.MovieDetail.passId(id)) })
    }
}

fun NavGraphBuilder.addMovieDetail(
    navController: NavHostController
) {
    composable(
        route = NavigationRoute.MovieDetail.route,
        arguments = listOf(
            navArgument(ROUTE_DETAIL_ID) {
                type = NavType.IntType
                defaultValue = 0
            }
        )
    ) {
        MovieDetailScreen()
    }
}

fun NavGraphBuilder.addDashBoard(
    navController: NavHostController,
) {
    composable(
        route = NavigationRoute.Dashboard.route
    ) {
        DashBoardScreen(onNextClick = { navController.navigate(NavigationRoute.Route.route) })
    }
}


fun NavGraphBuilder.addRoute(
    navController: NavHostController,
) {
    composable(
        route = NavigationRoute.Route.route
    ) {
        RouteScreen(onGoDetail = { id ->
            navController.navigate(NavigationRoute.RouteDetail.passId(id))
        })
    }
}


fun NavGraphBuilder.addRouteDetail(
    navController: NavHostController,
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
        //val id = it.arguments?.getInt(ROUTE_DETAIL_ID)!!
        RouteDetailScreen()
    }
}

