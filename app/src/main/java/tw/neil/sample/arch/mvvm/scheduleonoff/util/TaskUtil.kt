package tw.neil.sample.arch.mvvm.scheduleonoff.util

import tw.neil.sample.arch.mvvm.scheduleonoff.data.Task

object TaskUtil {

    fun getStartTime(tasks: List<Task>, dayOfWeek: Int): Task? = tasks.firstOrNull {
        it.dayOfWeek == dayOfWeek && it.screenOn
    }

    fun getEndTime(tasks: List<Task>, dayOfWeek: Int): Task? = tasks.firstOrNull {
        it.dayOfWeek == dayOfWeek && !it.screenOn
    }

    /**
     * Check specific [dayOfWeek] in the [tasks] is available.
     */
    fun checkWeekAvailable(tasks: List<Task>, dayOfWeek: Int): Boolean {
        val start = getStartTime(tasks, dayOfWeek)
        val end = getEndTime(tasks, dayOfWeek)
        return if (start != null && end != null) {
            checkAvailable(start, end)
        } else {
            false
        }
    }

    /**
     * Check [start] and [end] are available.
     */
    fun checkAvailable(start: Task, end: Task) = start.notSame(end)

    /**
     * Check days in the given [dayOfWeek] are all enabled.
     */
    fun checkWeekEnable(tasks: List<Task>, dayOfWeek: Int): Boolean =
        tasks.filter { it.dayOfWeek == dayOfWeek }.let { it.isNotEmpty() && it.all { it.enabled } }

}