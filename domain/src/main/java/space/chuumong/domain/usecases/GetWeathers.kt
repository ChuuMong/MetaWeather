package space.chuumong.domain.usecases

import io.reactivex.Single
import space.chuumong.domain.entities.SearchWeather
import space.chuumong.domain.entities.Weather
import space.chuumong.domain.entities.WeatherInfo
import space.chuumong.domain.repositories.WeatherRepository

class GetWeathers(private val repository: WeatherRepository) :
    UseCase<Map<String, String>, List<WeatherInfo>>() {

    companion object {
        private const val QUERY = "params:query"
    }

    override fun run(params: Map<String, String>): Single<List<WeatherInfo>> {
        val query = params[QUERY] ?: throw NullPointerException()

        return repository.getWeathers(query)
    }

    fun get(query: String): Single<List<WeatherInfo>> {
        val params = hashMapOf<String, String>()
        params[QUERY] = query

        return execute(params)
    }
}