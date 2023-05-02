package com.belyakov.notesforepilepsy.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileUserInfo(
    var name: String,
    var lastName: String,
    var middleName: String,
    var age: String,
    var email: String
) : Parcelable {
//    constructor(name: String) : this("", "", "", "", "")
}