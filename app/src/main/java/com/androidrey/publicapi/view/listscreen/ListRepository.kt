package com.androidrey.publicapi.view.listscreen

import com.androidrey.publicapi.datasource.TheDatabase
import com.androidrey.publicapi.datasource.UserApi
import com.androidrey.publicapi.model.User

class ListRepository(private val database: TheDatabase) {
    suspend fun getUsersFromServer() = UserApi.userInterface.getusers(0)
    fun getUsersFromDB() = database.userDao.getUsers()
    suspend fun insertPicsToDB(users: List<User>) = database.userDao.insertAll(users)
}