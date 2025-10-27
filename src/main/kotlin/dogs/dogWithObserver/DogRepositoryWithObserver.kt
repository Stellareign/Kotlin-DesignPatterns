package dogs.dogWithObserver

import dogs.Dog
import kotlinx.serialization.json.Json
import java.io.File

class DogRepositoryWithObserver private constructor() {
    private val dogFile = File("dogs.json")
    private val _dogs: MutableList<Dog> =Json.Default.decodeFromString(dogFile.readText().trim())
    private val dogObservers = mutableListOf<DogDisplayWithObserver>()

    private fun notifyObserver() {
        for(observer in dogObservers){
            observer.dogsOnChanged(dogs)
        }
    }
    fun addDogObserver(observer: DogDisplayWithObserver) {
        dogObservers.add(observer)
        observer.dogsOnChanged(dogs)
    }

    val dogs: List<Dog>
        get() = _dogs.toList()

       fun addDogToList(name: String, age: Int, breed: String, color: String, weight: Double) {
        _dogs.add(generateDog(name, age, breed, color, weight))
           notifyObserver()
    }

    fun deleteDogFromList(id: Int) {
        _dogs.removeIf { id == it.id }
        notifyObserver()
    }

    private fun generateDog(name: String, age: Int, breed: String, color: String, weight: Double): Dog {
        val id = _dogs.maxByOrNull { it.id }?.id ?: 0
        return Dog(id, name, age, breed, color, weight)

    }
    fun rewriteDogFile() {
        dogFile.writeText(Json.encodeToString(dogs))
    }
    fun showAllDogs() {
        dogs.forEach(::println)
    }

    /**
     * позволяет получать доступ к полям приватного класса
     */
    companion object { // для создания паттерна singleton

        private val lock = Any() // мьютер
        private var instance: DogRepositoryWithObserver? =
            null // создание экземпляра через нуаллабельное значение переменной

        fun getInstanceDogRepository(password: String): DogRepositoryWithObserver {
            val correctDogsPassword = File("dogPassword.txt").readText().trim()
            if (correctDogsPassword != password)
                throw IllegalArgumentException("Неправильный пароль")
            instance?.let { return it } // двойная проврка


            synchronized(lock) {
                instance?.let { return it }
                return DogRepositoryWithObserver().also { instance = it }
            }
        }
    }
}