package com.belyakov.ui.elements

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.belyakov.ui.R

@Composable
fun DefaultToolbar(
    onBackClosed: () -> Unit = { },
//    title: Int,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(id = R.string.main_screen_title),
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            style = TextStyle(
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 16.sp,
            )
        )
    }
}