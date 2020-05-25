package tw.neil.sample.arch.mvvm.scheduleonoff.repository

import androidx.lifecycle.LiveData
import tw.neil.sample.arch.mvvm.scheduleonoff.data.Task

interface Repository {

    val allTasks: LiveData<List<Task>>

    suspend fun getAll(): List<Task>
    suspend fun getStartTimeLiveData(dayOfWeek: Int): LiveData<Task>
    suspend fun getEndTimeLiveData(dayOfWeek: Int): LiveData<Task>
    suspend fun enableWeek(dayOfWeek: Int, enabled: Boolean)
    suspend fun enableAll(enabled: Boolean)
    suspend fun enableStartTime(enabled: Boolean)
    suspend fun enableEndTime(enabled: Boolean)
    suspend fun update(task: Task)
}