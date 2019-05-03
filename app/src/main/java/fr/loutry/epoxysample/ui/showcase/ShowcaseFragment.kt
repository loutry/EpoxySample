package fr.loutry.epoxysample.ui.showcase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import fr.loutry.epoxysample.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShowcaseFragment : Fragment() {

    private val viewModel: ShowcaseViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.showcase_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiState.observe(this, Observer { pageUiModel ->
            // TODO
        })
    }

    companion object {

        @JvmStatic
        fun newInstance(): ShowcaseFragment {
            return ShowcaseFragment()
        }
    }
}