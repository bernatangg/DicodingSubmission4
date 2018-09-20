package com.kotlin.anggie.submission4

import org.junit.ClassRule

open class BasePresenterTest {
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxSchedulerRule()
    }
}