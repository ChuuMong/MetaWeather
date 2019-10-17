package space.chuumong.metaweather.viewmodel

import space.chuumong.domain.usecases.GetWeathers
import space.chuumong.data.Result
import space.chuumong.domain.entities.WeatherInfo

class WeatherViewModel(private val getWeathers: GetWeathers) : BaseViewModel() {

    fun getWeathers(query: String, result: Result<List<WeatherInfo>>) {
        add(getWeathers.get(query).subscribe({
            result.onSuccess(it)
        }, {
            result.onFail(it)
        }))
    }
}