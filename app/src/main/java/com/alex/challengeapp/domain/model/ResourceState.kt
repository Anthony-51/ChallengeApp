package com.alex.challengeapp.domain.model

sealed class ResourceState<out T: Any> {
      data object Loading : ResourceState<Nothing>()
      data class Success<out T: Any>(val data: T) : ResourceState<T>()
      data class Error(val message: String, val code: Int = 0) : ResourceState<Nothing>()
}

