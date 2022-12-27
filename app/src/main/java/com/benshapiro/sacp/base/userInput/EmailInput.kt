package com.benshapiro.sacp.base.userInput

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.benshapiro.sacp.base.SACPErrorHandlingInputState
import com.benshapiro.sacp.base.SACPErrorHandlingUserInput2
import com.benshapiro.sacp.utils.InputValidator

@Composable
fun EmailInput(email: SACPErrorHandlingInputState) {
    Row() {
        SACPErrorHandlingUserInput2(
                modifier = Modifier.fillMaxWidth(),
                state = email,
                onValueChange = {
                    email.text = it
                    val error = InputValidator.getEmailErrorIdOrNull(email.text ?: "")
                    email.boxErrorMessage = error
                },
                keyboardOptions = KeyboardOptions(KeyboardCapitalization.None, false,
                                                  KeyboardType.Email)
        )
    }
}