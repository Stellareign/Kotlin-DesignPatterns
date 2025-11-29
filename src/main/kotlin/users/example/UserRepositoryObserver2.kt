package users.example

import Observer.Observer
import kotlinx.serialization.json.Json
import users.User
import java.io.File

class UserRepositoryObserver2 private constructor() {

    private val usersFile = File("users.json")
    private val _users: MutableList<User> = loadUsersProfiles(usersFile)
    val users
        get() = _users.toList()

    private fun loadUsersProfiles(file: File): MutableList<User> = Json.decodeFromString(file.readText().trim())

    /**
     * список подписчиков
     */
    private val observers = mutableListOf<(List<User>) -> Unit>() // лямбда

    /**
     * Уведомление подписчиков
     */
    fun notifyObserver() {
        for (observer in observers) {
            observer(users)
        }
    }

    /**
     * Добавление подписчиков
     */
    fun addObserver(observer: (List<User>) -> Unit) {
        observers.add(observer)
        observer(users)
    }

    private fun generateId(): Int {
        return users.maxOf { it.id } + 1
    }

    fun rewriteUserFile() {
        usersFile.writeText(Json.encodeToString(_users))
    }

    fun generateUser(firstName: String, lastName: String, age: Int): User {
        return User(generateId(), firstName, lastName, age)
    }

    fun showAlUsers() {
        users.forEach(::println)
    }

    fun addUserToList() {
        print("Введите имя: ")
        val firstName = readln()
        print("Введите фамилию: ")
        val lastName = readln()
        print("Введите возраст (целое число): ")
        val age = readln().toInt()
        _users.add(generateUser(firstName, lastName, age))
        users.forEach(::println)
//        display?.onChanged(users)
        notifyObserver()
    }

    /**
     * Удаление пользователя из списка по указанному ID
     */
    fun deleteUserFromList() {
        print("Введите ID пользователя, которого нужно удалить: ")
        val id = readln().toInt()
        _users.removeIf { it.id == id }
        users.forEach(::println)
//        display?.onChanged(users)
        notifyObserver()
    }


    /**
     * companion object {} - для доступа  свойствам приватного класса -> можно прописать условия доступа
     */
    companion object {
        /**
         * Делегат by lazy (ленивая инициализация) также не позволяется ничего передавать в конструктор
         * при создании экземпляра внутри класса не получится использовать приватный конструктор снаружи и что-то в него передать
         */
        private val lock = Any()
        private var instance: UserRepositoryObserver2? =
            null // создание экземпляра через нуаллабельное значение переменной

        fun getInstanceUserRepository(password: String): UserRepositoryObserver2 {
            val correctPassword = File("userPassword.txt").readText().trim()
            if (correctPassword != password) throw IllegalArgumentException("Неправильный пароль!")

            instance?.let { return it } // используем функциональный стиль ДЛЯ ДВОЙНОЙ ПРОВЕРКИ
            synchronized(lock) {
                instance?.let { return it } // с использованием функционального стиля
                return UserRepositoryObserver2().also { instance = it }
            }
        }
    }
}