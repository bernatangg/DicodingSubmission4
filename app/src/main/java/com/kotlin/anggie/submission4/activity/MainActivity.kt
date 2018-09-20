package com.kotlin.anggie.submission4.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.kotlin.anggie.submission4.R
import com.kotlin.anggie.submission4.fragment.FavoriteFragment
import com.kotlin.anggie.submission4.fragment.NextMatchFragment
import com.kotlin.anggie.submission4.fragment.PrevMatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_prev -> {
                    val fragment = PrevMatchFragment()
                    changeHomeFragment(fragment)
                    true
                }

                R.id.action_next -> {
                    val fragment = NextMatchFragment()
                    changeHomeFragment(fragment)
                    true
                }

                R.id.action_favorite -> {
                    val fragment = FavoriteFragment()
                    changeHomeFragment(fragment)
                    true
                }

                else -> false
            }
        }

        nav_bottom.selectedItemId = R.id.action_prev
    }

    fun changeHomeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_content, fragment)
                .commit()
    }
}
