package com.memebattle.tinkoffconvertertest.feature.converter.domain.interactor

import com.memebattle.tinkoffconvertertest.core.domain.BaseCallback
import com.memebattle.tinkoffconvertertest.feature.converter.data.CashDao
import com.memebattle.tinkoffconvertertest.feature.converter.domain.model.CashEntity
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.SingleOnSubscribe

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class RoomCashService(private val dao: CashDao) {
    fun isCashed(baseCallback: BaseCallback<Double>, params: String) {
        dao.getById(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<CashEntity>() {
                    override fun onError(e: Throwable) {
                        baseCallback.onError(e)
                    }

                    override fun onSuccess(t: CashEntity) {
                        baseCallback.onSuccess(t.current)
                    }
                })
    }

    fun addCurrent(current: Double, param: String) {
        Single.create(SingleOnSubscribe<Any> {
            dao.addCurrent(CashEntity(param, current))
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Any> {

                    override fun onSuccess(t: Any) {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onError(e: Throwable) {

                    }
                })
    }

    fun cleanCash() {
        Single.create(SingleOnSubscribe<Any> {
            dao.deleteAll()
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Any> {

                    override fun onSuccess(t: Any) {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onError(e: Throwable) {

                    }
                })
    }
}