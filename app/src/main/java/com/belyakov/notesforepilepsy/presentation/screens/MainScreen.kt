package com.belyakov.notesforepilepsy.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.belyakov.notesforepilepsy.R
import com.belyakov.notesforepilepsy.presentation.viewModel.SharedMainViewModel
import com.belyakov.ui.elements.DefaultToolbar
import com.belyakov.ui.theme.montserratBase

@Composable
fun MainScreen(
    navController: NavHostController,
    onEventsClicked: () -> Unit,
    onProfileClicked: () -> Unit,
    onSosClicked: () -> Unit,
    onSignUpForDoctor: () -> Unit,
    sharedMainViewModel: SharedMainViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 13.dp)

            ) {
                DefaultToolbar(
                    title = R.string.main_screen_title,
                    isNeedBackBtn = false,
                    onProfileClicked = { onProfileClicked() }
                )
            }

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
                    onClick = { onSosClicked() }) {
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
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 31.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { onSignUpForDoctor() }) {
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
}