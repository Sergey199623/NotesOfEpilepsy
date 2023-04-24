package com.belyakov.notesforepilepsy.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.belyakov.notesforepilepsy.R
import com.belyakov.ui.elements.EventItem
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.belyakov.notesforepilepsy.presentation.viewModel.MainViewModel
import com.belyakov.ui.elements.DefaultToolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.IntOffset
import com.belyakov.notesforepilepsy.utils.MeasureDefaultToolbarHeight

@Composable
fun MainScreen(
    onOpenProfile: () -> Unit,
    onSosClicked: () -> Unit,
    onAddNotes: () -> Unit,
    mainViewModel: MainViewModel = viewModel()
) {

    val toolbarHeight = remember { mutableStateOf(0) }

    val dataEvents by mainViewModel.data.collectAsState()
    val itemsListState = rememberLazyListState()

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        MeasureDefaultToolbarHeight { toolbarHeight.value = it }
        Row(
            modifier = Modifier
                .background(Color(android.graphics.Color.parseColor("#FF00BCD4")))
                .align(Alignment.TopCenter),
        ) {
            DefaultToolbar(
                onOpenProfile = onOpenProfile,
                onSosClicked = onSosClicked,
                isMainScreen = true,
                isShowBackIconNeeded = false
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
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

        Row(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            IconButton(
                onClick = { onAddNotes() }
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_notes),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
            }
        }
    }

}