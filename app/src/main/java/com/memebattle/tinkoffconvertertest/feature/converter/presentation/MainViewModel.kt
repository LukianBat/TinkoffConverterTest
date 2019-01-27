package com.memebattle.tinkoffconvertertest.feature.converter.presentation

import androidx.lifecycle.ViewModel
import com.memebattle.tinkoffconvertertest.App
import com.memebattle.tinkoffconvertertest.core.domain.BaseCallback
import com.memebattle.tinkoffconvertertest.feature.converter.domain.interactor.ApiConverterService
import com.memebattle.tinkoffconvertertest.feature.converter.domain.interactor.RoomCashService
import com.memebattle.tinkoffconvertertest.feature.converter.domain.model.CurrenciesResponse
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject
    lateinit var apiService: ApiConverterService
    @Inject
    lateinit var roomCashService: RoomCashService

    init {
        App.component.inject(this)
    }

    fun downloadList(baseCallback: BaseCallback<Boolean>) {
        apiService.getCurrencies(object : BaseCallback<CurrenciesResponse> {

            override fun onError(error: Throwable) {
                baseCallback.onError(error)
            }
            override fun onSuccess(result: CurrenciesResponse) {
               baseCallback.onSuccess(true)
                roomCashService.cleanCash()
            }
        })
    }
    fun toConvert(paramFirst: String, paramSecond: String, current : Double, baseCallback: BaseCallback<Double>){
        apiService.toConvert(object : BaseCallback<Double> {
            override fun onSuccess(result: Double) {
                baseCallback.onSuccess(current*result)
                roomCashService.addCurrent(result, paramFirst+"_"+paramSecond)
            }
            override fun onError(error: Throwable) {
                roomCashService.isCashed(object : BaseCallback<Double>{
                    override fun onSuccess(result: Double) {
                        baseCallback.onSuccess(current*result)
                    }
                    override fun onError(error: Throwable) {
                        baseCallback.onError(error)
                    }

                },paramFirst+"_"+paramSecond)
            }

        },paramFirst, paramSecond)
    }
    private fun isNotDigit(c: Char) = c !in '0'..'9'
    fun isCorrectCurrent(string: String):Boolean{
        for (i in string){
            if (isNotDigit(i)){
                return false
            }
        }
        if (string == "") return false
        return true
    }
}