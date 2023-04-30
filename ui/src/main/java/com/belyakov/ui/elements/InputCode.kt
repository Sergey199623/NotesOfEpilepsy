package com.belyakov.ui.elements

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