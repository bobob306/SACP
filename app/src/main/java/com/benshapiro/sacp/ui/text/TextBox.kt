package com.benshapiro.sacp.ui.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SACPTextBox(
    text: String
) {
    Text(
        text = text
    )
}

@Composable
fun SACPHeaderTextBox(
    text: String,
    modifier: Modifier,
) {
    Box(modifier = Modifier
        .padding(start = 12.dp),
        contentAlignment = CenterStart
    ) {
        Text(
            text = text,
            style = typography.h4,
            maxLines = 1,
            modifier = modifier,
        )
    }
}

@Preview
@Composable
private fun previewSACPTextBox(
) {
    val testString = "Welcome to SACP"
    SACPTextBox(text = testString)
}

@Preview
@Composable
private fun previewSACPHeaderTextBox(
) {
    val testString = "Welcome to SACP"
    SACPHeaderTextBox(text = testString, modifier = Modifier)
}