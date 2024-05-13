package com.grckikino.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.grckikino.ui.compose.round.RoundScreen
import com.grckikino.ui.compose.rounds.RoundsScreen
import com.grckikino.ui.compose.webview.LiveScreen

@Composable
fun NavHost(navHostController: NavHostController) {
    androidx.navigation.compose.NavHost(
        navController = navHostController,
        startDestination = ScreensRoute.ROUNDS.name
    ) {
        composable(ScreensRoute.ROUNDS.name) {
            RoundsScreen(navHostController)
        }
        composable(
            route = ScreensRoute.ROUND.name + "?drawId={drawId}",
            arguments = listOf(navArgument("drawId") {
                defaultValue = -1
                type = NavType.IntType
            })
        ) { backStackEntry ->
            RoundScreen(navHostController, backStackEntry.arguments?.getInt("drawId"))
        }
        composable(ScreensRoute.WEBVIEW.name) {
            // You can modify this URL or make it dynamic based on app state
            LiveScreen(navHostController, "https://mozzartbet.com/sr/lotto-animation/26#")
        }
    }
}