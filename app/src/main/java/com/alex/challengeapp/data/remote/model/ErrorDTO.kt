package com.alex.challengeapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class ErrorDTO(
      @SerializedName("status_code")
      val code: Int?,
      @SerializedName("status_message")
      val message: String?,
      @SerializedName("errors")
      val errors: List<String>?,
      @SerializedName("success")
      val success: Boolean?
)