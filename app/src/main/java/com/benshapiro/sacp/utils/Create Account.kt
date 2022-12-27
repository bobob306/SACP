package com.benshapiro.sacp.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.benshapiro.sacp.base.showToastWithText
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

val auth = Firebase.auth

fun accountCreationLogic(email: String?, password: String?, ctx: Context): FirebaseUser? {
    var user: FirebaseUser? = null
    if (!email.isNullOrBlank() && !password.isNullOrBlank())
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "createUserWithEmail:success")
                    Toast.makeText(ctx, "Account created", Toast.LENGTH_SHORT)
                        .show()
                    user = auth.currentUser
                    Log.d("mega party", user?.uid ?: "null")
                } else {
                    // If sign in fails, display a message to the user
                    when (task.exception) {
                        is FirebaseAuthUserCollisionException ->
                            showToastWithText(ctx, "Email already exists")
                        else -> showToastWithText(ctx, "Error creating account")
                    }
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(ctx, "Account creation failed",
                                   Toast.LENGTH_SHORT).show()
                }
            }
    return user
}