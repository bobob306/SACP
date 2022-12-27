package com.benshapiro.sacp.base

import android.content.Context
import android.widget.Toast

fun showToastWithText(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}