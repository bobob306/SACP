package com.benshapiro.sacp.base

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.benshapiro.sacp.R
import com.benshapiro.sacp.ui.theme.SacpGreen
import kotlinx.coroutines.flow.StateFlow

class SACPErrorHandlingInputState(
    private val boxName: String? = null,
    private val hint: String? = null,
    initialText: String? = hint ?: "",
    private val errorMessage: String? = null,
) {
    var text by mutableStateOf(initialText)
    val isHint: Boolean get() = text == hint
    var boxNameText by mutableStateOf(boxName)
    var boxErrorMessage by mutableStateOf(errorMessage)

    companion object {
        val Saver: Saver<SACPErrorHandlingInputState, *> = listSaver(
                save = {
                    listOf(it.boxName ?: "",
                           it.hint,
                           it.text,
                           it.errorMessage)
                }
        ) {
            SACPErrorHandlingInputState(
                    boxName = it[0] as String,
                    hint = it[1] as String,
                    initialText = it[2] as String,
                    errorMessage = it[3] as String,
            )
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SACPErrorHandlingUserInput(
    modifier: Modifier,
    state: StateFlow<SACPErrorHandlingInputState>,
    length: Int = state.value.text!!.length,
    onValueChange: (value: String) -> Unit,
    keyboardOptions: KeyboardOptions = remember { KeyboardOptions.Default },
    password: Boolean? = false,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()
    if (isPressed) if (state.value.isHint) state.value.text = ""
    var passwordVisibility: Boolean? by remember {
        mutableStateOf(password)
    }

    Column {
        OutlinedTextField(
                singleLine = true,
                interactionSource = interactionSource,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp),
                value = state.value.text!!,
                onValueChange = {
                    state.value.text = it
                    onValueChange(it)
                },
                isError = state.value.boxErrorMessage != null,
                keyboardOptions = keyboardOptions,
                placeholder = { state.value.text },
                label = {
                    Text(
                            text = state.value.boxNameText ?: "",
                            color = if (state.value.boxErrorMessage == null) {
                                SacpGreen
                            } else {
                                Color.Red
                            },
                    )
                },
                trailingIcon = {
                    if (passwordVisibility == true) {
                        IconButton(onClick = { passwordVisibility = !passwordVisibility!! }) {
                            Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_visibility_24),
                                    contentDescription = "Show password"
                            )
                        }
                    } else {
                        IconButton(onClick = { state.value.text = "" }) {
                            Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_clear_24),
                                    contentDescription = "Clear text button"
                            )
                        }
                    }
                },
                visualTransformation = if (passwordVisibility == true && !state.value.isHint) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                }
        )
        if (state.value.boxErrorMessage != null) {
            state.value.boxErrorMessage.let {
                Text(
                        text = state.value.boxErrorMessage!!,
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SACPErrorHandlingUserInput2(
    modifier: Modifier,
    state: SACPErrorHandlingInputState,
    length: Int = state.text!!.length,
    onValueChange: (value : String) -> Unit,
    keyboardOptions: KeyboardOptions = remember { KeyboardOptions.Default },
    password: Boolean? = false,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()
    if (isPressed) if (state.isHint) state.text = ""
    var passwordVisibility: Boolean? by remember {
        mutableStateOf(password)
    }

    Column {
        OutlinedTextField(
                singleLine = true,
                interactionSource = interactionSource,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                value = state.text!!,
                onValueChange = {
                    state.text = it
                    onValueChange(it)
                },
                isError = state.boxErrorMessage != null,
                keyboardOptions = keyboardOptions,
                placeholder = { state.text },
                label = {
                    Text(
                            text = state.boxNameText ?: "",
                            color = if (state.boxErrorMessage == null) {
                                SacpGreen
                            } else {
                                Color.Red
                            },
                    )
                },
                trailingIcon = {
                    if (password == true) {
                        IconButton(onClick = { passwordVisibility = !passwordVisibility!! }) {
                            Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_visibility_24),
                                    contentDescription = "Show password"
                            )
                        }
                    } else {
                        IconButton(onClick = { state.text = "" }) {
                            Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_clear_24),
                                    contentDescription = "Clear text button"
                            )
                        }
                    }
                },
                visualTransformation = if (passwordVisibility == true && !state.isHint) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                }
        )
        if (state.boxErrorMessage != null) {
            state.boxErrorMessage.let {
                Text(
                        text = state.boxErrorMessage!!,
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}


@Composable
fun rememberSACPEditableUserInputState(
    boxName: String?,
    hint: String,
    errorMessage: String,
): SACPErrorHandlingInputState =
    rememberSaveable(hint, saver = SACPErrorHandlingInputState.Saver) {
        // make the initial text the same as the hint text
        SACPErrorHandlingInputState(boxName, hint, hint, errorMessage)
    }

@Composable
fun rememberSACPErrorHandlingInput(
    boxName: String?,
    hint: String,
    errorMessage: String,
): SACPErrorHandlingInputState = rememberSaveable(hint, saver = SACPErrorHandlingInputState.Saver) {
    SACPErrorHandlingInputState(boxName, hint, hint, errorMessage)
}

// TODO make new password entry version of this, such a mess otherwise

@Composable
fun SACPOutlinedTextField(state: SACPErrorHandlingInputState) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()
    if (isPressed) {
        if (state.isHint) state.text = ""
    }
    OutlinedTextField(
            value = state.text!!,
            onValueChange = { state.text = it },
            placeholder = { state.text },
            label = {
                Text(
                        text = if (state.boxErrorMessage == "") {
                            state.boxNameText ?: ""
                        } else {
                            "${state.boxNameText} - ${state.boxErrorMessage}"
                        },
                        color = if (state.boxErrorMessage == "") {
                            Color.White
                        } else {
                            Color.Red
                        }
                )
            },
            trailingIcon = {
                IconButton(onClick = { state.text = "" }) {
                    Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_clear_24),
                            contentDescription = "Clear text button"
                    )
                }
            },
            singleLine = true,
            interactionSource = interactionSource,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp)
    )
}

@Composable
fun SACPBaseUserTextInput(
    state: SACPErrorHandlingInputState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    caption: String? = null, // describes the box on the left, e.g. input name box
    showCaption: () -> Boolean = { true }, // decides if the caption should be shown
    tint: Color = LocalContentColor.current,
    content: @Composable () -> Unit,
) {
    Row(
            Modifier.padding(all = 12.dp),
    ) {
        SACPOutlinedTextField(state)
    }
}



















