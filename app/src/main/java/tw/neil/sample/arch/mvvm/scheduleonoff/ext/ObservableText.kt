package tw.neil.sample.arch.mvvm.scheduleonoff.ext

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import tw.neil.sample.arch.mvvm.scheduleonoff.BR

/**
 * Observable [String] used for two-way data binding.
 */
class ObservableText(private var _text: String) : BaseObservable() {

    @Bindable
    fun getText() = _text

    fun setText(text: String) {
        // Avoids infinite loops.
        if (text != _text) {
            _text = text

            // React the changed
            //save()

            // Notify observers of a new value.
            notifyPropertyChanged(BR.text)
        }
    }

    /**
     * Convert to [Long], return [defValue] if failed.
     */
    fun getLong(defValue: Long = 0): Long {
        return try {
            _text.toLong()
        } catch (e: NumberFormatException) {
            defValue
        }
    }
}