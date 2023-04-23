package com.belyakov.auth

//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.relocation.BringIntoViewRequester
//import androidx.compose.foundation.relocation.bringIntoViewRequester
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.text.KeyboardActions
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.runtime.*
//import androidx.compose.ui.ExperimentalComposeUiApi
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.focus.onFocusEvent
//import androidx.compose.ui.platform.LocalSoftwareKeyboardController
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.TextRange
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import com.belyakov.auth.presentation.AuthViewModel
//import com.belyakov.notesforepilepsy.R
//import com.belyakov.ui.elements.*
//import com.belyakov.ui.ext.clearFocusOnKeyboardDismiss
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch

//@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
//@Composable
//fun LoginScreen(
//    viewModel: AuthViewModel,
//    navController: NavHostController
//) {
//
////    val model by component.models.subscribeAsState()
//    val bringIntoViewRequester = remember { BringIntoViewRequester() }
//    val keyboardController = LocalSoftwareKeyboardController.current
//    val coroutineScope = rememberCoroutineScope()
//
//    var prefix = remember {
//        ""
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .verticalScroll(rememberScrollState())
//            .padding(start = 16.dp, end = 16.dp, top = 52.dp, bottom = 16.dp)
//    ) {
//        ScaffoldSubmitForm(
//            modifier = Modifier.fillMaxSize(),
//            topContent = { AuthToolbar(onClose = { component.onCancel() }) },
//            title = stringResource(R.string.auth_login_title),
//            text = stringResource(R.string.auth_login_subtitle),
//            bottomContent = {
//                val textFieldValueState =
//                    remember { mutableStateOf(TextFieldValue(text = model.login)) }
//
//                TextEditorLogin(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .bringIntoViewRequester(bringIntoViewRequester)
//                        .clearFocusOnKeyboardDismiss()
//                        .onFocusEvent { focusState ->
//                            if (focusState.isFocused) {
//                                coroutineScope.launch {
//                                    delay(500)
//                                    bringIntoViewRequester.bringIntoView()
//                                }
//                            }
//                        },
//                    value = textFieldValueState.value,
//                    label = R.string.auth_login_hint,
//                    prefix = prefix,
//                    onValueChange = { changedValue ->
//
//                        if (changedValue.text.loginType() != ILogin.LoginType.Phone) { //не телефон
//                            prefix = ""
//                            if (changedValue.text.startsWith("+7")) {
//                                val newValue = changedValue.text.replace("+7", "")
//                                textFieldValueState.value = TextFieldValue(
//                                    text = newValue,
//                                    selection = TextRange(newValue.length + 1)
//                                )
//                            } else {
//                                textFieldValueState.value = changedValue
//                            }
//                        } else { //если телефон
//                            prefix = "+7 "
//                            textFieldValueState.value = changedValue
//                        }
//                        component.onLoginInput(textFieldValueState.value.text)
//                    },
//                    leftError = if (model.formatError) model.errorLabel else "",
//                    rightError = "",
//                    visualTransformation = if (model.login.loginType() == ILogin.LoginType.Phone) {
//                        CustomTransformer(format = "xxx xxx xx xx")
//                    } else VisualTransformation.None,
//                    limit = if (model.login.loginType() == ILogin.LoginType.Phone) {
//                        13
//                    } else 25,
//                    keyboardOptions = KeyboardOptions(
//                        imeAction = ImeAction.Done,
//                        autoCorrect = false,
//                        keyboardType = KeyboardType.Uri
//                    ),
//                    keyboardActions = KeyboardActions(
//                        onDone = {
//                            keyboardController?.hide()
////                            component.onNext()
//                        }
//                    )
//                )
//                Spacer(modifier = Modifier.height(24.dp))
//                LoginButton(
//                    modifier = Modifier.fillMaxWidth(),
//                    text = stringResource(R.string.auth_login_btn_next),
//                    onClick = { component.onNext() },
//                    enabled = !model.formatError,
//                    type = if (model.loading) ButtonState.Loading else ButtonState.Unsubscribed
//                )
//            }
//        )
//    }
//}