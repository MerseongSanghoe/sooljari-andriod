package com.mssh.sooljari

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.mssh.sooljari.ui.home.appBar.TopAppBar
import com.mssh.sooljari.ui.home.navigation.NavigationBar
import com.mssh.sooljari.ui.theme.SoolJariTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //status bar까지 화면 늘려주기
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            SoolJariTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    SoolJariApp()
                }
            }
        }
    }
}