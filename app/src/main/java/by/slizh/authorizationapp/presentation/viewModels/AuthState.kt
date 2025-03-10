package by.slizh.authorizationapp.presentation.viewModels

data class AuthState(
    val isLoading: Boolean = false,
    val verificationId: String? = null,
    val isAuthenticated: Boolean = false,
    val error: String? = null,
    val canResendCode: Boolean = true,
    val resendTimer: Int = 0
)
