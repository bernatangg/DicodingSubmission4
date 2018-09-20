package com.kotlin.anggie.submission4.presenter

import com.kotlin.anggie.submission4.api.ApiService
import com.kotlin.anggie.submission4.helper.HomeScreenState
import com.kotlin.anggie.submission4.view.NextMatchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NextMatchPresenter(private val nextView: NextMatchView,
                         private val apiService: ApiService) {

    fun getNextMatch() {
        nextView.setScreenState(HomeScreenState.Loading)
        apiService.getNextEvent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ eventResponse ->
                    eventResponse.events?.let {
                        nextView.setScreenState(
                                HomeScreenState.Data(it)
                        )
                    }
                }, {
                    nextView.setScreenState(
                            HomeScreenState.Error(it.message)
                    )
                })
    }
}