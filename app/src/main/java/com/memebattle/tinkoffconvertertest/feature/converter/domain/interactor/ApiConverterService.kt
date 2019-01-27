package com.memebattle.tinkoffconvertertest.feature.converter.domain.interactor

import android.util.Log
import com.google.gson.JsonObject
import com.memebattle.tinkoffconvertertest.core.domain.BaseCallback
import com.memebattle.tinkoffconvertertest.feature.converter.data.ConverterApi
import com.memebattle.tinkoffconvertertest.feature.converter.domain.model.CurrenciesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiConverterService(val converterApi: ConverterApi) {
    fun jsonToDouble(string: String): Double{

        for (i in 0..(string.length-1)){
            if (string[i].equals(':')){
                return string.substring(i+1, string.length-1).toDouble()
            }
        }
        return 0.0
    }
    fun getCurrencies(baseCallback: BaseCallback<CurrenciesResponse>) {
        converterApi.getCurrencies().enqueue(object : Callback<CurrenciesResponse> {
            override fun onFailure(call: Call<CurrenciesResponse>, t: Throwable) {
                baseCallback.onError(t)
            }
            override fun onResponse(call: Call<CurrenciesResponse>, response: Response<CurrenciesResponse>) {
                baseCallback.onSuccess(response.body()!!)
            }
        })
    }
    fun toConvert(baseCallback: BaseCallback<Double>, paramFirst: String, paramSec: String) {
        converterApi.toConvert(paramFirst+"_"+paramSec,"ultra").enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.i("TAG", jsonToDouble(response.body().toString()).toString())
                baseCallback.onSuccess(jsonToDouble(response.body().toString()))
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                baseCallback.onError(t)
            }


        })
    }
}