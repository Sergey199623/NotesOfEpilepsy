package com.belyakov.ui.elements

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.belyakov.ui.ext.dpToSp
import com.belyakov.ui.theme.ErrorRed
import com.belyakov.ui.theme.montserratBase

@Composable
fun TextEditorField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    prefix: String = "",
    defaultText: String = "",
    label: String = "",
    leftError: String = "",
    rightError: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    limit: Int = 256
) {

    Column(modifier = modifier.width(IntrinsicSize.Max)) {
        CardBox(
            modifier =
            if (leftError.isNotEmpty() || rightError.isNotEmpty()) {
                modifier
                    .height(64.dp)
                    .border(1.dp, ErrorRed, RoundedCornerShape(8.dp))
            } else {
                modifier
                    .height(64.dp)
            },
            roundedCornerShape = RoundedCornerShape(8.dp),
            background = Color.Gray
        ) {
            Column(
                modifier = Modifier.padding(
                    top = 11.dp,
                    bottom = 11.dp,
                    start = 16.dp,
                    end = 16.dp
                )
            ) {
                Text(
                    modifier = Modifier.padding(0.dp),
                    text = label,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontFamily = montserratBase,
                    fontStyle = FontStyle.Normal,
                    fontSize = dpToSp(12.dp),
                    letterSpacing = dpToSp(0.2.dp),
                    lineHeight = dpToSp(20.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxWidth()
                ) {

                    Text(
                        modifier = Modifier.padding(0.dp),
                        text = prefix,
                        textAlign = TextAlign.Start,
                        fontSize = dpToSp(14.dp),
                        textDecoration = TextDecoration.None,
                        letterSpacing = dpToSp(0.dp),
                        lineHeight = dpToSp(22.dp),
                        color = Color.Black,
                        fontFamily = montserratBase,
                        fontStyle = FontStyle.Normal
                    )

                    BasicTextField(
                        modifier = Modifier
                            .padding(0.dp)
                            .fillMaxWidth(),
                        value = value,
                        onValueChange = onValueChange
                        /*  onValueChangeChange(value)
                          if (value.text.length < limit) {
                              textState = value
                              onTextChange(textState.text)
                          }*/,
                        textStyle = LocalTextStyle.current.merge(
                            TextStyle(
                                textAlign = TextAlign.Start,
                                fontSize = dpToSp(14.dp),
                                textDecoration = TextDecoration.None,
                                letterSpacing = dpToSp(0.dp),
                                lineHeight = dpToSp(22.dp),
                                color = Color.Black,
                                fontFamily = montserratBase,
                                fontStyle = FontStyle.Normal
                            )
                        ),
                        keyboardOptions = keyboardOptions,
                        keyboardActions = keyboardActions,
                        cursorBrush = Brush.verticalGradient(
                            0.00f to Color.White,
                            1.00f to Color.White
                        ),
                        visualTransformation = visualTransformation
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        ErrorText(leftError, rightError)
    }
}