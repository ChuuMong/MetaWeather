package space.chuumong.data.mappers

import io.reactivex.functions.Function
import space.chuumong.data.remote.model.response.SearchWeatherResponse
import space.chuumong.data.remote.model.response.WeatherInfoResponse
import space.chuumong.domain.entities.SearchWeather
import space.chuumong.domain.entities.WeatherInfo

class WeatherMapper {

    fun toSearchWeatherEntities() =
        Function<List<SearchWeatherResponse>, List<SearchWeather>> { response ->
            response.map { it.toEntity() }
        }

    fun toWeatherEntities() = Function<WeatherInfoResponse, WeatherInfo> { response ->
        response.toEntity()
    }
}