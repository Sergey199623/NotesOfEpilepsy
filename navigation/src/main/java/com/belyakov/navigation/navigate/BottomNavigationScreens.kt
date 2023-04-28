package com.belyakov.navigation.navigate

import androidx.annotation.StringRes
import com.belyakov.navigation.R

sealed class BottomNavigationScreens(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: Int
) {
    object MainScreen :
        BottomNavigationScreens("main_screen", R.string.navigation_main_screen, R.drawable.ic_home)

    object ProfileScreen : BottomNavigationScreens(
        "profile_screen",
        R.string.navigation_profile_screen,
        R.drawable.ic_profile
    )

    object AddEventScreen : BottomNavigationScreens(
        "add_notes_screen",
        R.string.navigation_add_events_screen,
        R.drawable.ic_calendar
    )

    object CourseScreen : BottomNavigationScreens(
        "course_screen",
        R.string.navigation_course_screen,
        R.drawable.ic_capsule
    )

    object RegistrationScreen : BottomNavigationScreens(
        "registration_screen",
        R.string.navigation_registration_screen,
        R.drawable.ic_profile
    )
//
//    object CodeScreen :
//        BottomNavigationScreens("code_screen", R.string.scary_bag_screen_route, Icons.Filled.Cake)
}