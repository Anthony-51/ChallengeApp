package com.alex.challengeapp.presentation.login.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PasswordTextField(
      modifier: Modifier = Modifier,
      value: String,
      onValueChange: (String) -> Unit,
      isError: Boolean
) {
      OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = "Password") },
            colors = OutlinedTextFieldDefaults.colors(
                  unfocusedBorderColor = Color.Gray,
                  unfocusedLabelColor = Color.Gray,
                  errorBorderColor = Color.Red,
                  errorLabelColor = Color.Red
            ),
            keyboardOptions = KeyboardOptions(
                  keyboardType = KeyboardType.Password,
                  imeAction = ImeAction.Go
            ),
            isError = isError,
            visualTransformation = PasswordVisualTransformation()
      )
}

@Preview(showBackground = true)
@Composable
fun PasswordTextFieldPreview() {

}