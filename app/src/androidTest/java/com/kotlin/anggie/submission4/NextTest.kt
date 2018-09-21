package com.kotlin.anggie.submission4

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import com.kotlin.anggie.submission4.activity.MainActivity
import com.kotlin.anggie.submission4.activity.MatchDetailActivity
import com.kotlin.anggie.submission4.adapter.MatchAdapter.TeamViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Random

@RunWith(AndroidJUnit4::class)
@LargeTest
class NextTest {

    @Rule
    @JvmField
    val mainActivityRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun testClickNextMenuItem() {
        onView(withId(R.id.action_next)).perform(click())
        onView(withId(R.id.next_layout)).check(matches(isDisplayed()))

        val rand = Random()
        val itemPos = rand.nextInt(14)
        onView(withId(R.id.rv_next))
                .perform(RecyclerViewActions.actionOnItemAtPosition<TeamViewHolder>(itemPos, click()))
        intended(IntentMatchers.hasComponent(MatchDetailActivity::class.java.name))
    }

}