package com.benshapiro.sacp.screen.main

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.benshapiro.sacp.R
import com.benshapiro.sacp.ui.button.SACPButton
import com.benshapiro.sacp.ui.image.SACPHeaderImage
import com.benshapiro.sacp.ui.image.SACPImage


@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = viewModel(),
) {
    val defaultImage: Int = R.drawable.sacp
    val imageInput = "image/*"
    val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()) {
        viewModel.result.value = it
        Log.d("uri is", viewModel.result.value.toString())
    }
//    var bitmap: ImageBitmap? = viewModel.bitmapImage.value?.asImageBitmap()

//    val imageStorageRef = Firebase.storage.reference
//    var file = result.value.toString()
//    val fileName = file.substringAfterLast("/")
//    val uploadTask = imageStorageRef.child(fileName).putFile(file.toUri())
//    uploadTask.addOnFailureListener {
//        Log.d("Sad", " Fail")
//    }.addOnSuccessListener { taskSnapshot ->
//        Log.d("happy", " SUCCESS")
//        result.value = null
//    }

    val scrollState = rememberScrollState(0)
    Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
    ) {
        Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier
                    .verticalScroll(enabled = true, state = scrollState)
        ) {
            SACPHeaderImage()
            Row() {
                SACPButton(
                        buttonName = "Select Image",
                        onClick = { launcher.launch(imageInput) }
                )

            }
            Row() {
                SACPButton(
                        buttonName = "Upload Image",
                        onClick = { viewModel.uploadTask() }
                )
            }
            Row() {
                SACPButton(
                        buttonName = "Download Image",
                        onClick = { viewModel.downloadTask() }
                )
            }
            Row(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .heightIn(max = 400.dp)
                        .padding(vertical = 12.dp)
                        .alpha(if (viewModel.result.value != null) 1f else 0.3f),
                    horizontalArrangement = Center,
            ) {
                SACPImage(imageUri = viewModel.result.value)
            }
        }
//        bitmap.let {
//            LazyColumn(
//                    horizontalAlignment = Alignment.Start,
//                    verticalArrangement = Arrangement.Top,
//                    modifier = Modifier
//                        .padding(all = 16.dp)
//                        .fillMaxSize()
//            ) {
//                items(
//                        count = 1
//                ) { image ->
//                    bitmap?.let { it1 -> Image(bitmap = it1, contentDescription = "") }
//                }
//            }
//        }
    }
}


