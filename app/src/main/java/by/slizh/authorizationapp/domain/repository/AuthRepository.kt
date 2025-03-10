package by.slizh.authorizationapp.domain.repository

import android.app.Activity
import by.slizh.authorizationapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun sendVerificationCode(
        phoneNumber: String,
        activity: Activity
    ): Flow<Resource<String>>

    suspend fun verifyCode(verificationId: String, code: String): Flow<Resource<Boolean>>
    suspend fun resendCode(phoneNumber: String, activity: Activity): Flow<Resource<Unit>>
}
