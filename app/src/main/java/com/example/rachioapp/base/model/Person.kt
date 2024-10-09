package com.example.rachioapp.base.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Person(
    val id: String,
    val fullName: String,
    val devices: List<Device>
)