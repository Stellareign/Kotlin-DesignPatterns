package users.usersWithoutObserver

import users.Operations
import users.usersWithoutObserver.UserRepository
import kotlin.system.exitProcess

class Administrator {
    private val userRepo = UserRepository.Companion.getInstanceUserRepository("qwerty")
    private val userList = userRepo.users.toMutableList()
    private val operations = Operations.entries

    /**
     * Добавление newUser в виртуальный список
     */
    private fun addUserToList() {
        print("Введите имя: ")
        val firstName = readln()
        print("Введите фамилию: ")
        val lastName = readln()
        print("Введите возраст (целое число): ")
        val age = readln().toInt()
        userList.add(userRepo.generateUser(firstName, lastName, age))
        userList.forEach(::println)
    }

    /**
     * Удаление пользователя из списка по указанном ID
     */
    private fun deleteUserFromList() {
        print("Введите ID пользователя, которого нужно удалить: ")
        val id = readln().toInt()
        userList.removeIf { it.id == id }
        userList.forEach(::println)
    }

    /**
     * Метод, позволяющий создавать, удалять и сохранять в репозиторий пользователей
     */
    fun work() {
        println("Введите номер команды: ")
        punctuations()
        while (true) {
            val commandIndex = readln().toInt()
            val command = operations[commandIndex]
            when (command) {
                Operations.EXIT -> {
                    userRepo.rewriteUserFile(userList)
                    exitProcess(0)
                }

                Operations.ADD_USER -> addUserToList()
                Operations.DELETE_USER -> deleteUserFromList()
                Operations.SHOW_USER_LIST -> TODO()
            }
        }
    }

    private fun punctuations() {
        for ((index, operation) in operations.withIndex()) {
            print("$index -${operation.title}")
            if (index == operations.lastIndex) {
                print(": ")
            } else {
                print(", ")
            }
        }
    }
}