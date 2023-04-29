package com.belyakov.auth.presentation

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.belyakov.auth.data.RegistrationUserData
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SharedAuthViewModel (
    databaseUrl: String
) : ViewModel() {

    private var database: FirebaseDatabase? = null
    private var currentReferences: DatabaseReference? = null

    init {
        database = FirebaseDatabase.getInstance(databaseUrl)
        currentReferences = database?.getReference("userData")
    }

    fun submitRegistrationData(registrationData: RegistrationUserData, context: Activity) {
        // Сохраняем данные в Firebase Realtime Database
        val dataId = currentReferences?.push()?.key
        if (dataId != null) {
            currentReferences?.child(dataId)?.setValue(registrationData)
        }

        // Отправляем код подтверждения на телефонный номер пользователя
        val options = PhoneAuthOptions.newBuilder(Firebase.auth)
            .setPhoneNumber(registrationData.phoneNumber)
            .setTimeout(60L, java.util.concurrent.TimeUnit.SECONDS)
            .setActivity(context)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}