package controllers

import models.User

class UserStore {
    val users = ArrayList<User>()
    private var lastId = 1
    private fun getId() = lastId++

    fun findAll(): List<User> {
        return users
    }

    fun create(user: User) {
        user.id = getId()
        users.add(user)
    }

    fun findOne(id: Int): User? {
        return users.find { p -> p.id == id }
    }

    fun delete(id: Int): User? {
        val index = getUserIndexById(id)
        return if (index != -1) {
            users.removeAt(index)
        } else {
            null
        }
    }

    private fun getUserIndexById(id: Int): Int {
        return users.indexOfFirst { it.id == id }
    }

    fun update(id: Int, user: User): Boolean {
        val index = getUserIndexById(id)
        return if (index != -1) {
            user.id = id
            users[index] = user
            true
        } else {
            false
        }
    }



}