package com.belyakov.auth.presentation

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedAuthViewModel (
    databaseUrl: String
) : ViewModel() {

    private var database: FirebaseDatabase? = null
    private var currentReferences: DatabaseReference? = null

    private val _isSuccessVerifyPhoneNumber = MutableStateFlow(false)
    val isSuccessVerify: StateFlow<Boolean> = _isSuccessVerifyPhoneNumber

    private val _isSuccessConfirmPhoneNumber = MutableStateFlow(false)
    val isSuccessConfirm: StateFlow<Boolean> = _isSuccessConfirmPhoneNumber

    init {
        database = FirebaseDatabase.getInstance(databaseUrl)
        currentReferences = database?.getReference("userData")
    }

    fun submitRegistrationData(phoneNumber: String, context: Activity) {
        // Сохраняем данные в Firebase Realtime Database
        val dataId = currentReferences?.push()?.key
        if (dataId != null) {
            currentReferences?.child(dataId)?.setValue(phoneNumber)
        }

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // Обработка успешной аутентификации
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d("SharedAuthViewModel", "onVerificationCompleted:$credential")
                signInWithPhoneAuthCredential(credential, context)
            }

            override fun onVerificationFailed(ex: FirebaseException) {
                Log.w("SharedAuthViewModel", "onVerificationFailed", ex)

                when (ex) {
                    is FirebaseAuthInvalidCredentialsException -> {
                        // Invalid request
                    }
                    is FirebaseTooManyRequestsException -> {
                        // The SMS quota for the project has been exceeded
                    }
                    is FirebaseAuthMissingActivityForRecaptchaException -> {
                        // reCAPTCHA verification attempted with null Activity
                    }
                }
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d("SharedAuthViewModel", "onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
//                storedVerificationId = verificationId
//                resendToken = token
            }
        }

        // Отправляем код подтверждения на телефонный номер пользователя
        val options = PhoneAuthOptions.newBuilder(Firebase.auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, java.util.concurrent.TimeUnit.SECONDS)
            .setActivity(context)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        _isSuccessVerifyPhoneNumber.value = true
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential, context: Activity) {
        Firebase.auth.signInWithCredential(credential)
            .addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }
}