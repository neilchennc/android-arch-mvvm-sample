package tw.neil.sample.arch.mvvm.scheduleonoff.util

import tw.neil.sample.arch.mvvm.scheduleonoff.data.Task
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test
import java.util.*

class TaskUtilTest {

    @Test
    fun getStartTime_isCorrect() {
        val startTime = Task(1, Calendar.SUNDAY, 12, 0, true, false)
        val endTime = Task(4, Calendar.SUNDAY, 12, 0, false, false)
        val list = mutableListOf(startTime, endTime)
        val result = TaskUtil.getStartTime(list, Calendar.SUNDAY)
        Assert.assertEquals(startTime, result)
    }

    @Test
    fun getEndTime_isCorrect() {
        val startTime = Task(1, Calendar.SUNDAY, 12, 0, true, false)
        val endTime = Task(4, Calendar.SUNDAY, 12, 0, false, false)
        val list = mutableListOf(startTime, endTime)
        val result = TaskUtil.getEndTime(list, Calendar.SUNDAY)
        Assert.assertEquals(endTime, result)
    }

    @Ignore("not implemented yet")
    @Test
    fun checkWeekAvailable() {
    }

    @Ignore("not implemented yet")
    @Test
    fun checkAvailable() {
    }

    @Test
    fun checkWeekEnable_emptyList() {
        val list = emptyList<Task>()
        val result = TaskUtil.checkWeekEnable(list, Calendar.SUNDAY)
        Assert.assertFalse(result)
    }

    @Test
    fun checkWeekEnable_noDaysEnabled() {
        val list = mutableListOf(
            Task(1, Calendar.SUNDAY, 12, 0, false, false),
            Task(2, Calendar.MONDAY, 12, 0, false, true),
            Task(3, Calendar.MONDAY, 12, 0, false, false),
            Task(4, Calendar.SUNDAY, 12, 0, false, false)
        )
        val result = TaskUtil.checkWeekEnable(list, Calendar.SUNDAY)
        Assert.assertFalse(result)
    }

    @Test
    fun checkWeekEnable_oneDayEnable() {
        val list = mutableListOf(
            Task(1, Calendar.SUNDAY, 12, 0, false, true),
            Task(2, Calendar.MONDAY, 12, 0, false, true),
            Task(3, Calendar.MONDAY, 12, 0, false, false),
            Task(4, Calendar.SUNDAY, 12, 0, false, false)
        )
        val result = TaskUtil.checkWeekEnable(list, Calendar.SUNDAY)
        Assert.assertFalse(result)
    }

    @Test
    fun checkWeekEnable_twoDaysEnabled() {
        val list = mutableListOf(
            Task(1, Calendar.SUNDAY, 12, 0, false, true),
            Task(2, Calendar.MONDAY, 12, 0, false, true),
            Task(3, Calendar.MONDAY, 12, 0, false, false),
            Task(4, Calendar.SUNDAY, 12, 0, false, true)
        )
        val result = TaskUtil.checkWeekEnable(list, Calendar.SUNDAY)
        Assert.assertTrue(result)
    }

}