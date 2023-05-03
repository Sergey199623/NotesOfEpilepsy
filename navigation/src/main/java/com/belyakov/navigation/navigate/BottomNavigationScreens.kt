package com.belyakov.navigation.navigate

import androidx.annotation.StringRes
import com.belyakov.navigation.R

sealed class BottomNavigationScreens(
    val route: String,
    @StringRes val resourceId: Int
) {
    object MainScreen :
        BottomNavigationScreens("main_screen", R.string.navigation_main_screen)

    object EventScreen :
        BottomNavigationScreens("event_screen", R.string.navigation_main_screen)

    object ProfileScreen : BottomNavigationScreens(
        "profile_screen",
        R.string.navigation_profile_screen,
    )

    object AddEventScreen : BottomNavigationScreens(
        "add_notes_screen",
        R.string.navigation_add_events_screen,
    )

    object ConfirmCodeScreen : BottomNavigationScreens(
        "confirm_code_screen",
        R.string.navigation_confirm_code_screen,
    )

    object CourseScreen : BottomNavigationScreens(
        "course_screen",
        R.string.navigation_course_screen,
    )

    object RegistrationScreen : BottomNavigationScreens(
        "registration_screen",
        R.string.navigation_registration_screen,
    )

    object AuthScreen : BottomNavigationScreens(
        "authentication_screen",
        R.string.navigation_authentication_screen,
    )

    object MedicinesScreen : BottomNavigationScreens(
        route = "medicines_screen",
        R.string.navigation_medicines_screen
    )

    object CalendarScreen : BottomNavigationScreens(
        route = "calendar_screen",
        R.string.navigation_calendar_screen
    )
}