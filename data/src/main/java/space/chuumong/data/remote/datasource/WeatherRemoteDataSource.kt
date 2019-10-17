package space.chuumong.data.remote.datasource

import space.chuumong.data.remote.api.ApiService

class WeatherRemoteDataSource(apiService: ApiService) : BaseRemoteDataSource(apiService) {

    fun searchWeathers(query: String) = apiService.searchWeather(query)
    
    fun getWeathers(id: Int) = apiService.getWeathers(id)
}