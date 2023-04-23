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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.belyakov.notesforepilepsy.presentation.viewModel.MainViewModel
import com.belyakov.ui.elements.DefaultToolbar

@Composable
fun AddNotesScreen(
    navController: NavHostController,
    mainViewModel: MainViewModel = viewModel(),
    onNotesSaved: () -> Unit,
    onBackClicked: () -> Unit
) {
// создаем mutableState переменные для хранения значений полей ввода
    val titleState = remember { mutableStateOf("") }
    val descriptionState = remember { mutableStateOf("") }
    val dateState = remember { mutableStateOf("") }

    // функция для сохранения данных в mainViewModel и перехода на предыдущий экран
    fun onSaveClicked() {
        mainViewModel.addNotes(
            titleState.value,
            descriptionState.value,
            dateState.value
        )
        onNotesSaved()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(
            modifier = Modifier
                .background(Color(android.graphics.Color.parseColor("#FF00BCD4")))
                .align(Alignment.TopCenter),
        ) {
            DefaultToolbar(
                isMainScreen = false,
                isShowBackIconNeeded = true
            )
        }

        Column(
            modifier = Modifier.padding(16.dp)
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