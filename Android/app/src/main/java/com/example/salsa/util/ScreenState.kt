package com.example.salsa.util

sealed class ScreenState<T> {
    class PreCall<T> : ScreenState<T>()
    class Loading<T> : ScreenState<T>()
    class Loaded<T>(val result: T) : ScreenState<T>()
    class Error<T>(val e: Exception) : ScreenState<T>()

    val isLoading get() = (this is Loading)
}