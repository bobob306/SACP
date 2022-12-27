sealed class Screen (val route: String, val arguments: String? = null) {
    object MainScreen : Screen("main_screen")
    object AuthScreen : Screen("auth_screen")
    object OAuthScreen : Screen("oauth_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}