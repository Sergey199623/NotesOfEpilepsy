package com.belyakov.notesforepilepsy.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.Duration
import java.time.LocalDateTime

@Parcelize
data class Events(
    val id: Long, // уникальный идентификатор
    val title: String, // заголовок события
    val time: LocalDateTime, // время события
    val isRecurring: Boolean, // флаг, указывающий, является ли событие повторяющимся
    val recurrenceInterval: Duration? = null // интервал между повторениями, если isRecurring=true
) : Parcelable