package users.usersWithMutableObserver_6_10

import MutableObserver
import kotlinx.serialization.json.Json
import users.User
import java.io.File

class UserRepositoryObserver_6_10 private constructor() {

    private val usersFile = File("users.json")
    private val _users: MutableList<User> = loadUsersProfiles(usersFile)

    //создаём объект МутаблОбзервер и кладём в него коллекцию пользователй
    val users = MutableObserver(_users.toList())

    val oldestUser = MutableObserver(_users.maxBy { it.age })

    private fun loadUsersProfiles(file: File): MutableList<User> = Json.decodeFromString(file.readText().trim())

    private fun generateId(): Int {
        return _users.maxOf { it.id } + 1
    }

    fun rewriteUserFile() {
        usersFile.writeText(Json.encodeToString(_users))
    }

    fun showAlUsers() {
        _users.forEach(::println)
    }

    fun addUserToList() {
        print("Введите имя: ")
        val firstName = readln()
        print("Введите фамилию: ")
        val lastName = readln()
        print("Введите возраст (целое число): ")
        val age = readln().toInt()
        val user = User(generateId(), firstName, lastName, age)
        _users.add(user)
        _users.forEach(::println)
        users.currentValue = _users.toList()
        if (age > oldestUser.currentValue.age) {
            oldestUser.currentValue = user // чтоьы в текущем режиме отслеживать самого старшего пользователя

        }
    }

    /**
     * Удаление пользователя из списка по указанному ID
     */
    fun deleteUserFromList() {
        print("Введите ID пользователя, которого нужно удалить: ")
        val id = readln().toInt()
        _users.removeIf { it.id == id }
        _users.forEach(::println)
        users.currentValue = _users.toList()
        val newOldest = _users.maxBy { it.age }
        if(newOldest!= oldestUser.currentValue){
            oldestUser.currentValue = newOldest
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
        private var instance: UserRepositoryObserver_6_10? =
            null // создание экземпляра через нуаллабельное значение переменной

        fun getInstanceUserRepository(password: String): UserRepositoryObserver_6_10 {
            val correctPassword = File("userPassword.txt").readText().trim()
            if (correctPassword != password) throw IllegalArgumentException("Неправильный пароль!")

            instance?.let { return it } // используем функциональный стиль ДЛЯ ДВОЙНОЙ ПРОВЕРКИ
            synchronized(lock) {
                instance?.let { return it } // с использованием функционального стиля
                return UserRepositoryObserver_6_10().also { instance = it }
            }
        }
    }
}