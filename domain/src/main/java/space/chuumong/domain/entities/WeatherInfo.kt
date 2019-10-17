package space.chuumong.domain.entities

data class WeatherInfo(
    val name: String,
    val weathers: List<Weather>
)

data class Weather(
    val weatherType: String,
    val weatherTypeName: String,
    val date: String,
    val temp: Double,
    val humidity: Int
)