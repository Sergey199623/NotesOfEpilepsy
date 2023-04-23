package com.belyakov.notesforepilepsy.presentation.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.belyakov.notesforepilepsy.presentation.viewModel.MainViewModel

@Composable
fun AddNotesScreen(
    navController: NavHostController,
    mainViewModel: MainViewModel = viewModel(),
    onNotesSaved: () -> Unit,
    onBackClicked: () -> Unit
) {

}