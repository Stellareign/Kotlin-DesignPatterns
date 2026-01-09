package dogs.dogWithMutableObservableInt_6_10


import MutableObserver
import dogs.Dog
import kotlinx.serialization.json.Json
import java.io.File


class DogRepositoryWithObserver_6_10 private constructor() {
    private val dogFile = File("dogs.json")
    private val _dogs: MutableList<Dog> = Json.Default.decodeFromString(dogFile.readText().trim())

    val dogs = MutableObserver(_dogs.toList())


    fun addDogToList(name: String, age: Int, breed: String, color: String, weight: Double) {
        _dogs.add(generateDog(name, age, breed, color, weight))
        dogs.currentValue = _dogs.toList()
    }

    fun deleteDogFromList(id: Int) {
        _dogs.removeIf { id == it.id }
        dogs.currentValue = _dogs.toList()
    }

    private fun generateDog(name: String, age: Int, breed: String, color: String, weight: Double): Dog {
        val id = _dogs.maxOf { it.id } + 1
        return Dog(id, name, age, breed, color, weight)

    }

    fun rewriteDogFile() {
        dogFile.writeText(Json.encodeToString(dogs.currentValue))
    }

    fun showAllDogs() {
        dogs.currentValue.forEach(::println)
    }

    /**
     * позволяет получать доступ к полям приватного класса
     */
    companion object { // для создания паттерна singleton

        private val lock = Any() // мьютер
        private var instance: DogRepositoryWithObserver_6_10? =
            null // создание экземпляра через нуаллабельное значение переменной

        fun getInstanceDogRepository(password: String): DogRepositoryWithObserver_6_10 {
            val correctDogsPassword = File("dogPassword.txt").readText().trim()
            if (correctDogsPassword != password)
                throw IllegalArgumentException("Неправильный пароль")
            instance?.let { return it } // двойная проврка


            synchronized(lock) {
                instance?.let { return it }
                return DogRepositoryWithObserver_6_10().also { instance = it }
            }
        }
    }
}