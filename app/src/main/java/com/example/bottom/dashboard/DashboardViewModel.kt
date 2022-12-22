package com.example.bottom.dashboard

import androidx.lifecycle.LiveData
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
class DashboardViewModel  @Inject constructor(
repository: DashboardRepository
) : ViewModel() {

    private val repos = repository
    val profileDataResponse: MutableLiveData<Pair<UserModel?, ErrorResponse?>> = MutableLiveData()

    init {
        getProfileTwo()
    }

    private fun getProfileTwo() {

        CoroutineScope(Dispatchers.IO).launch {
            val response = repos.getProfileTwo()
            withContext(Dispatchers.Main) {
                profileDataResponse.value = response
            }
        }
    }
}