package com.kotlin.anggie.submission4.model

import com.google.gson.annotations.SerializedName

data class EventResponse(
        @SerializedName("events") val events: List<Event?>?
)