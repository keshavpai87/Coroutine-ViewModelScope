package com.example.coroutineviewmodelscope.models

import kotlinx.coroutines.delay

class UserRepository {
    suspend fun getUsers() : List<User> {
        delay(5000)
        val users : List<User> = listOf(
            User(1, "Sam"),
            User(2, "Ram"),
            User(3, "Dam"),
            User(4, "Shyam"),
            User(5, "Haram"),
            User(6, "Atuleet"),
            User(7, "Keshav"),

        )
        return users
    }
}