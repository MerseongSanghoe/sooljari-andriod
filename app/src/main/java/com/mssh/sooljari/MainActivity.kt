package com.mssh.sooljari

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        applicationContext

        super.onCreate(savedInstanceState)

        //status bar까지 화면 늘려주기
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            SoolJariApp(this.application)
        }
    }
}