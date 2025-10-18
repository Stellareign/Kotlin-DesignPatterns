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


    companion object {

        private var instance: UserRepository? = null

        fun getInstanceUserRepository(password: String): UserRepository {
            val correctPassword = File("userPassword.txt").readText().trim()
            if (correctPassword != password) throw IllegalArgumentException("Неправильный пароль!")
            if (instance == null) {
                instance = UserRepository()
            }
            return this.instance!!
        }
    }
}