package tw.neil.sample.arch.mvvm.scheduleonoff

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * https://developer.android.com/training/testing/espresso
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class HelloWorldEspressoTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkNameDisplayed() {
        //onView(withText(R.string.app_name)).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.textViewTableHeader), withText("Schedule On Off")))
            .check(matches(isDisplayed()))
    }
}
