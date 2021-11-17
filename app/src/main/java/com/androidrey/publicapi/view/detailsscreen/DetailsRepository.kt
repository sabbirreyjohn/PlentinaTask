package com.androidrey.publicapi.view.detailsscreen

import com.androidrey.publicapi.datasource.UserApi

class DetailsRepository {
    suspend fun getProfileFromServer(username: String) = UserApi.userInterface.getProfile(username)
}