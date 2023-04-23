package com.belyakov.ui.elements

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.belyakov.ui.ext.dpToSp
import com.belyakov.ui.theme.fontFamily

//@Composable
//fun InputCode(
//    length: Int = 4,
//    error: String? = null,
//    onCode: (String) -> Unit = {}
//) {
//    val code = remember { mutableStateOf(Array(length) { "" }) }
//    Column(
//        modifier = Modifier
//            .width(IntrinsicSize.Max)
//    ) {
//        Row(
//            modifier = Modifier
//                .wrapContentWidth()
//                .wrapContentHeight(),
//            horizontalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            for (i in 0 until length) {
//                InputCodeField(
//                    modifier = if (error != null && error.isNotEmpty()) {
//                        Modifier
//                            .border(1.dp, ErrorRed, RoundedCornerShape(8.dp))
//                    } else {
//                        Modifier
//                    }
//                ) { newText ->
//                    code.value[i] = if (newText.isNotEmpty()) newText[0].toString() else ""
//                    if (code.value.joinToString(separator = "").length == length) {
//                        onCode(code.value.joinToString(separator = ""))
//                    }
//                }
//            }
//        }
//        if (error != null) {
//            Spacer(modifier = Modifier.height(6.dp))
//            Error(error)
//        }
//    }
//}
//
//@Composable
//fun InputCodeField(
//    modifier: Modifier,
//    onValueChange: (String) -> Unit,
//) {
//    var color by remember { mutableStateOf(TransparentWhite) }
//    val focusManager = LocalFocusManager.current
//    var textState by remember { mutableStateOf("") }
//    val maxLength = 1
//    TextField(
//        modifier = modifier
//            .size(64.dp)
//            .padding(0.dp)
//            .onFocusChanged { color = if (it.isFocused) TransparentWhite10 else TransparentWhite5 },
//        shape = RoundedCornerShape(8.dp),
//        value = textState,
//        textStyle = LocalTextStyle.current.merge(
//            TextStyle(
//                textAlign = TextAlign.Center,
//                fontSize = dpToSp(24.dp),
//                textDecoration = TextDecoration.None,
//                letterSpacing = dpToSp(0.dp),
//                lineHeight = dpToSp(32.dp),
//                color = Color.White,
//                fontFamily = fontFamily
//            )
//        ),
//        colors = TextFieldDefaults.textFieldColors(
//            backgroundColor = color,
//            cursorColor = Color.White,
//            disabledLabelColor = LightGray,
//            focusedIndicatorColor = Color.Transparent,
//            unfocusedIndicatorColor = Color.Transparent
//        ),
//        onValueChange = {
//            onValueChange.invoke(it)
//            if (it.length <= maxLength) textState = it
//            if (it.isNotEmpty()) focusManager.moveFocus(FocusDirection.Right)
//            else focusManager.moveFocus(FocusDirection.Left)
//        },
//        keyboardOptions = KeyboardOptions(
//            keyboardType = KeyboardType.Number,
//            imeAction = ImeAction.Next
//        ),
//        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Right) })
//    )
//}
//
//@Composable
//fun Error(error: String) {
//    Box(
//        contentAlignment = Alignment.CenterStart
//    ) {
//        Text(
//            modifier = Modifier.padding(0.dp),
//            text = error,
//            color = ErrorRed,
//            fontWeight = FontWeight.Normal,
//            fontFamily = fontFamily,
//            fontStyle = FontStyle.Normal,
//            fontSize = dpToSp(12.dp),
//            letterSpacing = dpToSp(0.2.dp),
//            lineHeight = dpToSp(20.dp)
//        )
//    }
//}