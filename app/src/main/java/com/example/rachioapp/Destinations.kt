package com.example.rachioapp

interface SwitchDestination {
    val route: String
}

object Overview : SwitchDestination{
    override val route: String = "overview"
}