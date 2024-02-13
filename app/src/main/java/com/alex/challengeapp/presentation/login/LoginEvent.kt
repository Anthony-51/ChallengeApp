package com.alex.challengeapp.presentation.login

interface LoginEvent {
      data class OnEmailChanged(val email: String) : LoginEvent
      data class OnPasswordChanged(val password: String) : LoginEvent
      object ValidateCredentials : LoginEvent
}