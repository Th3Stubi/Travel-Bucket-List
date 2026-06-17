package com.example.travelbucketlist

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.initialize

/**
 * Custom Application class to initialize global libraries like Firebase
 * before the first screen (MainActivity) renders.
 */
class TravelApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
    }
}