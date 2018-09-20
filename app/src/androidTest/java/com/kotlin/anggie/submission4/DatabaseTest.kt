package com.kotlin.anggie.submission4

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.kotlin.anggie.submission4.activity.MainActivity
import com.kotlin.anggie.submission4.helper.DBHelper
import com.kotlin.anggie.submission4.helper.DatabaseListener
import com.kotlin.anggie.submission4.model.Event
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    @Rule
    @JvmField
    val mActivityRule : ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    private lateinit var dbHelper: DBHelper

    @Before
    fun setup() {
        dbHelper = DBHelper.getInstance(mActivityRule.activity)
    }

    @Test
    fun testInsertDeleteFavMatch() {
        var startEventCount = dbHelper.getAllFavMatches().size
        val eventId = System.currentTimeMillis()
        val event = Event(idEvent = "$eventId")
        dbHelper.insertFavMatch(event, object : DatabaseListener {
            override fun onSuccess() {
                startEventCount++
            }

            override fun onFailed(message: String) {
                System.out.print(message)
            }
        })

        val resultFavMatch = dbHelper.getAllFavMatches()
        assertEquals(startEventCount, resultFavMatch.size)

        dbHelper.removeFavMatch("$eventId", null)
    }

    @Test
    fun testEventMatchIsFavorite() {
        var startEventCount = dbHelper.getAllFavMatches().size
        val eventId = System.currentTimeMillis()
        val event = Event(idEvent = "$eventId")
        dbHelper.insertFavMatch(event, object : DatabaseListener {
            override fun onSuccess() {
                startEventCount++
            }

            override fun onFailed(message: String) {
                System.out.print(message)
            }
        })

        val favEvent = dbHelper.isMatchFavorite("$eventId")
        assertTrue(favEvent)
        dbHelper.removeFavMatch("$eventId", null)
    }

    @Test
    fun testEventMatchIsNotFavorite() {
        val eventId = System.currentTimeMillis()
        val favEvent = dbHelper.isMatchFavorite("$eventId")
        assertFalse(favEvent)
    }

}