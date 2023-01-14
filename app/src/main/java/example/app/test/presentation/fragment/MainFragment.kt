package example.app.test.presentation.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import example.app.test.BR
import example.app.test.R
import example.app.test.databinding.FoodItemLeftBinding
import example.app.test.databinding.MainFragmentBinding
import example.app.test.domain.model.local.SortingTypes
import example.app.test.domain.model.remote.response.Restaurant
import example.app.test.presentation.base.BaseFragment
import example.app.test.presentation.viewModel.MainViewModel
import example.app.test.utils.ViewProgressHandler
import example.app.test.utils.customView.WaveHelper
import example.app.test.utils.customView.WaveView
import example.app.test.utils.helper.AppExecutors
import example.app.test.utils.helper.DiffUtilBindingAdapter
import example.app.test.utils.mergeSort
import example.app.test.utils.setTextSpinnerAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment<MainFragmentBinding>(R.layout.main_fragment) {
    val viewModel: MainViewModel by viewModels()
    var rvAdapter: DiffUtilBindingAdapter<Restaurant, FoodItemLeftBinding>? = null
    private val spinner = arrayListOf(
        SortingTypes.BestMatch.sortingType,
        SortingTypes.AverageProductPrice.sortingType,
        SortingTypes.DeliveryCosts.sortingType,
        SortingTypes.Distance.sortingType,
        SortingTypes.MinCost.sortingType,
        SortingTypes.Newest.sortingType,
        SortingTypes.Popularity.sortingType,
        SortingTypes.RatingAverage.sortingType
    )
    private var mWaveView: WaveHelper? = null
    var progress: ViewProgressHandler? = null

    @Inject
    lateinit var appExecutors: AppExecutors
    private val list = ArrayList<Restaurant>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        initAdapter()
        initView()
        viewModel.getServerList(progress)
    }

    private fun initView() {
        progress = ViewProgressHandler({
            binding.progress.visibility = View.VISIBLE
        }, {
            binding.progress.visibility = View.GONE
        })
        binding.rvFood.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rvAdapter
        }
    }

    private fun initAdapter() {
        rvAdapter = DiffUtilBindingAdapter(
            appExecutors = appExecutors,
            context = requireContext(),
            layout = intArrayOf(R.layout.food_item_left),
            onBindViewHolder = { item, position, binder ->
                binder.setVariable(BR.vm, item)
                binder.lytRoot.setOnClickListener {
                    findNavController().navigate(
                        MainFragmentDirections.navigateToDetails(
                            item
                        )
                    )
                }
                binder.icFavorite.setOnClickListener {
                    setFavorite(binder.waveView, item)
                }
                if (item.isFavorite) {
                    initWaveView(binder.waveView)
                    waveViewVisible(binder.waveView)
                }
            },
            diffCallback =
            object : DiffUtil.ItemCallback<Restaurant>() {
                override fun areItemsTheSame(
                    oldItem: Restaurant,
                    newItem: Restaurant
                ): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(
                    oldItem: Restaurant,
                    newItem: Restaurant
                ): Boolean {
                    return oldItem.name == newItem.name
                }
            }
        )
    }

    private fun waveViewVisible(waveView: WaveView) {
        waveView.visibility = View.VISIBLE
        mWaveView?.start()
    }

    private fun waveViewGone(waveView: WaveView) {
        waveView.visibility = View.GONE
        mWaveView?.cancel()
    }

    private fun initWaveView(waveView: WaveView) {
        mWaveView = WaveHelper(waveView)
        waveView.setWaveColor(
            Color.parseColor("#28f16d7a"),
            Color.parseColor("#3cf16d7a")
        )
    }

    private fun setFavorite(waveView: WaveView, item: Restaurant) {
        initWaveView(waveView)
        if (!item.isFavorite) {
            item.isFavorite = true
            waveViewVisible(waveView)
            item.name?.let { viewModel.saveRestaurant(it) }
        } else {
            item.isFavorite = false
            waveViewGone(waveView)
            item.name?.let { viewModel.deleteRestaurant(it) }
        }
        lifecycleScope.launch {
            delay(1500)
            sortList()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mWaveView?.cancel()
    }

    private fun observeLiveData() {
        viewModel.restaurantLiveData.observe(viewLifecycleOwner) {
            list.clear()
            list.addAll(it)
            binding.spinner.visibility = View.VISIBLE
            setTextSpinnerAdapter(
                requireContext(),
                binding.spinner,
                spinner
            ) {
                viewModel.sortedItem = it
                progress?.showProgress()
                sortList()
            }
        }
    }

    private fun sortList() {
        when (viewModel.sortedItem) {
            SortingTypes.BestMatch.id -> {
                list.sortBy { it.sortingValues?.bestMatch }
            }
            SortingTypes.AverageProductPrice.id -> {
                list.sortBy { it.sortingValues?.averageProductPrice }
            }
            SortingTypes.DeliveryCosts.id -> {
                list.sortBy { it.sortingValues?.deliveryCosts }
            }
            SortingTypes.Distance.id -> {
                list.sortBy { it.sortingValues?.distance }
            }
            SortingTypes.MinCost.id -> {
                list.sortBy { it.sortingValues?.minCost }
            }
            SortingTypes.Newest.id -> {
                list.sortBy { it.sortingValues?.newest }
            }
            SortingTypes.Popularity.id -> {
                list.sortBy { it.sortingValues?.popularity }
            }
            SortingTypes.RatingAverage.id -> {
                list.sortBy { it.sortingValues?.ratingAverage }
            }
        }
        submitAdapter()
    }

    private fun submitAdapter() {
        rvAdapter?.submitList(mergeSort(list))
        binding.rvFood.scrollToPosition(0)
        progress?.dismissProgress()
    }
}
