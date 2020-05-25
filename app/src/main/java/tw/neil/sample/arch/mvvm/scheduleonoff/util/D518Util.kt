package tw.neil.sample.arch.mvvm.scheduleonoff.util

import android.content.Context
import android.content.Intent

/**
 * Utility class for D518 board-spec functions.
 */
object D518Util {

    /**
     * The action for D518 to set RTC alarm time.
     */
    const val ACTION_PCP_WAKEUP_ALARM = "com.pcpartner.action.ACTION_PCP_WAKEUP_ALARM"

    /**
     * The extra data for [ACTION_PCP_WAKEUP_ALARM] to set the time in milliseconds.
     */
    const val EXTRA_PCP_TIME = "com.pcpartner.extra.EXTRA_PCP_TIME"

    /**
     * The custom action to shutdown D518.
     */
    const val ACTION_PCP_SHUTDOWN = "com.pcpartner.action.ACTION_PCP_SHUTDOWN"

    /**
     * The custom action to do like power button.
     */
    const val ACTION_PCP_POWER_BUTTON = "com.pcpartner.action.ACTION_PCP_POWER_BUTTON"

    /**
     * The extra data for [ACTION_PCP_POWER_BUTTON].
     */
    const val EXTRA_PCP_POWER_STATE = "com.pcpartner.extra.EXTRA_PCP_POWER_STATE"

    /**
     * The custom action to turn screen on.
     */
    const val ACTION_PCP_SCREEN_ON = "com.pcpartner.action.ACTION_PCP_SCREEN_ON"

    /**
     * The custom action to turn screen off.
     */
    const val ACTION_PCP_SCREEN_OFF = "com.pcpartner.action.ACTION_PCP_SCREEN_OFF"

    /**
     * Set RTC alarm time in milliseconds.
     */
    fun setAlarmTime(context: Context, millis: Long) =
        context.sendBroadcast(Intent(ACTION_PCP_WAKEUP_ALARM).putExtra(EXTRA_PCP_TIME, millis))

    /**
     * Shutdown the device.
     */
    fun shutdown(context: Context) =
        context.sendBroadcast(Intent(ACTION_PCP_SHUTDOWN))

    /**
     * Perform action likes power on/off.
     */
    fun powerOnOff(context: Context, onOff: Boolean) =
        context.sendBroadcast(
            Intent(ACTION_PCP_POWER_BUTTON).putExtra(EXTRA_PCP_POWER_STATE, onOff)
        )

    /**
     * Perform screen on/off action.
     */
    fun screenOnOff(context: Context, onOff: Boolean) {
        if (onOff) {
            context.sendBroadcast(Intent(ACTION_PCP_SCREEN_ON))
        } else {
            context.sendBroadcast(Intent(ACTION_PCP_SCREEN_OFF))
        }
    }
}