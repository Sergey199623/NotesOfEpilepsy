package com.belyakov.notesforepilepsy.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.belyakov.notesforepilepsy.R
import com.belyakov.notesforepilepsy.presentation.viewModel.MainViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    mainViewModel: MainViewModel = viewModel(),
    onDataSaved: () -> Unit,
    onBackClicked: () -> Unit,
    onSosClicked: () -> Unit
) {
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val middleName = remember { mutableStateOf("") }
    val age = remember { mutableStateOf("") }
    val weight = remember { mutableStateOf("") }
    val bloodGroup = remember { mutableStateOf("") }
    val medication = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = firstName.value,
            onValueChange = { firstName.value = it },
            label = { Text("First Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = lastName.value,
            onValueChange = { lastName.value = it },
            label = { Text("Last Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = middleName.value,
            onValueChange = { middleName.value = it },
            label = { Text("Middle Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = age.value,
            onValueChange = { age.value = it },
            label = { Text("Age") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = weight.value,
            onValueChange = { weight.value = it },
            label = { Text("Weight") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = bloodGroup.value,
            onValueChange = { bloodGroup.value = it },
            label = { Text("Blood Group") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = medication.value,
            onValueChange = { medication.value = it },
            label = { Text("Medication") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            IconButton(
                onClick = { onSosClicked() }
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_notes),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
            }
        }
    }
}