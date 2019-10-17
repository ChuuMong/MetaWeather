package space.chuumong.metaweather.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import space.chuumong.domain.entities.Weather
import space.chuumong.metaweather.R
import space.chuumong.metaweather.ui.utlis.loadUrl


/**
 * Created by Home on 2019-10-17.
 */
class WeatherView
@JvmOverloads
constructor(context: Context, attr: AttributeSet) : RelativeLayout(context, attr) {

    companion object {
        private const val WEATHER_IMAGE_URL =
            "https://www.metaweather.com/static/img/weather/png/64/"

        private const val PNG_EXPRESSION = ".png"
    }

    private val ivWeather: ImageView
    private val tvWeather: TextView
    private val tvTemp: TextView
    private val tvHumidity: TextView

    var weather: Weather? = null
        set(value) {
            field = value

            if (value == null) {
                return
            }

            ivWeather.loadUrl(WEATHER_IMAGE_URL + value.weatherType + PNG_EXPRESSION)
            tvWeather.text = value.weatherTypeName
            tvTemp.text = String.format(context.getString(R.string.format_temp), value.temp.toInt())
            tvHumidity.text =
                String.format(context.getString(R.string.format_humidity), value.humidity)
        }

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_weather, this, false)

        ivWeather = view.findViewById(R.id.iv_weather)
        tvWeather = view.findViewById(R.id.tv_weather)
        tvTemp = view.findViewById(R.id.tv_temp)
        tvHumidity = view.findViewById(R.id.tv_humidity)

        addView(view)
    }
}