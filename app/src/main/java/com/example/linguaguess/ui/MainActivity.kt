package com.example.linguaguess.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.linguaguess.ui.screens.main.MainScreen
import com.example.linguaguess.ui.naviagation.NavigationRoutes
import com.example.linguaguess.ui.theme.LinguaGuessTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            LinguaGuessTheme {
                MainScreen(startDestination = NavigationRoutes.Unauthenticated.NavigationRoute.route)
            }
        }
    }
}




