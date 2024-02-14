package com.alex.challengeapp.presentation.login

data class LoginState(
      val user: String = "",
      val password: String = "",
      val isError: Boolean = false,
      val isValidCredentials: Boolean = false
)
