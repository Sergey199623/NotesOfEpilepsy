package com.belyakov.ui.elements

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.belyakov.ui.ext.dpToSp
import com.belyakov.ui.theme.PrimaryTextColor
import com.belyakov.ui.theme.robotoBase

@Composable
fun Timer(modifier: Modifier = Modifier, text: String) {
    Text(
        text = text,
        modifier = modifier,
        color = PrimaryTextColor,
        fontWeight = FontWeight.Normal,
        fontFamily = robotoBase,
        fontStyle = FontStyle.Normal,
        fontSize = dpToSp(14.dp),
        letterSpacing = dpToSp(0.2.dp),
        textAlign = TextAlign.Center
    )
}