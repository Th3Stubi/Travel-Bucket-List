package com.example.travelbucketlist.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Global dimensions architecture for the application.
 * Ensures consistent padding, spacing, and font sizes across all screens.
 */
object Dimens {
    // Spacing & Padding (Based on the 8dp grid)
    val spacingSmall: Dp = 8.dp
    val spacingMedium: Dp = 16.dp
    val spacingLarge: Dp = 24.dp
    val spacingExtraLarge: Dp = 32.dp

    // Element Sizes
    val minimumTouchTarget: Dp = 48.dp
    val buttonHeight: Dp = 48.dp
    val cardElevation: Dp = 4.dp

    // Typography Font Sizes (Always use SP for text)
    val fontSmall: TextUnit = 12.sp
    val fontMedium: TextUnit = 16.sp
    val fontLarge: TextUnit = 22.sp
    val fontTitle: TextUnit = 32.sp

    // Icons
    val iconSmall: Dp = 28.dp
    val iconMedium: Dp = 40.dp
    val iconLarge: Dp = 56.dp
}