package com.example.rachioapp.base.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonId(
    val id: String
)