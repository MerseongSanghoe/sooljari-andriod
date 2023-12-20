package com.mssh.sooljari.ui.home

import android.app.Application
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.mssh.sooljari.R
import com.mssh.sooljari.model.AlcoholRepository
import com.mssh.sooljari.model.AlcoholViewModel
import com.mssh.sooljari.ui.home.appBar.TopAppBar
import com.mssh.sooljari.ui.home.navigation.NavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    onNavigateToSearch: () -> Unit,
    viewModel: AlcoholViewModel,
    onVerticalCardClick: (Long) -> Unit,
    onNavigateToSearchByQuery: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                onNavigateToSearch,
                onNavigateToSearchByQuery
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .navigationBarsPadding()
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(52.dp),
                shape = CircleShape,
                containerColor = Color.Transparent
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_add_filled),
                    contentDescription = null 
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(colorResource(id = R.color.neutral1))
        ) {
            Feed(
                modifier = Modifier
                    .weight(1f),
                viewModel = viewModel,
                onVerticalCardClick = onVerticalCardClick,
                onNavigateToSearchByQuery = onNavigateToSearchByQuery,
            )
        }
    }
}

enum class HomeSections(
    @StringRes val title: Int,
    val route: String
) {
    CATEGORY(R.string.navi_description_menu, "home/category"),
    FIND(R.string.navi_description_find, "home/find"),
    FEED(R.string.navi_description_home, "home/feed"),
    USER(R.string.navi_description_user, "home/user"),
    REVIEW(R.string.navi_description_review, "home/review")
}

fun NavGraphBuilder.homeGraph(
    navController: NavHostController,
) {

}

@Preview
@Composable
fun HomePreview() {
    HomeView(
        onNavigateToSearch = {},
        viewModel = AlcoholViewModel(AlcoholRepository(), Application()),
        onVerticalCardClick = {},
        onNavigateToSearchByQuery = {}
    )
}