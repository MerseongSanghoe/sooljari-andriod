package com.mssh.sooljari

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.mssh.sooljari.ui.home.HomeSections
import com.mssh.sooljari.ui.home.HomeView
import com.mssh.sooljari.ui.home.homeGraph
import com.mssh.sooljari.ui.search.SearchView
import com.mssh.sooljari.ui.theme.SoolJariTheme

@Composable
fun SoolJariApp() {
    SoolJariTheme {
        val sooljariNavController = rememberNavController()
        NavHost(
            navController = sooljariNavController,
            startDestination = SoolJariDestinations.HOME_ROUTE
        ) {
            sooljariGraph(navController = sooljariNavController)
        }
    }

}

object SoolJariDestinations {
    const val HOME_ROUTE = "home"
    const val SEARCH_ROUTE = "search"
}

private fun NavGraphBuilder.sooljariGraph(
    navController: NavHostController
) {
    navigation(
        route = SoolJariDestinations.HOME_ROUTE,
        startDestination = HomeSections.FEED.route
    ) {
        homeGraph(navController)
    }
    composable(SoolJariDestinations.HOME_ROUTE) {
        HomeView(onNavigateToSearch = {
            navController.navigate(SoolJariDestinations.SEARCH_ROUTE) {
                popUpTo(SoolJariDestinations.HOME_ROUTE) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        })
    }

    composable(SoolJariDestinations.SEARCH_ROUTE) {
        SearchView(onNavigateToHome = {
            navController.navigate(SoolJariDestinations.HOME_ROUTE) {
                popUpTo(SoolJariDestinations.SEARCH_ROUTE) {
                    saveState = true
                }
                launchSingleTop = true
            }
        })
    }
}