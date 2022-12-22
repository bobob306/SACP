package com.benshapiro.sacp.screen.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.benshapiro.sacp.R
import com.benshapiro.sacp.base.SACPErrorHandlingUserInput
import com.benshapiro.sacp.ui.button.SACPButton
import com.benshapiro.sacp.ui.image.SACPHeaderImage
import com.benshapiro.sacp.ui.text.SACPTextBox
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthScreenViewModel = viewModel(),
) {
    val TAG = "EmailPassword"
    val auth = Firebase.auth
    val scrollState = rememberScrollState(0)
    var newAccountState = remember { mutableStateOf(false) }
    Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
    ) {
        Column(horizontalAlignment = CenterHorizontally,
               modifier = Modifier
                   .verticalScroll(scrollState)
                   .padding(horizontal = 12.dp)
        )
        {
            SACPHeaderImage()
            Row(modifier = Modifier
                .padding(bottom = 12.dp)
                .fillMaxWidth()
            ) {
                SACPTextBox(text = stringResource(R.string.welcome_text) +
                        if (newAccountState.value == false) " login" else " create an account")
            }
            Row() {
                SACPErrorHandlingUserInput(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        onValueChange = viewModel::onEmailEntered,
                        keyboardOptions = remember {
                            KeyboardOptions(
                                    capitalization = KeyboardCapitalization.None,
                                    autoCorrect = false,
                            )
                        },
                        state = viewModel.emailInput,
                        password = false
                )
            }
            Row() {
                if (newAccountState.value) {
                    SACPErrorHandlingUserInput(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            onValueChange = viewModel::onPasswordEntered,
                            keyboardOptions = remember {
                                KeyboardOptions(
                                        capitalization = KeyboardCapitalization.None,
                                        autoCorrect = false,
                                        keyboardType = KeyboardType.Password,
                                )
                            },
                            state = viewModel.passwordCreationInput,
                            password = true
                    )
                } else {
                    SACPErrorHandlingUserInput(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            onValueChange = viewModel::onPasswordEntered,
                            keyboardOptions = remember {
                                KeyboardOptions(
                                        capitalization = KeyboardCapitalization.None,
                                        autoCorrect = false,
                                        keyboardType = KeyboardType.Password,
                                )
                            },
                            state = viewModel.enterPassword,
                            password = true
                    )
                }
            }
            Row() {
                if (!newAccountState.value) {
                    SACPButton(buttonName = "Login",
                               onClick = { })//signIn("", "") })
                } else {
                    SACPButton(buttonName = "Create account",
                               onClick = {}) //{ createAccount("", "") })
                }
            }
            Row() {
                SACPButton(
                        buttonName = if (newAccountState.value == false) "New account" else
                            "Existing account",
                        onClick = { newAccountState.value = !newAccountState.value })

            }
        }
    }


}

//private fun createAccount(email: String, password: String) {
//
//    auth.createUserWithEmailAndPassword("email", "password")
//        .addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                // Sign in success, update UI with the signed-in user's information
//                Log.d(TAG, "createUserWithEmail:success")
//                val user = auth.currentUser
//                updateUI(user)
//            } else {
//                // If sign in fails, display a message to the user.
//                Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                updateUI(null)
//            }
//        }
//}
//
//private fun signIn(email: String, password: String) {
//    // [START sign_in_with_email]
//
//    auth.signInWithEmailAndPassword(email, password)
//        .addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                // Sign in success, update UI with the signed-in user's information
//                Log.d(TAG, "signInWithEmail:success")
//                val user = auth.currentUser
//                updateUI(user)
//            } else {
//                // If sign in fails, display a message to the user.
//                Log.w(TAG, "signInWithEmail:failure", task.exception)
//                updateUI(null)
//            }
//        }
//    // [END sign_in_with_email]
//}

private fun updateUI(user: FirebaseUser?) {

}

@Composable
@Preview
private fun previewAuthScreen() {
    val scrollState = rememberScrollState(0)
    var newAccountState = remember { mutableStateOf(false) }
    Surface(
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
    ) {
        Column(horizontalAlignment = CenterHorizontally,
               modifier = Modifier.verticalScroll(scrollState))
        {
            Row() {
                // add email address text entry box
            }
            Row() {
                // add password text entry box
            }
            Row() {
                if (!newAccountState.value) {
                    SACPButton(buttonName = "Login",
                               onClick = { })
                } else {
                    SACPButton(buttonName = "Create account",
                               onClick = { })
                }
            }
            Row() {
                SACPButton(buttonName = "New account",
                           onClick = { newAccountState.value = true })

            }
        }
    }
}