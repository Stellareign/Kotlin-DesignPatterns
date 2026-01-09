package users.`6_10_usersWithMutableObserver`

fun main() {

    UserRepositoryObserver_6_10.getInstanceUserRepository("qwerty")

//    UserDisplayForObserver_6_10().showWindow()
    UserDisplayOldest_6_10().showWindow()

    AdministratorObserver_6_10().work()

}