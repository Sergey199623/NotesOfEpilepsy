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
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
import android.Manifest.permission.CALL_PHONE
import android.graphics.Color
import android.view.View

class MainActivity : ComponentActivity() {

    @SuppressLint("UnrememberedGetBackStackEntry")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedMainViewModel = SharedMainViewModel(getString(R.string.firebase_database_url))
        val sharedAuthViewModel = SharedAuthViewModel(getString(R.string.firebase_database_url))
        val isHasAuth = false

        // запрос разрешения на звонки
        if (ContextCompat.checkSelfPermission(this, CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(CALL_PHONE),
                REQUEST_CALL_PHONE_PERMISSION
            )
        }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT

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
                                onPhoneNumberClicked = {
                                    navController.navigate(
                                        BottomNavigationScreens.ConfirmCodeScreen.route
                                    )
                                }
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
                                onSignUpForDoctor = { openUrlInBrowser(GOSUSLUGI_URL) },
                                onSosClicked = {
                                    callEmergency()
                                },
                                sharedMainViewModel = sharedMainViewModel
                            )
                        }
                        composable(route = BottomNavigationScreens.ProfileScreen.route) { backStackEntry ->
                            ProfileScreen(
                                onDataSaved = {
                                    // todo реализовать сохранение данных пользователя на удаленной БД
                                },
                                onBackClicked = { navController.navigateUp() },
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

    private fun callEmergency() {

        val dialIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:112"))
        // Проверяем, есть ли у приложения разрешение на звонок
        if (ContextCompat.checkSelfPermission(
                this,
                CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Запрашиваем разрешение на звонок
            ActivityCompat.requestPermissions(
                this,
                arrayOf(CALL_PHONE),
                REQUEST_CALL_PHONE_PERMISSION
            )
        } else {
            // Уже есть разрешение на звонок, выполняем звонок
            try {
                startActivity(dialIntent)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    private fun openUrlInBrowser(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            this.startActivity(intent)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    // обработка результата запроса разрешения
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_CALL_PHONE_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // разрешение получено, делаем звонок
                } else {
                    Toast.makeText(
                        this,
                        "Для совершения звонка нужно разрешение",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private companion object {
        const val REQUEST_CALL_PHONE_PERMISSION = 1
        const val GOSUSLUGI_URL = "https://www.gosuslugi.ru/category"
    }
}