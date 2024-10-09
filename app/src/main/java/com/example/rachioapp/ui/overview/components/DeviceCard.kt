package com.example.rachioapp.ui.overview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rachioapp.base.model.Device
import com.example.switchapp.R

@Composable
fun DeviceCard(
    device: Device,
    position: Int,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
                start = 6.dp,
                end = 6.dp
            )
            .height(140.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(MaterialTheme.colors.primaryVariant)
        ) {
            Text(
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary ,
                text = device.name,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 10.dp)
                    .fillMaxWidth(0.50f)
                    .wrapContentWidth(Alignment.Start)

            )
            TextButton(onClick = onClick,
                modifier = Modifier
                    .padding(end = 30.dp)
                    .align(Alignment.CenterVertically)
                    .wrapContentWidth(Alignment.End)) {
                Icon(
                    tint = if (device.on) Color.Green else Color.Red,
                    painter = painterResource(id = R.drawable.power),
                    contentDescription = null,

                    )
            }

        }
    }

}
