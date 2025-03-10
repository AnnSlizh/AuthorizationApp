package by.slizh.authorizationapp.domain.repository

import android.app.Activity
import by.slizh.authorizationapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun sendVerificationCode(
        phoneNumber: String,
        activity: Activity
    ): Flow<Resource<String>>

    fun verifyCode(verificationId: String, code: String): Flow<Resource<Boolean>>
    fun resendCode(phoneNumber: String, activity: Activity): Flow<Resource<Unit>>
}
