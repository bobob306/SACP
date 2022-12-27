package com.benshapiro.sacp.utils

import androidx.compose.runtime.Composable
import com.benshapiro.sacp.base.SACPErrorHandlingInputState

object enabledRules {
    @Composable
    fun enabledRules(
        email: SACPErrorHandlingInputState,
        newPassword: SACPErrorHandlingInputState,
        existingPassword: SACPErrorHandlingInputState,
        newAccount: Boolean,
    ): Boolean {
        var emailIsHint = !email.isHint
        var ePandNPIsHint = !(existingPassword.isHint && newPassword.isHint)
        var ePandNPIsBlank = !(newPassword.text.equals("") && existingPassword.text
            .equals(""))

        return when {
            emailIsHint && ePandNPIsHint && ePandNPIsBlank && !newAccount &&
                    existingPassword.boxErrorMessage.isNullOrBlank() && !existingPassword.text
                .isNullOrBlank() && email.boxErrorMessage.isNullOrBlank() && !email.text.isNullOrBlank()
            -> true // returns true more easily as fewer validation rules on existing password
            emailIsHint && ePandNPIsHint && ePandNPIsBlank && newAccount &&
                    newPassword.boxErrorMessage.isNullOrBlank() && !newPassword.text
                .isNullOrBlank() && email.boxErrorMessage.isNullOrBlank() && !email.text.isNullOrBlank()
            -> true // more difficult to return true because more validation rules on new password
            else -> false
        }
    }
}