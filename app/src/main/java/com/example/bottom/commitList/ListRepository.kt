package com.example.bottom.commitList

import com.example.bottom.models.CommitModel
import com.example.bottom.models.ErrorResponse
import com.example.bottom.networking.ApiInterface
import com.haroldadmin.cnradapter.NetworkResponse

class CommitListRepository(private val api: ApiInterface) {


    suspend fun getCommitList(
        slug: String,
        title: String,
       // status: Int
    ): Pair<List<CommitModel>?, ErrorResponse?>{

        return when (val response = api.getCommits(slug, title)) {
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