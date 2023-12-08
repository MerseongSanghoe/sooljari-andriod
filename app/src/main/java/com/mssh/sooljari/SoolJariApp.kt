package com.mssh.sooljari

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.mssh.sooljari.model.AlcoholRepository
import com.mssh.sooljari.model.AlcoholViewModel
import com.mssh.sooljari.model.AlcoholViewModelFactory
import com.mssh.sooljari.ui.detail.AlcoholDetailView
import com.mssh.sooljari.ui.home.HomeSections
import com.mssh.sooljari.ui.home.HomeView
import com.mssh.sooljari.ui.home.homeGraph
import com.mssh.sooljari.ui.search.SearchView
import com.mssh.sooljari.ui.theme.SoolJariTheme

@Composable
fun SoolJariApp() {
    SoolJariTheme {
        val viewModel: AlcoholViewModel =
            viewModel(factory = AlcoholViewModelFactory(AlcoholRepository()))
        viewModel.login("testandroid", "catdog09321")

        val sooljariNavController = rememberNavController()
        NavHost(
            navController = sooljariNavController,
            startDestination = SoolJariDestinations.HOME_ROUTE
        ) {
            sooljariGraph(
                navController = sooljariNavController,
                viewModel = viewModel
            )
        }
    }

}

object SoolJariDestinations {
    const val HOME_ROUTE = "home"
    const val SEARCH_ROUTE = "search"
    const val SEARCH_WITH_QUERY_ROUTE = "search/{query}"
    const val ALCOHOL_DETAIL_ROUTE = "alcoholDetail/{alcoholId}"
}

private fun NavGraphBuilder.sooljariGraph(
    navController: NavHostController,
    viewModel: AlcoholViewModel
) {
    navigation(
        route = SoolJariDestinations.HOME_ROUTE,
        startDestination = HomeSections.FEED.route
    ) {
        homeGraph(navController)
    }
    composable(SoolJariDestinations.HOME_ROUTE) {
        HomeView(
            onNavigateToSearch = {
                navController.navigate(SoolJariDestinations.SEARCH_ROUTE) {
                    popUpTo(SoolJariDestinations.HOME_ROUTE) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }

                //검색 기록 초기화
                viewModel.resetSearchResult()
            },
            viewModel = viewModel,
            onVerticalCardClick = { alcoholId ->
                navController.navigate("alcoholDetail/$alcoholId") {
                    popUpTo(SoolJariDestinations.HOME_ROUTE) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            onNavigateToSearchByQuery = { query ->
                navController.navigate("search/$query") {
                    popUpTo(SoolJariDestinations.HOME_ROUTE) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }

    composable(SoolJariDestinations.SEARCH_ROUTE) {
        SearchView(
            initialQuery = "",
            onNavigateToHome = {
                navController.navigate(SoolJariDestinations.HOME_ROUTE) {
                    popUpTo(SoolJariDestinations.SEARCH_ROUTE) {
                        saveState = true
                    }
                    launchSingleTop = true
                }
            },
            onResultCardClick = { alcoholId ->
                navController.navigate("alcoholDetail/$alcoholId") {
                    popUpTo(SoolJariDestinations.SEARCH_ROUTE) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            viewModel = viewModel
        )
    }

    composable(
        route = SoolJariDestinations.SEARCH_WITH_QUERY_ROUTE,
        arguments = listOf(navArgument("query") { type = NavType.StringType })
    ) {backStackEntry ->
        val query = backStackEntry.arguments?.getString("query") ?: ""
        SearchView(
            initialQuery = query,
            onNavigateToHome = {
                navController.navigate(SoolJariDestinations.HOME_ROUTE) {
                    popUpTo(SoolJariDestinations.SEARCH_ROUTE) {
                        saveState = true
                    }
                    launchSingleTop = true
                }
            },
            onResultCardClick = { alcoholId ->
                navController.navigate("alcoholDetail/$alcoholId") {
                    popUpTo(SoolJariDestinations.SEARCH_ROUTE) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            viewModel = viewModel
        )
    }

    composable(
        route = SoolJariDestinations.ALCOHOL_DETAIL_ROUTE,
        arguments = listOf(navArgument("alcoholId") { type = NavType.LongType })
    ) { backStackEntry ->
        val alcoholId = backStackEntry.arguments?.getLong("alcoholId")
        alcoholId?.let {
            Log.d("SoolJariApp", "alcoholId: $alcoholId")

            AlcoholDetailView(
                alcoholId = it,
                viewModel = viewModel,
                onBackButtonClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}