package gsg.corp.driver_presentation.route

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import gsg.corp.core_ui.LocalSpacing
import gsg.corp.driver_presentation.dashboard.components.RouteItem

@Composable
fun RouteScreen(
    viewModel: RouteViewModel = hiltViewModel(),
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(start = spacing.spaceMedium, end = spacing.spaceMedium)

        ) {
            item {
                Text(text = "¡Hola Ruterito, estos son tus envios!",
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp)
                )
            }
            items(state.listRoutes) { route ->
                RouteItem(route,
                    onClick = {

                    }
                )
            }
        }
    }

}