package com.belyakov.notesforepilepsy.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NotificationEvent(
    val title: String,
    val time: Long,
    val description: String,
) : Parcelable
