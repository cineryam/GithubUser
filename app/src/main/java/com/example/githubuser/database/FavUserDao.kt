package com.example.githubuser.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addToFav(favoriteUser: FavoriteUser)

    @Query("SELECT * FROM fav_user")
    fun getFavUser(): LiveData<List<FavoriteUser>>

    @Query("SELECT count(*) FROM fav_user WHERE fav_user.id = :id")
    suspend fun checkUser(id: Int): Int

    @Query("DELETE FROM fav_user WHERE fav_user.id = :id")
    suspend fun deleteFavUser(id: Int): Int
}