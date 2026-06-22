package com.example.travelbucketlist.ui.features.settings

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.travelbucketlist.ui.theme.Dimens
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.IconButton

/**
 * The settings screen.
 */
@Composable
fun SettingsScreen(
    onLogout: () -> Unit,
    onBackClick: () -> Unit,
    viewModel: SettingsViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.spacingExtraLarge, start = Dimens.spacingLarge),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(com.example.travelbucketlist.R.string.settings_cd_back_button)
                )
            }
            Spacer(modifier = Modifier.width(Dimens.spacingSmall))

            Text(
                text = stringResource(com.example.travelbucketlist.R.string.settings_title),
                fontSize = Dimens.fontTitle
            )
        }

        // FIXME: Do not extract strings, those are placeholder values
        SettingsProfileCard(
            profileName = "John Doe",
            profileEmail = viewModel.currentUserEmail ?: "this@email.com"
        )

        Spacer(modifier = Modifier.height(Dimens.spacingLarge))

        SettingsResetPasswordButton(onClick = {
            viewModel.resetPassword { success ->
                val message = if (success) "Email sent" else "Error while sending"
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        })

        Spacer(modifier = Modifier.height(Dimens.spacingMedium))

        SettingsLogoutButton(onClick = {
            viewModel.logout()
            onLogout()
        })
    }
}

@Composable
fun SettingsResetPasswordButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.buttonHeight),
        onClick = onClick,
    ) {
        Icon(
            imageVector = Icons.Filled.Lock,
            contentDescription = stringResource(com.example.travelbucketlist.R.string.settings_cd_reset_password_icon)
        )

        Spacer(modifier = Modifier.width(Dimens.spacingSmall))

        Text(text = stringResource(com.example.travelbucketlist.R.string.settings_button_reset_password))
    }
}

@Composable
fun SettingsProfileCard(
    profileName: String,
    profileEmail: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.spacingMedium),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = stringResource(com.example.travelbucketlist.R.string.settings_cd_user_profile_picture),
                modifier = Modifier.size(Dimens.iconLarge),
                tint = Color.Gray,
            )

            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(Dimens.spacingSmall)
            ) {
                Text(
                    text = profileName,
                    fontSize = Dimens.fontMedium
                )
                Text(
                    text = profileEmail,
                    fontSize = Dimens.fontMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                )
            }
        }
    }
}

@Composable
fun SettingsLogoutButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.buttonHeight),
        border = BorderStroke(0.5.dp, Color.Red),
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.Red,
        ),
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Logout,
            contentDescription = stringResource(com.example.travelbucketlist.R.string.settings_cd_logout_icon)
        )

        Spacer(modifier = Modifier.width(Dimens.spacingSmall))

        Text(text = stringResource(com.example.travelbucketlist.R.string.settings_button_sign_out))
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(onLogout = {}, onBackClick = {})
}