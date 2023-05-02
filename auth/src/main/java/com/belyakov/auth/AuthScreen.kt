package com.belyakov.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.belyakov.auth.presentation.SharedAuthViewModel
import com.belyakov.ui.theme.montserratBase

@Composable
fun AuthScreen(
    viewModel: SharedAuthViewModel,
    navController: NavHostController,
    onPhoneNumberClicked: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, bottom = 32.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            Icon(
                painter = painterResource(id = R.drawable.rectangle_1), contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .fillMaxSize()
        ) {

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(id = R.string.auth_screen_title),
                modifier = Modifier.padding(start = 16.dp),
                color = Color.Black,
                fontSize = 24.sp,
                fontFamily = montserratBase
            )

            Spacer(modifier = Modifier.height(18.dp))

            Button(
                onClick = { onPhoneNumberClicked() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.auth_screen_btn_phone),
                    color = Color.White,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 20.sp,
                    fontFamily = montserratBase
                )
            }

            Spacer(modifier = Modifier.height(26.dp))

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.auth_screen_btn_email),
                    color = Color.White,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 20.sp,
                    fontFamily = montserratBase
                )
            }
        }
    }
}