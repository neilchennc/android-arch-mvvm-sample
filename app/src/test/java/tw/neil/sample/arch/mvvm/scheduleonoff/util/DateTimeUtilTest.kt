package tw.neil.sample.arch.mvvm.scheduleonoff.util

import org.junit.Assert
import org.junit.Ignore
import org.junit.Test
import java.util.*

class DateTimeUtilTest {

    @Ignore("not implemented yet")
    @Test
    fun toDateFormat() {
    }

    @Ignore("not implemented yet")
    @Test
    fun toTimeFormat() {
    }

    @Test
    fun toDateTimeFormat_isCorrect() {
        val calendar = Calendar.getInstance().apply {
            set(2020, 0, 31, 23, 59, 59)
        }
        val expected = "2020/01/31 23:59:59"
        val result = DateTimeUtil.toDateTimeFormat(calendar)
        Assert.assertEquals(expected, result)
    }

}