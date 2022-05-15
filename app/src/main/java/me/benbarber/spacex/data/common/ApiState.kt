package me.benbarber.spacex.data.common

sealed interface ApiState<T> {
    data class Success<T>(val data: T) : ApiState<T>
    data class Loading<T>(val data: T? = null) : ApiState<T>
    data class Failure<T>(val error: Throwable, val data: T? = null) : ApiState<T>
}
