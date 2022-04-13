package gsg.corp.ruterito

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.*
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import gsg.corp.driver_presentation.dashboard.DashBoardScreen
import gsg.corp.driver_presentation.login.LoginScreen
import gsg.corp.ruterito.navigation.Route
import gsg.corp.ruterito.ui.theme.RuteritoTheme

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RuteritoTheme {
                val navController = rememberAnimatedNavController()
                val scaffoldState = rememberScaffoldState()
                BoxWithConstraints {
                    AnimatedNavHost(navController = navController,
                        startDestination = Route.LOGIN)
                    {
                        addLogin(navController = navController, scaffoldState)
                        addDashBoard(navController = navController, scaffoldState = scaffoldState)
                    }
                }

            }
        }
    }
}



@ExperimentalAnimationApi
fun NavGraphBuilder.addLogin(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
) {
    composable(
        route = Route.LOGIN,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { 1000 },
                animationSpec = tween(500)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -1000 },
                animationSpec = tween(500)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -1000 },
                animationSpec = tween(500)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { 1000 },
                animationSpec = tween(500)
            )
        }

    ) {
        LoginScreen(scaffoldState = scaffoldState,
            onNextClick = { navController.navigate(Route.DASHBOARD) })
    }

}

@ExperimentalAnimationApi
fun NavGraphBuilder.addDashBoard(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
) {
    composable(
        route = Route.DASHBOARD,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { 1000 },
                animationSpec = tween(500)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -1000 },
                animationSpec = tween(500)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -1000 },
                animationSpec = tween(500)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { 1000 },
                animationSpec = tween(500)
            )
        }
    ) {
        DashBoardScreen()
    }
}

