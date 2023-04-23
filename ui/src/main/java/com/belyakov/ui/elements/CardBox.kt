package com.belyakov.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.belyakov.ui.theme.CardBackground

//@Composable
//fun CardBox(
//    modifier: Modifier = Modifier,
//    contentPadding: PaddingValues = PaddingValues(0.dp),
//    contentAlignment: Alignment = Alignment.TopStart,
//    background: Color = CardBackground,
//    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(12.dp),
//    content: @Composable () -> Unit,
//) {
//    Box(
//        modifier = modifier
//            .clip(roundedCornerShape)
//            .background(background)
//            .padding(contentPadding),
//        contentAlignment = contentAlignment
//    ) {
//        content()
//    }
//}