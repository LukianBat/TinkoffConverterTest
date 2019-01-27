package com.memebattle.tinkoffconvertertest.core.di

import com.memebattle.tinkoffconvertertest.core.di.module.ApiModule
import com.memebattle.tinkoffconvertertest.core.di.module.RoomModule
import com.memebattle.tinkoffconvertertest.feature.converter.presentation.MainViewModel
import dagger.Component
import javax.inject.Singleton
@Singleton
@Component(modules = [ApiModule::class, RoomModule::class])
interface AppComponent {
    fun inject(mainViewModel: MainViewModel)
}