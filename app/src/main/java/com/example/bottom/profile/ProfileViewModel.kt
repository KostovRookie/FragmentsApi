package com.example.bottom.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bottom.models.ErrorResponse
import com.example.bottom.models.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    repository: ProfileRepository
) : ViewModel() {

    private val repos = repository
    val profileDataResponse: MutableLiveData<Pair<UserModel?, ErrorResponse?>> = MutableLiveData()

    init {
        getProfile()
    }

    private fun getProfile() {

        CoroutineScope(Dispatchers.IO).launch {
            val response = repos.getProfile()
            withContext(Dispatchers.Main) {
                profileDataResponse.value = response
            }
        }
    }
}