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
import com.belyakov.notesforepilepsy.data.Notes
import com.belyakov.ui.elements.NotesItem
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.belyakov.notesforepilepsy.presentation.viewModel.MainViewModel
import com.belyakov.ui.elements.MainToolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

@Composable
fun MainScreen(
    navController: NavHostController,
    onAddNotes: () -> Unit,
    mainViewModel: MainViewModel = viewModel(),
) {

    val notesList = remember {
        mutableStateListOf<Notes>()
    }
    notesList.addAll(
        listOf(
            Notes("Title 1", "Description 1", "2022-05-01"),
            Notes("Title 2", "Description 2", "2022-05-02"),
            Notes("Title 3", "Description 3", "2022-05-03")
        )
    )
    val itemsListState = rememberLazyListState()

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(
            modifier = Modifier
                .background(Color(android.graphics.Color.parseColor("#FF00BCD4")))
                .align(Alignment.TopCenter),
        ) {
            MainToolbar()
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            // Если пустой список
            if (notesList.isEmpty()) {
                Box(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(text = stringResource(R.string.main_screen_empty_list))
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    state = itemsListState
                ) {
                    items(notesList) { note ->
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
                onClick = { navController.navigate("add_notes_screen")}
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_notes),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp) // Установка размера иконки
                )
            }
        }
    }
}