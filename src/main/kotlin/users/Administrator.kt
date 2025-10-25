package users

import kotlin.system.exitProcess

class Administrator {
    val userRepo = UserRepository.getInstanceUserRepository("qwerty")
    val userList = userRepo.users.toMutableList()

    /**
     * Добавление newUser в виртуальный список
     */
    fun addUserToList() {

        print("Введите имя: ")
        val firstName = readln()
        print("Введите фамилию: ")
        val lastName = readln()
        print("Введите возраст (целое число): ")
        val age = readln().toInt()
        userList.add(userRepo.generateUser(firstName, lastName, age))
        userList.forEach (::println)
    }

    /**
     * Удаление пользователя из списка по указанном ID
     */
    fun deleteUserFromList() {
        print("Введите ID пользователя, которого нужно удалить: ")
        val id = readln().toInt()
        userList.removeIf { it.id == id }
        userList.forEach (::println)
    }

    /**
     * Метод, позволяющий создавать, удалять и сохранять в репозиторий пользователей
     */
    fun work() {
        while (true) {
            println("Введите номер команды: 0 - выйти и сохранить; 1 - добавить пользователя; 2 - удалить пользователя")
            val command = readln().toInt()
            when (command) {
                0 -> {
                    userRepo.rewriteUserFile(userList)
                    exitProcess(0)
                }

                1 -> addUserToList()
                2 -> deleteUserFromList()
            }
        }
    }
}