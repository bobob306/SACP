package com.benshapiro.sacp.ui.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SACPButton(
    buttonName: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Button(
            onClick = onClick,
            interactionSource = MutableInteractionSource(),
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.CenterVertically)
                .widthIn(min = 200.dp),
            enabled = enabled
        ) {
            Text(
                text = buttonName,
                modifier = Modifier
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSACPButton() {
    val btnName = "Upload Image"
    SACPButton(
        buttonName = btnName,
        onClick = {}
    )
}