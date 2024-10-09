package com.example.rachioapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rachioapp.ui.overview.OverviewScreen
import com.example.rachioapp.ui.theme.SwitchAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwitchAppTheme()
        }
    }

    @Composable
    private fun SwitchAppTheme() {
        SwitchAppTheme {
            val navController = rememberNavController()
            Scaffold { innerPadding ->
                SwitchNavHost(navController = navController, modifier = Modifier.padding(innerPadding))
            }

        }
    }

    @Composable
    private fun SwitchNavHost(navController: NavHostController, modifier: Modifier) {
        NavHost(navController = navController, startDestination = Overview.route, modifier = modifier){
            composable(route = Overview.route){
                OverviewScreen()
            }
        }
    }
}

