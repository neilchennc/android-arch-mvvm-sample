package tw.neil.sample.arch.mvvm.scheduleonoff.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import tw.neil.sample.arch.mvvm.scheduleonoff.R
import tw.neil.sample.arch.mvvm.scheduleonoff.databinding.MainFragmentBinding
import tw.neil.sample.arch.mvvm.scheduleonoff.ext.EventObserver

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false).apply {
            mainViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.openTimePickerEvent.observe(viewLifecycleOwner, EventObserver { task ->
            // Prevent user clicking multiple times by checking current destination
            // https://stackoverflow.com/questions/51060762/illegalargumentexception-navigation-destination-xxx-is-unknown-to-this-navcontr
            if (findNavController().currentDestination?.id == R.id.mainFragment) {
                val action = MainFragmentDirections.actionMainFragmentToTimePickerFragment(task)
                findNavController().navigate(action)
            }
        })
    }

}
