package space.chuumong.domain.repositories

import io.reactivex.Single
import space.chuumong.domain.entities.SearchWeather
import space.chuumong.domain.entities.Weather
import space.chuumong.domain.entities.WeatherInfo

interface WeatherRepository {

    fun getWeathers(query: String): Single<List<WeatherInfo>>
}