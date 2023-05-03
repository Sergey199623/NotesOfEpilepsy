package com.belyakov.notesforepilepsy.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.belyakov.notesforepilepsy.R
import com.belyakov.notesforepilepsy.data.Reminder
import com.belyakov.notesforepilepsy.presentation.viewModel.NotificationViewModel
import com.belyakov.ui.elements.DefaultToolbar
import java.time.format.DateTimeFormatter
import androidx.lifecycle.viewmodel.compose.viewModel

//@Composable
//fun NotificationScreen(
//    onProfileClicked: () -> Unit,
//    onBackClicked: () -> Unit
//) {
//    val reminders = remember { mutableListOf<Reminder>() }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(vertical = 20.dp)
//    ) {
//Column(
//modifier = Modifier
//.fillMaxSize()
//.align(Alignment.TopCenter)
//) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 13.dp)
//
//    ) {
//        DefaultToolbar(
//            title = R.string.notification_screen_title,
//            isNeedShowBackBtn = true,
//            isNeedShowProfileBtn = true,
//            onProfileClicked = { onProfileClicked() },
//            onBackClicked = { onBackClicked() }
//        )
//    }
//
//    Spacer(modifier = Modifier.height(32.dp))
//
//            ReminderList(reminders = reminders)
//
//            FloatingActionButton(
//                onClick = { /*TODO*/ },
//                content = { Icon(Icons.Filled.Add, null) }
//            )
//        }
//    }
//}

@Composable
fun NotificationScreen(
    viewModel: NotificationViewModel,
    onProfileClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    val reminderList by viewModel.reminders.collectAsState()

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
                    title = R.string.notification_screen_title,
                    isNeedShowBackBtn = true,
                    isNeedShowProfileBtn = true,
                    onProfileClicked = { onProfileClicked() },
                    onBackClicked = { onBackClicked() }
                )
            }
            ReminderList(
                reminderList = reminderList,
                onReminderClicked = { viewModel.addReminder(it) },
                onReminderRemoved = { reminder ->
                    // Удаление элемента из списка
//                    viewModel.removeReminder(reminder)
                }
            )
        }
        FloatingActionButton(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            onClick = {  }
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add reminder")
        }
    }
}


//    @Composable
//    fun ReminderList(reminders: List<Reminder>) {
//        LazyColumn {
//            items(reminders) { reminder ->
//                ReminderItem(reminder)
//            }
//        }
//    }

//    @Composable
//    fun ReminderItem(reminder: Reminder) {
//        Card(
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier.padding(16.dp)
//            ) {
//                Text(text = reminder.event.title, fontWeight = FontWeight.Bold)
//                Text(
//                    text = reminder.event.time.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),
//                    fontWeight = FontWeight.Bold
//                )
//                Text(text = reminder.notification.description)
//            }
//        }
//    }


@Composable
fun ReminderList(
    reminderList: List<Reminder>,
    onReminderClicked: (Reminder) -> Unit, // Параметр для действия по клику на элемент
    onReminderRemoved: (Reminder) -> Unit // Параметр для действия по удалению элемента
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(reminderList) { reminder ->
            ReminderItem(
                reminder = reminder,
                onReminderClicked = { onReminderClicked(reminder) },
                onReminderRemoved = { onReminderRemoved(reminder) } // Вызов функции по удалению элемента
            )
        }
    }
}

@Composable
fun ReminderItem(
    reminder: Reminder,
    onReminderClicked: () -> Unit, // Параметр для действия по клику на элемент
    onReminderRemoved: () -> Unit // Параметр для действия по удалению элемента
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .clickable(onClick = onReminderClicked) // Обработчик клика
        ) {
            Text(text = reminder.event.title, fontWeight = FontWeight.Bold)
            Text(
                text = reminder.event.time.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),
                fontWeight = FontWeight.Bold
            )
            Text(text = reminder.notification.description)
            IconButton(
                modifier = Modifier.align(Alignment.End),
                onClick = onReminderRemoved // Обработчик удаления
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Remove reminder")
            }
        }
    }
}