package com.example.travelbucketlist.ui.features.auth

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

/**
 * The ViewModel for the RegisterScreen.
 * It holds the screen state and handles the communication with Firebase Auth.
 */
class RegisterViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    var nameInput by mutableStateOf("")
        private set

    var emailInput by mutableStateOf("")
        private set

    var passwordInput by mutableStateOf("")
        private set

    fun onNameChanged(newValue: String) {
        nameInput = newValue
    }

    fun onEmailChanged(newValue: String) {
        emailInput = newValue
    }

    fun onPasswordChanged(newValue: String) {
        passwordInput = newValue
    }

    var isRegistrationSuccessful by mutableStateOf(false)
        private set

    /**
     * Triggers registration
     */
    fun registerUser() {
        if (emailInput.isBlank() || passwordInput.isBlank() || nameInput.isBlank()) {
            Log.d("RegisterViewModel", "Error: Fields cannot be empty")
            return
        }

        auth.createUserWithEmailAndPassword(emailInput, passwordInput)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Log.d("RegisterViewModel", "Success: User created with ID: ${user?.uid}")
                    isRegistrationSuccessful = true
                } else {
                    Log.e("RegisterViewModel", "Failure: ", task.exception)
                }
            }
    }
}