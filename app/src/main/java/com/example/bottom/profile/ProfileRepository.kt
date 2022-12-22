package com.example.bottom.profile

import com.example.bottom.models.ErrorResponse
import com.example.bottom.models.UserModel
import com.example.bottom.networking.ApiInterface
import com.example.bottom.utilities.Const
import com.haroldadmin.cnradapter.NetworkResponse

class ProfileRepository(private val api: ApiInterface) {
//    suspend fun getProfile(user: String = Const.PROFILE_USERNAME): Pair<UserModel?, ErrorResponse?>


    suspend fun getProfile(user: String = Const.PROFILE_USERNAME): Pair<UserModel?, ErrorResponse?> {

        return when (val response = api.getUser(user)) {
            is NetworkResponse.Success -> {
                Pair(response.body, null)
            }

            is NetworkResponse.ServerError -> {
                Pair(null, ErrorResponse(400, "Server shit"))
            }
            is NetworkResponse.NetworkError -> {
                Pair(null, ErrorResponse(400, "Network shit"))
            }
            is NetworkResponse.UnknownError -> {
                Pair(null, ErrorResponse(400, "Unknown shit"))
            }
        }

    }


}