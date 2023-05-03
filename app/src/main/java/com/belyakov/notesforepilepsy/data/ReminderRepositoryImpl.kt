package com.belyakov.notesforepilepsy.data

import android.content.SharedPreferences
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//class ReminderRepositoryImpl(
//    context: Context
//) {
//
//    private val sharedPreferences =
//        context.getSharedPreferences("ReminderPrefs", Context.MODE_PRIVATE)
//
//    fun saveReminders(reminders: List<Reminder>) {
//        val json = Gson().toJson(reminders)
//        sharedPreferences.edit().putString("reminders", json).apply()
//    }
//
//    fun getReminders(): List<Reminder> {
//        val json = sharedPreferences.getString("reminders", null)
//        return if (json != null) {
//            Gson().fromJson(json, object : TypeToken<List<Reminder>>() {}.type)
//        } else {
//            emptyList()
//        }
//    }
//}

internal class ReminderRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : ReminderRepository {
    private val remindersKey = "reminders"

    override fun getReminders(): Flow<List<Reminder>> = flow {
        val remindersJson = sharedPreferences.getString(remindersKey, null)
        val reminders = remindersJson?.let {
            Gson().fromJson(it, Array<Reminder>::class.java).toList()
        } ?: emptyList()
        emit(reminders)
    }

    override fun saveReminder(reminder: Reminder) {
        val remindersJson = sharedPreferences.getString(remindersKey, null)
        val reminders = remindersJson?.let {
            Gson().fromJson(it, Array<Reminder>::class.java).toMutableList()
        } ?: mutableListOf()
        reminders.add(reminder)
        val newRemindersJson = Gson().toJson(reminders)
        sharedPreferences.edit().putString(remindersKey, newRemindersJson).apply()
    }
}
