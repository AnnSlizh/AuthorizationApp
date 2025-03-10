package by.slizh.authorizationapp.presentation.viewModels

import android.app.Activity

sealed class AuthEvent {
    data class EnterPhoneNumber(val phoneNumber: String, val activity: Activity) : AuthEvent()
    data class EnterVerificationCode(val verificationId: String, val code: String) : AuthEvent()
    data class ResendCode(val phoneNumber: String, val activity: Activity) : AuthEvent()
    object ResetState : AuthEvent()
}
