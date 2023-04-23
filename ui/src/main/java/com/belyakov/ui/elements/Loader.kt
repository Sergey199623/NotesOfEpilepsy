package com.belyakov.ui.elements
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.CircularProgressIndicator
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import com.belyakov.ui.ext.dpToSp
//import com.belyakov.ui.theme.PrimaryTextColor
//import com.belyakov.ui.theme.fontFamily
//
//@Composable
//fun Loader(text: String) {
//    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
//        CircularProgressIndicator(
//            modifier = Modifier
//                .size(26.dp)
//                .align(Alignment.Center),
//            color = Color.White,
//            strokeWidth = 3.dp
//        )
//    }
//    Spacer(modifier = Modifier.height(17.dp))
//    Text(
//        text = text,
//        Modifier.fillMaxWidth(),
//        color = PrimaryTextColor,
//        fontWeight = FontWeight.Normal,
//        fontFamily = fontFamily,
//        fontStyle = FontStyle.Normal,
//        fontSize = dpToSp(14.dp),
//        letterSpacing = dpToSp(0.2.dp),
//        textAlign = TextAlign.Center
//    )
//}