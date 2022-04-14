package gsg.corp.driver_presentation.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.QuestionAnswer
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Message
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import gsg.corp.core_ui.LocalSpacing
import gsg.corp.core_ui.global_components_actions.GlobalOutLineButton
import gsg.corp.driver_domain.model.Route

@ExperimentalCoilApi
@Composable
fun RouteItem(
    route: Route,
    onClick: () -> Unit,
    onCall1: () -> Unit,
    onCall2: () -> Unit,
    onMessage: () -> Unit,
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
        Column(Modifier.padding(top = 16.dp, bottom = 16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
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
                        style = MaterialTheme.typography.h6.copy(fontSize = 15.sp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        modifier = Modifier.width(160.dp),
                        overflow = TextOverflow.Ellipsis)
                    Text(text = "${route.product}",
                        style = MaterialTheme.typography.subtitle1.copy(fontSize = 14.sp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        modifier = Modifier.width(200.dp),
                        overflow = TextOverflow.Ellipsis)
                }

                    IconButton(
                        onClick = onCall1,
                        enabled = route.customerPhone.isNotBlank()
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Call,
                            contentDescription = "Cel1"
                        )
                    }
                    IconButton(
                        onClick = onCall2,
                        enabled = route.customerOtherPhone.isNotBlank()
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Call,
                            contentDescription = "Cel2"
                        )
                    }
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp))
            {
                Text(text = "Distrito: ${route.customerDistrict}")
                Text(text = "Direccion: ${route.customerAddress}")
                Text(text = "Referencia: ${route.customerRef}")
            }
            Row(
                Modifier.fillMaxWidth().padding(start = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Fecha: ${route.dateRoute}")
                IconButton(
                    onClick = onMessage,
                    enabled = route.customerPhone.isNotBlank()
                ) {
                    Icon(
                        imageVector = Icons.Outlined.QuestionAnswer,
                        contentDescription = null
                    )
                }
            }
        }
    }

}