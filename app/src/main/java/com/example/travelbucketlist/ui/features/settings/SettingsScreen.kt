package com.example.travelbucketlist.ui.features.settings

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.filled.Language
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.travelbucketlist.R

/**
 * The settings screen.
 */
@SuppressLint("LocalContextConfigurationRead")
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

        SettingsProfileCard(
            profileEmail = viewModel.currentUserEmail ?: "your@email.com"
        )

        Spacer(modifier = Modifier.height(Dimens.spacingLarge))

        // Strings used for reset password toast message
        val resetSuccessMessage = stringResource(R.string.settings_reset_email_success)
        val resetErrorMessage = stringResource(R.string.settings_reset_email_error)

        SettingsResetPasswordButton(
            onClick = {
                viewModel.resetPassword { success ->
                    val message = if (success) resetSuccessMessage else resetErrorMessage
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.padding(horizontal = Dimens.spacingMedium)
        )

        Spacer(modifier = Modifier.height(Dimens.spacingMedium))

        val currentLocale = AppCompatDelegate.getApplicationLocales().toLanguageTags()
        val buttonText = if (currentLocale == "" || currentLocale.contains("en")) {
            "Zu Deutsch wechseln"
        } else {
            "Switch to English"
        }

        Spacer(modifier = Modifier.height(Dimens.spacingMedium))

        // Translator with the help of Claude coz Debugging
        var isGerman by remember {
            mutableStateOf(
                context.resources.configuration.locales[0].language == "de"
            )
        }

        OutlinedButton(
            onClick = {
                viewModel.toggleLanguage(context)
                isGerman = !isGerman
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.spacingMedium)
                .height(Dimens.buttonHeight)
        ) {
            Icon(
                imageVector = Icons.Filled.Language,
                contentDescription = stringResource(R.string.settings_cd_change_language_icon)
            )
            Spacer(modifier = Modifier.width(Dimens.spacingSmall))

            Text(text = if (isGerman) "Switch to English" else "Zu Deutsch wechseln")
        }

        Spacer(modifier = Modifier.height(Dimens.spacingLarge))

        SettingsLogoutButton(
            onClick = {
                viewModel.logout()
                onLogout()
            },
            modifier = Modifier.padding(horizontal = Dimens.spacingMedium)
        )
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