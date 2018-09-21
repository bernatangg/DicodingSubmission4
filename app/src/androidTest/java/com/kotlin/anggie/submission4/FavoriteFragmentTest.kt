package com.kotlin.anggie.submission4

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.kotlin.anggie.submission4.activity.MainActivity
import com.kotlin.anggie.submission4.activity.MatchDetailActivity
import com.kotlin.anggie.submission4.adapter.MatchAdapter.TeamViewHolder
import com.kotlin.anggie.submission4.R.id
import com.kotlin.anggie.submission4.fragment.FavFragment
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Random

@RunWith(AndroidJUnit4::class)
class FavoriteFragmentTest {

    @Rule
    @JvmField
    val mainActivityRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun testFavoriteFragment() {
        onView(withId(id.action_favorite)).perform(ViewActions.click())
        onView(withId(R.id.fav_layout)).check(matches(ViewMatchers.isDisplayed()))

        Thread.sleep(2000)

        val favFragment = mainActivityRule.activity.supportFragmentManager.findFragmentById(
                R.id.frame_content) as FavFragment
        val rv = favFragment.view?.findViewById<RecyclerView>(R.id.rv_fav)
        rv?.let {
            val itemCount = it.adapter?.itemCount
            if (itemCount != null && itemCount > 0) {
                val rand = Random()
                val itemPos = rand.nextInt(itemCount)
                onView(withId(R.id.rv_fav))
                        .perform(
                                RecyclerViewActions.actionOnItemAtPosition<TeamViewHolder>(itemPos, ViewActions.click()))
                Intents.intended(IntentMatchers.hasComponent(MatchDetailActivity::class.java.name))
            }
        }

    }
}