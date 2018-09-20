package com.kotlin.anggie.submission4.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kotlin.anggie.submission4.R
import com.kotlin.anggie.submission4.model.Event
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import com.kotlin.anggie.submission4.adapter.MatchAdapter.TeamViewHolder

class MatchAdapter(private val events: List<Event?>,
                   private val listener: (pos: Int) -> Unit) :
        RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup,
                                    p1: Int): TeamViewHolder {
        return TeamViewHolder(
                LayoutInflater.from(p0.context).inflate(R.layout.item_match, p0, false)
        )
    }

    override fun getItemCount() = events.size

    override fun onBindViewHolder(p0: TeamViewHolder,
                                  p1: Int) {
        p0.bindItem(events[p1], p1)
    }

    inner class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val matchDate: TextView = view.findViewById(R.id.tv_date)
        private val teamName1: TextView = view.findViewById(R.id.tv_team_1)
        private val teamScore1: TextView = view.findViewById(R.id.tv_skor_team_1)
        private val teamName2: TextView = view.findViewById(R.id.tv_team_2)
        private val teamScore2: TextView = view.findViewById(R.id.tv_skor_team_2)

        fun bindItem(event: Event?,
                     pos: Int) {
            event?.let { _ ->
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                try {
                    val myDate = sdf.parse(event.dateEvent)
                    sdf.applyPattern("EEE, d MMM yyyy")
                    matchDate.text = sdf.format(myDate)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }

                teamName1.text = event.strHomeTeam
                teamScore1.text = event.intHomeScore

                teamName2.text = event.strAwayTeam
                teamScore2.text = event.intAwayScore

                itemView.setOnClickListener { listener(pos) }
            }
        }
    }
}