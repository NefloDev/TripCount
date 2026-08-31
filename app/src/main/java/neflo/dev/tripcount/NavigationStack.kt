package neflo.dev.tripcount

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import neflo.dev.tripcount.api.viewModel.AuthenticationViewModel
import neflo.dev.tripcount.screens.LoginScreen
import neflo.dev.tripcount.screens.MainScreen

@Composable
fun NavigationStack(sharedPreferences: SharedPreferences) {
    val navController = rememberNavController()
    val authVM: AuthenticationViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(route = Screen.Login.route) {
            LoginScreen(sharedPreferences, navController, authVM)
        }
        composable(route = Screen.Main.route) {
            MainScreen(sharedPreferences, navController)
        }
    }
}