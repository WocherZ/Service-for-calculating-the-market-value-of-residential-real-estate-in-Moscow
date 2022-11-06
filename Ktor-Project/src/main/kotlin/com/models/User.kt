package com.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val login: String,
    val password: String
)


val userStorage = mutableListOf<User>()

fun MutableList<User>.toLoginList() : List<String> {
    val result: ArrayList<String> = ArrayList()
    for (user in this) {
        result.add(user.login)
    }
    return result
}