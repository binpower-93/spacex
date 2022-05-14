package me.benbarber.spacex.data.api.model

import kotlinx.serialization.SerialName

data class Reddit (

    @SerialName("campaign" ) var campaign : String? = null,
    @SerialName("launch"   ) var launch   : String? = null,
    @SerialName("media"    ) var media    : String? = null,
    @SerialName("recovery" ) var recovery : String? = null

)