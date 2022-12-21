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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    val selectedPictureLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

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

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload()
        }
    }
}

private fun reload() {}

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