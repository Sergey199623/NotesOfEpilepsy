package com.belyakov.notesforepilepsy

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.belyakov.notesforepilepsy.presentation.screens.MainScreen
import com.belyakov.notesforepilepsy.presentation.viewModel.SharedMainViewModel
import com.belyakov.notesforepilepsy.ui.theme.NotesForEpilepsyTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnrememberedGetBackStackEntry")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = getString(R.string.firebase_database_url)

        val sharedMainViewModel = SharedMainViewModel(url)
//        val sharedAuthViewModel = AuthViewModel()

        setContent {
            val navController = rememberNavController()
            NotesForEpilepsyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (true) {
                        MainScreen(
                            navController = navController,
                        )
                    } else {
//
                    }

//                    NavHost(
//                        navController = navController,
//                        startDestination = NavigateTo.MAIN_SCREEN.value
//                    ) {
//                        composable(route = NavigateTo.LOGIN_SCREEN.value) {
//                        }
//                        composable(route = NavigateTo.CODE_SCREEN.value) {
//                        }
//                        composable(route = NavigateTo.REGISTRATION_SCREEN.value) {
//                        }
//                        composable(route = NavigateTo.MAIN_SCREEN.value) {
//                            MainScreen(
//                                navController = navController,
//                                onOpenProfile = { navController.navigate(NavigateTo.PROFILE_SCREEN.value) },
//                                onSosClicked = {
//                                    // callEmergency()
//                                },
//                                onAddNotes = { navController.navigate(NavigateTo.ADD_NOTES_SCREEN.value) },
//                                sharedMainViewModel = sharedMainViewModel
//                            )
//                        }
//                        composable(route = NavigateTo.PROFILE_SCREEN.value) { backStackEntry ->
//                            ProfileScreen(
//                                sharedMainViewModel = sharedMainViewModel,
//                                onDataSaved = {
//                                    // todo реализовать сохранение данных пользователя на удаленной БД
//                                },
//                                onBackClicked = { navController.navigateUp() },
//                                onSosClicked = {
////                                    callEmergency()
//                                }
//                            )
//                        }
//                        composable(route = NavigateTo.ADD_NOTES_SCREEN.value) { backStackEntry ->
//                            AddEventScreen(
//                                sharedMainViewModel = sharedMainViewModel,
//                                onNotesSaved = { navController.navigate(NavigateTo.MAIN_SCREEN.value) },
//                                onBackClicked = { navController.navigateUp() }
//                            )
//                        }
//                    }
                }
            }
        }
    }

//    private fun callEmergency() {
//        val dialIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:112"))
//        // Проверяем, есть ли у приложения разрешение на звонок
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            // Запрашиваем разрешение на звонок
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CALL_PHONE_PERMISSION)
//        } else {
//            // Уже есть разрешение на звонок, выполняем звонок
//            startActivity(dialIntent)
//        }
//        try {
//            startActivity(dialIntent)
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//        }
//    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CALL_PHONE_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Разрешение на звонок предоставлено, выполняем звонок
//                callEmergency()
            } else {
                // Разрешение на звонок не предоставлено, выводим сообщение об ошибке
                Toast.makeText(
                    this,
                    this.getString(R.string.toast_call_permission),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private companion object {
        const val REQUEST_CALL_PHONE_PERMISSION = 1
    }
}