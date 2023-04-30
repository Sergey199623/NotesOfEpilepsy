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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.belyakov.auth.AuthScreen
import com.belyakov.auth.ConfirmCodeScreen
import com.belyakov.auth.RegistrationScreen
import com.belyakov.auth.presentation.SharedAuthViewModel
import com.belyakov.navigation.navigate.BottomNavigationScreens
import com.belyakov.notesforepilepsy.presentation.screens.AddEventScreen
import com.belyakov.notesforepilepsy.presentation.screens.EventScreen
import com.belyakov.notesforepilepsy.presentation.screens.MainScreen
import com.belyakov.notesforepilepsy.presentation.screens.ProfileScreen
import com.belyakov.notesforepilepsy.presentation.viewModel.SharedMainViewModel
import com.belyakov.notesforepilepsy.ui.theme.NotesForEpilepsyTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnrememberedGetBackStackEntry")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedMainViewModel = SharedMainViewModel(getString(R.string.firebase_database_url))
        val sharedAuthViewModel = SharedAuthViewModel(getString(R.string.firebase_database_url))
        val isHasAuth = false

        setContent {
            val navController = rememberNavController()
            NotesForEpilepsyTheme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    NavHost(
                        navController = navController,
                        startDestination = if (!isHasAuth) BottomNavigationScreens.MainScreen.route else BottomNavigationScreens.AuthScreen.route
                    ) {
                        composable(route = BottomNavigationScreens.RegistrationScreen.route) {
                            RegistrationScreen(
                                navController = navController,
                                viewModel = sharedAuthViewModel
                            )
                        }
                        composable(route = BottomNavigationScreens.AuthScreen.route) {
                            AuthScreen(
                                navController = navController,
                                viewModel = sharedAuthViewModel,
                                onPhoneNumberClicked = { navController.navigate(BottomNavigationScreens.ConfirmCodeScreen.route) }
                            )
                        }
                        composable(route = BottomNavigationScreens.ConfirmCodeScreen.route) {
                            ConfirmCodeScreen(
                                navController = navController,
                                viewModel = sharedAuthViewModel,
                                onConfirmCode = {
                                    navController.navigate(BottomNavigationScreens.MainScreen.route)

                                }
                            )
                        }
                        composable(route = BottomNavigationScreens.MainScreen.route) {
                            MainScreen(
                                navController = navController,
                                onEventsClicked = { navController.navigate(BottomNavigationScreens.EventScreen.route) },
                                onProfileClicked = { navController.navigate(BottomNavigationScreens.ProfileScreen.route) },
                                onSosClicked = {
                                    // callEmergency()
                                },
                                sharedMainViewModel = sharedMainViewModel
                            )
                        }
                        composable(route = BottomNavigationScreens.ProfileScreen.route) { backStackEntry ->
                            ProfileScreen(
                                sharedMainViewModel = sharedMainViewModel,
                                onDataSaved = {
                                    // todo реализовать сохранение данных пользователя на удаленной БД
                                },
                                onBackClicked = { navController.navigateUp() },
                                onSosClicked = {
//                                    callEmergency()
                                }
                            )
                        }
                        composable(route = BottomNavigationScreens.AddEventScreen.route) { backStackEntry ->
                            AddEventScreen(
                                sharedMainViewModel = sharedMainViewModel,
                                onNotesSaved = { navController.navigate(BottomNavigationScreens.EventScreen.route) },
                                onBackClicked = { navController.navigateUp() }
                            )
                        }
                        composable(route = BottomNavigationScreens.EventScreen.route) { backStackEntry ->
                            EventScreen(
                                navController = navController,
                                sharedMainViewModel = sharedMainViewModel,
//                                onNotesSaved = { navController.navigate(BottomNavigationScreens.MainScreen.route) },
//                                onAddEventClicked = { navController.navigateUp() }
                            )
                        }
                    }
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