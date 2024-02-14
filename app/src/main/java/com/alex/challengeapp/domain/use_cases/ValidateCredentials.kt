package com.alex.challengeapp.domain.use_cases

import javax.inject.Inject

class ValidateCredentials @Inject constructor(

){
      operator fun invoke(user: String, password: String): Boolean {
            if (user.isEmpty() || password.isEmpty()) return false
            return user.lowercase().trim() == "admin" && password.lowercase().trim() == "password*123"
      }
}