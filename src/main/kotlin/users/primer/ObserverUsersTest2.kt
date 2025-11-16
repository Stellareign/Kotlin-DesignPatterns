package users.primer

import users.usersWithObserver_6_6.AdministratorObserver
import users.usersWithObserver_6_6.UserDisplayForObserver
import users.usersWithObserver_6_6.UserRepositoryObserver

fun main() {

    UserRepositoryObserver2.Companion.getInstanceUserRepository("qwerty")

    UserDisplayForObserver2().showWindow()
    UserDisplayForObserver2().showWindow()

    AdministratorObserver().work()

}