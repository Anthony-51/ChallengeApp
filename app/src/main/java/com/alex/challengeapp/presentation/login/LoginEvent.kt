package com.alex.challengeapp.presentation.login

interface LoginEvent {
      data class OnUserChanged(val user: String) : LoginEvent
      data class OnPasswordChanged(val password: String) : LoginEvent
      object ValidateCredentials : LoginEvent
}