package com.androidrey.publicapi.view.detailsscreen

import android.app.Application
import androidx.lifecycle.*
import com.androidrey.publicapi.model.Profile
import com.androidrey.publicapi.model.User
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application, user: User) : AndroidViewModel(application) {

    val repo = DetailsRepository()

    private var _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile> get() = _profile

    init {
        loadProfile(user)
    }

    private fun loadProfile(user: User) {
        viewModelScope.launch {
            try {
                val tempProfile = repo.getProfileFromServer(user.userName)
                _profile.value = tempProfile

            } catch (networkError: Exception) {
                networkError.printStackTrace()
            }
        }
    }

}

class DetailsViewModelFactory(private val mApplication: Application, private val user: User) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(mApplication, user) as T
    }
}