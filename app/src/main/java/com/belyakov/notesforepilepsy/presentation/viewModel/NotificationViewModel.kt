package com.belyakov.notesforepilepsy.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belyakov.notesforepilepsy.data.Reminder
import com.belyakov.notesforepilepsy.data.ReminderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val reminderRepository: ReminderRepository
) : ViewModel() {

    private val _reminders = MutableStateFlow<List<Reminder>>(emptyList())
    val reminders: StateFlow<List<Reminder>> = _reminders.asStateFlow()

    init {
        viewModelScope.launch {
            reminderRepository.getReminders().collect { reminders ->
                _reminders.value = reminders
            }
        }
    }

    fun addReminder(reminder: Reminder) {
        viewModelScope.launch {
            reminderRepository.saveReminder(reminder)
        }
    }
}