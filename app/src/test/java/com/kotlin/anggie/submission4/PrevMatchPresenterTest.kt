package com.kotlin.anggie.submission4

import com.kotlin.anggie.submission4.api.ApiService
import com.kotlin.anggie.submission4.helper.HomeScreenState
import com.kotlin.anggie.submission4.model.Event
import com.kotlin.anggie.submission4.model.EventResponse
import com.kotlin.anggie.submission4.presenter.PrevMatchPresenter
import com.kotlin.anggie.submission4.view.PrevMatchView
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.inOrder
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever

@RunWith(MockitoJUnitRunner::class)
class PrevMatchPresenterTest : BasePresenterTest() {

    @Mock
    lateinit var prevMatchView : PrevMatchView

    @Mock
    lateinit var apiService: ApiService

    private lateinit var prevMatchPresenter: PrevMatchPresenter

    @Before
    fun setup() {
        prevMatchPresenter = PrevMatchPresenter(prevMatchView, apiService)
    }

    @Test
    fun testGetPrevMatchSuccess() {
        val listEvent = mutableListOf<Event>().apply { add(Event()) }

        val eventResponse = EventResponse(listEvent)
        whenever(apiService.getLastEvent()).thenReturn(Observable.just(eventResponse))

        val inorder = inOrder(prevMatchView)
        prevMatchPresenter.getPrevMatch()
        inorder.verify(prevMatchView).setScreenState(HomeScreenState.Loading)
        inorder.verify(prevMatchView).setScreenState(HomeScreenState.Data(listEvent))
    }

    @Test
    fun testGetPrevMatchFailed() {
        val message = "Error get match"
        val error = Throwable(message)
        whenever(apiService.getLastEvent()).thenReturn(Observable.error(error))

        val inorder = inOrder(prevMatchView)
        prevMatchPresenter.getPrevMatch()
        inorder.verify(prevMatchView).setScreenState(HomeScreenState.Loading)
        inorder.verify(prevMatchView).setScreenState(HomeScreenState.Error(any()))
    }
}