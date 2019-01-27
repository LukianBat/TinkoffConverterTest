package com.memebattle.tinkoffconvertertest.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.memebattle.tinkoffconvertertest.feature.converter.data.CashDao
import com.memebattle.tinkoffconvertertest.feature.converter.domain.model.CashEntity

@Database(entities = [CashEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cashDao(): CashDao
}