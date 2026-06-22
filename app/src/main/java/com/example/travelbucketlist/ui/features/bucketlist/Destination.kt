package com.example.travelbucketlist.ui.features.bucketlist

import android.net.Uri

data class Destination(
    val name: String,
    val country: String,
    val imageUri: Uri? = null
)