package com.belyakov.notesforepilepsy.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.belyakov.notesforepilepsy.R
import com.belyakov.ui.elements.EventItem
import androidx.compose.ui.graphics.Color
import com.belyakov.notesforepilepsy.presentation.viewModel.SharedMainViewModel
import com.belyakov.ui.elements.DefaultToolbar
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.belyakov.navigation.navigate.BottomNavigationScreens
import com.belyakov.ui.theme.PrimaryBackgroundColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EventScreen(
    navController: NavHostController,
    sharedMainViewModel: SharedMainViewModel = viewModel()
) {
    val toolbarHeight = remember { mutableStateOf(0) }

    val dataEvents by sharedMainViewModel.data.collectAsState()
    val itemsListState = rememberLazyListState()
    var tabBarSize by remember { mutableStateOf(Size.Zero) }

    val bottomNavigationItems = listOf(
        BottomNavigationScreens.AddEventScreen,
        BottomNavigationScreens.MainScreen,
        BottomNavigationScreens.ProfileScreen,
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .background(Color(android.graphics.Color.parseColor("#FF00BCD4")))
            ) {
                DefaultToolbar(
                    title = com.belyakov.navigation.R.string.navigation_event_screen,
                    isNeedShowBackBtn = true,
                    isNeedShowProfileBtn = true
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(navController, bottomNavigationItems)
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(constraints)

                    layout(placeable.width, placeable.height) {
                        placeable.placeRelative(IntOffset(0, toolbarHeight.value))
                    }
                }
        ) {
            // Если пустой список
            if (dataEvents.isEmpty()) {
                Box(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(text = stringResource(R.string.main_screen_empty_list))
                }
            } else {
                LazyColumn(
                    modifier = Modifier.align(Alignment.CenterStart),
                    state = itemsListState
                ) {
                    items(dataEvents) { event ->
                        EventItem(
                            title = event.title,
                            description = event.description,
                            createdAt = event.dateOfCreate,
                            key = event.id
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomNavigationBar(
    navController: NavHostController,
    items: List<BottomNavigationScreens>
) {
    BottomNavigation(
        backgroundColor = PrimaryBackgroundColor
    ) {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = screen.icon), contentDescription = null) },
                label = { Text(stringResource(id = screen.resourceId)) },
                selected = currentRoute == screen.route,
                alwaysShowLabel = false, // This hides the title for the unselected items
                onClick = {
                    // This if check gives us a "singleTop" behavior where we do not create a
                    // second instance of the composable if we are already on that destination
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
}

private const val KEY_ROUTE = "route"