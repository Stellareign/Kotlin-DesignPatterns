package usersWithObserver_6_6

import users.Operations
import kotlin.system.exitProcess

class AdministratorObserver {
    private val userRepo = UserRepositoryObserver.getInstanceUserRepository("qwerty")
    private val operations = Operations.entries

    /**
     * Добавление newUser в виртуальный список
     */


    /**
     * Метод, позволяющий создавать, удалять и сохранять в репозиторий пользователей
     */
    fun work() {

        while (true) {
            println("Введите номер команды: ")
            punctuations()
            val commandIndex = readln().toInt()
            val command = operations[commandIndex]
            when (command) {
                Operations.EXIT -> {
                    userRepo.rewriteUserFile()
                    break
                }

                Operations.ADD_USER -> userRepo.addUserToList()
                Operations.DELETE_USER -> userRepo.deleteUserFromList()
                Operations.SHOW_USER_LIST -> userRepo.showAlUsers()
            }
        }
    }

    private fun punctuations() {
        for ((index, operation) in operations.withIndex()) {
            print("$index -${operation.title}")
            if (index == operations.lastIndex) {
                print(":\n ")
            } else {
                print(", ")
            }
        }
    }
}