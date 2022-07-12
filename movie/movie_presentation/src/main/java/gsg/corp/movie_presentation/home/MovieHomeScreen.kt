package gsg.corp.movie_presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import gsg.corp.core_ui.LocalSpacing
import gsg.corp.movie_domain.model.Movie
import gsg.corp.movie_presentation.home.components.MovieItem

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieHomeScreen(
    scaffoldState: ScaffoldState,
    onDetail: (Int) -> Unit,
    viewModel: MovieHomeViewModel = hiltViewModel(),
) {
    val spacing = LocalSpacing.current
    val state = viewModel.movies
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        val moviesList: LazyPagingItems<Movie> = state.collectAsLazyPagingItems()
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(start = spacing.spaceMedium, end = spacing.spaceMedium)

        ) {
            item {
                Text(text = "¡Hola Cinéfilo!, que veras hoy?",
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp)
                )
            }
            items(moviesList) { item ->
                item?.let {
                    MovieItem(it, onClick = {
                        // go to detail
                    })
                }
            }
        }
    }

}