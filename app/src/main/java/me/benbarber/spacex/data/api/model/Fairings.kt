package me.benbarber.spacex.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Fairings (

    @SerialName("reused"           ) var reused          : Boolean?          = null,
    @SerialName("recovery_attempt" ) var recoveryAttempt : Boolean?          = null,
    @SerialName("recovered"        ) var recovered       : Boolean?          = null,
    @SerialName("ships"            ) var ships           : ArrayList<String> = arrayListOf()

)