package com.example.travelbucketlist.ui.features.bucketlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.travelbucketlist.ui.theme.Dimens

@Composable
fun DestinationDetailScreen(
    viewModel: BucketListViewModel,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val destination = viewModel.selectedDestination

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
                    contentDescription = stringResource(com.example.travelbucketlist.R.string.common_cd_goback_icon)
                )
            }
        }

        Spacer(modifier = Modifier.height(Dimens.spacingLarge))

        if (destination != null) {
            if (destination.imageUri != null) {
                AsyncImage(
                    model = destination.imageUri,
                    contentDescription = destination.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(Dimens.spacingSmall))
                )
                Spacer(modifier = Modifier.height(Dimens.spacingMedium))
            }

            Text(text = destination.name, fontSize = Dimens.fontTitle)
            Spacer(modifier = Modifier.height(Dimens.spacingSmall))
            Text(
                text = stringResource(com.example.travelbucketlist.R.string.common_text_country) + ": " + destination.country,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}