package tw.neil.sample.arch.mvvm.scheduleonoff.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.AlarmManagerCompat
import timber.log.Timber
import tw.neil.sample.arch.mvvm.scheduleonoff.AlarmReceiver
import tw.neil.sample.arch.mvvm.scheduleonoff.BuildConfig
import tw.neil.sample.arch.mvvm.scheduleonoff.Constant
import tw.neil.sample.arch.mvvm.scheduleonoff.data.Task
import java.util.*

/**
 * Helper class to set or cancel the alarm.
 */
class AlarmManagerHelper constructor(private val context: Context) {

    companion object {
        const val FLAG_ALARM = PendingIntent.FLAG_UPDATE_CURRENT
        const val FLAG_CANCEL = PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_CANCEL_CURRENT
    }

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun setExact(requestCode: Int, millis: Long, task: Task) {
        val alarmIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
            intent.action = Constant.ACTION_ALARM
            intent.putExtra(Constant.EXTRA_ID, task.id)
            intent.putExtra(Constant.EXTRA_SCREEN_STATE, task.screenOn)
            PendingIntent.getBroadcast(context, requestCode, intent, FLAG_ALARM)
        }

        AlarmManagerCompat.setExactAndAllowWhileIdle(
            alarmManager,
            AlarmManager.RTC_WAKEUP,
            millis,
            alarmIntent
        )

        // Debug log
        if (BuildConfig.DEBUG) {
            val calendar = Calendar.getInstance().apply { timeInMillis = millis }
            val datetime = DateTimeUtil.toDateTimeFormat(calendar)
            Timber.d("setExact(), requestCode=${requestCode}, id=${task.id}, datetime=${datetime}")
        }
    }

    fun cancel(requestCode: Int) {
        val alarmIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
            intent.action = Constant.ACTION_ALARM
            PendingIntent.getBroadcast(context, requestCode, intent, FLAG_CANCEL)
        }

        if (alarmIntent != null) {
            alarmManager.cancel(alarmIntent)
            alarmIntent.cancel()
        }

        // Debug log
        if (BuildConfig.DEBUG) {
            Timber.d("cancel(), requestCode=${requestCode}, alarmIntent=${alarmIntent}")
        }
    }
}