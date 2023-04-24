package com.belyakov.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.belyakov.notesforepilepsy.R
import com.belyakov.ui.ext.noRippleClickable

@Composable
fun DefaultToolbar(
    onOpenProfile: () -> Unit = { },
    onSosClicked: () -> Unit = { },
    onBackClosed: () -> Unit = { },
//    title: Int,
    isMainScreen: Boolean,
    isShowBackIconNeeded: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (isMainScreen) {
            Image(
                painter = painterResource(R.drawable.ic_profile),
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .noRippleClickable { onOpenProfile() }
                    .padding(16.dp, 16.dp),
                contentDescription = null
            )
        } else {
            if (isShowBackIconNeeded) {
                Image(
                    painter = painterResource(R.drawable.ic_back),
                    modifier = Modifier
                        .weight(1f)
                        .clip(CircleShape)
                        .noRippleClickable { onBackClosed() }
                        .padding(16.dp, 16.dp),
                    contentDescription = null
                )
            } else {
                Spacer(modifier = Modifier.size(32.dp))
            }
        }
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
        Image(
            painter = painterResource(R.drawable.image_sos),
            modifier = Modifier
                .weight(1f)
                .clip(CircleShape)
                .noRippleClickable { onSosClicked() }
                .padding(16.dp, 16.dp),
            contentDescription = null
        )
    }
}