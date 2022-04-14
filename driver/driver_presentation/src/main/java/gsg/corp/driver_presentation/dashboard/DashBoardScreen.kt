package gsg.corp.driver_presentation.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import gsg.corp.core.Util.UiEvent
import gsg.corp.core_ui.RedGsg

@Composable
fun DashBoardScreen(
    onNextClick: () -> Unit,
    viewModel: DashBoardViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
        ) {

            Text(text = "¡Bienvenido, ${viewModel.name}!",
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp,bottom = 16.dp)
            )
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Card(modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .clickable { },
                    backgroundColor = RedGsg,
                    elevation = 8.dp)//shape = RoundedCornerShape(8.dp)
                {
                    Row(modifier = Modifier.padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.MapsHomeWork,
                            contentDescription = null,
                            Modifier.padding(0.dp)
                        )
                        Text(text = "Recojos", Modifier.padding(start = 4.dp))
                    }
                }
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                        .clickable {viewModel.onNavigationClick(1) },
                    backgroundColor = RedGsg,
                    elevation = 8.dp
                ) {
                    Row(modifier = Modifier.padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.TwoWheeler,
                            contentDescription = null,
                            Modifier.padding(0.dp)
                        )
                        Text(text = "Envíos", Modifier.padding(start = 4.dp))
                    }
                }
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Card(modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .clickable { },
                    elevation = 8.dp)
                {
                    Row(modifier = Modifier.padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.LocationOn,
                            contentDescription = null,
                            Modifier.padding(0.dp)
                        )
                        Text(text = "Express", Modifier.padding(start = 4.dp))
                    }
                }
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                        .clickable { },
                    elevation = 8.dp
                ) {
                    Row(modifier = Modifier.padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.BusinessCenter,
                            contentDescription = null,
                            Modifier.padding(0.dp)
                        )
                        Text(text = "Empresarial", Modifier.padding(start = 4.dp))
                    }
                }
            }
        }
    }

}

@Composable
fun CardRoute() {

}