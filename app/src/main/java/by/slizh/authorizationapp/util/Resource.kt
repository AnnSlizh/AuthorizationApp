package by.slizh.authorizationapp.util

sealed class Resource<T>(
    open val data: T? = null,
    open val message: String? = null
) {
    data class Success<T>(override val data: T) : Resource<T>(data)
    class Error<T>(override val message: String) : Resource<T>(message = message)
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>()
}
