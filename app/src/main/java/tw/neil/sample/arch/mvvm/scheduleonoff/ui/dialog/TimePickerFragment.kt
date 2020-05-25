package tw.neil.sample.arch.mvvm.scheduleonoff.ui.dialog

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tw.neil.sample.arch.mvvm.scheduleonoff.ext.getRepository

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private val args by navArgs<TimePickerFragmentArgs>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the task time as the default values for the picker
        val hour = args.task?.hourOfDay ?: 0
        val minute = args.task?.minute ?: 0

        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(activity, this, hour, minute, true)
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        args.task?.let { task ->
            // update task only when different
            if (task.hourOfDay != hourOfDay || task.minute != minute) {
                val repository = getRepository()

                GlobalScope.launch {
                    val updatedTask = task.copy(hourOfDay = hourOfDay, minute = minute)
                    repository.update(updatedTask)
                }
            }
        }
    }
}