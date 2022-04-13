package gsg.corp.driver_presentation.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DashBoardScreen() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
        ){
            Row {
                Card(modifier = Modifier
                    .padding(end = 24.dp)
                    .clickable {  },
                elevation = 8.dp,
                shape = RoundedCornerShape(8.dp))
                {
                    Column() {

                    }
                }

            }
        }
    }

}