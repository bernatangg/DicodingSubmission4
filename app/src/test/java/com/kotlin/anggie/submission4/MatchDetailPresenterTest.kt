package com.kotlin.anggie.submission4

import com.kotlin.anggie.submission4.api.ApiService
import com.kotlin.anggie.submission4.helper.DBHelper
import com.kotlin.anggie.submission4.model.Team
import com.kotlin.anggie.submission4.model.TeamResponse
import com.kotlin.anggie.submission4.presenter.GetTeamListener
import com.kotlin.anggie.submission4.presenter.MatchDetailPresenter
import com.kotlin.anggie.submission4.view.MatchDetailView
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.Exception

class MatchDetailPresenterTest : BasePresenterTest() {

    @Mock
    lateinit var listener: GetTeamListener

    @Mock
    lateinit var apiService : ApiService

    @Mock
    lateinit var dbHelper: DBHelper

    @Mock
    lateinit var matchDetailView: MatchDetailView

    @Mock
    lateinit var matchDetailPresenter: MatchDetailPresenter

    private val fakeTeam = Team(strDivision = null, strTeamShort = null)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        matchDetailPresenter = MatchDetailPresenter(dbHelper, matchDetailView, apiService)
    }

    @Test
    fun getTeamSuccess() {
        val teamId = "1234"
        val listResultTeam = mutableListOf<Team>().apply {
            add(fakeTeam)
        }
        val teamResponse = TeamResponse(listResultTeam)
        whenever(apiService.getTeamDetail(teamId)).thenReturn(
                Observable.just(teamResponse))
        val inorder = inOrder(listener)
        matchDetailPresenter.getTeam(teamId, listener)
        inorder.verify(listener).onLoading()
        inorder.verify(listener).onSuccess(any())
    }

    @Test
    fun getTeamFailed() {
        val teamId = "1234"
        whenever(apiService.getTeamDetail(teamId)).thenReturn(
                Observable.error(Exception("anError")))
        val inorder = inOrder(listener)
        matchDetailPresenter.getTeam(teamId, listener)
        inorder.verify(listener).onLoading()
        inorder.verify(listener).onFailed(anyString())
    }



}