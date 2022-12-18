package com.benshapiro.sacp.ui.headerRow

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benshapiro.sacp.R
import com.benshapiro.sacp.ui.image.SACPLogo
import com.benshapiro.sacp.ui.text.SACPHeaderTextBox

@Composable
fun SACPHeaderRow() {
    val imageLogo = R.drawable.sacp
    val contentDescription = "SACP Logo"
    val text = "Welcome to SACP"
    Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
    ) {
        Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                horizontalAlignment = Start

        ) {
            SACPHeaderTextBox(
                    text = text,
                    modifier = Modifier
            )
        }
        Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = End
        ) {
            SACPLogo(
                    image = imageLogo,
                    contentDescription = contentDescription,
            )
        }
    }
}


@Preview
@Composable
private fun previewSACPHeaderRow() {
    SACPHeaderRow()
}