package gsg.corp.ruterito

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import gsg.corp.driver_presentation.login.LoginScreen
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
                    NavHost( navController = navController,
                        startDestination = Route.LOGIN)
                    {
                        composable(route=Route.LOGIN){
                            LoginScreen(scaffoldState = scaffoldState,
                                onNextClick = { /*TODO*/ })
                        }
                    }

            }
        }
    }
}
