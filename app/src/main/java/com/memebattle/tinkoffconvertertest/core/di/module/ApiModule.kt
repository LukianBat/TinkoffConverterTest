package com.memebattle.tinkoffconvertertest.core.di.module

import com.memebattle.tinkoffconvertertest.feature.converter.data.ConverterApi
import com.memebattle.tinkoffconvertertest.feature.converter.domain.interactor.ApiConverterService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule{

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://free.currencyconverterapi.com/api/v6/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun providesConverterService(retrofit: Retrofit): ApiConverterService {
        val converterApi = retrofit.create(ConverterApi::class.java)
        return ApiConverterService(converterApi)
    }

}