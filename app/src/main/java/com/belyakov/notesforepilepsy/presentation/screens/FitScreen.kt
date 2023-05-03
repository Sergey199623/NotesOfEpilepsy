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
import androidx.compose.ui.unit.IntOffset
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.belyakov.navigation.navigate.BottomNavigationScreens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FitScreen(
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