package tw.neil.sample.arch.mvvm.scheduleonoff

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import tw.neil.sample.arch.mvvm.scheduleonoff.repository.Repository
import tw.neil.sample.arch.mvvm.scheduleonoff.scheduler.Scheduler
import tw.neil.sample.arch.mvvm.scheduleonoff.util.D518Util

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Timber.d("received action=${intent.action}")

        val applicationContext = context.applicationContext
        val application = applicationContext as MainApplication
        val repository = application.repository
        val scheduler = application.scheduler

        when (intent.action) {
            Intent.ACTION_TIMEZONE_CHANGED,
            Intent.ACTION_TIME_CHANGED,
            Intent.ACTION_BOOT_COMPLETED -> {
                // Schedule alarms
                doSchedule(scheduler, repository)
            }
            Constant.ACTION_ALARM -> {
                // Received custom alarm action
                if (intent.hasExtra(Constant.EXTRA_ID) && intent.hasExtra(Constant.EXTRA_SCREEN_STATE)) {
                    val id = intent.getLongExtra(Constant.EXTRA_ID, -1)
                    val screenState = intent.getBooleanExtra(Constant.EXTRA_SCREEN_STATE, false)
                    Timber.d("id=${id}, state=${screenState}")

                    // do screen on/off
                    D518Util.screenOnOff(applicationContext, screenState)

                    // Schedule alarms
                    doSchedule(scheduler, repository)
                }
            }
            else -> {
                // no-op
            }
        }
    }

    private fun doSchedule(scheduler: Scheduler, repository: Repository) = GlobalScope.launch {
        scheduler.schedule(repository.getAll())
    }

}