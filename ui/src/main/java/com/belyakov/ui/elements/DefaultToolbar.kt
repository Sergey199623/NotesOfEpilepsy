package com.belyakov.ui.elements

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.belyakov.ui.theme.montserratMediumBold

@Composable
fun DefaultToolbar(
    title: Int,
    isNeedBackBtn: Boolean,
    onBackClicked: () -> Unit = { },
    onProfileClicked: () -> Unit = { },
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        if (isNeedBackBtn) {
            IconButton(
                onClick = { onBackClicked() },
                modifier = Modifier.align(Alignment.CenterStart)
            )
            {
                Icon(
                    painter = painterResource(id = com.belyakov.navigation.R.drawable.ic_profile),
                    contentDescription = null,
                    modifier = Modifier
                        .height(25.dp)
                        .width(25.dp),
                )
            }
        }
        Text(
            text = stringResource(id = title),
            modifier = Modifier.align(Alignment.Center),
            color = Color.Black,
            fontSize = 32.sp,
            fontFamily = montserratMediumBold,
        )
        IconButton(
            onClick = { onProfileClicked() },
            modifier = Modifier.align(Alignment.CenterEnd)
        )
        {
            Icon(
                painter = painterResource(id = com.belyakov.navigation.R.drawable.ic_profile),
                contentDescription = null,
                modifier = Modifier
                    .height(25.dp)
                    .width(25.dp),
            )
        }
    }
}