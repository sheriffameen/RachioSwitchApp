package com.example.rachioapp.ui.overview.domain

import com.example.rachioapp.base.api.APIService
import com.example.rachioapp.base.model.PersonId
import com.example.rachioapp.base.model.Person
import com.example.rachioapp.base.model.DeviceId
import javax.inject.Inject

class OverviewRepositoryImpl @Inject constructor(
    private val apiService: APIService
) : OverviewRepository {

    override suspend fun getPersonId(): PersonId? = apiService.getPersonId()
    override suspend fun getAccountInformation(personId: PersonId): Person =
        apiService.getPersonInformation(personId.id)

    override suspend fun off(deviceId: String) = apiService.turnOff(DeviceId(deviceId))

    override suspend fun on(deviceId: String) = apiService.turnOn(DeviceId(deviceId))

}