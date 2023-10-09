package com.onlinetours.data.db.dao

import android.database.Cursor
import androidx.room.*
import com.onlinetours.data.entities.local.city.CityDb


@Dao
interface CityDao {
    @Query("SELECT * from city")
    suspend fun getListCity(): List<CityDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)  // or OnConflictStrategy.IGNORE
    suspend fun insertList(list: List<CityDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cityDb: CityDb): Long

//    @Update
//    suspend fun update(quote: PeopleDb)

    @Update
    fun update(quote: CityDb): Int

    @Update
    suspend fun updateList(listQuote: List<CityDb>)

//    @Delete
//    suspend fun delete(quote: PeopleDb)

    @Delete
    fun delete(domain: CityDb): Int

    @Query("DELETE FROM city")
    suspend fun deleteAll()

    @Query(value = "DELETE FROM city WHERE id=:id")
    fun deleteById(id: Int): Int


    @Query(value = """SELECT * FROM city""")
    fun selectAll(): Cursor?

}