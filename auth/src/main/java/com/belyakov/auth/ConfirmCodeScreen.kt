package com.belyakov.auth

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.belyakov.auth.presentation.SharedAuthViewModel
import com.belyakov.ui.elements.PhoneNumberTransformer
import com.belyakov.ui.elements.TextEditorField
import com.belyakov.ui.ext.clearFocusOnKeyboardDismiss
import com.belyakov.ui.theme.montserratBase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun ConfirmCodeScreen(
    viewModel: SharedAuthViewModel,
    navController: NavHostController,
    onConfirmCode: () -> Unit
) {
    val isSuccessVerify by viewModel.isSuccessVerify.collectAsState()
    val isSuccessConfirm by viewModel.isSuccessConfirm.collectAsState()

    val textFieldValueState = remember { mutableStateOf(TextFieldValue("")) }
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()
    var isVisible by remember { mutableStateOf(false) }
    val prefix = remember { "+7 " }
    val context = LocalContext.current
    val activity = context as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {

        Icon(
            painter = painterResource(id = R.drawable.rectangle_1), contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )

        Spacer(modifier = Modifier.height(128.dp))

        Text(
            text = stringResource(id = R.string.confirm_code_screen_title),
            modifier = Modifier.padding(start = 16.dp),
            color = Color.Black,
            fontSize = 24.sp,
            fontFamily = montserratBase,
        )

        Spacer(modifier = Modifier.height(19.dp))

        TextEditorField(
            modifier = Modifier
                .fillMaxWidth()
                .bringIntoViewRequester(bringIntoViewRequester)
                .clearFocusOnKeyboardDismiss(),
            value = textFieldValueState.value,
            prefix = prefix,
            onValueChange = { changedValue ->
                textFieldValueState.value = changedValue
            },
            leftError = "",
            rightError = "",
            visualTransformation = PhoneNumberTransformer(format = "(XXX) XXX XX XX"),
            limit = 13,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                autoCorrect = false,
                keyboardType = KeyboardType.Uri
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    viewModel.submitRegistrationData(
                        phoneNumber = "+7" + textFieldValueState.value.text,
                        context = activity
                    )
                }
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (isSuccessVerify) {
            Text(
                text = stringResource(id = R.string.confirm_code_screen_code),
                modifier = Modifier.padding(start = 16.dp),
                color = Color.Black,
                fontSize = 24.sp,
                fontFamily = montserratBase,
            )

            TextEditorField(
                modifier = Modifier
                    .fillMaxWidth()
                    .bringIntoViewRequester(bringIntoViewRequester)
                    .clearFocusOnKeyboardDismiss(),
                value = textFieldValueState.value,
                onValueChange = { changedValue ->
                    textFieldValueState.value = changedValue
                },
                leftError = "",
                rightError = "",
                visualTransformation = PhoneNumberTransformer(format = "XXXX"),
                limit = 4,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Uri
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                )
            )

            if (isSuccessConfirm) {
                onConfirmCode()
            }
        }
    }
}