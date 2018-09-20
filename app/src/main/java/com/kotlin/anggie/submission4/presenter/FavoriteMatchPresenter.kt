package com.kotlin.anggie.submission4.presenter

import com.kotlin.anggie.submission4.helper.DBHelper
import com.kotlin.anggie.submission4.helper.HomeScreenState
import com.kotlin.anggie.submission4.view.FavoriteMatchView

class FavoriteMatchPresenter(private val favMatchView: FavoriteMatchView,
                             private val dbHelper: DBHelper) {
    fun getFavMatch() {
        favMatchView.setScreenState(HomeScreenState.Loading)
        val listEvent = dbHelper.getAllFavMatches()
        favMatchView.setScreenState(HomeScreenState.Data(listEvent))
    }
}
