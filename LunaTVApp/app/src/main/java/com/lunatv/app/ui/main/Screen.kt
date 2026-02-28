package com.lunatv.app.ui.main

sealed class Screen(val route: String, val label: String) {
    object Home : Screen("home", "首页")
    object Movies : Screen("movies", "影视")
    object Live : Screen("live", "直播")
    object Profile : Screen("profile", "我的")
}

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem(
        route = Screen.Home.route,
        label = Screen.Home.label,
        icon = androidx.compose.material.icons.Icons.Default.Home
    ),
    BottomNavItem(
        route = Screen.Movies.route,
        label = Screen.Movies.label,
        icon = androidx.compose.material.icons.Icons.Default.Movie
    ),
    BottomNavItem(
        route = Screen.Live.route,
        label = Screen.Live.label,
        icon = androidx.compose.material.icons.Icons.Default.LiveTv
    ),
    BottomNavItem(
        route = Screen.Profile.route,
        label = Screen.Profile.label,
        icon = androidx.compose.material.icons.Icons.Default.Person
    )
)
