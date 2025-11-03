package tasks.task_6_6_Observer_start

import users.usersWithObserver_6_6.UserRepositoryObserver

import users.usersWithoutObserver.UserRepository
import java.io.File


fun main() {
    val ur = UserRepository()
    val logger = UserLogger()
    ur.subscribe(logger)
    val logger2 = UserLogger()
    ur.subscribe(logger2)
    ur.addUser("Alice")
    ur.addUser("Bob")
    ur.addUser("Charly")
    ur.removeUser("Alice")
}

class UserRepository {
    private val users = mutableListOf<String>()


    // TODO: Добавить список подписчиков (наблюдателей) UserLogger
    private val observersList = mutableListOf<UserLogger>()

    fun addUser(user: String) {
        users.add(user)
//        for (user in users) println("Adding user $user ${user.indexOf(user)}")
        // TODO: уведомить подписчиков об изменении списка пользователей
        notifyObserver()
    }

    fun removeUser(user: String) {
        users.remove(user)
        // TODO: уведомить подписчиков об изменении списка пользователей
        notifyObserver()
    }

    // TODO: Реализовать метод подписки
    fun subscribe(logger: UserLogger) {
        observersList.add(logger)
        logger.onUsersChanged(users)
    }

    // TODO: Реализовать метод отписки
    fun unsubscribe(logger: UserLogger) {
        observersList.remove(logger)
    }

    // TODO: Реализовать метод уведомления подписчиков
    fun notifyObserver() {
        for (observer in observersList) {
            observer.onUsersChanged(users)
        }
    }
}

// Класс наблюдателя, который подписывается на изменения в UserRepository
class UserLogger {
    // TODO: Реализовать метод onUsersChanged(), который выводит сообщение в консоль в формате:
    // [LOG] Пользователи обновлены: <список пользователей>
    fun onUsersChanged(users: List<String>) {
        println("[LOG] Пользователи обновлены: $users")
        // users.forEach(::print)
    }

}

