package com.benshapiro.sacp.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.benshapiro.sacp.base.*
import com.benshapiro.sacp.base.userInput.EmailInput
import com.benshapiro.sacp.base.userInput.ExistingPasswordInput
import com.benshapiro.sacp.base.userInput.NewPasswordInput
import com.benshapiro.sacp.ui.button.SACPButton
import com.benshapiro.sacp.ui.image.SACPHeaderImage
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
