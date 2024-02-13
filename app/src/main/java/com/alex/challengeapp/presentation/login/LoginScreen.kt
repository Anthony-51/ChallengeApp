package com.alex.challengeapp.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alex.challengeapp.presentation.login.components.EmailTextField
import com.alex.challengeapp.presentation.login.components.PasswordTextField
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun LoginScreen(
      loginVM: LoginViewModel = hiltViewModel(),
      onNavigateToHome: () -> Unit
) {
      val state = loginVM.loginState.collectAsState()
      LaunchedEffect(key1 = true){
            loginVM.effect.onEach { effect ->
                  when (effect){
                        is LoginEffect.NavigateToHome -> {
                              onNavigateToHome()
                        }
                  }
            }.collect()
      }
      Column(
            modifier = Modifier
                  .fillMaxSize()
                  .background(Color.White)
                  .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
      ) {
            Text(
                  text = "Movie App", style = TextStyle(
                        fontSize = 30.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                  )
            )
            Spacer(modifier = Modifier.height(32.dp))
            EmailTextField(
                  modifier = Modifier.fillMaxWidth(),
                  value = state.value.email,
                  onValueChange = { loginVM.onEvent(LoginEvent.OnEmailChanged(it)) },
                  isError = state.value.isError
            )
            PasswordTextField(
                  modifier = Modifier.fillMaxWidth(),
                  value = state.value.password,
                  onValueChange = { loginVM.onEvent(LoginEvent.OnPasswordChanged(it)) },
                  isError = state.value.isError
            )
            if (state.value.isError) {
                  Text(
                        text = "Email o contraseña incorrectos",
                        style = TextStyle(color = Color.Red, fontSize = 12.sp)
                  )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                  modifier = Modifier.fillMaxWidth(),
                  onClick = { loginVM.onEvent(LoginEvent.ValidateCredentials) }) {
                  Text(text = "Ingresar")
            }
      }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
      LoginScreen(onNavigateToHome = {})
}