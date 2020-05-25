package tw.neil.sample.arch.mvvm.scheduleonoff.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import tw.neil.sample.arch.mvvm.scheduleonoff.data.Task
import tw.neil.sample.arch.mvvm.scheduleonoff.ext.Event
import tw.neil.sample.arch.mvvm.scheduleonoff.repository.Repository
import tw.neil.sample.arch.mvvm.scheduleonoff.scheduler.Scheduler
import tw.neil.sample.arch.mvvm.scheduleonoff.util.TaskUtil
import java.util.*

class MainViewModel(
    private val scheduler: Scheduler,
    private val repository: Repository
) : ViewModel() {

    companion object {
        // no-op
    }

    private val _openTimePickerEvent = MutableLiveData<Event<Task>>()
    val openTimePickerEvent: LiveData<Event<Task>> = _openTimePickerEvent

    /**
     * All [Task]s.
     */
    private val allTasks: LiveData<List<Task>> = repository.allTasks

    // Sunday

    val sundayStartTime: LiveData<Task> = Transformations.map(allTasks) { tasks ->
        TaskUtil.getStartTime(tasks, Calendar.SUNDAY)
    }
    val sundayEndTime: LiveData<Task> = Transformations.map(allTasks) { tasks ->
        TaskUtil.getEndTime(tasks, Calendar.SUNDAY)
    }
    val sundayEnabled: LiveData<Boolean> = Transformations.map(allTasks) { tasks ->
        TaskUtil.checkWeekEnable(tasks, Calendar.SUNDAY)
    }
    val sundayAvailable: LiveData<Boolean> = Transformations.map(allTasks) { tasks ->
        TaskUtil.checkWeekAvailable(tasks, Calendar.SUNDAY)
    }

    // Monday

    val mondayStartTime: LiveData<Task> = Transformations.map(allTasks) { tasks ->
        TaskUtil.getStartTime(tasks, Calendar.MONDAY)
    }
    val mondayEndTime: LiveData<Task> = Transformations.map(allTasks) { tasks ->
        TaskUtil.getEndTime(tasks, Calendar.MONDAY)
    }
    val mondayEnabled: LiveData<Boolean> = Transformations.map(allTasks) { tasks ->
        TaskUtil.checkWeekEnable(tasks, Calendar.MONDAY)
    }
    val mondayAvailable: LiveData<Boolean> = Transformations.map(allTasks) { tasks ->
        TaskUtil.checkWeekAvailable(tasks, Calendar.MONDAY)
    }

    // Tuesday

    val tuesdayStartTime: LiveData<Task> = Transformations.map(allTasks) { tasks ->
        TaskUtil.getStartTime(tasks, Calendar.TUESDAY)
    }
    val tuesdayEndTime: LiveData<Task> = Transformations.map(allTasks) { tasks ->
        TaskUtil.getEndTime(tasks, Calendar.TUESDAY)
    }
    val tuesdayEnabled: LiveData<Boolean> = Transformations.map(allTasks) { tasks ->
        TaskUtil.checkWeekEnable(tasks, Calendar.TUESDAY)
    }
    val tuesdayAvailable: LiveData<Boolean> = Transformations.map(allTasks) { tasks ->
        TaskUtil.checkWeekAvailable(tasks, Calendar.TUESDAY)
    }

    // Wednesday

    val wednesdayStartTime: LiveData<Task> = Transformations.map(allTasks) { tasks ->
        TaskUtil.getStartTime(tasks, Calendar.WEDNESDAY)
    }
    val wednesdayEndTime: LiveData<Task> = Transformations.map(allTasks) { tasks ->
        TaskUtil.getEndTime(tasks, Calendar.WEDNESDAY)
    }
    val wednesdayEnabled: LiveData<Boolean> = Transformations.map(allTasks) { tasks ->
        TaskUtil.checkWeekEnable(tasks, Calendar.WEDNESDAY)
    }
    val wednesdayAvailable: LiveData<Boolean> = Transformations.map(allTasks) { tasks ->
        TaskUtil.checkWeekAvailable(tasks, Calendar.WEDNESDAY)
    }

    // Thursday

    val thursdayStartTime: LiveData<Task> = Transformations.map(allTasks) { tasks ->
        TaskUtil.getStartTime(tasks, Calendar.THURSDAY)
    }
    val thursdayEndTime: LiveData<Task> = Transformations.map(allTasks) { tasks ->
        TaskUtil.getEndTime(tasks, Calendar.THURSDAY)
    }
    val thursdayEnabled: LiveData<Boolean> = Transformations.map(allTasks) { tasks ->
        TaskUtil.checkWeekEnable(tasks, Calendar.THURSDAY)
    }
    val thursdayAvailable: LiveData<Boolean> = Transformations.map(allTasks) { tasks ->
        TaskUtil.checkWeekAvailable(tasks, Calendar.THURSDAY)
    }

    // Friday

    val fridayStartTime: LiveData<Task> = Transformations.map(allTasks) { tasks ->
        TaskUtil.getStartTime(tasks, Calendar.FRIDAY)
    }
    val fridayEndTime: LiveData<Task> = Transformations.map(allTasks) { tasks ->
        TaskUtil.getEndTime(tasks, Calendar.FRIDAY)
    }
    val fridayEnabled: LiveData<Boolean> = Transformations.map(allTasks) { tasks ->
        TaskUtil.checkWeekEnable(tasks, Calendar.FRIDAY)
    }
    val fridayAvailable: LiveData<Boolean> = Transformations.map(allTasks) { tasks ->
        TaskUtil.checkWeekAvailable(tasks, Calendar.FRIDAY)
    }

    // Saturday

    val saturdayStartTime: LiveData<Task> = Transformations.map(allTasks) { tasks ->
        TaskUtil.getStartTime(tasks, Calendar.SATURDAY)
    }
    val saturdayEndTime: LiveData<Task> = Transformations.map(allTasks) { tasks ->
        TaskUtil.getEndTime(tasks, Calendar.SATURDAY)
    }
    val saturdayEnabled: LiveData<Boolean> = Transformations.map(allTasks) { tasks ->
        TaskUtil.checkWeekEnable(tasks, Calendar.SATURDAY)
    }
    val saturdayAvailable: LiveData<Boolean> = Transformations.map(allTasks) { tasks ->
        TaskUtil.checkWeekAvailable(tasks, Calendar.SATURDAY)
    }

    /**
     * Determine all start time (screen on) are enabled.
     */
    val allStartTimeEnabled = Transformations.map(allTasks) { tasks ->
        tasks.isNotEmpty() && tasks.filter { it.screenOn }.all { it.enabled }
    }

    /**
     * Determine all end time (screen off) are enabled.
     */
    val allEndTimeEnabled = Transformations.map(allTasks) { tasks ->
        tasks.isNotEmpty() && tasks.filter { !it.screenOn }.all { it.enabled }
    }

    /**
     * Determine all [Task]s are enabled.
     */
    val allTaskEnabled = Transformations.map(allTasks) { tasks ->
        tasks.isNotEmpty() && tasks.all { it.enabled }
    }

    init {
        Timber.d("init")
    }

    fun onTestButton1Click() {
        Timber.d("test button 1 clicked")
        scheduler.schedule(allTasks.value!!)
    }

    fun onTestButton2Click() {
        Timber.d("test button 2 clicked")
        scheduler.cancel(allTasks.value!!)
    }

    fun onTimeClick(task: Task?) {
        if (task != null) {
            _openTimePickerEvent.value = Event(task)
        }
    }

    fun onWeekEnableChanged(isChecked: Boolean, dayOfWeek: Int) = GlobalScope.launch {
        allTasks.value?.let { tasks ->
            val start = TaskUtil.getStartTime(tasks, dayOfWeek)
            val end = TaskUtil.getEndTime(tasks, dayOfWeek)
            if (start != null && end != null) {
                // enable/disable only when different
                if (start.enabled != isChecked || end.enabled != isChecked) {
                    repository.enableWeek(dayOfWeek, isChecked)
                }
            }
        }
    }

    fun onSettingEnableChanged(isChecked: Boolean) = GlobalScope.launch {
        // enable/disable only when different
        if (isChecked != allTaskEnabled.value) {
            repository.enableAll(isChecked)
        }
    }

    fun updateTaskTime(task: Task, hourOfDay: Int, minute: Int) = GlobalScope.launch {
        // update only when different
        if (task.hourOfDay != hourOfDay || task.minute != minute) {
            val updateTask = task.copy(hourOfDay = hourOfDay, minute = minute)
            repository.update(updateTask)
        }
    }

    override fun onCleared() {
        Timber.d("onCleared")
        super.onCleared()
    }

}
