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
import com.kotlin.anggie.submission4.helper.Constant
import com.kotlin.anggie.submission4.helper.DBHelper
import com.kotlin.anggie.submission4.helper.HomeScreenState
import com.kotlin.anggie.submission4.model.Event
import com.kotlin.anggie.submission4.presenter.FavoriteMatchPresenter
import com.kotlin.anggie.submission4.view.FavoriteMatchView
import kotlinx.android.synthetic.main.fragment_fav.*


class FavFragment : Fragment(), FavoriteMatchView {
    lateinit var favMatchPresenter: FavoriteMatchPresenter
    private var matches = mutableListOf<Event?>()
    private lateinit var adapter: MatchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbHelper = DBHelper.getInstance(view.context)
        favMatchPresenter = FavoriteMatchPresenter(this, dbHelper)

        adapter = MatchAdapter(matches) {pos ->
            val event = matches[pos]
            event?.let {
                val intent = Intent(context, MatchDetailActivity::class.java)
                intent.putExtra(Constant.EVENT, it)
                intent.putExtra(Constant.DONE_MATCH, false)
                startActivity(intent)

            }
        }

        val layoutManager = LinearLayoutManager(context)
        rv_fav.layoutManager = layoutManager
        rv_fav.adapter = adapter
        swipe_fav.setOnRefreshListener {
            favMatchPresenter.getFavMatch()
        }
    }

    override fun onResume() {
        super.onResume()
        favMatchPresenter.getFavMatch()
    }

    override fun setScreenState(homeScreenState: HomeScreenState) {
        when (homeScreenState) {
            is HomeScreenState.Error -> {
                swipe_fav.isRefreshing = false
                Toast.makeText(context, homeScreenState.message, Toast.LENGTH_SHORT).show()
            }
            is HomeScreenState.Loading -> {
                swipe_fav.isRefreshing = true
            }
            is HomeScreenState.Data -> {
                matches.clear()
                matches.addAll(homeScreenState.eventResponse)
                adapter.notifyDataSetChanged()
                swipe_fav.isRefreshing = false

                if (matches.isEmpty()) {
                    Toast.makeText(context, getString(R.string.toast_belum_favorite), Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

}
