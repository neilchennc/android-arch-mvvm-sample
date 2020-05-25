package tw.neil.sample.arch.mvvm.scheduleonoff

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tw.neil.sample.arch.mvvm.scheduleonoff.repository.Repository
import tw.neil.sample.arch.mvvm.scheduleonoff.scheduler.Scheduler
import tw.neil.sample.arch.mvvm.scheduleonoff.ui.main.MainViewModel

/**
 * Factory for all ViewModels.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val scheduler: Scheduler,
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) -> MainViewModel(scheduler, repository)
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}