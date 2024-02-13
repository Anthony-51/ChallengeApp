package com.alex.challengeapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse422(
      @SerializedName("errors")
      val errors: List<String>?,
      @SerializedName("success")
      val success: Boolean?
)
