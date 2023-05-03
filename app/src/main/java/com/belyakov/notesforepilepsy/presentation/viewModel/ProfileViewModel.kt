package com.belyakov.notesforepilepsy.presentation.viewModel

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.belyakov.notesforepilepsy.data.Fits
import com.google.firebase.database.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel(
    databaseUrl: String
) : ViewModel() {

    private var database: FirebaseDatabase? = null
    private var profileReferences: DatabaseReference? = null

    private val _data = MutableStateFlow(SnapshotStateList<Fits>())
    val data: StateFlow<SnapshotStateList<Fits>> = _data

    init {
        database = FirebaseDatabase.getInstance(databaseUrl)
        profileReferences = database?.getReference("profile")
        profileReferences?.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (noteSnapshot in dataSnapshot.children) {
                    val note = noteSnapshot.getValue(Fits::class.java)
                    note?.let { data.value.add(it) }
                }
                // здесь можно обновить список заметок в UI
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Profile Screen", "Failed to read value.", error.toException())
            }
        })
    }

    fun onClickInfoSaved(
        firstName: String,
        lastName: String,
        middleName: String,
        age: String,
        mail: String
    ) {
        val id = profileReferences?.push()?.key ?: return
//        val info = ProfileUserInfo(
//            name = firstName,
//            lastName = lastName,
//            middleName = middleName,
//            age = age,
//            email = mail
//        )

        val updates = mutableMapOf<String, Any>()
        updates["/name"] = firstName
        updates["/lastName"] = lastName
        updates["/middleName"] = middleName
        updates["/age"] = age
        updates["/email"] = mail
        profileReferences!!.child(id).updateChildren(updates)
    }
}