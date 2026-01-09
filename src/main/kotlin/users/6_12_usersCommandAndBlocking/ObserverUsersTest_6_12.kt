
import users.`6_12_usersCommandAndBlocking`.UserDisplayForObserver_6_12
import users.`6_12_usersCommandAndBlocking`.UserDisplayOldest_6_12
import users.`6_12_usersCommandAndBlocking`.UserRepoObserver_6_12


fun main() {

    UserRepoObserver_6_12.getInstanceUserRepository("qwerty")
    UserDisplayForObserver_6_12().showWindow()
    AdministratorObserver_6_12().work()
}