package com.example.travelbucketlist.ui.features.bucketlist

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.travelbucketlist.R
import com.example.travelbucketlist.ui.theme.Dimens
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.graphics.Color

/**
 * The main screen for displaying the user's travel bucket list.
 * It handles the tab state and displays the corresponding list.
 */
@Composable
fun BucketListScreen(
    viewModel: BucketListViewModel,
    onDestinationClick: (Destination) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        BucketListContent(
            destinations = viewModel.destinations,
            modifier = Modifier.fillMaxSize(),
            onDestinationClick = onDestinationClick,
            onDestinationDelete = { viewModel.removeDestination(it) },
        )
    }
}

@Composable
fun BucketListTabs(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabs = listOf(
        stringResource(R.string.bucketlist_tab_all),
        stringResource(R.string.bucketlist_tab_pending),
        stringResource(R.string.bucketlist_tab_visited)
    )

    PrimaryTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                text = { Text(text = title) }
            )
        }
    }
}

@Composable
fun BucketListContent(
    destinations: List<Destination>,
    onDestinationClick: (Destination) -> Unit,
    onDestinationDelete: (Destination) -> Unit,
    modifier: Modifier = Modifier
) {
    if (destinations.isEmpty()) {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            Text(text = stringResource(R.string.bucketlist_info_no_locations_added_yet))
        }
        return
    }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(Dimens.spacingMedium),
        verticalArrangement = Arrangement.spacedBy(Dimens.spacingMedium)
    ) {
        items(destinations) { destination ->
            DestinationCard(
                title = destination.name,
                country = destination.country,
                imageUri = destination.imageUri,
                onClick = { onDestinationClick(destination) },
                onDelete = { onDestinationDelete(destination) }
            )
        }
    }
}

@Composable
fun DestinationCard(
    title: String,
    country: String,
    imageUri: Uri?,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.spacingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (imageUri != null) {
                AsyncImage(
                    model = imageUri,
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(Dimens.iconLarge)
                        .clip(RoundedCornerShape(Dimens.spacingSmall))
                )
                Spacer(modifier = Modifier.width(Dimens.spacingMedium))
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.bucketlist_info_country) + ": " + country,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                )
            }
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Löschen",
                    tint = Color.Red
                )
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun BucketListScreenPreview() {
    BucketListScreen(viewModel = BucketListViewModel(), onDestinationClick = {})
}