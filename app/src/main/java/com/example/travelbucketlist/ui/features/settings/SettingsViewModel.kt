package com.example.travelbucketlist.ui.features.settings

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

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
    // Übersetzung mit der Hilfe von Claude wegen Config Bugs
    fun toggleLanguage(context: android.content.Context) {
        val config = context.resources.configuration
        val currentLocale = config.locales[0].language

        val newLocale = if (currentLocale == "de") {
            java.util.Locale("en")
        } else {
            java.util.Locale("de")
        }

        java.util.Locale.setDefault(newLocale)
        val newConfig = android.content.res.Configuration(config)
        newConfig.setLocale(newLocale)
        context.resources.updateConfiguration(newConfig, context.resources.displayMetrics)
    }

    fun logout() {
        auth.signOut()
    }
}