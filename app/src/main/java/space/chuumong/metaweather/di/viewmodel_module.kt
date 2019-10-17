package space.chuumong.metaweather.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import space.chuumong.metaweather.viewmodel.WeatherViewModel

val viewModelModule = module {
    viewModel { WeatherViewModel(get()) }
}
