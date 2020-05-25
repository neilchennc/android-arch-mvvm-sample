package tw.neil.sample.arch.mvvm.scheduleonoff.scheduler

import android.app.AlarmManager
import android.content.Context
import tw.neil.sample.arch.mvvm.scheduleonoff.data.Task
import tw.neil.sample.arch.mvvm.scheduleonoff.util.AlarmManagerHelper
import org.junit.Ignore
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import java.util.*

class NearestSchedulerTest {

    @Ignore("not implemented yet")
    @Test
    fun schedule_isNearestTask() {
        val context = mock(Context::class.java)
        val alarmManager = mock(AlarmManager::class.java)
        val alarmManagerHelper = mock(AlarmManagerHelper::class.java)

        `when`(context.getSystemService(Context.ALARM_SERVICE)).thenReturn(alarmManager)
        //`when`(alarm)

        val list = mutableListOf(
            Task(1, Calendar.SUNDAY, 12, 0, false, true),
            Task(4, Calendar.SUNDAY, 12, 0, false, true),
            Task(2, Calendar.MONDAY, 12, 0, false, true),
            Task(3, Calendar.MONDAY, 12, 0, false, false)
        )

        val scheduler = NearestScheduler(alarmManagerHelper)
        scheduler.schedule(list)
    }

    @Ignore("not implemented yet")
    @Test
    fun cancel_emptyTasks() {
        val context = mock(Context::class.java)
        val alarmManager = mock(AlarmManager::class.java)
        val alarmManagerHelper = mock(AlarmManagerHelper::class.java) // FIXME interface?

        `when`(context.getSystemService(Context.ALARM_SERVICE)).thenReturn(alarmManager)
        `when`(alarmManagerHelper.cancel(ArgumentMatchers.anyInt())).thenReturn(Unit)
        //verify(alarmManagerHelper).cancel(ArgumentMatchers.anyInt())

        val list = emptyList<Task>()
        val scheduler = NearestScheduler(alarmManagerHelper)
        scheduler.cancel(list)

        verify(alarmManagerHelper).cancel(ArgumentMatchers.anyInt())
    }
}