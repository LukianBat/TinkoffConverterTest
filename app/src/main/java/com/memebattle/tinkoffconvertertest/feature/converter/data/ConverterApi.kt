package com.memebattle.tinkoffconvertertest.feature.converter.data

import com.google.gson.JsonObject
import com.memebattle.tinkoffconvertertest.feature.converter.domain.model.CurrenciesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ConverterApi {
    @GET("https://free.currencyconverterapi.com/api/v6/currencies")
    fun getCurrencies(): Call<CurrenciesResponse>

    @GET("https://free.currencyconverterapi.com/api/v6/convert")
    fun toConvert(@Query("q") params: String, @Query("compact") compact: String): Call<JsonObject>
}