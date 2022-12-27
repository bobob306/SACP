package com.benshapiro.sacp.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.benshapiro.sacp.base.LoadingContent
import com.benshapiro.sacp.base.SACPErrorHandlingInputState
import com.benshapiro.sacp.base.SACPErrorHandlingUserInput2
import com.benshapiro.sacp.ui.button.SACPButton
import com.benshapiro.sacp.ui.image.SACPHeaderImage
import com.benshapiro.sacp.utils.InputValidator
import com.benshapiro.sacp.utils.accountCreationLogic
import com.benshapiro.sacp.utils.enabledRules.enabledRules
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

val auth = Firebase.auth

@Composable
fun OAuthScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavController,
    scrollState: ScrollState = rememberScrollState(0),
) {
    OAuthScreen(
            scaffoldState = scaffoldState,
            scrollState = scrollState
    )
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun OAuthScreen(
    scaffoldState: ScaffoldState,
    scrollState: ScrollState,
) {
    val coroutineScope = rememberCoroutineScope()
    val email by remember {
        mutableStateOf(value = SACPErrorHandlingInputState(
                "Enter your email",
                "Enter your email"))
    }
    val existingPassword by remember {
        mutableStateOf(value = SACPErrorHandlingInputState(
                "Enter your password",
                "Enter your password"
        ))
    }
    val newPassword by remember {
        mutableStateOf(value = SACPErrorHandlingInputState(
                "Create strong password",
                "Create strong password"
        ))
    }
    var newAccount by remember {
        mutableStateOf(false)
    }
    var makeNewAccount by remember {
        mutableStateOf(false)
    }
    var login by remember {
        mutableStateOf(false)
    }
    var user by remember {
        mutableStateOf<FirebaseUser?>(null)
    }
    Scaffold(
            scaffoldState = scaffoldState,
            backgroundColor = MaterialTheme.colors.background,
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        Column(
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .wrapContentHeight()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
        ) {
            SACPHeaderImage()
            if (makeNewAccount) LoadingContent()
            else {
                EmailInput(email)
                if (newAccount) NewPasswordInput(newPassword)
                else ExistingPasswordInput(existingPassword)
                Row() {
                    var enabled by remember { mutableStateOf(false) }
                    enabled = enabledRules(email = email,
                                           newPassword = newPassword,
                                           existingPassword = existingPassword,
                                           newAccount = newAccount)
                    SACPButton(
                            buttonName = if (!newAccount) "Login" else
                                "Create account",
                            onClick = {
                                if (!newAccount) {
                                    login = true
                                    coroutineScope.launch {
                                        scaffoldState.snackbarHostState.showSnackbar(
                                                "Logging in", null,
                                                SnackbarDuration.Short
                                        )
                                    }
                                } else {
                                    makeNewAccount = true
                                }
//                                   TODO()
//                                    this is good working snackbar code, but not currently in use
//                                    coroutineScope.launch {
//                                        scaffoldState.snackbarHostState.showSnackbar(
//                                                "Creating account", null,
//                                                SnackbarDuration.Short
//                                        )
//
//                                    }
                            },
                            enabled = enabled
                    )
                }
                Row() {
                    SACPButton(
                            buttonName = if (!newAccount) "New account" else
                                "Existing account",
                            onClick = { newAccount = !newAccount })
                }
                SACPButton(
                        buttonName = "Forgotten password",
                        onClick = {}
                )
                SACPHeaderImage()
                SACPHeaderImage()
                SACPHeaderImage()
                SACPHeaderImage()
            }
        }
        if (makeNewAccount) {
            val ctx = LocalContext.current
            coroutineScope.launch {
                user = accountCreationLogic(email = email.text, password = newPassword.text, ctx)
            }
            makeNewAccount = !makeNewAccount
        }
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
