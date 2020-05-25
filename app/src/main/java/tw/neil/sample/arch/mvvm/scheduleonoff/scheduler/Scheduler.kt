package tw.neil.sample.arch.mvvm.scheduleonoff.scheduler

import tw.neil.sample.arch.mvvm.scheduleonoff.data.Task

interface Scheduler {
    fun schedule(tasks: List<Task>)
    fun cancel(tasks: List<Task>?)
}