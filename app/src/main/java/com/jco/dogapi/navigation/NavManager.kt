package com.jco.dogapi.navigation

import StoreDarkMode
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jco.dogapi.viewModel.DogViewModel
import com.jco.dogapi.views.DogScreen
import com.jco.dogapi.views.FavoriteImages

@Composable
fun NavManager(viewModel: DogViewModel, darkModeStore: StoreDarkMode, darkMode:Boolean){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            DogScreen(navController, viewModel, darkModeStore, darkMode)
        }
        composable("favorites") {
            FavoriteImages(navController, viewModel, darkModeStore, darkMode)
        }
    }
}