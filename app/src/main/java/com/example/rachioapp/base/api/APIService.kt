package com.example.rachioapp.base.api

import com.example.rachioapp.base.model.PersonId
import com.example.rachioapp.base.model.Person
import com.example.rachioapp.base.model.Device
import com.example.rachioapp.base.model.DeviceId
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface APIService {

    @PUT("public/device/on")
    suspend fun turnOn(@Body deviceId: DeviceId): Response<Any>

    @PUT("public/device/off")
    suspend fun turnOff(@Body deviceId: DeviceId): Response<Any>

    @GET("public/device/{id}")
    suspend fun getDevice(@Path("id") id: String): Device

    @GET("public/person/info")
    suspend fun getPersonId(): PersonId?

    @GET("public/person/{id}")
    suspend fun getPersonInformation(@Path("id") id: String): Person
}