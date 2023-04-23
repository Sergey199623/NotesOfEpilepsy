package com.belyakov.ui.elements

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.belyakov.ui.ext.dpToSp
import com.belyakov.ui.theme.PrimaryTextColor
import com.belyakov.ui.theme.fontFamily

@Composable
fun SubmitForm(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    topContent: @Composable () -> Unit,
    bottomContent: @Composable () -> Unit,
) {
    CardBox(
        modifier = modifier,
        contentPadding = PaddingValues(32.dp)
    ) {
        Column {
            topContent()
            Spacer(modifier = Modifier.height(29.dp))
            Text(
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
                text = title,
                color = White,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                fontStyle = FontStyle.Normal,
                fontSize = dpToSp(20.dp),
                letterSpacing = dpToSp(0.2.dp),
                lineHeight = dpToSp(28.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(11.dp))
            Text(
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
                text = text,
                color = PrimaryTextColor,
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                fontStyle = FontStyle.Normal,
                fontSize = dpToSp(14.dp),
                letterSpacing = dpToSp(0.2.dp),
                lineHeight = dpToSp(22.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(34.dp))

            bottomContent()
        }
    }
}