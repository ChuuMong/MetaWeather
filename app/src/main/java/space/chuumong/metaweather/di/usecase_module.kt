package space.chuumong.metaweather.di

import org.koin.dsl.module
import space.chuumong.domain.usecases.GetWeathers

val useCaseModule = module {
    factory { GetWeathers(get()) }
}