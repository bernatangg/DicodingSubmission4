package com.kotlin.anggie.submission4

import org.mockito.Mockito

object Utils {
    @Suppress("UNCHECKED_CAST")
    fun <T> any(): T {
        Mockito.any<T>()
        return null as T
    }
}