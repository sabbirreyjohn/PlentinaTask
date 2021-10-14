package com.androidrey.publicapi.view.listscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androidrey.publicapi.datasource.getDatabase
import com.androidrey.publicapi.model.User
import kotlinx.coroutines.launch
import java.io.IOException

enum class ApiStatus { LOADING, DONE, ERROR }
class ListViewModel(application: Application) : AndroidViewModel(application) {

    val repo = ListRepository(getDatabase(application))


    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> get() = _status


    val users = repo.getUsersFromDB()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                var tempUsers = repo.getUsersFromServer()
                repo.insertPicsToDB(tempUsers)
                _status.value = ApiStatus.DONE
            } catch (networkError: IOException) {
                networkError.printStackTrace()
                _status.value = ApiStatus.ERROR
            }
        }
    }

}