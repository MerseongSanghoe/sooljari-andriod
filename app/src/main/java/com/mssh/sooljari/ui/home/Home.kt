package com.mssh.sooljari.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mssh.sooljari.ui.SearchView
import com.mssh.sooljari.ui.home.appBar.TopAppBar
import com.mssh.sooljari.ui.home.navigation.NavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    onNavigateToSearch: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(onNavigateToSearch)
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .navigationBarsPadding()
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Feed(
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

enum class SearchSections(

) {
    HOME,
    SEARCH
}

@Composable
fun SetSearchGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = SearchSections.HOME.name
    ) {
        composable(SearchSections.HOME.name) {
            HomeView(
                onNavigateToSearch = {
                    navController.navigate(SearchSections.SEARCH.name) {
                        popUpTo(SearchSections.HOME.name)

                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable("Search") {
            SearchView(
                onNavigateToHome = {
                    navController.navigate(SearchSections.HOME.name) {
                        popUpTo(SearchSections.HOME.name) {
                            inclusive = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    val search: () -> Unit = {}
    HomeView(search)
}