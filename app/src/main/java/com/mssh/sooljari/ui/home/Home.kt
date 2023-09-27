package com.mssh.sooljari.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.mssh.sooljari.ui.home.appBar.TopAppBar
import com.mssh.sooljari.ui.home.navigation.NavigationBar

@Composable
fun HomeView() {
    Column() {
        TopAppBar()
        Feed()
        NavigationBar()
    }
}