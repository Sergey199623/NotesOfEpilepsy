package com.belyakov.notesforepilepsy.presentation.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.belyakov.notesforepilepsy.data.Events

class MainViewModel(

) : ViewModel() {

    private val eventsList = mutableStateListOf<Events>()

    fun addNotes(title: String, description: String, date: String) {
        eventsList.add(Events(title, description, date))
    }
}