package com.example.travelbucketlist.ui.features.auth

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    var emailInput by mutableStateOf("")
        private set

    var passwordInput by mutableStateOf("")
        private set

    fun onEmailChanged(newValue: String) {
        emailInput = newValue
    }

    fun onPasswordChanged(newValue: String) {
        passwordInput = newValue
    }

    var isLoginSuccessful by mutableStateOf(false)
        private set

    fun loginUser() {
        if (emailInput.isBlank() || passwordInput.isBlank()) {
            Log.d("LoginViewModel", "Error: Fields cannot be empty")
            return
        }

        auth.signInWithEmailAndPassword(emailInput, passwordInput)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("LoginViewModel", "Success: User logged in")
                    isLoginSuccessful = true
                } else {
                    Log.e("LoginViewModel", "Failure: ", task.exception)
                }
            }
    }
}