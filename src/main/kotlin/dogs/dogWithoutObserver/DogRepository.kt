package dogs.dogWithoutObserver

import dogs.Dog
import kotlinx.serialization.json.Json
import java.io.File

class DogRepository private constructor() {
    private val dogFile = File("dogs.json")
    private val _dogs: MutableList<Dog> = loadDogList(dogFile)

    val dogs: List<Dog>
        get() = _dogs.toList()

    private fun loadDogList(file: File): MutableList<Dog> = Json.Default.decodeFromString(file.readText().trim())

    /**
     * позволяет получать доступ к полям приватного класса
     */
    companion object { // для создания паттерна singleton
        /**
         * lateinit - отсроченная инициализация, если разработчик уверен, что переменная будет инициализирована
         * чаще используется при библиотечных автоматических инъекциях зависимостей
         */
//        private lateinit var instance: DogRepository //
        private val lock = Any() // мьютер
        private var instance: DogRepository? = null // создание экземпляра через нуаллабельное значение переменной

        fun getInstanceDogRepository(password: String): DogRepository {
            val correctDogsPassword = File("dogPassword.txt").readText().trim()
            if (correctDogsPassword != password)
                throw IllegalArgumentException("Неправильный пароль")
            instance?.let { return it } // двойная проврка

//            if (!::instance.isInitialized) {
//                instance = DogRepository()
//            }
            synchronized(lock) {
                instance?.let { return it }
                return DogRepository().also { instance = it }
            }
//            if (instance == null) {
//                instance = DogRepository()
//            }
//            return instance!!
        }
    }
}