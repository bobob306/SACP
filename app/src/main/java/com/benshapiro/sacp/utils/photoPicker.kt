package com.benshapiro.sacp.utils

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

interface photoPicker {

    @Composable
    fun photoPicker() {
        val result = remember { mutableStateOf<Uri?>(null) }
        val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()) {
            result.value = it
        }
    }
}