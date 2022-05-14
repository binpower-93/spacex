package me.benbarber.spacex.data.api.model

import kotlinx.serialization.SerialName

data class Flickr (

    @SerialName("small"    ) var small    : ArrayList<String> = arrayListOf(),
    @SerialName("original" ) var original : ArrayList<String> = arrayListOf()

)