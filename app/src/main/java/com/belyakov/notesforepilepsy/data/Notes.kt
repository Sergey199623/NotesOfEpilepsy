package com.belyakov.notesforepilepsy.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Notes(
    val title: String,
    val description: String,
    val dateOfCreate: String,
) : Parcelable