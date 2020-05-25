package tw.neil.sample.arch.mvvm.scheduleonoff.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tw.neil.sample.arch.mvvm.scheduleonoff.data.TaskDatabase
import tw.neil.sample.arch.mvvm.scheduleonoff.repository.Repository
import tw.neil.sample.arch.mvvm.scheduleonoff.repository.TaskRepository
import tw.neil.sample.arch.mvvm.scheduleonoff.scheduler.NearestScheduler
import tw.neil.sample.arch.mvvm.scheduleonoff.scheduler.Scheduler
import tw.neil.sample.arch.mvvm.scheduleonoff.ui.main.MainViewModel
import tw.neil.sample.arch.mvvm.scheduleonoff.util.AlarmManagerHelper

val appModule = module {
    single<Scheduler> {
        NearestScheduler(AlarmManagerHelper(get()))
    }
    single<Repository> {
        val database = TaskDatabase.getDatabase(get())
        TaskRepository(database.taskDao())
    }
    viewModel {
        MainViewModel(get(), get())
    }
}