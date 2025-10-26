package users.usersWithoutObserver

import users.usersWithoutObserver.UserRepository

fun main() {

    UserRepository.Companion.getInstanceUserRepository("qwerty").users.forEach (::println)
    UserDisplay().showWindow()

    Administrator().work()

}