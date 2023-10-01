package com.mssh.sooljari

import androidx.compose.runtime.Composable
import com.mssh.sooljari.ui.home.HomeView
import com.mssh.sooljari.ui.theme.SoolJariTheme

@Composable
fun SoolJariApp() {
    SoolJariTheme {
        HomeView()
    }

}