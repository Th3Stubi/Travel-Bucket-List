package com.example.travelbucketlist.ui.features.bucketlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.travelbucketlist.ui.theme.Dimens

/**
 * The main screen for displaying the user's travel bucket list.
 * It handles the tab state and displays the corresponding list.
 */
@Composable
fun BucketListScreen(modifier: Modifier = Modifier) {
    // States: 0 = All, 1 = Pending, 2 = Visited
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        BucketListTabs(
            selectedTabIndex = selectedTabIndex,
            onTabSelected = { newIndex -> selectedTabIndex = newIndex },
            modifier = Modifier.fillMaxWidth()
        )

        BucketListContent(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun BucketListTabs(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabs = listOf("All", "Pending", "Visited")

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
    selectedTabIndex: Int,
    modifier: Modifier = Modifier
) {
    val currentFilterText = when (selectedTabIndex) {
        0 -> "All Destinations"
        1 -> "Pending Destinations"
        2 -> "Visited Destinations"
        else -> "Unknown"
    }

    // for scrollable lists
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(Dimens.spacingMedium),
        verticalArrangement = Arrangement.spacedBy(Dimens.spacingMedium)
    ) {
        // debug list
        items(5) { index ->
            DestinationCard(
                title = "City #${index + 1}",
                country = currentFilterText
            )
        }
    }
}

@Composable
fun DestinationCard(
    title: String,
    country: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(Dimens.spacingMedium)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Country: $country",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BucketListScreenPreview() {
    BucketListScreen()
}