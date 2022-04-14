package gsg.corp.driver_presentation.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import gsg.corp.core_ui.LocalSpacing
import gsg.corp.driver_domain.model.Route

@ExperimentalCoilApi
@Composable
fun RouteItem(
    route: Route,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(spacing.spaceExtraSmall)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(5.dp)
            )
            .background(MaterialTheme.colors.surface)
            .clickable { onClick() }
            .padding(end = spacing.spaceMedium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(
                    data = null,
                    builder = {
                        crossfade(true)
                        error(gsg.corp.core.R.drawable.ic_logo_gsg)
                        fallback(gsg.corp.core.R.drawable.ic_logo_gsg)
                    }
                ),
                contentDescription = route.customer,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(topStart = 5.dp))
            )
            Column() {
                Text(text = "${route.customer}",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black)
                Text(text = "Producto: ${route.product}",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black)
            }

        }
        Column( modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp))
        {
            Text(text = "Distrito: ${route.customerDistrict}")
            Text(text = "Direccion: ${route.customerAddress}")
            Text(text = "Referencia: ${route.customerRef}")
        }

    }

}