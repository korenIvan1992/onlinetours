package com.onlinetours.data.db.dao

import android.database.Cursor
import androidx.room.*
import com.onlinetours.data.entities.local.country.RegionsDb


@Dao
interface CountryDao {
    @Query("SELECT * from country")
    suspend fun getListCountry(): List<RegionsDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountryList(list: List<RegionsDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cityDb: RegionsDb): Long


    @Update
    fun update(quote: RegionsDb): Int

    @Update
    suspend fun updateList(listQuote: List<RegionsDb>)

//    @Delete
//    suspend fun delete(quote: PeopleDb)

    @Delete
    fun delete(domain: RegionsDb): Int

    @Query("DELETE FROM country")
    suspend fun deleteAll()

    @Query(value = "DELETE FROM country WHERE id=:id")
    fun deleteById(id: Int): Int


    @Query(value = """SELECT * FROM country""")
    fun selectAll(): Cursor?

}