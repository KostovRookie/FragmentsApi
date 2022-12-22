package com.example.bottom.models

import kotlinx.serialization.Serializable


@Serializable
data class UserModel(
    val avatar_url: String? = null,
    val html_url: String? = null,
    val id: Int? = null,
    val login: String? = null,
    val name: String? = null,
    val type: String? = null,
    val updated_at: String? = null,
    val url: String? = null
)