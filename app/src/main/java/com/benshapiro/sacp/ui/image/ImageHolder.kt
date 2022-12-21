package com.benshapiro.sacp.ui.image

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.benshapiro.sacp.R

@Composable
fun SACPLogo(
    image: Int,
    contentDescription: String?,
) {
    val noContDesc = "no content description provided"
    Image(
            painter = painterResource(id = image),
            if (contentDescription.isNullOrEmpty()) noContDesc else contentDescription,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(48.dp),
    )
}

@Composable
fun SACPImage(
    image: Int? = R.drawable.sacp,
    imageUri: Uri? = null,
) {
    val contDesc = "Your uploaded image"
    val defaultImage = R.drawable.sacp_profile_pic
    if (imageUri == null) {
        Image(
                painter = painterResource(id = image!!),
                contentDescription = contDesc,
        )
    } else {
        AsyncImage(model = imageUri, contentDescription = contDesc)
    }
}

@Composable
fun SACPHeaderImage(
    image: Int = R.drawable._56_sacp_master_logo_rgb,
    contDesc: String = "SACP Header"
){
    Row(modifier = Modifier
        .padding(vertical = 12.dp)
        .fillMaxWidth()
    ) {
        Image(painter = painterResource(id = image), contentDescription = contDesc)
    }
}

@Preview
@Composable
private fun previewImage() {
    SACPLogo(image = R.drawable.sacp, contentDescription = "nothing")
}