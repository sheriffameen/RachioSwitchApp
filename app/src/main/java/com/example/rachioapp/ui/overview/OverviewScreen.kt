package com.example.rachioapp.ui.overview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rachioapp.base.model.Person
import com.example.rachioapp.ui.overview.components.DeviceCard
import com.example.rachioapp.ui.overview.components.ErrorMessage
import com.example.rachioapp.ui.overview.components.ProgressBar
import timber.log.Timber

@Composable
fun OverviewScreen(
    viewModel: OverviewViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is OverviewViewModel.OverviewState.SUCCESS -> {
            val accountInformation = (state as OverviewViewModel.OverviewState.SUCCESS).person

            OverViewContent(accountInformation, viewModel)

        }
        is OverviewViewModel.OverviewState.BLANK -> {
            val message = (state as OverviewViewModel.OverviewState.BLANK).message
            ErrorMessage(errorMessage = message)
        }
        is OverviewViewModel.OverviewState.FAILURE -> {
            val message = (state as OverviewViewModel.OverviewState.FAILURE).message
            ErrorMessage(errorMessage = message)
        }
        OverviewViewModel.OverviewState.LOADING -> {
            ProgressBar()
        }
        is OverviewViewModel.OverviewState.RESET -> {
            val accountInformation = (state as OverviewViewModel.OverviewState.RESET).person

            OverViewContent(accountInformation, viewModel)
        }
        else ->{
            Timber.e("Screen in bad unknown state")
        }
    }

}

@Composable
private fun OverViewContent(
    person: Person,
    viewModel: OverviewViewModel
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp),
            color = MaterialTheme.colors.primary,
            text = "Welcome, ${person.fullName}",
            style = MaterialTheme.typography.h1
        )
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 76.dp),
            color = MaterialTheme.colors.primary,
            text = "Your Devices",
            style = MaterialTheme.typography.body2
        )
        LazyColumn(
            modifier = Modifier
                .padding(top = 120.dp)
        ) {
            itemsIndexed(items = person.devices.sortedBy { it.name }) { index, item ->
                DeviceCard(device = item, position = index) {
                    viewModel.switchDevice(item)
                }
            }
        }

    }
}


