package tw.neil.sample.arch.mvvm.scheduleonoff.scheduler

import tw.neil.sample.arch.mvvm.scheduleonoff.data.Task
import tw.neil.sample.arch.mvvm.scheduleonoff.util.AlarmManagerHelper
import tw.neil.sample.arch.mvvm.scheduleonoff.util.TaskUtil
import java.util.*

/**
 * Schedule nearest [Task] only.
 */
class NearestScheduler constructor(
    private val alarmManagerHelper: AlarmManagerHelper
) : Scheduler {

    companion object {
        const val ALARM_ID = 1
    }

    @Synchronized
    override fun schedule(tasks: List<Task>) {
        val now = Calendar.getInstance()
        val availableList = mutableListOf<Task>()

        // Assume the list is already sorted as we expected
        // i.e. startTime = odd, endTime = even, two times/tasks should be the same day
        for (i in tasks.indices step 2) {
            val startTime = tasks[i]
            val endTime = tasks[i + 1]

            if (TaskUtil.checkAvailable(startTime, endTime)) {
                availableList.add(startTime)
                availableList.add(endTime)
            }
        }

        // Find the nearest task/time to schedule
        availableList
            // filter enable only
            .filter { it.enabled }
            // calculate nearest task/time
            .minBy {
                val calendar = it.getCalendar()
                if (now.after(calendar)) {
                    calendar.add(Calendar.WEEK_OF_YEAR, 1)
                }
                calendar.timeInMillis
            }
            .let { nearestTask ->
                if (nearestTask != null) {
                    // schedule the task
                    val calendar = nearestTask.getCalendar()
                    if (now.after(calendar)) {
                        calendar.add(Calendar.WEEK_OF_YEAR, 1)
                    }
                    alarmManagerHelper.setExact(ALARM_ID, calendar.timeInMillis, nearestTask)
                } else {
                    // no available tasks, cancel previous
                    alarmManagerHelper.cancel(ALARM_ID)
                }
            }
    }

    @Synchronized
    override fun cancel(tasks: List<Task>?) {
        // cancel previous one
        alarmManagerHelper.cancel(ALARM_ID)
    }
}