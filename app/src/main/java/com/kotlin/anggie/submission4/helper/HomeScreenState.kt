package com.kotlin.anggie.submission4.helper

import com.kotlin.anggie.submission4.model.Event

sealed class HomeScreenState {
    class Error(val message: String?) : HomeScreenState()
    object Loading : HomeScreenState()
    data class Data(val  eventResponse: List<Event?>): HomeScreenState()
}