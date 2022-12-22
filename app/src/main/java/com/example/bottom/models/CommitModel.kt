package com.example.bottom.models

import kotlinx.serialization.Serializable



@Serializable
data class CommitModel(
    val pages: Int,
    val posts: Post,
    val count: Int,
    val status: String?,
    val html_url: String?,
    val sha: String?,
    val url: String?,
   // val posts: Posts?
)
@Serializable
data class Post(
    val comment_count: Int?,
    val title: String? = null,
    val url: String?=null,
    val thumbnail: String?=null
)



