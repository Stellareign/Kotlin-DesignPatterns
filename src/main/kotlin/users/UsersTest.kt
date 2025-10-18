package users

import java.io.File

fun main() {

    UserRepository.getInstanceUserRepository("qwerty").users.forEach (::println)
}