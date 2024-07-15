package com.example.linguaguess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.linguaguess.ui.screens.main.MainScreen
import com.example.linguaguess.ui.naviagation.NavigationRoutes
import com.example.linguaguess.ui.naviagation.authenticatedGraph
import com.example.linguaguess.ui.naviagation.unauthenticatedGraph
import com.example.linguaguess.ui.theme.LinguaGuessTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LinguaGuessTheme {
                MainScreen(startDestination = NavigationRoutes.Authenticated.NavigationRoute.route)
            }
        }
    }
}




