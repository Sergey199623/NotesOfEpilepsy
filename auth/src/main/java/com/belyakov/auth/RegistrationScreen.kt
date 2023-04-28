package com.belyakov.auth

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.belyakov.auth.presentation.AuthViewModel
import com.belyakov.ui.ext.clearFocusOnKeyboardDismiss
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.belyakov.ui.elements.*
import com.belyakov.ui.ext.dpToSp
import com.belyakov.ui.theme.fontFamily

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun RegistrationScreen(
    viewModel: AuthViewModel,
    navController: NavHostController
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 52.dp, bottom = 16.dp)
    ) {
        SubmitForm(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.ime.asPaddingValues()),
            topContent = {
                AuthToolbar(
                    onClose = { component.onCancel() },
                    onBack = { component.onBack() })
            },
            title = stringResource(id = R.string.auth_title_registration),
            text = stringResource(id = R.string.auth_subtitle_registration),
            bottomContent = {
                TextEditor(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clearFocusOnKeyboardDismiss()
                        .onFocusEvent { focusState ->
                            if (focusState.isFocused) {
                                coroutineScope.launch {
                                    delay(500)
                                    bringIntoViewRequester.bringIntoView()
                                }
                            }
                        },

                    label = stringResource(id = R.string.auth_registration_user_name),
                    onTextChange = {
                        component.onNameInput(it)
                        Log.d("", it)
                    },
                    leftError = model.error,
                    rightError = if (model.error.isNotEmpty()) "${model.name.length}/30" else "",
                    limit = 30,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        autoCorrect = false
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            component.onNext()
                        }
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
                LoginButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.auth_login_btn_next),
                    enabled = model.error.isEmpty(),
                    onClick = { component.onNext() },
                    type = if (model.loading) ButtonState.Loading else ButtonState.Unsubscribed
                )
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                        .fillMaxWidth()
                )
                Text(
                    modifier = Modifier.bringIntoViewRequester(bringIntoViewRequester),
                    text = stringResource(id = R.string.auth_privacy_policies),
                    color = White,
                    fontWeight = FontWeight.Normal,
                    fontFamily = fontFamily,
                    fontStyle = FontStyle.Normal,
                    fontSize = dpToSp(12.dp),
                    letterSpacing = dpToSp(0.2.dp),
                    textAlign = TextAlign.Center
                )
            }
        )
    }
}