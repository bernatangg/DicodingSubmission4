package com.kotlin.anggie.submission4

import com.kotlin.anggie.submission4.api.ApiService
import com.kotlin.anggie.submission4.helper.HomeScreenState
import com.kotlin.anggie.submission4.model.Event
import com.kotlin.anggie.submission4.model.EventResponse
import com.kotlin.anggie.submission4.presenter.NextMatchPresenter
import com.kotlin.anggie.submission4.view.NextMatchView
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever
import org.mockito.Mockito.inOrder
import org.mockito.ArgumentMatchers.any

@RunWith(MockitoJUnitRunner::class)
class NextMatchPresenterTest : BasePresenterTest() {

    @Mock
    lateinit var nextMatchView : NextMatchView

    @Mock
    lateinit var apiService: ApiService

    private lateinit var nextMatchPresenter: NextMatchPresenter

    @Before
    fun setup() {
        nextMatchPresenter = NextMatchPresenter(nextMatchView, apiService)
    }

    @Test
    fun testGetNextMatchSucess() {
      val listEvent = mutableListOf<Event>().apply { add(Event()) }
      val eventResponse = EventResponse(listEvent)
        whenever(apiService.getNextEvent()).thenReturn(
                Observable.just(eventResponse)
        )
      val inorder = inOrder(nextMatchView)
      nextMatchPresenter.getNextMatch()
      inorder.verify(nextMatchView).setScreenState(HomeScreenState.Loading)
      inorder.verify(nextMatchView).setScreenState(HomeScreenState.Data(listEvent))
    }

    @Test
    fun testGetNextMatchField() {
        val message = "Error get match"
        val error = Throwable(message)
        whenever(apiService.getNextEvent()).thenReturn(
                Observable.error(error)
        )
        val inorder = inOrder(nextMatchView)
        nextMatchPresenter.getNextMatch()
        inorder.verify(nextMatchView).setScreenState(HomeScreenState.Loading)
        inorder.verify(nextMatchView).setScreenState(HomeScreenState.Error(any()))

    }
}