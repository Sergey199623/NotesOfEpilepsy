package com.belyakov.auth

//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.relocation.BringIntoViewRequester
//import androidx.compose.foundation.relocation.bringIntoViewRequester
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.Text
//import androidx.compose.material.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.focus.onFocusEvent
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import com.belyakov.auth.presentation.AuthViewModel
//import com.belyakov.ui.ext.clearFocusOnKeyboardDismiss
//import com.belyakov.ui.ext.dpToSp
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//import com.belyakov.notesforepilepsy.R
//import com.belyakov.ui.elements.*
//import com.belyakov.ui.theme.PrimaryTextColor
//import com.belyakov.ui.theme.fontFamily

//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun CodeScreen(
//    viewModel: AuthViewModel,
//    navController: NavHostController
//) {
//    val model by component.models.subscribeAsState()
//    val bringIntoViewRequester = remember { BringIntoViewRequester() }
//    val coroutineScope = rememberCoroutineScope()
//
//    Box(
//        modifier = Modifier
//            .verticalScroll(rememberScrollState())
//            .fillMaxWidth()
//            .padding(start = 16.dp, end = 16.dp, top = 52.dp, bottom = 16.dp)
//    ) {
//        ScaffoldSubmitForm(modifier = Modifier.fillMaxSize(),
//            topContent = {
//                AuthToolbar(
//                    onClose = { component.onCancel() },
//                    onBack = { component.onBack() })
//            },
//            title = R.string.auth_input_code,
//            text = "Код выслан на ${if (model.login.loginType() == ILogin.LoginType.Email) "почту" else "телефон"}\n${model.login}",
//            bottomContent = {
//                Box(
//                    Modifier
//                        .fillMaxWidth()
//                        .clearFocusOnKeyboardDismiss()
//                        .onFocusEvent { focusState ->
//                            if (focusState.isFocused) {
//                                coroutineScope.launch {
//                                    delay(500)
//                                    bringIntoViewRequester.bringIntoView()
//                                }
//                            }
//                        }, contentAlignment = Alignment.Center
//                ) {
//                    InputCode(length = model.length,
//                        error = model.error,
//                        onCode = { component.onCodeInput(it) })
//                }
//
//                Spacer(modifier = Modifier.height(34.dp))
//
//                if (model.loadingCode) {
//                    Loader(text = "Идет запрос кода...")
//                } else if (model.canRequestCode) {
//                    TextButton(onClick = { component.fetchNewCode() }) {
//                        Text(
//                            text = R.string.auth_repeat_send_code,
//                            Modifier.fillMaxWidth().bringIntoViewRequester(bringIntoViewRequester),
//                            color = PrimaryTextColor,
//                            fontWeight = FontWeight.SemiBold,
//                            fontFamily = fontFamily,
//                            fontStyle = FontStyle.Normal,
//                            fontSize = dpToSp(14.dp),
//                            letterSpacing = dpToSp(0.2.dp),
//                            textAlign = TextAlign.Center
//                        )
//                    }
//                } else {
//                    Timer(
//                        modifier = Modifier.fillMaxSize()
//                            .bringIntoViewRequester(bringIntoViewRequester),
//                        text = "Повторная отправка доступна\nчерез ${model.timer}"
//                    )
//                }
//            })
//    }
//}