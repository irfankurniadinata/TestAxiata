package com.example.testaxiata.data.local

import com.example.testaxiata.data.model.User
import io.paperdb.Paper

object UsersRepository {
    fun setUser(user: List<User>?) {
        if (user == null) {
            return
        }
        Paper.book("user_session").write("user_data", user)
    }

    fun getUser(): List<User>? {
        return Paper.book("user_session").read("user_data", null)
    }
}