package com.example.bottom.dashboard

import com.haroldadmin.cnradapter.NetworkResponse
import com.example.bottom.models.ErrorResponse
import com.example.bottom.models.UserModel
import com.example.bottom.networking.ApiInterface
import com.example.bottom.utilities.Const

class DashboardRepository (private val api: ApiInterface) {

    suspend fun getProfileTwo(user: String = Const.PROFILE_USERNAME): Pair<UserModel?, ErrorResponse?> {

        return when (val response = api.getUser(user)) {
            is NetworkResponse.Success -> {
                Pair(response.body, null) // ok response
            }

            is NetworkResponse.ServerError -> {
                Pair(null, ErrorResponse(400, "Server shit"))
            }
            is NetworkResponse.NetworkError -> {
                // Handle network error
                Pair(null, ErrorResponse(400, "Network shit"))
            }
            is NetworkResponse.UnknownError -> {
                // Handle other errors
                Pair(null, ErrorResponse(400, "Unknown shit"))
            }
        }

    }


}