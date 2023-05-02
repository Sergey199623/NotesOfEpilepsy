package com.belyakov.notesforepilepsy.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.belyakov.notesforepilepsy.R
import com.belyakov.notesforepilepsy.presentation.viewModel.ProfileViewModel
import com.belyakov.ui.elements.DefaultToolbar

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = viewModel(),
    onDataSaved: () -> Unit,
    onBackClicked: () -> Unit,
) {
    val firstName = remember { mutableStateOf(TextFieldValue("")) }
    val lastName = remember { mutableStateOf(TextFieldValue("")) }
    val middleName = remember { mutableStateOf(TextFieldValue("")) }
    val age = remember { mutableStateOf(TextFieldValue("")) }
    val email = remember { mutableStateOf(TextFieldValue("")) }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 13.dp)

        ) {
            DefaultToolbar(
                title = R.string.profile_screen_title,
                isNeedShowBackBtn = true,
                isNeedShowProfileBtn = false,
                onBackClicked = { onBackClicked() },
            )
        }

        Spacer(modifier = Modifier.height(31.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 31.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = { /*onChangePhoto()*/ },
                modifier = Modifier
                    .height(210.dp)
                    .width(210.dp)
            ) {
                Icon(
                    painter = painterResource(id = com.belyakov.ui.R.drawable.image_user_no_photo),
                    contentDescription = null
                )
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 31.dp)
            ) {
                BasicTextField(
                    value = firstName.value,
                    onValueChange = { firstName.value = it },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    )
                )
                if (firstName.value.text.isEmpty()) {
                    Text(
                        text = "Ваше имя",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.LightGray
                    )
                }
            }
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 31.dp)
                    .background(Color.Gray)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 31.dp)
                    .padding(top = 21.dp)
            ) {
                BasicTextField(
                    value = lastName.value,
                    onValueChange = { lastName.value = it },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    )
                )
                if (lastName.value.text.isEmpty()) {
                    Text(
                        text = "Фамилия",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.LightGray
                    )
                }
            }
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 31.dp)
                    .background(Color.Gray)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 31.dp)
                    .padding(top = 21.dp)
            ) {
                BasicTextField(
                    value = middleName.value,
                    onValueChange = { middleName.value = it },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    )
                )
                if (middleName.value.text.isEmpty()) {
                    Text(
                        text = "Отчество",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.LightGray
                    )
                }
            }
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 31.dp)
                    .background(Color.Gray)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 31.dp)
                    .padding(top = 21.dp)
            ) {
                BasicTextField(
                    value = age.value,
                    onValueChange = { age.value = it },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    )
                )
                if (age.value.text.isEmpty()) {
                    Text(
                        text = "Возраст",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.LightGray
                    )
                }
            }
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .padding(horizontal = 31.dp)
                    .fillMaxWidth()
                    .background(Color.Gray)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 31.dp)
                    .padding(top = 21.dp)
            ) {
                BasicTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Email
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    )
                )
                if (email.value.text.isEmpty()) {
                    Text(
                        text = "E-mail",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.LightGray
                    )
                }
            }
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .padding(horizontal = 31.dp)
                    .fillMaxWidth()
                    .background(Color.Gray)
            )
        }
    }

    fun saveInfo() {
        profileViewModel.onClickInfoSaved(
            firstName.value.text,
            lastName.value.text,
            middleName.value.text,
            age.value.text,
            email.value.text
        )
    }
}