package com.example.rachioapp.base.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Device(
    val id: String,
    val name: String,
    val on: Boolean,
)

@JsonClass(generateAdapter = true)
data class DeviceId(
    val id: String
)