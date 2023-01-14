package example.app.test.presentation.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import example.app.test.R
import example.app.test.databinding.RestuarantDetailsFragmentBinding
import example.app.test.domain.model.remote.response.Restaurant
import example.app.test.presentation.base.BaseFragment
import example.app.test.utils.customView.WaveHelper
import example.app.test.utils.customView.WaveView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RestaurantDetailsFragment :
    BaseFragment<RestuarantDetailsFragmentBinding>(R.layout.restuarant_details_fragment) {
    val args: RestaurantDetailsFragmentArgs by navArgs()
    var restaurant: Restaurant? = null
    private var mWaveView: WaveHelper? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurant = args.restaurantData
        binding.vm = restaurant
        setFavorite()
    }
    private fun setFavorite() {
        initWaveView(binding.waveView)
        if (restaurant?.isFavorite == true) {
            waveViewVisible()
        } else {
            waveViewGone()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        mWaveView?.cancel()
    }
    private fun waveViewVisible() {
        binding.waveView.visibility = View.VISIBLE
        mWaveView?.start()
    }

    private fun waveViewGone() {
        binding.waveView.visibility = View.GONE
        mWaveView?.cancel()
    }

    private fun initWaveView(waveView: WaveView) {
        mWaveView = WaveHelper(waveView)
        binding.waveView.setWaveColor(
            Color.parseColor("#28f16d7a"),
            Color.parseColor("#3cf16d7a")
        )
    }
}
