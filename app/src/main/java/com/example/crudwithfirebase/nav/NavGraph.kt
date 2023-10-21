package com.example.crudwithfirebase.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.crudwithfirebase.screen.AddDataScreen
import com.example.crudwithfirebase.screen.GetDataScreen
import com.example.crudwithfirebase.screen.MainScreen
import com.example.crudwithfirebase.util.ShareViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    shareViewModel: ShareViewModel
) {
    NavHost(navController = navController,
            startDestination = Screens.MainScreen.route
    ) {
        composable(
            route = Screens.MainScreen.route
        ) {
            MainScreen(
                navController = navController,)
        }

        composable(
            route = Screens.GetDataScreen.route
        ) {
            GetDataScreen(
                navController = navController,
                shareViewModel = shareViewModel)
        }

        composable(
            route = Screens.AddDataScreen.route
        ) {
            AddDataScreen(
                navController = navController,
                shareViewModel = shareViewModel)
        }
    }

}