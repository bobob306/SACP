package com.benshapiro.sacp.screen.main

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val handle: SavedStateHandle,
) : ViewModel() {
    val result = mutableStateOf<Uri?>(null)
    var loading = mutableStateOf(true)
    private val _mostRecentImage = MutableLiveData<String?>()
    val mostRecentImage get() = _mostRecentImage
    private val _bitmapImage = MutableLiveData<Bitmap>(null)
    val bitmapImage get() = _bitmapImage

    private val _image = MutableLiveData<ByteArray?>(null)
    val image get() = _image

    val db = "gs://sacp-57b9f.appspot.com/"

    val imageStorageRef = Firebase.storage.reference

    fun uploadTask() {
        val file: String = result.value.toString()
        val fileName: String = file.substringAfterLast("/")
        viewModelScope.launch(Dispatchers.Default) {
            loading.value = true
            Log.d("init ", "upload")
            Log.d("file url is ", file)
            Log.d("file url is ", result.value.toString())
            Log.d("file name is ", result.value.toString().substringAfterLast("/"))
            imageStorageRef.child(fileName).putFile(result.value.toString().toUri())
                .addOnSuccessListener {
                    Log.d("outcome ", "success")
                    result.value = null
//                    viewModelScope.launch {
//                    }
                    viewModelScope.launch {
                        _mostRecentImage.postValue("gs://sacp-57b9f.appspot.com" + imageStorageRef.listAll()
                            .await().items.last().path)
                        Log.d("name is ", imageStorageRef.listAll().await().items.last().name)
                        Log.d("path is ",
                              "gs://sacp-57b9f.appspot.com/" + imageStorageRef.listAll()
                                  .await().items.last().name)
                        val localFile =
                            withContext(Dispatchers.IO) {
                                File.createTempFile("tempImg", "jpg")
                            }
                        imageStorageRef.getFile(mostRecentImage.value!!.toUri())
                            .addOnSuccessListener {
                                bitmapImage.postValue(BitmapFactory.decodeFile(localFile.absolutePath))
                            }
                    }
                }.addOnFailureListener {
                    Log.d("outcome ", "fail")
                }
            loading.value = false
        }
    }

    private fun downloadImage() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val maxDownloadSize = 10L * 1024 * 1024
            val bytes = imageStorageRef.child("1000000019.jpg")
                .getBytes(maxDownloadSize).await()
            _bitmapImage.setValue(BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Log.d("sad", "error")
            }
        }
    }

    fun downloadTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val maxDLS = 10L * 1024 * 1024
            val dl = imageStorageRef.listAll().result.items.last().getBytes(maxDLS).await()
            Log.d("dl =", dl.toString())
        }
    }

    val storage = Firebase.storage
    val listRef = storage.reference
}