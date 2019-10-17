package space.chuumong.data.remote.model.response

import com.google.gson.annotations.SerializedName
import space.chuumong.data.utils.DateUtil
import space.chuumong.domain.entities.Weather
import space.chuumong.domain.entities.WeatherInfo


/**
 * Created by Home on 2019-10-17.
 */
data class WeatherInfoResponse(
    @SerializedName("title")
    val cityName: String,
    @SerializedName("consolidated_weather")
    val weathers: List<WeatherResponse>?
) {
    fun toEntity(): WeatherInfo {
        return WeatherInfo(cityName, toWeatherEntities())
    }

    private fun toWeatherEntities(): List<Weather> {
        return weathers?.filter {
            it.date == DateUtil.getToday() || it.date == DateUtil.getTomorrow()
        }?.map {
            it.toEntity()
        }?.sortedBy {
            it.date
        } ?: mutableListOf()
    }
}

data class WeatherResponse(
    @SerializedName("weather_state_abbr")
    val weatherType: String,
    @SerializedName("weather_state_name")
    val weatherTypeName: String,
    @SerializedName("applicable_date")
    val date: String,
    @SerializedName("the_temp")
    val temp: Double,
    @SerializedName("humidity")
    val humidity: Int
) {
    fun toEntity() = Weather(weatherType, weatherTypeName, date, temp, humidity)
}