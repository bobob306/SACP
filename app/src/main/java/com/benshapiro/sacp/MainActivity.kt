package com.benshapiro.sacp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.benshapiro.sacp.navigation.Navigation
import com.benshapiro.sacp.ui.theme.SACPTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val selectedPictureLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SACPTheme {
                Navigation()
                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SACPTheme {
        Greeting("Android")
    }
}