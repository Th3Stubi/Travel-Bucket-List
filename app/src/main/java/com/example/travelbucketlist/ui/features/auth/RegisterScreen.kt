package com.example.travelbucketlist.ui.features.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.travelbucketlist.ui.theme.Dimens
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import com.example.travelbucketlist.R

/**
 * Displays the register user interface.
 * This screen handles the layout and UI components.
 */
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = viewModel(),
    onRegisterSuccess: () -> Unit
) {
    LaunchedEffect(viewModel.isRegistrationSuccessful) {
        if (viewModel.isRegistrationSuccessful) {
            onRegisterSuccess()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // Main container for the register UI
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val customWidthModifier = Modifier.fillMaxWidth(0.85f)

            RegisterHeader(modifier = customWidthModifier)

            Spacer(modifier = Modifier.height(Dimens.spacingLarge))

            RegisterFields(
                nameValue = viewModel.nameInput,
                onNameChange = { viewModel.onNameChanged(it) },
                emailValue = viewModel.emailInput,
                onEmailChange = { viewModel.onEmailChanged(it) },
                passwordValue = viewModel.passwordInput,
                onPasswordChange = { viewModel.onPasswordChanged(it) },
                modifier = customWidthModifier,
            )

            Spacer(modifier = Modifier.height(Dimens.spacingLarge))

            RegisterButtons(
                onRegisterClick = { viewModel.registerUser() },
                modifier = customWidthModifier
            )
        }
    }
}

@Composable
fun RegisterHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.register_header_create_account),
            fontSize = Dimens.fontTitle,
            fontWeight = Bold,
            modifier = Modifier.padding(vertical = Dimens.spacingSmall)
        )
        Text(
            text = stringResource(R.string.register_header_start_your_travel_journey),
            fontSize = Dimens.fontMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
    }
}

@Composable
fun RegisterFields(
    modifier: Modifier = Modifier,
    nameValue: String,
    onNameChange: (String) -> Unit,
    emailValue: String,
    onEmailChange: (String) -> Unit,
    passwordValue: String,
    onPasswordChange: (String) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.spacingMedium)
    ) {
        OutlinedTextField(
            value = nameValue,
            onValueChange = onNameChange,
            label = { Text(text = stringResource(R.string.common_name)) },
            placeholder = { Text(text = stringResource(R.string.register_placeholder_your_name)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = emailValue,
            onValueChange = onEmailChange,
            label = { Text(text = stringResource(R.string.common_email)) },
            placeholder = { Text(text = stringResource(R.string.register_placeholder_example_mail)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        var isPasswordVisible by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = passwordValue,
            onValueChange = onPasswordChange,
            label = { Text(text = stringResource(R.string.common_password)) },
            placeholder = { Text(text = stringResource(R.string.register_placeholder_your_password)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            visualTransformation = if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                val image = if (isPasswordVisible) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }

                val description = if (isPasswordVisible) "Hide password" else "Show password"

                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun RegisterButtons(
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onRegisterClick,
        ) {
            Text(
                text = stringResource(R.string.register_button_create_account),
                fontSize = Dimens.fontMedium
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.register_info_already_have_an_account),
            )

            TextButton(onClick = { /* TODO: Navigate to RegisterScreen */ }
            ) {
                Text(
                    text = stringResource(R.string.register_textbutton_sign_in),
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(onRegisterSuccess = {})
}