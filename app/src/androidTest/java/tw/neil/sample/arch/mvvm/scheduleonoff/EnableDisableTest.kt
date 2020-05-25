package tw.neil.sample.arch.mvvm.scheduleonoff

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * https://developer.android.com/training/testing/espresso
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class EnableDisableTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun enableAllThenDisableAll() {
//        // Assume switch is not checked, clicking to enable
//        onView(withId(R.id.switchEnableAll)).perform(click())
//        // Check enabled
//        onView(withId(R.id.switchEnableAll)).check(matches(isChecked()))
//        onView(withId(R.id.switchSunday)).check(matches(isChecked()))
//        onView(withId(R.id.switchMonday)).check(matches(isChecked()))
//        onView(withId(R.id.switchTuesday)).check(matches(isChecked()))
//        onView(withId(R.id.switchWednesday)).check(matches(isChecked()))
//        onView(withId(R.id.switchThursday)).check(matches(isChecked()))
//        onView(withId(R.id.switchFriday)).check(matches(isChecked()))
//        onView(withId(R.id.switchSaturday)).check(matches(isChecked()))
//
//        // click again to disable
//        onView(withId(R.id.switchEnableAll)).perform(click())
//        // check disabled
//        onView(withId(R.id.switchEnableAll)).check(matches(isNotChecked()))
//        onView(withId(R.id.switchSunday)).check(matches(isNotChecked()))
//        onView(withId(R.id.switchMonday)).check(matches(isNotChecked()))
//        onView(withId(R.id.switchTuesday)).check(matches(isNotChecked()))
//        onView(withId(R.id.switchWednesday)).check(matches(isNotChecked()))
//        onView(withId(R.id.switchThursday)).check(matches(isNotChecked()))
//        onView(withId(R.id.switchFriday)).check(matches(isNotChecked()))
//        onView(withId(R.id.switchSaturday)).check(matches(isNotChecked()))
    }
}
