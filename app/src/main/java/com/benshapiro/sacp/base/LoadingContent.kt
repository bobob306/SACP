package com.benshapiro.sacp.base

import androidx.compose.foundation.layout.Row
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun LoadingContent() {
    Row() {
        CircularProgressIndicator()
    }
    Row() {
        Text(text = "Please wait while we try to set up your account")
    }
}