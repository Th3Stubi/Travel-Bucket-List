package com.example.travelbucketlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.travelbucketlist.ui.navigation.AppNavigation
import com.example.travelbucketlist.ui.theme.TravelBucketListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelBucketListTheme {
                Surface(color = MaterialTheme.colorScheme.background) {

                    AppNavigation()

                }
            }
        }
    }
}
