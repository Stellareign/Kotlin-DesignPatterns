package dogs.dogWithMutableObservableInt_6_11


import MutableObservable
import Observer.Observable
import dogs.Dog
import kotlinx.serialization.json.Json
import java.io.File

class DogRepoObserver_6_11 private constructor() {
    private val dogFile = File("dogs.json")
    private val dogsList: MutableList<Dog> = Json.Default.decodeFromString(dogFile.readText().trim())
    private val _dogs : MutableObservable<List<Dog>> = MutableObservable(dogsList.toList())

    val dogs : Observable<List<Dog>>
    get() = _dogs


    fun addDogToList(name: String, age: Int, breed: String, color: String, weight: Double) {
        dogsList.add(generateDog(name, age, breed, color, weight))
        _dogs.currentValue = dogsList.toList()
    }

    fun deleteDogFromList(id: Int) {
        dogsList.removeIf { id == it.id }
        _dogs.currentValue = dogsList.toList()
    }

    private fun generateDog(name: String, age: Int, breed: String, color: String, weight: Double): Dog {
        val id = dogsList.maxOf { it.id } + 1
        return Dog(id, name, age, breed, color, weight)

    }

    fun rewriteDogFile() {
        dogFile.writeText(Json.encodeToString(_dogs.currentValue))
    }

    fun showAllDogs() {
        _dogs.currentValue.forEach(::println)
    }

    /**
     * позволяет получать доступ к полям приватного класса
     */
    companion object { // для создания паттерна singleton

        private val lock = Any() // мьютер
        private var instance: DogRepoObserver_6_11? =
            null // создание экземпляра через нуаллабельное значение переменной

        fun getInstanceDogRepository(password: String): DogRepoObserver_6_11 {
            val correctDogsPassword = File("dogPassword.txt").readText().trim()
            if (correctDogsPassword != password)
                throw IllegalArgumentException("Неправильный пароль")
            instance?.let { return it } // двойная проврка


            synchronized(lock) {
                instance?.let { return it }
                return DogRepoObserver_6_11().also { instance = it }
            }
        }
    }
}