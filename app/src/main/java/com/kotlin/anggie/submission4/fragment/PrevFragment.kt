package com.kotlin.anggie.submission4.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.kotlin.anggie.submission4.R
import com.kotlin.anggie.submission4.activity.MatchDetailActivity
import com.kotlin.anggie.submission4.adapter.MatchAdapter
import com.kotlin.anggie.submission4.api.ApiService
import com.kotlin.anggie.submission4.helper.Constant
import com.kotlin.anggie.submission4.helper.HomeScreenState
import com.kotlin.anggie.submission4.model.Event
import com.kotlin.anggie.submission4.presenter.PrevMatchPresenter
import com.kotlin.anggie.submission4.view.PrevMatchView
import kotlinx.android.synthetic.main.fragment_prev.rv_prev
import kotlinx.android.synthetic.main.fragment_prev.swipe_prev

class PrevFragment : Fragment(), PrevMatchView {
    lateinit var prevMatchPresenter: PrevMatchPresenter
    private var matches = mutableListOf<Event?>()
    private lateinit var adapter: MatchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prev, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prevMatchPresenter = PrevMatchPresenter(this, ApiService.instance)

        adapter = MatchAdapter(matches) { pos ->
            val event = matches[pos]
            event?.let {
                val intent = Intent(context, MatchDetailActivity::class.java)
                intent.putExtra(Constant.EVENT, it)
                startActivity(intent)
            }
        }
        val layoutManager = LinearLayoutManager(context)
        swipe_prev.isRefreshing = true
        rv_prev.layoutManager = layoutManager
        rv_prev.adapter = adapter
        swipe_prev.setOnRefreshListener {
            prevMatchPresenter.getPrevMatch()
        }
    }

    override fun onResume() {
        super.onResume()
        prevMatchPresenter.getPrevMatch()
    }

    override fun setScreenState(homeScreenState: HomeScreenState) {
        when (homeScreenState) {
            is HomeScreenState.Error -> {
                swipe_prev.isRefreshing = false
                Toast.makeText(context, homeScreenState.message, Toast.LENGTH_SHORT)
                        .show()
            }
            is HomeScreenState.Loading -> {
                swipe_prev.isRefreshing = true
            }
            is HomeScreenState.Data -> {
                matches.clear()
                matches.addAll(homeScreenState.eventResponse)
                adapter.notifyDataSetChanged()
                swipe_prev.isRefreshing = false

                if (matches.isEmpty()) {
                    Toast.makeText(context, getString(R.string.blm_ada_tanding), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
