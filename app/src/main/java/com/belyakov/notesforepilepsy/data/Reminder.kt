package com.belyakov.notesforepilepsy.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Reminder(
    val event: Events,
    val notification: NotificationEvent
) : Parcelable