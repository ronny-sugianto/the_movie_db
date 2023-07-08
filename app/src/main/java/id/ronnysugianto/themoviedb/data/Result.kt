package id.ronnysugianto.themoviedb.data

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error<out T>(val exception: Throwable, val data: T? = null) : Result<T>()
    data class Loading<out T>(val data: T? = null) : Result<T>()
}