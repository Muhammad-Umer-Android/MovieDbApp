package com.application.themoviedb.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.application.themoviedb.presentation.components.MovieItem
import com.application.themoviedb.utils.Category
import com.application.themoviedb.utils.isScrollingUp

/**
 * @Author: Umer Dev
 * @Created: 03/07/2025
 * @File: PopularMoviesScreen.kt
 */
@Composable
fun PopularMoviesScreen(
    movieListState: MovieListState,
    navController: NavHostController,
    onEvent: (MovieListUiEvent) -> Unit
) {
    val listState = rememberSaveable(saver = LazyGridState.Saver) {
        LazyGridState()
    }

    /*LaunchedEffect(listState) {
        var previousOffset = 0
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .collect { currentOffset ->
                val isScrollingDown = currentOffset > previousOffset
                val isScrollingUp = currentOffset < previousOffset
                previousOffset = currentOffset

                if (isScrollingDown) {
                    onScrollDirectionChange(false)
                } else if (isScrollingUp) {
                    onScrollDirectionChange(true)
                }
            }
    }*/

    if (movieListState.popularMovieList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ) {
            items(movieListState.popularMovieList.size) { index ->
                MovieItem(
                    movie = movieListState.popularMovieList[index],
                    navHostController = navController
                )
                Spacer(modifier = Modifier.height(16.dp))

                if (index >= movieListState.popularMovieList.size - 1 && !movieListState.isLoading) {
                    onEvent(MovieListUiEvent.Paginate(Category.POPULAR))
                }
            }
        }
    }
}
