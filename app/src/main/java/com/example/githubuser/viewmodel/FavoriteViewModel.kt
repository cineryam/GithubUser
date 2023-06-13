package com.example.githubuser.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.githubuser.database.FavUserDao
import com.example.githubuser.database.FavoriteUser
import com.example.githubuser.database.UserDatabase

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private var userDao: FavUserDao?
    private var userdb: UserDatabase?

    init {
        userdb = UserDatabase.getDatabase(application)
        userDao = userdb?.favUserDao()
    }

    fun getFavUser(): LiveData<List<FavoriteUser>>? {
        return userDao?.getFavUser()
    }
}