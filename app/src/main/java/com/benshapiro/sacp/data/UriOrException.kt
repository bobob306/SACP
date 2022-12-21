package com.benshapiro.sacp.data

import javax.inject.Singleton

@Singleton
data class UriOrException<T, E : Exception?>(
    var uri: T? = null,
    var e: E? = null
)