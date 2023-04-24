package com.belyakov.notesforepilepsy

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.belyakov.notesforepilepsy.presentation.screens.AddEventScreen
import com.belyakov.notesforepilepsy.presentation.screens.MainScreen
import com.belyakov.notesforepilepsy.presentation.screens.ProfileScreen
import com.belyakov.notesforepilepsy.presentation.viewModel.MainViewModel
import com.belyakov.notesforepilepsy.ui.theme.NotesForEpilepsyTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {

    @SuppressLint("UnrememberedGetBackStackEntry")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val url = this.getString(R.string.firebase_database_url)
            val mainViewModel = MainViewModel(url)

            val navController = rememberNavController()
            NotesForEpilepsyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

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
                                onOpenProfile = { navController.navigate(NavigateTo.PROFILE_SCREEN.value) },
                                onSosClicked = { callEmergency() },
                                onAddNotes = { navController.navigate(NavigateTo.ADD_NOTES_SCREEN.value) },
                                mainViewModel = mainViewModel
                            )
                        }
                        composable(route = NavigateTo.PROFILE_SCREEN.value) { backStackEntry ->
                            ProfileScreen(
                                mainViewModel = mainViewModel,
                                onDataSaved = {
                                    // todo реализовать сохранение данных пользователя на удаленной БД
                                },
                                onBackClicked = { navController.navigateUp() },
                                onSosClicked = { callEmergency() }
                            )
                        }
                        composable(route = NavigateTo.ADD_NOTES_SCREEN.value) { backStackEntry ->
                            AddEventScreen(
                                mainViewModel = mainViewModel,
                                onNotesSaved = { navController.navigate(NavigateTo.MAIN_SCREEN.value) },
                                onBackClicked = { navController.navigateUp() }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun callEmergency() {
        val dialIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:112"))
        // Проверяем, есть ли у приложения разрешение на звонок
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Запрашиваем разрешение на звонок
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CALL_PHONE_PERMISSION)
        } else {
            // Уже есть разрешение на звонок, выполняем звонок
            startActivity(dialIntent)
        }
        try {
            startActivity(dialIntent)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CALL_PHONE_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Разрешение на звонок предоставлено, выполняем звонок
                callEmergency()
            } else {
                // Разрешение на звонок не предоставлено, выводим сообщение об ошибке
                Toast.makeText(this, "Разрешение на звонок не предоставлено", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private companion object {
        const val REQUEST_CALL_PHONE_PERMISSION = 1
    }
}