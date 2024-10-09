package com.example.rachioapp.ui.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rachioapp.base.model.Person
import com.example.rachioapp.base.model.Device
import com.example.rachioapp.base.model.PersonId
import com.example.rachioapp.ui.overview.domain.OverviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val overviewRepository: OverviewRepository
) : ViewModel() {
    val state: StateFlow<OverviewState> get() = _state
    private val _state = MutableStateFlow<OverviewState>(OverviewState.START)

    private val personId: LiveData<PersonId> get() = _personId
    private val _personId = MutableLiveData<PersonId>()

    init {
        _state.value = (OverviewState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            getStatus()
        }
    }

    private suspend fun getStatus() {
        try {
            val personId = overviewRepository.getPersonId()
            if (personId != null) {
                _personId.postValue(personId)
                val accountInformation = overviewRepository.getAccountInformation(personId)
                _state.value = (OverviewState.SUCCESS(accountInformation))
            } else {
                _state.value = (OverviewState.BLANK("No Account Available"))
            }

        } catch (e: Exception) {
            Timber.e(e)
            _state.value = (OverviewState.FAILURE("Error, Please try Again"))
        }
    }

    fun switchDevice(device: Device) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (device.on) {
                    overviewRepository.off(device.id)
                } else {
                    overviewRepository.on(device.id)
                }
            } catch (e: Exception) {
                when (e) {
                    is ConnectException -> {
                        _state.value = OverviewState.FAILURE("Connection Error please try again")
                    }
                    is UnknownHostException -> {
                        _state.value = OverviewState.FAILURE("Network Error, please try again")
                    }
                }
            }

        }
        refresh()
    }

    private fun refresh() = viewModelScope.launch(Dispatchers.IO) {
        personId.value?.let {
            val accountInformation = overviewRepository.getAccountInformation(personId.value!!)
            _state.value = OverviewState.RESET(accountInformation)
        }
    }

    sealed class OverviewState {
        object START : OverviewState()
        object LOADING : OverviewState()
        data class BLANK(val message: String) : OverviewState()
        data class SUCCESS(val person: Person) : OverviewState()
        data class RESET(val person: Person) : OverviewState()
        data class FAILURE(val message: String) : OverviewState()
    }

}