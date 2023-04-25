package com.belyakov.notesforepilepsy.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.belyakov.notesforepilepsy.presentation.viewModel.SharedMainViewModel
import com.belyakov.ui.elements.DefaultToolbar
import com.belyakov.notesforepilepsy.utils.MeasureDefaultToolbarHeight

@Composable
fun AddEventScreen(
    sharedMainViewModel: SharedMainViewModel = viewModel(),
    onNotesSaved: () -> Unit,
    onBackClicked: () -> Unit
) {
    val titleState = remember { mutableStateOf("") }
    val descriptionState = remember { mutableStateOf("") }
    val dateState = remember { mutableStateOf("") }
    val toolbarHeight = remember { mutableStateOf(0) }

        // функция для сохранения данных в mainViewModel и перехода на предыдущий экран
    fun onSaveClicked() {
        sharedMainViewModel.addNotes(
            titleState.value,
            descriptionState.value,
            dateState.value
        )
        onNotesSaved()
    }

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
                onBackClosed = onBackClicked,
                isMainScreen = false,
                isShowBackIconNeeded = true
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(constraints)

                    layout(placeable.width, placeable.height) {
                        placeable.placeRelative(IntOffset(0, toolbarHeight.value))
                    }
                }
        ) {
            // поле для ввода названия события
            TextField(
                value = titleState.value,
                onValueChange = { titleState.value = it },
                label = { Text("Название события") },
                maxLines = 1,
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // поле для ввода описания события
            TextField(
                value = descriptionState.value,
                onValueChange = { descriptionState.value = it },
                label = { Text("Симптомы") },
                maxLines = 10,
                modifier = Modifier.fillMaxWidth()
            )

            // поле для ввода даты создания
            TextField(
                value = dateState.value,
                onValueChange = { dateState.value = it },
                label = { Text("Дата создания") },
                maxLines = 1,
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // кнопка сохранения
            Button(
                onClick = { onSaveClicked() },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Сохранить")
            }
        }
    }
}