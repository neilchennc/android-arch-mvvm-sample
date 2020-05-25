package tw.neil.sample.arch.mvvm.scheduleonoff.ext

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import tw.neil.sample.arch.mvvm.scheduleonoff.MainApplication
import tw.neil.sample.arch.mvvm.scheduleonoff.ViewModelFactory
import tw.neil.sample.arch.mvvm.scheduleonoff.repository.Repository
import tw.neil.sample.arch.mvvm.scheduleonoff.scheduler.Scheduler

/**
 * Extension functions for [Fragment].
 */

fun Fragment.getScheduler(): Scheduler {
    return (requireContext().applicationContext as MainApplication).scheduler
}

fun Fragment.getRepository(): Repository {
    return (requireContext().applicationContext as MainApplication).repository
}

fun Fragment.getViewModelFactory(): ViewModelFactory {
    return ViewModelFactory(getScheduler(), getRepository())
}

fun Fragment.hideSoftKeyboard() {
    val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    view?.windowToken?.let {
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }
}