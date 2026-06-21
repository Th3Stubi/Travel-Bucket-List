package com.example.travelbucketlist.ui.features.settings

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SettingsViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    val currentUserEmail: String?
        get() = auth.currentUser?.email

    // onResult ist eine Funktion, die wir reinreichen — Firebase ruft sie auf,
    // sobald die E-Mail verschickt wurde (oder es fehlgeschlagen ist).
    // true = hat geklappt, false = nicht
    fun resetPassword(onResult: (Boolean) -> Unit) {
        val email = auth.currentUser?.email ?: return
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                onResult(task.isSuccessful)
            }
    }

    fun logout() {
        auth.signOut()
    }
}