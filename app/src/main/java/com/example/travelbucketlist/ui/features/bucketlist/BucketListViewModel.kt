package com.example.travelbucketlist.ui.features.bucketlist

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BucketListViewModel : ViewModel() {

    val destinations = mutableStateListOf<Destination>()

    var selectedDestination by mutableStateOf<Destination?>(null)
        private set

    fun addDestination(name: String, country: String, imageUri: Uri? = null) {
        destinations.add(Destination(name = name, country = country, imageUri = imageUri))
    }

    fun selectDestination(destination: Destination) {
        selectedDestination = destination
    }
    fun removeDestination(destination: Destination) {
        destinations.remove(destination)
    }
}