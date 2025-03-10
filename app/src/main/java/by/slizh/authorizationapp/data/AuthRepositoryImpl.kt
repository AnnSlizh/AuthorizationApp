package by.slizh.authorizationapp.data

import android.app.Activity
import by.slizh.authorizationapp.domain.repository.AuthRepository
import by.slizh.authorizationapp.util.Resource
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null

    override suspend fun sendVerificationCode(
        phoneNumber: String, activity: Activity
    ): Flow<Resource<String>> = callbackFlow {
        trySend(Resource.Loading())

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    trySend(Resource.Success(credential.smsCode ?: ""))
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    trySend(Resource.Error(e.localizedMessage ?: "Error"))
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    resendToken = token
                    trySend(Resource.Success(verificationId))
                }
            })
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    override suspend fun verifyCode(
        verificationId: String, code: String
    ): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        val result = try {
            firebaseAuth.signInWithCredential(credential).await()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Entry error", false)
        }
        emit(result)
    }

    override suspend fun resendCode(
        phoneNumber: String, activity: Activity
    ): Flow<Resource<Unit>> = callbackFlow {
        val token = resendToken ?: return@callbackFlow

        trySend(Resource.Loading())

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    trySend(Resource.Success(Unit))
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    trySend(Resource.Error(e.localizedMessage ?: "Resend code error"))
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    resendToken = token
                    trySend(Resource.Success(Unit))
                }
            })
            .setForceResendingToken(token)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}
