package com.mssh.sooljari

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.mssh.sooljari.ui.home.HomeView
import com.mssh.sooljari.ui.home.SetSearchGraph
import com.mssh.sooljari.ui.theme.SoolJariTheme

@Composable
fun SoolJariApp() {
    SoolJariTheme {
        val searchNavController = rememberNavController()
        SetSearchGraph(navController = searchNavController)
        //HomeView()
    }

}