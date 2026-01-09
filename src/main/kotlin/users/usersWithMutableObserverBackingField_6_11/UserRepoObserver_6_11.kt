package users.usersWithMutableObserverBackingField_6_11

import MutableObservable
import Observer.Observable
import kotlinx.serialization.json.Json
import users.User
import java.io.File

class UserRepoObserver_6_11 private constructor() {

    private val usersFile = File("users.json")
    private val usersList: MutableList<User> = loadUsersProfiles(usersFile) // переименован в 6.11
    private val _users: MutableObservable<List<User>> = MutableObservable(usersList.toList())

    //создаём объект МутаблОбзервер и кладём в него коллекцию пользователй
    val users: Observable<List<User>>
        get() = _users

    private val _oldestUser = MutableObservable(usersList.maxBy { it.age })
    val oldestUser: Observable<User>
        get() = _oldestUser

    private fun loadUsersProfiles(file: File): MutableList<User> = Json.decodeFromString(file.readText().trim())

    private fun generateId(): Int {
        return usersList.maxOf { it.id } + 1
    }

    fun rewriteUserFile() {
        usersFile.writeText(Json.encodeToString(usersList))
    }

    fun showAlUsers() {
        usersList.forEach(::println)
    }

    fun addUserToList() {
        print("Введите имя: ")
        val firstName = readln()
        print("Введите фамилию: ")
        val lastName = readln()
        print("Введите возраст (целое число): ")
        val age = readln().toInt()
        val user = User(generateId(), firstName, lastName, age)
        usersList.add(user)
        usersList.forEach(::println)
        _users.currentValue = usersList.toList()
        if (age > _oldestUser.currentValue.age) {
            _oldestUser.currentValue = user // чтоьы в текущем режиме отслеживать самого старшего пользователя

        }
    }

    /**
     * Удаление пользователя из списка по указанному ID
     */
    fun deleteUserFromList() {
        print("Введите ID пользователя, которого нужно удалить: ")
        val id = readln().toInt()
        usersList.removeIf { it.id == id }
        usersList.forEach(::println)
        _users.currentValue = usersList.toList()
        val newOldest = usersList.maxBy { it.age }
        if (newOldest != _oldestUser.currentValue) {
            _oldestUser.currentValue = newOldest
        }
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
        private var instance: UserRepoObserver_6_11? =
            null // создание экземпляра через нуаллабельное значение переменной

        fun getInstanceUserRepository(password: String): UserRepoObserver_6_11 {
            val correctPassword = File("userPassword.txt").readText().trim()
            if (correctPassword != password) throw IllegalArgumentException("Неправильный пароль!")

            instance?.let { return it } // используем функциональный стиль ДЛЯ ДВОЙНОЙ ПРОВЕРКИ
            synchronized(lock) {
                instance?.let { return it } // с использованием функционального стиля
                return UserRepoObserver_6_11().also { instance = it }
            }
        }
    }
}