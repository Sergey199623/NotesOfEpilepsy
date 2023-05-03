package com.belyakov.notesforepilepsy.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.belyakov.notesforepilepsy.R
import com.belyakov.notesforepilepsy.presentation.CollapsibleListItem
import com.belyakov.ui.elements.DefaultToolbar

//@Composable
//fun MedicinesScreen(
//    onBackClicked: () -> Unit,
//    onProfileClicked: () -> Unit
//) {
//
//    val itemsListState = rememberLazyListState()
//    val medicines = remember { mutableStateListOf("Medicine 1", "Medicine 2") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(vertical = 20.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 13.dp)
//
//        ) {
//            DefaultToolbar(
//                title = R.string.medicines_screen_title,
//                isNeedShowBackBtn = true,
//                isNeedShowProfileBtn = true,
//                onBackClicked = { onBackClicked() },
//                onProfileClicked = { onProfileClicked() }
//            )
//        }
//
//        Spacer(modifier = Modifier.height(37.dp))
//
//        LazyColumn(
//            modifier = Modifier.padding(horizontal = 28.dp),
//            state = itemsListState
//        ) {
//            items(medicines) { medicine ->
//                CollapsibleListItem(
//                    text = medicine,
//                    content = {
//                        // Collapsed content
//                        Text("Start date:")
//                        Text("Days count:")
//                        Text("Consumed count:")
//                    },
//                    onExpanded = {
//                        // When expanded
//                    },
//                    onCollapsed = {
//                        // When collapsed
//                    }
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.height(22.dp))
//
//        Box(modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 30.dp)
//        ) {
//            IconButton(
//                onClick = { medicines.add("New medicine") },
//                modifier = Modifier
//                    .align(Alignment.CenterEnd)
//                    .height(25.dp)
//                    .width(25.dp)
//            ) {
//                Icon(
//                    painter = painterResource(id = com.belyakov.ui.R.drawable.ic_add_medicines),
//                    contentDescription = null
//                )
//            }
//        }
//    }
//}

@Composable
fun CollapsibleListItem(
    text: String,
    modifier: Modifier = Modifier,
    onExpanded: () -> Unit = {},
    onCollapsed: () -> Unit = {},
    isEditable: Boolean = false,
    onStartDateChanged: (String) -> Unit = {},
    onDaysCountChanged: (String) -> Unit = {},
    onConsumedCountChanged: (String) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier,
        elevation = 4.dp
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        expanded = !expanded
                        if (expanded) {
                            onExpanded()
                        } else {
                            onCollapsed()
                        }
                    }
                    .padding(16.dp)
            ) {
                Text(
                    text = text,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    painter = painterResource(R.drawable.ic_dropdown),
                    contentDescription = null,
                    modifier = Modifier.rotate(if (expanded) 180f else 0f)
                )
            }

            if (expanded) {
                if (isEditable) {
                    MedicinesListItem(
                        onStartDateChanged = onStartDateChanged,
                        onDaysCountChanged = onDaysCountChanged,
                        onConsumedCountChanged = onConsumedCountChanged
                    )
                } else {
                    // Содержимое списка по умолчанию
                }
            }
        }
    }
}