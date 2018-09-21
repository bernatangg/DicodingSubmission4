package com.kotlin.anggie.submission4.presenter

import com.kotlin.anggie.submission4.api.ApiService
import com.kotlin.anggie.submission4.helper.HomeScreenState
import com.kotlin.anggie.submission4.view.PrevMatchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PrevMatchPresenter(private val prevMatchView: PrevMatchView,
                         private val apiService: ApiService) {

    fun getPrevMatch() {
        prevMatchView.setScreenState(HomeScreenState.Loading)
        apiService.getLastEvent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({eventResponse ->
                    eventResponse.events?.let {
                        prevMatchView.setScreenState(HomeScreenState.Data(it))
                    }
                }, {
                    prevMatchView.setScreenState(HomeScreenState.Error(it.message))
                })
    }
}