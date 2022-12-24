package com.benshapiro.sacp.screen.auth

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.benshapiro.sacp.base.SACPErrorHandlingInputState
import com.benshapiro.sacp.utils.InputValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

const val EMAIL = "email"
const val PASSWORDCREATION = "passwordCreation"
const val EXISTINGPASSWORD = "existingPassword"

@HiltViewModel
class AuthScreenViewModel @Inject constructor(
    private val handle: SavedStateHandle,
) : ViewModel() {
    val emailInput = handle.getStateFlow(EMAIL, SACPErrorHandlingInputState(
            "Email address",
            "Enter email address", ))
    fun onEmailEntered(input: String) {
        val error = InputValidator.getEmailErrorIdOrNull(input)
        emailInput.value.boxErrorMessage = error
    }

    val passwordCreationInput = handle.getStateFlow(PASSWORDCREATION, SACPErrorHandlingInputState(
            "Create new password",
            "Create a new strong password"
    ))
    fun onNewPasswordEntered(input: String) {
        val error = InputValidator.newPasswordErrorIdOrNull(input)
        passwordCreationInput.value.boxErrorMessage = error
    }
    val enterPassword = handle.getStateFlow(EXISTINGPASSWORD, SACPErrorHandlingInputState(
            "Enter your password",
            "Enter your password"
    ))
    fun onPasswordEntered(input: String) {
        val error = InputValidator.passwordEnteredErrorIdOrNull(input)
        enterPassword.value.boxErrorMessage = error
    }
}