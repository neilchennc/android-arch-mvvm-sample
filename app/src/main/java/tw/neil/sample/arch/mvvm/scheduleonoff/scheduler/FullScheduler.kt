package tw.neil.sample.arch.mvvm.scheduleonoff.scheduler

import tw.neil.sample.arch.mvvm.scheduleonoff.data.Task
import tw.neil.sample.arch.mvvm.scheduleonoff.util.AlarmManagerHelper
import tw.neil.sample.arch.mvvm.scheduleonoff.util.TaskUtil
import java.util.*

/**
 * Schedule all [Task]s.
 */
class FullScheduler constructor(
    private val alarmManagerHelper: AlarmManagerHelper
) : Scheduler {

    companion object {
        //
    }

    @Synchronized
    override fun schedule(tasks: List<Task>) {
        // Assume the list is already sorted as we expected
        // i.e. startTime = odd, endTime = even, two times/tasks are in the same day
        for (i in tasks.indices step 2) {
            val startTime = tasks[i]
            val endTime = tasks[i + 1]
            processStartEndTime(startTime, endTime)
        }
    }

    @Synchronized
    override fun cancel(tasks: List<Task>?) {
        // Cancel all
        tasks?.forEach { alarmManagerHelper.cancel(it.id.toInt()) }
    }

    private fun processStartEndTime(startTime: Task, endTime: Task) {
        val now = Calendar.getInstance()

        if (TaskUtil.checkAvailable(startTime, endTime)) {
            if (startTime.enabled) {
                val calendar = startTime.getCalendar()
                // Add one week if the time is before now
                if (now.after(calendar)) {
                    calendar.add(Calendar.WEEK_OF_YEAR, 1)
                }
                alarmManagerHelper.setExact(startTime.id.toInt(), calendar.timeInMillis, startTime)
            } else {
                alarmManagerHelper.cancel(startTime.id.toInt())
            }

            if (endTime.enabled) {
                val calendar = endTime.getCalendar()
                // Add one week if the time is before now
                if (now.after(calendar)) {
                    calendar.add(Calendar.WEEK_OF_YEAR, 1)
                }
                alarmManagerHelper.setExact(endTime.id.toInt(), calendar.timeInMillis, endTime)
            } else {
                alarmManagerHelper.cancel(endTime.id.toInt())
            }
        } else {
            // unavailable
            alarmManagerHelper.cancel(startTime.id.toInt())
            alarmManagerHelper.cancel(endTime.id.toInt())
        }
    }

}