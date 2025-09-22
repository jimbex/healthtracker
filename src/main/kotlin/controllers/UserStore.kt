package controllers

import models.User

class UserStore {
    val users = ArrayList<User>()

    fun findAll(): List<User> {
        return users
    }

    fun create(user: User) {
        users.add(user)
    }

    fun findOne(id: Int): User? {
        return users.find { p -> p.id == id }
    }

    fun getUserById() : User?{
        print("Enter the id of the user: ")
        return  userStore.findOne(readlnOrNull()?.toIntOrNull() ?: -1)
    }

}