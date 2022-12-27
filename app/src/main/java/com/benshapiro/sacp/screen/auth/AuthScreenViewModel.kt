package com.benshapiro.sacp.screen.auth

import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.benshapiro.sacp.base.SACPErrorHandlingInputState
import com.benshapiro.sacp.utils.InputValidator
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

const val EMAIL = "email"
const val PASSWORDCREATION = "passwordCreation"
const val EXISTINGPASSWORD = "existingPassword"
const val ERRORMESSAGESNACKBAR = "error message snackbar"

@HiltViewModel
class AuthScreenViewModel @Inject constructor(
    private val handle: SavedStateHandle,
) : ViewModel() {
    val emailInput = handle.getStateFlow(EMAIL, SACPErrorHandlingInputState(
            "Email address",
            "Enter email address",
    ))

    fun onEmailEntered(input: String) {
        val error = InputValidator.getEmailErrorIdOrNull(input)
        emailInput.value.boxErrorMessage = error
    }

    val passwordCreationInput = handle.getStateFlow(PASSWORDCREATION, SACPErrorHandlingInputState(
            "Create new password",
            "Create strong password"
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

    val errorMessageSnackbar = handle.getStateFlow(ERRORMESSAGESNACKBAR, mutableStateOf(false))

    val auth = Firebase.auth
    private fun createAccount(email: String, password: String) {
        if ((passwordCreationInput.value.boxErrorMessage == null &&
                    emailInput.value.boxErrorMessage == null)
        )
            try {

            } catch (e: Exception) {

            } else {
                showSnackbar("Error creating account")
        }
    }

    private fun showSnackbar(text: String) {
        errorMessageSnackbar.value.value = true
    }
}