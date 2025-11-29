package users.example

import users.usersWithObserver_6_6.AdministratorObserver

fun main() {

    UserRepositoryObserver2.getInstanceUserRepository("qwerty")


    UserDisplayForObserver2().showWindow()
//    UserDisplayForObserver2().showWindow()
    UserDisplayOldest().showWindow()

    AdministratorObserver().work()

}