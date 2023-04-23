package com.belyakov.ui.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.belyakov.ui.ext.dpToSp
import com.belyakov.ui.theme.ButtonGray
import com.belyakov.ui.theme.PrimaryAccentColor
import com.belyakov.ui.theme.fontFamily

typealias ButtonSize = Dp

sealed class ButtonState {

    object Subscribed : ButtonState()
    object Unsubscribed : ButtonState()
    object Loading : ButtonState()
}

@Composable
fun LoginButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "",
    enabled: Boolean = true,
    type: ButtonState = ButtonState.Unsubscribed
) {
    BaseButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled && type != ButtonState.Loading,
        colors = type.subscribeBehaviorColor(),
        size = 42.dp
    ) {
        if (type == ButtonState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(14.dp),
                color = White,
                strokeWidth = 1.75.dp
            )
        } else {
            Text(
                text,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily,
                fontStyle = FontStyle.Normal,
                fontSize = dpToSp(12.dp),
                letterSpacing = dpToSp(0.2.dp)
            )
        }
    }
}

@Composable
fun BaseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: ButtonSize,
    enabled: Boolean = true,
    colors: ButtonColors,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = { onClick.invoke() },
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .height(size/*29.dp*/),
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 4.dp),
        enabled = enabled,
        colors = colors
    ) {
        content()
    }
}

@Composable
fun ButtonState.subscribeBehaviorColor(): ButtonColors {
    return when (this) {
        is ButtonState.Subscribed -> {
            ButtonDefaults.textButtonColors(
                backgroundColor = ButtonGray,
                contentColor = White,
                disabledContentColor = White.copy(alpha = 0.4f),
            )
        }
        else -> {
            ButtonDefaults.textButtonColors(
                backgroundColor = PrimaryAccentColor,
                contentColor = White,
                disabledContentColor = White.copy(alpha = 0.4f),
            )
        }
    }
}