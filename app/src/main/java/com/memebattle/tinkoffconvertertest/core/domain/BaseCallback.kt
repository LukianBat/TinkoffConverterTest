package com.memebattle.tinkoffconvertertest.core.domain

interface BaseCallback<T> {
    fun onSuccess(result: T)
    fun onError(error: Throwable)
}