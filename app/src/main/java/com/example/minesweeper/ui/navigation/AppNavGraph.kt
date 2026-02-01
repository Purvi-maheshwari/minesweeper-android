package com.example.minesweeper.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.minesweeper.data.session.SessionManager
import com.example.minesweeper.ui.screen.game.GameScreen
import com.example.minesweeper.ui.screen.home.HomeScreen
import com.example.minesweeper.ui.screen.login.LoginScreen

@Composable
fun AppNavGraph() {

    val context = LocalContext.current
    val sessionManager = SessionManager(context)

    val navController = rememberNavController()

    val startDestination =
        if (sessionManager.isLoggedIn()) Routes.HOME else Routes.LOGIN

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(route = Routes.LOGIN) {
            LoginScreen { username ->
                sessionManager.saveLogin(username)
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.LOGIN) { inclusive = true }
                }
            }
        }

        composable(route = Routes.HOME) {
            HomeScreen(
                username = sessionManager.getUsername(),
                bestTimeText = "—", // will be dynamic later
                onStartGame = { difficulty ->
                    navController.navigate("${Routes.GAME}/$difficulty")
                },
                onLogout = {
                    sessionManager.logout()
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = "${Routes.GAME}/{difficulty}",
            arguments = listOf(
                navArgument("difficulty") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val difficulty =
                backStackEntry.arguments?.getString("difficulty") ?: "EASY"

            GameScreen(
                difficulty = difficulty,
                onHomeClick = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                }
            )

        }
    }
}
