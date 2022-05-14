package me.benbarber.spacex.data.api.model

import kotlinx.serialization.SerialName

data class Patch (

    @SerialName("small" ) var small : String? = null,
    @SerialName("large" ) var large : String? = null

)