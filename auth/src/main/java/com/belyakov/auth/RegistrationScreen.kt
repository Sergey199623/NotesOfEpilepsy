package com.belyakov.auth

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.belyakov.auth.presentation.SharedAuthViewModel

@Composable
fun RegistrationScreen(
    viewModel: SharedAuthViewModel,
    navController: NavHostController
) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(4.dp, Color.Black, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.registration_screen_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = stringResource(id = R.string.registration_screen_hint_name)) },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.size(12.dp))
            TextField(
                value = age,
                onValueChange = { age = it },
                label = { Text(text = stringResource(id = R.string.registration_screen_hint_age)) },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.size(12.dp))
            TextField(
                value = phone,
                onValueChange = { phone = it},
                label = { Text(text = stringResource(id = R.string.registration_screen_hint_telephone_number)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )
        }
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(12.dp),
        ) {
            Text(
                text = stringResource(id = R.string.registration_screen_btn_next),
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}