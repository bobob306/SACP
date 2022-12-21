package com.benshapiro.sacp.screen.auth

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthScreenViewModel @Inject constructor(
    private val handle: SavedStateHandle
) : ViewModel() {

}