package tw.neil.sample.arch.mvvm.scheduleonoff.repository

import androidx.lifecycle.LiveData
import tw.neil.sample.arch.mvvm.scheduleonoff.data.Task
import tw.neil.sample.arch.mvvm.scheduleonoff.data.TaskDao

/**
 * Provides a clean API for data access to the rest of the application.
 */
class TaskRepository(private val taskDao: TaskDao) : Repository {

    override val allTasks: LiveData<List<Task>> = taskDao.getAllLiveData()

    init {
        //
    }

    override suspend fun getAll(): List<Task> = taskDao.getAll()

    override suspend fun getStartTimeLiveData(dayOfWeek: Int): LiveData<Task> {
        return taskDao.getLiveData(dayOfWeek, true)
    }

    override suspend fun getEndTimeLiveData(dayOfWeek: Int): LiveData<Task> {
        return taskDao.getLiveData(dayOfWeek, false)
    }

    override suspend fun enableWeek(dayOfWeek: Int, enabled: Boolean) {
        taskDao.enableWeek(dayOfWeek, enabled)
    }

    override suspend fun enableAll(enabled: Boolean) {
        taskDao.enableAll(enabled)
    }

    override suspend fun enableStartTime(enabled: Boolean) {
        taskDao.enableScreenOn(enabled)
    }

    override suspend fun enableEndTime(enabled: Boolean) {
        taskDao.enableScreenOff(enabled)
    }

    override suspend fun update(task: Task) {
        taskDao.update(task)
    }
}