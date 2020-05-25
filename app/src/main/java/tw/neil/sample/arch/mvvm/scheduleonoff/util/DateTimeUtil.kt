package tw.neil.sample.arch.mvvm.scheduleonoff.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * Helper class for handling date and time.
 */
@SuppressLint("ConstantLocale")
object DateTimeUtil {
    private val dateFormatter = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
    private val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())

    /**
     * Format date pattern: yyyy-MM-dd
     */
    fun toDateFormat(dateTime: Date): String {
        return dateFormatter.format(dateTime)
    }

    /**
     * Format time pattern: HH:mm
     */
    fun toTimeFormat(dateTime: Date): String {
        return timeFormatter.format(dateTime)
    }

    /**
     * Format to pattern: yyyy/MM/dd HH:mm:ss
     */
    fun toDateTimeFormat(calendar: Calendar): String {
        return "%04d/%02d/%02d %02d:%02d:%02d".format(
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH] + 1,
            calendar[Calendar.DAY_OF_MONTH],
            calendar[Calendar.HOUR_OF_DAY],
            calendar[Calendar.MINUTE],
            calendar[Calendar.SECOND]
        )
    }
}
