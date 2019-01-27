package com.memebattle.tinkoffconvertertest.feature.converter.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CashEntity(var param : String, var current : Double) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}