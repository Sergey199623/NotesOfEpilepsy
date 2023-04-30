package com.belyakov.notesforepilepsy.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.belyakov.notesforepilepsy.MainActivity

fun Context.relaunchApplication() {
    if (this is Activity) {
        finish()
    }
    val intent = Intent(this, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
}