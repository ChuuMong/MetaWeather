package space.chuumong.data.remote.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import space.chuumong.data.remote.model.response.SearchWeatherResponse
import space.chuumong.data.remote.model.response.WeatherInfoResponse

interface ApiService {

    @GET("location/search")
    fun searchWeather(@Query("query") query: String): Single<List<SearchWeatherResponse>>

    @GET("location/{id}")
    fun getWeathers(@Path("id") id: Int): Single<WeatherInfoResponse>
}