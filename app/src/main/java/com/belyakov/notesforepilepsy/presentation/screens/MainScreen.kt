package com.belyakov.notesforepilepsy.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
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
import androidx.navigation.NavHostController
import com.belyakov.notesforepilepsy.R
import com.belyakov.notesforepilepsy.presentation.viewModel.SharedMainViewModel
import com.belyakov.ui.theme.montserratBase
import com.belyakov.ui.theme.montserratMediumBold

@Composable
fun MainScreen(
    navController: NavHostController,
    onEventsClicked: () -> Unit,
    onProfileClicked: () -> Unit,
    onSosClicked: () -> Unit,
    sharedMainViewModel: SharedMainViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 13.dp)
        ) {
            IconButton(onClick = { onProfileClicked() }) {
                Icon(
                    painter = painterResource(id = com.belyakov.navigation.R.drawable.ic_profile),
                    contentDescription = null,
                    modifier = Modifier
                        .height(25.dp)
                        .width(25.dp),
                )
            }
        }

        Spacer(modifier = Modifier.height(19.dp))

        Text(
            text = stringResource(id = R.string.main_screen_title),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 32.dp),
            color = Color.Black,
            fontSize = 32.sp,
            fontFamily = montserratMediumBold,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 31.dp)
                .padding(top = 32.dp)
        ) {
            Button(
                modifier = Modifier
                    .height(155.dp)
                    .width(155.dp)
                    .weight(0.5f)
                    .padding(end = 9.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { onEventsClicked() }) {
                Text(
                    text = stringResource(id = R.string.navigation_affect),
                    fontFamily = montserratBase,
                    fontSize = 16.sp
                )
            }

            Button(
                modifier = Modifier
                    .height(155.dp)
                    .width(155.dp)
                    .weight(0.5f)
                    .padding(start = 9.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.navigation_sos),
                    fontFamily = montserratBase,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(19.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 31.dp)
        ) {
            Button(
                modifier = Modifier
                    .height(155.dp)
                    .width(155.dp)
                    .weight(0.5f)
                    .padding(end = 9.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.navigation_capsule),
                    fontFamily = montserratBase,
                    fontSize = 16.sp
                )
            }

            Button(
                modifier = Modifier
                    .height(155.dp)
                    .width(155.dp)
                    .weight(0.5f)
                    .padding(start = 9.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.navigation_calendar),
                    fontFamily = montserratBase,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(104.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 31.dp),
            shape = RoundedCornerShape(20.dp),
            onClick = { /*TODO*/ }) {
            Text(
                text = stringResource(id = R.string.navigation_emergency),
                modifier = Modifier.padding(vertical = 22.dp),
                fontFamily = montserratBase,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 31.dp),
            shape = RoundedCornerShape(20.dp),
            onClick = { /*TODO*/ }) {
            Text(
                text = stringResource(id = R.string.navigation_apteks),
                modifier = Modifier.padding(vertical = 22.dp),
                fontFamily = montserratBase,
                fontSize = 16.sp
            )
        }
    }
}