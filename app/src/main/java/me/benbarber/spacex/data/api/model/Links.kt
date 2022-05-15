package me.benbarber.spacex.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Links (

    @SerialName("patch"      ) var patch     : Patch?  = Patch(),
    @SerialName("reddit"     ) var reddit    : Reddit? = Reddit(),
    @SerialName("flickr"     ) var flickr    : Flickr? = Flickr(),
    @SerialName("presskit"   ) var presskit  : String? = null,
    @SerialName("webcast"    ) var webcast   : String? = null,
    @SerialName("youtube_id" ) var youtubeId : String? = null,
    @SerialName("article"    ) var article   : String? = null,
    @SerialName("wikipedia"  ) var wikipedia : String? = null

)