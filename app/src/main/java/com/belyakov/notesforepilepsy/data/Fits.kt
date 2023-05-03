package com.belyakov.notesforepilepsy.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fits(
    val title: String,
    val description: String,
    val dateOfCreate: String,
    val id: String
) : Parcelable {
    constructor() : this("", "", "", "")
}

//https://notesofepilepsy-default-rtdb.europe-west1.firebasedatabase.app/