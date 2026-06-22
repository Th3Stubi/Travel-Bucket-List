package com.example.travelbucketlist.ui.features.bucketlist

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import com.example.travelbucketlist.R
import com.example.travelbucketlist.ui.theme.Dimens

@Composable
fun AddLocationScreen(
    viewModel: BucketListViewModel,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var nameInput by remember { mutableStateOf("") }
    var countryInput by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_IMAGES
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        selectedImageUri = uri
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            galleryLauncher.launch("image/*")
        }
    }

    fun openGallery() {
        val hasPermission = ContextCompat.checkSelfPermission(
            context, imagePermission
        ) == PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            galleryLauncher.launch("image/*")
        } else {
            permissionLauncher.launch(imagePermission)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Dimens.spacingMedium)
            .padding(top = Dimens.spacingLarge)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.common_cd_goback_icon)
                )
            }
            Spacer(modifier = Modifier.width(Dimens.spacingSmall))
            Text(text = stringResource(R.string.addlocation_button_add_location))
        }

        Spacer(modifier = Modifier.height(Dimens.spacingLarge))

        OutlinedTextField(
            value = nameInput,
            onValueChange = { nameInput = it },
            label = { Text(stringResource(R.string.addlocation_otf_name)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(Dimens.spacingMedium))

        OutlinedTextField(
            value = countryInput,
            onValueChange = { countryInput = it },
            label = { Text(stringResource(R.string.common_text_country)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(Dimens.spacingLarge))

        if (selectedImageUri != null) {
            AsyncImage(
                model = selectedImageUri,
                contentDescription = stringResource(R.string.addlocation_image_cd_chosen_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(Dimens.spacingSmall))
            )
            Spacer(modifier = Modifier.height(Dimens.spacingMedium))
        }

        OutlinedButton(
            onClick = { openGallery() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (selectedImageUri != null) stringResource(R.string.addlocation_button_chose_other_image) else stringResource(
                    R.string.addlocation_button_choose_image
                )
            )
        }

        Spacer(modifier = Modifier.height(Dimens.spacingLarge))

        Button(
            onClick = {
                if (nameInput.isNotBlank() && countryInput.isNotBlank()) {
                    viewModel.addDestination(nameInput, countryInput, selectedImageUri)
                    onBackClick()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.addlocation_button_save))
        }
    }
}