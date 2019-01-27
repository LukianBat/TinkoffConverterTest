package com.memebattle.tinkoffconvertertest.feature.converter.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.memebattle.tinkoffconvertertest.feature.converter.domain.model.CashEntity
import io.reactivex.Single

@Dao
interface CashDao {

    @Insert
    fun addCurrent(current: CashEntity)

    @Query("SELECT * FROM cashentity WHERE param = :param")
    fun getById(param: String): Single<CashEntity>

    @Query("DELETE FROM cashentity")
    fun deleteAll()

}