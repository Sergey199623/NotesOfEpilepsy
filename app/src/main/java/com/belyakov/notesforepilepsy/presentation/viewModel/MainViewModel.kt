package com.belyakov.notesforepilepsy.presentation.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.belyakov.notesforepilepsy.data.Events
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainViewModel(

) : ViewModel() {

    private val eventsList = mutableStateListOf<Events>()

    fun addNotes(title: String, description: String, date: String) {
        val database = Firebase.database
        val myRef = database.getReference("notes")
        val note = Events(
            title = title,
            description = description,
            dateOfCreate = date
        )
        val key = myRef.push().key ?: return // генерация ключа для заметки

        myRef.child(key).setValue(note)

//        eventsList.add(Events(title, description, date))
    }
}