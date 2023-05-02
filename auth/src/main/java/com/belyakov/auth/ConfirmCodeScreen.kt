package com.belyakov.auth

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.belyakov.auth.presentation.SharedAuthViewModel
import com.belyakov.ui.elements.PhoneNumberTransformer
import com.belyakov.ui.elements.TextEditorField
import com.belyakov.ui.ext.clearFocusOnKeyboardDismiss
import com.belyakov.ui.theme.montserratBase

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun ConfirmCodeScreen(
    viewModel: SharedAuthViewModel,
    navController: NavHostController,
    onConfirmCode: () -> Unit
) {
    val isSuccessVerify by viewModel.isSuccessVerify.collectAsState()
    val isSuccessConfirm by viewModel.isSuccessConfirm.collectAsState()

    var phoneNumber by remember { mutableStateOf("") }
    var mobileNumber by rememberSaveable { mutableStateOf("") }

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

        Column {
            Row(modifier = Modifier.padding(all = 10.dp)) {
                BasicTextField(
                    value = mobileNumber,
                    onValueChange = { mobileNumber = it },
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    visualTransformation = { mobileNumberFilter(it) },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Uri
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            viewModel.submitRegistrationData(
                                phoneNumber = mobileNumber,
                                context = activity
                            )
                        }
                    )
                )
            }
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .padding(start = 10.dp)
                    .fillMaxWidth()
                    .background(Color.Gray)
            )
        }

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

const val mask = "+7 (хxx)-xxx-xx-хх"

fun mobileNumberFilter(text: AnnotatedString): TransformedText {
    // change the length
    val trimmed =
        if (text.text.length >= 13) text.text.substring(0..12) else text.text

    val annotatedString = AnnotatedString.Builder().run {
        for (i in trimmed.indices) {
            append(trimmed[i])
            if (i == 1 || i == 4 || i == 7 || i == 9) {
                append(" ")
            }
        }
        pushStyle(SpanStyle(color = Color.LightGray))
        append(mask.takeLast(mask.length - length))
        toAnnotatedString()
    }

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 4) return offset + 1
            if (offset <= 6) return offset + 2
            if (offset <= 9) return offset + 3
            return 12
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 4) return offset - 1
            if (offset <= 6) return offset - 2
            if (offset <= 9) return offset - 3
            return 9
        }
    }

    return TransformedText(annotatedString, phoneNumberOffsetTranslator)
}