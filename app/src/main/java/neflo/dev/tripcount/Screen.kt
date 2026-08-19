package neflo.dev.tripcount

sealed class Screen(val route: String) {
    object Login: Screen("login_screen")
    object Signup: Screen("signup_screen")
    object Main: Screen("main_screen")
    object Group: Screen("group_screen")
    object CreateGroup: Screen("create_group_screen")
    object Profile: Screen("profile_screen")
    object EditProfile: Screen("edit_profile_screen")
}