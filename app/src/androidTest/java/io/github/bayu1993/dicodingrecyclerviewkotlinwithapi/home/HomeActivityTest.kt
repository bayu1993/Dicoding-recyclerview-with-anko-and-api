package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.home

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.R.id.*
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.ui.activity.HomeActivity
import org.hamcrest.core.StringEndsWith.endsWith
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Bayu teguh pamuji on 9/19/18.
 * email : bayuteguhpamuji@gmail.com.
 */
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @Rule
    @JvmField
    var ActivityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {
        Thread.sleep(3000)
        onView(withId(listClubs))
                .check(matches(isDisplayed()))
        onView(withId(listClubs))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(listClubs))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
    }

    @Test
    fun testAppsBehaviour() {
        onView(withId(spinner))
                .check(matches(isDisplayed()))
        onView(withId(spinner)).perform(click())
        onView(withText("Spanish La Liga")).perform(click())
        Thread.sleep(5000)
        onView(withText("Barcelona"))
                .check(matches(isDisplayed()))
        onView(withText("Barcelona")).perform(click())
        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText(endsWith("add to favorite")))
                .check(matches(isDisplayed()))
        Espresso.pressBack()
        onView(withId(bottom_navigation))
                .check(matches(isDisplayed()))
        onView(withId(favorites)).perform(click())
    }
}