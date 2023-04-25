package com.belyakov.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.belyakov.ui.R

@Composable
fun EventItem(
    title: String,
    description: String,
    createdAt: String,
    key: String // не отрисовываем
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = title, fontSize = 12.sp)
        Text(text = description, fontSize = 12.sp)
        Text(text = "Created at: $createdAt", fontSize = 12.sp, color = colorResource(R.color.primary_text_color))
    }
}