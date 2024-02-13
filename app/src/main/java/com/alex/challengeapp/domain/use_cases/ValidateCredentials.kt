package com.alex.challengeapp.domain.use_cases

import javax.inject.Inject

class ValidateCredentials @Inject constructor(

){
      operator fun invoke(email: String, password: String): Boolean {
            if (email.isEmpty() || password.isEmpty()) return false
            return email.lowercase().trim() == "admin" && password.lowercase().trim() == "password*123"
      }
}