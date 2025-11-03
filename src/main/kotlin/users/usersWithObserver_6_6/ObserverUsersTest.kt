package users.usersWithObserver_6_6

fun main() {

    UserRepositoryObserver.getInstanceUserRepository("qwerty")

    UserDisplayForObserver().showWindow()
    UserDisplayForObserver().showWindow()

    AdministratorObserver().work()

}