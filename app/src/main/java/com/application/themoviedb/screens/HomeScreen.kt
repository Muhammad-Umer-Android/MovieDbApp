package com.application.themoviedb.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.application.themoviedb.presentation.MovieListViewModel
import com.application.themoviedb.R
import com.application.themoviedb.presentation.MovieListUiEvent
import com.application.themoviedb.presentation.PopularMoviesScreen
import com.application.themoviedb.presentation.UpcomingMoviesScreen
import com.application.themoviedb.utils.Screen


/**
 * @Author: Umer Dev
 * @Created: 03/07/2025
 * @File: HomeScreen.kt
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    val movieListViewModel = hiltViewModel<MovieListViewModel>()
    val movieListState = movieListViewModel.movieListState.collectAsState().value
    val bottomNavController = rememberNavController()
    val saveableStateHolder = rememberSaveableStateHolder()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                bottomNavController = bottomNavController,
                onEvent = movieListViewModel::onEvent
            )
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (movieListState.isCurrentPopularScreen)
                            stringResource(R.string.popular_movies)
                        else
                            stringResource(R.string.upcoming_movies),
                        fontSize = 20.sp
                    )
                },
                modifier = Modifier.shadow(2.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    MaterialTheme.colorScheme.inverseOnSurface
                )
            )
        }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NavHost(
                navController = bottomNavController,
                startDestination = Screen.PopularMovieList.rout
            ) {
                composable(Screen.PopularMovieList.rout) {
                    saveableStateHolder.SaveableStateProvider(Screen.PopularMovieList.rout) {
                        PopularMoviesScreen(
                            navController = navController,
                            movieListState = movieListState,
                            onEvent = movieListViewModel::onEvent,
                        )
                    }

                }
                composable(Screen.UpcomingMovieList.rout) {
                    saveableStateHolder.SaveableStateProvider(Screen.UpcomingMovieList.rout) {
                        UpcomingMoviesScreen(
                            navController = navController,
                            movieListState = movieListState,
                            onEvent = movieListViewModel::onEvent
                        )
                    }

                }
            }
        }
    }

}


@Composable
fun BottomNavigationBar(
    bottomNavController: NavHostController, onEvent: (MovieListUiEvent) -> Unit
) {

    val items = listOf(
        BottomItem(
            title = stringResource(R.string.popular),
            icon = Icons.Rounded.Movie
        ), BottomItem(
            title = stringResource(R.string.upcoming),
            icon = Icons.Rounded.Upcoming
        )
    )

    val selected = rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            items.forEachIndexed { index, bottomItem ->
                NavigationBarItem(
                    selected = selected.intValue == index,
                    onClick = {
                        selected.intValue = index
                        when (selected.intValue) {
                            /*0 -> {
                                onEvent(MovieListUiEvent.Navigate)
//                            bottomNavController.popBackStack()
                                bottomNavController.navigate(Screen.PopularMovieList.rout)
                            }

                            1 -> {
                                onEvent(MovieListUiEvent.Navigate)
//                            bottomNavController.popBackStack()
                                bottomNavController.navigate(Screen.UpcomingMovieList.rout)
                            }*/

                            0 -> {
                                onEvent(MovieListUiEvent.Navigate)
                                bottomNavController.navigate(Screen.PopularMovieList.rout) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(bottomNavController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                }

                            }

                            1 -> {
                                onEvent(MovieListUiEvent.Navigate)
                                bottomNavController.navigate(Screen.UpcomingMovieList.rout) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(bottomNavController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                }

                            }
                        }
                    }, icon = {
                        Icon(
                            imageVector = bottomItem.icon,
                            contentDescription = bottomItem.title,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }, label = {
                        Text(
                            text = bottomItem.title, color = MaterialTheme.colorScheme.onBackground
                        )
                    })
            }
        }
    }

}

data class BottomItem(
    val title: String, val icon: ImageVector
)