package com.benshapiro.sacp.utils

import java.util.regex.Pattern

object InputValidator {
    const val invalidEmail = "Email address not valid"
    fun getEmailErrorIdOrNull(input: String): String? {
        return when {
            input.isNullOrEmpty() -> "Enter an email address"
            input.length < 7 -> "Email address too short"
            !input.contains("@") -> invalidEmail
            input.substringAfter("@").length < 5 -> invalidEmail
            input.substringAfterLast(".").length < 2 -> invalidEmail
            input.contains("..") -> invalidEmail
            input.contains(" ") -> invalidEmail
            !input.contains(".") -> invalidEmail
            !input.substringAfter("@").contains(".") -> invalidEmail
            input.substringAfter("@").contains("@") -> invalidEmail
            else -> null
        }
    }

    fun newPasswordErrorIdOrNull(input: String): String? {
        return when {
            input.isNullOrBlank() -> "Password must be at least eight characters long"
            input.length < 8 -> "Password must be at least eight characters long"
            !Pattern.compile("\\p{Nd}").matcher(input).find() ->
                "Password must contain at least one number"
            !Pattern.compile("\\p{Lu}").matcher(input).find() ->
                "Password must contain at least one lower case letter"
            !Pattern.compile("\\p{Ll}").matcher(input).find() ->
                "Password must contain at least one upper case letter"
            !Pattern.compile("[^A-Za-z0-9]").matcher(input).find() ->
                "Password must contain at least one special character"
            else -> null
        }
    }

    fun passwordEnteredErrorIdOrNull(input: String) : String? {
        return when {
            input.isNullOrBlank() -> "Password cannot be blank"
            else -> null
        }
    }
}