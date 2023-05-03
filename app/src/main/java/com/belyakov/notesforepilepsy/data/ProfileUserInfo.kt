package com.belyakov.notesforepilepsy.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileUserInfo(
    val name: String,
    val lastName: String,
    val middleName: String,
    val age: String,
    val email: String
) : Parcelable {
    constructor(name: String) : this("", "", "", "", "")
}