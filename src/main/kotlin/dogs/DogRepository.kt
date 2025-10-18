package dogs

import kotlinx.serialization.json.Json
import java.io.File

class DogRepository private constructor() {
    private val dogFile = File("dogs.json")
    private val _dogs: MutableList<Dog> = loadDogList(dogFile)

    val dogs: List<Dog>
        get() = _dogs.toList()

    private fun loadDogList(file: File): MutableList<Dog> = Json.decodeFromString(file.readText().trim())

    companion object { // для создания паттерна singleton
        private var instance: DogRepository? = null

        fun getInstanceDogRepository(password: String): DogRepository {
            val correctDogsPassword = File("dogPassword.txt").readText().trim()
            if (correctDogsPassword != password)
                throw IllegalArgumentException("Неправильный пароль")

            if (instance == null) {
                instance = DogRepository()
            }
            return instance!!
        }
    }
}