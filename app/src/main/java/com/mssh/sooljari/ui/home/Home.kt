package com.mssh.sooljari.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
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
    Home,
    Search
}

@Composable
fun SetSearchGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable("Home") {
            HomeView(
                onNavigateToSearch = {
                    navController.navigate("Search") {
                        popUpTo("Home") {

                        }
                    }
                }
            )
        }

        composable("Search") {
            SearchView()
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    val search: () -> Unit = {}
    HomeView(search)
}