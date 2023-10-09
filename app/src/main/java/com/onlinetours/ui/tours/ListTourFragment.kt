package com.onlinetours.ui.tours

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.onlinetours.MyApp
import com.onlinetours.R
import com.onlinetours.base.extensions.parcelableArrayList
import com.onlinetours.databinding.ListTourFragmentBinding
import com.onlinetours.domain.entities.result.Tour
import com.onlinetours.ui.search.adapter.ToursAdapter
import javax.inject.Inject

class ListTourFragment : Fragment() {

    @Inject
    lateinit var viewModel: ListToursViewModel

    private lateinit var binding: ListTourFragmentBinding

    private val adapter by lazy { ToursAdapter { onItemClick(it) } }

    companion object {
        private const val LIST = "LIST_TOUR"
        private const val NAME_REGION = "NAME_REGION"
        fun newInstance(list: List<Tour>, name : String) = ListTourFragment().apply {
            val bundle = Bundle()
            bundle.putParcelableArrayList(LIST, ArrayList(list))
            bundle.putString(NAME_REGION,name)
            arguments = bundle
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
    }

    private fun initDagger() {
        requireActivity().let {
            (it.applicationContext as MyApp)
                .mainComponent
                .inject(this@ListTourFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString(NAME_REGION)

        binding.toolbar.let {
            it.setTitleTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
            it.title = title
            it.setNavigationIcon(R.drawable.back_gray)
            it.setNavigationOnClickListener {
                viewModel.backPress()
            }
        }


        binding.recyclerTours.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = this@ListTourFragment.adapter
        }

        val qq: ArrayList<Tour>? = arguments?.parcelableArrayList(LIST)
        qq?.let {
            adapter.setData(it.toMutableList())
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListTourFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun onItemClick(it: Tour) {
    }

}