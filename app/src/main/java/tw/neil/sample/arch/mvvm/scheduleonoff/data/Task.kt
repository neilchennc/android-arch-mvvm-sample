package tw.neil.sample.arch.mvvm.scheduleonoff.data

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Keep
@Entity
data class Task(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "day_of_week") val dayOfWeek: Int,
    @ColumnInfo(name = "hour_of_day") val hourOfDay: Int,
    @ColumnInfo(name = "minute") val minute: Int,
    @ColumnInfo(name = "screen_on") val screenOn: Boolean,
    @ColumnInfo(name = "enabled") val enabled: Boolean = false
) : Parcelable {

    /**
     * Format time to string likes "7:00"
     */
    fun toTimeFormat() = "%d:%02d".format(hourOfDay, minute)

    fun totalMinutes() = (hourOfDay * 60 + minute)

    fun before(other: Task) = totalMinutes() < other.totalMinutes()

    fun beforeOrSame(other: Task) = totalMinutes() <= other.totalMinutes()

    fun after(other: Task) = totalMinutes() > other.totalMinutes()

    fun afterOrSame(other: Task) = totalMinutes() >= other.totalMinutes()

    fun same(other: Task) = totalMinutes() == other.totalMinutes()

    fun notSame(other: Task) = totalMinutes() != other.totalMinutes()

    /**
     * Get [Calendar] by current values.
     */
    fun getCalendar(): Calendar = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_WEEK, dayOfWeek)
        set(Calendar.HOUR_OF_DAY, hourOfDay)
        set(Calendar.MINUTE, minute)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

}