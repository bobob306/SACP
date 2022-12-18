package com.benshapiro.sacp.screen

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benshapiro.sacp.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val defaultImage: Int = R.drawable.sacp
    val imageInput = "image/*"
    val result = mutableStateOf<Uri?>(null)

    val imageStorageRef = Firebase.storage.reference
//    val file = result.value.toString()
//    val fileName = file.substringAfterLast("/")
//    val uploadTask = imageStorageRef.child(fileName).putFile(file.toUri())

    fun uploadTask() {
        val file:String = result.value.toString()
        val fileName:String = file.substringAfterLast("/")
        viewModelScope.launch ( Dispatchers.Default ) {
            Log.d("init ", "upload")
            Log.d("file url is ", file)
            Log.d("file url is ", result.value.toString())
            Log.d("file name is ", result.value.toString().substringAfterLast("/"))
            imageStorageRef.child(fileName).putFile(result.value.toString().toUri()).addOnSuccessListener {
                Log.d("outcome ", "success")
                result.value = null
            }.addOnFailureListener{
                Log.d("outcome ", "fail")
            }
        }
    }
}