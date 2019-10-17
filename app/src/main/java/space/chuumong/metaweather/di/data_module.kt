package space.chuumong.metaweather.di

import org.koin.dsl.module
import space.chuumong.data.mappers.WeatherMapper
import space.chuumong.data.remote.datasource.WeatherRemoteDataSource
import space.chuumong.data.repositories.WeatherRepositoryImpl
import space.chuumong.domain.repositories.WeatherRepository

val weatherModule = module {
    factory { WeatherMapper() }

    factory { WeatherRemoteDataSource(get()) }

    factory { WeatherRepositoryImpl(get(), get()) as WeatherRepository }
}