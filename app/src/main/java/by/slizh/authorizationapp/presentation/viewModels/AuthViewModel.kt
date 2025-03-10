package by.slizh.authorizationapp.presentation.viewModels

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.slizh.authorizationapp.domain.repository.AuthRepository
import by.slizh.authorizationapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authState = MutableStateFlow(AuthState())
    val authState = _authState.asStateFlow()

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.EnterPhoneNumber -> sendVerificationCode(event.phoneNumber, event.activity)
            is AuthEvent.EnterVerificationCode -> verifyCode(event.verificationId, event.code)
            is AuthEvent.ResendCode -> resendCode(event.phoneNumber, event.activity)
            AuthEvent.ResetState -> _authState.value = AuthState()
        }
    }

    private fun sendVerificationCode(phoneNumber: String, activity: Activity) {
        viewModelScope.launch {
            authRepository.sendVerificationCode(phoneNumber, activity)
                .collect { resource ->
                    when (resource) {
                        is Resource.Loading -> _authState.update { it.copy(isLoading = true) }
                        is Resource.Success -> _authState.update {
                            it.copy(verificationId = resource.data, isLoading = false)
                        }

                        is Resource.Error -> _authState.update {
                            it.copy(error = resource.message, isLoading = false)
                        }
                    }
                }
        }
    }

    private fun verifyCode(verificationId: String, code: String) {
        viewModelScope.launch {
            authRepository.verifyCode(verificationId, code)
                .collect { resource ->
                    when (resource) {
                        is Resource.Loading -> _authState.update { it.copy(isLoading = true) }
                        is Resource.Success -> _authState.update {
                            it.copy(isAuthenticated = resource.data ?: false, isLoading = false)
                        }

                        is Resource.Error -> _authState.update {
                            it.copy(error = resource.message, isLoading = false)
                        }
                    }
                }
        }
    }

    private fun resendCode(phoneNumber: String, activity: Activity) {
        viewModelScope.launch {
            authRepository.resendCode(phoneNumber, activity)
                .collect { resource ->
                    when (resource) {
                        is Resource.Loading -> _authState.update {
                            it.copy(isLoading = true, canResendCode = false, resendTimer = 30)
                        }

                        is Resource.Success -> startResendTimer()
                        is Resource.Error -> _authState.update {
                            it.copy(error = resource.message, isLoading = false)
                        }
                    }
                }
        }
    }

    private fun startResendTimer() {
        viewModelScope.launch {
            for (i in 30 downTo 1) {
                _authState.update { it.copy(resendTimer = i) }
                delay(1000L)
            }
            _authState.update { it.copy(canResendCode = true, resendTimer = 0) }
        }
    }
}



