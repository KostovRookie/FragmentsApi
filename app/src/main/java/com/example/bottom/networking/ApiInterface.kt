package com.example.bottom.networking

import com.example.bottom.models.CommitModel
import com.example.bottom.models.ErrorResponse
import com.example.bottom.models.UserModel
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
//    @GET("api/get_posts/")
//    @GET("repos/{owner}/{repo}/commits")
//    suspend fun getCommits(
//
//        @Path("date") OwnerName:String?,
//        @Path("title") RepoName:String?,
//        @Path("id") PerPage:Int?
//        @Path("owner") OwnerName: String,
//        @Query("per_page") PerPage: Int
//    ): NetworkResponse<List<CommitModel>, ErrorResponse>



    @GET("api/get_category_posts/?slug=статии")   //категория статии
    //  @GET("users/{username}")
    suspend fun getUser(
        @Query("title") username: String?
//        @Path("username") username: String,
    ): NetworkResponse<UserModel, ErrorResponse>


    @GET("api/get_category_posts")        //default последни 10 статии всички категории
        suspend fun getCommits (

//        @Query("count") OwnerName:String?,
//        @Query("status") RepoName:String?,
//        @Query("pages") PerPage: Int,

        @Query("slug") slug: String,
        @Query("title") title:String,
       // @Query("status") status: Int
        ): NetworkResponse<List<CommitModel>, ErrorResponse>

    @GET("api/get_posts/?count=40")
    suspend fun getPost(): CommitModel


}