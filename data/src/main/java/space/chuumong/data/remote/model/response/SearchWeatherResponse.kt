package space.chuumong.data.remote.model.response

import com.google.gson.annotations.SerializedName
import space.chuumong.domain.entities.SearchWeather

data class SearchWeatherResponse(
    @SerializedName("woeid")
    val id: Int
) {
    fun toEntity() = SearchWeather(id)
}