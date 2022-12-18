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
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp)
                .align(Alignment.CenterVertically)
                .width(200.dp),
            enabled = true
        ) {
            Text(
                text = buttonName,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
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