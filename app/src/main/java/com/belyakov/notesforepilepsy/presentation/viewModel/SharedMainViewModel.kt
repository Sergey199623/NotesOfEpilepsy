package com.belyakov.notesforepilepsy.presentation.viewModel

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.belyakov.notesforepilepsy.data.Events
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedMainViewModel(
    databaseUrl: String
) : ViewModel() {

    private var database: FirebaseDatabase? = null
    private var currentReferences: DatabaseReference? = null

    private val _data = MutableStateFlow(SnapshotStateList<Events>())
    val data: StateFlow<SnapshotStateList<Events>> = _data

    init {
        database = FirebaseDatabase.getInstance(databaseUrl)
        currentReferences = database?.getReference("notes")
        currentReferences?.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (noteSnapshot in dataSnapshot.children) {
                    val note = noteSnapshot.getValue(Events::class.java)
                    note?.let { data.value.add(it) }
                }
                // здесь можно обновить список заметок в UI
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Main Screen", "Failed to read value.", error.toException())
            }
        })
    }

    fun addNotes(title: String, description: String, date: String) {
        val id = currentReferences?.push()?.key ?: return // генерация ключа для заметки
        val note = Events(
            title = title,
            description = description,
            dateOfCreate = date,
            id = id
        )
        currentReferences!!.child(id).setValue(note)
    }
}