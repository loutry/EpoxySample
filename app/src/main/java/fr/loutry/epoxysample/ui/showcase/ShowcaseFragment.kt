package fr.loutry.epoxysample.ui.showcase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.airbnb.epoxy.EpoxyRecyclerView
import fr.loutry.epoxysample.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShowcaseFragment : Fragment() {

    private val viewModel: ShowcaseViewModel by viewModel()
    private val controller = ShowcaseController()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.showcase_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: EpoxyRecyclerView = view.findViewById(R.id.showcase_recycler_view)
        recyclerView.setController(controller)

        viewModel.uiState.observe(this, Observer { pageUiModel ->
            controller.setData(pageUiModel.content)
        })
    }

    companion object {

        @JvmStatic
        fun newInstance(): ShowcaseFragment {
            return ShowcaseFragment()
        }
    }
}