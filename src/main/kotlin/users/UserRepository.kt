package users

import kotlinx.serialization.json.Json
import java.io.File

class UserRepository private constructor() {
//    init { // блок инит позволяется ограничить доступ к данным класса при первичном конструкторе (важно: должен написан первым). Не синглтон
//        val correctPassword = File("password.txt").readText().trim()
//        if (correctPassword != password) throw IllegalArgumentException("Неправильный пароль!")
//    }

    private val file = File("users.json")
    private val _users: MutableList<User> = loadUsersProfiles(file)
    val users
        get() = _users.toList()

    private fun loadUsersProfiles(file: File): MutableList<User> = Json.decodeFromString(file.readText().trim())

    /**
     * companion object {} - для доступа свойствам приватного класса -> можно прописать условия доступа
     */
    companion object {
               /**
         * Делегат by lazy (ленивая инициализация) также не позволяется ничего передавать в конструктор
         */
//        private val instance: UserRepository by lazy { UserRepository() }
        /**
         * при создании экземпляра внутри класса не получится использовать приватный конструктор снаружи и что-то в него передать
         */
//        private val instance: UserRepository = UserRepository()

        private var instance: UserRepository? = null // создание экземпляра через нуаллабельное значение переменной
        fun getInstanceUserRepository(password: String): UserRepository {
            val correctPassword = File("userPassword.txt").readText().trim()
            if (correctPassword != password) throw IllegalArgumentException("Неправильный пароль!")
            if (instance == null) {
                instance = UserRepository()
            }
            return instance!!
        }
    }
}