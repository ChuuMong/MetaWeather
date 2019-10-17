package space.chuumong.data.repositories

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import space.chuumong.data.mappers.WeatherMapper
import space.chuumong.data.remote.datasource.WeatherRemoteDataSource
import space.chuumong.domain.entities.SearchWeather
import space.chuumong.domain.entities.Weather
import space.chuumong.domain.entities.WeatherInfo
import space.chuumong.domain.repositories.WeatherRepository

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val mapper: WeatherMapper
) : WeatherRepository {

    override fun getWeathers(query: String): Single<List<WeatherInfo>> {
        return searchWeathers(query)
            .flattenAsObservable { it }
            .flatMap {
                getWeathers(it.id).toObservable()
            }
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun searchWeathers(query: String): Single<List<SearchWeather>> {
        return remoteDataSource.searchWeathers(query)
            .map(mapper.toSearchWeatherEntities())
    }

    private fun getWeathers(id: Int): Single<WeatherInfo> {
        return remoteDataSource.getWeathers(id).map(mapper.toWeatherEntities())
    }
}