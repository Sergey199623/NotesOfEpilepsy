package com.belyakov.notesforepilepsy.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.belyakov.notesforepilepsy.R
import com.belyakov.notesforepilepsy.data.Events
import com.belyakov.ui.elements.NotesItem
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.belyakov.notesforepilepsy.presentation.viewModel.MainViewModel
import com.belyakov.ui.elements.DefaultToolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

@Composable
fun MainScreen(
    navController: NavHostController,
    onOpenProfile: () -> Unit,
    onSosClicked: () -> Unit,
    onAddNotes: () -> Unit,
    mainViewModel: MainViewModel = viewModel(),
) {

    val eventsList = remember {
        mutableStateListOf<Events>()
    }
    val itemsListState = rememberLazyListState()

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
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
                .align(Alignment.Center)
        ) {
            // Если пустой список
            if (eventsList.isEmpty()) {
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
                    items(eventsList) { note ->
                        NotesItem(
                            title = note.title,
                            description = note.description,
                            createdAt = note.dateOfCreate
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