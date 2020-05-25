package tw.neil.sample.arch.mvvm.scheduleonoff

import android.app.Application
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import tw.neil.sample.arch.mvvm.scheduleonoff.di.appModule
import tw.neil.sample.arch.mvvm.scheduleonoff.repository.Repository
import tw.neil.sample.arch.mvvm.scheduleonoff.scheduler.Scheduler

class MainApplication : Application() {

    val scheduler by inject<Scheduler>()
    val repository by inject<Repository>()

    override fun onCreate() {
        super.onCreate()

        // Debug log plant
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Setup Koin
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule)
        }

        // Observing data changes for scheduling
        repository.allTasks.observeForever {
            GlobalScope.launch {
                if (it.isNotEmpty()) {
                    scheduler.schedule(it)
                }
            }
        }
    }
}