package com.example.rachioapp.ui.overview.domain

import com.example.rachioapp.base.model.PersonId
import com.example.rachioapp.base.model.Person
import retrofit2.Response

interface OverviewRepository {
    suspend fun getPersonId(): PersonId?
    suspend fun getAccountInformation(personId: PersonId) : Person
    suspend fun off(deviceId: String): Response<Any>
    suspend fun on(deviceId: String): Response<Any>
}