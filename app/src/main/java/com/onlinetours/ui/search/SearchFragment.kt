package com.onlinetours.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onlinetours.MyApp
import com.onlinetours.R
import com.onlinetours.base.extensions.makeVisibleOrGone
import com.onlinetours.base.extensions.onClick
import com.onlinetours.databinding.SearchFragmentBinding
import com.onlinetours.domain.entities.City
import com.onlinetours.domain.entities.Regions
import com.onlinetours.ui.search.dailog.SheetListCityFromDialog
import com.onlinetours.ui.search.dailog.SheetListCityToDialog
import com.onlinetours.ui.utills.AlertUtils
import com.onlinetours.ui.utills.SearchState
import javax.inject.Inject


class SearchFragment : Fragment(), CallBackData {

    @Inject
    lateinit var viewModel: SearchViewModel

    private lateinit var dialogFrom: SheetListCityFromDialog

    lateinit var dialogTo: SheetListCityToDialog

    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeDagger()
        viewModel.state.observe(viewLifecycleOwner) { state -> render(state = state) }

        binding.progressBar.onClick {  }
        binding.fromTitle.onClick {
            viewModel.listCity.let {
                if (it != null) {
                    if (!dialogFrom.isVisible) {
                        dialogFrom.show(
                            requireActivity().supportFragmentManager,
                            dialogFrom.tag
                        )
                    }
                } else showMessage(getString(R.string.empty_data))

            }
        }

        binding.toTitle.onClick {
            viewModel.listRegions.let {
                if (it != null) {
                    if (!dialogTo.isVisible) {
                        dialogTo.show(
                            requireActivity().supportFragmentManager,
                            dialogTo.tag
                        )
                    }
                } else showMessage(getString(R.string.empty_data))

            }
        }

        binding.buttonSearch.onClick {
            viewModel.searchTours()
        }

        /**
         * По хорошему выносить такие вещи надо
         * Загружать заранее чтобы не было небыло прогресса
         * В сплэш скрин c анимацией например загрузить
         * Ну или обсудить как должно быть
         * Пока оставил здесь - так как тестовое.
         * В требованиях небыло прописанно как должно быть реализованно
         */
        viewModel.getData()

    }

    private fun render(state: SearchState) {
        when (state) {
            is SearchState.Loading -> {
                showProgress(true)
            }

            is SearchState.Suspense -> {
                showProgress(false)
            }

            is SearchState.Success -> {
                showProgress(false)
                initData()
            }

            is SearchState.Error -> {
                showProgress(false)
                showMessage(state.message)
            }

            is SearchState.ErrorInt -> {
                showProgress(false)
                AlertUtils.showAlertNoCustom(
                    requireActivity(), getString(state.message)
                ) { _, _ ->
                }
            }
        }
    }

    private fun showProgress(check: Boolean) {
        binding.progressBar.makeVisibleOrGone(check)
    }

    private fun onItemClick(it: City) {
//            viewModel.openRingtone(ringtone)
    }

    private fun initializeDagger() {
        requireActivity().let {
            (it.applicationContext as MyApp)
                .mainComponent
                .inject(this@SearchFragment)
        }
    }

    private fun setFromCity(it: City) {
        viewModel.setCityFromData(it)
        binding.fromTitle.setTextColor(requireActivity().getColor(R.color.black))
        binding.fromTitle.text = it.name
    }

    private fun setFromCountry(it: Regions) {
        viewModel.setCountryFromData(it)
        binding.toTitle.setTextColor(requireActivity().getColor(R.color.black))
        binding.toTitle.text = it.name
    }

    private fun showMessage(text: String) {
        AlertUtils.showAlertNoCustom(
            requireActivity(), text
        ) { _, _ ->
        }
    }

    private fun initData() {
        viewModel.listCity?.let{
            dialogFrom = SheetListCityFromDialog.newInstance(it)
        }
        viewModel.listRegions?.let{
            dialogTo = SheetListCityToDialog.newInstance(it)
        }
    }

     override fun setData(any: Any) {
        when (any) {
             is City -> {
                setFromCity(any)
            }
            is Regions -> {
                setFromCountry(any)
            }
        }
    }
}