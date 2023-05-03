package com.belyakov.notesforepilepsy.presentation.viewModel

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.belyakov.notesforepilepsy.data.Fits
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
    private var notesReferences: DatabaseReference? = null

    private val _data = MutableStateFlow(SnapshotStateList<Fits>())
    val data: StateFlow<SnapshotStateList<Fits>> = _data

    init {
        database = FirebaseDatabase.getInstance(databaseUrl)
        notesReferences = database?.getReference("notes")
        notesReferences?.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (noteSnapshot in dataSnapshot.children) {
                    val note = noteSnapshot.getValue(Fits::class.java)
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
        val id = notesReferences?.push()?.key ?: return // генерация ключа для заметки
        val note = Fits(
            title = title,
            description = description,
            dateOfCreate = date,
            id = id
        )
        notesReferences!!.child(id).setValue(note)
    }
}