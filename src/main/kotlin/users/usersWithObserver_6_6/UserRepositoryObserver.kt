package users.usersWithObserver_6_6

import Observer.Observable
import Observers.Observer

import kotlinx.serialization.json.Json
import users.User
import java.io.File

class UserRepositoryObserver private constructor() : Observable<List<User>> {

    private val usersFile = File("users.json")
    private val _users: MutableList<User> = loadUsersProfiles(usersFile)
//    val users
//        get() = _users.toList()

    private fun loadUsersProfiles(file: File): MutableList<User> = Json.decodeFromString(file.readText().trim())

    /**
     * Список подписчиков
     */
    private val _observers = mutableListOf<Observer<List<User>>>()
    override val observers
        get() = _observers.toList()

    override val currentValue: List<User>
        get() = _users.toList()



    /**
     * Уведомление подписчиков
     */
    override fun notifyObservers() {
        super.notifyObservers()
    }
//    fun notifyObserver() {
//        for (observer in observers) {
//            observer.onChanged(users)
//        }
//    }

    /**
     *   Добавление подписчиков
     */
    override fun registerObserver(observer: Observer<List<User>>) {
        _observers.add(observer)
        observer.onChanged(currentValue)
    }
// оставляем оба метода
    fun addOnUsersChangedListener(observer: Observer<List<User>>) {
       registerObserver(observer)
    }
//        var display: UserDisplayForObserver? = null // подписчик
//        set(value) { // для вывода данных на экран при старте (в момент подписки)
//            field = value
//            display?.onChanged(users)
//    }

    /**
     * Удаление подписчиков
     */

    private fun generateId(): Int {
        return currentValue.maxOf { it.id } + 1
    }

    override fun unregisterObserver(observer: Observer<List<User>>) {
       _observers.remove(observer)
    }

    fun rewriteUserFile() {
        usersFile.writeText(Json.encodeToString(_users))
    }

    fun generateUser(firstName: String, lastName: String, age: Int): User {
        return User(generateId(), firstName, lastName, age)
    }

    fun showAlUsers() {
        currentValue.forEach(::println)
    }

    fun addUserToList() {
        print("Введите имя: ")
        val firstName = readln()
        print("Введите фамилию: ")
        val lastName = readln()
        print("Введите возраст (целое число): ")
        val age = readln().toInt()
        _users.add(generateUser(firstName, lastName, age))
        currentValue.forEach(::println)
//        display?.onChanged(users)
        notifyObservers()
    }

    /**
     * Удаление пользователя из списка по указанному ID
     */
    fun deleteUserFromList() {
        print("Введите ID пользователя, которого нужно удалить: ")
        val id = readln().toInt()
        _users.removeIf { it.id == id }
        currentValue.forEach(::println)
//        display?.onChanged(users)
        notifyObservers()
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
        private var instance: UserRepositoryObserver? =
            null // создание экземпляра через нуаллабельное значение переменной

        fun getInstanceUserRepository(password: String): UserRepositoryObserver {
            val correctPassword = File("userPassword.txt").readText().trim()
            if (correctPassword != password) throw IllegalArgumentException("Неправильный пароль!")

            instance?.let { return it } // используем функциональный стиль ДЛЯ ДВОЙНОЙ ПРОВЕРКИ
            synchronized(lock) {
                instance?.let { return it } // с использованием функционального стиля
                return UserRepositoryObserver().also { instance = it }
            }
        }
    }
}