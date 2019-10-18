package space.chuumong.metaweather.ui.main

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.koin.android.viewmodel.ext.android.getViewModel
import space.chuumong.data.Result
import space.chuumong.domain.entities.Weather
import space.chuumong.domain.entities.WeatherInfo
import space.chuumong.metaweather.R
import space.chuumong.metaweather.databinding.ActivityMainBinding
import space.chuumong.metaweather.ui.BaseActivity
import space.chuumong.metaweather.ui.adapter.WeatherAdapter
import space.chuumong.metaweather.ui.utlis.showErrorDialog
import space.chuumong.metaweather.viewmodel.WeatherViewModel

class MainActivity : BaseActivity<ActivityMainBinding>(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        private const val SEARCH_QUERY = "se"
    }

    private val weatherViewModel: WeatherViewModel by lazy { getViewModel() as WeatherViewModel }

    private val weatherAdapter by lazy { WeatherAdapter() }

    override fun getLayoutId() = R.layout.activity_main

    private var isSwipeRefresh = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rvWeather.layoutManager = LinearLayoutManager(this)
        binding.rvWeather.adapter = weatherAdapter

        binding.layoutSwipe.setColorSchemeColors(Color.BLACK)
        binding.layoutSwipe.setOnRefreshListener(this)

        getWeathers()
    }

    private fun getWeathers() {
        if (!isSwipeRefresh) {
            binding.progress.isVisible = true
        }

        weatherAdapter.clear()
        weatherViewModel.getWeathers(SEARCH_QUERY, object : Result<List<WeatherInfo>> {
            override fun onSuccess(result: List<WeatherInfo>) {
                binding.progress.isVisible = false
                binding.layoutSwipe.isRefreshing = false
                isSwipeRefresh = false

                weatherAdapter.addAll(result)
            }

            override fun onFail(t: Throwable) {
                binding.progress.isVisible = false
                binding.layoutSwipe.isRefreshing = false
                isSwipeRefresh = false

                showErrorDialog(t.message)
            }
        })
    }

    override fun onRefresh() {
        isSwipeRefresh = true

        getWeathers()
    }
}
