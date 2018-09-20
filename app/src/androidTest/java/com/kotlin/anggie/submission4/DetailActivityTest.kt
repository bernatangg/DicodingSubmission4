package com.kotlin.anggie.submission4

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.kotlin.anggie.submission4.activity.MatchDetailActivity
import com.kotlin.anggie.submission4.helper.Constant
import com.kotlin.anggie.submission4.model.Event
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.containsString
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class DetailActivityTest {

    private val date = "2018-09-28"
    val event = Event(dateEvent = date, intAwayScore = "4", intHomeScore = "3",
            intHomeShots = "5", intAwayShots = "3",
            strHomeFormation = "5-3-2", strAwayFormation = "4-4-2",
            strHomeGoalDetails = "Siwon; Donghae; Heechul; Kyuhyun",
            strAwayGoalDetails = "Leeteuk; Sungmin; Eunhyuk",
            strHomeLineupGoalkeeper = "Yesung;Shindong",
            strAwayLineupGoalkeeper = "Yesung;Shindong")

    @Rule
    @JvmField
    val mActivityRule: ActivityTestRule<MatchDetailActivity> =
            object : ActivityTestRule<MatchDetailActivity>(MatchDetailActivity::class.java) {
                override fun getActivityIntent(): Intent {
                    val intent = Intent()
                    intent.putExtra(Constant.EVENT, event)
                    return intent
                }
            }

    @Test
    fun testShowRightContent() {
        onView(withId(R.id.tv_date_detail)).check(matches(withText(containsString("2018"))))
        onView(withId(R.id.tv_skor_team_1)).check(matches(withText("3")))
        onView(withId(R.id.tv_skor_team_2)).check(matches(withText("4")))
        onView(withId(R.id.tv_shots_team_1)).check(matches(withText("5")))
        onView(withId(R.id.tv_shots_team_2)).check(matches(withText("3")))

        onView(withId(R.id.tv_formation_team_1)).check(matches(withText("5-3-2")))
        onView(withId(R.id.tv_formation_team_2)).check(matches(withText("4-4-2")))

        onView(withId(R.id.tv_goal_team_1)).check(matches(withText("Siwon\n Donghae\n Heechul\n Kyuhyun")))
        onView(withId(R.id.tv_goal_team_2)).check(matches(withText("Leeteuk\n Sungmin\n Eunhyuk")))

        onView(withId(R.id.tv_goalkeeper_team_1)).check(matches(withText("Yesung\nShindong")))
        onView(withId(R.id.tv_goalkeeper_team_2)).check(matches(withText("Yesung\nShindong")))
    }

    @Test
    fun testClickFavoriteShowSnackbar()  {
        onView(withId(R.id.add_to_favorite)).perform(click())
        onView(allOf(withId(android.support.design.R.id.snackbar_text),
                withText(containsString("favorite"))))
                .check(matches(isDisplayed()))
    }

    @After
    fun deleteFakeMatch() {
        onView(withId(R.id.add_to_favorite)).perform(click())
    }
}