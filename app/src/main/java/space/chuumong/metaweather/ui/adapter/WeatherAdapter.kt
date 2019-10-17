package space.chuumong.metaweather.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import space.chuumong.data.utils.DateUtil
import space.chuumong.domain.entities.Weather
import space.chuumong.domain.entities.WeatherInfo
import space.chuumong.metaweather.R
import space.chuumong.metaweather.ui.view.WeatherView

class WeatherAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val HEADER_SIZE = 1

        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ITEM = 1
    }

    private val weathers = mutableListOf<WeatherInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_weather_header, parent, false)
                WeatherHeaderViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_weather, parent, false)
                WeatherViewHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_HEADER
            else -> VIEW_TYPE_ITEM
        }
    }

    override fun getItemCount(): Int {
        if (weathers.isEmpty()) {
            return 0
        }

        return weathers.size + HEADER_SIZE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WeatherViewHolder -> holder.bind(getItem(position))
        }
    }

    private fun getItem(position: Int): WeatherInfo {
        return weathers[position - HEADER_SIZE]
    }

    fun addAll(items: List<WeatherInfo>) {
        weathers.clear()
        weathers.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        weathers.clear()
        notifyDataSetChanged()
    }

    inner class WeatherHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view)

    inner class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val WEATHER_DATE_TODAY = 0
        private val WEATHER_DATE_TOMORROW = 1

        private val tvCity = view.findViewById<TextView>(R.id.tv_city)
        private val weatherToday = view.findViewById<WeatherView>(R.id.weather_today)
        private val weatherTomorrow = view.findViewById<WeatherView>(R.id.weather_tomorrow)

        fun bind(item: WeatherInfo) {
            tvCity.text = item.name

            weatherToday.weather = item.weathers[WEATHER_DATE_TODAY]
            weatherTomorrow.weather = item.weathers[WEATHER_DATE_TOMORROW]
        }
    }
}