package me.benbarber.spacex.ui.home.models

data class LaunchData(
    val id: String,
    val nameOfMission: String,
    val launchDate: String,
    val wasSuccessful: Boolean,
    val badgeUrl: String,
    val badgeContentDescription: String?,
)
