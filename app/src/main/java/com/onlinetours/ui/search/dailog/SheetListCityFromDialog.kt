package com.onlinetours.ui.search.dailog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.onlinetours.MyApp
import com.onlinetours.base.extensions.parcelableArrayList
import com.onlinetours.databinding.BottomSheetFromBinding
import com.onlinetours.domain.entities.City
import com.onlinetours.domain.usecases.MyUseCase
import com.onlinetours.ui.MainActivity
import com.onlinetours.ui.search.CallBackData
import com.onlinetours.ui.search.adapter.CityAdapter
import com.onlinetours.ui.utills.AlertUtils
import javax.inject.Inject


class SheetListCityFromDialog : BottomSheetDialogFragment() {

    @Inject
    lateinit var useCase: MyUseCase

    private lateinit var binding: BottomSheetFromBinding

    private val adapter by lazy { CityAdapter { onItemClick(it) } }

    companion object {
        private const val LIST = "LIST"
        fun newInstance(list: List<City>) = SheetListCityFromDialog().apply {
            val args = Bundle()
            args.putParcelableArrayList(LIST, ArrayList(list))
            arguments = args

        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeDagger()
        (dialog as? BottomSheetDialog)?.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = this@SheetListCityFromDialog.adapter
        }

        val qq: ArrayList<City>? = arguments?.parcelableArrayList(LIST)
        qq?.let {
            val f = it.toMutableList()
            adapter.setData(f)

        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetFromBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun onItemClick(it: City) {
        (requireActivity() as CallBackData).setData(it)
        dialog?.dismiss()
    }

    private fun setMessage(text: String) {
        activity?.let {
            AlertUtils.showAlertNoCustom(
                it, text
            ) { _, _ ->
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val sheetContainer = requireView().parent as? ViewGroup ?: return
        sheetContainer.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
    }

    private fun initializeDagger() {
        requireActivity().let {
            (it.applicationContext as MyApp)
                .mainComponent
                .inject(this@SheetListCityFromDialog)
        }
    }
}