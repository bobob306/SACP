package com.benshapiro.sacp.base.userInput

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.benshapiro.sacp.base.SACPErrorHandlingInputState
import com.benshapiro.sacp.base.SACPErrorHandlingUserInput2
import com.benshapiro.sacp.utils.InputValidator

@Composable
fun ExistingPasswordInput(existingPassword: SACPErrorHandlingInputState) {
    Row() {
        SACPErrorHandlingUserInput2(
                modifier = Modifier
                    .fillMaxWidth(),
                state = existingPassword,
                onValueChange = {
                    existingPassword.text = it
                    val error = InputValidator.passwordEnteredErrorIdOrNull(
                            existingPassword.text ?: "")
                    existingPassword.boxErrorMessage = error
                },
                keyboardOptions = remember {
                    KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Password,
                    )
                },
                password = true,
        )
    }
}

@Composable
fun NewPasswordInput(newPassword: SACPErrorHandlingInputState) {
    Row() {
        SACPErrorHandlingUserInput2(
                modifier = Modifier
                    .fillMaxWidth(),
                state = newPassword,
                onValueChange = {
                    newPassword.text = it
                    val error = InputValidator.newPasswordErrorIdOrNull(
                            newPassword.text ?: "")
                    newPassword.boxErrorMessage = error
                },
                keyboardOptions = remember {
                    KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Password,
                    )
                },
                password = true,
        )
    }
}
