package com.alex.challengeapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.challengeapp.domain.use_cases.ValidateCredentials
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
      private val validateCredentials: ValidateCredentials
) :ViewModel(){

      private val _loginState = MutableStateFlow(LoginState())
      val loginState = _loginState.asStateFlow()


      private val _effect: Channel<LoginEffect> = Channel()
      val effect = _effect.receiveAsFlow()

      fun onEvent(event: LoginEvent) {
            when (event) {
                  is LoginEvent.OnEmailChanged -> {
                        _loginState.update {
                              it.copy(email = event.email, isError = false)
                        }
                  }
                  is LoginEvent.OnPasswordChanged -> {
                        _loginState.update {
                              it.copy(password = event.password, isError = false)
                        }
                  }
                  is LoginEvent.ValidateCredentials -> processUser()
            }
      }

      private fun processUser() {
            viewModelScope.launch {
                  val validateCredentials = validateCredentials(_loginState.value.email, _loginState.value.password)
                  if (validateCredentials) {
                        _effect.send(LoginEffect.NavigateToHome)
                        return@launch
                  }
                  _loginState.update {
                        it.copy(isError = true)
                  }
            }
      }
}