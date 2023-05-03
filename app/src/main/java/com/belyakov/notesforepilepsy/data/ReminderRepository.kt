package com.belyakov.notesforepilepsy.data

import kotlinx.coroutines.flow.Flow

interface ReminderRepository {
    fun getReminders(): Flow<List<Reminder>>
    fun saveReminder(reminder: Reminder)
}
