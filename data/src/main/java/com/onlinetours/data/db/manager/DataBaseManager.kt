package com.onlinetours.data.db.manager

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.onlinetours.data.db.convertor.ConverterDb
import com.onlinetours.data.db.dao.CityDao
import com.onlinetours.data.db.dao.CountryDao
import com.onlinetours.data.entities.local.city.CityDb
import com.onlinetours.data.entities.local.country.RegionsDb


@Database(
    entities = [CityDb::class,RegionsDb::class],
    version = 11)
@TypeConverters(ConverterDb::class)
abstract class DatabaseManager : RoomDatabase() {

    abstract fun cityDao(): CityDao

    abstract fun countryDao(): CountryDao

}

