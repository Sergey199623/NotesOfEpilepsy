package com.belyakov.notesforepilepsy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.belyakov.notesforepilepsy.presentation.screens.AddNotesScreen
import com.belyakov.notesforepilepsy.presentation.screens.MainScreen
import com.belyakov.notesforepilepsy.presentation.viewModel.MainViewModel
import com.belyakov.notesforepilepsy.ui.theme.NotesForEpilepsyTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedGetBackStackEntry")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NotesForEpilepsyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    AuthNavGraph(navController = navController)
                    val mainViewModel = MainViewModel()

                    NavHost(
                        navController = navController,
                        startDestination = NavigateTo.MAIN_SCREEN.value
                    ) {
                        composable(route = NavigateTo.LOGIN_SCREEN.value) {
//            LoginScreen(
//                authViewModel,
//                navController
//            )
                        }
                        composable(route = NavigateTo.CODE_SCREEN.value) {
//                            val currentViewModel = navController.getBackStackEntry(NavigateTo.LOGIN_SCREEN.value)
//            CodeScreen(
//                viewModel(currentViewModel),
//                navController
//            )
                        }
                        composable(route = NavigateTo.REGISTRATION_SCREEN.value) {
//                            val currentViewModel = navController.getBackStackEntry(NavigateTo.CODE_SCREEN.value)
//            RegistrationScreen(
//                viewModel(currentViewModel),
//                navController
//            )
                        }
                        composable(route = NavigateTo.MAIN_SCREEN.value) {
                            MainScreen(
                                navController = navController,
                                onAddNotes = { navController.navigate(NavigateTo.ADD_NOTES_SCREEN.value) },
                                mainViewModel = mainViewModel
                            )
                        }
                        composable(route = NavigateTo.ADD_NOTES_SCREEN.value) { backStackEntry ->
                            val currentViewModel = remember(backStackEntry) {
                                navController.getBackStackEntry(NavigateTo.MAIN_SCREEN.value)
                            }
                            AddNotesScreen(
                                navController = navController,
                                mainViewModel = viewModel(currentViewModel),
                                onNotesSaved = { navController.navigate( NavigateTo.MAIN_SCREEN.value) },
                                onBackClicked = { navController.navigateUp() }
                            )
                        }
                    }
                }
            }
        }
    }
}