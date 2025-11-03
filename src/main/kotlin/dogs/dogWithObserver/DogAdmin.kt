package dogs.dogWithObserver

import com.sun.jndi.url.dns.dnsURLContext
import dogs.Dog
import dogs.DogsOperations
import users.Operations
import kotlin.system.exitProcess

class DogAdmin {
    private val operations = DogsOperations.entries
    private val dogRepo = DogRepositoryWithObserver.getInstanceDogRepository("qwertyu")


    fun work() {
        println("Введите номер команды: ")
        punctuations()
        while (true) {
            val commandIndex = readln().toInt()
            val command = operations[commandIndex]
            when (command) {
                DogsOperations.EXIT -> {
                    dogRepo.rewriteDogFile()
//                    break
                    exitProcess(0)
                }

                DogsOperations.ADD_DOG -> addDog()
                DogsOperations.DELETE_DOG -> deleteDog()
                DogsOperations.SHOW_ALL_DOG_LIST -> dogRepo.showAllDogs()
            }

        }
    }
    fun addDog() {
        print("Введите имя собаки: ")
        val dogName = readln()
        print("Укажите возраст собаки (целое число): ")
        val dogAge = readln().toInt()
        print("Укажите породу собаки: ")
        val dogBreed = readln()
        print("Укажите окрас собаки: ")
        val dogColor = readln()
        print("Укажите вес собаки(десятичная дробь): ")
        val dogWeight = readln().toDouble()
        dogRepo.addDogToList(dogName, dogAge, dogBreed, dogColor, dogWeight)

    }
    fun deleteDog() {
        print("Введи ID: ")
        dogRepo.deleteDogFromList(readln().toInt())
    }

    private fun punctuations() {
        for ((index, operation) in operations.withIndex()) {

            print("$index -${operation.title}")
            if (index == operations.lastIndex) {
                print(": ")
            } else {
                print(", ")
            }
        }
    }


}

